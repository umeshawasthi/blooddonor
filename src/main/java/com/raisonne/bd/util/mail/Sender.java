package com.raisonne.bd.util.mail;
/**
 * 
 */


/**
 * @author Umesh Awasthi
 * @version 1.0
 * @since 03/11/2011
 *
 */
public class Sender {
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	private String from;
	private String name;

}
