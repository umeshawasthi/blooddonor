<%@taglib prefix="s" uri="/struts-tags"%>



<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/jquery.ui.core.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/jquery.custom.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/jquery/jquery.validate.js"></script>

<script>
$(document).ready(function() {
	$("#donationRecordUpdateForm").validate();
});
	
$(function() {
	$( "#bloodDonationdate").datepicker({
		yearRange: 'c-100:c+100',
		changeMonth: true,
		changeYear: true
	});
});



</script>

<%@include file="DonationHistoryOptions.jsp"%><br />
<br />
<div class="fcTabsContent">
	<div class="fcTabsContentInr">
		<div class="flW" id="validatorDiv">
			<s:form action="update-donation-record" theme="simple"
				id="donationRecordUpdateForm" cssClass="cmxform"
				namespace="/account/donationhistory">
				<div class="dvMainForm">
					<div class="row">
						<div class="inputLabel">
							Patient name<span class="asterisk">*</span>
						</div>
						<div class="inputField">
							<div class="flW">
								<s:textfield label="PatientName"
									name="bloodDonationRecordDTO.patientName" id="patientName"
									cssClass="textField required" />
								<s:fielderror fieldName="bloodDonationRecordDTO.patientName" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="inputLabel">
							Place<span class="asterisk">*</span>
						</div>
						<div class="inputField">
							<div class="flW">
								<s:textfield label="DonationPlace"
									name="bloodDonationRecordDTO.donationPlace" id="donationPlace"
									cssClass="textField required" />
								<s:fielderror fieldName="bloodDonationRecordDTO.donationPlace" />
							</div>
						</div>
					</div>
					<div class="row">
						<div class="inputLabel">
							Hospital<span class="asterisk">*</span>
						</div>
						<div class="inputField">
							<div class="flW">
								<s:textfield label="HospitalName"
									name="bloodDonationRecordDTO.hospitalName" id="hospitalName"
									cssClass="textField required" />
								<s:fielderror fieldName="bloodDonationRecordDTO.hospitalName" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="inputLabel">
							Donation date<span class="asterisk">*</span>
						</div>
						<div class="inputField">
							<div class="flW">
								<s:date name="bloodDonationRecordDTO.tempBloodDonationDate"
									var="formattedBR" format="MM/dd/yyyy" />

								<s:textfield label="BloodDonationdate"
									name="bloodDonationRecordDTO.bloodDonationdate"
									id="bloodDonationdate" cssClass="textField required"
									value="%{formattedBR}" />

								<s:fielderror
									fieldName="bloodDonationRecordDTO.bloodDonationdate" />
							</div>
						</div>
					</div>

					<div class="row">
						<div class="inputLabel">&nbsp;</div>
						<div class="inputField">
							<div class="flW">
								<s:submit cssClass="button" name="donationRecordForm" value="update"></s:submit>
							</div>
						</div>
					</div>
				</div>

				
				<div id="failDiv"></div>

				<s:hidden name="bloodDonationRecordDTO.blood_donationrecord_uuid" />
				<s:hidden name="bloodDonationRecordDTO.donor_id" />
			</s:form>
		</div>
		<div class="clearfix"></div>
	</div>
</div>