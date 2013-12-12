<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div id="error-404">
  <img alt="" src="images/error/login-failed.png">
  <div style="float: left;margin: 1.5em 0 0;width: 300px;">
   <h1 style="color: #000000;font-size: 171.42%;margin-bottom: 0.5em;font-weight: normal;">Unable to Authorize</h1>
   <p style="margin-bottom:1.5em;margin-top: 1em;">
		<s:property value="%{#session.accessDeniedMsg}"/>
	</p>
   
  </div>
 </div>
</body>
</html>