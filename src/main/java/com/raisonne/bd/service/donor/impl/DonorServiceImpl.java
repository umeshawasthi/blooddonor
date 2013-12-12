package com.raisonne.bd.service.donor.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.raisonne.bd.converter.DonorConverter;
import com.raisonne.bd.dao.donor.DonorDao;
import com.raisonne.bd.dto.donor.BloodDonationRecordDTO;
import com.raisonne.bd.dto.donor.DonorCredentialDTO;
import com.raisonne.bd.dto.donor.DonorProfileDTO;
import com.raisonne.bd.dto.donor.DonorRolesDTO;
import com.raisonne.bd.dto.donor.EmailVerificationDTO;
import com.raisonne.bd.dto.donor.FeedbackDTO;
import com.raisonne.bd.pojo.BloodDonationRecord;
import com.raisonne.bd.pojo.DonorCredential;
import com.raisonne.bd.pojo.DonorPreferences;
import com.raisonne.bd.pojo.DonorProfile;
import com.raisonne.bd.pojo.DonorRoles;
import com.raisonne.bd.pojo.EmailVerification;
import com.raisonne.bd.pojo.Feedback;
import com.raisonne.bd.service.donor.DonorService;
import com.raisonne.bd.util.PasswordDesEncrypter;
import com.raisonne.bd.util.PropertyLoader;
import com.raisonne.bd.util.mail.MailSender;
import com.raisonne.bd.util.mail.Sender;
import com.raisonne.bd.util.mail.SimpleMailMessage;

/**
 * This service class is created to implement service methods for donor(s).
 * These methods defines logic for CRUD operations on donor instances and
 * business logic as well.
 * 
 * 
 */
public class DonorServiceImpl implements DonorService{

	private static Logger _log = Logger.getLogger(DonorServiceImpl.class);
	private DonorDao donorDao;
	private DonorConverter donorConverter;
	
	private SimpleMailMessage simpleMailMessage;
	private Sender sender;	
	private MailSender mailSender;
	
