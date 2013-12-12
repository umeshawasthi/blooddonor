/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.pojo;



import java.util.Date;



/**
 * Blood donation record class is used to keep track of the last donation date of the
 * donor.This will help to modify search criteria.
 *  
 * @author Umesh Awasthi
 * @version 1.0  09/11/2011
 * @since 1.0
 * 
 *  
 */
public class BloodDonationRecord implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String blood_donationrecord_uuid;
	private String patientName;
	private String donationPlace;
	private String hospitalName;
	private Date bloodDonationdate;
	private Date creationDate=new Date();
	private Date lastUpdated=new Date();
	private DonorProfile donorProfiles;
	private String donor_id;

	public BloodDonationRecord() {
	}

	public BloodDonationRecord(String uuid, Date creationDate, Date lastUpdated) {
		this.blood_donationrecord_uuid = uuid;
		this.creationDate = creationDate;
		this.lastUpdated = lastUpdated;
	}

	public BloodDonationRecord(String uuid, String patientName,
			String donationPlace, String hospitalName, Date bloodDonationdate,
			Date creationDate, Date lastUpdated, DonorProfile donorProfiles) {
		this.blood_donationrecord_uuid = uuid;
		this.patientName = patientName;
		this.donationPlace = donationPlace;
		this.hospitalName = hospitalName;
		this.bloodDonationdate = bloodDonationdate;
		this.creationDate = creationDate;
		this.lastUpdated = lastUpdated;
		this.donorProfiles = donorProfiles;
	}

	
	public String getBlood_donationrecord_uuid() {
		return blood_donationrecord_uuid;
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

	public Date getCreationDate() {
		return this.creationDate;
	}

	

	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public DonorProfile getDonorProfiles() {
		return donorProfiles;
	}

	public void setDonorProfiles(DonorProfile donorProfiles) {
		this.donorProfiles = donorProfiles;
	}

	public String getDonor_id() {
		return donor_id;
	}

	public void setDonor_id(String donor_id) {
		this.donor_id = donor_id;
	}

	

	   
	
}
