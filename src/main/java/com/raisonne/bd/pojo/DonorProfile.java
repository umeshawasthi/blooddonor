/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.pojo;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *Main DTO which is being used to carry user profile from hibernate layer to database layer.
 *This DTO encapsulating most of the donor specific data being used through out the application.
 *  
 * @author Umesh Awasthi
 * @version 1.0  09/11/2011
 * @since 1.0
 * 
 *  
 */
public class DonorProfile implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String donor_uuid;
	private Set<BloodDonationRecord> bloodDonationrecord =new HashSet<BloodDonationRecord>();
	private DonorPreferences donorPreferences;
	private String name;
	private Integer age;
	private Date dateOfBirth;
	private String gender;
	private Integer weight;
	private String email;
	private String secondaryContactNumber;
	private String state;
	private String location;
	private String bloodGroup;
	private String district;
	private String postalCode;
	private String mobile;
	private boolean oauth;
	private boolean activatedFlag;
	private Date creationDate =new Date();
	private Date lastUpdated=new Date();
	private Date lastDonated=new Date(0);
	private Date lastLogin=new Date();
	private Date lastEmailDate=new Date(0);
	private Date lastSmsDate=new Date(0);
	private DonorRoles donorRoles;
	
	public DonorProfile() {
	}

	public DonorProfile(String donor_uuid, Set<BloodDonationRecord> bloodDonationrecord,
			DonorPreferences donorPreferences, String email, String state,
			String bloodGroup, String district, String mobile,boolean oauth,boolean activatedFlag, Date creationDate,DonorRoles donorRoles) {
		this.donor_uuid = donor_uuid;
		this.bloodDonationrecord = bloodDonationrecord;
		this.donorPreferences = donorPreferences;
		this.donorRoles=donorRoles;
		this.email = email;
		this.state = state;
		this.bloodGroup = bloodGroup;
		this.district = district;
		this.mobile = mobile;
		this.oauth=oauth;
		this.activatedFlag=activatedFlag;
		this.creationDate = creationDate;
	}

	public DonorProfile(String donor_uuid, Set<BloodDonationRecord> bloodDonationrecord,
			DonorPreferences donorPreferences, String name, Integer age,
			Date dateOfBirth, String gender, Integer weight,
			String postalcode, String email, String secondaryContactNumber,
			String state, String location, String bloodGroup, String district,
			String postalCode, String mobile,boolean oauth,boolean activatedFlag, Date creationDate, Date lastUpdated, Date lastLogin,DonorRoles donorRoles) {
		this.donor_uuid = donor_uuid;
		this.bloodDonationrecord = bloodDonationrecord;
		this.donorPreferences = donorPreferences;
		this.name = name;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.weight = weight;
		this.email = email;
		this.secondaryContactNumber = secondaryContactNumber;
		this.state = state;
		this.location = location;
		this.bloodGroup = bloodGroup;
		this.district = district;
		this.postalCode = postalCode;
		this.mobile = mobile;
		this.oauth=oauth;
		this.activatedFlag=activatedFlag;
		this.creationDate = creationDate;
		this.lastUpdated = lastUpdated;
		this.lastLogin = lastLogin;
		this.donorRoles=donorRoles;
	}

	
	

	public String getDonor_uuid() {
		return donor_uuid;
	}

	public void setDonor_uuid(String donor_uuid) {
		this.donor_uuid = donor_uuid;
	}

	public Set<BloodDonationRecord> getBloodDonationrecord() {
		return bloodDonationrecord;
	}

	public void setBloodDonationrecord(Set<BloodDonationRecord> bloodDonationrecord) {
		this.bloodDonationrecord = bloodDonationrecord;
	}

	public DonorPreferences getDonorPreferences() {
		return donorPreferences;
	}

	public void setDonorPreferences(DonorPreferences donorPreferences) {
		this.donorPreferences = donorPreferences;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getWeight() {
		return this.weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSecondaryContactNumber(String secondaryContactNumber) {
		this.secondaryContactNumber = secondaryContactNumber;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBloodGroup() {
		return this.bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Date getCreationDate() {
		return creationDate;
	}	

	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	
	public void addBloodDonationRecord(BloodDonationRecord bloodDonationrecord) {
		bloodDonationrecord.setDonorProfiles(this);
		this.bloodDonationrecord.add(bloodDonationrecord);
	}

	public String getSecondaryContactNumber() {
		return secondaryContactNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public boolean isOauth() {
		return oauth;
	}

	public void setOauth(boolean oauth) {
		this.oauth = oauth;
	}

	public boolean isActivatedFlag() {
		return activatedFlag;
	}

	public void setActivatedFlag(boolean activatedFlag) {
		this.activatedFlag = activatedFlag;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastDonated() {
		return lastDonated;
	}

	public void setLastDonated(Date lastDonated) {
		this.lastDonated = lastDonated;
	}
	
	public Date getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Date getLastEmailDate() {
		return lastEmailDate;
	}

	public void setLastEmailDate(Date lastEmailDate) {
		this.lastEmailDate = lastEmailDate;
	}

	public Date getLastSmsDate() {
		return lastSmsDate;
	}

	public void setLastSmsDate(Date lastSmsDate) {
		this.lastSmsDate = lastSmsDate;
	}

	public DonorRoles getDonorRoles() {
		return donorRoles;
	}

	public void setDonorRoles(DonorRoles donorRoles) {
		this.donorRoles = donorRoles;
	}
	
}
