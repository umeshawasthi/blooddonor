package com.raisonne.bd.login.action;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.raisonne.bd.action.donor.BaseAction;
import com.raisonne.bd.constant.DonorServiceConstants;
import com.raisonne.bd.constant.OAuthConstants;
import com.raisonne.bd.converter.DonorConverter;
import com.raisonne.bd.dto.donor.DonorCredentialDTO;
import com.raisonne.bd.dto.donor.DonorProfileDTO;
import com.raisonne.bd.service.donor.DonorService;

/**
 * This action class is written to handle the login authentication of donor.
 * 
 * @author Vivek
 * 
 */
public class DonorLoginAction extends BaseAction implements SessionAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(DonorLoginAction.class);
	private DonorService donorService;
	private DonorCredentialDTO donorCredentialDTO;
	Map<String, Object> session=null;
	private String message;
	/**
	 * This variable will be used to set working tempelate. Developer need to
	 * set working tempelate here,application will pick it as per the given
	 * structure.
	 */
	private String workingTemplate = null;

	/**
	 * This method is written to authenticate the donor credentials for
	 * successful login by passing respected DonorProfileDTO instance to service
	 * API.
	 * 
	 * @return Returns success
	 */
	public String donorLoginAuthentication() {
		log.info("Starting to authenticate provided credentials");
		boolean result = donorService.loginAuthentication(donorCredentialDTO);
		if (result) {
			setSessionForUser(donorCredentialDTO);
			message = "User logged in successfully";
			setWorkingTemplate("/WEB-INF/templates/layout/slider.jsp");
			log.info("Working tempelate is " + getWorkingTemplate());
			return SUCCESS;
		} else {
			message = "credentials provided are wrong";
			setWorkingTemplate("/WEB-INF/templates/donor/loginoptions.jsp");
			log.info("Working tempelate is " + getWorkingTemplate());
			return ERROR;
		}
	}

	private void setSessionForUser(DonorCredentialDTO donorCredentialDTO) {
		String name="";
		try
		{
			name=donorService.loadDonorByEmailId(donorCredentialDTO.getDonorEmail()).getName();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		log.info("user has been put on session now for the email id "+donorCredentialDTO.getDonorEmail()+" and "+name);
		DonorProfileDTO donorProfileDto=null;
		try {
			donorProfileDto=donorService.loadDonorByEmailId(donorCredentialDTO.getDonorEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.put(DonorServiceConstants.USER, donorProfileDto);
		session.put(DonorServiceConstants.USER_CREDENTIAL, donorCredentialDTO);
    	session.put(DonorServiceConstants.IS_Logged_In, true);
		}
	
	
	public String donorLogout() {
		log.info("Donor Logout and session is invalidated");
		session.remove(DonorServiceConstants.IS_Logged_In);
		session.clear();
		
			message = "User logout successfully";
			setWorkingTemplate("/WEB-INF/templates/donor/loginoptions.jsp");
			log.info("Working tempelate is " + getWorkingTemplate());
			return SUCCESS;
		}
	public DonorService getDonorService() {
		return donorService;
	}

	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}

		
	
	public DonorCredentialDTO getDonorCredentialDTO() {
		return donorCredentialDTO;
	}

	public void setDonorCredentialDTO(DonorCredentialDTO donorCredentialDTO) {
		this.donorCredentialDTO = donorCredentialDTO;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getWorkingTemplate() {
		return workingTemplate;
	}

	public void setWorkingTemplate(String workingTemplate) {
		this.workingTemplate = workingTemplate;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;		
	}
	
	public Map<String, Object> getSession() {
		return session;
	}
	
}
