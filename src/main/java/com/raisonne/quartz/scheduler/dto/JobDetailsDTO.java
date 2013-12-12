/**
 * 
 */
package com.raisonne.quartz.scheduler.dto;

import java.util.Date;

/**
 * @author Umesh A
 *
 */
public class JobDetailsDTO {
	
	private boolean isPaused;
	private int count;
	private String triggerExpression;
	private Date previousFireTime;
	private Date nextFireTime;
	private String statefulJob;
	private String interruptableJob;
	private int numInstances;
	private String groupName;
	private String jobName;
	private int jobLenght;
	
	public boolean isPaused() {
		return isPaused;
	}
	public void setPaused(boolean isPaused) {
		this.isPaused = isPaused;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTriggerExpression() {
		return triggerExpression;
	}
	public void setTriggerExpression(String triggerExpression) {
		this.triggerExpression = triggerExpression;
	}
	public Date getPreviousFireTime() {
		return previousFireTime;
	}
	public void setPreviousFireTime(Date previousFireTime) {
		this.previousFireTime = previousFireTime;
	}
	public Date getNextFireTime() {
		return nextFireTime;
	}
	public void setNextFireTime(Date nextFireTime) {
		this.nextFireTime = nextFireTime;
	}
	public String getStatefulJob() {
		return statefulJob;
	}
	public void setStatefulJob(String statefulJob) {
		this.statefulJob = statefulJob;
	}
	public String getInterruptableJob() {
		return interruptableJob;
	}
	public void setInterruptableJob(String interruptableJob) {
		this.interruptableJob = interruptableJob;
	}
	public int getNumInstances() {
		return numInstances;
	}
	public void setNumInstances(int numInstances) {
		this.numInstances = numInstances;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public int getJobLenght() {
		return jobLenght;
	}
	public void setJobLenght(int jobLenght) {
		this.jobLenght = jobLenght;
	}
	
	

}
