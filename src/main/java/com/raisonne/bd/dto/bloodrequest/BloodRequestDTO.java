package com.raisonne.bd.dto.bloodrequest;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * This DTO is created to store blood request specific form(UI) data.
 * 
 * @author Himanshu Durgaapal
 * @version 1.0 14/12/2011
 * @since   1.0
 * 
 */
public class BloodRequestDTO implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private int uuid;
	@NotBlank(message="not.blank")
	private String name;
	@NotNull(message="not.blank")
	private Integer age;
	@MaxLength(value=12,message="max.length")
	@MinLength(value=10,message="min.length")
	@MatchPattern(pattern="^\\d*$", message="only.digits.allowed")
	private String mobileNumber;
	
	private String secondaryContact;
	@NotBlank(message="not.blank")
	private char gender;
	@NotBlank(message="not.blank")
	private String bloodGroup;
	@NotNull(message="not.blank")
	@MaxLength(value=2,message="max.length")
	@MatchPattern(pattern="^\\d*$", message="only.digits.allowed")	
	private Integer unitNeeded;
	@NotBlank(message="not.blank")
	private String hospitalName;
	@NotBlank(message="not.blank")
	private String state;	
	@NotBlank(message="not.blank")
	private String district;
	@NotBlank(message="not.blank")
	private String location;
	@NotNull(message="not.blank")
	private Integer postalCode;
	@NotBlank(message="not.blank")
	private String patientName;
	@NotNull(message="not.blank")
	private String purpose;
	
	@NotBlank(message="not.blank")
	@NotNull(message="not.blank")
	private Date requiredDate;
	
	private boolean verificationFlag;
	private boolean bloodRequestUpdated=false;
	
	
	
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
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
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public Integer getUnitNeeded() {
		return unitNeeded;
	}
	public void setUnitNeeded(Integer unitNeeded) {
		this.unitNeeded = unitNeeded;
	}
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}
	public String getPatientName() {
		return patientName;
	}
	public boolean isVerificationFlag() {
		return verificationFlag;
	}
	public void setVerificationFlag(boolean verificationFlag) {
		this.verificationFlag = verificationFlag;
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
	public Date getRequiredDate() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String tempDate=df.format(this.requiredDate);
		try {
			return df.parse(tempDate);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		return this.requiredDate;
	}
	public void setRequiredDate(Date requiredDate) {
		this.requiredDate = requiredDate;
	}
	
   
}
