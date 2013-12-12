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

import org.quartz.JobDetail;
import org.quartz.SchedulerException;

import com.raisonne.quartz.scheduler.dto.JobDTO;
import com.raisonne.quartz.scheduler.job.GenericJob;
import com.raisonne.quartz.scheduler.job.JobAware;

/**
 *<p> Interface declaring various method to construct
 * {@link JobDetail} instance based on the DTO being provided
 * along with other configurable parameters.</p>
 * @author Umesh Awasthi
 *
 */
public interface JobDetailStrategy {
	
	/**
	 * <p>Simple Method takes DTO as input and will use other default setting
	 * to create JobDetail instance.</p>
	 * @param jobDto
	 * @return {@link JobDetail}
	 */
	public  JobDetail createDefaultJobDetail(JobDTO jobDto);
	
	/**
	 * <p>JobDetail creating method will take other parameters like
	 * if the Job should recover when scheduler get shutdown and
	 * can job exist in the database even if its associated trigger expired
	 * </p>
	 * <p>
	 * {@link JobDTO} will contain other inputs parameters to create a valid
	 * JobDetail instance.
	 * </p>
	 * @param jobDto DTO instance contains various input parameters.
	 * @param isrequestRecovery flag indicating if recovery is required.
	 * @param isDurable flag indicating if job is durable or not
	 * @return {@link JobDetail}
	 */
	public JobDetail createJobDetail(JobDTO jobDto,boolean isrequestRecovery, boolean isDurable );
	
	/**
	 * <p>
	 * Extension point to provide custom JobClass other than {@link GenericJob}.
	 * Class need to implement {@link JobAware} interface to work as 
	 * JobClass.
	 * </p>
	 * <p>JobDetail creating method will take other parameters like
	 * if the Job should recover when scheduler get shutdown and
	 * can job exist in the database even if its associated trigger expired
	 * </p>
	 * <p>
	 * {@link JobDTO} will contain other inputs parameters to create a valid
	 * JobDetail instance.
	 * </p>
	 * 
	 * @param jobDto DTO instance contains various input parameters.
	 * @param isrequestRecovery flag indicating if recovery is required.
	 * @param isDurable flag indicating if job is durable or not
	 * @param jobClass implementation of {@link JobAware}
	 * @return {@link JobDetail}
	 */
	public JobDetail createJobDetail(JobDTO jobDto,boolean isrequestRecovery, boolean isDurable, Class<JobAware> jobClass );
	
	/**
	 * <p>
	 * Extension point to provide custom JobClass other than {@link GenericJob}.
	 * Class need to implement {@link JobAware} interface to work as 
	 * JobClass.
	 * </p>
	 * <p>
	 * {@link JobDTO} will contain other inputs parameters to create a valid
	 * JobDetail instance.
	 * </p>
	 * @param jobDto jobDto DTO instance contains various input parameters.
	 * @param jobClass  implementation of {@link JobAware}
	 * @return {@link JobDetail}
	 */
	public JobDetail createJobDetail(JobDTO jobDto,Class<JobAware> jobClass );
	
	
	/**
	 * Determine whether a Job with the given identifier already exists within the scheduler.
	 * @param jobName job name.
	 * @param group group with in which job exists.
	 * @return flag indicating if job already exists or not
	 * @throws SchedulerException
	 */
	public boolean checkJobExists(String jobName,String group) throws SchedulerException;

}
