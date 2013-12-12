<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib prefix="s" uri="/struts-tags"%>

 <div class="lftCntCol">
            <div class="grnTtlBox">
              <div class="topCrv">
                <div class="topCrvL">
                  <div class="topCrvR">Find a Donor</div>
                </div>
              </div>
             <div class="bd" style="padding:0;">
                <div class="bdM" >
                  <div class="findDonorTabs">
                    <div class="tabs">
                      <ul>
                        <li class="first"><a href="#findDonorArea">Search by Area</a></li>
                        <li class="second"><a href="#findDonorPO">Search by PO</a></li>
                      </ul>
                    </div>
                    <div class="flW" style="background:#FFF;">
                      <s:action name="area-component" namespace="/search" executeResult="true"/>
                      <s:action name="postal-component" namespace="/search" executeResult="true"/>
                   </div>
                  </div>
                </div>
                <div class="clearfix"> </div>
              </div>
              <div class="botCrv">
                <div class="botCrvL">
                  <div class="botCrvR">&nbsp;</div>
                </div>
              </div>
            </div>
            <div class="flW"><img src="${webRoot}/media/images/advertise1.jpg" alt="Advertise" width="235px"/></div>
          </div>
 