<%@taglib prefix="s" uri="/struts-tags"%>

<div class="pageTitle">Find A Donor</div>
<s:if test="%{error ==1}">
   
  <!--<div align="center"> <font color="red"><b>No result found.</b></font> </div> -->
   <script>
  
   //document.getElementById('noSearchresultFound').style='block';
   </script>
</s:if>
<s:form action="donor-search-by-area" theme="simple" id="donorSearch" namespace="/search">
  <table class="tblDonorSearchForm" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td valign="middle" align="left" width="25%"><s:select list="bloodGroups" label="Blood Group"
						name="bloodGroup" cssClass="selectField" style="width:131px;"></s:select></td>
      <td valign="middle" align="left" width="25%"><s:select list="states" label="State" name="state"
						onchange="ajaxCallForDistrict();" id="stateList" cssClass="selectField" style="width:131px;"></s:select>
      <td valign="middle" align="left" width="25%"><s:select list="districts" label="District" name="district"
						id="district" cssClass="selectField required" style="width:131px;"></s:select></td>
      <td class="lst" valign="middle" align="left" width="25%"><input type="submit"  value="Search" onclick="validateCriteria(true);" class="button" >
        <%-- <s:submit name="Search"></s:submit> --%></td>
    </tr>
  </table>
  <jsp:include page="donorSearchResults.jsp"></jsp:include>
  <s:if test="%{donorsInfo !=null}">
    <s:if test="%{not(donorsInfo.isEmpty())}">
      <s:include value=".././paging/paging.jsp" />
    </s:if>
  </s:if>
  <s:hidden name="criteriaChanged" id="criteriaChanged" value="false"/>
   <s:hidden name="selectedDonorsIds" id="selectedDonorsIds" value="%{selectedDonorsIds}"/>
</s:form>
