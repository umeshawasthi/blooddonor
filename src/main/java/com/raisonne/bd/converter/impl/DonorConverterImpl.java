package com.raisonne.bd.converter.impl;

import org.springframework.beans.BeanUtils;

import com.raisonne.bd.converter.DonorConverter;
import com.raisonne.bd.dto.bloodrequest.BloodRequestDTO;
import com.raisonne.bd.dto.donor.BloodDonationRecordDTO;
import com.raisonne.bd.dto.donor.DonorCredentialDTO;
import com.raisonne.bd.dto.donor.DonorPreferencesDTO;
import com.raisonne.bd.dto.donor.DonorProfileDTO;
import com.raisonne.bd.dto.donor.DonorRolesDTO;
import com.raisonne.bd.dto.donor.EmailVerificationDTO;
import com.raisonne.bd.dto.donor.FeedbackDTO;
import com.raisonne.bd.pojo.BloodDonationRecord;
import com.raisonne.bd.pojo.BloodRequest;
import com.raisonne.bd.pojo.DonorCredential;
import com.raisonne.bd.pojo.DonorPreferences;
import com.raisonne.bd.pojo.DonorProfile;
import com.raisonne.bd.pojo.DonorRoles;
import com.raisonne.bd.pojo.EmailVerification;
import com.raisonne.bd.pojo.Feedback;

/**
 * This converter class is written to implement methods for conversion of donor
 * related DTOs to respected POJOs and vice-versa
 * 
 * @author Arun
 * 
 */
public class DonorConverterImpl implements DonorConverter {

	/**
	 * This method is implemented to convert given donorProfileDto to an object
	 * of type DonorProfile
	 * 
	 * @param donorProfileDto
	 * @return Returns converted object of type DonorProfile
	 */
	@Override
	public DonorProfile convertdonorDtoToPojo(DonorProfileDTO donorProfileDto) {
		DonorProfile donorProfile = new DonorProfile();
		DonorPreferences donorPreferences = new DonorPreferences();
		DonorRoles donorRoles=new DonorRoles();
		String[] ignoreProperties = { "donorPreferences","donorRoles" };
		BeanUtils.copyProperties(donorProfileDto, donorProfile,
				ignoreProperties);
		BeanUtils.copyProperties(donorProfileDto.getDonorPreferences(),
				donorPreferences);
		BeanUtils.copyProperties(donorProfileDto.getDonorRoles(),
				donorRoles);
		donorProfile.setDonorPreferences(donorPreferences);
		donorProfile.setDonorRoles(donorRoles);
		donorPreferences.setDonorProfile(donorProfile);
		donorPreferences.setdonorID(donorProfile.getDonor_uuid());
		donorRoles.setDonorProfile(donorProfile);
		donorRoles.setDonorId(donorProfile.getDonor_uuid());
		return donorProfile;
	}

	/**
	 * This method is implemented to convert given donorCredentialDto to an
	 * object of type DonorCredential
	 * 
	 * @param donorCredentialDto
	 * @return Returns converted object of type DonorCredential
	 */
	@Override
	public DonorCredential convertdonorCredentialDtoToPojo(
			DonorCredentialDTO donorCredentialDto) {
		DonorCredential donorCredential = new DonorCredential();
		BeanUtils.copyProperties(donorCredentialDto, donorCredential);
		return donorCredential;
	}

	/**
	 * This method is overridden to implement conversion of given DonorProfile to donorProfileDTO
	 * 
	 * @param donorProfile
	 * @return Returns converted object of type DonorProfileDTO
	 */
	@Override
	public DonorProfileDTO convertDonorPojoToDto(DonorProfile donorProfile) {
		DonorProfileDTO donorProfileDTO = new DonorProfileDTO();
		DonorPreferencesDTO donorPreferencesDTO = new DonorPreferencesDTO();
		DonorRolesDTO donorRolesDTO=new DonorRolesDTO(); 
		String[] ignoreProperties = { "donorPreferences","donorRoles" };
		BeanUtils.copyProperties(donorProfile, donorProfileDTO,
				ignoreProperties);
		BeanUtils.copyProperties(donorProfile.getDonorPreferences(),
				donorPreferencesDTO);
		BeanUtils.copyProperties(donorProfile.getDonorRoles(),
				donorRolesDTO);
		donorProfileDTO.setDonorPreferences(donorPreferencesDTO);
		donorProfileDTO.setDonorRoles(donorRolesDTO);
		return donorProfileDTO;
	}

