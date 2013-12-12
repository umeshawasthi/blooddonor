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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raisonne.quartz.scheduler.constant.SchedulerConstants;

/**
 *<p>Helper class which is capable of creating data
 * required to create trigger UI view.
 * This data will be used to provide trigger building options to 
 * the user</p>
 * @author Umesh Awasthi
 *
 */
public class TriggerUIHelper {
	
	public static final Logger log=LoggerFactory.getLogger(TriggerUIHelper.class);
	private static int [] dailyTriggerFrequencyRange;
	private static int [] weeklyTriggerFrequencyRange;
	private static int [] monthlyTriggerFrequencyRange;
	
	
	private static int[] getDailyTriggerFrequency(){
		log.info("Creating day frequency drop down");
		int[] dailyTriggerFrequencyRange=new int[SchedulerConstants.DAILY_TRIGGER_FREQUENCY_RANGE];
		for (int i=2; i<=SchedulerConstants.DAILY_TRIGGER_FREQUENCY_RANGE+1;i++){
			dailyTriggerFrequencyRange[i-2]=i;
		}
		return dailyTriggerFrequencyRange;
	}

	
	
	private static int[] getWeeklyTriggerFrequency(){
		log.info("Creating day frequency drop down");
		int[] weeklyTriggerFrequencyRange=new int[SchedulerConstants.WEEKLY_TRIGGER_FREQUENCY_RANGE];
		for (int i=2; i<=SchedulerConstants.WEEKLY_TRIGGER_FREQUENCY_RANGE+1;i++){
			weeklyTriggerFrequencyRange[i-2]=i;
		}
		return weeklyTriggerFrequencyRange;
	}
	
	private static int[] getMonthlyTriggerFrequency(){
		log.info("Creating day frequency drop down");
		int[] monthlyTriggerFrequencyRange=new int[SchedulerConstants.MONTHLY_TRIGGER_FREQUENCY_RANGE];
		for (int i=2; i<=SchedulerConstants.MONTHLY_TRIGGER_FREQUENCY_RANGE+1;i++){
			monthlyTriggerFrequencyRange[i-2]=i;
		}
		return monthlyTriggerFrequencyRange;
	}
	
	
	
	/**
	 * <p> Method responsible for creating a drop down list for 
	 * day frequency while creating drop down in trigger creation
	 * UI view </p>
	 * <p>
	 * Frequency range can be set by either property file or
	 * with the help of database.Based on the user preferences this method will
	 * create the range either reading a property file or getting
	 * range from database.
	 * </p>
	 * <p>
	 * In case of undefined user preference it will used the default
	 * value DAILY_TRIGGER_FREQUENCY_RANGE defined in 
	 * {@link SchedulerConstants}.
	 * </p>
	 * @return int array contain range of day frequency;
	 */
	public static int[] getDailyTriggerFrequencyRange() {
		if(null!= dailyTriggerFrequencyRange && dailyTriggerFrequencyRange.length>0){
			return dailyTriggerFrequencyRange;
		}
		else
		  return getDailyTriggerFrequency();    // this will be changed to set range at server startup.
	}

	/**
	 * @param dailyTriggerFrequencyRange the dailyTriggerFrequencyRange to set
	 */
	public static void setDailyTriggerFrequencyRange(
			int[] dailyTriggerFrequencyRange) {
		TriggerUIHelper.dailyTriggerFrequencyRange = dailyTriggerFrequencyRange;
	}


	/**
	 * <p> Method responsible for creating a drop down list for 
	 * weekly frequency while creating drop down in trigger creation
	 * UI view </p>
	 * <p>
	 * Frequency range can be set by either property file or
	 * with the help of database.Based on the user preferences this method will
	 * create the range either reading a property file or getting
	 * range from database.
	 * </p>
	 * <p>
	 * In case of undefined user preference it will used the default
	 * value WEEKLY_TRIGGER_FREQUENCY_RANGE defined in 
	 * {@link SchedulerConstants}.
	 * </p>
	 * @return int array contain range of week frequency;
	 */
	public static int[] getWeeklyTriggerFrequencyRange() {
		if(null!= weeklyTriggerFrequencyRange && weeklyTriggerFrequencyRange.length>0){
			return weeklyTriggerFrequencyRange;
		}
		else
		   return getWeeklyTriggerFrequency();
	}


	/**
	 * @param weeklyTriggerFrequencyRange the weeklyTriggerFrequencyRange to set
	 */
	public static void setWeeklyTriggerFrequencyRange(
			int[] weeklyTriggerFrequencyRange) {
		TriggerUIHelper.weeklyTriggerFrequencyRange = weeklyTriggerFrequencyRange;
	}


	

	/**
	 * <p> Method responsible for creating a drop down list for 
	 * monthly frequency while creating drop down in trigger creation
	 * UI view </p>
	 * <p>
	 * Frequency range can be set by either property file or
	 * with the help of database.Based on the user preferences this method will
	 * create the range either reading a property file or getting
	 * range from database.
	 * </p>
	 * <p>
	 * In case of undefined user preference it will used the default
	 * value MONTHLY_TRIGGER_FREQUENCY_RANGE defined in 
	 * {@link SchedulerConstants}.
	 * </p>
	 * @return int array contain range of monthly frequency;
	 */
	public static int[] getMonthlyTriggerFrequencyRange() {
		
		if(null!= monthlyTriggerFrequencyRange && monthlyTriggerFrequencyRange.length>0){
			return monthlyTriggerFrequencyRange;
		}
		else
		   return getMonthlyTriggerFrequency();
	}



	/**
	 * @param monthlyTriggerFrequencyRange the monthlyTriggerFrequencyRange to set
	 */
	public static void setMonthlyTriggerFrequencyRange(
			int[] monthlyTriggerFrequencyRange) {
		TriggerUIHelper.monthlyTriggerFrequencyRange = monthlyTriggerFrequencyRange;
	}
	
	
}
