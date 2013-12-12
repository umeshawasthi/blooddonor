package com.raisonne.bd.action.bloodrequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.oval.constraint.AssertValid;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Preparable;
import com.raisonne.bd.action.donor.BaseAction;
import com.raisonne.bd.dto.bloodrequest.BloodRequestDTO;
import com.raisonne.bd.dto.bloodrequest.BloodRequestStatus;
import com.raisonne.bd.service.bloodrequest.BloodRequestService;
import com.raisonne.bd.util.BloodGroupUtils;
import com.raisonne.bd.util.StateInfoUtils;

/**
 * This action class is written to handle all the blood request related requests.
 * It makes use of respected service api for performing any operation.
 * @author Himanshu Durgapal
 * @version 1.0 14/12/2011
 * @since   1.0
 * 
 */

public class BloodRequest extends BaseAction implements Preparable{
	
	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(BloodRequest.class);
	@AssertValid	
    private BloodRequestDTO bloodRequestDTO;
    private BloodRequestService bloodRequestService;
    private List<String> bloodGroups;
    private List<String> districts;
    private Map<String, String> states;
    private boolean bloodRequestPage;
    private String message;
    
    
    /**
	 * This variable will be used to set working tempelate.
	 * Developer need to set working tempelate here,application will pick it
	 * as per the given structure.
	 */
	private String workingTemplate=null;
    /**
	 * This method is implemented to redirect to blood request page and populate blood group
	 *  and state drop down in jsp page . 
	 * @return Returns string result and perform action based on the result.
	 */
    
	public String getBloodRequest() throws Exception
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
		   districts.add("--select district--");
		   setWorkingTemplate("/WEB-INF/templates/bloodrequest/bloodRequest.jsp");
		   setBloodRequestPage(true);
		   setTitle("Save A Life Today| Submit blood request");
		   return SUCCESS;
	}
	
	/**
	 * This method is implemented to save blood request .
	 * @return Returns string result based on result of action performed.
	 * 
	 */
	
	public String saveBloodRequest() throws Exception
	{
		
		BloodRequestStatus status=bloodRequestService.saveBloodRequest(bloodRequestDTO);
		if(status.isResult()){
			message="Your Blood Request has been saved successfully</br>";
		    message=message+"Your request number is: "+status.getRefrence_id()+"</br>" ;
		    message=message+"Please use above refrence number for editing your request or any communication.";
		}
		else
			message="Error while saving blood request";
			setWorkingTemplate("/WEB-INF/templates/bloodrequest/message.jsp");
	    setTitle("Save A Life Today| Submit a blood reqquest");
		return SUCCESS;
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

	public String getWorkingTemplate() {
		return workingTemplate;
	}

	public void setWorkingTemplate(String workingTemplate) {
		this.workingTemplate = workingTemplate;
	}

	public void setBloodRequestService(BloodRequestService bloodRequestService) {
		this.bloodRequestService = bloodRequestService;
	}

	public boolean isBloodRequestPage() {
		return bloodRequestPage;
	}

	public void setBloodRequestPage(boolean bloodRequestPage) {
		this.bloodRequestPage = bloodRequestPage;
	}

	public BloodRequestDTO getBloodRequestDTO() {
		return bloodRequestDTO;
	}

	public void setBloodRequestDTO(BloodRequestDTO bloodRequestDTO) {
		this.bloodRequestDTO = bloodRequestDTO;
	}
	@Override
	public void prepare() throws Exception {
		getBloodRequest();
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
