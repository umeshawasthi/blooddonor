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

import java.sql.Connection;
import java.sql.SQLException;

import org.quartz.utils.DBConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p.Manager class responsible to provide data base connection
 * to perform any database operation.</p>
 * <p>
 * This manager class will utilize the underlying data source
 * used by the Quartz.
 * </p>
 * 
 * @author Umesh Awasthi
 *
 */
public class ConnectionManager {

	
	private final static Logger log=LoggerFactory.getLogger(ConnectionManager.class);
	/**
	 * <p>Method responsible for creating an instance of
	 * Database connection and returning it for performing any 
	 * data base specific operation.</p>
	 * <p>
	 * The method will utilize the underlying data-base pooling 
	 * created by the Quartz.
	 * </p>
	 * @return database connection instance
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		log.info("Fetching connection instance from underlying data source");
		Connection con=DBConnectionManager.getInstance().getConnection("myDS");
		return con;
	}
	
	/**
	 * Method responsible for closing connection so that the connection
	 * can be returned to the pool.
	 * @param con connection instance
	 * @throws SQLException 
	 */
	public static void closeConnection(Connection con) throws SQLException{
		if(con!=null){
			try{
			     log.info("Preparing to close the connection");
				 con.close();	
			}
			catch(Exception e){
				log.error("unable to close the connection");
				e.printStackTrace();
			}
			finally{
				 if(!con.isClosed())
				  con.close();
			}
			
			if(con.isClosed()){
				log.info("Connection has been closed successfuly");
			}
		}
		
	}
	
}
