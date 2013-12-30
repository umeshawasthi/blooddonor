/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.pojo;



import java.util.Date;

/**
 *data transfer object for fetching and storing record for fresh blood request on entire 
 *blood donation domain.
 *  
 * @author Umesh Awasthi
 * @version 1.0  09/11/2011
 * @since 1.0
 * @see DonorProfile
 *  
 */
public class BloodRequest implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uuid;
	private String name;
	private Integer age;
	private String mobileNumber;
	private String secondaryContact;
	private char gender;
	private String bloodGroup;
	private Integer unitNeeded;
	private String hospitalName;
	private String state;
	private String district;
	private String location;
	private Integer postalCode;
	private Date creationDate=new Date();
	private Date lastUpdated=new Date();
	private String patientName;
	private String purpose;
	private boolean bloodRequestUpdated=false;
	private boolean verificationFlag=false;
	private Date requiredDate;

	public BloodRequest() {
	}

	public BloodRequest(int uuid, String name, Integer age, String mobileNumber,
			char gender, String bloodGroup, Integer unitNeeded, String hospitalName,
			String state, String location, Date creationDate, Date lastUpdated,String patientName,String purpose,boolean verificationFlag, Date requiredDate) {
		this.uuid = uuid;
		this.name = name;
		this.age = age;
		this.mobileNumber = mobileNumber;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.unitNeeded = unitNeeded;
		this.hospitalName = hospitalName;
		this.state = state;
		this.location = location;
		this.creationDate = creationDate;
		this.lastUpdated = lastUpdated;
		this.patientName=patientName;
		this.purpose=purpose;
		this.verificationFlag=verificationFlag;
		this.requiredDate=requiredDate;
	}

	public BloodRequest(int uuid, String name, Integer age, String mobileNumber,
			String secondaryContact, char gender, String bloodGroup,
			Integer unitNeeded, String hospitalName, String state, String district,
			String location, Integer postalCode, Date creationDate,
			Date lastUpdated,String patientName,String purpose,boolean verificationFlag,Date requiredDate)  {
		this.uuid = uuid;
		this.name = name;
		this.age = age;
		this.mobileNumber = mobileNumber;
		this.secondaryContact = secondaryContact;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.unitNeeded = unitNeeded;
		this.hospitalName = hospitalName;
		this.state = state;
		this.district = district;
		this.location = location;
		this.postalCode = postalCode;
		this.creationDate = creationDate;
		this.lastUpdated = lastUpdated;
		this.patientName=patientName;
		this.purpose=purpose;
		this.verificationFlag=verificationFlag;
		this.requiredDate=requiredDate;
	}

	public int getUuid() {
		return this.uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
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

	
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSecondaryContact() {
		return secondaryContact;
	}

	public void setSecondaryContact(String secondaryContact) {
		this.secondaryContact = secondaryContact;
	}

	public char getGender() {
		return this.gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}


	public Integer getUnitNeeded() {
		return this.unitNeeded;
	}

	public void setUnitNeeded(Integer unitNeeded) {
		this.unitNeeded = unitNeeded;
	}

	public String getHospitalName() {
		return this.hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLocation() {
		return this.location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Integer getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
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

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	
	
	
	public boolean isBloodRequestUpdated() {
		return bloodRequestUpdated;
	}

	public void setBloodRequestUpdated(boolean bloodRequestUpdated) {
		this.bloodRequestUpdated = bloodRequestUpdated;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public boolean isVerificationFlag() {
		return verificationFlag;
	}

	public void setVerificationFlag(boolean verificationFlag) {
		this.verificationFlag = verificationFlag;
	}

	public Date getRequiredDate() {
		return requiredDate;
	}

	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}
	
	

}
