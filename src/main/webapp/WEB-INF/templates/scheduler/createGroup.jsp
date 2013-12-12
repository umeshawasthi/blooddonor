<%@ page pageEncoding="UTF-8" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:a action="createTriggersView">Schedule New Job</s:a> &nbsp; &nbsp; &nbsp;
<s:a action="createNewGroup">Create New Group</s:a>&nbsp; &nbsp; &nbsp;
     <br/><br/>
  <s:form action="createGroup">
   
   <s:if test="hasActionMessages()">
       <div>
       		<s:actionmessage cssStyle="color:red;"/>
       </div>
    </s:if>
    <table>
     <tr>
      <td><s:textfield name="schedulerGroupName" id="schedulerGroupName" label="Group Name:"/></td>
     </tr>
     <tr>
      <td><s:textarea name="schedulerGroupDescription" label="Group Description" id="schedulerGroupDescription" cols="20" rows="5">
        </s:textarea>
       </td>
     </tr>
    </table>
    <s:submit/>
  </s:form>
  <table>
   <thead>Groups</thead>
   <s:iterator value="groupModelsList" var="groupModel">
   	<tr>
    	<td>
           <s:property value="groupName"/>
     	</td>
     	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
    	<td>
    	 <s:property value="groupDescription"/>
    	</td>
   </tr>
   </s:iterator>
  </table>
