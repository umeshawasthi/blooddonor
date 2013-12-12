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
package com.raisonne.quartz.scheduler.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p> Class responsible for creating a list of execution interval
 * list cab be prepared by
 * <li> Populating from DB</li>
 * <li> Populating from property file </li>
 * <li> default list </li> 
 * </p>
 */

public class PrepareExecutionIntervalList {

	private static final Logger log=LoggerFactory.getLogger(PrepareExecutionIntervalList.class);
	private static List<String> executionIntervalList;
	
	
	public static List<String> getExecutionIntervalList(){
		createExecutionIntervalList();
		return Collections.unmodifiableList(executionIntervalList);
	}

	

	/**
	 * @param executionIntervalList the executionIntervalList to set
	 */
	public static void setExecutionIntervalList(final List<String> executionIntervalList) {
		log.info("Setting list of executionInterval");
		PrepareExecutionIntervalList.executionIntervalList = executionIntervalList;
	}
	
	/**
	 * <p>Method responsible for creating a list of execution interval
	 * list cab be prepared by
	 * <li> Populating from DB</li>
	 * <li> Populating from property file </li>
	 * <li> default list </li> 
	 * </p>
	 * @return {@link List} of execution interval
	 */
	private static void createExecutionIntervalList(){
		log.info("Preparing list of execution interval for triggers");
		List<String> executionInterval=new ArrayList<String>();
		executionInterval.add("Daily");
		executionInterval.add("Weekly");
		executionInterval.add("Monthly");
		executionInterval.add("Once");
		executionInterval.add("CronExpression");
		//executionInterval.add("Everday Except Holidays");
		setExecutionIntervalList(executionInterval);
		
	}
}
