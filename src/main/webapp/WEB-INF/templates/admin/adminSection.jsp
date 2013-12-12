<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>

<div class="admin flW">
<s:include value="../layout/adminsectionnavigation.jsp"/>
  
  <div class="colR">
  	<div class="pageTitle">Approve Blood Requests</div>
    <div class="flW">
      <s:form action="verify-blood-requests" namespace="/request" name="verifyBloodRequest" validate="true" id="unVerifyBloodRequest" onsubmit="checkBoxValidate();">
        <table class="datatable" cellpadding="0" cellspacing="0" border="0" width="100%;">
          <tr>
            <th width="5%" align="left">&nbsp;</th>
            <th width="50%" align="left">Name</th>
            <th width="50%" align="center">Contact No</th>
            <th width="30%" align="left">District</th>
            <th width="50%" align="left">Blood Group</th>
            <th width="50%" align="left">Hospital Name</th>
          </tr>
          <s:set var="showApproveButton" value="false"/>
          <s:iterator var="bloodRequests" value="bloodRequests" status="indice">
            <s:set var="showApproveButton" value="true"/>
            <s:set name="bloodRequest" value="bloodRequests[#indice.index]"/>
            <s:if test="#indice.odd == true">
              <tr>
                <td align="left"><s:checkbox theme="simple" id="bloodRequest_id" name="bloodReqs" fieldValue="%{#bloodRequest.uuid}" onclick="$('#statusMsg').html('');" cssClass="required"/></td>
                <td align="left"><s:property value="%{#bloodRequest.name}" /></td>
                <td align="center"><s:property value="%{#bloodRequest.mobileNumber}" /></td>
                <td align="left"><s:property value="%{#bloodRequest.district}" /></td>
                <td align="left"><s:property value="%{#bloodRequest.bloodGroup}" /></td>
                <td align="left"><s:property value="%{#bloodRequest.hospitalName}" /></td>
              </tr>
            </s:if>
            <s:else>
              <tr>
                <td align="left"><s:checkbox theme="simple" name="bloodReqs" fieldValue="%{#bloodRequest.uuid}" onclick="$('#statusMsg').html('');" cssClass="required"/></td>
                <td align="left"><s:property value="%{#bloodRequest.name}" /></td>
                <td align="center"><s:property value="%{#bloodRequest.mobileNumber}" /></td>
                <td align="left"><s:property value="%{#bloodRequest.district}" /></td>
                <td align="left"><s:property value="%{#bloodRequest.bloodGroup}" /></td>
                <td align="left"><s:property value="%{#bloodRequest.hospitalName}" /></td>
              </tr>
            </s:else>
          </s:iterator>
        </table><br/>
       
       <s:if test="#showApproveButton == true">
       <div class="row">
				<div class="submitBtn">
	     <s:submit value="Approve Request" cssClass="button"/>
	</div></div>
	</s:if>
      </s:form>
    </div>
  </div>
</div>
<script>
	$(document).ready(function() {
		$("#unVerifyBloodRequest").validate({
			rules : {
			 bloodRequest_id: "required",
			//confirmPassword : {
			//equalTo : "#donorPassword"
			//}
			}
		});
	});
</script>
