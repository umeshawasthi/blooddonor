package com.raisonne.bd.action.donor;




import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import net.sf.oval.constraint.AssertValid;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.Preparable;
import com.raisonne.bd.dto.donor.DonorCredentialDTO;
import com.raisonne.bd.dto.donor.DonorPreferencesDTO;
import com.raisonne.bd.dto.donor.DonorProfileDTO;
import com.raisonne.bd.dto.donor.DonorRolesDTO;
import com.raisonne.bd.dto.donor.EmailVerificationDTO;
import com.raisonne.bd.service.donor.DonorService;
import com.raisonne.bd.util.BloodGroupUtils;
import com.raisonne.bd.util.StateInfoUtils;


/**
 * This action class is written to handle all the registration related requests.
 * It makes use of respected service api for performing any operation.
 * @author Himanshu Durgapal
 * @version 1.0 15/11/2011
 * @since   1.0
 * 
 */

public class DonorRegistration extends BaseAction implements ServletContextAware,Preparable,ServletRequestAware{

	/**
	 * Class level property decelerations.
	 */
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(DonorRegistration.class);
	private DonorService donorService;
	@AssertValid
	private DonorProfileDTO donorProfileDTO;
	@AssertValid
	private DonorPreferencesDTO donorPreferencesDTO;
	@AssertValid
	private DonorCredentialDTO donorCredentialDTO;	
	private DonorRolesDTO donorRolesDTO;
	private Map<String, String> states;
	private List<String> bloodGroups;
	private String state;
	private List<String> districts;
	private String user_gender="male";
	private boolean readonly=false;
	private boolean registrationPage;
	private ServletContext context;
	private String message;
	
	/**
	 * This variable will be used to set working template.
	 * Developer need to set working template here,application will pick it
	 * as per the given structure.
	 */
	private String workingTemplate=null;
	
	/**
	 * This method is implemented to register or save donor profile .
	 * @return Returns string result based on result of action performed.
	 * 
	 */
	public String registerDonor() 
	{
		Boolean result=false;
		setTitle("Save A Life Today| Donor Registration");
		EmailVerificationDTO emailVerificationDTO=new EmailVerificationDTO();
		getListData();
		try {
			donorCredentialDTO.setDonorEmail(donorProfileDTO.getEmail());
			donorProfileDTO.setDonorPreferences(donorPreferencesDTO);
			donorProfileDTO.setDonorRoles(donorRolesDTO);
			if(donorProfileDTO.isOauth())
			{
				DonorCredentialDTO donorCredential=new DonorCredentialDTO();
				donorCredential.setDonorEmail(donorProfileDTO.getEmail());
				result=donorService.saveDonor(donorProfileDTO, donorCredential);
			}
			else
			{
				emailVerificationDTO.setEmail(donorProfileDTO.getEmail());
				result=donorService.saveDonor(donorProfileDTO, donorCredentialDTO,emailVerificationDTO,donorRolesDTO,context);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(result && donorProfileDTO.isOauth())
		{
			message="Your account is created successfully Please login to your account.";
			setWorkingTemplate("/WEB-INF/templates/donor/donorConfirmation.jsp");
			setRegistrationPage(false);
			return SUCCESS;
		}
		if(result){
			message="Your account is created successfully and a verification email is sent to your email id.<br/> Please execute the url(sent in mail) to activate and login to your account.";
			setWorkingTemplate("/WEB-INF/templates/donor/donorConfirmation.jsp");
			setRegistrationPage(false);
			return SUCCESS;
		  
		}
		
		message="Unable to create account. Please contact system administrator.";
		setWorkingTemplate("/WEB-INF/templates/donor/donorRegistration.jsp");
		return INPUT;
	}
	
	public Map<String, String> getStates() {
		return states;
	}

	public void setStates(Map<String, String> states) {
		this.states = states;
	}

	public List<String> getBloodGroups() {
		return bloodGroups;
	}

	public void setBloodGroups(List<String> bloodGroups) {
		this.bloodGroups = bloodGroups;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<String> getDistricts() {
		return districts;
	}

	public void setDistricts(List<String> districts) {
		this.districts = districts;
	}

	
	
	/**
	 * This method is implemented to redirect to registration page and populate blood group
	 *  and state drop down in jsp page . 
	 * @return Returns string result and perform action based on the result.
	 */
	public String getListData()
	{	
		 log.info("Starting to populate lists for drop downs");
		 /*
		  * getting list of blood groups
		  */
		   bloodGroups=BloodGroupUtils.getAvailableBloodGroups();
		   /*
		    * getting state list.
		    */
		   states=StateInfoUtils.getStates();
		   districts=new ArrayList<String>();
		   setWorkingTemplate("/WEB-INF/templates/donor/donorRegistration.jsp");
		   setTitle("Save A Life Today| Register New Blood Donor");
		   log.info("Working tempelate is "+getWorkingTemplate());
		   setRegistrationPage(true);
		   return SUCCESS;
	}
	
	
	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}

	

	public DonorService getDonorService() {
		return donorService;
	}

	public DonorProfileDTO getDonorProfileDTO() {
		return donorProfileDTO;
	}

	public void setDonorProfileDTO(DonorProfileDTO donorProfileDTO) {
		this.donorProfileDTO = donorProfileDTO;
	}

	public DonorPreferencesDTO getDonorPreferencesDTO() {
		return donorPreferencesDTO;
	}

	public void setDonorPreferencesDTO(DonorPreferencesDTO donorPreferencesDTO) {
		this.donorPreferencesDTO = donorPreferencesDTO;
	}

	public DonorCredentialDTO getDonorCredentialDTO() {
		return donorCredentialDTO;
	}

	public void setDonorCredentialDTO(DonorCredentialDTO donorCredentialDTO) {
		this.donorCredentialDTO = donorCredentialDTO;
	}

	public String getUser_gender() {
		return user_gender;
	}

	public boolean isReadonly() {
		return readonly;
	}

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

	@Override
	public void setServletContext(ServletContext context) {
		this.context=context;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void prepare() throws Exception {
		getListData();

		String key = "donorProfileDTO.state";
		final String state = (String) request.getParameter(key);
		if (StringUtils.isNotBlank(state)) {
				setDistricts(StateInfoUtils.getDistricts().get(state));
		
		}
	}

	public DonorRolesDTO getDonorRolesDTO() {
		return donorRolesDTO;
	}

	public void setDonorRolesDTO(DonorRolesDTO donorRolesDTO) {
		this.donorRolesDTO = donorRolesDTO;
	}

	
	protected HttpServletRequest request = null;

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	
	
	
}
