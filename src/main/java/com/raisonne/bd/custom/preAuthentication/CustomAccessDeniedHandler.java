package com.raisonne.bd.custom.preAuthentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.opensymphony.xwork2.ActionSupport;
import com.raisonne.bd.action.donor.BaseAction;

public class CustomAccessDeniedHandler extends BaseAction implements AccessDeniedHandler {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String accessDeniedUrl;
	private String workingTemplate;
	@Override
	public void handle(HttpServletRequest request,
			HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException,
			ServletException {
		response.sendRedirect(accessDeniedUrl);
		request.getSession().setAttribute("accessDeniedMsg", "Dear visitor, you do not have permission to access this page! Please contact to your administrator ");
		
	}
	
	public String accessDeniedPage()
	{
		this.workingTemplate = "/WEB-INF/templates/error/exception_403.jsp";
		return ActionSupport.SUCCESS;
	}
	
	public String getAccessDeniedUrl() {
		return accessDeniedUrl;
	}
	public void setAccessDeniedUrl(String accessDeniedUrl) {
		this.accessDeniedUrl = accessDeniedUrl;
	}

	public String getWorkingTemplate() {
		return workingTemplate;
	}

	public void setWorkingTemplate(String workingTemplate) {
		this.workingTemplate = workingTemplate;
	}

	
}
