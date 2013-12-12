package com.raisonne.bd.util.mail.impl;
/**
 * 
 */


import java.io.File;
import java.util.Map;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.raisonne.bd.util.mail.MimeMessagePreparator;
import com.raisonne.bd.util.mail.SimpleMailMessage;
import com.raisonne.bd.util.mail.templateprocessor.TemplateProcessingEngine;


/**
 * An implimentation for {@link MimeMessagePreparator} which is capable of creating two
 * types of MimeMessage
 * <pre>
 * 1. Simple MimeMessage with no attachment
 * 2. MimeMessage with attachments
 * </pre>
 * @author Umesh Awasthi
 * @since 03/11/2011
 * @version 1.0
 *
 */
public class MimeMessagePreparatorImpl implements MimeMessagePreparator {
	
	Logger log=Logger.getLogger(MimeMessagePreparatorImpl.class);
	public static MimeMessagePreparatorImpl mimeMessagePreparatorImpl;
	MimeMessage mimeMessage;
	/**
	 * Simple prepareMimeMessage method,this method has the capability of creating simple
	 * MimeMessage that can be used to send a simple mail.
	 * It assumes that before calling this method the caller has already set the required values in the
	 * SimpleMailMessage object as well the velocity template is there in the specificed location.
	 * This method will use Velcity Template engine to convert the template data in to simple HTML text.
	 * @param simpleMessage
	 * @param template
	 * @return mimeMessage
	 * @throws MessagingException 
	 */
	public MimeMessage prepareMimeMessgae(SimpleMailMessage simpleMessage,
			String template,Session session, Map model) throws MessagingException {
		log.info("Creating mimemessage for the mail");
		mimeMessage=new MimeMessage(session);
		mimeMessage.setSubject(simpleMessage.getSubject());
		mimeMessage.setSentDate(simpleMessage.getSentDate());
		mimeMessage.setContent(TemplateProcessingEngine.getInstance(model).parseTemplate(template), "text/html");
		return mimeMessage;
	}

	
	/**
	 * Advance prepareMimeMessage method,this method has the capability of creating 
	 * MimeMessage with attachments as provided in the attachment array,also make sure the attachment location should be in the
	 * format of standard URL 
	 * <pre>URL example: www.travellingrants.com/mail/travellingguide.pdf</pre>
	 * It assumes that before calling this method the caller has already set the required values in the
	 * SimpleMailMessage object as well the velocity template is there in the specificed location.
	 * This method will use Velcity Template engine to convert the template data in to simple HTML text.
	 * @param simpleMessage
	 * @param template
	 * @return mimeMessage
	 * @return {@link MimeMessage} with attachment
	 * @throws MessagingException 
	 */
	public MimeMessage prepareMimeMessageWithAttachment(
			SimpleMailMessage simpleMessage, String[] attachments,
			String template,Session session, Map model) throws MessagingException {
		log.info("Creating mimemessage for the mail with attachment(s)");
		mimeMessage=new MimeMessage(session);
		mimeMessage.setSubject(simpleMessage.getSubject());
		mimeMessage.setSentDate(simpleMessage.getSentDate());
		BodyPart messageBodyPart=new MimeBodyPart();
		messageBodyPart.setContent(TemplateProcessingEngine.getInstance(model).parseTemplate(template), "text/html");
		Multipart multipart=new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		/*
		 * Mail attachment section goes here
		 */
		mimeMessage=getAttachmentsToMail(mimeMessage,messageBodyPart,multipart,attachments);
		return mimeMessage;
	}
	
    private MimeMessage getAttachmentsToMail(MimeMessage mimeMessage,BodyPart messageBodyPart,Multipart multipart,String[] attachments) throws MessagingException{
    	log.info("Starting attachment(s) to mail");
    	if(attachments.length==0){
    		log.info("No Source specified for attachments");
    		return null;
    	}
    	DataSource dataSource;
    	for(int i=0;i<attachments.length;i++){
    		messageBodyPart=new MimeBodyPart();
    		dataSource=new FileDataSource(new File(attachments[i]));
    		messageBodyPart.setDataHandler(new DataHandler(dataSource));
    		messageBodyPart.setFileName(dataSource.getName());
    		multipart.addBodyPart(messageBodyPart);
    		mimeMessage.setContent(multipart);
    	}
    	log.info("Total "+attachments.length + "attachments done");
    	
    	return mimeMessage;
    }
	
	public static MimeMessagePreparatorImpl createInstance(){
		if(mimeMessagePreparatorImpl==null){
			mimeMessagePreparatorImpl=new MimeMessagePreparatorImpl();
		}
		return mimeMessagePreparatorImpl;
	}
}
