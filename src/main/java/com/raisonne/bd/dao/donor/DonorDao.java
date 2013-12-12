package com.raisonne.bd.dao.donor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.raisonne.bd.dto.donor.FeedbackDTO;
import com.raisonne.bd.generic.dao.GenericDAO;
import com.raisonne.bd.pojo.BloodDonationRecord;
import com.raisonne.bd.pojo.BloodRequest;
import com.raisonne.bd.pojo.DonorCredential;
import com.raisonne.bd.pojo.DonorProfile;
import com.raisonne.bd.pojo.DonorRoles;
import com.raisonne.bd.pojo.EmailVerification;
import com.raisonne.bd.pojo.Feedback;

/**
 * This interface is created to declare general CRUD methods for donor(s).
 * 
 * @author Raisonne
 */
public interface DonorDao extends GenericDAO<DonorProfile, String> {

	/**
	 * This method is declared to retrieve donor profiles based on blood group,
	 * state and city of donors.
	 * 
	 * @param bloodType
	 *            Blood group of donor(s) to be retrieved.
	 * @param state
	 *            State of donor(s) to be retrieved.
	 * @param city
	 *            City of donor(s) to be retrieved.
	 * @param fromIndex
	 *            Starting page number for pagination.
	 * @param pageSize
	 *            Number of records to be fetched.
	 * 
	 * @return It returns List of retrieved donors information.
	 */
	public List loadDonorsInfoByArea(String bloodGroup, String state,
			String city, int fromIndex, int pageSize);

	/**
	 * This method is declared to retrieve donor profiles based on blood group
	 * and postal code of donors.
	 * 
	 * @param bloodType
	 *            Blood group of donor(s) to be retrieved.
	 * @param state
	 *            State of donor(s) to be retrieved.
	 * @param city
	 *            City of donor(s) to be retrieved.
	 * @return It returns collection of retrieved donor profiles.
	 */
	List loadDonorsInfoByPostalCode(String bloodGroup,
			String postalCode, int fromIndex, int pageSize);

	/**
	 * This method is declared to register new donor profile .
	 * 
	 * @param DonorProfile
	 *            DonorProfile instance to be saved
	 * 
	 * @param DonorCredential
	 *            DonorCredential instance to be saved
	 * 
	 * @return true if donor profile is created successfully else false.
	 */

	boolean saveDonor(DonorProfile donorProfile, DonorCredential donorCredential)
			throws Exception;

	/**
	 * This method is declared to return total count of existing donor profiles
	 * in the database.
	 * 
	 * @return Returns total count of existing donor profiles
	 */
	long loadDonorProfilesCount(Map<String,String> searchCriteris,String searchType);

	/**
	 * This method is declared to return donorProfiles for given donor IDs
	 * @param donorIds
	 * @return Returns list of donor profiles
	 */
	List<DonorProfile> loadDonorProfilesForIds(List<String> donorIds) throws Exception;
	
	/**
	 * This method is declared to return the avability status of donor mail id
	 * @param String mailid
	 * @return Returns boolean
	 */	
	public boolean checkEmailId(String mailId);
	
	/**
	 * Method responsible for returning user profile based on the unique email
	 * @param email email id of user
	 * @return  {@link DonorProfile} profile associated with the email id
	 * @throws Exception TODO
	 */
	public DonorProfile loadDonorByEmaiId(String email) throws Exception;
	
	/**
	 * This method is implemented to update donor profiles.
	 * 
	 * * @param DonorProfile
	 *            Instance of donor profile to be updated
	 * 
	 * @return Returns true if donor profile updated successfully else false
	 * 			 
	 */
	public boolean updateDonorProfile(DonorProfile donorProfile);
	
	/**
	 * This method is declared to return credentials corresponding to given id.
	 * 
	 * * @param String
	 *            ID of the donor
	 * 
	 * @return Returns instance of respective donor credentials
	 * 			 
	 */
	public DonorCredential loadCredentials(String id);
	
