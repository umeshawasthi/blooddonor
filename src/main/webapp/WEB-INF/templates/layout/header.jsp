<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<div class="plcT">
  <div class="plcTL">
    <div class="plcTR">&nbsp;</div>
  </div>
</div>
<div class="plcM">
  <div class="plcMCnt">
    <div class="header">
      <div class="logo">
        <s:a  action="" namespace="/" title="welcome to donateblood.com" > <img src="${webRoot}/media/images/logo.gif" alt="welcome to donateblood.com" /> </s:a>
      </div>
      <div class="rhtCol">
        <div class="date flw">
          <div class="header-bar">
            <ul class="top-menu">
              <s:if test="%{#session.logged_in==true}">
                <li> <a class="logged-link btn-open"><span>Hello,<strong>
                  <s:property value="%{#session.user.name}"/>
                  </strong></span></a>
                  <ul class="login-drop drop">
                    <li>
                      <s:a action="get-donor-credential" label="UpdatePassword" namespace="/account">Change Password</s:a>
                    </li>
                    <li>
                      <s:a action="get-donor-by-id" label="UpdateProfile" namespace="/account">Update profile</s:a>
                    </li>
                    <li>
                      <s:a action="donation-history-options" label="DonationHistory" namespace="/account/donationhistory">Donation History</s:a>
                    </li>
                     <!-- Need to show this only to admin -->
                     <li>
                      <s:a action="admin" label="Settings" namespace="/account">Settings</s:a>
                    </li>
                    <!-- End of admin section -->
                    <li>
                      <s:a action="donor-logout" namespace="/accountLogin">Logout</s:a>
                    </li>
                  </ul>
                  <div class="account-drop  drop"></div>
                </li>
              </s:if>
			  <s:else>
			       <li>
				      <s:a  action="login?simple=true">Login</s:a>
					   <s:hidden name="forLogin" id="forLogin"/>
                   </li>				   
			  </s:else>
              <li class="last">
                <script>showDate()</script>
              </li>
            </ul>
          </div>
        </div>
       
      </div>
    </div>
    <div class="clearfix"></div>
  </div>
  <div class="clearfix"></div>
</div>
