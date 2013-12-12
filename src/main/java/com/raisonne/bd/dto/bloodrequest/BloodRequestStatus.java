/**
 * 
 */
package com.raisonne.bd.dto.bloodrequest;

/**
 * @author Umesh A
 *
 */
public class BloodRequestStatus {

	private boolean result;
	private int refrence_id;
	
	public BloodRequestStatus() {
		super();
		
	}
	
	

	public BloodRequestStatus(boolean result, int refrence_id) {
		super();
		this.result = result;
		this.refrence_id = refrence_id;
	}



	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}



	public int getRefrence_id() {
		return refrence_id;
	}



	public void setRefrence_id(int refrence_id) {
		this.refrence_id = refrence_id;
	}

	
	
	
}
