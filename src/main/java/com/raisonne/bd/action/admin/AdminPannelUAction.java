/**
 * 
 */
package com.raisonne.bd.action.admin;

import java.util.List;

import org.apache.log4j.Logger;

import com.raisonne.bd.action.donor.BaseAction;
import com.raisonne.bd.dto.bloodrequest.BloodRequestDTO;
import com.raisonne.bd.service.bloodrequest.BloodRequestService;

/**
 * @author Umesh A
 * <p>This action class is responsible for showing the admin panel.it will be responsible to show 
 * following section to Admin user</p>
 * <li>Approve Feedback Section </li>
 * <li>Approve Blood Request</li>
 * <li>Creating and Managing schedulers </li>
 */
public class AdminPannelUAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Logger log=Logger.getLogger(AdminPannelUAction.class);
	private BloodRequestService bloodRequestService;
	private List<BloodRequestDTO> bloodRequests;
	
	
	/**
	 * This method is responsible to show the admin section 
	 * to user.only user with admin rights will able to call this method
	 * @return control String to show view
	 * @throws Exception
	 */
	public String showAdminPanel() throws Exception{
		log.info("Setting working tempelate: /WEB-INF/templates/admin/adminSection.jsp");
		bloodRequests=bloodRequestService.loadUnVerifiedBloodRequests();
		setWorkingTemplate("/WEB-INF/templates/admin/adminSection.jsp");
		setHideLeftPannel(true);
		setHideRightPannel(true);
		return SUCCESS;
		
	}
	
	
	public BloodRequestService getBloodRequestService() {
		return bloodRequestService;
	}
	public void setBloodRequestService(BloodRequestService bloodRequestService) {
		this.bloodRequestService = bloodRequestService;
	}
	
	public List<BloodRequestDTO> getBloodRequests() {
		return bloodRequests;
	}
	
	public void setBloodRequests(List<BloodRequestDTO> bloodRequests) {
		this.bloodRequests = bloodRequests;
	}
}
