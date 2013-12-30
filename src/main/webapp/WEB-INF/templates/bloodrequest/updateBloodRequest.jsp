<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<style type="text/css">
label.error {
	float: none;
	color: red;
	padding-left: .5em;
	vertical-align: top;
}

.submit {
	margin-left: 12em;
}

em {
	color: red;
}
</style>
</head>

<div class="midCntCol2">
	<s:form action="update-blood-request" theme="simple" id="bloodrequest"
		cssClass="cmxform" namespace="/request" validate="true">
		<div class="dvMainForm">
			${message}
			<div class="row">
				<div class="col">


					<div class="inputLabel">
						Name<span class="asterisk">*</span>
					</div>
					<div class="inputField">
						<div class="flW">
							<s:textfield label="Name" name="bloodRequestDTO.name" id="name"
								cssClass="required textField" />
								<s:hidden name="bloodRequestDTO.uuid"></s:hidden>
						</div>
					</div>
				</div>
				<div class="col">


					<div class="inputLabel">
						Patient Name<span class="asterisk">*</span>
					</div>
					<div class="inputField">
						<div class="flW">
							<s:textfield label="Patient Name"
								name="bloodRequestDTO.patientName" id="name" cssClass="required textField" />
						</div>
					</div>
				</div>
				</div>
				<div class="row">
				<div class="col">
					<div class="inputLabel">
						Patient Age<span class="asterisk">*</span>
					</div>
					<div class="inputField">
						<div class="flW">
							<s:textfield label="Age" name="bloodRequestDTO.age" id="age"
								cssClass="required  textField" />
						</div>
					</div>
				</div>

				<div class="col">
					<div class="inputLabel">
						Mobile Number<span class="asterisk">*</span>
					</div>
					<div class="inputField">
						<div class="flW">
							<s:textfield label="Mobile Number"
								name="bloodRequestDTO.mobileNumber" id="mobilenumber"
								cssClass="required  textField" />
						</div>
					</div>
				</div>
				</div>
				<div class="row">
				<div class="col">
					<div class="inputLabel">
						Secondary Mobile Number<span class="asterisk">*</span>
					</div>
					<div class="inputField">
						<div class="flW">
							<s:textfield label="Secondary Mobile Number"
								name="bloodRequestDTO.secondaryContact" id="sMobileNumber"
								cssClass="required  textField ignore" />
						</div>
					</div>
				</div>

				<div class="col">
					<div class="inputLabel">
						Gender<span class="asterisk">*</span>
					</div>
					<div class="inputField">
						<div class="flW">
							<s:radio label="Gender" name="bloodRequestDTO.gender"
								list="#{'M':'Male','F':'Female'}" id="gender"
								value="%{bloodRequestDTO.gender}">
							</s:radio>
							<s:fielderror fieldName="bloodRequestDTO.gender"/>
						</div>
					</div>
				</div>
				</div>
				<div class="row">


				<div class="col">
					<div class="inputLabel">
						Blood group<span class="asterisk">*</span>
					</div>
					<div class="inputField">
						<div class="flW">
							<s:select list="bloodGroups" label="Blood Group"
								name="bloodRequestDTO.bloodGroup" id="bloodGroup"
								cssClass="required selectField"></s:select>
						</div>
					</div>
				</div>



				<div class="col">
					<div class="inputLabel">
						Units Needed<span class="asterisk">*</span>
					</div>
					<div class="inputField">
						<div class="flW">
							<s:textfield label="Units Needed"
								name="bloodRequestDTO.unitNeeded" id="unitsNeeded"
								cssClass="required  textField" />
						</div>
					</div>
				</div>

				</div>
				<div class="row">
				<div class="col">
					<div class="inputLabel">
						Hospital<span class="asterisk">*</span>
					</div>
					<div class="inputField">
						<div class="flW">
							<s:textfield label="Hospital" name="bloodRequestDTO.hospitalName"
								id="hospital" cssClass="required  textField" />
						</div>
					</div>
				</div>


				<div class="col">
					<div class="inputLabel">
						State<span class="asterisk">*</span>
					</div>
					<div class="inputField">
						<div class="flW">
							<s:select list="states" label="State"
								name="bloodRequestDTO.state" onchange="ajaxCallForDistrict();"
								id="stateList" cssClass="required selectField"></s:select>
						</div>
					</div>
				</div>
				</div>
				<div class="row">
				<div class="col">
					<div class="inputLabel">
						Districts<span class="asterisk">*</span>
					</div>
					<div class="inputField">
						<div class="flW">
							<s:select list="districts" label="District"
								name="bloodRequestDTO.district" id="district"
								cssClass="required selectField"></s:select>
						</div>
					</div>
				</div>



				<div class="col">
					<div class="inputLabel">
						Location<span class="asterisk">*</span>
					</div>
					<div class="inputField">
						<div class="flW">
							<s:textfield label="location" name="bloodRequestDTO.location"
								id="location" cssClass="required  textField" />
						</div>
					</div>
				</div>
				</div>


			<div class="row">
        <div class="col">
          <div class="inputLabel"> Postal Code<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="Pin Code" name="bloodRequestDTO.postalCode"
								id="pincode" cssClass="required  textField" />
              <s:fielderror fieldName="bloodRequestDTO.postalCode" />
            </div>
          </div>
        </div>
        <div class="col">
          <div class="inputLabel">Required Date (MM/DD/YY)<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW">
            <s:textfield cssClass="required  textField" name="bloodRequestDTO.requiredDate" id="requiredDate">
            
            </s:textfield>
             <s:fielderror fieldName="bloodRequestDTO.requiredDate" />
            
            </div>
          </div>
        </div>
      </div>
      
      <div class="row">
    
        <div class="col">
          <div class="inputLabel">Purpose<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW">
            <s:textarea cssClass="required  textField" name="bloodRequestDTO.purpose" id="lastDonationDate">
            
            </s:textarea>
             <s:fielderror fieldName="bloodRequestDTO.lastDonationDate" />
            </div>
          </div>
        </div>
      </div>
			</div>
			<div class="row">
				<div class="submitBtn">
					<s:submit name="Bloodrequest" cssClass="button" value="Update Request"></s:submit>
				</div>
			</div>
		
	</s:form>
</div>


<script type="text/javascript" src="${webRoot}/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="${webRoot}/jquery/jquery.ui.core.js"></script>

<script type="text/javascript"
	src="${webRoot}/jquery/jquery.ui.datepicker.js"></script>
<script type="text/javascript"
	src="${webRoot}/jquery/jquery.validate.js"></script>


<script>
	$(document).ready(function() {
		$("#bloodrequest").validate({
			ignore: ".ignore"
		});
	});
	
	$(function() {
		$( "#requiredDate" ).datepicker({
			yearRange: 'c:c+1',
			changeMonth: true,
			changeYear: true,
			
		});
	});
</script>
