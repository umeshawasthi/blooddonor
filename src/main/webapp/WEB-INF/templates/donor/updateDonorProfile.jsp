<%@taglib prefix="s" uri="/struts-tags"%>

<div class="midCntCol2">
  <div class="pageTitle">Update Profile</div>
  <s:form action="update-donor-profile" theme="simple" id="updateDonorProfile" cssClass="cmxform" namespace="/account" validate="true">
    <div class="dvMainForm">${message}
      <div class="row">
        <div class="col">
          <div class="inputLabel">Email ID <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW">
              <s:if test="%{donorProfileDTO != null}">
                <s:if
                                    test="%{donorProfileDTO.email != null && donorProfileDTO.email != '' }">
                  <s:textfield label="Email" name="donorProfileDTO.email"
                                        id="email" cssClass="required email textField" readonly="true" />
                  <s:fielderror fieldName="donorProfileDTO.email"/>
                </s:if>
                <s:else>
                  <s:textfield label="Email" name="donorProfileDTO.email" id="email"
                                    cssClass="required email textField"
                                    onchange="ajaxUniqueMailId();" onclick="$('#errorMsg').html('');" />
                </s:else>
              </s:if>
              <s:else>
                <s:textfield label="Email" name="donorProfileDTO.email" id="email"
                                    cssClass="required email textField"
                                    onchange="ajaxUniqueMailId();" onclick="$('#errorMsg').html('');" />
              </s:else>
              <s:fielderror fieldName="donorProfileDTO.email"/>
            </div>
          </div>
        </div>
        <div class="col">
          <s:hidden name="donorProfileDTO.oauth"></s:hidden>
          <div class="inputLabel">Moblie Number <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="Mobile No"
							name="donorProfileDTO.mobile" id="mobile"
							cssClass="required textField" />
              <s:fielderror fieldName="donorProfileDTO.mobile"/>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="inputLabel">Name <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="Name" name="donorProfileDTO.name"
							id="name" cssClass="required textField" />
              <s:fielderror fieldName="donorProfileDTO.name"/>
              <s:hidden name="donorRolesDTO.donorRoles" value="ROLE_USER"/>
              <s:hidden name="donorRolesDTO.userEnabled" value="true"/>
              <s:hidden name="donorRolesDTO.accountExpired" value="true"/>
              <s:hidden name="donorRolesDTO.credentialExpired" value="true"/>
              <s:hidden name="donorRolesDTO.accountLocked" value="true"/>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="inputLabel">Blood group <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW">
              <s:select list="bloodGroups" label="Blood Group"
							name="donorProfileDTO.bloodGroup" id="bloodGroup"
							cssClass="required selectField"></s:select>
              <s:fielderror fieldName="donorProfileDTO.bloodGroup"/>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="inputLabel">Date Of Birth <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW">
              <s:date name="donorProfileDTO.tempDOB" var="formattedDOB" format="MM/dd/yyyy"/>
              <s:textfield label="Date of Birth"
							name="donorProfileDTO.dateOfBirth" id="dateOfBirth"
							cssClass="required textField" value="%{formattedDOB}"/>
              <s:fielderror fieldName="donorProfileDTO.dateOfBirth"/>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="inputLabel">Gender <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW rdo">
              <s:radio label="Gender" name="donorProfileDTO.gender" cssClass="chkinput"
							list="#{'Male':'Male','Female':'Female'}" id="gender" value="%{donorProfileDTO.gender}" > </s:radio>
              <s:fielderror fieldName="donorProfileDTO.gender"/>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="inputLabel">State <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW">
              <s:select list="states" label="State"
							name="donorProfileDTO.state" onchange="ajaxCallForDistrict();"
							id="stateList" cssClass="required selectField"></s:select>
              <s:fielderror fieldName="donorProfileDTO.state"/>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="inputLabel">Districts <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW">
              <s:select list="districts" label="District"
							name="donorProfileDTO.district" id="district" cssClass="required selectField"></s:select>
              <s:fielderror fieldName="donorProfileDTO.district"/>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="inputLabel">Location <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="location"
							name="donorProfileDTO.location" id="location" cssClass="required textField" />
              <s:fielderror fieldName="donorProfileDTO.location"/>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="inputLabel">Weight <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="Weight" name="donorProfileDTO.weight"
							id="weight" cssClass="required textField" />
              <s:fielderror fieldName="donorProfileDTO.weight"/>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="inputLabel">Postal Code <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="Pin Code"
							name="donorProfileDTO.postalCode" id="pincode"
							cssClass="required textField" />
              <s:fielderror fieldName="donorProfileDTO.postalCode"/>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="inputLabel">Preferred Contact Time <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW">
              <s:select list="#{'AnyTime':'AnyTime','OnlyDay':'OnlyDay'}"
							label="Preferred Contact Time"
							name="donorPreferencesDTO.preferedContactTime" id="pContactTime"
							cssClass="required selectField"></s:select>
              <s:fielderror fieldName="donorPreferencesDTO.preferedContactTime"/>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="inputLabel">Secondary Mobile No.</div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="Secondary Mobile Number"
							name="donorProfileDTO.secondaryContactNumber" id="sMobileNumber"
							cssClass="textField" />
              <s:fielderror fieldName="donorProfileDTO.secondaryContactNumber"/>
            </div>
          </div>
        </div>
        <div class="col">
          <div class="inputLabel">Phone Visibilty <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW rdo">
              <s:radio label="Phone Visibilty"  cssClass="chkinput"
                                name="donorPreferencesDTO.hidePhonenumbers"
                                list="#{'h':'Hide','s':'Show'}" id="phoneVisibilty"> </s:radio>
              <s:fielderror fieldName="donorPreferencesDTO.hidePhonenumbers"/>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="col">
          <div class="inputLabel">Availability Status <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW rdo">
              <s:radio label="Availability Status"  cssClass="chkinput"
							name="donorPreferencesDTO.donorAvailability"
							list="#{'y':'Available','n':'Not Available'}"
							id="availabilityStatus"> </s:radio>
              <s:fielderror fieldName="donorPreferencesDTO.donorAvailability"/>
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="submitBtn">
          <s:submit name="updateDonorProfile" value="update"  cssClass="button"/>
        </div>
      </div>
    </div>
  </s:form>
</div>

<script type="text/javascript" src="${webRoot}/jquery/jquery.validate.js"></script>
<script>
$(document).ready(function() {
	$("#updateDonorProfile").validate({
		/* rules : {
			
			}
		} */
	});
	
	
	$("#weight").rules("add", {
		 digits: true,
		 min: 45,
		 max: 200
		});	
	$("#mobile").rules("add", {
		 digits: true,
		 minlength: 10,
		 maxlength: 12
		});
	$("#sMobileNumber").rules("add", {
		 digits: true,
		 minlength: 10,
		 maxlength: 12
		});
});
	
$(function() {
	$( "#dateOfBirth" ).datepicker({
		yearRange: 'c-100:c+100',
		changeMonth: true,
		changeYear: true
	});
});
	
	

</script>
