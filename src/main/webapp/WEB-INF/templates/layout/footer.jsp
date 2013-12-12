<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="java.util.Calendar"%>



<div class="footer">
  <div class="foolinks-holder">
    <ul>
      <li><a href="#" title="">Disclaimer & Privacy Policy</a></li>
      <li><a href="#" title="">Who can/ Can't Donate</a></li>
      <li><a href="#" title="">Contact Us</a></li>
      <li><s:a action="why-donate-blood" namespace="/">Why Donate Blood</s:a></li>
      <li><a href="#" title="">FAQs</a></li>
      <li class="last"> <s:a action="team" label="Team" namespace="/">Technical Team</s:a></li>
    </ul>
    
    
    <p class="copyright">Copyright &copy; DonateBlood.com, <% out.print(Calendar.getInstance().get(Calendar.YEAR)); %>. All rights reserved.</p>
  </div>
  <div class="social-network">
    <ul>
      <li class="icn-facebook"><a href="#"></a></li>
      <li class="icn-youtube"><a href="#"></a></li>
      <li class="icn-twitter"><a href="#"></a></li>
    </ul>
   
    
    <p class="sponser">Maintained by <a href="http://www.travellingrants.com" target="blank">travellingrants</a></p>
  </div>
  <div class="clearfix"></div>
</div>
