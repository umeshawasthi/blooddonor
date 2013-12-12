<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="midCntCol2">
  <div class="pageTitle">Post Blood Request</div>
  <s:form action="save-blood-request" theme="simple" id="bloodrequest"
		cssClass="cmxform" namespace="/request" validate="true">
    <div class="dvMainForm"> ${message}
      <div class="row">
        <div class="col">
          <div class="inputLabel"> Name<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="Name" name="bloodRequestDTO.name" id="name"
								cssClass="required textField" />
              <s:fielderror fieldName="bloodRequestDTO.name" />
            </div>
          </div>
        </div>
        <div class="col">
          <div class="inputLabel"> Patient Name<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="Patient Name"
								name="bloodRequestDTO.patientName" id="name"
								cssClass="required textField" />
              <s:fielderror fieldName="bloodRequestDTO.patientName" />
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="inputLabel"> Patient Age<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="Age" name="bloodRequestDTO.age" id="age"
								cssClass="required  textField" />
              <s:fielderror fieldName="bloodRequestDTO.age" />
            </div>
          </div>
        </div>
        <div class="col">
          <div class="inputLabel"> Mobile Number<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="Mobile Number"
								name="bloodRequestDTO.mobileNumber" id="mobilenumber"
								cssClass="required  textField" />
              <s:fielderror fieldName="bloodRequestDTO.mobileNumber" />
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="inputLabel"> Secondary Mobile Number<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="Secondary Mobile Number"
								name="bloodRequestDTO.secondaryContact" id="sMobileNumber"
								cssClass="required  textField" />
              <s:fielderror fieldName="bloodRequestDTO.secondaryContact" />
            </div>
          </div>
        </div>
        <div class="col">
          <div class="inputLabel"> Gender<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW rdo">
              <s:radio label="Gender" name="bloodRequestDTO.gender"
								list="#{'Male':'Male','Female':'Female'}" id="gender"
								cssClass="required"> </s:radio>
            
            </div>
              <s:fielderror fieldName="bloodRequestDTO.gender" theme="simple"/>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="inputLabel"> Blood group<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW">
              <s:select list="bloodGroups" label="Blood Group"
								name="bloodRequestDTO.bloodGroup" id="bloodGroup"
								cssClass="required selectField"></s:select>
              <s:fielderror fieldName="bloodRequestDTO.bloodGroup" />
            </div>
          </div>
        </div>
        <div class="col">
          <div class="inputLabel"> Units Needed<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="Units Needed"
								name="bloodRequestDTO.unitNeeded" id="unitsNeeded"
								cssClass="required  textField" />
              <s:fielderror fieldName="bloodRequestDTO.unitNeeded" />
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="inputLabel"> Hospital<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="Hospital" name="bloodRequestDTO.hospitalName"
								id="hospital" cssClass="required  textField" />
              <s:fielderror fieldName="bloodRequestDTO.hospitalName" />
            </div>
          </div>
        </div>
        <div class="col">
          <div class="inputLabel"> State<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW">
              <s:select list="states" label="State"
								name="bloodRequestDTO.state" onchange="ajaxCallForDistrict();"
								id="stateList" cssClass="required selectField"></s:select>
              <s:fielderror fieldName="bloodRequestDTO.state" />
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="inputLabel"> Districts<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW">
              <s:select list="districts" label="District"
								name="bloodRequestDTO.district" id="district"
								cssClass="required selectField"></s:select>
              <s:fielderror fieldName="bloodRequestDTO.district" />
            </div>
          </div>
        </div>
        <div class="col">
          <div class="inputLabel"> Location<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="location" name="bloodRequestDTO.location"
								id="location" cssClass="required  textField" />
              <s:fielderror fieldName="bloodRequestDTO.location" />
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
          <div class="inputLabel">Purpose<span class="asterisk">*</span> </div>
          <div class="inputField">
            <div class="flW">
            <s:textarea cssClass="required  textField" name="bloodRequestDTO.purpose" id="lastDonationDate">
            
            </s:textarea>
             <s:fielderror fieldName="bloodRequestDTO.lastDonationDate" />
              <%-- <s:textfield label="last donation date"
								
								cssClass="required  textField" />
              <s:fielderror fieldName="bloodRequestDTO.lastDonationDate" /> --%>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="submitBtn">
          <s:submit name="bloodRequest" value="Send Request"
						cssClass="button" />
        </div>
      </div>
    </div>
  </s:form>
</div>

<script type="text/javascript"
	src="${webRoot}/jquery/jquery.validate.js"></script>
<script>
	$(document).ready(function() {
		$("#bloodrequest").validate({
			rules : {
			//donorPassword : "required",
			//confirmPassword : {
			//equalTo : "#donorPassword"
			//}
			}
		});
	});
</script>
