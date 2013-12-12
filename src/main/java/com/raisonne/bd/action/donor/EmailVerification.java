package com.raisonne.bd.action.donor;

import org.apache.log4j.Logger;

import com.raisonne.bd.service.donor.DonorService;
/**
 * This action class is written to verify registered donor and changes the activation status of donor based on the input.
 * It makes use of respected service api for performing any operation.
 * @author Himanshu Durgapal
 * @version 1.0 30/12/2011
 * @since   1.0
 * 
 */
public class EmailVerification extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Class level property decelerations.
	 */
	Logger log = Logger.getLogger(EmailVerification.class);
	private String token;
	private DonorService donorService;
	private String message;
	/**
	 * This variable will be used to set working template.
	 * Developer need to set working template here,application will pick it
	 * as per the given structure.
	 */
	private String workingTemplate=null;
	
	/**
	 * This method is implemented to verify donor when a donor clicks on the verification link this method is executed 
	 * and change the donor status of donor to verified if the input token value matches the token value in database .
	 * @return Returns string result based on result of action performed.
	 * 
	 */
	public String execute() throws Exception
	{
		boolean result=false;
		try
		{
			result=donorService.updateVerifiedDonor(token);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		if(result)
		{
			/**
			 * Status message to be displayed in view. 
			 */
			message="activated";
		}
		else
		message="not activated";
		log.info("Setting working template");
		setWorkingTemplate("/WEB-INF/templates/donor/donorConfirmation.jsp");
		return SUCCESS;
	}
	
	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
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

}
