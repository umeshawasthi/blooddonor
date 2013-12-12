<%@ page pageEncoding="UTF-8" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Job</title>
</head>
<body>
  <s:form action="createNewJob">
   <s:if test="hasActionMessages()">
       <div>
       		<s:actionmessage cssStyle="color:red;"/>
       </div>
    </s:if>
    <table>
     <tr>
      <td><s:textfield name="jobDTO.jobName" id="jobName" label="Job Name:"/></td>
     </tr>
     <tr>
      <td>
       <s:select list="%{groupModelsList}" listKey="groupName" listValue="groupName" cssStyle="width:150px;" name="jobDTO.jobGroupName" id="jobGroupName" label="Job Group" headerValue="Please select a  Group"/>
      </td>
     </tr>
     <tr>
      <td>
       <s:select list="%{jobClassName}"  cssStyle="width:150px;" name="jobDTO.jobClassName" id="jobClassName" label="Job Class" headerValue="Please select job class"/>
      </td>
     </tr>
     <tr>
      <td><s:textfield name="jobDTO.jobDetails" id="className" label="jobDetails"/></td>
     </tr>
     <tr>
      <td><s:textfield name="jobDTO.className" id="className" label="Class Name"/></td>
     </tr>
     
     <tr>
      <td><s:textfield name="jobDTO.methodName" id="methodName" label="Method Name"/></td>
     </tr>
    </table>
    <s:submit></s:submit>
  </s:form>
</body>
</html>