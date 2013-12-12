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
package com.raisonne.quartz.scheduler.job.service;

import java.util.List;

import org.quartz.SchedulerException;

import com.raisonne.quartz.scheduler.dto.JobDTO;
import com.raisonne.quartz.scheduler.dto.JobDetailsDTO;

/**
 * Interface defining various methods being used in the
 * Job handling service. 
 * @author Umesh Awasthi
 *
 */
public interface JobService {
	
	/**
	 * Create a Job in Scheduler API.this will take JobDTO.
	 *  
	 * @param jobDto jobDTO carrying job related data
	 * @throws SchedulerException 
	 */
	public boolean createJob(JobDTO jobDto) throws SchedulerException;
	public boolean deleteJob()throws SchedulerException;
	public boolean checkJobExists(String jobName,String group) throws SchedulerException;
	public List<String> getSchedulerJobListForGroup(String group);
	public List<String> getAllJobList();
	public List<JobDetailsDTO> getAllJobsDetails();

}
