<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="DonationHistoryOptions.jsp" %><br/><br/>
<div class="flW">
<s:form action="get-donation-record"  theme="simple" id="donationListForm" cssClass="cmxform" namespace="/account/donationhistory">
 <table class="datatable" cellpadding="0" cellspacing="0" border="0">
	
	<tr>
             
                   <th width="20%" align="left">Patient Name</th>
                   <th width="25%" align="center">Donation Place</th>
                   <th width="25%" align="left">Hospital Name</th>
                   <th width="30%" align="left">Blood Donation Date</th>
				  
                </tr>
	

	<s:iterator var="historyRecords" value="historyRecords" status="indice">
	<s:set name="historyRecord" value="historyRecords[#indice.index]"/>
		<s:if test="#indice.odd == true">			
		      <tr class="odd">
		          <td align="left"><s:property value="#historyRecord.patientName" /></td>
                  <td align="center"><s:property value="#historyRecord.donationPlace" /></td>
				  <td align="left"><s:property value="#historyRecord.hospitalName" /></td>
                  <td align="center"><s:property value="#historyRecord.bloodDonationdate" /></td>
                </tr>
               
		</s:if>
		<s:else>
		    <tr class="even">
		          <td align="left"><s:property value="#historyRecord.patientName" /></td>
                  <td align="center"><s:property value="#historyRecord.donationPlace" /></td>
				  <td align="left"><s:property value="#historyRecord.hospitalName" /></td>
                  <td align="center"><s:property value="#historyRecord.bloodDonationdate" /></td>
                </tr>
          
		</s:else>
	</s:iterator>
</table>

</s:form>
</div>