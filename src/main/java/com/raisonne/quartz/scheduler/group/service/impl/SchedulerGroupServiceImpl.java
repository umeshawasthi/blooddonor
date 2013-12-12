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
package com.raisonne.quartz.scheduler.group.service.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raisonne.quartz.scheduler.group.dao.GroupModel;
import com.raisonne.quartz.scheduler.group.dao.SchedulerGroupDAO;
import com.raisonne.quartz.scheduler.group.dao.SchedulerGroupDAOImpl;
import com.raisonne.quartz.scheduler.group.service.SchedulerGroupService;

/**
 * Implementation for {@link SchedulerGroupService}, this class is responsible for 
 * creating a group to be used for creating scheduler's triggers and jobs.
 * @author Umesh Awasthi
 *
 */
public class SchedulerGroupServiceImpl implements SchedulerGroupService{

	private final Logger log=LoggerFactory.getLogger(getClass());
	/**
	 * <p>Implementation responsible for creating a group in the underlying
	 * database.</p>
	 * <p>
	 * This method will use the underlying Quartz Data source to interact with the
	 * database and create group with the specified database.
	 * </p>
	 * @throws SQLException 
	 */
	@Override
	public boolean createGroup(final String groupName,final String schedulerGroupDescription) throws SQLException {
		if(null==groupName){
			log.error("group name is null, Please provide group name");
			return false;
		}
		
		SchedulerGroupDAO schedulerGroupDAO=new SchedulerGroupDAOImpl();
		return schedulerGroupDAO.createGroupByName(groupName,schedulerGroupDescription);
		
	}
	
	/**
	 * <p>Method used to check if the given Group with group name already
	 * exists in the underlying database</p>
	 * <p>
	 * It will return a flag indicating id group already exists or not
	 * </p>
	 * @param groupName groupName which needs to be checked with underlying DB.
	 * @return boolean flag indicating if it exists or not
	 * @throws SQLException
	 */
	@Override
	public boolean checkIfGroupExists(final String groupName){
		if(null==groupName){
			log.error("group name is null, Please provide group name");
			return false;
		}
		
		SchedulerGroupDAO schedulerGroupDAO=new SchedulerGroupDAOImpl();
		try {
			return schedulerGroupDAO.checkIfGroupExists(groupName);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	/**
	 *<p>
	 *Fetch list of all groups stored in the underlying database.
	 *</p>
	 *@return List of {@link GroupModel}.
	 */
	public List<GroupModel> getAllGroups() {
		List<GroupModel> list=null;
	    SchedulerGroupDAO schedulerGroupDAO=new SchedulerGroupDAOImpl();
		try {
			  list=schedulerGroupDAO.getAllGroups();
		} catch (SQLException e) {
			log.error("Error while retrieving list of Groups");
			e.printStackTrace();
		}
		log.info("Returning list of all groups");
		return Collections.unmodifiableList(list);
	}
	
	

}
