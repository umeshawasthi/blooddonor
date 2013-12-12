package com.raisonne.bd.util.mail;
/**
 * 
 */



import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/**
 * Mime message preparator interfce this is responsible for preparing 
 * mime message including attachment handlind
 * @author Umesh Awasthi
 * @since 03/11/2011
 * @version 1.0
 *
 */
public interface MimeMessagePreparator {

	/**
	 * 
	 * @param simpleMessage
	 * @param template
	 * @return mimeMessage
	 */
	public MimeMessage prepareMimeMessgae(SimpleMailMessage simpleMessage,String template,Session session, Map model) throws MessagingException;
	
	/**
	 * 
	 * @param simpleMessage
	 * @param attachments
	 * @param template
	 * @return {@link MimeMessage} with attachment
	 */
	public MimeMessage prepareMimeMessageWithAttachment(SimpleMailMessage simpleMessage,String[] attachments,String template,Session session, Map model) throws MessagingException;
}
