package com.raisonne.bd.action.donor;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;

import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.raisonne.bd.dto.donor.DonorProfileDTO;
import com.raisonne.bd.service.donor.DonorService;
import com.raisonne.bd.util.BloodGroupUtils;
import com.raisonne.bd.util.PropertyLoader;
import com.raisonne.bd.util.mail.MailSender;
import com.raisonne.bd.util.mail.Sender;
import com.raisonne.bd.util.mail.SimpleMailMessage;

public class MailDonorInfo extends ActionSupport implements ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MailSender mailSender;
	private List<String> donorIDs;
	private DonorService donorService;
	private String toMail;
	private SimpleMailMessage simpleMailMessage;
	private Sender sender;
	private ServletContext context;
	private String message = "Sending Failed";

	@Override
	public String execute() throws Exception {
		List<DonorProfileDTO> donorProfiles = null;
		try {
			donorProfiles = donorService.loadDonorProfilesForIds(donorIDs);
		} catch (Exception e) {
			e.printStackTrace();
			return SUCCESS;
		}
		Properties properties = PropertyLoader
				.loadProperties("mail.properties");
		sender.setFrom(properties.getProperty("mail.fromEmail"));
		sender.setName(properties.getProperty("mail.fromEmail.name"));
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setSubject(properties.getProperty("mail.subject"));
		simpleMailMessage.setTo(toMail);
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("donorInfos", donorProfiles);
		model.put("servletContext", context);
		model.put("site_url", BloodGroupUtils.getProjectProperties("website.bloodddonor.http"));

		try {
			/*String logoPath = context.getRealPath(java.io.File.separator
					.concat("images").concat(java.io.File.separator)
					.concat("logo.jpg"));*/

			
			mailSender.send(simpleMailMessage, properties.getProperty("mail.template.donorsInformation"), model);
			/*mailSender.sendAttachment(simpleMailMessage,
					new String[] {""},
					properties.getProperty("mail.template.donorsInformation"),
					model);*/
			List<String> donorIDs = new ArrayList<String>();
			for (DonorProfileDTO donorProfile : donorProfiles) {
				donorIDs.add(donorProfile.getDonor_uuid());
			}
			donorService.updateLastEmailDate(donorIDs, new Date());

		}

		catch (AddressException addressException) {
			addressException.printStackTrace();
			return SUCCESS;
		} catch (UnsupportedEncodingException unsupportedEncodingException) {
			unsupportedEncodingException.printStackTrace();
			return SUCCESS;
		} catch (MessagingException messagingException) {
			messagingException.printStackTrace();
			return SUCCESS;
		} catch (Exception exception) {
			exception.printStackTrace();
			return SUCCESS;
		}
		message = "Mail sent successfully";
		return SUCCESS;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public List<String> getDonorIDs() {
		return donorIDs;
	}

	public void setDonorIDs(List<String> donorIDs) {
		this.donorIDs = donorIDs;
	}

	public DonorService getDonorService() {
		return donorService;
	}

	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		this.toMail = toMail;
	}

	public SimpleMailMessage getSimpleMailMessage() {
		return simpleMailMessage;
	}

	public void setSimpleMailMessage(SimpleMailMessage simpleMailMessage) {
		this.simpleMailMessage = simpleMailMessage;
	}

	public Sender getSender() {
		return sender;
	}

	public void setSender(Sender sender) {
		this.sender = sender;
	}

	@Override
	public void setServletContext(ServletContext context) {
		// TODO Auto-generated method stub
		this.context = context;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
