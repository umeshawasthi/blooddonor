package com.raisonne.bd.action.donor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;
import com.raisonne.bd.paging.PagingObject;
import com.raisonne.bd.service.donor.DonorService;
import com.raisonne.bd.util.BloodGroupUtils;
import com.raisonne.bd.util.StateInfoUtils;

/**
 * This action class is written to handle all the donor search related requests.
 * It makes use of respected service api for performing operations.
 * 
 * @author Arun
 * 
 */
public class DonorSearch extends BaseAction {

	private static final long serialVersionUID = 1L;
	Logger log = Logger.getLogger(DonorSearch.class);
	DonorService donorService;
	private int pageSize = 0;
	private long totalCount = 0;
	private boolean criteriaChanged=false;
	private int p=1;
	private Map<String, String> states;
	private List<String> bloodGroups;
	private List<String> districts;
	private String state;
	private String selectedDonorsIds;
	private List<String> selectedDonorsIdList;
	private String bloodGroup;
	private String district;
	private String postalCode;
	private int error=0;
    private PagingObject pagingObject; 
	private List donorsInfo= null;
	
	/**
	 * This method is implemented to retrieve donor profiles based on blood
	 * group, state and city of donors.
	 * 
	 * @return Returns string result based on result of action performed.
	 */
	public String getDonorsByArea() {
		initDonorSearch();
		
		try {
			 if(pagingObject==null){
				 pagingObject=new PagingObject();
			 }
			 pagingObject.setPage_number(p);
			 if(pageSize!=0 && pagingObject.getRecord_size()!=pageSize){
				 pagingObject.setRecord_size(pageSize);
			 }
			 Map<String,String> searchCriteria=new HashMap<String, String>();
			 searchCriteria.put("blood_group", bloodGroup);
			 searchCriteria.put("state", state);
			 searchCriteria.put("district", district);
			 if(totalCount==0 || criteriaChanged==true)
			     totalCount = donorService.loadDonorProfilesCount(searchCriteria,"area");
			 if(totalCount>0){
				pagingObject.setTotalProfiles(totalCount) ;
			    
			 }
			 
			 donorsInfo=donorService.loadDonorsInfoByArea(bloodGroup, state, district, pagingObject.getLimits(), pagingObject.getRecord_size());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			error=1;
			return INPUT;
		}
		if(donorsInfo == null || donorsInfo.size() == 0)
			error=1;
		
		setWorkingTemplate("/WEB-INF/templates/donorsearch/searchDonorByArea.jsp");
		setTitle("Save A Life Today| Search Donor By Area");
		if(selectedDonorsIds!=null )
		   setSelectedDonorsIdList(Arrays.asList(selectedDonorsIds.split(",")));
		return SUCCESS;
	}

	/**
	 * This method is implemented to retrieve donor profiles based on blood
	 * group and postal code of donors.
	 * 
	 * @return Returns string result based on result of action performed.
	 */
	public String getDonorsByPostalCode() {
		initDonorSearch();
		try {
			
			 if(pagingObject==null){
				 pagingObject=new PagingObject();
			 }
			 pagingObject.setPage_number(p);
			 if(pageSize!=0 && pagingObject.getRecord_size()!=pageSize){
				 pagingObject.setRecord_size(pageSize);
			 }
			 Map<String,String> searchCriteria=new HashMap<String, String>();
			 searchCriteria.put("blood_group", bloodGroup);
			 searchCriteria.put("postalcode", postalCode);
			 if(totalCount==0 || criteriaChanged==true)
			     totalCount = donorService.loadDonorProfilesCount(searchCriteria,"postal");
			 if(totalCount>0){
				pagingObject.setTotalProfiles(totalCount) ;
			    
			 }
			 
			 donorsInfo=donorService.loadDonorsInfoByPostalCode(bloodGroup, postalCode, pagingObject.getLimits(), pagingObject.getRecord_size());
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			error=1;
			return INPUT;
		}
		if(donorsInfo == null || donorsInfo.size() == 0)
			error=1;
		  log.info("Setting working template as searchDonorByPostalCode.jsp");
		  setTitle("Save A Life Today| Search Donor By Postal Code");
		  setWorkingTemplate("/WEB-INF/templates/donorsearch/searchDonorByPostalCode.jsp");
		  if(selectedDonorsIds!=null )
			   setSelectedDonorsIdList(Arrays.asList(selectedDonorsIds.split(",")));
		  return SUCCESS;
	}

	public String initDonorSearch() {
		log.info("Starting to populate lists");
		bloodGroups = BloodGroupUtils.getAvailableBloodGroups();
		
		states = StateInfoUtils.getStates();
		if (state == null) {
			districts = new ArrayList<String>();
		} else {
			districts = StateInfoUtils.getDistricts().get(state);
		}
		return SUCCESS;
	}

	public DonorService getDonorService() {
		return donorService;
	}

	public void setDonorService(DonorService donorService) {
		this.donorService = donorService;
	}


	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public Map<String, String> getStates() {
		return states;
	}

	public void setStates(Map<String, String> states) {
		this.states = states;
	}

	public List<String> getBloodGroups() {
		return bloodGroups;
	}

	public void setBloodGroups(List<String> bloodGroups) {
		this.bloodGroups = bloodGroups;
	}

	public List<String> getDistricts() {
		return districts;
	}

	public void setDistricts(List<String> districts) {
		this.districts = districts;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public int getError() {
		return error;
	}

	public void setError(int error) {
		this.error = error;
	}

	public List getDonorsInfo() {
		return donorsInfo;
	}

	public void setDonorsInfo(List donorsInfo) {
		this.donorsInfo = donorsInfo;
	}

	public PagingObject getPagingObject() {
		return pagingObject;
	}

	public void setPagingObject(PagingObject pagingObject) {
		this.pagingObject = pagingObject;
	}

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public boolean isCriteriaChanged() {
		return criteriaChanged;
	}

	public void setCriteriaChanged(boolean criteriaChanged) {
		this.criteriaChanged = criteriaChanged;
	}

	private String workingTemplate=null;
	public String getWorkingTemplate() {
		return workingTemplate;
	}
	public void setWorkingTemplate(String workingTemplate) {
		this.workingTemplate = workingTemplate;
	}

	public String getSelectedDonorsIds() {
		return selectedDonorsIds;
	}

	public void setSelectedDonorsIds(String selectedDonorsIds) {
		this.selectedDonorsIds = selectedDonorsIds;
	}

	public List<String> getSelectedDonorsIdList() {
		return selectedDonorsIdList;
	}

	public void setSelectedDonorsIdList(List<String> selectedDonorsIdList) {
		this.selectedDonorsIdList = selectedDonorsIdList;
	}

	

	
	
	

}
