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

import static org.quartz.CalendarIntervalScheduleBuilder.calendarIntervalSchedule;
import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.CronScheduleBuilder.monthlyOnDayAndHourAndMinute;
import static org.quartz.DateBuilder.dateOf;
import static org.quartz.TriggerBuilder.newTrigger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raisonne.quartz.scheduler.dto.CronTriggerDTO;
import com.raisonne.quartz.scheduler.trigger.action.ExecutionIntervalsEnum;
import com.raisonne.quartz.scheduler.util.CustomSchdularFactory;
/**
 * <code>TriggerBuilderHelper</code> is used create a trigger.
 * <p>
 * Trigger creation is based on the {@link CronTriggerDTO} being provided
 * by the UI which includes various parameters ranging from 
 * Cron expression to Simple scheduling expression.
 * </p>
 * @author Umesh Awasthi
 *
 */
public class TriggerBuilderHelperImpl implements TriggerBuilderHelper {
	
	private final Logger log=LoggerFactory.getLogger(getClass());
    
	/**
	 * Trigger instance variable responsible for holding the final 
	 * trigger which later will be associated with scheduler.
	 */
	Trigger trigger;
	
	/**
	 * <p>Method responsible for creating a cron expression based on the 
	 * provided {@link CronTriggerDTO}.</p>.
	 * <p>
	 * It will take help of various helper method to build the final 
	 * Cron expression.A Cron expression can range from building a simple expression
	 * to any complex Cron expression.
	 * </p>
	 * @param cronTriggerDTO DTO containing various fields to be used o crate expression.
	 * @return Cron expression based on the provided DTO.
	 * @throws SchedulerException 
	 */
	@Override
	public boolean scheduleTrigger(CronTriggerDTO cronTriggerDTO) throws SchedulerException {
		 if(checkTriggerExists(cronTriggerDTO.getTiggerId(), cronTriggerDTO.getTriggerGroup())){
			 log.info("Trigger with name {} already exists in {} group ",cronTriggerDTO.getTiggerId(), cronTriggerDTO.getTriggerGroup() );
			 throw new ObjectAlreadyExistsException(cronTriggerDTO.getTiggerId()); 
		 }
		log.info("Creating trigger expression for given cron  trigger DTO");
		ExecutionIntervalsEnum intervals=ExecutionIntervalsEnum.valueOf(cronTriggerDTO.getExecutionInterval().toUpperCase());
		Trigger trigger=null;
		switch(intervals){
		
		  case DAILY:
			   log.info("creating trigger for daily execution");
			   trigger=createDailyTriggerExpression(cronTriggerDTO);
			   break;
			  
		  case WEEKLY:
			   log.info("Creating trigger for weekly execution");
			   trigger=createWeeklyTriggerExpression(cronTriggerDTO);
			   break;
			  
		  case MONTHLY:
			  log.info("Creating trigger for Monthly execution");
			  trigger=createMonthlyTriggerExpression(cronTriggerDTO);
			  break;
			  
		  case ONCE:
			  log.info("Creating trigger for one time execution");
			  trigger=createSingleExecutionTriggerExpression(cronTriggerDTO);
			  break;
			  
		  case CRONEXPRESSION:
			  log.info("Creating cron expression for execution");
			  trigger=createCronExpression(cronTriggerDTO);
			  break;
		 
		  default:
				break;
		
		}
		
		if(trigger!=null){
			return scheduleTrigger(trigger);
		}
		return false;
	}
	
	/****************************** methods implementation for creating various trigger expressions *****************/
	
	private Trigger createDailyTriggerExpression(CronTriggerDTO cronTriggerDTO){
		
	
		trigger=newTrigger().withIdentity(cronTriggerDTO.getTiggerId(), cronTriggerDTO.getTriggerGroup()).forJob(new JobKey(cronTriggerDTO.getTriggerJobName(), cronTriggerDTO.getTriggerGroup()))
				.startAt(getTriggerExecutionDate(cronTriggerDTO)).withSchedule(calendarIntervalSchedule().withIntervalInDays(cronTriggerDTO.getDaySkipFrequency()))
				.build();
		
		return trigger;
	}
	
	private Trigger createWeeklyTriggerExpression(CronTriggerDTO cronTriggerDTO){
		trigger=newTrigger().withIdentity(cronTriggerDTO.getTiggerId(), cronTriggerDTO.getTriggerGroup()).forJob(new JobKey(cronTriggerDTO.getTriggerJobName(), cronTriggerDTO.getTriggerGroup()))
				.startAt(getTriggerExecutionDate(cronTriggerDTO)).withSchedule(calendarIntervalSchedule().withIntervalInWeeks(cronTriggerDTO.getWeeklyInterval()))
				.build();
		return trigger;
	}
	
