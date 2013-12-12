/**
 * 
 */

package com.raisonne.bd.util.mail.impl;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

import com.raisonne.bd.util.PropertyLoader;
import com.raisonne.bd.util.mail.MailSender;
import com.raisonne.bd.util.mail.SimpleMailMessage;

/**
 * Mail implimentation class responsible for sending all type of mails in
 * Travellingrant's domain.This class is cabable of sending two types of mails
 * with or without attachments.
 * <p>
 * 
 * @author Umesh Awasthi
 * @since 03/11/2011
 * @version 1.0
 * 
 */
public class MailSenderImpl implements MailSender {

	Logger log = Logger.getLogger(MailSenderImpl.class);
	private static MailSenderImpl mailSenderImpl;
	private MimeMessage mimeMessage;
	Session session;
	Properties properties;

	/**
	 * A simple version of mail system used to send the given simple mail
	 * message. it will {@link SimpleMailMessage} and name of the template as
	 * input
	 * 
	 * @param simpleMessage
	 *            message to send
	 * @param template
	 *            template used for sending HTML contents
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public boolean send(SimpleMailMessage simpleMessage, String template,
			Map model) throws MessagingException, UnsupportedEncodingException {
		getMailSession();
		mimeMessage = MimeMessagePreparatorImpl.createInstance()
				.prepareMimeMessgae(simpleMessage, template, getMailSession(), model);
		log.info("Setting addresses in the message part");
		if (simpleMessage.getFrom().getName() != null)
			mimeMessage.setFrom(new InternetAddress(simpleMessage.getFrom()
					.getFrom(), simpleMessage.getFrom().getName()));
		else
			mimeMessage.setFrom(new InternetAddress(simpleMessage.getFrom()
					.getFrom()));
		mimeMessage.setRecipients(RecipientType.TO,
				getAddress(simpleMessage.getTo()));
		mimeMessage.setRecipients(RecipientType.CC,
				getAddress(simpleMessage.getCc()));
		mimeMessage.setRecipients(RecipientType.BCC,
				getAddress(simpleMessage.getBcc()));
		return sendMail(mimeMessage);

	}

	/**
	 * A specilized version of mail system which is capable of sending mail with
	 * attachments it will also take {@link SimpleMailMessage} file resources to
	 * be attached as well as the name of the template being used to send mail
	 * 
	 * @param simpleMessage
	 *            message to send
	 * @param attachments
	 *            path of the attachemnt to be send with mail
	 * @param template
	 *            template used for sending HTML contents
	 */
	public boolean sendAttachment(SimpleMailMessage simpleMessage,
			String[] attachments, String template, Map model)
			throws MessagingException, UnsupportedEncodingException {

		getMailSession();
		mimeMessage = MimeMessagePreparatorImpl.createInstance()
				.prepareMimeMessageWithAttachment(simpleMessage, attachments,
						template, getMailSession(), model);
		log.info("Setting addresses in the message part");
		if (simpleMessage.getFrom().getName() != null)
			mimeMessage.setFrom(new InternetAddress(simpleMessage.getFrom()
					.getFrom(), simpleMessage.getFrom().getName()));
		else
			mimeMessage.setFrom(new InternetAddress(simpleMessage.getFrom()
					.getFrom()));
		mimeMessage.setRecipients(RecipientType.TO,
				getAddress(simpleMessage.getTo()));
		mimeMessage.setRecipients(RecipientType.CC,
				getAddress(simpleMessage.getCc()));
		mimeMessage.setRecipients(RecipientType.BCC,
				getAddress(simpleMessage.getBcc()));
		return sendMail(mimeMessage);
	}

	private Address[] getAddress(String[] addressArray) {
		if (addressArray == null)
			return null;
		Address[] addresses = new Address[addressArray.length];
		for (int i = 0; i < addressArray.length; i++)
			try {
				addresses[i] = new InternetAddress(addressArray[i]);
			} catch (AddressException e) {
				e.printStackTrace();
			}
		return addresses;
	}

	/*
	 * An helper method to return java mail session object
	 */
	private Session getMailSession(){
		log.info("Creating mail session");
		properties=PropertyLoader.loadProperties("mail");
		if(properties==null){
			log.error("No properties file defined or file does not contain any key value");
			throw new IllegalArgumentException("Not able to find any mail.properties file or the file is empty");
		}
			
		session=Session.getDefaultInstance(properties, new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{ return new PasswordAuthentication(properties.getProperty("mail.smtp.user"), properties.getProperty("mail.smtp.password"));    }
			});
		if(properties.getProperty("debug.mode")!=null)
	    session.setDebug(Boolean.parseBoolean(properties.getProperty("debug.mode")));
		return session;
	}

	private boolean sendMail(MimeMessage mimeMessage)
			throws NoSuchProviderException {
		boolean sentMail = true;

		Transport transport = getMailSession().getTransport("smtps");
		try {
			transport.connect(properties.getProperty("mail.smtp.host"), 465,
					properties.getProperty("mail.smtp.user"), properties.getProperty("mail.smtp.password"));
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
		} catch (MessagingException e) {
			e.printStackTrace();
			sentMail = false;

		} finally {
			try {
				transport.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		return sentMail;
	}

}
