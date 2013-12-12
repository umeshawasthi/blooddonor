package com.raisonne.bd.action.donor;

import java.util.List;
import java.util.Map;

import net.sf.oval.constraint.AssertValid;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.raisonne.bd.constant.DonorServiceConstants;
import com.raisonne.bd.dto.donor.BloodDonationRecordDTO;
import com.raisonne.bd.dto.donor.DonorProfileDTO;
import com.raisonne.bd.service.donor.DonorService;

public class DonorHistory extends BaseAction implements SessionAware {

	private static final long serialVersionUID = 1L;
	@AssertValid
	private BloodDonationRecordDTO bloodDonationRecordDTO;
	private DonorService donorService;
	private String recordId;
	private String donorId;
	private String actionName;
	private String workingTemplate;
	Map<String, Object> session = null;
	private List historyRecords = null;

	public String donationHistoryOptions() {
		
		  return loadDonationHistoryForDonor();
		}

	public String createDonationHistory() {
		this.workingTemplate = "/WEB-INF/templates/donationhistory/CreateDonationRecord.jsp";
		setTitle("Save A Life Today| Create Blood Donation Record");
		actionName=getCurrentActionName();
		return ActionSupport.SUCCESS;
	}

	public String saveDonationHistory() {
		setTitle("Save A Life Today| Save Blood Donor History");
		bloodDonationRecordDTO.setDonor_id(((DonorProfileDTO) session
				.get(DonorServiceConstants.USER)).getDonor_uuid());
		actionName=getCurrentActionName();
		if(donorService.saveBloodDonationRecord(bloodDonationRecordDTO))
			{
			    return loadDonationHistoryForDonor();
			}
		return ActionSupport.SUCCESS;
	}

	public String loadDonationHistoryById() {
		bloodDonationRecordDTO = donorService.loadBloodDonationRecord(donorId);
		actionName=getCurrentActionName();
		this.workingTemplate = "/WEB-INF/templates/donationhistory/UpdateDonationRecord.jsp";
		setTitle("Save A Life Today| Blood Donor History");
		return ActionSupport.SUCCESS;
	}

	public String updateDonationHistory() {
		actionName=getCurrentActionName();
		setTitle("Save A Life Today| Update Blood Donor History");
		if(donorService.updateBloodDonationRecord(bloodDonationRecordDTO))
			{
			return  loadDonationHistoryForDonor();
			}
		return ActionSupport.SUCCESS;
	}

	public String loadDonationHistoryForDonor() {
		historyRecords = donorService
				.loadBloodDonationRecords(((DonorProfileDTO) session
						.get(DonorServiceConstants.USER)).getDonor_uuid());
		actionName=getCurrentActionName();
		this.workingTemplate = "/WEB-INF/templates/donationhistory/ListDonationRecords.jsp";
		setTitle("Save A Life Today| Blood Donor History");
		return ActionSupport.SUCCESS;
	}

	private String getCurrentActionName(){
		return ActionContext.getContext().getName();
	}
	public DonorService getDonorService() {
		return donorService;
	}

	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}

	public BloodDonationRecordDTO getBloodDonationRecordDTO() {
		return bloodDonationRecordDTO;
	}

	public void setBloodDonationRecordDTO(
			BloodDonationRecordDTO bloodDonationRecordDTO) {
		this.bloodDonationRecordDTO = bloodDonationRecordDTO;
	}

	public String getRecordId() {
		return recordId;
	}

	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}

	public String getDonorId() {
		return donorId;
	}

	public void setDonorId(String donorId) {
		this.donorId = donorId;
	}

	public String getWorkingTemplate() {
		return workingTemplate;
	}

	public void setWorkingTemplate(String workingTemplate) {
		this.workingTemplate = workingTemplate;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public List getHistoryRecords() {
		return historyRecords;
	}

	public void setHistoryRecords(List historyRecords) {
		this.historyRecords = historyRecords;
	}

	public String getActionName() {
		return actionName;
	}
	
	

}
