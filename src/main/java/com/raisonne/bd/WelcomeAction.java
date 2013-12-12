/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd;

import org.apache.log4j.Logger;

import com.raisonne.bd.action.donor.BaseAction;

/**
 * Entry level Action which is being called in place of welcome file
 * using web.xml file.
 * Purpose of this Action class is to init any required menu item or any other 
 * navigation pannel which is DB driven or need any action level calculation
 * @author Umesh Awasthi
 * @version 1.0
 * @since 17-12-2011
 *
 */
public class WelcomeAction extends BaseAction{

	/**
	 * Entry level Action which is being called in place of welcome file
	 * using web.xml file.
	 * Purpose of this Action class is to init any required menu item or any other 
	 * navigation pannel which is DB driven or need any action level calculation
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log=Logger.getLogger(WelcomeAction.class);
	
	
	public String execute() throws Exception{
		log.info("Setting working tempelate");
		//bloodReqDtos=scrollerUtil.getScrollerData();
		setWorkingTemplate("/WEB-INF/templates/layout/slider.jsp");
		//ActionContext.getContext().getApplication().put("webRootContext", "/blood_donors/static");
		return SUCCESS;
	}
	public String aboutUs() throws Exception
	{
		setWorkingTemplate("/WEB-INF/templates/layout/aboutUs.jsp");
		return SUCCESS;
	}
	public String whyDonateBlood() throws Exception
	{
		setWorkingTemplate("/WEB-INF/templates/static/whyDonate.jsp");
		return SUCCESS;
	}
	
	public String team() throws Exception
	{
		setWorkingTemplate("/WEB-INF/templates/static/team.jsp");
		return SUCCESS;
	}
	
	public String inviteFriends() throws Exception{
		setWorkingTemplate("/WEB-INF/templates/static/inviteFriends.jsp");
		return SUCCESS;
	}
	
	private String workingTemplate=null;
	public String getWorkingTemplate() {
		return workingTemplate;
	}
	public void setWorkingTemplate(String workingTemplate) {
		this.workingTemplate = workingTemplate;
	}
	/*public String getWebRoot() {
		return webRoot;
	}
	public void setWebRoot(String webRoot) {
		this.webRoot = webRoot;
	}*/
	
	
	
	
	
	
	

}
