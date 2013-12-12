/**
 * 
 */

package com.raisonne.bd.util.mail;
import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.mail.MessagingException;

/**
 * central interface for sending emails called <code>MailSender</code> and the value object which encapsulates
 * properties of a simple mail such as from, to, cc, subject, text called <code>MailMessage</code><p>
 * 
 * @author Umesh Awasthi
 * @version 1.1 03/11/2011
 * @since 1.1
 * 
 */
public interface MailSender {
	/**
     * Send the given simple mail message.
     * @param simpleMessage message to send
     * @param template template used for sending HTML contents
     * @throws MessagingException,UnsupportedEncodingException
     * @return flag indicating if mail sent is successfull or not
     * 
     */
    public boolean send(SimpleMailMessage simpleMessage,String template, Map model) throws MessagingException,UnsupportedEncodingException;
    
    /**
     * Send the given simple mail message.
     * @param simpleMessage message to send
     * @param attachments path of the attachemnt to be send with mail
     * @param template template used for sending HTML contents
     * @throws MessagingException,UnsupportedEncodingException
     * @return flag indicating if mail sent is successfull or not
     */
    public boolean sendAttachment(SimpleMailMessage simpleMessage,String[] attachments,String template, Map model) throws MessagingException,UnsupportedEncodingException;
	

}
