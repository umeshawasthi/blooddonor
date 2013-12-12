/*
 * Copyright 2010-2011 Raisonne Techonologies.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 *
 */
package com.raisonne.quartz.scheduler.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.Action;
import com.raisonne.bd.action.donor.BaseAction;
import com.raisonne.quartz.scheduler.group.dao.GroupModel;
import com.raisonne.quartz.scheduler.group.service.SchedulerGroupService;
import com.raisonne.quartz.scheduler.group.service.impl.SchedulerGroupServiceImpl;

/**
 * <p>Each scheduler Job and Trigger belongs to a certain
 * group.Job/Trigger Id along with Group Id work as a unique
 * key and only one combination can exists for this.<p>
 * <p>
 * This Action class is responsible for creating independent
 * Group so that they can be utilize when creating Jobs or
 * triggers.Group name is unique.
 * </p>
 * @author Umesh Awasthi
 *
 */
public class SchedulerGroupAction extends BaseAction{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	private String schedulerGroupName;
	private String schedulerGroupDescription;
	private final Logger log=LoggerFactory.getLogger(getClass());
	/**
	 * <p>Action method responsible for creating group(s)
	 * to be used while creating quartz jobs or triggers.</p>
	 * <p>
	 * 
	 * </p>
	 * @return result string indicating the view to rendered.
	 * @throws Exception
	 */
	public String createGroup() throws Exception{
	    log.info("About to create group with name {} ",schedulerGroupName);
		SchedulerGroupService schedulerGroupService=new SchedulerGroupServiceImpl();
		if(schedulerGroupService.checkIfGroupExists(schedulerGroupName)){
			log.info("Group with name {} already exists ",schedulerGroupName);
			String[] args={schedulerGroupName};
			addActionMessage(getText("group.exists", args));
			setSchedulerGroupName(schedulerGroupName);
			prepareGroupUI();
			setHideLeftPannel(true);
			setHideRightPannel(true);
			setWorkingTemplate("/WEB-INF/templates/scheduler/createGroup.jsp");
			return Action.INPUT;
		}
		schedulerGroupService.createGroup(schedulerGroupName,schedulerGroupDescription);
		addActionMessage(getText("group.created"));
		prepareGroupUI();
		setHideLeftPannel(true);
		setHideRightPannel(true);
		setWorkingTemplate("/WEB-INF/templates/scheduler/createGroup.jsp");
		return Action.SUCCESS;	
	}

	/**
	 * @return the schedulerGroupName
	 */
	public String getSchedulerGroupName() {
		return schedulerGroupName;
	}

	/**
	 * @param schedulerGroupName the schedulerGroupName to set
	 */
	public void setSchedulerGroupName(String schedulerGroupName) {
		this.schedulerGroupName = schedulerGroupName;
	}
	
	
    /**
	 * @return the schedulerGroupDescription
	 */
	public String getSchedulerGroupDescription() {
		return schedulerGroupDescription;
	}

	/**
	 * @param schedulerGroupDescription the schedulerGroupDescription to set
	 */
	public void setSchedulerGroupDescription(String schedulerGroupDescription) {
		this.schedulerGroupDescription = schedulerGroupDescription;
	}

	/**
     * Action class method responsible 
     * @return control string indicating view to rendered.
     * @throws Exception
     */
	public String getAllGroups() throws Exception{
		log.info("About to fetch all groups..");
		SchedulerGroupService schedulerGroupService=new SchedulerGroupServiceImpl();
		groupModelsList=schedulerGroupService.getAllGroups();
		if(groupModelsList.isEmpty()){
			log.info("No group found with underlying database");
			return ERROR;
		}
		else{
			  log.info("Found total {} groups. ",groupModelsList.size());
		      return SUCCESS;
		}
	}
	
	List<GroupModel> groupModelsList;
	/**
	 * @return the groupModelsList
	 */
	public List<GroupModel> getGroupModelsList() {
		return groupModelsList;
	}

	/**
	 * @param groupModelsList the groupModelsList to set
	 */
	public void setGroupModelsList(List<GroupModel> groupModelsList) {
		this.groupModelsList = groupModelsList;
	}
	
	/**
	 * Method responsible for creating the view for scheduler Group
	 * This includes fetching all the groups stored in underlying
	 * DB so as Groups can be displayed in the JSP.
	 * @return control String to render the view
	 * @throws Exception
	 */
	public String prepareGroupUI() throws Exception{
		log.info("About to start preparing the view..");
		if(groupModelsList==null){
			getAllGroups();
		}
		else if(groupModelsList.isEmpty()){
			getAllGroups();
		}
		setHideLeftPannel(true);
		setHideRightPannel(true);
		setWorkingTemplate("/WEB-INF/templates/scheduler/createGroup.jsp");
		return SUCCESS;
	}
	
	
	
}

