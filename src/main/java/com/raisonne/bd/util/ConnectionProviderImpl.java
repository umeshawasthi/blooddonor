/**
 * 
 */
package com.raisonne.bd.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @author Umesh A
 *
 */
public class ConnectionProviderImpl implements ConnectionProvider {
	private static Logger LOG=LoggerFactory.getLogger(ConnectionProviderImpl.class);
	public static final String DB_DRIVER = "driver";
	public static final String DB_URL = "URL";
	public static final String DB_USER = "user";
	public static final String DB_PASSWORD = "password";
	public static final String DB_MAX_CONNECTIONS = "maxConnections";
	private ComboPooledDataSource datasource;
	
	public ConnectionProviderImpl() throws IllegalStateException{
		initialize();
	}

	// needs more generic functionality to read form the property file
	private void initialize() throws IllegalStateException {
		this.datasource = new ComboPooledDataSource();
		Configuration config=getDataBaseProperties("database.properties");
		
		try {
			this.datasource.setDriverClass(config.getString("database.driver"));
		} catch (PropertyVetoException e) {
			e.printStackTrace();
			throw new IllegalStateException("Problem setting driver class name on datasource: " + e.getMessage(), e);
			
		}
		this.datasource.setJdbcUrl(config.getString("database.url"));
	    this.datasource.setUser(config.getString("database.user"));
	    this.datasource.setPassword(config.getString("database.password"));
	    this.datasource.setMaxPoolSize(config.getInt("database.maxPoolSize"));
	    this.datasource.setMinPoolSize(config.getInt("database.minPoolSize"));
	}
	
	protected ComboPooledDataSource getDataSource()
	  {
	    return this.datasource;
	  }
	
	public Connection getConnection() throws SQLException {
	    return this.datasource.getConnection();
	  }

	  public void shutdown() throws SQLException {
	    this.datasource.close();
	  }
	  
	  private Configuration getDataBaseProperties(String propertyFileName){
		  Configuration config=null;
		  try {
			  config = new PropertiesConfiguration(propertyFileName);
		} catch (ConfigurationException e) {
			LOG.error("Unable to read {} file.. ",propertyFileName);
			LOG.error("Error... "+e);
			
		}
		  return config;
	  }
}
