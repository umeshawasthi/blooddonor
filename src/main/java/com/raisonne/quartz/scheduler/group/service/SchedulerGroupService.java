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
package com.raisonne.quartz.scheduler.group.service;

import java.sql.SQLException;
import java.util.List;

import com.raisonne.quartz.scheduler.group.dao.GroupModel;

/**
 * Service being used to create group using underlying quartz data source.
 * these groups will be used to create scheduler jobs and triggers.
 * given job/trigger is treated as unique in combination with group id/name.
 * @author Umesh Awasthi
 *
 */
public interface SchedulerGroupService {
	
	/**
	 * Method responsible for creating a group with given group name
	 * @param groupName name of the group being created
	 * @return flag indicating if group has been created or not.
	 * @throws SQLException 
	 */
	public boolean createGroup(final String groupName,final String schedulerGroupDescription) throws SQLException;
	public boolean checkIfGroupExists(final String groupName);
	public List<GroupModel> getAllGroups();

}
