package com.raisonne.bd.action.bloodrequest;



import java.util.List;

import net.sf.oval.constraint.AssertValid;

import com.raisonne.bd.action.donor.BaseAction;
import com.raisonne.bd.dto.bloodrequest.BloodRequestDTO;
import com.raisonne.bd.service.bloodrequest.BloodRequestService;
/**
 * 
 * @author Himanshu Durgapal
 *
 */
public class BloodRequestVerification extends BaseAction{

	
	private static final long serialVersionUID = 1L;
	/**
	 * class level property declarations.
	 */
	private BloodRequestService bloodRequestService;
	private String workingTemplate;
	@AssertValid
	private List<BloodRequestDTO> bloodRequests;
	private List<String> bloodReqs;
	/**
	 * This method is implemented to load unverified blood requests.
	 * @return Returns string result based on result of action performed.
	 * @throws Exception
	 */
	public String loadUnverifiedBloodRequests() throws Exception
	{
		bloodRequests=bloodRequestService.loadUnVerifiedBloodRequests();
		setHideLeftPannel(true);
		setHideRightPannel(true);
		setWorkingTemplate("/WEB-INF/templates/bloodrequest/verifiedBloodRequests.jsp");
		return SUCCESS;
	}
	/**
	 * This method is implemented to load verified blood requests.
	 * @return Returns string result based on result of action performed.
	 * @throws Exception
	 */
	public String loadVerifiedBloodRequests() throws Exception
	{
		bloodRequests=bloodRequestService.loadVerifiedBloodRequests();
		return SUCCESS;
	}
	/**
	 * This method is implemented to load both unverified and unverified blood requests in a single page  .
	 * @return Returns string result based on result of action performed.
	 * @throws Exception
	 */
	public String loadBloodRequests() throws Exception
	{
		setWorkingTemplate("/WEB-INF/templates/bloodrequest/bloodrequestComponent.jsp");
		return SUCCESS;
	}
	/**
	 * This method is implemented to change the verification status of blood requests based on the id's of blood request.
	 * @return Returns string result based on result of action performed.
	 * @throws Exception
	 */
	public String verifyBloodRequest() throws Exception
	{
		//bloodRequestDTO.set
		for(String  bloodRequests :bloodReqs )
		{
			BloodRequestDTO bloodReqDto=new BloodRequestDTO();
			bloodReqDto.setUuid(Integer.parseInt(bloodRequests));
			bloodReqDto=bloodRequestService.loadBloodRequestById(bloodReqDto);
			bloodReqDto.setVerificationFlag(true);
			BloodRequestDTO bloodRequestDTO= bloodRequestService.updateBloodRequest(bloodReqDto);
			updateBloodRequestCache(bloodRequestDTO);
		}
		
		setWorkingTemplate("/WEB-INF/templates/admin/adminSection.jsp");
		setHideLeftPannel(true);
		setHideRightPannel(true);
		return SUCCESS;
	}
	
	/**
	 *s method is implemented to change the verification status of blood requests based on the id's of blood request.
	 * @return Returns string result based on result of action performed.
	 * @throws Exception
	 */
	public String updateVerifiedRequests() throws Exception
	{
		//bloodRequestDTO.set
		for(String  bloodRequests :bloodReqs )
		{
			BloodRequestDTO bloodReqDto=new BloodRequestDTO();
			bloodReqDto.setUuid(Integer.parseInt(bloodRequests));
			bloodReqDto=bloodRequestService.loadBloodRequestById(bloodReqDto);
			bloodReqDto.setVerificationFlag(false);
			bloodRequestService.updateBloodRequest(bloodReqDto);
		}		
		setWorkingTemplate("/WEB-INF/templates/bloodrequest/bloodrequestComponent.jsp");
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
	public String getWorkingTemplate() {
		return workingTemplate;
	}
	public void setWorkingTemplate(String workingTemplate) {
		this.workingTemplate = workingTemplate;
	}
	public void setBloodRequests(List<BloodRequestDTO> bloodRequests) {
		this.bloodRequests = bloodRequests;
	}
	public List<String> getBloodReqs() {
		return bloodReqs;
	}
	public void setBloodReqs(List<String> bloodReqs) {
		this.bloodReqs = bloodReqs;
	}

}
