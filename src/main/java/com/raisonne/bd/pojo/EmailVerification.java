/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.pojo;




/**
 *Main DTO which is being used to carry the  donor mail id and the unique token from hibernate layer to database layer.
 *This DTO encapsulating mail verification data being used through out the application.
 *  
 * @author Himanshu Durgapal
 * @version 1.0  27/12/2011
 * @since 1.0
 * 
 *  
 */
public class EmailVerification implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String tokenId;	
	private String email;
	public EmailVerification() {
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public EmailVerification(String tokenId, String email) {
		super();
		this.tokenId = tokenId;
		this.email = email;
	}

	

	
}
