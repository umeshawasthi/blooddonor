package com.raisonne.bd.service.bloodrequest;

import java.util.List;
import java.util.Map;

import com.raisonne.bd.dto.bloodrequest.BloodRequestDTO;
import com.raisonne.bd.dto.bloodrequest.BloodRequestScrollerDTO;
import com.raisonne.bd.dto.bloodrequest.BloodRequestStatus;

/**
 * This interface is created to declare general service methods for blood request.
 * 
 * @author Himanshu
 */
public interface BloodRequestService
{

	/**
	 * This method is declared to save new blood request
	 * 
	 * @param BloodRequestDTO
	 *            BloodRequestDTO instance to be saved
	 *            
	 * @return true if blood request is saved successfully else false.
	 */
	public BloodRequestStatus saveBloodRequest(BloodRequestDTO bloodrequestDto);
	/**
	 *This method is declared to update blood request 
	 * @param BloodRequestDTO
	 * 			blood request to be saved
	 * @return  Returns true if blood request is updated successfully else false
	 */
	public BloodRequestDTO updateBloodRequest(BloodRequestDTO bloodRequestDTO);
	/**
	 * This method is declared to load blood request based on the input .
	 * @param bloodRequestId
	 * 			Blood request id by which blood request data is fetched.
	 * @return
	 *    {@link BloodRequestDTO}  based on the input id.
	 */
	public BloodRequestDTO loadBloodRequestById(BloodRequestDTO bloodRequestDTO) ;
	public BloodRequestDTO getBloodRequestById(String uid);
	/**
	 * This method is declared to load unverified blood requests .
	 * @return 
	 *    {@link List} of unverified of blood requests
	 */
	public List<BloodRequestDTO> loadUnVerifiedBloodRequests();
	
	/**
	 * This method is declared to load verified blood requests .
	 * @return 
	 *    {@link List} of unverified of blood requests
	 */
	public List<BloodRequestDTO> loadVerifiedBloodRequests();
	public Map<Integer, BloodRequestScrollerDTO> loadVerifiedBloodRequestsForScroller();
	
}
