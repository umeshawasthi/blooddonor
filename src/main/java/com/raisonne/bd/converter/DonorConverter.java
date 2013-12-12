package com.raisonne.bd.converter;

import com.raisonne.bd.dto.bloodrequest.BloodRequestDTO;
import com.raisonne.bd.dto.donor.BloodDonationRecordDTO;
import com.raisonne.bd.dto.donor.DonorCredentialDTO;
import com.raisonne.bd.dto.donor.DonorProfileDTO;
import com.raisonne.bd.dto.donor.DonorRolesDTO;
import com.raisonne.bd.dto.donor.EmailVerificationDTO;
import com.raisonne.bd.dto.donor.FeedbackDTO;
import com.raisonne.bd.pojo.BloodDonationRecord;
import com.raisonne.bd.pojo.BloodRequest;
import com.raisonne.bd.pojo.DonorCredential;
import com.raisonne.bd.pojo.DonorProfile;
import com.raisonne.bd.pojo.DonorRoles;
import com.raisonne.bd.pojo.EmailVerification;
import com.raisonne.bd.pojo.Feedback;

/**
 * This interface is written to declare general methods for conversion of donor
 * related DTOs to POJOs and vice-versa
 * 
 * @author Arun
 * 
 */
public interface DonorConverter {
	/**
	 * This method is declared to convert given donorProfileDTO to DonorProfile
	 * 
	 * @param donorProfileDto
	 * @return Returns converted object of type DonorProfile
	 */
	DonorProfile convertdonorDtoToPojo(DonorProfileDTO donorProfileDto);

	/**
	 * This method is declared to convert given donorCredentialDto to
	 * DonorCredential
	 * 
	 * @param donorCredentialDto
	 * @return Returns converted object of type DonorCredential
	 */
	DonorCredential convertdonorCredentialDtoToPojo(
			DonorCredentialDTO donorCredentialDto);
	
	/**
	 * This method is declared to convert given DonorProfile to donorProfileDTO
	 * 
	 * @param donorProfile
	 * @return Returns converted object of type DonorProfileDTO
	 */
	DonorProfileDTO convertDonorPojoToDto(DonorProfile donorProfile);

	
	/**
	 * This method is declared to convert given donorCredential to donorcredentialDTO
	 * 
	 * @param donorProfile
	 * @return Returns converted object of type DonorCredentialDTO
	 */
	DonorCredentialDTO convertDonorCredentialToDto(DonorCredential donorCredential);

	/**
	 * This method is declared to convert given BloodRequestDTO to BloodRequest pojo
	 * 
	 * @param BloodRequestDTO
	 * @return Returns converted object of type BloodRequest
	 */
	BloodRequest convertBloodRequestDtoToPojo(BloodRequestDTO bloodRequestDto);
	/**
	 * This method is declared to  convert EmailVerificationDTO to EmailVerification pojo .
	 * 
	 * @param BloodRequest
	 * @return Returns converted object of type EmailVerification
	 */
	EmailVerification convertEmailVerificationDtoToPojo(EmailVerificationDTO verifyEmailDto);
	/**
	 * This method is declared to  convert BloodRequest to BloodRequestDTO .
	 * 
	 * @param BloodRequest
	 * @return Returns converted object of type BloodRequestDTO
	 */
	BloodRequestDTO convertBloodRequestPojoToDto(BloodRequest bloodRequest);

	
	BloodDonationRecord convertBloodDonationRecordDtoToPojo(BloodDonationRecordDTO bloodDonationRecordDTO);
	BloodDonationRecordDTO convertBloodDonationRecordToDto(BloodDonationRecord bloodDonationRecord);
	
	/**
	 * This method is declared to  convert DonorRolesDTO to DonorRoles pojo .
	 * 
	 * @param DonorRolesDTO
	 * @return Returns converted object of type DonorRoles
	 */
	DonorRoles convertDonorRolesDtoToPojo(DonorRolesDTO donorRolesDTO);
	/**
	 * This method is declared to  convert DonorRoles pojo to DonorRolesDTO .
	 * 
	 * @param DonorRoles
	 * @return Returns converted object of type DonorRolesDTO
	 */
	DonorRolesDTO convertDonorRolesToDto(DonorRoles donorRoles);
	
	/**
	 * This method is declared to  convert FeedbackDTO to Feedback pojo .
	 * 
	 * @param FeedbackDTO
	 * @return Returns converted object of type Feedback
	 */
	Feedback convertFeedbackDtoToPojo(FeedbackDTO feedbackDTO);
	/**
	 * This method is declared to  convert Feedback pojo to FeedbackDTO .
	 * 
	 * @param Feedback
	 * @return Returns converted object of type FeedbackDTO
	 */
	FeedbackDTO convertFeedbackToDto(Feedback feedback);
}
