/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.oauth.action;

import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;
import org.expressme.openid.Association;
import org.expressme.openid.Endpoint;
import org.expressme.openid.OpenIdManager;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import com.opensymphony.xwork2.ActionSupport;
import com.raisonne.bd.constant.OAuthConstants;
import com.raisonne.bd.util.BloodGroupUtils;
import com.raisonne.oauth.config.OAuthGetRequestToken;
import com.raisonne.oauth.config.OAuthServiceConfiguration;

/**
 * This action class is responsible for preparing an OAuth object and 
 * redirecting user to the Authentication service provider login page.
 * 
 * This action will only redirect user to a login page and will not be responsible for the handling
 * call back mechanism.This will store the request token in to session and it will be the responsibility
 * of the call back handler action to retrieve token instance and create a valid
 * Authorization request.
 * 
 * @author Umesh Awasthi
 * @version 1.0
 * @since 24-11-2011
 *
 */
public class OAuthRedirectAction extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static Logger log=Logger.getLogger(OAuthRedirectAction.class);
	private static final long serialVersionUID = 1L;
	
	/**
	 * Name of the service provider with whom user want to authenticate there self
	 * this is a crucial piece of information as based on this OAuth object will be created.
	 */
	private String service_provider_name;
	private OAuthServiceConfiguration oAuthServiceConfiguration;
	private OAuthGetRequestToken oAuthGetRequestToken;
	private String requestUrl;
	Map<String, Object> session=null;
	private String callback_URL="";
	
	/**
	 * Entry method which will be responsible to create a authorization URL to which user will be redirected
	 * to authenticate there self.
	 */
	public String execute() throws Exception{
		log.info("Preparing to authenticate user from "+service_provider_name);
		callback_URL=BloodGroupUtils.getParameter("app.callback.url");
		if("BD".equals(service_provider_name)){
			return "bd";
		}
		
		else if("GOOGLE".equals(service_provider_name) || "LINKEDIN".equals(service_provider_name)){
			requestUrl=getOauth2Redirection();
		}
		
		else if("YAHOO".equals(service_provider_name)){
			requestUrl=getOpenIDRedirectionToYahoo();
		}
		else{
			requestUrl=getOtherOauthRedirection();
		}
		log.info("Redirecting user to "+requestUrl + " for authentication");
		return "redirect_oauth_serice";
	}
	
	
	
	
	
	
	private String getOauth2Redirection(){
		
		OAuthService service=oAuthServiceConfiguration.getOAuthService(service_provider_name, callback_URL+service_provider_name);
		Token token=oAuthGetRequestToken.getRequestToken(service);
		session.put(OAuthConstants.REQUEST_TOKEN,token);
		return oAuthGetRequestToken.getAuthorizationUrl(service, token);
		
		
	}
	
	
	private String getOtherOauthRedirection(){
		OAuthService service=oAuthServiceConfiguration.getOAuthService(service_provider_name, callback_URL+service_provider_name);
		return service.getAuthorizationUrl(OAuthConstants.EMPTY_TOKEN);
	}
	
	
	private String getOpenIDRedirectionToYahoo(){
		log.info("Creating Redirection for Yahoo");
		OpenIdManager manager=new OpenIdManager();
		manager.setRealm(OAuthConstants.YAHOO_OPENID_RELEAM);
		manager.setReturnTo(callback_URL+service_provider_name);
		Endpoint endpoint = manager.lookupEndpoint("Yahoo");
		Association association = manager.lookupAssociation(endpoint);
		session.put(OAuthConstants.ATTR_MAC, association.getRawMacKey());
		session.put(OAuthConstants.ATTR_ALIAS, endpoint.getAlias());
		return manager.getAuthenticationUrl(endpoint, association);
		
	}
	
	

	public String getService_provider_name() {
		return service_provider_name;
	}

	public void setService_provider_name(String service_provider_name) {
		this.service_provider_name = service_provider_name;
	}

	public void setoAuthServiceConfiguration(
			OAuthServiceConfiguration oAuthServiceConfiguration) {
		this.oAuthServiceConfiguration = oAuthServiceConfiguration;
	}

	public void setoAuthGetRequestToken(OAuthGetRequestToken oAuthGetRequestToken) {
		this.oAuthGetRequestToken = oAuthGetRequestToken;
	}
	
	
	@Override
	public void setSession(Map<String, Object> arg0) {
		session=arg0;
		 
	}
	public Map<String, Object> getSession() {
		return session;
	}

	public String getRequestUrl() {
		return requestUrl;
	}

	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}

	public OAuthServiceConfiguration getoAuthServiceConfiguration() {
		return oAuthServiceConfiguration;
	}

	public OAuthGetRequestToken getoAuthGetRequestToken() {
		return oAuthGetRequestToken;
	}
	
	

}
