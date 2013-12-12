package com.raisonne.bd.dto.donor;

import java.util.Date;

import net.sf.oval.constraint.MatchPattern;

public class FeedbackDTO {
	private String uuid;
	private String donorName;
	@MatchPattern(pattern="^([a-z0-9]{1,}[\\.\\_\\-]?[a-z0-9]{1,})\\@([a-z0-9]{2,}\\.)([a-z]{2,2}|org|net|com|gov|edu|int|info|biz|museum)$",message="invalid.email")
	private String email;
	private String contact;
	private String message;
	private Date feedbackTime =new Date();
	private boolean verified=false;
	
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
	public Date getFeedbackTime() {
		return feedbackTime;
	}
	public void setFeedbackTime(Date feedbackTime) {
		this.feedbackTime = feedbackTime;
	}
	
	public boolean isVerified() {
		return verified;
	}
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
