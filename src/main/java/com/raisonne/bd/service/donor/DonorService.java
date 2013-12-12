package com.raisonne.bd.service.donor;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;



import com.raisonne.bd.dto.donor.BloodDonationRecordDTO;
import com.raisonne.bd.dto.donor.DonorCredentialDTO;
import com.raisonne.bd.dto.donor.DonorProfileDTO;
import com.raisonne.bd.dto.donor.DonorRolesDTO;
import com.raisonne.bd.dto.donor.EmailVerificationDTO;
import com.raisonne.bd.dto.donor.FeedbackDTO;
import com.raisonne.bd.pojo.BloodDonationRecord;
import com.raisonne.bd.pojo.DonorProfile;
import com.raisonne.bd.pojo.Feedback;

/**
 * This interface is created to declare general service methods for donor(s).
 * 
 * @author Arun
 */
public interface DonorService {

	/**
	 * This method is declared to retrieve donor profiles based on blood group,
	 * state and city of donors.
	 * 
	 * @param bloodType
	 *            Blood group of donor(s) to be retrieved.
	 * @param state
	 *            State of donor(s) to be retrieved.
	 * @param district
	 *            District of donor(s) to be retrieved.
	 * @param fromIndex
	 *            Starting page number for pagination.
	 * @param pageSize
	 *            Number of records to be loaded.
	 * 
	 * @return It returns List of retrieved donor profiles.
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
	List<DonorProfile> loadDonorsInfoByPostalCode(String bloodGroup,
			String postalCode, int limits, int pageSize);

	/**
	 * This method is declared to register new donor profile using third party login.
	 * 
	 * @param DonorProfile
	 *            DonorProfile instance to be saved
	 * 
	 * @param DonorCredential
	 *            DonorCredential instance to be saved
	 * 
	 * @return true if donor profile is created successfully else false.
	 */
	public boolean saveDonor(DonorProfileDTO donorProfile,
			DonorCredentialDTO donorCredential) throws Exception;

	/**
	 * This method is declared to sign in the donor
	 * 
	 * @param donorCredential
	 *            Credentials of the donor
	 * @return true if donor sign in successfully else false
	 * 
	 * @throws Exception
	 */
	public boolean signInDonor(DonorCredentialDTO donorCredential)
			throws Exception;

	/**
	 * This method is declared to return total count of existing donor profiles
	 * in the database.
	 * 
	 * @return Returns total count of existing donor profiles
	 */
	long loadDonorProfilesCount(Map<String,String> searchCriteria, String searchType);
	
	/**
	 * This service method is declared to return donorProfiles for given donor IDs
	 * @param donorIds
	 * @return Returns list of donor profiles
	 */
	List<DonorProfileDTO> loadDonorProfilesForIds(List<String> donorIds) throws Exception;
	/**
	 * This service method is declared to return the avability status of mail id 
	 * @param String mailId
	 * @return Returns boolean
	 */
	public boolean checkEmailId(String mailId) throws Exception;
	
	/**
	 * The method will help to load donor profile based on the email-id of the user
	 * email will be unique so can work as a unique key to load unique user profile.
	 * 
	 * @param email email id of user
	 * @return  {@link DonorProfileDTO} donor profile based o the email id
	 * @throws Exception TODO
	 */
	public DonorProfileDTO loadDonorByEmailId(String email) throws Exception;
	
	/**
	 * This service method is declared to update donor profiles.
	 * 
	 * * @param DonorProfileDTO
	 *            Instance of DonorProfileDTO to be updated
	 * 
	 * @return Returns true if donor profile updated successfully else false
	 * 			 
	 */
	public boolean updateDonorProfile(DonorProfileDTO donorProfileDTO);
	
	/**
	 * This service method is declared to update donor credentials.
	 * 
	 * * @param DonorProfileDTO
	 *            Instance of DonorProfileDTO to be updated
	 * 
	 * @return Returns true if donor credential updated successfully else false
	 * 			 
	 */
	public boolean updateDonorCredential(DonorCredentialDTO donorCredentialDTO);
	
	/**
	 * This service method is declared to check whether donor is logged in or not.
	 * 
	 * * @param String
	 *            ID of the donor
	 * 
	 * @return Returns true if donor is logged in else false
	 * 			 
	 */
	public boolean isLoggedIn(String id);
	
	/**
	 * This service method is declared to return credentials corresponding to given id.
	 * 
	 * * @param String
	 *            ID of the donor
	 * 
	 * @return Returns instance of respective donor credentials
	 * 			 
	 */
	public DonorCredentialDTO loadCredentials(String id);
	
	public boolean saveBloodDonationRecord(BloodDonationRecordDTO bloodDonationRecordDTO);
	public boolean updateBloodDonationRecord(BloodDonationRecordDTO bloodDonationRecordDTO);
	public List<BloodDonationRecord> loadBloodDonationRecords(String donorId);
	public BloodDonationRecordDTO loadBloodDonationRecord(String recordId);
	

	
	/**
	 * This method is declared to get the login authentication
	 * 
	 * @param DonorCredentialDTO
	 *            DonorCredentialDTO instance to be saved
	 *            
	 * @return true if login request is authenticated successfully else false.
	 */
	public boolean loginAuthentication(DonorCredentialDTO donorCredentialDTO);
	
	
	/**
	 * This method is declared to activate the registered donor
	 *  
	 * @param String
	 *            token of donor is verified.
	 *            
	 * @return true if donor is activated successfully else false.
	 * @throws Exception
	 */
	public boolean updateVerifiedDonor(String token) throws Exception;
	/**
	 * This method is declared to register new donor profile using blood donation system. 
	 * @param donorProfile
	 *             DonorProfile instance to be saved.
	 * @param donorCredential
	 * 				DonorCredential instance to be saved.
	 * @param emailVerificationDTO
	 *              EmailVerificationDTO instance to be saved.
	 * @param context
	 * 				Used for mail functionality.
	 * @return
	 * 				based of the result of method returns true or false.
	 * @throws Exception
	 */
	public boolean saveDonor(DonorProfileDTO donorProfile,
			DonorCredentialDTO donorCredential,EmailVerificationDTO emailVerificationDTO,DonorRolesDTO donorRolesDTO,ServletContext context) throws Exception; 
	
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
	 *           @param verify  to verify the feedbacks   
	 * @return list of all the Feedbacks.
	 */
	public List<FeedbackDTO> loadAllFeedbacks(boolean verify);
	/**
	 * This method is declared to save the feedback
	 * 
	 * @param FeedbackDTO
	 *            FeedbackDTO instance to be saved
	 *            
	 * @return true if Feedback is saved successfully else false.
	 */
	public boolean saveFeedback(FeedbackDTO feedbackDTO);
	
	/**
	 * This method is declared to verify the feedbacks
	 * 
	 * @param donorFeedbackDTO
	 *            feedback instance to be verified
	 *            
	 * @return true if Feedback is saved successfully else false.
	 */
	public boolean verifyFeedbacks(String uuid,boolean verify);
	
	
}
