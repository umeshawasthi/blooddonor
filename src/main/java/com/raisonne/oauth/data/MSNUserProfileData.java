/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.oauth.data;

import java.util.Map;

/**
 * User Authenticated using Window Live services has different data format being returned from the
 * Live OAuth Server.
 * This data handler class will be responsible to capture primary level data being provided 
 * by Live OAuth server and handling it over to the Application level data handler.
 * @author Umesh Awasthi
 * @version 1.0
 * @since 28-11-2011
 *
 */
public class MSNUserProfileData {

	public MSNUserProfileData() {
		super();
		
	}
	
	private String name;
	private Map<String,String> emails;
	private String gender;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Map<String, String> getEmails() {
		return emails;
	}
	public void setEmails(Map<String, String> emails) {
		this.emails = emails;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	

}
