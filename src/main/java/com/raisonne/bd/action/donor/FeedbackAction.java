package com.raisonne.bd.action.donor;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletContext;

import net.sf.oval.constraint.AssertValid;

import org.apache.struts2.util.ServletContextAware;

import com.raisonne.bd.dto.donor.FeedbackDTO;
import com.raisonne.bd.service.donor.DonorService;
import com.raisonne.bd.util.PropertyLoader;
import com.raisonne.bd.util.mail.MailSender;
import com.raisonne.bd.util.mail.Sender;
import com.raisonne.bd.util.mail.SimpleMailMessage;

public class FeedbackAction extends BaseAction implements ServletContextAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DonorService donorService;
	@AssertValid
	private FeedbackDTO donorFeedbackDTO;
	private List<FeedbackDTO> feedbackList;
	private MailSender mailSender;
	private SimpleMailMessage simpleMailMessage;
	private Sender sender;
	private ServletContext context;
	private String message = "";
	private String uuid;
	private boolean verify;
	/**
	 * This variable will be used to set working template.
	 * Developer need to set working template here,application will pick it
	 * as per the given structure.
	 */
	private String workingTemplate=null;
	
	public String loadAllFeedbacks() throws Exception
	{
		boolean verify=true;
		setFeedbackList(donorService.loadAllFeedbacks(verify));
		setTitle("Save A Life Today| Provide your Feedback");
		setWorkingTemplate("/WEB-INF/templates/donor/donorFeedback.jsp");
		return SUCCESS;
	}
	
	public String loadUnverifiedFeedbacks() throws Exception
	{
		boolean verify=false;
		setFeedbackList(donorService.loadAllFeedbacks(verify));
		feedbackList.addAll(donorService.loadAllFeedbacks(!verify));
		setHideLeftPannel(true);
		setHideRightPannel(true);
		setWorkingTemplate("/WEB-INF/templates/donor/verifyDonorFeedback.jsp");
		return SUCCESS;
	}
	public String verifyFeedbacks() throws Exception
	{
		if(donorService.verifyFeedbacks(uuid,verify))
		{
			if(verify==true)
				verify=false;
			setFeedbackList(donorService.loadAllFeedbacks(verify));
			feedbackList.addAll(donorService.loadAllFeedbacks(!verify));
			setTitle("Save A Life Today| Feedback");
			setWorkingTemplate("/WEB-INF/templates/donor/verifyDonorFeedback.jsp");
		}
		else
		{
			setWorkingTemplate("/WEB-INF/templates/donor/verifyDonorFeedback.jsp");
			setTitle("Save A Life Today| Feedback");
		}
		setHideLeftPannel(true);
		setHideRightPannel(true);
		return SUCCESS;
	}
	@Override
	public String execute() throws Exception {

		Properties properties = PropertyLoader
				.loadProperties("mail.properties");
		sender.setFrom(donorFeedbackDTO.getEmail());
		sender.setName(donorFeedbackDTO.getDonorName());
		simpleMailMessage.setFrom(sender);
		simpleMailMessage.setSubject(properties.getProperty("mail.feedbackSubject"));
		simpleMailMessage.setTo(properties.getProperty("mail.feedback.toMail"));
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("donorFeedbackDTO", donorFeedbackDTO);
		model.put("servletContext", context);

		try {
			donorService.saveFeedback(donorFeedbackDTO);
		boolean sentStatus=	mailSender.send(simpleMailMessage,
					properties.getProperty("mail.template.feedback"),
					model);
		boolean verify=true;
		setFeedbackList(donorService.loadAllFeedbacks(verify));
		if(sentStatus)
		{
			message = "Your feedback has been sent successfully to our team ,we are very gratefull to you!";
			
			setTitle("Save A Life Today|Donor Feedback");
			setWorkingTemplate("/WEB-INF/templates/donor/donorFeedback.jsp");
		}
		else
		{
			message = "Some network problem ,Please try again.";
			setTitle("Save A Life Today|Donor Feedback");
			setWorkingTemplate("/WEB-INF/templates/donor/donorFeedback.jsp");
		}
			
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
		
		return SUCCESS;
	}
	
	public FeedbackDTO getDonorFeedbackDTO() {
		return donorFeedbackDTO;
	}

	public void setDonorFeedbackDTO(FeedbackDTO donorFeedbackDTO) {
		this.donorFeedbackDTO = donorFeedbackDTO;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
		
	}

	public String getWorkingTemplate() {
		return workingTemplate;
	}

	public void setWorkingTemplate(String workingTemplate) {
		this.workingTemplate = workingTemplate;
	}

	public DonorService getDonorService() {
		return donorService;
	}

	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}

	public List<FeedbackDTO> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<FeedbackDTO> feedbackList) {
		this.feedbackList = feedbackList;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public boolean isVerify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	
}
