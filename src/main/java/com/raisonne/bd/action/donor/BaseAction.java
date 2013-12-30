package com.raisonne.bd.action.donor;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.raisonne.bd.dto.bloodrequest.BloodRequestDTO;
import com.raisonne.bd.dto.bloodrequest.BloodRequestScrollerDTO;
import com.raisonne.bd.util.StateInfoUtils;


/**
 * 
 * @author Himanshu Durgapal
 *
 */

public class BaseAction extends ActionSupport{

	/**
	 * 
	 */
	Logger log = Logger.getLogger(BaseAction.class);
	private static final long serialVersionUID = 1L;
	private  String applicationFrame="/WEB-INF/templates/application/applicationframe.jsp";
	private String title="Save A Life Today| Welcome to Blood Donation";
	private String workingTemplate;
	private boolean hideRightPannel=false;
	private boolean hideLeftPannel=false;
	
	
	public String execute() throws Exception
	{
		return SUCCESS;
		
	}
	
	public void updateBloodRequestCache(BloodRequestDTO bloodRequestDTO){
		if(bloodRequestDTO.isBloodRequestUpdated()){
			log.info("Blood request has been updated.Preparing to update cache..");
			@SuppressWarnings("unchecked")
			Map<Integer,BloodRequestScrollerDTO> cache=(Map<Integer,BloodRequestScrollerDTO>)ActionContext.getContext().getApplication().get("bloodRequestCache");
			BloodRequestScrollerDTO dto=new BloodRequestScrollerDTO();
			dto.setBloodGroup(bloodRequestDTO.getBloodGroup());
			dto.setContactNumber(bloodRequestDTO.getMobileNumber());
			dto.setLocation(bloodRequestDTO.getLocation());
			dto.setState(StateInfoUtils.getStates().get(bloodRequestDTO.getState()));
			SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
			final String date=sdf.format(bloodRequestDTO.getRequiredDate());
			dto.setRequiredBlooddate(date);
			
			dto.setUuid(bloodRequestDTO.getUuid());
			cache.put(bloodRequestDTO.getUuid(), dto);
			ActionContext.getContext().getApplication().put("bloodRequestCache", (Map<Integer, BloodRequestScrollerDTO>)Collections.synchronizedMap(cache));
			log.info("Cache updated successfully.Total object in cache "+cache.size());
		}
	}
	
	public String getApplicationFrame() {
		return applicationFrame;
	}
	public void setApplicationFrame(String applicationFrame) {
		this.applicationFrame = applicationFrame;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getWorkingTemplate() {
		return workingTemplate;
	}
	public void setWorkingTemplate(String workingTemplate) {
		this.workingTemplate = workingTemplate;
	}
	public boolean isHideRightPannel() {
		return hideRightPannel;
	}
	public void setHideRightPannel(boolean hideRightPannel) {
		this.hideRightPannel = hideRightPannel;
	}
	public boolean isHideLeftPannel() {
		return hideLeftPannel;
	}
	public void setHideLeftPannel(boolean hideLeftPannel) {
		this.hideLeftPannel = hideLeftPannel;
	}
	
}
