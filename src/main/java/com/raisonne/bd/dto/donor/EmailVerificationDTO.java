package com.raisonne.bd.dto.donor;

import java.io.Serializable;

public class EmailVerificationDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tokenId;	
	private String email;
	
	public EmailVerificationDTO()
	{
		
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public EmailVerificationDTO(String tokenId, String email) {
		super();
		this.tokenId = tokenId;
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
