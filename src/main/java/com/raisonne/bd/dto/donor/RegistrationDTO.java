package com.raisonne.bd.dto.donor;

import java.io.Serializable;

public class RegistrationDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String age;
	private String dateOfBirth;
	private String gender;
	private String weight;
	private String postalCode;
	private String email;
	private String state;
	private String location;
	private String pinCode;
	private String contactTime;
	private String secondaryContactNumber;
	private String bloodGroup;
	private String district;
	private String mobileNumber;
	private String password;
	private String confirmPassword;
	private String phoneVisibility;
	private String availabilityStatus;
	public RegistrationDTO()
	{
		
	}	
	public RegistrationDTO(String name, String age, String dateOfBirth,
			String gender, String weight, String postalCode, String email,
			String state, String location, String pinCode, String contactTime,
			String secondaryContactNumber, String bloodGroup, String district,
			String mobileNumber, String password, String confirmPassword,
			String phoneVisibility, String availabilityStatus) {
		super();
		this.name = name;
		this.age = age;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.weight = weight;
		this.postalCode = postalCode;
		this.email = email;
		this.state = state;
		this.location = location;
		this.pinCode = pinCode;
		this.contactTime = contactTime;
		this.secondaryContactNumber = secondaryContactNumber;
		this.bloodGroup = bloodGroup;
		this.district = district;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.phoneVisibility = phoneVisibility;
		this.availabilityStatus = availabilityStatus;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPinCode() {
		return pinCode;
	}
	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}
	public String getContactTime() {
		return contactTime;
	}
	public void setContactTime(String contactTime) {
		this.contactTime = contactTime;
	}
	public String getSecondaryContactNumber() {
		return secondaryContactNumber;
	}
	public void setSecondaryContactNumber(String secondaryContactNumber) {
		this.secondaryContactNumber = secondaryContactNumber;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getPhoneVisibility() {
		return phoneVisibility;
	}
	public void setPhoneVisibility(String phoneVisibility) {
		this.phoneVisibility = phoneVisibility;
	}
	public String getAvailabilityStatus() {
		return availabilityStatus;
	}
	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	

}
