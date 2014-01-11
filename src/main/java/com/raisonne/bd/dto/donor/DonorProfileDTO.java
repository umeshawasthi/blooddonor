/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.dto.donor;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.raisonne.bd.validators.MinAge;

import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.MaxLength;
import net.sf.oval.constraint.Min;
import net.sf.oval.constraint.MinLength;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * This DTO is created to store donor profile data from UI form.
 * 
 * @author Arun
 * 
 */
public class DonorProfileDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private DonorPreferencesDTO donorPreferences;
	private DonorRolesDTO donorRoles;
	private String role;
	@NotBlank(message="not.blank")
	private String name;
	private Date tempDOB;
	@NotBlank(message="not.blank")
	@NotNull(message="not.blank")
	@MinAge(message="invalid.minAge")
	private Date dateOfBirth;
	
	@NotBlank(message="not.blank")
	@NotNull(message="not.blank")
	private String gender;
	
	@NotNull(message="not.blank")
	@Min(value=45,message="min.number")
	private Integer weight;
	
	@MatchPattern(pattern="^([a-z0-9]{1,}[\\.\\_\\-]?[a-z0-9]{1,})\\@([a-z0-9]{2,}\\.)([a-z]{2,2}|org|net|com|gov|edu|int|info|biz|museum)$",message="invalid.email")
	private String email;
	
	@NotBlank(message="not.blank")
	private String bloodGroup;
	
	@NotBlank(message="not.blank")
	private String state;
	
	@NotBlank(message="not.blank")
	private String district;
	
	@NotBlank(message="not.blank")
	private String location;
	
	@NotBlank(message="not.blank")
	private String postalCode;
	

	@MaxLength(value=12,message="max.length")
	@MinLength(value=10,message="min.length")
	@MatchPattern(pattern="^\\d*$", message="only.digits.allowed")
	private String mobile;
	
	
	@MatchPattern(pattern="^\\d*$", message="only.digits.allowed")
	private String secondaryContactNumber;
	
	private String donor_uuid;
	private boolean oauth=false;

/*	public String getMinAgeDate(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR,-18);
		return DateFormat.getInstance().format(cal);
	}*/
	public DonorProfileDTO() {
		
	}

	public DonorPreferencesDTO getDonorPreferences() {
		return donorPreferences;
	}

	public void setDonorPreferences(DonorPreferencesDTO donorPreferences) {
		this.donorPreferences = donorPreferences;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateOfBirth() {
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String tempDate=df.format(this.dateOfBirth);
		try {
			return df.parse(tempDate);
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
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

	public String getSecondaryContactNumber() {
		return this.secondaryContactNumber;
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

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the donor_uuid
	 */
	public String getDonor_uuid() {
		return donor_uuid;
	}

	/**
	 * @param donor_uuid
	 *            the donor_uuid to set
	 */
	public void setDonor_uuid(String donor_uuid) {
		this.donor_uuid = donor_uuid;
	}
	
	public Date getTempDOB(){
		return this.dateOfBirth;
	}
	

	public boolean isOauth() {
		return oauth;
	}

	public void setOauth(boolean oauth) {
		this.oauth = oauth;
	}

	public String getRole() {
		return "user";//role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public DonorRolesDTO getDonorRoles() {
		return donorRoles;
	}

	public void setDonorRoles(DonorRolesDTO donorRoles) {
		this.donorRoles = donorRoles;
	}

	

	
}
