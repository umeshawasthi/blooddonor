/*
 * Copyright 2011 Raisonne Technologies All rights reserved.
 */
package com.raisonne.bd.paging;

/**
 * @author raisonne
 *
 */
public class PagingObject {

	private int record_size=10;
	private int page_number=1;
	private int limit=0;
	private int previous=0;
	private int next=0;
	private int limits=0;
	private long totalProfiles=0;
	
	
	public int getRecord_size() {
		return record_size;
	}
	
	public void setRecord_size(int record_size) {
		this.record_size = record_size;
	}
	public int getPage_number() {
		return page_number;
	}
	public void setPage_number(int page_number) {
		this.page_number = page_number;
	}
	public int getLimit() {
		
		limit=(getPage_number()-1) * getRecord_size();
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public int getPrevious() {
		previous=getPage_number()-1;
		return previous;
	}
	public void setPrevious(int previous) {
		this.previous = previous;
	}
	public int getNext() {
		next=getPage_number()+1;
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public int getLimits() {
		limits=(getPage_number()-1) * getRecord_size();
		return limits;
	}
	public void setLimits(int limits) {
		this.limits = limits;
	}
	
	public long getTotalProfiles() {
		return totalProfiles;
	}

	public long getTotalProfiles(long count) {
        //totalProfiles=java.lang.Math.round(count/getRecord_size());
        return totalProfiles;
	}

	public void setTotalProfiles(long totalPosts) {
		this.totalProfiles =(long) Math.ceil(totalPosts/(double)getRecord_size());
	}			
	
	
	
	
}
