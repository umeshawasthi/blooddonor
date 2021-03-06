/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.oauth.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.expressme.openid.Authentication;
import org.expressme.openid.OpenIdException;
import org.expressme.openid.OpenIdManager;

import com.raisonne.bd.action.donor.BaseAction;
import com.raisonne.bd.constant.DonorServiceConstants;
import com.raisonne.bd.constant.OAuthConstants;
import com.raisonne.bd.dto.donor.DonorProfileDTO;
import com.raisonne.bd.service.donor.DonorService;
import com.raisonne.bd.util.BloodGroupUtils;
import com.raisonne.bd.util.StateInfoUtils;
import com.raisonne.oauth.config.OAuthSignInException;
import com.raisonne.oauth.data.OAuthUserProfileData;
import com.raisonne.oauth.data.parser.JsonDataParser;
import com.raisonne.oauth.service.OAuthServiceProvider;
import com.raisonne.oauth.service.api.OauthClientAPI;

/**
 * Callack handler class for OAuth system.This implimenation will be responsible
 * for handling callback and will communicate with OAuth system to access user
 * public profile. It will also take care of parsing json/xml data being
 * received from OAuth system and will convert to {@link OAuthUserProfileData}.
 * This implimentation will take care of auto login for already exisitng user
 * and will redirect new user to registration page with basic data being
 * obtained from OAuth system currently it is capable of handling callback from
 * <li>Google</li> <li>Yahoo</li> <li>Facebook</li> <li>Window Live</li> <li>
 * LinkedIn</li>
 * 
 * @author Umesh Awasthi
 * @version 1.0
 * @since 06-12-2011
 * 
 */
