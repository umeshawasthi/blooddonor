<%@taglib prefix="s" uri="/struts-tags"%>
<script> 

function showLogin()
{
var s="<%=request.getParameter("simple")%>"; 
if(s=="true")
	{
	//Show_Popup();
	centerPopup();
					//load popup
	loadPopup();
	return true;
	}
}
</script>

<body>
<div class="pageTitle">Sign-in or Create New Account</div>
<s:form action="oAuth" name="oAuthLogin" id="openid_form">
  <input type="hidden" name="action" value="verify" />
  <fieldset>
  
  <div id="openid_choice">
    <p style="margin-bottom:10px; display:none;">Please click your account provider:</p>
    <div id="openid_btns"></div>
  </div>
  <div id="openid_input_area">
    <input id="openid_identifier" name="openid_identifier" type="text"
				value="http://" />
    <input id="openid_submit" type="submit"
				value="Sign-In" />
  </div>
  </fieldset>
  <s:hidden name="service_provider_name" id="service_provider_name"
		value="%{service_provider_name}" />
</s:form>
<link href="${webRoot}/media/css/openid.css" rel="stylesheet"
	type="text/css" media="all" />
<script type="text/javascript" src="${webRoot}/jquery/jquery.validate.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		openid.init('openid_identifier');
		$("#normalLogin").validate();
	});
</script>

<div id="backgroundPopup" class="popupOverlayBg"></div>
<div id="popupContact" class="popupContainer" style="width:408px;">
  <s:form action="donor-login" name="normalLogin" id="normalLogin" cssClass="cmxform"	namespace="/accountLogin" theme="simple">
    <s:property value="%{message}" />
    <div class="hd">
      <div class="ttl">Login</div>
      <div class="close"> <a id="close" href="javascript:void(0);" onClick="disablePopup();">[X]</a> </div>
      <div class="clearfix"></div>
    </div>
    <div class="popupForm">
      <div class="row">
        <div class="inputLabel">Email ID <span class="asterisk">*</span></div>
        <div class="inputField">
          <div class="flW">
            <s:textfield name="donorCredentialDTO.donorEmail" label="Email ID*" id="email" cssClass="required email textField" />
          </div>
        </div>
      </div>
      <div class="row">
        <div class="inputLabel">Password <span class="asterisk">*</span></div>
        <div class="inputField">
          <div class="flW">
            <s:password name="donorCredentialDTO.donorPassword" label="Password *" id="donorPassword" cssClass="required  textField" />
          </div>
        </div>
      </div>
      <div class="row">
        <div style="float:right; padding:2px 18px 0 0;">
          <s:submit name="Login" cssClass="button" value="Login"></s:submit>
        </div>
        <div id="failDiv"></div>
      </div>
    </div>
  </s:form>
</div>


<s:if test="%{#parameters.simple[0] == 'true'}">
 Do not have account? 
 <s:a action="sign-up" label="registration" namespace="/account">Create new Account</s:a>
</s:if>

</body>
