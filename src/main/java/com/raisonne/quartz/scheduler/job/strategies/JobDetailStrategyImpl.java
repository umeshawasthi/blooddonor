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
package com.raisonne.quartz.scheduler.job.strategies;

import static org.quartz.JobBuilder.newJob;



import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.raisonne.quartz.scheduler.constant.SchedulerConstants;
import com.raisonne.quartz.scheduler.dto.JobDTO;
import com.raisonne.quartz.scheduler.job.GenericJob;
import com.raisonne.quartz.scheduler.job.JobAware;
import com.raisonne.quartz.scheduler.util.CustomSchdularFactory;

/**
 * <p>Helper class used to create Job Detail to be
 * Scheduled in the Scheduler API.</p> 
 * @author Umesh Awasthi
 *
 */
public class JobDetailStrategyImpl implements JobDetailStrategy{
	
	 private final Logger log=LoggerFactory.getLogger(getClass());
	
	/**
	 * <p>Method which will create {@link JobDetail} instance based on the
	 * information being provided by the DTO.</p>
	 * <p>
	 * JobDeatil will later be used schedule Job in the scheduler.
	 * JobBuilder classed will be used to create an instance of 
	 * JobDetail.
	 * </p>
	 * <p>
	 * This method will use {@link GenericJob} as a job class.This
	 * class is capable enough to handle all type of Job functionality and
	 * will call the underlying class being passed to it in the JobDTO.
	 * </p>
	 * <p>
	 * This will be create JobDeatail with default setting
	 * <li> Request is true </li>
	 * <li> Durably is true </li>
	 * </p>
	 * @param jobDto DTO which will be used to construct the {@link JobDetail}.
	 * @return {@link JobDetail} instance
	 */
	@SuppressWarnings("rawtypes")
	public  JobDetail createDefaultJobDetail(JobDTO jobDto){
		log.debug("Creating JobDeatil instance with default settings");
		Class c;
		
		try {
			c = Class.forName(jobDto.getJobClassName());
		} catch (ClassNotFoundException e) {
			
			c=GenericJob.class;
			e.printStackTrace();
		}
		@SuppressWarnings("unchecked")
		JobDetail jobDetail=newJob(c).withIdentity(jobDto.getJobName(), jobDto.getJobGroupName())
							.usingJobData(SchedulerConstants.CLASS_NAME, jobDto.getClassName())
							.usingJobData(SchedulerConstants.METHOD_NAME, jobDto.getMethodName()).withDescription(jobDto.getJobDetails())
							 .requestRecovery(true).storeDurably(true)
							.build();
		return jobDetail;
	}
	
	/**
	 * <p>Method which will create {@link JobDetail} instance based on the
	 * information being provided by the DTO.</p>
	 * <p>
	 * JobDeatil will later be used schedule Job in the scheduler.
	 * JobBuilder classed will be used to create an instance of 
	 * JobDetail.
	 * </p>
	 * <p>
	 * This method will use {@link GenericJob} as a job class.This
	 * class is capable enough to handle all type of Job functionality and
	 * will call the underlying class being passed to it in the JobDTO.
	 * </p>
	 * 
	 * @param jobDto jobDto DTO which will be used to construct the {@link JobDetail}.
	 * @param isrequestRecovery if the job should be recovered if scheduler shutdown.
	 * @param isDurable should the job be retain even if the trigger it was associated expired.
	 * @return {@link JobDetail} instance
	 */
	public JobDetail createJobDetail(JobDTO jobDto,boolean isrequestRecovery, boolean isDurable ){
		 log.info("Creating JobDetail with requestRecovery {} and Durable {} ",isrequestRecovery,isDurable);
		 
		 JobDetail jobDetail=newJob(GenericJob.class).withIdentity(jobDto.getJobName(), jobDto.getJobGroupName())
		.usingJobData(SchedulerConstants.CLASS_NAME, jobDto.getClassName())
		.usingJobData(SchedulerConstants.METHOD_NAME, jobDto.getMethodName())
		 .requestRecovery(isrequestRecovery).storeDurably(isDurable).withDescription(jobDto.getJobDetails())
		.build();
		 
		return jobDetail;
		
	}
	
	
	/**
	 * An extension point to provide custom JobClass {@link Job} 
	 * which will be used by the scheduler while triggering the job.
     * <p>Method which will create {@link JobDetail} instance based on the
	 * information being provided by the DTO.</p>
	 * <p>
	 * JobDeatil will later be used schedule Job in the scheduler.
	 * JobBuilder classed will be used to create an instance of 
	 * JobDetail.
	 * </p>
	 * <p>
	 * This method will use {@link GenericJob} as a job class.
	 * </p>
	 * @param jobDto
	 * @param isrequestRecovery
	 * @param isDurable
	 * @param jobClass
	 * @return
	 */
	
