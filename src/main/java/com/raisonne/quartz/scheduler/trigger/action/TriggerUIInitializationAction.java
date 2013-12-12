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
package com.raisonne.quartz.scheduler.trigger.action;

import java.io.File;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.raisonne.bd.action.donor.BaseAction;
import com.raisonne.quartz.scheduler.dto.CronTriggerUIDTO;
import com.raisonne.quartz.scheduler.dto.JobDTO;
import com.raisonne.quartz.scheduler.group.dao.GroupModel;
import com.raisonne.quartz.scheduler.group.service.SchedulerGroupService;
import com.raisonne.quartz.scheduler.group.service.impl.SchedulerGroupServiceImpl;
import com.raisonne.quartz.scheduler.job.JobAware;
import com.raisonne.quartz.scheduler.util.JobClassScanner;
import com.raisonne.quartz.scheduler.util.PrepareExecutionIntervalList;
import com.raisonne.quartz.scheduler.util.TriggerUIHelper;

/**
 * <p>Action class responsible to preparing the UI view to show
 * triggering options.</p>
 * This class is responsible for preparing various options that can be 
 * offered to the user while creating the triggers.Trigger creation is independent 
 * from the job creation and same trigger can be associated with multiple jobs.</p>
 * @author Umesh Awasthi
 *
 */
