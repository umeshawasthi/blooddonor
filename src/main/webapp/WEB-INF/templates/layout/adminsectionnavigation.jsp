<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib prefix="s" uri="/struts-tags"%>
<div class="colL">
    <ul class="linklist">
      <li class="selected">
        <s:a action="load-unverified-requests" namespace="/request">Approve Blood Requests</s:a>
      </li>
      <li>
        <s:a action="load-unverified-feedbacks"  namespace="/feedback">Approve Feedbacks</s:a>
      </li>
      <li>
        <s:a action="showAllJobs"  namespace="/">Create/Edit jobs</s:a>  
      </li>
    </ul>
  </div>