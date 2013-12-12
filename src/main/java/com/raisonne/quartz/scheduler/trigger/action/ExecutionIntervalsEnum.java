/**
 * 
 */
package com.raisonne.quartz.scheduler.trigger.action;

/**
 * @author raisonne
 *
 */
public enum ExecutionIntervalsEnum {
	
	DAILY(1),
	WEEKLY(2),
	MONTHLY(3),
	ONCE(4),
	CRONEXPRESSION(5);
	
	private final int executionIntervals;
	
	ExecutionIntervalsEnum(int intervals){
		this.executionIntervals=intervals;
	}

	public int getExecutionIntervals() {
		return executionIntervals;
	}
	
	

}
