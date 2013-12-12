package com.raisonne.bd;


import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.raisonne.bd.dto.bloodrequest.BloodRequestDTO;
import com.raisonne.bd.dto.bloodrequest.BloodRequestScrollerDTO;
import com.raisonne.bd.service.bloodrequest.BloodRequestService;

public class ScrollerUtilImpl extends ActionSupport implements ScrollerUtil{
	
	private static final long serialVersionUID = 1L;
	private List<BloodRequestDTO> bloodRequestDTOs;
	private BloodRequestService bloodRequestService;
	public String execute() throws Exception
	{
		  bloodRequestDTOs=bloodRequestService.loadVerifiedBloodRequests();
		//BloodRequestServiceImpl bloodRequestServiceImpl=new BloodRequestServiceImpl();
		//sbloodRequestDTOs=bloodRequestServiceImpl.loadVerifiedBloodRequests();
		return SUCCESS;
	}
	@Override
	public List<BloodRequestDTO> getScrollerData()
	{
		List<BloodRequestDTO> bloodReqDtos=bloodRequestService.loadVerifiedBloodRequests();
		return bloodReqDtos;
	}
	
	@Override
	public Map<Integer, BloodRequestScrollerDTO> getScrollerLlistData() {
		return bloodRequestService.loadVerifiedBloodRequestsForScroller();
		
	}
	public List<BloodRequestDTO> getBloodRequestDTOs() {
		return bloodRequestDTOs;
	}
	public void setBloodRequestDTOs(List<BloodRequestDTO> bloodRequestDTOs) {
		this.bloodRequestDTOs = bloodRequestDTOs;
	}
	public BloodRequestService getBloodRequestService() {
		return bloodRequestService;
	}
	public void setBloodRequestService(BloodRequestService bloodRequestService) {
		this.bloodRequestService = bloodRequestService;
	}
	


}
