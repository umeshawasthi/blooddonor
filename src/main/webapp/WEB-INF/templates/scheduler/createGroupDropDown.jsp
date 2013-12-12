<%@ page pageEncoding="UTF-8" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:select list="%{groupModelsList}" listKey="groupName" listValue="groupName" 
cssStyle="width:150px;" name="cronTriggerDTO.triggerGroup" id="triggerGroupName" 
label="Trigger Group" headerKey="-1" headerValue="Please select a  Group" onchange="getJobsForGroup('triggerjobName','triggerGroupName')"/>