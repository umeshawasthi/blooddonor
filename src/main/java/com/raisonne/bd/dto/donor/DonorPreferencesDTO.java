/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.dto.donor;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

/**
 * This DTO is created to store donor prefrences related data from the UI form.
 * Prefrences can range from hiding donor mobile numbers,donor availablity and
 * preferable contatc time.
 * 
 * 
 * @author Arun
 * 
 */
public class DonorPreferencesDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	

	private Character smsAlert;
	
	@NotNull(message="not.blank")
	@NotBlank(message="not.blank")
	private Character hidePhonenumbers;
	
	@NotNull(message="not.blank")
	@NotBlank(message="not.blank")
	private String preferedContactTime;
	
	@NotNull(message="not.blank")
	@NotBlank(message="not.blank")
	private Character donorAvailability;

	public DonorPreferencesDTO() {
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

}