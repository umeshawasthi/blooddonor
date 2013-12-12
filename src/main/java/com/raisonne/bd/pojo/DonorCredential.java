/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.pojo;



import java.util.Date;

/**
 * Used to store donor credential in a seperate table.
 *  
 * @author Umesh Awasthi
 * @version 1.0  09/11/2011
 * @since 1.0
 * @see DonorProfile
 *  
 */
public class DonorCredential implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uuid;
	private String donorEmail;
	private String donorPassword;
	private String donorId;
	private Date creationDate=new Date();;
	private Date lastUpdated=new Date();
	

	public DonorCredential() {
	}

	public DonorCredential(String uuid, String donorEmail, String donorId,
			Date creationDate, Date lastUpdated) {
		this.uuid = uuid;
		this.donorEmail = donorEmail;
		this.donorId = donorId;
		this.creationDate = creationDate;
		this.lastUpdated = lastUpdated;
	}

	public DonorCredential(String uuid, String donorEmail,
			String donorPassword, String donorId, Date creationDate,
			Date lastUpdated) {
		this.uuid = uuid;
		this.donorEmail = donorEmail;
		this.donorPassword = donorPassword;
		this.donorId = donorId;
		this.creationDate = creationDate;
		this.lastUpdated = lastUpdated;
		
	}

	public String getUuid() {
		return this.uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
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

	public String getDonorId() {
		return this.donorId;
	}

	public void setDonorId(String donorId) {
		this.donorId = donorId;
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

	

}
