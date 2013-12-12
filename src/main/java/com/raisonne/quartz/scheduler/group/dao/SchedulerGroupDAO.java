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
package com.raisonne.quartz.scheduler.group.dao;

import java.sql.SQLException;
import java.util.List;

/**
 * 
 * @author Umesh Awasthi
 *
 */
public interface SchedulerGroupDAO {
	
	/**
	 * Method used to create a group with underlying database.
	 * @param groupName name of the group to be created
	 * @return flag
	 * @throws SQLException 
	 */
	public boolean createGroupByName(final String groupName,final String schedulerGroupDescription) throws SQLException;
	public List<GroupModel> getAllGroups() throws SQLException;
	public boolean checkIfGroupExists(final String groupName) throws SQLException;

}