	@Override
	public DonorCredentialDTO convertDonorCredentialToDto(DonorCredential donorCredential) {
		DonorCredentialDTO donorCredentialDTO = new DonorCredentialDTO();
		BeanUtils.copyProperties(donorCredential, donorCredentialDTO);
		return donorCredentialDTO;
	}

	/**
	 * This method is overridden to implement conversion of given BloodRequestDTO to BloodRequest pojo
	 * 
	 * @param BloodRequestDTO
	 * @return Returns converted object of type BloodRequest
	 */
	@Override
	public BloodRequest convertBloodRequestDtoToPojo(
			BloodRequestDTO bloodRequestDto) {
		BloodRequest bloodrequest=new BloodRequest();
		BeanUtils.copyProperties(bloodRequestDto, bloodrequest);
		return bloodrequest;
		
	}

	@Override
	public BloodDonationRecord convertBloodDonationRecordDtoToPojo(
			BloodDonationRecordDTO bloodDonationRecordDTO) {
		BloodDonationRecord bloodDonationRecord = new BloodDonationRecord();
		BeanUtils.copyProperties(bloodDonationRecordDTO, bloodDonationRecord);
		return bloodDonationRecord;
	}

	@Override
	public BloodDonationRecordDTO convertBloodDonationRecordToDto(
			BloodDonationRecord bloodDonationRecord) {
		BloodDonationRecordDTO bloodDonationRecordDTO = new BloodDonationRecordDTO();
		BeanUtils.copyProperties(bloodDonationRecord, bloodDonationRecordDTO);
		return bloodDonationRecordDTO;
	}
	/**
	 * This method is   implemented to  convert EmailVerificationDTO to EmailVerification pojo .
	 * 
	 * @param BloodRequest
	 * @return Returns converted object of type EmailVerification
	 */
	@Override
	public EmailVerification convertEmailVerificationDtoToPojo(EmailVerificationDTO verifyEmailDto) {
		// TODO Auto-generated method stub
		EmailVerification verifyEmail=new EmailVerification();
		BeanUtils.copyProperties(verifyEmailDto,verifyEmail);		
		return verifyEmail;
	}

	/**
	 * This method is   implemented to  convert BloodRequest to BloodRequestDTO .
	 * 
	 * @param BloodRequest
	 * @return Returns converted object of type BloodRequestDTO
	 */
	@Override
	public BloodRequestDTO convertBloodRequestPojoToDto(
			BloodRequest bloodRequest) {
		BloodRequestDTO bloodRequestDTO=new BloodRequestDTO();
		BeanUtils.copyProperties(bloodRequest, bloodRequestDTO);
		return bloodRequestDTO;
	}
	
	

	@Override
	public DonorRoles convertDonorRolesDtoToPojo(DonorRolesDTO donorRolesDTO) {
	
		DonorRoles donorRoles=new DonorRoles();
		BeanUtils.copyProperties(donorRolesDTO, donorRoles);
		return  donorRoles;
		
	}

	@Override
	public DonorRolesDTO convertDonorRolesToDto(DonorRoles donorRoles) {
		DonorRolesDTO donorRolesDTO=new DonorRolesDTO();
		BeanUtils.copyProperties(donorRoles, donorRolesDTO);
		return  donorRolesDTO;
	}

	@Override
	public Feedback convertFeedbackDtoToPojo(FeedbackDTO feedbackDTO) {
		Feedback feedback=new Feedback();
		BeanUtils.copyProperties(feedbackDTO, feedback);
		return  feedback;
	}

	@Override
	public FeedbackDTO convertFeedbackToDto(Feedback feedback) {
		FeedbackDTO donorRolesDTO=new FeedbackDTO();
		BeanUtils.copyProperties(feedback, donorRolesDTO);
		return  donorRolesDTO;
	}

	

}
