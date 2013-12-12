<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
$(function() {
	$('#unVerifiedRequest').click(
			function() {
				$('#verifiedRequestDiv').hide();
				$('#unVerifiedRequestDiv').show();
			});
});

$(function() {
	$('#verifiedRequest').click(
			function() {
				$('#unVerifiedRequestDiv').hide();
				$('#verifiedRequestDiv').show();
				
			});
});
</script>

 <div  style="padding:0;">
                <div >
                  <div class="bloodRequestTabs">
                    <div class="tabs">
                      <ul>
                      <li class="first" id="unVerifiedRequest">Unverified Blood Requests</li>
                        <li class="second" id="verifiedRequest">Verified Blood Requests</li>
                        
                      </ul>
                    </div>
                    <div style="background:#FFF;" id="unVerifiedRequestDiv" >
                      <s:action name="load-unverified-requests" namespace="/request" executeResult="true"/>
                    </div>
                    <div style="background:#FFF;" id="verifiedRequestDiv" class="verifiedReq" >
                     <s:action name="load-verified-requests" namespace="/request" executeResult="true"/>
                   </div>
                  </div>
                </div>
                <div class="clearfix"> </div>
              </div>