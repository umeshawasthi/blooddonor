/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.pojo;


import java.util.Date;

/**
 *DTO for handling donor prefrences.Prefrences can range from hiding donor mobile numbers,
 *donor availablity and preferable contatc time.
 *
 *  
 * @author Umesh Awasthi
 * @version 1.0  09/11/2011
 * @since 1.0
 * @see DonorProfile
 *  
 */
public class DonorPreferences implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String donorID;
	private Character smsAlert;
	private Character hidePhonenumbers;
	private String preferedContactTime;
	private Character donorAvailability;
	private Date creationDate=new Date();
	private Date lastUpdated=new Date();
	private DonorProfile donorProfile;
	

	public DonorPreferences() {
	}

	public DonorPreferences(String uuid, String preferedContactTime,
			String donorId, Date creationDate, Date lastUpdated) {
		this.donorID = uuid;
		this.preferedContactTime = preferedContactTime;
		//this.donorId = donorId;
		this.creationDate = creationDate;
		this.lastUpdated = lastUpdated;
	}

	public DonorPreferences(String uuid, Character smsAlert,
			Character hidePhonenumbers, String preferedContactTime,
			Character donorAvailability, String donorId, Date creationDate,
			Date lastUpdated, DonorProfile donorProfiles) {
		this.donorID = uuid;
		this.smsAlert = smsAlert;
		this.hidePhonenumbers = hidePhonenumbers;
		this.preferedContactTime = preferedContactTime;
		this.donorAvailability = donorAvailability;
		//this.donorId = donorId;
		this.creationDate = creationDate;
		this.lastUpdated = lastUpdated;
		this.donorProfile = donorProfiles;
	}

	

	public String getdonorID() {
		return donorID;
	}

	public void setdonorID(String donorID) {
		this.donorID = donorID;
	}

	public Character getSmsAlert() {
		return this.smsAlert;
	}

	public void setSmsAlert(Character smsAlert) {
		this.smsAlert = smsAlert;
	}

	public Character getHidePhonenumbers() {
		return this.hidePhonenumbers;
	}

	public void setHidePhonenumbers(Character hidePhonenumbers) {
		this.hidePhonenumbers = hidePhonenumbers;
	}

	public String getPreferedContactTime() {
		return this.preferedContactTime;
	}

	public void setPreferedContactTime(String preferedContactTime) {
		this.preferedContactTime = preferedContactTime;
	}

	public Character getDonorAvailability() {
		return this.donorAvailability;
	}

	public void setDonorAvailability(Character donorAvailability) {
		this.donorAvailability = donorAvailability;
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

	public DonorProfile getDonorProfile() {
		return donorProfile;
	}

	public void setDonorProfile(DonorProfile donorProfile) {
		this.donorProfile = donorProfile;
	}

	

	
}
