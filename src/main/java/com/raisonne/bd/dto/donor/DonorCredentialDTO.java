/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.dto.donor;

import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.MinLength;

/**
 * This DTO is created to store donor credential specific form(UI) data.
 * 
 * @author Arun
 * 
 */
public class DonorCredentialDTO implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	@MatchPattern(pattern="^([a-z0-9]{1,}[\\.\\_\\-]?[a-z0-9]{1,})\\@([a-z0-9]{2,}\\.)([a-z]{2,2}|org|net|com|gov|edu|int|info|biz|museum)$",message="invalid.email")
	private String donorEmail;
	private String donorPassword;



	public DonorCredentialDTO(String donorEmail, String donorPassword) {
		super();
		this.donorEmail = donorEmail;
		this.donorPassword = donorPassword;
	}

	public DonorCredentialDTO() {
	}

	public String getDonorEmail() {
		return this.donorEmail;
	}

	public void setDonorEmail(String donorEmail) {
		this.donorEmail = donorEmail;
	}

	public String getDonorPassword() {
		return this.donorPassword;
	}

	public void setDonorPassword(String donorPassword) {
		this.donorPassword = donorPassword;
	}

}
