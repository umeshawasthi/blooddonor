package com.raisonne.bd.util;

import java.sql.Connection;
import java.sql.SQLException;


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
public class GenericConnectionManager {

	
	private final static Logger log=LoggerFactory.getLogger(GenericConnectionManager.class);
	private static GenericConnectionManager instance=new GenericConnectionManager();
	
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
	public static GenericConnectionManager getInstance()
	  {
	    return instance;
	  }
	
	public Connection getConnection()
		    throws SQLException
		  {
		    ConnectionProvider provider = new ConnectionProviderImpl();
		    return provider.getConnection();
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
	
public void shutdown()
			    throws SQLException
			  {
	             ConnectionProvider provider = new ConnectionProviderImpl();
			      provider.shutdown();
			  }
	
	

	
}
