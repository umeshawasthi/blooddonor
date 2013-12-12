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
package com.raisonne.quartz.scheduler.job.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.opensymphony.xwork2.Action;
import com.raisonne.bd.action.donor.BaseAction;
import com.raisonne.quartz.scheduler.dto.JobDTO;
import com.raisonne.quartz.scheduler.dto.JobDetailsDTO;
import com.raisonne.quartz.scheduler.group.dao.GroupModel;
import com.raisonne.quartz.scheduler.group.service.SchedulerGroupService;
import com.raisonne.quartz.scheduler.group.service.impl.SchedulerGroupServiceImpl;
import com.raisonne.quartz.scheduler.job.JobAware;
import com.raisonne.quartz.scheduler.job.service.JobService;
import com.raisonne.quartz.scheduler.job.service.impl.JobServiceImpl;
import com.raisonne.quartz.scheduler.util.JobClassScanner;
import com.raisonne.quartz.scheduler.util.SchedulerUtil;

/**
 * <p>
 * Action class responsible for handling all work related
 * to {@link Job}, which includes</p>
 * <li>Creation of new Jobs.</li>
 * <li>Editing existing Job.</li>
 * <li>Deleting a Job.</li>
 * @author Umesh Awasthi
 *
 */
public class JobAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger log=LoggerFactory.getLogger(getClass());
	
	/**
	 * DTO to carry job specific data from view to 
	 * action class and vice-versa.
	 */
	private JobDTO jobDTO;
	private List<GroupModel> groupModelsList;
	private List<Class<? extends JobAware>> jobClassList;
	private List<String> jobClassName;
	private List<JobDetailsDTO> jobDetailsDTOs;
	private String schedulerStatus="Not Running";
	
	/**
	 * <p>Action Method which will handle the creation of new Job 
	 * in Quartz Scheduler API.</p>
	 * <p>
	 * It will take certain parameters from the view to map 
	 * a Job and configure it in the API.Will use {@link JobDTO} 
	 * to carry the Job Data.JobDTO includes following information
	 * </p>
	 * <li> Job Name(Must be Unique with in a group) </li>
	 * <li>Class Name (Class whose method needs to be called when Job will get executed)
	 * <p>
	 * Class Name will the name of the class with your business logic which you want 
	 * Quartz to trigger as per provided schedule.This class can be any simple class and
	 * need not to implement {@link Job} interface.
	 * Make sure you provide class name with fully qualified name (with package structure)
	 * so that it should not have any conflict with similar class name.
	 * </p>
	 * </li>
	 * <li> Method Name, name of the method which you want to call.</li>
	 * <li>Any parameters you want to pass to the Method </li>
	 * <p>
	 * Method will be the name of the method which should be called by the Quartz API
	 * to execute your business logic.Only primitives and String type will be allowed as 
	 * the parameter types to avoid any Serialization issue.
	 * </p>
	 * @return <p>Result string indicating which view to render
	 *            or which action to call in the chain. </p>
	 * @throws Exception generic Exception
	 */
	public String createJob() throws Exception{
		log.info("Preparing to create a new Job for schdeluer");
		JobService jobService=new JobServiceImpl();
		if(!(jobService.checkJobExists(jobDTO.getJobName(), jobDTO.getJobGroupName()))){
			log.info("Job does not exists, creating a new one");
			jobService.createJob(jobDTO);
			addActionMessage(getText("job.created"));
			prepareJobUI();
			setHideLeftPannel(true);
			setHideRightPannel(true);
			setWorkingTemplate("/WEB-INF/templates/scheduler/newJob.jsp");
			return Action.SUCCESS;  
		}
		else{
			log.warn("Job with name {} already exists in {} group. ",jobDTO.getJobName(), jobDTO.getJobGroupName());
			String[] args ={jobDTO.getJobName(), jobDTO.getJobGroupName()};
			addActionMessage(getText("job.exists", args));
			setJobDTO(jobDTO);
			prepareJobUI();
			setHideLeftPannel(true);
			setHideRightPannel(true);
			setWorkingTemplate("/WEB-INF/templates/scheduler/newJob.jsp");
			return INPUT;  
		}
	}
	
	public String showAllJobs(){
		JobService jobService=new JobServiceImpl();
		jobDetailsDTOs=jobService.getAllJobsDetails();
		schedulerStatus=SchedulerUtil.getSchedulerStatus();
		setHideLeftPannel(true);
		setHideRightPannel(true);
		setWorkingTemplate("/WEB-INF/templates/scheduler/showAllJobs.jsp");
		setTitle("Jobs Management");
				
		return SUCCESS;
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
	public String prepareJobUI() throws Exception{
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
		
		setHideLeftPannel(true);
		setHideRightPannel(true);
		setWorkingTemplate("/WEB-INF/templates/scheduler/newJob.jsp");
		return SUCCESS;
	}
	
	
	   private void getAllGroups(){
		   SchedulerGroupService schedulerGroupService=new SchedulerGroupServiceImpl();
		   groupModelsList=schedulerGroupService.getAllGroups();
		  
		}
	   
	  

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

	public List<JobDetailsDTO> getJobDetailsDTOs() {
		return jobDetailsDTOs;
	}

	public void setJobDetailsDTOs(List<JobDetailsDTO> jobDetailsDTOs) {
		this.jobDetailsDTOs = jobDetailsDTOs;
	}

	public String getSchedulerStatus() {
		return schedulerStatus;
	}
	
	
	   
	   
}