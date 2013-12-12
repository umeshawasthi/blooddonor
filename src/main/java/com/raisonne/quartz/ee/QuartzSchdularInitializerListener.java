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
package com.raisonne.quartz.ee;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raisonne.quartz.scheduler.util.CustomSchdularFactory;


/**
 * <p>
 * A ServletContextListner that can be used to Create Quartz Scheduler Instance.
 * Goal is to made available an instance of Scheduler instance on application start-up.
 * </p>
 * <p>
 * The init parameter 'quartz:servlet-context-factory-key' can be used to override the
 * name under which the StdSchedulerFactory is stored into the ServletContext.
 * </p>
 * @author Umesh Awasthi
 *
 */
public class QuartzSchdularInitializerListener implements ServletContextListener{

	public static final String QUARTZ_FACTORY_KEY = "org.quartz.impl.StdSchedulerFactory.KEY";
	private StdSchedulerFactory schedulerFactory = null;
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		
		try{
			//CustomSchdularFactory.setSchdularFactoryInstance(schedulerFactory);
			//CustomSchdularFactory.get
			for(Scheduler scheduler:CustomSchdularFactory.getAllScheduler()){
			    scheduler.shutdown();
			}
		}
		catch(Exception e){
			log.error("Scheduler failed to shutdown cleanly: " + e.toString());
            e.printStackTrace();
		}
		log.info("Closing quartz schdular instance...");
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		log.info("initializing schdular instance...");
		try {
		ServletContext servletContext=servletContextEvent.getServletContext();
		try {
			
			 String factoryKey = servletContext.getInitParameter("quartz:servlet-context-factory-key");
	            if(factoryKey == null)
	                factoryKey = servletContext.getInitParameter("servlet-context-factory-key");
	            if (factoryKey == null) {
	                factoryKey = QUARTZ_FACTORY_KEY;
	            }
	            log.info("Trying to fetch Quartz Scheduler Factory using key: "+ factoryKey);
	            schedulerFactory=(StdSchedulerFactory)servletContext.getAttribute(factoryKey);
	            CustomSchdularFactory.setSchdularFactoryInstance(schedulerFactory);
	            //schedulerFactory.getScheduler();
			
		} catch (Exception e) {
			log.error("Unable to initialize Schdular innstance: "+e.toString());
			e.printStackTrace();
		}
		}
		catch(Exception e){
		    e.printStackTrace();
		}
		
	}

}
