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
package com.raisonne.quartz.scheduler.job.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.quartz.InterruptableJob;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.ObjectAlreadyExistsException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.StatefulJob;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raisonne.quartz.scheduler.dto.JobDTO;
import com.raisonne.quartz.scheduler.dto.JobDetailsDTO;
import com.raisonne.quartz.scheduler.job.service.JobService;
import com.raisonne.quartz.scheduler.job.strategies.JobDetailStrategy;
import com.raisonne.quartz.scheduler.job.strategies.JobDetailStrategyImpl;
import com.raisonne.quartz.scheduler.util.CustomSchdularFactory;

/**
 * <p>Implementation of {@link JobService} interface.This Implementation
 * is responsible for providing entry to do various operations with {@link JobDetail}.
 * @author Umesh Awasthi
 *
 */
@SuppressWarnings("deprecation")
public class JobServiceImpl implements JobService {

	private final Logger log=LoggerFactory.getLogger(getClass());
	/**
	 * {@link JobDetailStrategy} for handling job Strategy.
	 */
	private JobDetailStrategy jobDetailStrategy;
	
	@Override
	public boolean createJob(JobDTO jobDto) throws SchedulerException {
		jobDetailStrategy=new JobDetailStrategyImpl();
		if(!(jobDetailStrategy.checkJobExists(jobDto.getJobName(),jobDto.getJobGroupName()))){
			log.info("Adding the Job to scheduler");
			JobDetail jobDetail=jobDetailStrategy.createDefaultJobDetail(jobDto);
		    CustomSchdularFactory.getSchedulerInstance().addJob(jobDetail, false);
		    return true;
		}
		else{
			 log.info("Job with name {} already exists in {} group ",jobDto.getJobName(),jobDto.getJobGroupName() );
			 throw new ObjectAlreadyExistsException(jobDto.getJobName()); 
		}
	}

	@Override
	public boolean deleteJob() {
		log.info("Deleting a Job and adding with Quartz");
		return false;
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
		jobDetailStrategy=new JobDetailStrategyImpl();
		return jobDetailStrategy.checkJobExists(jobName, group);
		
	}
	
	
	@Override
	public List<String> getSchedulerJobListForGroup(String group) {
		List<String> jobName=null;
		try {
			Set<JobKey> keys=CustomSchdularFactory.getSchedulerInstance().getJobKeys(GroupMatcher.<JobKey>groupEquals(group));
			jobName=new ArrayList<String>();
			Iterator<JobKey> iter = keys.iterator();
			while (iter.hasNext()){
				JobKey jobKey=iter.next();
				jobName.add(jobKey.getName());
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		return jobName == null ? null :Collections.unmodifiableList(jobName);
	} 
	
	public List<String> getAllJobList(){
		Scheduler scheduler;
		List<String> jobName=new ArrayList<String>();
		try {
			scheduler = CustomSchdularFactory.getSchedulerInstance();
			List<String> jobGroups=scheduler.getJobGroupNames();
			for(int i=0;i<jobGroups.size();i++){
				String name = (String) jobGroups.get(i);
				Set<JobKey> keys=CustomSchdularFactory.getSchedulerInstance().getJobKeys(GroupMatcher.<JobKey>groupEquals(name));
				Iterator<JobKey> iter = keys.iterator();
				while (iter.hasNext()){
					JobKey jobKey=iter.next();
					jobName.add(jobKey.getName());
				}
			}
			
		} catch (SchedulerException e) {
			
			e.printStackTrace();
		}
		
		
		return jobName == null ? null :Collections.unmodifiableList(jobName);
	}
	

public List<JobDetailsDTO> getAllJobsDetails(){
	List<String> groups=null; 
	Scheduler scheduler=null;
	List<JobDetailsDTO> jobDetailsDTOList=new ArrayList<JobDetailsDTO>();
	JobDetailsDTO jobDetailsDTO=null;
	try {
		scheduler = CustomSchdularFactory.getSchedulerInstance();
		groups=scheduler.getJobGroupNames();
		Collections.sort(groups);
		List<JobExecutionContext> executingJobs = scheduler.getCurrentlyExecutingJobs();
		for (String group : groups){
			Set<JobKey> keys = scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(group));
			
			int i = 0;
			Iterator<JobKey> iter = keys.iterator();
			while (iter.hasNext()){
				JobKey jobKey=iter.next();
				Trigger trigger = null;
				@SuppressWarnings("unchecked")
				List<Trigger> jobTriggers = (List<Trigger>) scheduler.getTriggersOfJob(new JobKey(jobKey.getName(), group));
				if(!jobTriggers.isEmpty()){
					trigger=jobTriggers.get(0);
				}
				JobDetail jobDetail=scheduler.getJobDetail(new JobKey(jobKey.getName(), group));
				int numInstances = 0;
				for (JobExecutionContext jobExecutionContext : executingJobs)

			      {

			        JobDetail execJobDetail = jobExecutionContext.getJobDetail();

			        if (execJobDetail.getKey().equals(jobDetail.getKey()))

			        {

			          numInstances++;

			        }

			      }

				boolean isPaused = scheduler.getTriggerState(new TriggerKey(jobKey.getName(), group)) == Trigger.TriggerState.PAUSED;
				i++;
				jobDetailsDTO=new JobDetailsDTO();
				jobDetailsDTO.setPaused(isPaused);
				if(InterruptableJob.class.isAssignableFrom(jobDetail.getJobClass())){
				    jobDetailsDTO.setInterruptableJob("YES");
				}
				else{
					 jobDetailsDTO.setInterruptableJob("NO");
				}
				
				if(StatefulJob.class.isAssignableFrom(jobDetail.getJobClass())){
					jobDetailsDTO.setStatefulJob("YES");
				}
				else{
					jobDetailsDTO.setStatefulJob("NO");
				}
				
				jobDetailsDTO.setCount(i);
				
				if(trigger!=null){
					jobDetailsDTO.setPreviousFireTime(trigger.getPreviousFireTime());
					jobDetailsDTO.setNextFireTime(trigger.getNextFireTime());
				}
				
				jobDetailsDTO.setTriggerExpression("NA");
				
				jobDetailsDTO.setNumInstances(numInstances);
				jobDetailsDTO.setGroupName(group);
				jobDetailsDTO.setJobName(jobKey.getName());
				jobDetailsDTO.setJobLenght(keys.size());
				
				jobDetailsDTOList.add(jobDetailsDTO);
				
			}
		 }
	} catch (SchedulerException e) {
		
		e.printStackTrace();
	}
	
	return jobDetailsDTOList;
}
	
	/**
	 * @return the jobDetailStrategy
	 */
	public JobDetailStrategy getJobDetailStrategy() {
		return jobDetailStrategy;
	}

	/**
	 * @param jobDetailStrategy the jobDetailStrategy to set
	 */
	public void setJobDetailStrategy(JobDetailStrategy jobDetailStrategy) {
		this.jobDetailStrategy = jobDetailStrategy;
	}

	

	
}