	/**
	 * This method is declared to update credentials
	 * 
	 * * @param DonorCredential
	 *            donor credential to be updated
	 * 
	 * @return Returns true if credential upated successfully else false
	 * 			 
	 */
	public boolean updateDonorCredential(DonorCredential donorCredential);

	/**
	 * This method is declared to save Blood Request 
	 * 
	 * * @param BloodRequest
	 *            blood request to be saved
	 * 
	 * @return Returns true if blood request is saved successfully else false
	 * 			 
	 */
	public boolean saveBloodRequest(BloodRequest bloodRequest);
	
	public boolean saveBloodDonationRecord(BloodDonationRecord bloodDonationRecord);
	public boolean updateBloodDonationRecord(BloodDonationRecord bloodDonationRecord);
	public List<BloodDonationRecord> loadBloodDonationRecords(String donorId);
	public BloodDonationRecord loadBloodDonationRecord(String recordId);
	
		
	/**
	 * This method is declared for login authentication Request 
	 * 
	 * * @param DonorCredential
	 *            DonorCredential to be authenticated
	 * 
	 * @return Returns true if donor is authenticated successfully else false
	 * 			 
	 */
	public boolean loginAuthentication(DonorCredential donorCredential);
	/**
	 * This method is declared to update the status registered donor to activate or decativate based on the input.
	 * @param donorProfile
	 * 			DonorProfile instance to be updated.
	 * @param emailVerification
	 * 			EmailVerification instance to be removed from database.
	 * @return
	 * 		Return true or false based on the input.
	 */
	public boolean updateVerifiedDonor(DonorProfile donorProfile,
			EmailVerification emailVerification);
	/**
	 * This method is overloaded  to register new donor profile using blood donation system. 
	 * @param donorProfile
	 * 			DonorProfile instance to saved.
	 * @param donorCredential
	 *         DonorCredential instance to be saved.
	 * @param emailVerification
	 * 			EmailVerification instance to be saved.
	 * @return
	 * 			return true or false based on the implementation.
	 * @throws Exception
	 */
	boolean saveDonor(DonorProfile donorProfile, DonorCredential donorCredential,DonorRoles donorRoles,EmailVerification emailVerification)
			throws Exception;
	
	
	
	/**
	 * This method is declared to retrieve EmailVerification data based on token of donor.
	 * @param token
	 * 		token used for verification of donor.
	 * @return
	 * 		instance of EmailVerification.
	 * @throws Exception
	 */
	public EmailVerification loadEmailByTokenId(String token) throws Exception;
	
	/**
	 * This method is declared to update last date on which donor's information shared by email
	 * 
	 * @param List<String>
	 *            List of donor IDs to be updated
	 * 
	 * @return Returns true if profiles upated successfully else false
	 * 			 
	 */
	public boolean updateLastEmailDate(List<String> donorIDs, Date date);
	/**
	 * This method is declared to update last date on which donor's information shared by SMS
	 * 
	 * @param List<String>
	 *            List of donor IDs to be updated
	 * 
	 * @return Returns true if profiles upated successfully else false
	 * 			 
	 */
	public boolean updateLastSmsDate(List<String> donorIDs, Date date);
	
	/**
	 * Method responsible for returning user role and authorities based on the unique email(username)
	 * @param userName email id of user
	 * @return  list of authorities associated with the email id
	 * @throws Exception 
	 */
	public DonorProfile loadDonorRoleDetailsByEmaiId(String userName) throws Exception;
	
	/**
	 * This method is declared to load all the feedbacks
	 * @param verify  to verify the feedbacks            
	 * @return list of all the Feedbacks.
	 */
	public List<Feedback> loadAllFeedbacks(boolean verify);
	
	/**
	 * This method is declared to save the feedback
	 * 
	 * @param Feedback
	 *            Feedback instance to be saved
	 *            
	 * @return true if Feedback is saved successfully else false.
	 */
	public boolean saveFeedback(Feedback feedback);
	/**
	 * This method is declared to verify the feedbacks
	 * 
	 * @param verify
	 *            feedback instance to be verified
	 *            
	 * @return true if Feedback is verified successfully else false.
	 */
	public boolean verifyFeedbacks(String uuid,boolean verify);
}
