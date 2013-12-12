/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.oauth.action;

import org.apache.log4j.Logger;

import com.raisonne.bd.action.donor.BaseAction;

/**
 * An action class which will be responsible to provide login options
 * to user,this action class can be used at the time of login as well
 * when user want to register themself as number of login option will be same for all
 * appllication
 * @author Umesh Awasthi
 * @version 1.0
 * @since 17-12-2011
 *
 */
public class LoginSelector extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log=Logger.getLogger(LoginSelector.class);
	
	public String execute() throws Exception{
		log.info("Setting working tempelate as loginoptions");
		setTitle("Save A Life Today|Select Login Option");
		setWorkingTemplate("/WEB-INF/templates/donor/loginoptions.jsp");
		
		return SUCCESS;
	}
	
	
	private String workingTemplate=null;
	public String getWorkingTemplate() {
		return workingTemplate;
	}
	public void setWorkingTemplate(String workingTemplate) {
		this.workingTemplate = workingTemplate;
	}

}
