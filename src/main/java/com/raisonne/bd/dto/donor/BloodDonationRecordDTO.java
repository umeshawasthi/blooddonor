/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.dto.donor;



import java.util.Date;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;



/**
 * Blood donation record Dto class is used to store and transfer donors blood donation history.
 *  
 *  
 */
public class BloodDonationRecordDTO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String blood_donationrecord_uuid;
	@NotNull(message="not.blank")
	@NotBlank(message="not.blank")
	private String patientName;
	
	@NotNull(message="not.blank")
	@NotBlank(message="not.blank")
	private String donationPlace;
	
	@NotNull(message="not.blank")
	@NotBlank(message="not.blank")
	private String hospitalName;
	
	@NotNull(message="not.blank")
	@NotBlank(message="not.blank")
	private Date bloodDonationdate;
	
	private Date tempBloodDonationDate;
	private String donor_id;


	public String getBlood_donationrecord_uuid() {
		return blood_donationrecord_uuid;
	}
	
	

	public Date getTempBloodDonationDate() {
		return bloodDonationdate;
	}



	public void setBlood_donationrecord_uuid(String blood_donationrecord_uuid) {
		this.blood_donationrecord_uuid = blood_donationrecord_uuid;
	}

	public String getPatientName() {
		return this.patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getDonationPlace() {
		return this.donationPlace;
	}

	public void setDonationPlace(String donationPlace) {
		this.donationPlace = donationPlace;
	}

	public String getHospitalName() {
		return this.hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Date getBloodDonationdate() {
		return this.bloodDonationdate;
	}

	public void setBloodDonationdate(Date bloodDonationdate) {
		this.bloodDonationdate = bloodDonationdate;
	}

	public String getDonor_id() {
		return donor_id;
	}

	public void setDonor_id(String donor_id) {
		this.donor_id = donor_id;
	}   
	
}
