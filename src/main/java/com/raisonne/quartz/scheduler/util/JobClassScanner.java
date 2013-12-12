/**
 * 
 */
package com.raisonne.quartz.scheduler.util;

import java.util.List;

import org.quartz.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raisonne.classfinder.util.JavaClassFinder;
import com.raisonne.quartz.scheduler.job.JobAware;

/**
 * <p>Utility class which will take care of scanning classpath
 * to find all those classes which will implement {@link JobAware}
 * interface.</p>
 * All those classes which wants to declare them as Quartz {@link Job}
 * should implement {@link JobAware} interface and provides an implimentation
 * to its execute method so as to indicate the system that this class want itself
 * to be treated as a Job class.
 * 
 * @author Umesh Aawasthi
 * @since 29-05-2012
 * @version 1.0
 *
 */
public class JobClassScanner {
	
	
	private static final Logger log=LoggerFactory.getLogger(JobClassScanner.class);
	/**
	 * Finds all classes which are Assignable from the specified class
	 * @return List of class objects
	 */
	public static <T> List<Class<? extends JobAware>> getJobClassList(String path){
		log.info("Starting classpath scan to get list of all classes implimenting JobAware interface");
		JavaClassFinder classFinder=new JavaClassFinder();
		List<Class<? extends JobAware>> classes =classFinder.findAllMatchingTypes(JobAware.class,path);
		if(!classes.isEmpty() && classes.size()>=1){
			log.info("Removing instance of com.raisonne.quartz.scheduler.job.JobAware");
			classes.remove(com.raisonne.quartz.scheduler.job.JobAware.class);
		}
		
		return classes;
		
	}

}
