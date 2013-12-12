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
 * @author Umesh Awasthi
 *
 */
public class JobDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public JobDTO() {
		
	}
	private String jobName;
	private String jobGroupName;
	private String className;
	private String methodName;
	private String jobDetails;
	private String jobClassName;
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
	 * @return the jobDetails
	 */
	public String getJobDetails() {
		return jobDetails;
	}
	/**
	 * @param jobDetails the jobDetails to set
	 */
	public void setJobDetails(String jobDetails) {
		this.jobDetails = jobDetails;
	}
	public String getJobClassName() {
		return jobClassName;
	}
	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}
	
	
	
	
	
	

}
