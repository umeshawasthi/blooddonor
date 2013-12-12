<%@taglib prefix="s" uri="/struts-tags"%>

<div class="flW">
  <div class="pageSubTitle">Feedback</div>
  <div class="navigation" style="width: 260px;"><ul><li><a  id="feedback" title="" onclick="feedBackPopup();">Please provide your Feedback</a></li></ul></div> <br/>
  <br/>
  <br/>
  ${message}<br/>
  <br/>
  <div id="feedbackBackgroundPopup" class="popupOverlayBg"></div>
  <div id="feedbackPopupContact"  class="popupContainer" style="width:508px;">
    <s:form action="donor-feedback" name="feedbackForm" id="feedbackForm" cssClass="cmxform" namespace="/feedback" theme="simple">
      <div class="hd">
        <div class="ttl">Submit your Feedback</div>
        <div class="close"> <a id="close" href="javascript:void(0);" onclick="feedbackDisablePopup();">[X]</a> </div>
        <div class="clearfix"></div>
      </div>
      <div class="popupForm">
        <div class="row">
          <div class="col">
            <div class="inputLabel">Name <span class="asterisk">*</span></div>
            <div class="inputField">
              <div class="flW">
                <s:textfield name="donorFeedbackDTO.donorName" label="Name*" id="donorName" cssClass="required textField" />
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <div class="inputLabel">Contact <span class="asterisk">*</span></div>
            <div class="inputField">
              <div class="flW">
                <s:textfield name="donorFeedbackDTO.contact" label="Contact*" id="contact" cssClass="required textField" />
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <div class="inputLabel">Email ID <span class="asterisk">*</span></div>
            <div class="inputField">
              <div class="flW">
                <s:textfield name="donorFeedbackDTO.email" label="Email ID*" id="email" cssClass="required email textField" />
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <div class="inputLabel">Message</div>
            <div class="inputField">
              <div class="flW">
                <s:textarea cols="10" rows="5" name="donorFeedbackDTO.message" label="Message" id="message" cssClass="required textField" />
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col">
            <div style="float:right; padding:2px 23px 0 0;">
              <s:submit name="Submit" cssClass="button" />
            </div>
          </div>
        </div>
      </div>
    </s:form>
  </div>
  <table class="datatable" cellpadding="0" cellspacing="0" border="0" width="100%;">
    <tr>
      <th width="20%" align="left">Name</th>
      <th width="50%" align="center">Message</th>
      <th width="30%" align="left">Feedback Time</th>
    </tr>
    <s:iterator var="feedback" value="feedbackList" status="indice">
      <tr>
        <td align="left"><s:property value="%{#feedback.donorName}" /></td>
        <td align="center"><s:property value="%{#feedback.message}" /></td>
        <td align="left"><s:property value="%{#feedback.feedbackTime}" /></td>
      </tr>
    </s:iterator>
  </table>
</div>
