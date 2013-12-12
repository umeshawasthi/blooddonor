/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.listener;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;

import com.raisonne.bd.dto.bloodrequest.BloodRequestScrollerDTO;
import com.raisonne.bd.util.BloodGroupUtils;
import com.raisonne.bd.util.GenericConnectionManager;
import com.raisonne.bd.util.PropertyLoader;
import com.raisonne.bd.util.StateInfoUtils;


/**
 * A servlet context listner responsible for preparing all necessary data from
 * the resources and make available through out application. This Context
 * listner class will fetch list of all available blood groups as well list of
 * states and District and will set them to {@link BloodGroupUtils} class so as
 * to make them available for entire apllication.
 * 
 * @author Umesh Awasthi
 * @version 1.0
 * @since 1.0 10/11/2011
 * 
 */
public class BDContextListener implements ServletContextListener {

	Logger log = Logger.getLogger(BDContextListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {
		log.info("Context is shutting down.Releasing all resources");

	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		log.info("Initializing servlet context.Preparing all mendatory data for BD");
		Configuration config=null;
		try
        {
            config = new PropertiesConfiguration("project.properties");
        }
        catch ( ConfigurationException e )
        {
           
            log.error( "error while reading project propery filee", e);
        }
		if(config !=null){
		    servletContextEvent.getServletContext().setAttribute("webRootContext", config.getProperty( "webroot.static.context" ));
		    servletContextEvent.getServletContext().setAttribute("rootContext", config.getProperty( "webroot.context" ));
		    
		}else{

		    servletContextEvent.getServletContext().setAttribute("webRootContext", "/blood_donors/static");
		    servletContextEvent.getServletContext().setAttribute("rootContext", "/blood_donors");
		}
		//Save a life today| Voluntary Blood Donors Database, India blood donors
		//ActionContext.getContext().getApplication().put("webRootContext", "/blood_donors/static");
		// fetching blood group list
		BloodGroupUtils.availableBloodGroups = readDataFiles("blood_grouptype.txt");

		// fetching states list
		initStates();

		// fetching district list
		initDistricts();
		
		// fetching oauth specific data
		initOAuthData();
		
		readProjectpropertiesFile();
		initBloodRequestCache(servletContextEvent);

	}
	
	
	@SuppressWarnings("serial")
	private void initBloodRequestCache(ServletContextEvent servletContextEvent){
		log.info("Initializing blood request cache");
		
		// Create cache
		final int MAX_ENTRIES = 50;
		Map<Integer,BloodRequestScrollerDTO> cache = new LinkedHashMap<Integer, BloodRequestScrollerDTO>(MAX_ENTRIES+1, .75F, true) {
		    // This method is called just after a new entry has been added
		    @SuppressWarnings("rawtypes")
			public boolean removeEldestEntry(Map.Entry eldest) {
		        return size() > MAX_ENTRIES;
		    }
		};
		
		cache= (Map<Integer, BloodRequestScrollerDTO>)Collections.synchronizedMap(loadInitialBloodRequests());
        log.info("Putting cache in application Context.Total object in cache.. "+cache.size());
		servletContextEvent.getServletContext().setAttribute("bloodRequestCache", cache);
	}
	
	private Map<Integer,BloodRequestScrollerDTO> loadInitialBloodRequests(){
		log.info("Starting to load initial blood requests from DB");
		Map<Integer,BloodRequestScrollerDTO> cacheObjMap=new HashMap<Integer, BloodRequestScrollerDTO>();
		BloodRequestScrollerDTO dto=null;
		ResultSet rs = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
		try {
			conn=GenericConnectionManager.getInstance().getConnection();
			String query="select uuid,mobile_number,blood_group,location,state from blood_request where verification_flag=?";
			pstmt=conn.prepareStatement(query);
			pstmt.setBoolean(1, true);
			rs=pstmt.executeQuery();
			while(rs.next()){
				dto=new BloodRequestScrollerDTO();
				dto.setUuid(rs.getInt("uuid"));
				dto.setBloodGroup(rs.getString("blood_group"));
				dto.setContactNumber(rs.getString("mobile_number"));
				dto.setLocation(rs.getString("location"));
				dto.setState(StateInfoUtils.getStates().get(rs.getString("state")));
				cacheObjMap.put(dto.getUuid(), dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return Collections.emptyMap();
		}
	   finally {
	      try {
	        rs.close();
	        pstmt.close();
	        conn.close();
	        GenericConnectionManager.getInstance().shutdown();
	      } catch (SQLException e) {
	        e.printStackTrace();
	      }
		
	}
	
		return cacheObjMap;
	}
	/**
	 * This implementation is responsible for reading given property files
	 * available in class path and returning them as a list.
	 * 
	 * @param fileName
	 * @return {@link List}
	 */
	private List<String> readDataFiles(String fileName) {

		ArrayList<String> list = new ArrayList<String>();
		BufferedReader input = null;
		try {
			log.info("Trying to read " + fileName + " from the classpath");
			input = new BufferedReader(new InputStreamReader(this.getClass()
					.getClassLoader().getResourceAsStream(fileName)));
			String line = null;
			while ((line = input.readLine()) != null) {
				list.add(line);
			}

		} catch (IOException ioexception) {
			log.error("Error in reading input file", ioexception);
		}

		finally {
			if (null != input)
				try {
					input.close();
				} catch (IOException e) {
					log.error("Error in closing reader stream", e);
					e.printStackTrace();
				}
		}

		return Collections.unmodifiableList(list);
	}

	/**
	 * This method is written to load state as static members in StateInfoUtils
	 * class
	 * 
	 */
	private void initStates() {
		log.info("Start loading states");
		Properties properties = PropertyLoader
				.loadProperties("states-mapping.properties");
		@SuppressWarnings("unchecked")
		Enumeration<String> stateEnum = (Enumeration<String>) properties
				.propertyNames();

		while (stateEnum.hasMoreElements()) {
			String state = (String) stateEnum.nextElement();
			String stateName = properties.getProperty(state);
			StateInfoUtils.addState(state, stateName);
		}
		log.info("Finished loading states");
	}

	
	
	/**
	 * This method is written to load state  as static members in StateInfoUtils class
	 * 
	 */
	private void readProjectpropertiesFile() {
		log.info("Start reading project properties file");
		Properties properties = PropertyLoader
				.loadProperties("project.properties");		
		@SuppressWarnings("unchecked")
		Enumeration<String> stateEnum = (Enumeration<String>) properties
				.propertyNames();
		

		while (stateEnum.hasMoreElements()) {
			String key = (String) stateEnum.nextElement();
			String value=properties.getProperty(key);	
			BloodGroupUtils.addProjectProperties(key,value);
		}
		log.info("Finished loading project.properties file");
	}
	
	
	/**
	 * This method is written to load state  as static members in StateInfoUtils class
	 * 
	 */
	private void initOAuthData() {
		log.info("Start loading Oauth data");
		Properties properties = PropertyLoader
				.loadProperties("oauth-data.properties");		
		@SuppressWarnings("unchecked")
		Enumeration<String> stateEnum = (Enumeration<String>) properties
				.propertyNames();
		

		while (stateEnum.hasMoreElements()) {
			String key = (String) stateEnum.nextElement();
			String value=properties.getProperty(key);	
			BloodGroupUtils.addOauthdata(key,value);
		}
		log.info("Finished loading states");
	}
	

	/**
	 * This method is written to load districts as static members in
	 * StateInfoUtils class
	 * 
	 */
	private void initDistricts() {
		log.info("Start loading districts");
		List<String> districts = null;
		Properties properties = PropertyLoader
				.loadProperties("states-districts.properties");
		@SuppressWarnings("unchecked")
		Enumeration<String> districtEnum = (Enumeration<String>) properties
				.propertyNames();

		while (districtEnum.hasMoreElements()) {
			String state = (String) districtEnum.nextElement();
			String[] districtsArray = properties.getProperty(state).split(",");
			districts = new ArrayList<String>();

			for (String district : districtsArray) {
				districts.add(district);
			}
			Collections.sort(districts);
			StateInfoUtils.addDistricts(state, districts);

		}
		log.info("Finished loading districts");
	}

}
