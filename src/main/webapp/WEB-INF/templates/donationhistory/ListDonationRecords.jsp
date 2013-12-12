<%@taglib prefix="s" uri="/struts-tags"%>
<%@include file="DonationHistoryOptions.jsp" %>
<div class="fcTabsContent">
  <div class="fcTabsContentInr">
    <div class="flW">
      <table class="datatable" cellpadding="0" cellspacing="0" border="0" width="100%">
        <tr>
          <th align="left">Patient Name</th>
          <th align="center">Donation Place</th>
          <th align="left">Hospital Name</th>
          <th align="left" width="30%">Blood Donation Date</th>
        </tr>
        <s:iterator var="historyRecords" value="historyRecords" status="indice">
          <s:set name="historyRecord" value="historyRecords[#indice.index]"/>
          <s:if test="#indice.odd == true">
            <tr>
              <td align="left"><s:url var="patient_url" value="get-donation-record?donorId=%{#historyRecord.blood_donationrecord_uuid}" />
                <s:a href="%{patient_url}" label="Click to update" namespace="/account/donationhistory">
                  <s:property value="#historyRecord.patientName" />
                </s:a></td>
              <td align="center"><s:property value="#historyRecord.donationPlace" /></td>
              <td align="left"><s:property value="#historyRecord.hospitalName" /></td>
              <s:date name="#historyRecord.bloodDonationdate" var="formattedDD" format="MM/dd/yyyy"/>
              <td align="center"><s:property value="formattedDD" /></td>
            </tr>
          </s:if>
          <s:else>
            <tr>
              <td align="left"><s:url var="patient_url" value="get-donation-record?donorId=%{#historyRecord.blood_donationrecord_uuid}" />
                <s:a href="%{patient_url}" label="Click to update" namespace="/account/donationhistory">
                  <s:property value="#historyRecord.patientName" />
                </s:a></td>
              <td align="center"><s:property value="#historyRecord.donationPlace" /></td>
              <td align="left"><s:property value="#historyRecord.hospitalName" /></td>
              <s:date name="#historyRecord.bloodDonationdate" var="formattedDD" format="MM/dd/yyyy"/>
              <td align="center"><s:property value="formattedDD" /></td>
            </tr>
          </s:else>
        </s:iterator>
      </table>
    </div>
    <div class="clearfix"></div>
  </div>
</div>
</div>