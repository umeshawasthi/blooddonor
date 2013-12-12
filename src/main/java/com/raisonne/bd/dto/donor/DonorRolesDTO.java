package com.raisonne.bd.dto.donor;

import java.io.Serializable;
import java.util.Date;

public class DonorRolesDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String donorRoles;
	private boolean userEnabled;
	private boolean accountExpired;
	private boolean credentialExpired;
	private boolean accountLocked;
	
	
	
	public DonorRolesDTO(String donorRoles, boolean userEnabled,
			boolean accountExpired, boolean credentialExpired,
			boolean accountLocked) {
		super();
		this.donorRoles = donorRoles;
		this.userEnabled = userEnabled;
		this.accountExpired = accountExpired;
		this.credentialExpired = credentialExpired;
		this.accountLocked = accountLocked;
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

	public DonorRolesDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getDonorRoles() {
		return donorRoles;
	}

	public void setDonorRoles(String donorRoles) {
		this.donorRoles = donorRoles;
	}
	
	
	
	
}
