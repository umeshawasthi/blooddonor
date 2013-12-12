<%@taglib prefix="s" uri="/struts-tags"%>

<div class="admin flW">
  <div class="colL">
    <ul class="linklist">
      <li>
        <s:a action="load-unverified-requests" namespace="/request">Approve Blood Requests</s:a>
      </li>
      <li class="selected">
        <s:a action="load-unverified-feedbacks"  namespace="/feedback">Approve Feedbacks</s:a>
      </li>
      <li>
        <s:a>Create/Edit jobs</s:a>
      </li>
    </ul>
  </div>
  <div class="colR">
    <div class="pageTitle">Approve Feedbacks</div>
    <div class="flW">
      <table class="datatable" cellpadding="0" cellspacing="0" border="0" width="100%;">
        <tr>
          <th width="15%" align="left">Name</th>
          <th align="center">Message</th>
          <th width="15%" align="left">Feedback Time</th>
          <th width="10%" align="left">Action</th>
        </tr>
        <s:iterator var="feedback" value="feedbackList" status="indice">
          <tr>
            <td align="left"><s:property value="%{#feedback.donorName}" /></td>
            <td align="center"><s:property value="%{#feedback.message}" /></td>
            <td align="left"><s:property value="%{#feedback.feedbackTime}" /></td>
            <td align="left"><s:if test="%{#feedback.verified!=true}">
                <s:url action="verify-feedback" var="urlTag" >
                  <s:param name="uuid" value="%{#feedback.uuid}"/>
                  <s:param name="verify">true</s:param>
                </s:url>
                <s:a href="%{urlTag}" label="Verify Feedback" namespace="/feedback"><font color="Green">APPROVE</font></s:a>
              </s:if>
              <s:else>
                <s:url var="urlTag" value="verify-feedback?uuid=%{#feedback.uuid}&verify=false" />
                <s:a href="%{urlTag}" label="Reject Feedback" namespace="/feedback"><font color="Red">REJECT</font></s:a>
              </s:else>
              <s:hidden name="selectedDonorsIds" id="selectedDonorsIds" value="%{selectedDonorsIds}"/>
            </td>
          </tr>
        </s:iterator>
      </table>
    </div>
  </div>
</div>
