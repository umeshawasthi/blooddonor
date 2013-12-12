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
package com.raisonne.quartz.scheduler.constant;

/**
 * Constant being used throughout application. 
 * @author Umesh Awasthi
 *
 */
public class SchedulerConstants {
	
	public static final String CLASS_NAME = "className";
	public static final String METHOD_NAME= "methodName";
	public static final int DAILY_TRIGGER_FREQUENCY_RANGE=98;
	public static final int WEEKLY_TRIGGER_FREQUENCY_RANGE=9;
	public static final int MONTHLY_TRIGGER_FREQUENCY_RANGE=30;
	
	public static final String GROUP_NAME="group_name";
	public static final String GROUP_DESCRIPTION="group_description";
	public static final String CREATE_GROUP="INSERT INTO SCHEDULER_GROUP "+"("+GROUP_NAME+","+GROUP_DESCRIPTION+")" + "VALUES (?,?)";
	
	public static final String SELECT_ALL_GROUPS="SELECT * FROM SCHEDULER_GROUP";
	public static final String GROUP_COUNT="SELECT COUNT(*) FROM SCHEDULER_GROUP WHERE "+"("+GROUP_NAME+")=?";

}
