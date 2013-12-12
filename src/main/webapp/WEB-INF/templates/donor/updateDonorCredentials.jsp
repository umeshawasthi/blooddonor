<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="s" uri="/struts-tags"%>
>


<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery-1.7.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.ui.core.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.custom.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/jquery/jquery.validate.js"></script>

<script>
$(document).ready(function() {
	$("#updateDonorCredential").validate({
		rules : {
			donorPassword : "required",
			confirmPassword : {
				equalTo : "#donorPassword"
			}
		}
	});
});
</script>

<div class="midCntCol2">
<div class="pageTitle">Update Password</div>
  <s:form action="update-donor-credential" theme="simple" id="updateDonorCredential" cssClass="cmxform" namespace="/account">
  <s:hidden name="donorProfileDTO.donor_uuid"></s:hidden>
  <div class="dvMainForm">${message}
    <div class="row">
      <div class="col">
        <div class="inputLabel">Email ID <span class="asterisk">*</span></div>
        <div class="inputField">
          <div class="flW">
            <s:if test="%{donorCredentialDTO != null}">
              <s:if
                                    test="%{donorCredentialDTO.donorEmail != null && donorCredentialDTO.donorEmail != '' }">
                <s:textfield label="Email" name="donorCredentialDTO.donorEmail"
                                        id="email" cssClass="required email textField" readonly="true" />
                                        <s:fielderror fieldName="donorCredentialDTO.donorEmail"/>
              </s:if>
               <s:else>
              <s:textfield label="Email" name="donorCredentialDTO.donorEmail" id="email"
                                    cssClass="required email textField"
                                     onclick="$('#errorMsg').html('');" />
            </s:else>
            </s:if>
            <s:fielderror fieldName="donorCredentialDTO.donorEmail"/>
          </div>
        </div>
      </div>
    
    </div>
    
    <div class="row">
        <div class="col">
          <div class="inputLabel">Password <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW">
              <s:password label="Password"
							name="donorCredentialDTO.donorPassword" id="donorPassword"
							cssClass="required textField" />
							
							<s:fielderror fieldName="donorCredentialDTO.donorPassword"/>
            </div>
          </div>
        </div>
        </div>
        <div class="row">
        <div class="col">
          <div class="inputLabel">Confirm Password <span class="asterisk">*</span></div>
          <div class="inputField">
            <div class="flW">
              <s:password label="Confirm Password"
							name="confirmPassword" cssClass="required textField" id="confirmPassword"/>
            </div>
          </div>
        </div>
      </div>
	  <div class="row">
    <div class="submitBtn">
      <s:submit name="updateDonorCredential" value="update" cssClass="button" />
    </div>
  </div>
	  </div>
	
	
</s:form>
</div>