/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.oauth.data;

/**
 * Data class which will carry user profile data being provided by 
 * OAuth service provider.
 * This will only carry data being handed over by Oauth service provider after
 * a mutual hand shake mechanism.
 * This class is intended to carry only data related to public profile of user.
 * 
 * @author Umesh Awasthi
 * @version 1.0
 * @since 25-11-2011
 *
 */
public class OAuthUserProfileData {
	
	private String name;
	private String email;
	private String gender;
	private String verified;
	private String id;
	private boolean newUser=false;
	
	public OAuthUserProfileData() {
		super();
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getVerified() {
		return verified;
	}

	public void setVerified(String verified) {
		this.verified = verified;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isNewUser() {
		return newUser;
	}

	public void setNewUser(boolean newUser) {
		this.newUser = newUser;
	}
	
	
	

}
