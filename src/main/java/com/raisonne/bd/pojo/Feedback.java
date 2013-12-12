package com.raisonne.bd.pojo;

import java.util.Date;


public class Feedback {
	private String uuid;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	private String donorName;
	private String email;
	private String contact;
	private String message;
	private boolean verified;
	private Date feedbackTime =new Date();
	
	
	
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Feedback(String uuid,String donorName, String email, String contact,
			String message, boolean verified, Date feedbackTime) {
		super();
		this.uuid=uuid;
		this.donorName = donorName;
		this.email = email;
		this.contact = contact;
		this.message = message;
		this.verified = verified;
		this.feedbackTime = feedbackTime;
	}
	public String getDonorName() {
		return donorName;
	}
	public void setDonorName(String donorName) {
		this.donorName = donorName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public Date getFeedbackTime() {
		return feedbackTime;
	}
	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
	
	

}
