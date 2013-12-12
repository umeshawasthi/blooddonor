package com.raisonne.bd.dao.bloodrequest.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.raisonne.bd.dao.bloodrequest.BloodRequestDao;
import com.raisonne.bd.dto.bloodrequest.BloodRequestStatus;
import com.raisonne.bd.generic.dao.impl.GenericDAOImpl;
import com.raisonne.bd.pojo.BloodRequest;
import com.raisonne.bd.pojo.DonorProfile;

/**
 * This dao class is created to implement CRUD methods for blood request.
 * 
 * @author Himanshu
 * 
 */
public class BloodRequestDaoImpl extends GenericDAOImpl<DonorProfile, String>
		implements BloodRequestDao {

	

	/**
	 * This method is implemented to save blood request object in the
	 * database .
	 * 
	 * @param BloodRequest
	 *            BloodRequest instance to be saved
	 * 
	 * @return Returns true if donor request is saved successfully else
	 *         returns false.
	 */
	@Override
	public BloodRequestStatus saveBloodRequest(BloodRequest bloodRequest) {
		BloodRequestStatus status=new BloodRequestStatus(false,0);
		
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.save(bloodRequest);
			status.setResult(true);
			status.setRefrence_id(bloodRequest.getUuid());
			

		} catch (Exception e) {
			e.printStackTrace();
			status.setResult(false);
			status.setRefrence_id(0);
		}
		return status;
	}
	/**
	 *This method is declared to update blood request 
	 * @param bloodRequest
	 * 			blood request to be saved
	 * @return  Returns true if blood request is updated successfully else false
	 */
	@Override
	public BloodRequest updateBloodRequest(BloodRequest bloodRequest) {
		
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.update(bloodRequest);
			bloodRequest.setBloodRequestUpdated(true);

		} catch (Exception e) {
			e.printStackTrace();
			bloodRequest.setBloodRequestUpdated(false);
		}
		
		return bloodRequest;

	}
	/**
	 * This method is implemented to load blood request based on the input .
	 * @param bloodRequestId
	 * 			Blood request id by which blood request data is fetched.
	 * @return
	 *    {@link BloodRequest} pojo based on the input id.
	 */
	@Override
	public BloodRequest loadBloodRequestById(String bloodRequestId) {
		BloodRequest bloodRequest=null;
		Session session = getSessionFactory().getCurrentSession();

		try {
			Query query = session.getNamedQuery("getBloodRequestById")
					.setString("requestId", bloodRequestId);
			bloodRequest =  (BloodRequest) query.uniqueResult();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return bloodRequest;
	}
	/**
	 * This method is implemented to load unverified blood requests .
	 * @return 
	 *    {@link List} of unverified of blood requests
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BloodRequest> loadUnverifiedBloodRequests() {
		List<BloodRequest> bloodRequests=null;
		Session session = getSessionFactory().getCurrentSession();

		try {
			Query query = session.getNamedQuery("getBloodRequests").setBoolean("booleanFlag", false);
			bloodRequests =  query.list();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return bloodRequests;
	}
	/**
	 * This method is implemented to load verified blood requests .
	 * @return 
	 *    {@link List} of unverified of blood requests
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BloodRequest> loadVerifiedBloodRequests() {
		List<BloodRequest> bloodRequests=null;
		Session session = getSessionFactory().getCurrentSession();

		try {
			Query query = session.getNamedQuery("getBloodRequests").setBoolean("booleanFlag", true);
			bloodRequests =  query.list();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return bloodRequests;
	}

	

	
}
