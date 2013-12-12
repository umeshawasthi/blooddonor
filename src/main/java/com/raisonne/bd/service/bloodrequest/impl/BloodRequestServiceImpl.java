package com.raisonne.bd.service.bloodrequest.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.raisonne.bd.converter.DonorConverter;
import com.raisonne.bd.dao.bloodrequest.BloodRequestDao;
import com.raisonne.bd.dto.bloodrequest.BloodRequestDTO;
import com.raisonne.bd.dto.bloodrequest.BloodRequestScrollerDTO;
import com.raisonne.bd.dto.bloodrequest.BloodRequestStatus;
import com.raisonne.bd.pojo.BloodRequest;
import com.raisonne.bd.service.bloodrequest.BloodRequestService;

/**
 * This service class is created to implement service methods for donor(s).
 * These methods defines logic for CRUD operations on donor instances and
 * business logic as well.
 * 
 * 
 */
public class BloodRequestServiceImpl implements BloodRequestService {

	Logger log = Logger.getLogger(BloodRequestServiceImpl.class);
	private BloodRequestDao bloodRequestDao;
	private DonorConverter donorConverter;
	
	/**
	 * This method is implemented to save new blood request
	 * 
	 * @param BloodRequestDTO
	 *            BloodRequestDTO instance to be saved
	 *            
	 * @return true if blood request is saved successfully else false.
	 */
	@Override
	public BloodRequestStatus saveBloodRequest(BloodRequestDTO bloodrequestDto) {
		
		BloodRequest bloodRequest = donorConverter.convertBloodRequestDtoToPojo(bloodrequestDto);		
		BloodRequestStatus status = bloodRequestDao.saveBloodRequest(bloodRequest);
		return status;
	}
	/**
	 * This method is implemented to load blood request based on the input .
	 * @param bloodRequestId
	 * 			Blood request id by which blood request data is fetched.
	 * @return
	 *    {@link BloodRequestDTO}  based on the input id.
	 */
	@Override
	public BloodRequestDTO loadBloodRequestById(BloodRequestDTO bloodRequestDTO){
		BloodRequest bloodRequest=bloodRequestDao.loadBloodRequestById(Integer.toString(bloodRequestDTO.getUuid()));
		if(bloodRequest==null)
		{
			return null;
		}
		return donorConverter.convertBloodRequestPojoToDto(bloodRequest);
	}
	
	@Override
	public BloodRequestDTO getBloodRequestById(String uid) {
		BloodRequest bloodRequest=bloodRequestDao.loadBloodRequestById(uid);
		if(bloodRequest==null)
		{
			return null;
		}
		return donorConverter.convertBloodRequestPojoToDto(bloodRequest);
	}
	/**
	 *This method is implemented to update blood request 
	 * @param BloodRequestDTO
	 * 			blood request to be saved
	 * @return  Returns true if blood request is updated successfully else false
	 */
	@Override
	public BloodRequestDTO updateBloodRequest(BloodRequestDTO bloodRequestDTO) {
		
		BloodRequest bloodRequest= donorConverter.convertBloodRequestDtoToPojo(bloodRequestDTO);
	    return donorConverter.convertBloodRequestPojoToDto(bloodRequestDao.updateBloodRequest(bloodRequest));
		
	}
	/**
	 * This method is implemented to load unverified blood requests .
	 * @return 
	 *    {@link List} of unverified of blood requests
	 */
	@Override
	public List<BloodRequestDTO> loadUnVerifiedBloodRequests() {
		List<BloodRequestDTO> bloodRequestDTOs=new ArrayList<BloodRequestDTO>();
		List<BloodRequest> bloodRequests=bloodRequestDao.loadUnverifiedBloodRequests();
		
		for (BloodRequest bloodRequest : bloodRequests) {
			bloodRequestDTOs.add(donorConverter.convertBloodRequestPojoToDto(bloodRequest));
		}	
		
		return bloodRequestDTOs;
	}
	/**
	 * This method is implemented to load verified blood requests .
	 * @return 
	 *    {@link List} of unverified of blood requests
	 */
	@Override
	public List<BloodRequestDTO> loadVerifiedBloodRequests() {
		List<BloodRequestDTO> bloodRequestDTOs=new ArrayList<BloodRequestDTO>();
		List<BloodRequest> bloodRequests=bloodRequestDao.loadVerifiedBloodRequests();
		
		for (BloodRequest bloodRequest : bloodRequests) {
			bloodRequestDTOs.add(donorConverter.convertBloodRequestPojoToDto(bloodRequest));
		}	
		
		return bloodRequestDTOs;
	}
	
	@Override
	public Map<Integer, BloodRequestScrollerDTO> loadVerifiedBloodRequestsForScroller() {
		Map<Integer, BloodRequestScrollerDTO> bloodRequestScrDTOs=new HashMap<Integer,BloodRequestScrollerDTO>();
		List<BloodRequest> bloodRequests=bloodRequestDao.loadVerifiedBloodRequests();
		BloodRequestScrollerDTO dto=null;
		for (BloodRequest bloodRequest : bloodRequests) {
			  dto=new BloodRequestScrollerDTO();
			  dto.setUuid(bloodRequest.getUuid());
			  dto.setBloodGroup(bloodRequest.getBloodGroup());
			  dto.setLocation(bloodRequest.getLocation());
			  dto.setState(bloodRequest.getState());
			  dto.setContactNumber(bloodRequest.getMobileNumber());
			  bloodRequestScrDTOs.put(bloodRequest.getUuid(), dto);
		}	
		
		return bloodRequestScrDTOs;
	}
	
	public void setBloodRequestDao(BloodRequestDao bloodRequestDao) {
		this.bloodRequestDao = bloodRequestDao;
	}

	public void setDonorConverter(DonorConverter donorConverter) {
		this.donorConverter = donorConverter;
	}
	
	
	

	

	
}
