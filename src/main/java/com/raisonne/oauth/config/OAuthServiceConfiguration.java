/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */

package com.raisonne.oauth.config;

import org.apache.log4j.Logger;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.Api;
import org.scribe.builder.api.FacebookApi;
import org.scribe.builder.api.GoogleApi;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.builder.api.LiveApi;
import org.scribe.builder.api.TwitterApi;
import org.scribe.builder.api.YahooApi;
import org.scribe.oauth.OAuthService;

import com.raisonne.bd.util.BloodGroupUtils;

/**
 * This class will be responsible for creating an object of OAuthService based on
 * the parameters provided.
 * This will hand over a fully configured AuthService object for a specific service
 * In order to configure Authentication object it needs following parameters 
 * <li>Scope of request</li>
 * <li>Feed URls</li>
 * <li>Secret Key</li>
 * <li>API Key</li>
 * <li>Authorization URL</li>
 * @author Umesh Awasthi
 * @version 1.0 23/11/2011
 * @since   1.0
 *
 */

public class OAuthServiceConfiguration {
	
	
	Logger log=Logger.getLogger(OAuthServiceConfiguration.class);
	/**
	 * 
	 * @param servive parameters describing which service object needs to be initialized.
	 * @param callBackURL url to be configured where call back from service provider will be handled.
	 * @return {@link OAuthService} OAuthService object of the requested service based on service parameter.
	 */
	@SuppressWarnings("static-access")
	public OAuthService getOAuthService(String servive,String callBackURL){
		OAuthService service=null;
		log.info("Starting to initialize required data for "+servive);
		setImplimentedServivesName(ImplimentedServivesName.valueOf(servive));  
		setServiceProviderData();
		if(isUseScope()){
				if(null!=callBackURL && !("".equals(callBackURL))){
					log.info("Preparing OAuthService object with call back handler");
					service= new ServiceBuilder().provider(getProviderName()).apiKey(BloodGroupUtils.getParameter(getoAuthProviderPrefix()+".apiKey"))
				               .apiSecret(BloodGroupUtils.getParameter(getoAuthProviderPrefix()+".apiSecret")).scope(BloodGroupUtils.getParameter(getoAuthProviderPrefix()+".scope"))
				               .callback(callBackURL).build();
				}
			else{
			
				log.info("no call back handler is provided.Preparing OAuthService object without call back handler");
				service=new ServiceBuilder().provider(getProviderName()).apiKey(BloodGroupUtils.getParameter(getoAuthProviderPrefix()+".apiKey"))
		               .apiSecret(BloodGroupUtils.getParameter(getoAuthProviderPrefix()+".apiSecret")).scope(BloodGroupUtils.getParameter(getoAuthProviderPrefix()+".scope"))
		               .build();
			
			}
				
	 }
	
		else{
			
			if(null!=callBackURL && !("".equals(callBackURL))){
				log.info("Preparing OAuthService object using call back handler with no scope");
				service= new ServiceBuilder().provider(getProviderName()).apiKey(BloodGroupUtils.getParameter(getoAuthProviderPrefix()+".apiKey"))
			               .apiSecret(BloodGroupUtils.getParameter(getoAuthProviderPrefix()+".apiSecret"))
			               .callback(callBackURL).build();
			}
		else{
		
			log.info("no call back handler is provided.Preparing OAuthService object without call back handler with no scope");
			service=new ServiceBuilder().provider(getProviderName()).apiKey(BloodGroupUtils.getParameter(getoAuthProviderPrefix()+".apiKey"))
	               .apiSecret(BloodGroupUtils.getParameter(getoAuthProviderPrefix()+".apiSecret"))
	               .build();
		
		}
			
		}
		
		return service;
		
	}

		
	/**
	 * Enumeration type for currently oauth implimeted services 
	 * 
	 * @author Umesh Awasthi
	 *
	 */
	public enum ImplimentedServivesName {
	    GOOGLE,
	    YAHOO,
	    FACEBOOK,
	    TWITTER,
	    MSN,
	    LINKEDIN
	  }
	
	
	
	private ImplimentedServivesName ImplimentedServivesName;
	
	public ImplimentedServivesName getImplimentedServivesName() {
		return ImplimentedServivesName;
	}
	
	public void setImplimentedServivesName(
			ImplimentedServivesName implimentedServivesName) {
		ImplimentedServivesName = implimentedServivesName;
	}
	
	
	
	/**
	 * Method being used to set Service provider class name as its prefix which will be used to
	 * fetch data like secret key and APIkey from the context
	 */
	private void setServiceProviderData(){
		
		switch (getImplimentedServivesName()) {
		
		case GOOGLE:
			
			setProviderName(GoogleApi.class);
			setoAuthProviderPrefix("google");
			setUseScope(true);
		   break;
		case YAHOO:
			setProviderName(YahooApi.class);
			setoAuthProviderPrefix("yahoo");
			setUseScope(false);
			break;
		case FACEBOOK:
			setProviderName(FacebookApi.class);
			setoAuthProviderPrefix("facebook");
			setUseScope(true);
			break;
		case TWITTER:
			setProviderName(TwitterApi.class);
			setoAuthProviderPrefix("twitter");
			setUseScope(false);
			break;
		case MSN:
			setProviderName(LiveApi.class);
			setoAuthProviderPrefix("msn");
			setUseScope(true);
			break;
		case LINKEDIN:
			setProviderName(LinkedInApi.class);
			setoAuthProviderPrefix("linkedin");
			setUseScope(true);
			break;
		default:
			throw new IllegalArgumentException("We are not provind oauth for the requested service provider");
		}
		
	}
	
	
	
	
	/**
	 * Getter and setter for 
	 * <li> Service provider prefix which will be used to fetch values from utility class to create instance</li>
	 * <li> service provider class name
	 */
	
	public boolean useScope=true;
	
	
	public boolean isUseScope() {
		return useScope;
	}

	public void setUseScope(boolean useScope) {
		this.useScope = useScope;
	}




	private String oAuthProviderPrefix;
	public String getoAuthProviderPrefix() {
		log.info("Returing prefix for "+oAuthProviderPrefix);
		return oAuthProviderPrefix;
	}
	public void setoAuthProviderPrefix(String oAuthProviderPrefix) {
		this.oAuthProviderPrefix = oAuthProviderPrefix;
	}
	
	public Class<? extends Api> getProviderName() {
		log.info("Returning instance of "+ProviderName.getName());
		return ProviderName;
	}
	public void setProviderName(Class<? extends Api> providerName) {
		ProviderName = providerName;
	}
	private Class<? extends Api> ProviderName;
}


