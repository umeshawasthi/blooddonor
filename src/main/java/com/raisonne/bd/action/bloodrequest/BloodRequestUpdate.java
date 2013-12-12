package com.raisonne.bd.action.bloodrequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.oval.constraint.AssertValid;

import com.raisonne.bd.action.donor.BaseAction;
import com.raisonne.bd.dto.bloodrequest.BloodRequestDTO;
import com.raisonne.bd.service.bloodrequest.BloodRequestService;
import com.raisonne.bd.util.BloodGroupUtils;
import com.raisonne.bd.util.StateInfoUtils;


/**
 * This action class is written to handle all the blood request update related requests.
 * It makes use of respected service api for performing any operation.
 * @author Himanshu Durgapal
 * @version 1.0 07/01/2012
 * @since   1.0
 * 
 */

public class BloodRequestUpdate extends BaseAction {

	/**
	 * class level property declarations.
	 */
	private static final long serialVersionUID = 1L;
	private BloodRequestService bloodRequestService;
	private String bloodRequestId;
	@AssertValid
	private BloodRequestDTO bloodRequestDTO;
	private List<String> bloodGroups;
	private List<String> districts;
	private Map<String, String> states;
	private boolean bloodRequestPage=false;
	private String message;
	private char user_gender;
	
	/**
	 * This variable will be used to set working template.
	 * Developer need to set working template here,application will pick it
	 * as per the given structure.
	 */
	private String workingTemplate=null;
	/**
	 * This method is implemented to get bloodrequest data based on the bloodrequest id .
	 * @return Returns string result based on result of action performed.
	 * @throws Exception
	 */
	
	public String getBloodRequestById_ajax(){
		bloodRequestDTO=bloodRequestService.getBloodRequestById(bloodRequestId);
		return SUCCESS;
	}
	
	public String getBloodRequetById() throws Exception
	{
		bloodRequestDTO=bloodRequestService.loadBloodRequestById(bloodRequestDTO);
		if(bloodRequestDTO==null)
		{
			message="blood request id not found";
			setWorkingTemplate("/WEB-INF/templates/bloodrequest/message.jsp");
			setTitle("Save A Life Today| Update Blood Request");
			return SUCCESS;
		}
		bloodGroups=BloodGroupUtils.getAvailableBloodGroups();
		   /*
		    * getting state list.
		    */
		   states=StateInfoUtils.getStates();
		   districts=new ArrayList<String>();
		   districts=StateInfoUtils.getDistricts().get(bloodRequestDTO.getState());
		   setBloodRequestPage(true);
		   user_gender=bloodRequestDTO.getGender();
		    setWorkingTemplate("/WEB-INF/templates/bloodrequest/updateBloodRequest.jsp");
		    setTitle("Save A Life Today| Update Blood Request");
		return SUCCESS;
	}	
	/**
	 * This method is implemented to update blood request data based on blood request id .
	 * @return Returns string result based on result of action performed.
	 * @throws Exception
	 */
	public String updateBloodRequest() throws Exception
	{
		BloodRequestDTO dto=bloodRequestService.updateBloodRequest(bloodRequestDTO);
		if(dto.isVerificationFlag()){
			updateBloodRequestCache(dto);
		}
		message="Blood request updated successfully";
		setWorkingTemplate("/WEB-INF/templates/bloodrequest/message.jsp");
		setTitle("Save A Life Today| Update Blood Request");
		return SUCCESS;
	}

	public String getBloodRequestId() {
		return bloodRequestId;
	}
	public void setBloodRequestId(String bloodRequestId) {
		this.bloodRequestId = bloodRequestId;
	}
	public BloodRequestDTO getBloodRequestDTO() {
		return bloodRequestDTO;
	}
	public void setBloodRequestDTO(BloodRequestDTO bloodRequestDTO) {
		this.bloodRequestDTO = bloodRequestDTO;
	}
	public String getWorkingTemplate() {
		return workingTemplate;
	}
	public void setWorkingTemplate(String workingTemplate) {
		this.workingTemplate = workingTemplate;
	}
	public void setBloodRequestService(BloodRequestService bloodRequestService) {
		this.bloodRequestService = bloodRequestService;
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
	public Map<String, String> getStates() {
		return states;
	}
	public void setStates(Map<String, String> states) {
		this.states = states;
	}
	public boolean isBloodRequestPage() {
		return bloodRequestPage;
	}
	public void setBloodRequestPage(boolean bloodRequestPage) {
		this.bloodRequestPage = bloodRequestPage;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public char getUser_gender() {
		return user_gender;
	}
	

}
