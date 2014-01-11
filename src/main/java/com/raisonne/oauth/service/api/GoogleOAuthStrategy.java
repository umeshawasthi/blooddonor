/**
 * 
 */
package com.raisonne.oauth.service.api;

import org.apache.commons.lang.StringUtils;
import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.OAuthProviderType;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.raisonne.bd.util.BloodGroupUtils;
import com.raisonne.oauth.config.OAuthSignInException;
import com.raisonne.oauth.data.OAuthUserProfileData;
import com.raisonne.oauth.data.parser.JsonDataParser;


/**
 * @author Admin
 *
 */
public class GoogleOAuthStrategy implements OauthClientAPI {

	@Autowired
	private JsonDataParser jsonDataParser;
	
	protected final static Logger LOG=LoggerFactory.getLogger(GoogleOAuthStrategy.class);
	private String callbackURL="";
	@Override
	public OAuthClientRequest getRedirectionObj(){
		OAuthClientRequest request=null;
		callbackURL=BloodGroupUtils.getParameter("app.callback.url");
		LOG.info("Found application call back URL as {} ",callbackURL);
		try {
			LOG.info("Creating Client request object for Google");
			request = OAuthClientRequest.authorizationProvider(OAuthProviderType.GOOGLE).
					  setClientId(BloodGroupUtils.getParameter(StringUtils.lowerCase(OAuthProviderType.GOOGLE.getProviderName())+".apiKey")).
					  setResponseType(BloodGroupUtils.getParameter(StringUtils.lowerCase(OAuthProviderType.GOOGLE.getProviderName())+".responseType")).
					  setScope(BloodGroupUtils.getParameter(StringUtils.lowerCase(OAuthProviderType.GOOGLE.getProviderName())+".scope")).
					  setParameter("approval_prompt", "force").
					  setRedirectURI(callbackURL).buildQueryMessage();
			
		} catch (OAuthSystemException e) {
			
			LOG.info("Error while creating OAuthClientRequest object for Google" , e);
		}
		return request;
	}
	
	@Override
	public OAuthClientRequest getAccessTokenOAuthObj(final String authorizationCode) {
		OAuthClientRequest request=null;
		callbackURL=BloodGroupUtils.getParameter("app.callback.url");
		try {
			LOG.info("Creating Client request object for Google");
			request = OAuthClientRequest.tokenProvider(OAuthProviderType.GOOGLE).
					  setGrantType(GrantType.AUTHORIZATION_CODE).
					  setClientId(BloodGroupUtils.getParameter(StringUtils.lowerCase(OAuthProviderType.GOOGLE.getProviderName())+".apiKey")).
					  setClientSecret(BloodGroupUtils.getParameter(StringUtils.lowerCase(OAuthProviderType.GOOGLE.getProviderName())+".apiSecret")).
					  setRedirectURI(callbackURL).
					  setCode(authorizationCode).buildBodyMessage();
			
		} catch (OAuthSystemException e) {
			
			LOG.info("Error while creating OAuthClientRequest object for Google" , e);
		}
		return request;
	}

	@Override
	public OAuthUserProfileData getUserProfileData(final OAuthClientRequest request) throws OAuthSignInException {
		LOG.info("Starting process to fetch acess token and user data");
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
		OAuthAccessTokenResponse oAuthResponse=null;
		try {
			
			oAuthResponse=oAuthClient.accessToken(request);
				if(oAuthResponse !=null){
					String accessToken = oAuthResponse.getAccessToken();
					Long expiresIn = oAuthResponse.getExpiresIn();
					LOG.info("Successfully fected response token and expirary value : {} {} ",accessToken,expiresIn);
					OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(BloodGroupUtils.getParameter(StringUtils.lowerCase(OAuthProviderType.GOOGLE.getProviderName())+".datarequest_url"))
			         .setAccessToken(accessToken).buildHeaderMessage();
					OAuthResourceResponse resourceResponse = oAuthClient.resource(bearerClientRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
					if (resourceResponse.getResponseCode() != 200) {
						LOG.error("OAuth failed, we have got the following error code "
								+ resourceResponse.getResponseCode());
						throw new OAuthSignInException(
								"There seems some problem in authenticate yourself with Google");
					}
					return (OAuthUserProfileData) jsonDataParser.parseJson(
							resourceResponse.getBody(), OAuthUserProfileData.class);
			}
			
			
		 
		} catch (OAuthSystemException e) {
			LOG.error("Error while fetching access token for Google..",e);
			
		} catch (OAuthProblemException e) {
			LOG.error("Error while fetching access token for Google..",e);
		}
		//GoogleTokenResponse tok;
		return null;
	}

	

}