public class TriggerUIInitializationAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger log=LoggerFactory.getLogger(getClass());
	private String template=null;
	private List<String> demoList;
	private CronTriggerUIDTO cronTriggerUIDTO;
	private List<GroupModel> groupModelsList;

	
	
	/**
	 * Field used to find out which interval user has selected
	 * this will help in returning the related template and also
	 * the required data.
	 */
	private String selectedExecutionInterval;
	
	/**
	 * <p>List defining the possible execution interval for a trigger
	 * list can be populated using a property file or can be done with
	 * the help of Database table</p>
	 */
	public List<String> executionInterval;
	
	public String prepareTriggerUI() throws Exception{
		log.info("Starting preparing TriggerUI...");
		executionInterval=PrepareExecutionIntervalList.getExecutionIntervalList();
		prepareJobUI();
		
		setHideLeftPannel(true);
		setHideRightPannel(true);
		setWorkingTemplate("/WEB-INF/templates/scheduler/createtriggers.jsp");
		return SUCCESS;
	}

	
	/**
	 * <p>Method responsible to providing user with options
	 * to add triggering information.</p>
	 * <p>
	 * Based on the user choice this method will pick the required
	 * Template and will initialize any required data to UI display.
	 * </p>
	 * @return template to be included based on the user choice
	 * @throws Exception
	 */
	public String selectTemplate()throws Exception
	{
		
		log.info("Selecting the tempelate for {} ",selectedExecutionInterval);
		if(cronTriggerUIDTO==null)
			cronTriggerUIDTO=new CronTriggerUIDTO();
		setExecutionIntervalsEnum(ExecutionIntervals.valueOf(selectedExecutionInterval.toUpperCase()));
		prepareTriggerTempelateUIData();
		return SUCCESS;
	}
	
	
	/**
	 * @return the executionInterval
	 */
	public List<String> getExecutionInterval() {
		return executionInterval;
	}

	/**
	 * @param executionInterval the executionInterval to set
	 */
	public void setExecutionInterval(List<String> executionInterval) {
		this.executionInterval = executionInterval;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}


	/**
	 * @return the demoList
	 */
	public List<String> getDemoList() {
		return demoList;
	}


	/**
	 * @param demoList the demoList to set
	 */
	public void setDemoList(List<String> demoList) {
		this.demoList = demoList;
	}

	/**
	 * @return the cronTriggerUIDTO
	 */
	public CronTriggerUIDTO getCronTriggerUIDTO() {
		return cronTriggerUIDTO;
	}


	/**
	 * @param cronTriggerUIDTO the cronTriggerUIDTO to set
	 */
	public void setCronTriggerUIDTO(CronTriggerUIDTO cronTriggerUIDTO) {
		this.cronTriggerUIDTO = cronTriggerUIDTO;
	}


	/**
	 * @return the selectedExecutionInterval
	 */
	public String getSelectedExecutionInterval() {
		return selectedExecutionInterval;
	}


	/**
	 * @param selectedExecutionInterval the selectedExecutionInterval to set
	 */
	public void setSelectedExecutionInterval(String selectedExecutionInterval) {
		this.selectedExecutionInterval = selectedExecutionInterval;
	}
	
	
	
	/**
	 * @return the groupModelsList
	 */
	public List<GroupModel> getGroupModelsList() {
		return groupModelsList;
	}


	/**
	 * @param groupModelsList the groupModelsList to set
	 */
	public void setGroupModelsList(List<GroupModel> groupModelsList) {
		this.groupModelsList = groupModelsList;
	}


	private void prepareTriggerTempelateUIData(){
		
		switch (getExecutionIntervalsEnum()) {
		
		case DAILY:
			 log.info("Creating daily trigger expression.");
			 cronTriggerUIDTO.setDaily(true);
			 cronTriggerUIDTO.setDailyTriggerFrequencyRange(TriggerUIHelper.getDailyTriggerFrequencyRange());
			 setTemplate("/WEB-INF/templates/scheduler/dailyfrequencytrigger.jsp");
			 break;
		    
		case WEEKLY:
			 log.info("Creating weekly trigger expression.");
			 cronTriggerUIDTO.setWeekly(true);
			 cronTriggerUIDTO.setWeeklyTriggerFrequencyRange(TriggerUIHelper.getWeeklyTriggerFrequencyRange());
			 cronTriggerUIDTO.setWeekdays(getWeekDaysList());
			 setTemplate("/WEB-INF/templates/scheduler/weeklyfrequencytrigger.jsp");
			 break;
			 
		case MONTHLY:
			 log.info("Creating monthly trigger expression.");
			 cronTriggerUIDTO.setMonthly(true);
			 cronTriggerUIDTO.setMonthlyTriggerFrequencyRange(TriggerUIHelper.getMonthlyTriggerFrequencyRange());
			 setTemplate("/WEB-INF/templates/scheduler/monthlytrigger.jsp");
			 break;
			
		case ONCE:
			 log.info("Creating trigger expression which will fire once.");
			 break;
			
		case CRONEXPRESSION:
			 log.info("Creating free cron expression.");
			 setTemplate("/WEB-INF/templates/scheduler/cronexpression.jsp");
			 break;
			 
		default:
			break;
		
		}
		
	}
	

	private ExecutionIntervals executionIntervalsEnum;

	/**
	 * @return the executionIntervalsEnum
	 */
	public ExecutionIntervals getExecutionIntervalsEnum() {
		return executionIntervalsEnum;
	}


	/**
	 * @param executionIntervalsEnum the executionIntervalsEnum to set
	 */
	public void setExecutionIntervalsEnum(ExecutionIntervals executionIntervalsEnum) {
		this.executionIntervalsEnum = executionIntervalsEnum;
	}
	
	
	/**
	 * <p>Creation of Jobs and Triggers require to assign them a 
	 * group so that they can be treated as unique within the group</p>
	 * This method is responsible to fetching list of groups from 
	 * underlying BD and will return the list to UI to fill the 
	 * drop down.
	 * @return control string to render view
	 * @throws Exception
	 */
	public String triggerGroupDropDown() throws Exception{
		log.info("About to start preparing the view..");
		if(groupModelsList==null){
			getAllGroups();
		}
		else if(groupModelsList.isEmpty()){
			getAllGroups();
		}
		return SUCCESS;
	}
	
	
	   private void getAllGroups(){
		   SchedulerGroupService schedulerGroupService=new SchedulerGroupServiceImpl();
		   groupModelsList=schedulerGroupService.getAllGroups();
		  
		}

	
	/**
	 * Enumeration type for currently Trigger creation options.
	 */
	public enum ExecutionIntervals {
		DAILY,
		WEEKLY,
		MONTHLY,
		ONCE,
		CRONEXPRESSION

	}
	
	private String[] getWeekDaysList(){
		String [] tempWeekDays=DateFormatSymbols.getInstance().getShortWeekdays();
		String [] weekDays=new String[7];
		System.arraycopy(tempWeekDays , 1, weekDays , 0, 7);
		return weekDays;
	}
	
	/**
	 * <p>Each job being created in the scheduler will be unique within the group and
	 * scheduler API provides a way to create groups separately.</p>
	 * <p> Method is responsible for creating/init the view which includes
	 * <li>fetching the list of group from underlying DB</li>
	 * <li>sending the list to UI so that it can be displayed in the Job creating UI </li>
	 * 
	 * @return control string to render the view.
	 * @throws Exception
	 */
	public void prepareJobUI() throws Exception{
		log.info("About to start preparing the view..");
		ServletContext context = ServletActionContext.getServletContext();
		 String s=new File(context.getRealPath(File.separator)).getAbsolutePath();
		 s+=File.separator+"WEB-INF"+File.separator+"classes";
				 
				 
		jobClassList=JobClassScanner.getJobClassList(s);
		if(!jobClassList.isEmpty()){
			jobClassName=new ArrayList<String>();
			for (@SuppressWarnings("rawtypes") Class clazz:jobClassList){
				
				  jobClassName.add(clazz.getName());
			}
		}
		
		if(groupModelsList==null){
			getAllGroups();
			
			
		}
		else if(groupModelsList.isEmpty()){
			getAllGroups();
		}
		
	}
	
	
	
	private List<Class<? extends JobAware>> jobClassList;
	private List<String> jobClassName;
	private JobDTO jobDTO;
	
	public List<Class<? extends JobAware>> getJobClassList() {
		return jobClassList;
	}

	public void setJobClassList(List<Class<? extends JobAware>> jobClassList) {
		this.jobClassList = jobClassList;
	}
	
	public List<String> getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(List<String> jobClassName) {
		this.jobClassName = jobClassName;
	}
	
	/**
	 * @return the jobDTO
	 */
	public JobDTO getJobDTO() {
		return jobDTO;
	}

	/**
	 * @param jobDTO the jobDTO to set
	 */
	public void setJobDTO(JobDTO jobDTO) {
		this.jobDTO = jobDTO;
	}
}
