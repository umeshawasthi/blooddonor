/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.oauth.config;

/**
 * @author Umesh Awasthi
 * @version 1.0
 * @since 03-12-2011
 *
 */
public class OAuthSignInException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String exception="There seems some problem with your OAuth";
	
	public OAuthSignInException(){
		super();
	}
	
	public OAuthSignInException(String cause){
		super(cause);
		exception=cause;
	}
	
	public String getError()
	  {
	    return exception;
	  }
	

}
