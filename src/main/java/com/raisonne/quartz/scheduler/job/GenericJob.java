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
package com.raisonne.quartz.scheduler.job;

import java.lang.reflect.Method;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

/**
 * <p>
 * A Generic Job which is capable enough to handle any kind of
 * job being created for scheduling.
 * </p>
 * <p>
 * This job class will be responsible for calling the
 * worker class method based on the class and method name
 * provided in the {@link JobDataMap}
 * </p>
 * @author Umesh Awasthi
 *
 */
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class GenericJob implements JobAware {
	
	private String jobName;
	private String jobGroupName;
	private String className;
	private String methodName;



	/*
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     * Constructors.
     *  
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
	
	public GenericJob() {
		
	}

	/**
	 * Execute method which will be responsible to call a given class's method
	 * using reflection.Default implementation will only be able to call no argument
	 * method of the given class.
	 * 
	 * <p>
	 * For any other method need to provide an implementation by implementing
	 * {@link JobAware} interface 
	 * </p>
	 */
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		 Class<?> noparams[] = {};
		 JobDataMap dataMap = context.getJobDetail().getJobDataMap();
		 Class<?> cls=null;
		 Object obj=null;
		 Method method=null;
		 
		 try {
				cls = Class.forName(dataMap.getString("className"));
				 obj = cls.newInstance();
				 method = cls.getDeclaredMethod(dataMap.getString("methodName"), noparams);
				 method.invoke(obj, new Object());
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			finally{
				cls=null;
				obj=null;
				method=null;
			}
	}
	
	/*
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     *
     * Data Map Values Injections
     *  
     *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */

	
	 
	 
	/**
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return the jobGroupName
	 */
	public String getJobGroupName() {
		return jobGroupName;
	}

	/**
	 * @param jobGroupName the jobGroupName to set
	 */
	public void setJobGroupName(String jobGroupName) {
		this.jobGroupName = jobGroupName;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	
	

}
