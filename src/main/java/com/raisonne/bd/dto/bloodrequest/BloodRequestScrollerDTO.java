/**
 * 
 */
package com.raisonne.bd.dto.bloodrequest;

/**
 * @author Umesh A
 * 
 */
public class BloodRequestScrollerDTO {

	private int uuid;
	private String bloodGroup;
	private String state;
	private String location;
	private String contactNumber;
	
	
	

	public BloodRequestScrollerDTO() {
		super();
		
	}

	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	
	

}
