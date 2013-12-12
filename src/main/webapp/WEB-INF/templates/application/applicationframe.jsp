<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="s" uri="/struts-tags"%>
<head>
<!-- Template Name: applicationFrame.jsp
	 Location  <s:property value="workingTemplate"/>
 -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
 <s:if test="%{title != null}">
   <title><s:property value="%{title}"/></title>
</s:if>
<s:else>
<title>Save A Life Today| Welcome to Blood Donation</title>
</s:else>

<meta name="description" content="India blood donor database, register as blood donor,Voluntary Blood Donors"/>
<meta name="keywords" content="blood, blood donor, blood bank, blood banks, blood donors, blood donor database, seek blood donors, get blood donors, blood donors on request, voluntary blood donors, search blood donors, blood donors list, blood donors on demand, donate blood, register blood donors, register blood donor, get blood donor, seek blood donor, voluntary blood donor in India, blood donor in India, blood donors in India, search blood donor, voluntary blood donors in India, blood donation, , india blood donors, , indian blood donors, online blood bank, active blood donors, Platelet donors, Voluntary blood donor registration, blood transfusion, give blood, giving blood,  donating blood, blood center, blood group, blood facts, blood donor online, blood donor requirements, donors blood, donor blood, donor's blood, blood donor service, blood donor help"/>
<s:set var="webRoot" value="#application.webRootContext" />
<s:set var="currentContextUrl" value="#application.rootContext" />


<link href="${webRoot}/media/css/style.css" rel="stylesheet"
	type="text/css" media="all" />
<link href="${webRoot}/media/css/general.css" rel="stylesheet"
	type="text/css" />
<link rel="shortcut icon" href="${webRoot}/media/images/favicon.ico">
<s:head />
<s:set name="theme" value="'simple'" scope="page" />
<script type="text/javascript" src="${webRoot}/js/display_date.js"></script>
<%-- <script type="text/javascript"
	src="${webRoot}/jquery/jquery-1.2.6.min.js"></script> --%>

<s:if test="%{workingTemplate != '/WEB-INF/templates/layout/slider.jsp'}">
     <link href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/themes/blitzer/jquery-ui.css" rel="stylesheet" type="text/css"/>
 </s:if>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5/jquery.min.js"></script>
 
 
</head>
<body>
	<div class="wrapper">
		<div class="plcBox">
			<s:include value="../layout/header.jsp" />
			<s:include value="../layout/topnavigation.jsp" />
            <div class="clearfix"></div>
		</div>
		<div class="clearfix h15"></div>
		<div class="plcBox">
			<div class="plcT">
				<div class="plcTL">
					<div class="plcTR">&nbsp;</div>
				</div>
			</div>
			<div class="plcM">
				<div class="plcMCnt">
					<div class="content">
					   
                       <s:if test="%{hideRightPannel == false}">
					      <s:include value="../layout/rightpannel.jsp" />
					   </s:if>
						
						<s:if test="%{registrationPage == true || bloodRequestPage== true || donorUpdatePage==true || hideLeftPannel==true}">
							
								<!-- Working template -->
								<s:include value="%{workingTemplate}" />
							
						</s:if>
						
                        <s:else>
							<s:include value="../layout/leftpannel.jsp" />
							<div class="midCntCol">
								<!-- Working template -->
								<s:include value="%{workingTemplate}" />
							</div>
						</s:else>

						<div class="clearfix"></div>
					</div>
                    <div class="clearfix"></div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="plcB">
				<div class="plcBL">
					<div class="plcBR">&nbsp;</div>
				</div>
			</div>
            <div class="clearfix"></div>
		</div>
		<s:include value="../layout/footer.jsp" />
	</div>
    
  
	<input type="hidden" id="currentContextPath" name="currentContextPath" value='<s:property value="#currentContextUrl"/>'/>
</body>


<head>



<script type="text/javascript" src="${webRoot}/jquery/jquery.custom.js"></script>
<script type="text/javascript" src="${webRoot}/js/default.js"></script>
<script type="text/javascript" src="${webRoot}/js/openid-jquery.js"></script>
<script type="text/javascript" src="${webRoot}/js/openid-en.js"></script>

<script type="text/javascript" src="${webRoot}/jquery/jquery-1.7.1.js"></script>
 <script type="text/javascript" src="${webRoot}/jquery/jquery.ui.core.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8/jquery-ui.min.js"></script>


<script type="text/javascript"
	src="${webRoot}/jquery/jquery.validate.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$("#donorSearch_postal_component").validate();
	});
	$(document).ready(function() {
		$("#donorSearch_areacomponent").validate();
	});
	$(document).ready(function() {
		$("#donorSearch").validate();
	});
</script>
</head>
</html>