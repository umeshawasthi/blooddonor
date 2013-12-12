package com.raisonne.bd.action.donor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.oval.constraint.AssertValid;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.Preparable;
import com.raisonne.bd.constant.DonorServiceConstants;
import com.raisonne.bd.dto.donor.DonorCredentialDTO;
import com.raisonne.bd.dto.donor.DonorPreferencesDTO;
import com.raisonne.bd.dto.donor.DonorProfileDTO;
import com.raisonne.bd.dto.donor.DonorRolesDTO;
import com.raisonne.bd.service.donor.DonorService;
import com.raisonne.bd.util.BloodGroupUtils;
import com.raisonne.bd.util.StateInfoUtils;

/**
 * This action class is written to handle all the operations related to update
 * of donor profiles.
 * 
 * @author Arun
 * 
 */
public class DonorUpdate extends BaseAction implements SessionAware,Preparable{

	
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(DonorUpdate.class);
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
	private List<String> districts;
	private String message;
	private boolean donorUpdatePage;
	Map<String, Object> session=null;
	/**
	 * This variable will be used to set working tempelate. Developer need to
	 * set working tempelate here,application will pick it as per the given
	 * structure.
	 */
	private String workingTemplate = null;
	
	/**
	 * This method is written to load donor profile(to be updated) by email id.
	 * It also initializes the bloodgroups, states and districts lists to be
	 * rendered on UI.
	 * 
	 * @return Returns success
	 */
	public String getDonorProfileById() {
		try
		{
		donorProfileDTO = donorService.loadDonorByEmailId(((DonorProfileDTO)session.get(DonorServiceConstants.USER)).getEmail());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		donorPreferencesDTO = donorProfileDTO.getDonorPreferences();
		initLists();
		setDonorUpdatePage(true);
		setWorkingTemplate("/WEB-INF/templates/donor/updateDonorProfile.jsp");
		setTitle("Save A Life Today| Update Profile");
		log.info("Working tempelate is " + getWorkingTemplate());
		return Action.SUCCESS;
	}

	/**
	 * This method is written to update donor profile by passing respected
	 * DonorProfileDTO instance to service api. It also initializes the
	 * bloodgroups, states and districts lists to be rendered on UI.
	 * 
	 * @return Returns success
	 */
	public String updateDonorProfile() {
		donorProfileDTO.setDonorRoles(donorRolesDTO);
		donorProfileDTO.setDonorPreferences(donorPreferencesDTO);
		boolean result = donorService.updateDonorProfile(donorProfileDTO);
		initLists();
		if (result) {
			message = "Profile upated successfully";
			setDonorUpdatePage(true);
			setTitle("Save A Life Today| Update Profile");
			setWorkingTemplate("/WEB-INF/templates/donor/updateDonorProfile.jsp");
			log.info("Working tempelate is " + getWorkingTemplate());
			return Action.SUCCESS;
		} else {
			message = "Profile could not be updated";
			setDonorUpdatePage(true);
			setTitle("Save A Life Today| Update Profile");
			setWorkingTemplate("/WEB-INF/templates/donor/updateDonorProfile.jsp");
			log.info("Working tempelate is " + getWorkingTemplate());
			return Action.ERROR;
		}
	}
	
	public String getDonorCredential() {
		donorCredentialDTO = donorService.loadCredentials(((DonorProfileDTO)session.get(DonorServiceConstants.USER)).getEmail());
		setTitle("Save A Life Today| Update Password");
		setWorkingTemplate("/WEB-INF/templates/donor/updateDonorCredentials.jsp");
		log.info("Working tempelate is " + getWorkingTemplate());
		return Action.SUCCESS;
	}
	
	public String updateDonorCredential() {
		boolean result = donorService.updateDonorCredential(donorCredentialDTO);
		if (result) {
			message = "Password upated successfully";
			setTitle("Save A Life Today| Update Password");
			setWorkingTemplate("/WEB-INF/templates/donor/updateDonorCredentials.jsp");
			log.info("Working tempelate is " + getWorkingTemplate());
			return Action.SUCCESS;
		} else {
			message = "password could not be updated";
			setTitle("Save A Life Today| Update Password");
			setWorkingTemplate("/WEB-INF/templates/donor/updateDonorCredentials.jsp");
			log.info("Working tempelate is " + getWorkingTemplate());
			return Action.ERROR;
		}
	}
	
	public void initLists() {

		// log.info("Starting to populate lists");
		bloodGroups = BloodGroupUtils.getAvailableBloodGroups();
		states = StateInfoUtils.getStates();
		districts = new ArrayList<String>();
		districts.add("");
		if(donorProfileDTO != null){
		if (donorProfileDTO.getState() == null) {
			districts = new ArrayList<String>();
			districts.add("--select--");
		} else {
			districts = StateInfoUtils.getDistricts().get(
					donorProfileDTO.getState());
		}
		}
	}

	@Override
	public void prepare() throws Exception {
		initLists();
		setTitle("Save A Life Today| Update Password");
		setWorkingTemplate("/WEB-INF/templates/donor/updateDonorCredentials.jsp");
		
	}
	
	public DonorService getDonorService() {
		return donorService;
	}

	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
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

	public List<String> getDistricts() {
		return districts;
	}

	public void setDistricts(List<String> districts) {
		this.districts = districts;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DonorCredentialDTO getDonorCredentialDTO() {
		return donorCredentialDTO;
	}

	public void setDonorCredentialDTO(DonorCredentialDTO donorCredentialDTO) {
		this.donorCredentialDTO = donorCredentialDTO;
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

	public boolean isDonorUpdatePage() {
		return donorUpdatePage;
	}

	public void setDonorUpdatePage(boolean donorUpdatePage) {
		this.donorUpdatePage = donorUpdatePage;
	}

	public DonorRolesDTO getDonorRolesDTO() {
		return donorRolesDTO;
	}

	public void setDonorRolesDTO(DonorRolesDTO donorRolesDTO) {
		this.donorRolesDTO = donorRolesDTO;
	}
}