public class OAuthCallbackHandlerAction extends BaseAction implements
		SessionAware {

	/**
	 * Class level property decelarations
	 */
	private Logger log = Logger.getLogger(OAuthCallbackHandlerAction.class);
	private static final long serialVersionUID = 1L;
	Map<String, Object> session = null;
	private String oauth_verifier;
	private String code;
	private OAuthServiceProvider oAuthServiceProvider;
	private String service_provider_name;
	private DonorService donorService;
	private DonorProfileDTO donorProfileDTO;
	private JsonDataParser jsonDataParser;
	private OAuthUserProfileData authUserProfileData;
	private boolean registrationPage;

	public String execute() throws Exception {
		log.info("Preparing to handle call back from " + service_provider_name);
		authUserProfileData = new OAuthUserProfileData();

		service_provider_name = (String) session
				.get(OAuthConstants.SERVICE_PROVIDER_NAME);
		if(service_provider_name.equalsIgnoreCase("yahoo")){
			authUserProfileData =handleYahooCallBack();
		}
		
		else{
		OauthClientAPI oAuthClient = oAuthServiceProvider
				.getSelectedOAuthClientAPI(StringUtils
						.lowerCase(service_provider_name));
		OAuthClientRequest request = oAuthClient.getAccessTokenOAuthObj(code);
		authUserProfileData = oAuthClient.getUserProfileData(request);
		if (authUserProfileData.getEmail() == null) {
			if (MapUtils.isNotEmpty(authUserProfileData.getEmails())) {
				if (authUserProfileData.getEmails().get(
						OAuthConstants.ACCOUNT_EMAIL) != null) {
					authUserProfileData.setEmail(authUserProfileData
							.getEmails().get(OAuthConstants.ACCOUNT_EMAIL));
				} else if (authUserProfileData.getEmails().get(
						OAuthConstants.PREFERRED_EMAIL) != null) {
					authUserProfileData.setEmail(authUserProfileData
							.getEmails().get(OAuthConstants.PREFERRED_EMAIL));
				} else if (authUserProfileData.getEmails().get(
						OAuthConstants.PERSONAL_EMAIL) != null) {
					authUserProfileData.setEmail(authUserProfileData
							.getEmails().get(OAuthConstants.PERSONAL_EMAIL));
				}
			}
		}
		}
		session.remove(OAuthConstants.SERVICE_PROVIDER_NAME);
		session.remove(OAuthConstants.REQUEST_TOKEN);
		session.clear();
		donorProfileDTO = checkUserProfile(authUserProfileData.getEmail());
		if (authUserProfileData.isNewUser()) {
			log.info("User do not have associated profile with us.Redirecting to registration page");
			donorProfileDTO = new DonorProfileDTO();
			donorProfileDTO.setEmail(authUserProfileData.getEmail());
			donorProfileDTO.setName(authUserProfileData.getName());
			donorProfileDTO.setOauth(true);
			if (authUserProfileData.getGender() != null) {
				donorProfileDTO.setGender(authUserProfileData.getGender());
				user_gender = authUserProfileData.getGender();
			} else {
				readonly = false;
				user_gender = "male";
			}
			prepareDropDownData();
			log.info("Setting working tempelate as donorRegistration.jsp");
			setTitle("Save A Life Today|Donor Registration");
			setWorkingTemplate("/WEB-INF/templates/donor/donorRegistration.jsp");
			setRegistrationPage(true);
			return "register_new_user";
		} else {
			log.info("User has a active profile. Starting autologin process");
			session.put(DonorServiceConstants.USER, donorProfileDTO);
			session.put(DonorServiceConstants.IS_Logged_In, true);
			return "user_validated";
		}

	}
	
	

	/**
	 * This method will be responsible for checking user in our domain based on
	 * the email id provided by the OAuth service provider and if user exist
	 * will fetch user and construct user profile place user object to the
	 * session and will redirect user to the success page. In case user will not
	 * be available in the system, it will take care of setting a flag
	 * indicating system that the user is new and will initiate the registration
	 * process.This method will take care of providing verified data obtained
	 * from the OAuth system which includes <li>user email</li> <li>user name</li>
	 * <li>gender (can be null also)</li>
	 * 
	 * @param email
	 *            email id of the user
	 * @throws OAuthSignInException
	 */
	private DonorProfileDTO checkUserProfile(String email)
			throws OAuthSignInException {
		log.info("Initiating process to validate user with the underlying system");
		if (email == null) {
			log.error("Email is null, make sure OAuth service is working fine");
			throw new OAuthSignInException(
					"There seems some problem in the OAuth handler, user email can not be null");
		}
		DonorProfileDTO donorProfileDTO = null;
		try {
			donorProfileDTO = donorService.loadDonorByEmailId(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (donorProfileDTO != null) {
			if (donorProfileDTO.getEmail().equals(email)) {
				authUserProfileData.setNewUser(false);
			}
			return donorProfileDTO;
		} else {
			authUserProfileData.setNewUser(true);
			return null;
		}
	}
	
	
	
	 /**
     * Method responsible to handle call back from Yahoo Oauth server. This
     * method will take care to get Access Token from the Yahoo services and get
     * user profile. User profile will be parsed from JSON to Java object and
     * will be handed over the standard sign process.
     *
     * @return {@link OAuthUserProfileData}
     * @throws OAuthSignInException
     */
    private OAuthUserProfileData handleYahooCallBack()
                    throws OAuthSignInException {
    		String callback_URL=BloodGroupUtils.getParameter("app.callback.url");
            HttpServletRequest request = ServletActionContext.getRequest();
            byte[] mac_key = (byte[]) session.get(OAuthConstants.ATTR_MAC);
            String alias = (String) session.get(OAuthConstants.ATTR_ALIAS);
            OpenIdManager manager = new OpenIdManager();
            manager.setRealm(OAuthConstants.YAHOO_OPENID_RELEAM);
            manager.setReturnTo(callback_URL + service_provider_name);
            // request.getAttribute(name)
            Authentication auth = manager
                            .getAuthentication(request, mac_key, alias);
            if (auth.getEmail() == null) {
                    log.error("Yahoo OAuth failed");
                    throw new OAuthSignInException(
                                    "There seems some problem in authenticate user with "
                                                    + service_provider_name);
            }
            OAuthUserProfileData authUserProfileData = new OAuthUserProfileData();
            if (auth.getEmail() != null)
                    authUserProfileData.setEmail(auth.getEmail());
            if (auth.getFirstname() != null)
                    authUserProfileData.setName(auth.getFullname());
            if (auth.getGender() != null) {
                    if(auth.getGender().equals("M"))
                     authUserProfileData.setGender("male");
                    else
                            authUserProfileData.setGender("female");
            }

            return authUserProfileData;

    }
    

	public String getOauth_verifier() {
		return oauth_verifier;
	}

	public void setOauth_verifier(String oauth_verifier) {
		this.oauth_verifier = oauth_verifier;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		session = arg0;

	}

	public String getService_provider_name() {
		return service_provider_name;
	}

	public void setService_provider_name(String service_provider_name) {
		this.service_provider_name = service_provider_name;
	}

	

	public JsonDataParser getJsonDataParser() {
		return jsonDataParser;
	}

	public void setJsonDataParser(JsonDataParser jsonDataParser) {
		this.jsonDataParser = jsonDataParser;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public OAuthUserProfileData getAuthUserProfileData() {
		return authUserProfileData;
	}

	public void setAuthUserProfileData(OAuthUserProfileData authUserProfileData) {
		this.authUserProfileData = authUserProfileData;
	}

	public DonorProfileDTO getDonorProfileDTO() {
		return donorProfileDTO;
	}

	public void setDonorProfileDTO(DonorProfileDTO donorProfileDTO) {
		this.donorProfileDTO = donorProfileDTO;
	}

	public DonorService getDonorService() {
		return donorService;
	}

	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}

	/**
	 * A Private method to handle any replay attack from yahoo openID login
	 * mechanism.
	 */
	@SuppressWarnings("unused")
	private void checkNonce(String nonce) {
		// check response_nonce to prevent replay-attack:
		if (nonce == null || nonce.length() < 20)
			throw new OpenIdException("Verify failed.");
		// make sure the time of server is correct:
		long nonceTime = getNonceTime(nonce);
		long diff = Math.abs(System.currentTimeMillis() - nonceTime);
		if (diff > OAuthConstants.ONE_HOUR)
			throw new OpenIdException("Bad nonce time.");
		if (isNonceExist(nonce))
			throw new OpenIdException("Verify nonce failed.");

		// storeNonce(nonce, nonceTime + TWO_HOUR); need to store this in
		// database
	}

	/**
	 * 
	 * @param nonce
	 * @return flag indicating in this nonce already exists in database or not
	 */
	private boolean isNonceExist(String nonce) {
		return true;
	}

	private long getNonceTime(String nonce) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(
					nonce.substring(0, 19) + "+0000").getTime();
		} catch (ParseException e) {
			throw new OpenIdException("Bad nonce time.");
		}
	}

	

	private void prepareDropDownData() {
		log.info("Preparing drop down data for user registration");
		bloodGroups = BloodGroupUtils.getAvailableBloodGroups();
		states = StateInfoUtils.getStates();
		districts = new ArrayList<String>();
		districts.add("--select--");
	}

	private Map<String, String> states;
	private List<String> bloodGroups;
	private List<String> districts;
	private String user_gender;
	private boolean readonly = true;

	public Map<String, String> getStates() {
		return states;
	}

	public List<String> getBloodGroups() {
		return bloodGroups;
	}

	public List<String> getDistricts() {
		return districts;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public boolean isReadonly() {
		return readonly;
	}

	private String workingTemplate = null;

	public String getWorkingTemplate() {
		return workingTemplate;
	}

	public void setWorkingTemplate(String workingTemplate) {
		this.workingTemplate = workingTemplate;
	}

	public boolean isRegistrationPage() {
		return registrationPage;
	}

	public void setRegistrationPage(boolean registrationPage) {
		this.registrationPage = registrationPage;
	}

	public OAuthServiceProvider getoAuthServiceProvider() {
		return oAuthServiceProvider;
	}

	public void setoAuthServiceProvider(
			OAuthServiceProvider oAuthServiceProvider) {
		this.oAuthServiceProvider = oAuthServiceProvider;
	}

}
