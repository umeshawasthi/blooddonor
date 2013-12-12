package com.raisonne.bd.pojo;

import java.io.Serializable;
import java.util.Date;

public class DonorRoles implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String donorId;
	private String donorRoles;
	private Date creationDate=new Date();
	private Date lastUpdated=new Date();
	private boolean userEnabled;
	private boolean accountExpired;
	private boolean credentialExpired;
	private boolean accountLocked;
	private DonorProfile donorProfile;
	

	public DonorRoles( String donorRoles, String donorId,
			Date creationDate, Date lastUpdated, boolean userEnabled,
			boolean accountExpired, boolean credentialExpired,
			boolean accountLocked, DonorProfile donorProfile) {
		super();
		
		this.donorRoles = donorRoles;
		this.donorId = donorId;
		this.creationDate = creationDate;
		this.lastUpdated = lastUpdated;
		this.userEnabled = userEnabled;
		this.accountExpired = accountExpired;
		this.credentialExpired = credentialExpired;
		this.accountLocked = accountLocked;
		this.donorProfile = donorProfile;
	}
	public DonorRoles() {
		// TODO Auto-generated constructor stub
	}
	
	public String getDonorRoles() {
		return donorRoles;
	}
	public void setDonorRoles(String donorRoles) {
		this.donorRoles = donorRoles;
	}
	public String getDonorId() {
		return donorId;
	}
	public void setDonorId(String donorId) {
		this.donorId = donorId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	public DonorProfile getDonorProfile() {
		return donorProfile;
	}
	public void setDonorProfile(DonorProfile donorProfile) {
		this.donorProfile = donorProfile;
	}
	public boolean isUserEnabled() {
		return userEnabled;
	}
	public void setUserEnabled(boolean userEnabled) {
		this.userEnabled = userEnabled;
	}
	public boolean isAccountExpired() {
		return accountExpired;
	}
	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}
	public boolean isCredentialExpired() {
		return credentialExpired;
	}
	public void setCredentialExpired(boolean credentialExpired) {
		this.credentialExpired = credentialExpired;
	}
	public boolean isAccountLocked() {
		return accountLocked;
	}
	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}
		
}