	/**
	 * This method is implemented to retrieve donor profiles based on blood
	 * group, state and city of donors.
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
	 *            Number of records to be loaded.
	 * 
	 * @return It returns List of retrieved donor profiles.
	 */
	@Override
	public List loadDonorsInfoByArea(String bloodGroup, String state,
			String city, int fromIndex, int pageSize) {
		return getDonorDao().loadDonorsInfoByArea(bloodGroup, state, city,
				fromIndex, pageSize);
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
	public List loadDonorsInfoByPostalCode(String bloodGroup, String postalCode,
			int fromIndex, int pageSize) {
		return getDonorDao().loadDonorsInfoByPostalCode(bloodGroup, postalCode,
				fromIndex, pageSize);
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
	public boolean saveDonor(DonorProfileDTO donorProfileDto,
			DonorCredentialDTO donorCredentialDto) throws Exception {
		DonorProfile donorProfile = donorConverter
				.convertdonorDtoToPojo(donorProfileDto);
		DonorCredential donorCredential = donorConverter
				.convertdonorCredentialDtoToPojo(donorCredentialDto);
		boolean result = donorDao.saveDonor(donorProfile, donorCredential);		
		if (result)
		{
			return signInDonor(donorCredentialDto);
		}
		else
			return result;
	}

	/**
	 * This method is implemented to sign in the donor
	 * 
	 * @param donorCredential
	 *            Credentials of the donor
	 * @return true if donor sign in successfully else false
	 * 
	 * @throws Exception
	 */

	@Override
	public boolean signInDonor(DonorCredentialDTO donorCredential)
			throws Exception {

		return true;
	}

	/**
	 * This method is implemented to return total count of existing donor
	 * profiles in the database.
	 * 
	 * @return Returns total count of existing donor profiles
	 */

	@Override
	public long loadDonorProfilesCount(Map<String, String> searchCriteris,
			String searchType) {
		return donorDao.loadDonorProfilesCount(searchCriteris, searchType);
	}

	/**
	 * This service method is declared to return donorProfilesDTOs for given
	 * donor IDs
	 * 
	 * @param donorIds
	 * @return Returns list of donor profiles
	 */
	@Override
	public List<DonorProfileDTO> loadDonorProfilesForIds(List<String> donorIds) throws Exception{
		List<DonorProfile> donorProfiles = donorDao
				.loadDonorProfilesForIds(donorIds);
		List<DonorProfileDTO> donorProfileDTOs = new ArrayList<DonorProfileDTO>();
		Iterator<DonorProfile> itr = donorProfiles.iterator();
		while (itr.hasNext()) {
			donorProfileDTOs.add(donorConverter.convertDonorPojoToDto(itr
					.next()));
		}

		return donorProfileDTOs;
	}

	/**
	 * This service method is declared to return the status of avability of
	 * donor's mailid
	 * 
	 * @param String
	 *            mailId
	 * @return boolean based on the result
	 */
	@Override
	public boolean checkEmailId(String mailId) throws Exception {
		return donorDao.checkEmailId(mailId);

	}

	/**
	 * An implimentation method responsible for loading Donor Profile based on
	 * the email id. Email will work as a unique key and can be used to load
	 * donor profile this method is useful in the context when user want to
	 * authenticate him/her using any of the OAuth/OpenID service which give
	 * back verified email id associated with user account.
	 */
	@Override
	public DonorProfileDTO loadDonorByEmailId(String email) throws Exception {

		DonorProfile donorProfile = donorDao.loadDonorByEmaiId(email);
		if (donorProfile == null) {
			_log.info("Unable to find any profile for this email id " + email
					+ " returning null");
			return null;
		}
		DonorProfileDTO donorProfileDTOs = new DonorProfileDTO();

		donorProfileDTOs = donorConverter.convertDonorPojoToDto(donorProfile);
		// setting reference to null for GC
		donorProfile = null;
		return donorProfileDTOs;
	}

	/**
	 * This service method is implemented to update donor profiles by calling
	 * respected dao api.
	 * 
	 * * @param DonorProfileDTO Instance of DonorProfileDTO to be updated
	 * 
	 * @return Returns true if donor profile updated successfully else false
	 * 
	 */

	@Override
	public boolean updateDonorProfile(DonorProfileDTO donorProfileDTO) {
		// checked if logged in
		
		DonorProfile donorProfile=null;
		try {
			donorProfile = donorDao.loadDonorByEmaiId(donorProfileDTO.getEmail());
		} catch (Exception e1) {
			
			e1.printStackTrace();
		}
		
		if (isLoggedIn(donorProfileDTO.getEmail())) {
			donorProfile.setMobile(donorProfileDTO.getMobile());
			donorProfile.setName(donorProfileDTO.getName());
			donorProfile.setBloodGroup(donorProfileDTO.getBloodGroup());
			donorProfile.setDateOfBirth(donorProfileDTO.getDateOfBirth());
			donorProfile.setGender(donorProfileDTO.getGender());
			donorProfile.setState(donorProfileDTO.getState());
			donorProfile.setDistrict(donorProfileDTO.getDistrict());
			donorProfile.setLocation(donorProfileDTO.getLocation());
			donorProfile.setWeight(donorProfileDTO.getWeight());
			donorProfile.setPostalCode(donorProfileDTO.getPostalCode());
			donorProfile.setSecondaryContactNumber(donorProfile.getSecondaryContactNumber());
			DonorPreferences donorPreferences=donorProfile.getDonorPreferences();
			donorPreferences.setHidePhonenumbers(donorProfileDTO.getDonorPreferences().getHidePhonenumbers());
			donorPreferences.setPreferedContactTime(donorProfileDTO.getDonorPreferences().getPreferedContactTime());
			donorPreferences.setDonorAvailability(donorProfileDTO.getDonorPreferences().getDonorAvailability());
			donorProfile.setDonorPreferences(donorPreferences);
			return donorDao.updateDonorProfile(donorProfile);
		} else
			return false;
	}
	
	

	@Override
	public DonorCredentialDTO loadCredentials(String id) {
		if (isLoggedIn(id)) {
			DonorCredential donorCredential=donorDao.loadCredentials(id);
			PasswordDesEncrypter encrypter =PasswordDesEncrypter.getInstance();
			donorCredential.setDonorPassword(encrypter.decrypt(donorCredential.getDonorPassword()));
			return donorConverter.convertDonorCredentialToDto(donorCredential);
		} else
			return null;
	}

	@Override
	public boolean updateDonorCredential(DonorCredentialDTO donorCredentialDTO) {
		if (isLoggedIn(donorCredentialDTO.getDonorEmail()))
			return donorDao.updateDonorCredential(donorConverter
					.convertdonorCredentialDtoToPojo(donorCredentialDTO));
		else
			return false;
	}

	
	@Override
	public boolean loginAuthentication(DonorCredentialDTO donorCredentialDTO) {
		
		boolean userauthenticated=donorDao.loginAuthentication(donorConverter
				.convertdonorCredentialDtoToPojo(donorCredentialDTO));
		_log.info("user authenticated---- "+userauthenticated);
		if (userauthenticated)
		{			
			return true;
		}
		else
			return false;
	}


		
	@Override
	public boolean isLoggedIn(String id) {
		return true;
	}

	public DonorDao getDonorDao() {
		return donorDao;
	}

	public void setDonorDao(DonorDao donorDao) {
		this.donorDao = donorDao;
	}

	public DonorConverter getDonorConverter() {
		return donorConverter;
	}

	public void setDonorConverter(DonorConverter donorConverter) {
		this.donorConverter = donorConverter;
	}

	@Override
	public boolean saveBloodDonationRecord(
			BloodDonationRecordDTO bloodDonationRecordDTO) {
		
		return donorDao.saveBloodDonationRecord(donorConverter.convertBloodDonationRecordDtoToPojo(bloodDonationRecordDTO));
	}

	@Override
	public boolean updateBloodDonationRecord(
			BloodDonationRecordDTO bloodDonationRecordDTO) {
		return donorDao.updateBloodDonationRecord(donorConverter.convertBloodDonationRecordDtoToPojo(bloodDonationRecordDTO));
	}

	@Override
	public List<BloodDonationRecord> loadBloodDonationRecords(String donorId) {
		return donorDao.loadBloodDonationRecords(donorId);
	}

	@Override
	public BloodDonationRecordDTO loadBloodDonationRecord(String recordId) {
		return donorConverter.convertBloodDonationRecordToDto(donorDao.loadBloodDonationRecord(recordId));
	}
	
	/**
	 * This method is created to implement registration verification mail functionality.
	 * @param toMail
	 * 			mail id of donor .
	 * @param token
	 * 			generated token which is used to create verification URL. 
	 * @param context
	 * 			Used for creation of verIfication URL.
	 * @return	
	 * 			Return true or false based on the result.
	 */
	private boolean sendVerficationMail(String toMail,String token,ServletContext context)
	{
		Properties properties = PropertyLoader
				.loadProperties("mail.properties");
		sender.setFrom(properties.getProperty("mail.fromEmail"));
		sender.setName(properties.getProperty("mail.fromEmail.name"));
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setSubject(properties.getProperty("mail.verificationSubject"));
		simpleMailMessage.setTo(toMail);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("toMail", toMail);
		model.put("token", token);
		model.put("servletContext", context);

		try {
			String logoPath = context.getRealPath(java.io.File.separator
					.concat("images").concat(java.io.File.separator)
					.concat("logo.jpg"));

			mailSender.sendAttachment(simpleMailMessage,
					new String[] { logoPath },
					properties.getProperty("mail.template.registrationVerfication"),
					model);
		}

		catch (Exception exception) {
			exception.printStackTrace();
		}
		return true;
	}
	/**
	 * This method is implemented to activate the registered donor.
	 *  
	 * @param String
	 *            token of donor is verified.
	 *            
	 * @return true if donor is activated successfully else false.
	 * @throws Exception
	 */
	@Override
	public boolean updateVerifiedDonor(String token) throws Exception {
		DonorProfile donorProfile=null;
		EmailVerification emailVerification=donorDao.loadEmailByTokenId(token);
		if(null!=emailVerification)
		{
		donorProfile=donorDao.loadDonorByEmaiId(emailVerification.getEmail());
		donorProfile.setActivatedFlag(true);
		}
		return(donorDao.updateVerifiedDonor(donorProfile,emailVerification));
		
	}
	/**
	 * This method is implemented  to register new donor profile using blood donation system. 
	 * @param donorProfile
	 *             DonorProfile instance to be saved.
	 * @param donorCredential
	 * 				DonorCredential instance to be saved.
	 * @param emailVerificationDTO
	 *              EmailVerification instance to be saved.
	 * @param context
	 * 				Used for mail functionality.
	 * @return
	 * 				based of the result of method returns true or false.
	 * @throws Exception
	 */
	@Override
	public boolean saveDonor(DonorProfileDTO donorProfileDto,
			DonorCredentialDTO donorCredentialDto,EmailVerificationDTO emailVerificationDTO,DonorRolesDTO donorRolesDTO,ServletContext context) throws Exception {
		DonorProfile donorProfile = donorConverter
				.convertdonorDtoToPojo(donorProfileDto);
		DonorCredential donorCredential = donorConverter
				.convertdonorCredentialDtoToPojo(donorCredentialDto);
		EmailVerification emailVerification=donorConverter.convertEmailVerificationDtoToPojo(emailVerificationDTO);
		PasswordDesEncrypter encrypter =PasswordDesEncrypter.getInstance();
		donorCredential.setDonorPassword(encrypter.encrypt(donorCredentialDto.getDonorPassword()));
		DonorRoles donorRoles=donorConverter.convertDonorRolesDtoToPojo(donorRolesDTO);
		//boolean result = donorDao.saveDonor(donorProfile, donorCredential,emailVerification);		
		if (donorDao.saveDonor(donorProfile, donorCredential,donorRoles,emailVerification))
		{
			sendVerficationMail(emailVerification.getEmail(), emailVerification.getTokenId(),context);
			return signInDonor(donorCredentialDto);
		}
		else
			return false;//result;
	}

	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * This method is implemented to update last date on which donor's information shared by email
	 * 
	 * @param List<String>
	 *            List of donor IDs to be updated
	 * 
	 * @return Returns true if profiles upated successfully else false
	 * 			 
	 */
	@Override
	public boolean updateLastEmailDate(List<String> donorIDs, Date date) {
		return donorDao.updateLastEmailDate(donorIDs, date);
	}

	/**
	 * This method is implemented to update last date on which donor's information shared by SMS
	 * 
	 * @param List<String>
	 *            List of donor IDs to be updated
	 * 
	 * @return Returns true if profiles upated successfully else false
	 * 			 
	 */
	@Override
	public boolean updateLastSmsDate(List<String> donorIDs, Date date) {
		return donorDao.updateLastSmsDate(donorIDs, date);
	}

	@Override
	public DonorProfile loadDonorRoleDetailsByEmaiId(String userName) throws Exception {
		return getDonorDao().loadDonorRoleDetailsByEmaiId(userName);
	}

	@Override
	public boolean saveFeedback(FeedbackDTO feedbackDTO) {
		Feedback feedback = donorConverter
				.convertFeedbackDtoToPojo(feedbackDTO);
		if (donorDao.saveFeedback(feedback))
		{
				return true;
		}
		else
			return false;
		}

	@Override
	public List<FeedbackDTO> loadAllFeedbacks(boolean verify) {
		
		List<FeedbackDTO> feedbackdtos=new ArrayList<FeedbackDTO>();
		List<Feedback> feedbacks=donorDao.loadAllFeedbacks(verify);
		Iterator<Feedback> feedback=feedbacks.iterator();
		while(feedback.hasNext())
		{
			feedbackdtos.add(donorConverter.convertFeedbackToDto(feedback.next()));
		}
		return feedbackdtos;
	}
	
	
	@Override
	public boolean verifyFeedbacks(String uuid,boolean verify) {
		
		return donorDao.verifyFeedbacks(uuid,verify);	
	}

	
	}