	private Trigger createMonthlyTriggerExpression(CronTriggerDTO cronTriggerDTO){
		Calendar tempCalendar=Calendar.getInstance();
		tempCalendar.setTime(getTriggerExecutionDate(cronTriggerDTO));
		trigger=newTrigger().withIdentity(cronTriggerDTO.getTiggerId(), cronTriggerDTO.getTriggerGroup()).forJob(new JobKey(cronTriggerDTO.getTriggerJobName(), cronTriggerDTO.getTriggerGroup()))
				.startAt(getTriggerExecutionDate(cronTriggerDTO)).withSchedule(monthlyOnDayAndHourAndMinute(tempCalendar.get(Calendar.DAY_OF_MONTH), tempCalendar.get(Calendar.HOUR_OF_DAY), tempCalendar.get(Calendar.MINUTE)))
				.build();
		return trigger;
	}
	
	private Trigger createSingleExecutionTriggerExpression(CronTriggerDTO cronTriggerDTO){
		return newTrigger().withIdentity(cronTriggerDTO.getTiggerId(), cronTriggerDTO.getTriggerGroup()).forJob(new JobKey(cronTriggerDTO.getTriggerJobName(), cronTriggerDTO.getTriggerGroup())).startAt(getTriggerExecutionDate(cronTriggerDTO)).build();
	}
	
	private CronTrigger createCronExpression(CronTriggerDTO cronTriggerDTO){
		return newTrigger().withIdentity(cronTriggerDTO.getTiggerId(), cronTriggerDTO.getTriggerGroup()).forJob(new JobKey(cronTriggerDTO.getTriggerJobName(), cronTriggerDTO.getTriggerGroup())).withSchedule(cronSchedule(cronTriggerDTO.getCron_expression())).build();
	}
	
	
	private Date getTriggerExecutionDate(CronTriggerDTO cronTriggerDTO){
		Calendar now=Calendar.getInstance();
		now.setTime(cronTriggerDTO.getStartDate());
		SimpleDateFormat df = new SimpleDateFormat("hh:mm:ss aa");
		Date d=null;
		String[] timeString=cronTriggerDTO.getStartTime().trim().split(":");
		StringBuilder strBuilder=new StringBuilder();
		for(int i=0; i< timeString.length;i++ ){
			if(!(timeString[i].equalsIgnoreCase("AM") || timeString[i].equalsIgnoreCase("PM"))){
				strBuilder.append(timeString[i]);
				if((i+2)!=timeString.length)
				strBuilder.append(":");
			}
			else{
				strBuilder.append(" ");
				strBuilder.append(timeString[i].toUpperCase());
			}
		}	
		
		
		try {
		    d = df.parse(strBuilder.toString());
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		
		strBuilder=null;
		timeString=null;
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(d);
		d=null;
		return dateOf(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND), now.get(Calendar.DAY_OF_MONTH), now.get(Calendar.MONTH)+1,now.get(Calendar.YEAR));
		
	}
	

	/**
	 * <p>
	 * Method responsible for checking if a trigger already exists for a given 
	 * combination of trigger name and group.
	 * For a given combination of a key and group, only one trigger can exists
	 * under the given scheduler instance.
	 * </p>
	 * @param triggerName trigger name
	 * @param group group associated with the trigger
	 * @return boolean flag indicating if trigger already exists or not.
	 * @throws SchedulerException
	 */
	public boolean checkTriggerExists(String triggerName,String group) throws SchedulerException {
		log.info("Checking if any trigger exists for trigger name = {} and group = {} ",triggerName,group);
		if(triggerName==null || group ==null){
			log.error("Trigger key required both triggerName and group, please provide both parameters");
			throw new IllegalArgumentException("triggerName and group can not be null,both are needed format a unique combination");
		}
		TriggerKey triggerKey=new TriggerKey(triggerName, group);
		return CustomSchdularFactory.getSchedulerInstance().checkExists(triggerKey);
		
	}
	
	private  boolean scheduleTrigger(Trigger trigger){
		try {
			CustomSchdularFactory.getSchedulerInstance().scheduleJob(trigger);
		} catch (SchedulerException e) {
			
			log.error("Unable to schedule trigger");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
}
