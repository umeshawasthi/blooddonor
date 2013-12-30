<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="navigation">
  <div class="navigationLftCrv">
    <div class="navigationRhtCrv">
      <ul>
        <s:if test="%{#session.logged_in!=true}">
        <li>
          <s:a action="sign-up" label="registration" namespace="/account">Register</s:a>
        </li>
        </s:if>
        <li>
          <s:a action="blood-request" label="bloodRequest" namespace="/request">Post Blood Request</s:a>
        </li>
        <li><a  id="editBloodRequest" title="" onclick="editBloodReqPopup();">Edit Blood Request</a>
          <!--<s:a action="load-blood-request" label="bloodRequest" namespace="/request">Edit Blood Request</s:a>-->
        <li>
          <s:a action="why-donate-blood" namespace="/" label="whyDonateBlood">Why Donate Blood</s:a>
        </li>
        <li><s:a action="invite-friends">Invite Friends</s:a></li>
        <li><!-- <a  id="feedback" title="" onclick="feedBackPopup();">Feedback</a> -->
        <s:a action="user-feedback" label="Feedback" namespace="/feedback">Feedback</s:a>
        </li>
        <li>
          <s:a action="about-us" label="About us" namespace="/">About us</s:a>
        </li>
      </ul>
    </div>
  </div>
</div>



<div id="editBloodBackgroundPopup" class="popupOverlayBg"></div>
<div id="editBloodPopupContact" class="popupContainer" style="width:508px;">
  <s:form action="get-blood-request" theme="simple" id="getBloodrequest"
		cssClass="cmxform" namespace="/request" validate="true">
    <div class="hd">
      <div class="ttl">Enter Blood Request ID</div>
      <div class="close"> <a id="close" href="javascript:void(0);" onclick="editBloodDisablePopup();">[X]</a> </div>
      <div class="clearfix"></div>
    </div>
    <div class="popupForm">
      <div class="row">
        <div class="col">
          <div class="inputLabel"> Blood Request Id<em>*</em> </div>
          <div class="inputField">
            <div class="flW">
              <s:textfield label="Blood Request Id" name="bloodRequestDTO.uuid"
								id="uniqueId" cssClass="required textField" />
            </div>
          </div>
        </div>
      </div>
      <div class="row">
        <div class="submitBtn">
          <s:submit name="GetBloodrequest" cssClass="button" />
        </div>
        <div id="statusMsg"></div>
      </div>
    </div>
  </s:form>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		$("#getBloodrequest").validate({
			rules : {}
		});

		$("#uniqueId").rules("add", {
			digits : true,
			min : 1
		});
		
		$("#feedbackForm").validate();
	});
</script>
