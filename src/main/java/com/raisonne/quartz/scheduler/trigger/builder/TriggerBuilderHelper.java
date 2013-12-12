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
package com.raisonne.quartz.scheduler.trigger.builder;

import org.quartz.SchedulerException;

import com.raisonne.quartz.scheduler.dto.CronTriggerDTO;

/**
 * Interface defining various method to build a trigger 
 * expression.
 * @author Umesh Awasthi
 *
 */
public interface TriggerBuilderHelper {

	/**
	 * <p>Method responsible for creating a cron expression based on the 
	 * provided {@link CronTriggerDTO}.</p> 
	 * @param cronTriggerDTO DTO containing various fields to be used o crate expression.
	 * @return Cron expression based on the provided DTO.
	 * @throws SchedulerException 
	 */
	public boolean scheduleTrigger(CronTriggerDTO cronTriggerDTO) throws SchedulerException;
	
	
	/**
	 * <p>
	 * Method responsible for checking if a trigger already exists for a given 
	 * combination of trigger name and group.
	 * 
	 * </p>
	 * @param triggerName
	 * @param group
	 * @return
	 * @throws SchedulerException
	 */
	public boolean checkTriggerExists(String triggerName,String group) throws SchedulerException;
}
