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
package com.raisonne.quartz.scheduler.group.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.raisonne.quartz.scheduler.constant.SchedulerConstants;
import com.raisonne.quartz.scheduler.util.ConnectionManager;

/**
 * @author Umesh Awasthi
 *
 */
public class SchedulerGroupDAOImpl implements SchedulerGroupDAO {

	
	private final Logger log=LoggerFactory.getLogger(getClass());
	/**
	 * <p>Implementation responsible for creating group with underlying database.
	 * This implementation is responsible for getting a connection instance from the
	 * Quartz data source and will create group.</p>
	 * <p>
	 * This method will take care of data base connection creating as well as closing the 
	 * connection so that it can be safely returned to the underlying data source.
	 * </p>
	 * @return flag indicating if the group has been created or not.
	 * @throws SQLException 
	 */
	@Override
	public boolean createGroupByName(final String groupName,final String schedulerGroupDescription) throws SQLException {
	 log.info("Starting to create group with name {} ", groupName);
		Connection con=ConnectionManager.getConnection();
		PreparedStatement ps=null;
		try{
			
			ps=con.prepareStatement(SchedulerConstants.CREATE_GROUP);
			ps.setString(1, groupName);
			ps.setString(2, schedulerGroupDescription);
			int count=ps.executeUpdate();
			if(count>0){
				log.info("Successfuly created group with name {} ", groupName);
				return true;
			}
			else{
				log.info("Unable to create group with name {} ", groupName);
				return false;
		}
		}
		finally{
			log.info("Closing database connection");
			closeStatement(ps);
			ConnectionManager.closeConnection(con);
			
		}
		
	}
	
	
	
	/**
	 * <p>Method used to check if the given Group with group name already
	 * exists in the underlying database</p>
	 * <p>
	 * It will return a flag indicating id group already exists or not
	 * </p>
	 * @param groupName groupName which needs to be checked with underlying DB.
	 * @return boolean flag indicating if it exists or not
	 * @throws SQLException
	 */
	@Override
	public boolean checkIfGroupExists(final String groupName) throws SQLException{
		Connection con=ConnectionManager.getConnection();
		PreparedStatement ps=null;
		int count=0;
		ResultSet rs = null;
		try {

			ps = con.prepareStatement(SchedulerConstants.GROUP_COUNT);
			ps.setString(1, groupName);
			log.info("Executng query {} ", ps);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
				
			}
            log.info("Total {} records found ",count);
			if (count > 0) {
				return true;

			} else {
				return false;
			}
		} finally {
			closeResultSet(rs);
			closeStatement(ps);
			ConnectionManager.closeConnection(con);
		}
		
		
	}
	
	
	/**
	 * <p>Method responsible for returning list of groups
	 * stored in underlying database.</p>
	 * <p>
	 * These groups will be used to combine create Triggers and Jobs.
	 * </p>
	 * @return List of groupModel object.
	 * @throws SQLException 
	 */
	@Override
	public List<GroupModel> getAllGroups() throws SQLException {
		Connection con=ConnectionManager.getConnection();
		ResultSet rs = null;
		PreparedStatement ps=null;
		try{
			 log.info("Prearing to fetch all groups from database");
			 ps = con.prepareStatement(SchedulerConstants.SELECT_ALL_GROUPS);
			 rs=ps.executeQuery();
			 List<GroupModel> lists=new ArrayList<GroupModel>();
			 while(rs.next()){
				 lists.add(new GroupModel(rs.getString(2),rs.getInt(1),rs.getString(3)));
			 }
			 log.info("Found total {} groups ",lists.size());
			 return Collections.unmodifiableList(lists);
		}
		finally{
			
				log.info("Closing up resources");
				ConnectionManager.closeConnection(con);
			    closeResultSet(rs);
				closeStatement(ps);
			 
		}
		
	}
	
	
	/**
     * Cleanup helper method that closes the given <code>ResultSet</code>
     * while ignoring any errors.
     */
    protected static void closeResultSet(ResultSet rs) {
        if (null != rs) {
            try {
                rs.close();
            } catch (SQLException ignore) {
            }
        }
    }
    
    /**
     * Cleanup helper method that closes the given <code>Statement</code>
     * while ignoring any errors.
     */
    protected static void closeStatement(Statement statement) {
        if (null != statement) {
            try {
                statement.close();
            } catch (SQLException ignore) {
            	
            }
        }
    }
	

}
