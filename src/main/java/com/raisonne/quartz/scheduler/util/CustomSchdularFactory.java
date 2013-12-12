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
package com.raisonne.quartz.scheduler.util;

import java.util.Collection;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>This class consist exclusively for static methods that operates on or
 * returns Quartz scheduler instance.
 * </p>
 * Class in itself will be responsible for providing Quartz scheduler instance
 * and providing caching mechanism to avoid unnecessary creation of 
 * scheduler instance.
 * 
 * @author Umesh Awasthi
 *
 */
public class CustomSchdularFactory {
	
	private static final Logger log=LoggerFactory.getLogger(CustomSchdularFactory.class);
	// Suppresses default constructor, ensuring non-instantiability.
	private CustomSchdularFactory(){
		
	}
	
	private static SchedulerFactory schdularFactoryInstance=null;
	private static Scheduler scheduler=null; 
    
	
	/**
	 * <p>
	 * Method is responsible of returning scheduler instance of
	 * {@link SchedulerFactory} so make sure to cast it as 
	 * per your needs.
	 * </p>
	 * @return scheduler Quartz scheduler instance which is central for
	 * scheduling API
	 * @throws SchedulerException Scheduler exception 
	 * 
	 */
	public static Scheduler getSchedulerInstance() throws SchedulerException{
		log.debug("returning scheduler instance");
		if(scheduler==null){
			scheduler=schdularFactoryInstance.getScheduler();
		}
		return scheduler;
		
	}
	
	/**
	 * Returns a handle to the Scheduler with the given name, if it exists (if it has already been instantiated).
	 * @param schdulerName
	 * @return
	 * @throws SchedulerException
	 */
	public static Scheduler getSchedulerInstance(String schdulerName) throws SchedulerException{
		log.debug("returning scheduler instance with name {} ",schdulerName);
		if(scheduler==null){
			scheduler=schdularFactoryInstance.getScheduler(schdulerName);
		}
		return scheduler;
		
	}
	
	/**
	 * 
	 * @return Returns a handle to the default Scheduler, creating it if it does not
     * yet exist.
	 * @throws SchedulerException
	 */
	@SuppressWarnings("static-access")
	public static Scheduler getDefaultScheduler() throws SchedulerException{
		log.debug("returning default scheduler instance");
		StdSchedulerFactory stdSchedulerFactory=(StdSchedulerFactory)schdularFactoryInstance;
		if(scheduler==null){
			scheduler=stdSchedulerFactory.getDefaultScheduler();
		}
		return scheduler;
	}
	
	/**
	 * 
	 * @return   Returns a handle to all known Schedulers (made by any StdSchedulerFactory instance.).
	 * @throws SchedulerException
	 */
	public static Collection<Scheduler> getAllScheduler() throws SchedulerException{
		return schdularFactoryInstance.getAllSchedulers();
	}
	
	
	
	/**
	 * 
	 * @param schdular Quartz scheduler instance which needs to be injected
	 */
	public static void setSchdularFactoryInstance(SchedulerFactory schdularFactory) {
		schdularFactoryInstance = schdularFactory;
	}
	
	

}
