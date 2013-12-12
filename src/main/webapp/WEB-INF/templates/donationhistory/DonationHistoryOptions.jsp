<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 <%-- <s:if test="%{actionName =='create-donation-record'}"> --%>
<div class="flW">
<div class="fcTabs">
  <ul id="tabsRulesTools">
    <s:if test="%{actionName =='create-donation-record'}"><li class="selected"></s:if><s:else><li></s:else><s:a action="create-donation-record" label="DonationHistory" namespace="/account/donationhistory">Create Donation History</s:a></li>
    <s:if test="%{(actionName =='get-donation-records') || (actionName== 'get-donation-record')}"><li class="selected"></s:if><s:else><li></s:else><s:a action="get-donation-records" label="DonationHistory" namespace="/account/donationhistory">Update Donation History</s:a></li>
    <s:if test="%{(actionName =='view-donation-records') ||(actionName =='donation-history-options') || (actionName == 'save-donation-record')}"><li class="selected"></s:if><s:else><li></s:else><s:a action="view-donation-records" label="DonationHistory" namespace="/account/donationhistory">View Donation History</s:a></li>
  </ul>
</div>
