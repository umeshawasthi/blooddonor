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

/**
 * DTO responsible for encapsulating the data required to 
 * build a view to provide user different options for creating
 * triggers.
 * @author Umesh Awasthi
 *
 */
public class CronTriggerUIDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isDaily=false;
	private int[] dailyTriggerFrequencyRange;
	private boolean isWeekly;
	private int[] weeklyTriggerFrequencyRange;
	private String[] weekdays;
	private boolean isMonthly;
	private int[] monthlyTriggerFrequencyRange;
	
	/**
	 * @return the isDaily
	 */
	public boolean isDaily() {
		return isDaily;
	}
	/**
	 * @param isDaily the isDaily to set
	 */
	public void setDaily(boolean isDaily) {
		this.isDaily = isDaily;
	}
	/**
	 * @return the dailyTriggerFrequencyRange
	 */
	public int[] getDailyTriggerFrequencyRange() {
		return dailyTriggerFrequencyRange;
	}
	/**
	 * @param dailyTriggerFrequencyRange the dailyTriggerFrequencyRange to set
	 */
	public void setDailyTriggerFrequencyRange(int[] dailyTriggerFrequencyRange) {
		this.dailyTriggerFrequencyRange = dailyTriggerFrequencyRange;
	}
	/**
	 * @return the isWeekly
	 */
	public boolean isWeekly() {
		return isWeekly;
	}
	/**
	 * @param isWeekly the isWeekly to set
	 */
	public void setWeekly(boolean isWeekly) {
		this.isWeekly = isWeekly;
	}
	/**
	 * @return the weeklyTriggerFrequencyRange
	 */
	public int[] getWeeklyTriggerFrequencyRange() {
		return weeklyTriggerFrequencyRange;
	}
	/**
	 * @param weeklyTriggerFrequencyRange the weeklyTriggerFrequencyRange to set
	 */
	public void setWeeklyTriggerFrequencyRange(int[] weeklyTriggerFrequencyRange) {
		this.weeklyTriggerFrequencyRange = weeklyTriggerFrequencyRange;
	}
	/**
	 * @return the weekdays
	 */
	public String[] getWeekdays() {
		return weekdays;
	}
	/**
	 * @param weekdays the weekdays to set
	 */
	public void setWeekdays(String[] weekdays) {
		this.weekdays = weekdays;
	}
		
	
	/**
	 * @return the isMonthly
	 */
	public boolean isMonthly() {
		return isMonthly;
	}
	/**
	 * @param isMonthly the isMonthly to set
	 */
	public void setMonthly(boolean isMonthly) {
		this.isMonthly = isMonthly;
	}
	/**
	 * @return the monthlyTriggerFrequencyRange
	 */
	public int[] getMonthlyTriggerFrequencyRange() {
		return monthlyTriggerFrequencyRange;
	}
	/**
	 * @param monthlyTriggerFrequencyRange the monthlyTriggerFrequencyRange to set
	 */
	public void setMonthlyTriggerFrequencyRange(int[] monthlyTriggerFrequencyRange) {
		this.monthlyTriggerFrequencyRange = monthlyTriggerFrequencyRange;
	}
	
	

}
