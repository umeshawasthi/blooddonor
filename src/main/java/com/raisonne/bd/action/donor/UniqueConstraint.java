package com.raisonne.bd.action.donor;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.raisonne.bd.service.donor.DonorService;

/**
 * Action class responsible for verifying emailid which the user enters while registration
 * Action class will return a string  as a JSON data and jquery will be responsible
 * for parsing the JSON data and showing the status message .
 * 
 * @author Himanshu Durgapal
 * @version 1.0 12/12/2011
 * @since   1.0
 */

public class UniqueConstraint extends ActionSupport{
	
	/**
	 * Class level property declarations. 
	 */
	Logger log=Logger.getLogger(DropDownRenderer.class);	
	private static final long serialVersionUID = 1L;
	private DonorService donorService;
	private String uniqueIdStatus;
	private String emailId;
	
	/**
	 * This method will be responsible for checking mail id  in our system  based on the email id provided
	 * by checking it againest the mail id provided by user while registration  if mailid is already registered service will return false
	 * In case mai id  is not registered in syatem service will return true 
	 * Verify Email includes
	 * <li> user email </li>
	 * 
	 * 
	 * @param emailId email id of the user
	 * @throws Exception 
	 */
	
	public String verifyEmail() throws Exception
	{
		  log.info("checking uniqueness of mail id");		  
		  if(donorService.checkEmailId(emailId))
		  uniqueIdStatus="true";
		  else
			  uniqueIdStatus="false";
		  return SUCCESS;
	}
	
	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}

	public String getUniqueIdStatus() {
		return uniqueIdStatus;
	}

	public void setUniqueIdStatus(String uniqueIdStatus) {
		this.uniqueIdStatus = uniqueIdStatus;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
