/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A general purpose utility class for Blood bank application.This utility class will be responsible for
 * creating list of available blood group type, number of states/district and any other required
 * data.
 * This utiltiy class will either loads these values on system startup or load values when the class loads.
 * Do not create a new instance of this class to access its values,since container will be responsible for creating a new instance and
 * initilizing it so creating new instance will not work as expected.
 * 
 * @author Umesh awasthi
 * @since 1.0
 * @version 1.0 09/11/2011
 *
 */
public final class BloodGroupUtils {
	
	
	
	/**
	 * Static variable to encapsulate all available blood groups.
	 * This variable should be accessed in static way when some one want to get access to 
	 * all available blood groups.
	 */
	public static List<String> availableBloodGroups=new ArrayList<String>();
	private static Map<String, String> OAUTH_SERVICE_PARAMETERS=new HashMap<String, String>();
	private static Map<String,String> projectProperties=new HashMap<String, String>();
	
	/**
	 * Utility Method responsible for returning a list of all available 
	 * blood group type.This static method should be called when the class loads.
	 * 
	 * @return {@link List} a list of available blood groups.
	 */
	
	public static List<String> getAvailableBloodGroups() {
		return Collections.unmodifiableList(availableBloodGroups);
	}
	
	
	
	public static void addProjectProperties(String key,String value) {
		projectProperties.put(key, value);
		
	}


	public static String getProjectProperties(String key){
		return projectProperties.get(key);
	}

	/**
	 * 
	 * @param key  map key
	 * @return value with respect to the key provided
	 */
	public static String getParameter(String key){
		return OAUTH_SERVICE_PARAMETERS.get(key);
	}
	
	public static void addOauthdata(String key,String value) {
		OAUTH_SERVICE_PARAMETERS.put(key, value);
	}

}
