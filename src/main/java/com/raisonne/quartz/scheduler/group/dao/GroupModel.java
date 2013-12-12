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

import java.io.Serializable;

/**
 * @author Umesh Awasthi
 *
 */
public class GroupModel implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String groupName;
	private int groupId;
	private String groupDescription;
	
	
	
	public GroupModel() {
		super();
	
	}
	public GroupModel(String groupName, int groupId,String groupDescription ) {
		super();
		this.groupName = groupName;
		this.groupId = groupId;
		this.groupDescription=groupDescription;
	}
	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}
	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return the groupDescription
	 */
	public String getGroupDescription() {
		return groupDescription;
	}
	/**
	 * @param groupDescription the groupDescription to set
	 */
	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}
	
	

}
