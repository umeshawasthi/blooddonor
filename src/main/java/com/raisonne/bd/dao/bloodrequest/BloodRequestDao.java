package com.raisonne.bd.dao.bloodrequest;

import java.util.List;

import com.raisonne.bd.dto.bloodrequest.BloodRequestStatus;
import com.raisonne.bd.generic.dao.GenericDAO;
import com.raisonne.bd.pojo.BloodRequest;
import com.raisonne.bd.pojo.DonorProfile;

/**
 * This interface is created to declare general CRUD methods for blood request functionality.
 * 
 * @author Himanshu Durgapal
 * v
 */
public interface BloodRequestDao extends GenericDAO<DonorProfile, String> {


	/**
	 * This method is declared to save Blood Request 
	 * 
	 * * @param BloodRequest
	 *            blood request to be saved
	 * 
	 * @return Returns true if blood request is saved successfully else false
	 * 			 
	 */
	public BloodRequestStatus saveBloodRequest(BloodRequest bloodRequest);
	/**
	 *This method is declared to update blood request 
	 * @param bloodRequest
	 * 			blood request to be saved
	 * @return  Returns true if blood request is updated successfully else false
	 */
	public BloodRequest updateBloodRequest(BloodRequest bloodRequest);
	/**
	 * This method is declared to load blood request based on the input .
	 * @param bloodRequestId
	 * 			Blood request id by which blood request data is fetched.
	 * @return
	 *    {@link BloodRequest} pojo based on the input id.
	 */
	public BloodRequest loadBloodRequestById(String bloodRequestId);
	/**
	 * This method is declared to load unverified blood requests .
	 * @return 
	 *    {@link List} of unverified of blood requests
	 */
	public List<BloodRequest> loadUnverifiedBloodRequests();
	/**
	 * This method is declared to load verified blood requests .
	 * @return 
	 *    {@link List} of unverified of blood requests
	 */
	public List<BloodRequest> loadVerifiedBloodRequests();

	
	
}
