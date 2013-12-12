package com.raisonne.bd.dao.donor.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.raisonne.bd.dao.donor.DonorDao;
import com.raisonne.bd.dto.donor.FeedbackDTO;
import com.raisonne.bd.generic.dao.impl.GenericDAOImpl;
import com.raisonne.bd.pojo.BloodDonationRecord;
import com.raisonne.bd.pojo.BloodRequest;
import com.raisonne.bd.pojo.DonorCredential;
import com.raisonne.bd.pojo.DonorProfile;
import com.raisonne.bd.pojo.DonorRoles;
import com.raisonne.bd.pojo.EmailVerification;
import com.raisonne.bd.pojo.Feedback;
import com.raisonne.bd.util.PasswordDesEncrypter;

/**
 * This dao class is created to implement CRUD methods for donor(s).
 * 
 * @author Raisonne
 * 
 */
public class DonorDaoImpl extends GenericDAOImpl<DonorProfile, String>
		implements DonorDao {

	/**
	 * This method is implemented to retrieve donor profiles based on blood
	 * group, state and city of donors.
	 * 
	 * @param bloodGroup
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
	 * @return It returns List of retrieved donor profiles.
	 */
	@Override
	public List loadDonorsInfoByArea(String bloodGroup, String state,
			String district, int fromIndex, int pageSize) {
		List list = null;
		Calendar ninetyDaysBackDate = Calendar.getInstance();
		ninetyDaysBackDate.add(Calendar.DAY_OF_MONTH, -90);
		Calendar oneDayBackDate = Calendar.getInstance();
		oneDayBackDate.add(Calendar.DAY_OF_MONTH, -1);
		Session session = getSessionFactory().getCurrentSession();
		try {
			Query query = session.getNamedQuery("getDonorsInfoByArea")
					.setString("bloodGroup", bloodGroup)
					.setString("district", district).setString("state", state)
					.setCalendarDate("lastDonated", ninetyDaysBackDate)
					.setString("donorAvailability", "y")
					.setBoolean("activatedFlag", true)
					.setCalendarDate("lastEmailDate", oneDayBackDate)
					.setCalendarDate("lastSmsDate", oneDayBackDate);
			list = query.setMaxResults(pageSize).setFirstResult(fromIndex)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.unmodifiableList(list);
	}

	/**
	 * This method is implemented to retrieve donor profiles based on blood
	 * group and postal code of donors.
	 * 
	 * @param bloodType
	 *            Blood group of donor(s) to be retrieved.
	 * @param state
	 *            State of donor(s) to be retrieved.
	 * @param city
	 *            City of donor(s) to be retrieved.
	 * @return It returns collection of retrieved donor profiles.
	 */
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List loadDonorsInfoByPostalCode(String bloodGroup,
			String postalCode, int fromIndex, int pageSize) {
		List list = null;
		Calendar ninetyDaysBackDate = Calendar.getInstance();
		ninetyDaysBackDate.add(Calendar.DAY_OF_MONTH, -90);
		Calendar oneDayBackDate = Calendar.getInstance();
		oneDayBackDate.add(Calendar.DAY_OF_MONTH, -1);
		Session session = getSessionFactory().getCurrentSession();

		try {
			Query query = session.getNamedQuery("getDonorsInfoByPostalCode")
					.setString("bloodGroup", bloodGroup)
					.setString("postalCode", postalCode)
					.setCalendarDate("lastDonated", ninetyDaysBackDate)
					.setString("donorAvailability", "y")
					.setBoolean("activatedFlag", true)
					.setCalendarDate("lastEmailDate", oneDayBackDate)
					.setCalendarDate("lastSmsDate", oneDayBackDate);
			list = query.setMaxResults(pageSize).setFirstResult(fromIndex)
					.list();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return Collections.unmodifiableList(list);
	}

	/**
	 * This method is implemented to save given donor profile object in the
	 * database .
	 * 
	 * @param DonorProfile
	 *            DonorProfile instance to be saved
	 * 
	 * @param DonorCredential
	 *            DonorCredential instance to be saved
	 * 
	 * @return Returns true if donor profile is created successfully else
	 *         returns false.
	 */
	@Override
	public boolean saveDonor(DonorProfile donorProfile,
			DonorCredential donorCredential) throws Exception {
		boolean result = false;
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.save(donorProfile);
			donorCredential.setDonorId(donorProfile.getDonor_uuid());
			session.save(donorCredential);
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
		return result;
	}

	/**
	 * This method is implemented to return total count of existing donor
	 * profiles in the database.
	 * 
	 * @return Returns total count of existing donor profiles
	 */
	@Override
	public long loadDonorProfilesCount(Map<String, String> searchCriteria,
			String searchType) {
		Session session = null;
		long count = 0;
		Calendar ninetyDaysBackDate = Calendar.getInstance();
		ninetyDaysBackDate.add(Calendar.DAY_OF_MONTH, -90);
		Calendar oneDayBackDate = Calendar.getInstance();
		oneDayBackDate.add(Calendar.DAY_OF_MONTH, -1);
		try {
			session = getSessionFactory().getCurrentSession();
			Query query = null;
			if ("postal".equals(searchType)) {
				query = session
						.getNamedQuery("getDonorsCountForPostalCode")
						.setString("bloodGroup",
								searchCriteria.get("blood_group"))
						.setString("postalCode",
								searchCriteria.get("postalcode"))
						.setCalendarDate("lastDonated", ninetyDaysBackDate)
						.setString("donorAvailability", "y")
						.setBoolean("activatedFlag", true)
						.setCalendarDate("lastEmailDate", oneDayBackDate)
						.setCalendarDate("lastSmsDate", oneDayBackDate);
			} else if ("area".equals(searchType)) {

				query = session
						.getNamedQuery("getDonorsCountForArea")
						.setString("bloodGroup",
								searchCriteria.get("blood_group"))
						.setString("district", searchCriteria.get("district"))
						.setString("state", searchCriteria.get("state"))
						.setCalendarDate("lastDonated", ninetyDaysBackDate)
						.setString("donorAvailability", "y")
						.setBoolean("activatedFlag", true)
						.setCalendarDate("lastEmailDate", oneDayBackDate)
						.setCalendarDate("lastSmsDate", oneDayBackDate);

			}
			count = (Long) query.list().get(0);

			/*
			 * StringBuilder sb=new StringBuilder();
			 * sb.append("select count(*) from DonorProfile where "); int
			 * keySetSize=searchCriteria.keySet().size(); int i=1;
			 * for(Map.Entry<String, String> entry:searchCriteria.entrySet()){
			 * sb
			 * .append(entry.getKey()).append("=").append("'"+entry.getValue()+
			 * "'"); if(i<keySetSize){ sb.append(" and "); } i++; }
			 */

		} catch (Exception e) {

			e.printStackTrace();
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DonorProfile> loadDonorProfilesForIds(List<String> donorIds)
			throws Exception {
		Session session = getSessionFactory().getCurrentSession();
		List<DonorProfile> donorProfiles = null;

		try {

			Query query = session.getNamedQuery("getDonorsByIds")
					.setParameterList("donorIds", donorIds);
			donorProfiles = query.list();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return Collections.unmodifiableList(donorProfiles);
	}

	/**
	 * This method is implemented to check that donor is already registered or
	 * not on the basis of mail id.
	 * 
	 * @param String
	 *            mailId of donor .
	 * 
	 * @return 1 or 0 depends on the record availability on the basis of email
	 *         id.
	 */
	@Override
	public boolean checkEmailId(String mailId) {

		Session session = null;
		long count = 0;

		try {
			session = getSessionFactory().getCurrentSession();

			String SQL_QUERY = "select  count(*) from DonorProfile donorProfile where donorProfile.email = '"
					+ mailId + "'";

			count = (Long) session.createQuery(SQL_QUERY).list().get(0);

			System.out.println("Total coount: " + count);
		} catch (Exception e) {

			e.printStackTrace();
		}
		if (count > 0) {
			return false;
		}

		return true;
	}

	/**
	 * Implementation method responsible for fetching user profile based on the
	 * email id. This implementation is helpful for fetching user profile who
	 * want to authenticate themself with OAuth/OpenID service provider.
	 */
	@Override
	public DonorProfile loadDonorByEmaiId(String email) throws Exception {

		Session session = getSessionFactory().getCurrentSession();
		DonorProfile donorProfiles = null;
		try {
			Query query = session.getNamedQuery("getDonorsByEmailId")
					.setString("email", email);
			donorProfiles = (DonorProfile) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return donorProfiles;
	}

	/**
	 * This method is implemented to update donor profiles.
	 * 
	 * * @param DonorProfile Instance of donor profile to be updated
	 * 
	 * @return Returns true if donor profile updated successfully else false
	 * 
	 */
	@Override
	public boolean updateDonorProfile(DonorProfile donorProfile) {
		boolean result = false;
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.update(donorProfile);
			session.flush();
			result = true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return result;
	}

	/**
	 * This method is implemented to return credentials corresponding to given
	 * id.
	 * 
	 * * @param String ID of the donor
	 * 
	 * @return Returns instance of respective donor credentials
	 * 
	 */
	@Override
	public DonorCredential loadCredentials(String id) {
		DonorCredential donorCredential = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Query query = session.getNamedQuery("getCredentialsById")
					.setString("id", id);
			donorCredential = (DonorCredential) query.list().get(0);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return donorCredential;
	}

	@Override
	public boolean updateDonorCredential(DonorCredential donorCredential) {
		DonorCredential donorCredentials = loadCredentials(donorCredential
				.getDonorEmail());
		PasswordDesEncrypter encrypter = PasswordDesEncrypter.getInstance();
		donorCredentials.setDonorPassword(encrypter.encrypt(donorCredential
				.getDonorPassword()));
		getSessionFactory().getCurrentSession().update(donorCredentials);
		return true;
	}

	/**
	 * This method is implemented to save blood request object in the database .
	 * 
	 * @param BloodRequest
	 *            BloodRequest instance to be saved
	 * 
	 * @return Returns true if donor request is saved successfully else returns
	 *         false.
	 */
	@Override
	public boolean saveBloodRequest(BloodRequest bloodRequest) {

		boolean result = false;
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.save(bloodRequest);
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean saveBloodDonationRecord(
			BloodDonationRecord bloodDonationRecord) {
		boolean result = false;
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.save(bloodDonationRecord);
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<BloodDonationRecord> loadBloodDonationRecords(String recordId) {
		List<BloodDonationRecord> bloodDonationRecords = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Query query = session.getNamedQuery("getDonationRecordById")
					.setString("recordId", recordId);
			bloodDonationRecords = query.list();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return Collections.unmodifiableList(bloodDonationRecords);
	}

	@Override
	public BloodDonationRecord loadBloodDonationRecord(String donorId) {
		BloodDonationRecord bloodDonationRecord = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Query query = session.getNamedQuery("getDonationRecordsByDonorId")
					.setString("donorId", donorId);
			bloodDonationRecord = (BloodDonationRecord) query.list().get(0);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return bloodDonationRecord;
	}

	@Override
	public boolean updateBloodDonationRecord(
			BloodDonationRecord bloodDonationRecord) {
		boolean result = false;
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.update(bloodDonationRecord);
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean loginAuthentication(DonorCredential donorCredential) {

		long count = 0;
		try {
			Session session = getSessionFactory().getCurrentSession();
			PasswordDesEncrypter encrypter = PasswordDesEncrypter.getInstance();
			Query query = session
					.getNamedQuery("getCredentialsById_Pwd")
					.setString("id", donorCredential.getDonorEmail())
					.setString(
							"password",
							encrypter.encrypt(donorCredential
									.getDonorPassword()));
			count = (Long) query.list().get(0);
			if (count > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * This method is implemented to update the status registered donor to
	 * activate or decativate based on the input.
	 * 
	 * @param donorProfile
	 *            DonorProfile instance to be updated.
	 * @param emailVerification
	 *            EmailVerification instance to be removed from database.
	 * @return Return true or false based on the input.
	 */
	@Override
	public boolean updateVerifiedDonor(DonorProfile donorProfile,
			EmailVerification emailVerification) {
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.update(donorProfile);
			session.delete(emailVerification);
			session.flush();
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * This method is implemented to register new donor profile using blood
	 * donation system.
	 * 
	 * @param donorProfile
	 *            DonorProfile instance to saved.
	 * @param donorCredential
	 *            DonorCredential instance to be saved.
	 * @param emailVerification
	 *            EmailVerification instance to be saved.
	 * @return return true or false based on the implementation.
	 * @throws Exception
	 */
	@Override
	public boolean saveDonor(DonorProfile donorProfile,
			DonorCredential donorCredential,DonorRoles donorRoles, EmailVerification emailVerification)
			throws Exception {
		if (saveDonor(donorProfile, donorCredential)) {
			Session session = getSessionFactory().getCurrentSession();
			try {
				session.save(emailVerification);
				/*donorRoles.setDonorId(donorProfile.getDonor_uuid());
				session.save(donorRoles);*/
				return true;

			} catch (Exception e) {
				e.printStackTrace();
				return false;
				
			}
		}
		return false;
	}

	@Override
	public EmailVerification loadEmailByTokenId(String token) throws Exception {
		EmailVerification emailVerification = null;
		try {
			Session session = getSessionFactory().getCurrentSession();
			Query query = session.getNamedQuery("getTokenByMail").setString(
					"tokenId", token);
			emailVerification = (EmailVerification) query.list().get(0);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return emailVerification;
	}

	/**
	 * This method is implemented to update last date on which donor's
	 * information shared by email
	 * 
	 * @param List
	 *            <String> List of donor IDs to be updated
	 * 
	 * @return Returns true if profiles upated successfully else false
	 * 
	 */

	@Override
	public boolean updateLastEmailDate(List<String> donorIDs, Date date) {
		List<DonorProfile> donorProfiles = null;
		try {
			donorProfiles = loadDonorProfilesForIds(donorIDs);
			Session session = getSessionFactory().getCurrentSession();
			for (DonorProfile donorProfile : donorProfiles) {
				donorProfile.setLastEmailDate(date);
				session.saveOrUpdate(donorProfile);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * This method is implemented to update last date on which donor's
	 * information shared by SMS
	 * 
	 * @param List
	 *            <String> List of donor IDs to be updated
	 * 
	 * @return Returns true if profiles upated successfully else false
	 * 
	 */

	@Override
	public boolean updateLastSmsDate(List<String> donorIDs, Date date) {
		List<DonorProfile> donorProfiles = null;
		try {
			donorProfiles = loadDonorProfilesForIds(donorIDs);
			Session session = getSessionFactory().getCurrentSession();
			for (DonorProfile donorProfile : donorProfiles) {
				donorProfile.setLastSmsDate(date);
				session.saveOrUpdate(donorProfile);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public DonorProfile loadDonorRoleDetailsByEmaiId(String userName)
			throws Exception {

		Session session = getSessionFactory().getCurrentSession();
		DonorProfile donorProfiles = null;
		try {
			Query query = session.getNamedQuery("getDonorsRoleDetailByUserName")
					.setString("username", userName);
			donorProfiles = (DonorProfile) query.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return donorProfiles;
	}

	@Override
	public boolean saveFeedback(Feedback feedback) {
		boolean result = false;
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.save(feedback);
			result = true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
			
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Feedback> loadAllFeedbacks(boolean verify) {
		
		List<Feedback> feedbackList=null;
		Session session=getSessionFactory().getCurrentSession();
		try{
			Query query = session.getNamedQuery("getAllFeedbacks").setBoolean("verified", verify);
			feedbackList = query.list();

		} catch (Exception e) {

			e.printStackTrace();
		}

		return Collections.unmodifiableList(feedbackList);
	}
	


	
	@Override
	public boolean verifyFeedbacks(String uuid,boolean verify) {
		Session session=getSessionFactory().getCurrentSession();
		try{
			Query query = session.getNamedQuery("verifyFeedbacks").setString("uuid", uuid).setBoolean("verified", verify);
			if(query.executeUpdate()>0)
			{
				return true;
			}
			else return false;

		} catch (Exception e) {

			e.printStackTrace();
		}
		return true;
	}
}