	public JobDetail createJobDetail(JobDTO jobDto,boolean isrequestRecovery, boolean isDurable, Class<JobAware> jobClass ){
		 log.info("Creating JobDetail for given class");
		 JobDetail jobDetail=newJob(jobClass).withIdentity(jobDto.getJobName(), jobDto.getJobGroupName())
			.usingJobData(SchedulerConstants.CLASS_NAME, jobDto.getClassName())
			.usingJobData(SchedulerConstants.METHOD_NAME, jobDto.getMethodName())
			.requestRecovery(isrequestRecovery).storeDurably(isDurable).withDescription(jobDto.getJobDetails())
			.build();
		 return jobDetail;
	}
	
	/**
	 * An extension point to provide custom JobClass {@link Job} 
	 * which will be used by the scheduler while triggering the job.
     * <p>Method which will create {@link JobDetail} instance based on the
	 * information being provided by the DTO.</p>
	 * <p>
	 * JobDeatil will later be used schedule Job in the scheduler.
	 * JobBuilder classed will be used to create an instance of 
	 * JobDetail.
	 * </p>
	 * @param jobDto
	 * @param isrequestRecovery
	 * @return {@link JobDetail} instance
	 */
	
	public JobDetail createJobDetail(JobDTO jobDto,Class<JobAware> jobClass ){
		 log.info("Creating JobDetail for given class");
		 JobDetail jobDetail=newJob(jobClass).withIdentity(jobDto.getJobName(), jobDto.getJobGroupName())
			.usingJobData(SchedulerConstants.CLASS_NAME, jobDto.getClassName())
			.usingJobData(SchedulerConstants.METHOD_NAME, jobDto.getMethodName())
			 .requestRecovery(true).storeDurably(true).withDescription(jobDto.getJobDetails())
			.build();
		 return jobDetail;
	}

	

	/**
	 * <p> A job with the given name can only be unique within a group,
	 * if we try to save job with same name in the same group.we will get 
	 * an exception indicating Job with same name already exists within the
	 * group</p>
	 * <p> This method will check if the job with given identifier already exists or not
	 * and will return a boolean flag indicating the same.
	 * </p>
	 * {@link JobKey} is a combination of Job name and group combined together they
	 * work as PK for a job.
	 * <p>
	 * 
	 * </p>
	 * Determine whether a Job with the given identifier already exists within the scheduler.
	 * @return flag indicating if job already exists or not
	 * @throws SchedulerException
	 */
	public boolean checkJobExists(String jobName,String group) throws SchedulerException {
		log.info("Checking if any job exists for jobname = {} and group = {} ",jobName,group);
		if(jobName==null || group ==null){
			log.error("JobKey required both jobName and group, please provide both parameters");
			throw new IllegalArgumentException("jobName and group can not be null,both are needed format a unique combination");
		}
		JobKey jobKey=new JobKey(jobName, group);
		return CustomSchdularFactory.getSchedulerInstance().checkExists(jobKey);
	}
	
	
}
