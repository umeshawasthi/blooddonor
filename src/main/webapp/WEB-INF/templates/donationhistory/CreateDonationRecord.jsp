<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.custom.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.validate.js"></script>
<script>
$(document).ready(function() {
	$("#donationRecordForm").validate();
});
	$(function() {
		$( "#bloodDonationdate" ).datepicker();
	});

</script>

<%@include file="DonationHistoryOptions.jsp" %>
<div class="fcTabsContent">
  <div class="fcTabsContentInr">
    <div class="flW" id="validatorDiv">
      <s:form action="save-donation-record" theme="simple" id="donationRecordForm" cssClass="cmxform" namespace="/account/donationhistory">
        <%-- <s:hidden name="bloodDonationRecordDTO.blood_donationrecord_uuid"></s:hidden>
		<s:hidden name="bloodDonationRecordDTO.donor_id" value="40288183341373cf01341379b1810000"></s:hidden> --%>
        <div class="dvMainForm">
          <div class="row">
            <div class="inputLabel">Patient name<span class="asterisk">*</span></div>
            <div class="inputField">
              <div class="flW">
                <s:textfield label="PatientName" name="bloodDonationRecordDTO.patientName"
							id="patientName" cssClass="textField required"/>
				<s:fielderror fieldName="bloodDonationRecordDTO.patientName" />
              </div>
            </div>
          </div>
          <div class="row">
            <div class="inputLabel">Place<span class="asterisk">*</span></div>
            <div class="inputField">
              <div class="flW">
                <s:textfield label="DonationPlace"
							name="bloodDonationRecordDTO.donationPlace" id="donationPlace" cssClass="textField required" />
							<s:fielderror fieldName="bloodDonationRecordDTO.donationPlace" />
              </div>
            </div>
          </div>
          <div class="row">
            <div class="inputLabel">Hospital<span class="asterisk">*</span></div>
            <div class="inputField">
              <div class="flW">
                <s:textfield label="HospitalName" name="bloodDonationRecordDTO.hospitalName"
							id="hospitalName" cssClass="textField required" />
								<s:fielderror fieldName="bloodDonationRecordDTO.hospitalName" />
              </div>
            </div>
          </div>
          <div class="row">
            <div class="inputLabel">Donation date<span class="asterisk">*</span></div>
            <div class="inputField">
              <div class="flW">
                <s:textfield label="BloodDonationdate"
							name="bloodDonationRecordDTO.bloodDonationdate" id="bloodDonationdate"
							cssClass="textField required" />
							<s:fielderror fieldName="bloodDonationRecordDTO.bloodDonationdate" />
              </div>
            </div>
          </div>
          <div class="row">
            <div class="inputLabel">&nbsp;</div>
            <div class="inputField">
              <div class="flW">
                <s:submit cssClass="button" name="donationRecordForm"></s:submit>
              </div>
            </div>
          </div>
        </div>
      </s:form>
    </div>
    <div class="clearfix"></div>
  </div>
</div>
</div>

