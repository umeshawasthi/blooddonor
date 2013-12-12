package com.raisonne.bd.util.mail;


import java.io.Serializable;
import java.util.Date;


/**
 * Encapsulates properties of a simple mail such as from, to, cc, subject, text.
 * To be sent with a MailSender implementation.
 * <p>
 * 
 * @author Umesh Awasthi
 * @since 03/11/2011
 * @version 1.0
 * 
 */
public class SimpleMailMessage implements Serializable {

	private static final long serialVersionUID = 1L;
	private static SimpleMailMessage simpleMailMessage;

	public void setFrom(Sender sender) {
		this.sender = sender;
	}

	public Sender getFrom() {
		return sender;
	}

	public void setReplyTo(Sender sender) {
		this.sender = sender;

	}

	public Sender getReplyTo() {
		return sender;
	}

	public void setTo(String to) {
		if (to != null) {
			this.toArray = to.split(",");
		}

	}

	public String[] getTo() {
		return toArray;
	}

	public void setCc(String cc) {
		if (cc != null) {
			this.ccArray = cc.split(",");
		}
	}

	public String[] getCc() {
		return ccArray;
	}

	public void setBcc(String bcc) {
		if (bcc != null) {
			this.bccArray = bcc.split(",");
		}
	}

	public String[] getBcc() {
		return bccArray;
	}

	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}

	public Date getSentDate() {
		return sentDate;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setTemplate(String template) {
		this.template = template;

	}

	public String getTemlate() {
		return template;
	}

	private Sender sender;
	private String[] toArray;
	private String[] ccArray;
	private String[] bccArray;
	private Date sentDate;
	private String subject;
	private String template;

}