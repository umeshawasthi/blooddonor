

<%@ taglib prefix="s" uri="/struts-tags"%>

<style type="text/css">
#scroller_container {
	position: relative;
	height: 200px;
	overflow: hidden;
}

#marqueecontainer {
	height: 118px;
	overflow: hidden;
	position: relative;
	width: 100%;
}
</style>

<script type="text/javascript">
                            var delayb4scroll=2000 //Specify initial delay before marquee starts to scroll on page (2000=2 seconds)
                            var marqueespeed=1 //Specify marquee scroll speed (larger is faster 1-10)
                            var pauseit=1 //Pause marquee onMousever (0=no. 1=yes)?
                            
                            ////NO NEED TO EDIT BELOW THIS LINE////////////
                            
                            var copyspeed=marqueespeed
                            var pausespeed=(pauseit==0)? copyspeed: 0
                            var actualheight=''
                            
                            function scrollmarquee(){
                            if (parseInt(cross_marquee.style.top)>(actualheight*(-1)+8)) //if scroller hasn't reached the end of its height
                            cross_marquee.style.top=parseInt(cross_marquee.style.top)-copyspeed+"px" //move scroller upwards
                            else //else, reset to original position
                            cross_marquee.style.top=parseInt(marqueeheight)+8+"px"
                            }
                            
                            function initializemarquee(){
                            cross_marquee=document.getElementById("vmarquee")
                            cross_marquee.style.top=0
                            marqueeheight=document.getElementById("marqueecontainer").offsetHeight
                            actualheight=cross_marquee.offsetHeight //height of marquee content (much of which is hidden from view)
                            if (window.opera || navigator.userAgent.indexOf("Netscape/7")!=-1){ //if Opera or Netscape 7x, add scrollbars to scroll and exit
                            cross_marquee.style.height=marqueeheight+"px"
                            cross_marquee.style.overflow="scroll"
                            return
                            }
                            setTimeout('lefttime=setInterval("scrollmarquee()",50)', delayb4scroll)
                            }
                            
                            if (window.addEventListener)
                            window.addEventListener("load", initializemarquee, false)
                            else if (window.attachEvent)
                            window.attachEvent("onload", initializemarquee)
                            else if (document.getElementById)
                            window.onload=initializemarquee
                            
                            
                            </script>
	                 
<div class="rhtCntCol">
	<div class="grnTtlBox">
		<div class="topCrv">
			<div class="topCrvL">
				<div class="topCrvR">Blood Required</div>
			</div>
		</div>


		<div class="bd">
        	<div onmouseout="copyspeed=marqueespeed"
			onmouseover="copyspeed=pausespeed" id="marqueecontainer"
			style="position: relative; width: 100%;">
			<div style="position: absolute; width: 100%;" id="vmarquee">
			<ul class="bloodReqList" id="bloodReqList">
			 <s:iterator value="#application.bloodRequestCache" var="bloodReqScrDto">
					<s:iterator value="value">
					    <li><a onclick="bloodRequestBackPopup('<s:property value="uuid"/>');">Need&nbsp;<s:property value="bloodGroup" />
							blood, Contact No: &nbsp;<s:property
								value="contactNumber" />&nbsp;At&nbsp;<s:property value="location" />(<s:property value="state" />)</a></li>
    		      </s:iterator>
                </s:iterator>
					
				</ul>
			</div>
		</div>
		</div>

		<div class="botCrv">
			<div class="botCrvL">
				<div class="botCrvR">&nbsp;</div>
			</div>
		</div>
	</div>
	<div class="flW advBox2">
		<img src="${webRoot}/media/images/advertise2.jpg" alt="Advertise" />
	</div>
</div>

<div id="showBloodRequestBackgroundPopup" class="popupOverlayBg"></div>
  <div id="showBloodRequestPopupContact"  class="popupContainer" style="width:508px;">
  
      <div class="hd">
        <div class="ttl">Blood Request Details</div>
        <div class="close"> <a id="close" href="javascript:void(0);" onclick="showBloodRequestDisablePopup();">[X]</a> </div>
        <div class="clearfix"></div>
      </div>
      <div class="popupForm">
        <div class="row">
          <div class="col">
              <div class="inputField">
              <div class="flW">
                <div id="name"></div>
                <div id="age"></div>
                <div id="mobileNumber"></div>
                <div id="gender"></div>
                <div id="bloodGroup"></div>
                <div id="unitNeeded"></div>
                <div id="hospitalName"></div>
                <div id="patientName"></div>
                <div id="purpose"></div>
                
                
              </div>
            </div>
          </div>
        </div>
      
      </div>
   
  </div>