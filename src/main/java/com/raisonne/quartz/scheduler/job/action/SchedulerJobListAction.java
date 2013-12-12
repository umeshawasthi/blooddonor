/**
 * 
 */
package com.raisonne.quartz.scheduler.job.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



import com.raisonne.bd.action.donor.BaseAction;
import com.raisonne.quartz.scheduler.job.service.JobService;
import com.raisonne.quartz.scheduler.job.service.impl.JobServiceImpl;

/**
 * @author Umesh Awasthi
 *
 */
public class SchedulerJobListAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String group;
	private List<String> jobList;
	
	private final Logger log=LoggerFactory.getLogger(getClass());
	
	public String getSchedulerJobList(){
		log.info("Starting to fetch all jobs for {} group ",group);
		JobService jobService=new JobServiceImpl();
		if(group.equalsIgnoreCase("*")){
			jobList=jobService.getAllJobList();
		}
		else{
			jobList=jobService.getSchedulerJobListForGroup(group);
		}
		
		return SUCCESS;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public List<String> getJobList() {
		return jobList;
	}

	public void setJobList(List<String> jobList) {
		this.jobList = jobList;
	}
	
	

}
