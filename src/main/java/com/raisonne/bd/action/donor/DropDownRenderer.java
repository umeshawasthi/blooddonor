/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.action.donor;

import java.util.List;

import org.apache.log4j.Logger;

import com.raisonne.bd.util.StateInfoUtils;

/**
 * Action class responsible for creating list of all generic data that will be used
 * as a drop down for the blood bank domian.few of them are
 * <li> Blood group type</li>
 * <li> State list </li>
 * <li> District list</li>
 * Action class will return the lists as a JSON data and jquery will be responsible
 * for parsing the JSON data and populating respected drop downs.
 * 
 * @author Umesh Awasthi
 * @version 1.0 20/11/2011
 * @since   1.0
 */

public class DropDownRenderer extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String state;
	private List<String> districts;
	
	Logger log=Logger.getLogger(DropDownRenderer.class);
	
	
	public String getDistrict() throws Exception{
		
		  log.info("Starting to populate lists");
		  districts=StateInfoUtils.getDistricts().get(state);
		  return SUCCESS;
	   	
	}
	
	public String getState() {
		return state;
	}
	
	

	public void setState(String state) {
		this.state = state;
	}

	public List<String> getDistricts() {
		return districts;
	}

	public void setDistricts(List<String> districts) {
		this.districts = districts;
	}
	}


