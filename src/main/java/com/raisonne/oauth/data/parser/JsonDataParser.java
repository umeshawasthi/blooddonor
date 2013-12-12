/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.oauth.data.parser;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.raisonne.oauth.data.OAuthUserProfileData;

/**
 * JSON data parser class this class will be responsible for parsing 
 * incoming JSON data from the OAuthentication service provider in to 
 * internal data object.
 * This object will be used to persist some of the user data in to the system.
 * 
 * @author Umesh Awasthi
 * @version 1.0
 * @since 25-11-2011
 *
 */
public class JsonDataParser {
	
	Logger log=Logger.getLogger(JsonDataParser.class);
	
	
	/**
	 * 
	 * @param json json data which need to parse
	 * @return {@link OAuthUserProfileData} parsed json data in java object form
	 */
	public OAuthUserProfileData parseJson(String json){
		log.info("Starting json parsing");
		Gson gson = new Gson();
		OAuthUserProfileData oAuthUserProfileData=gson.fromJson(json, OAuthUserProfileData.class);
		return oAuthUserProfileData;
		
	}
	

	/**
	 * 
	 * @param json json data which need to parse
	 * @return parsed JSON data in specified object
	 */
	public <T> T parseJson(String json,Class<T> type){
		log.info("Starting json parsing");
		Gson gson = new Gson();
		return gson.fromJson(json, type);
		
		
	}

}
