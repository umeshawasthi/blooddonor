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
package com.raisonne.quartz.scheduler.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Data transfer Object responsible for carrying Cron 
 * trigger data from UI layer to the underlying API to create
 * the cron trigger and vice-versa.</p>
 * <p>
 * <p>All essential data needed to create trigger should be passed
 * using the DTO, a direct interaction can break the API.</p>
 * @author Umesh Awasthi
 *
 */
public class CronTriggerDTO  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public CronTriggerDTO() {
		
	}
	private Date startDate;
	private String startTime;
	private String triggerGroup;
	private String tiggerId;
	private String executionInterval;
	private int daySkipFrequency;
	private int weeklyInterval;
	private String cron_expression;
	private String triggerJobName;
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}
	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	/**
	 * @return the triggerGroup
	 */
	public String getTriggerGroup() {
		return triggerGroup;
	}
	/**
	 * @param triggerGroup the triggerGroup to set
	 */
	public void setTriggerGroup(String triggerGroup) {
		this.triggerGroup = triggerGroup;
	}
	/**
	 * @return the tiggerId
	 */
	public String getTiggerId() {
		return tiggerId;
	}
	/**
	 * @param tiggerId the tiggerId to set
	 */
	public void setTiggerId(String tiggerId) {
		this.tiggerId = tiggerId;
	}
	/**
	 * @return the executionInterval
	 */
	public String getExecutionInterval() {
		return executionInterval;
	}
	/**
	 * @param executionInterval the executionInterval to set
	 */
	public void setExecutionInterval(String executionInterval) {
		this.executionInterval = executionInterval;
	}
	public int getDaySkipFrequency() {
		return daySkipFrequency;
	}
	public void setDaySkipFrequency(int daySkipFrequency) {
		this.daySkipFrequency = daySkipFrequency;
	}
	public int getWeeklyInterval() {
		return weeklyInterval;
	}
	public void setWeeklyInterval(int weeklyInterval) {
		this.weeklyInterval = weeklyInterval;
	}
	/**
	 * @return the cron_expression
	 */
	public String getCron_expression() {
		return cron_expression;
	}
	/**
	 * @param cron_expression the cron_expression to set
	 */
	public void setCron_expression(String cron_expression) {
		this.cron_expression = cron_expression;
	}
	public String getTriggerJobName() {
		return triggerJobName;
	}
	public void setTriggerJobName(String triggerJobName) {
		this.triggerJobName = triggerJobName;
	}
	
	
	
	

}
