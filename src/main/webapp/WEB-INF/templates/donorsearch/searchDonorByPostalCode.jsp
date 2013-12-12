<%@taglib prefix="s" uri="/struts-tags"%>
<script>
	String.prototype.trim = function() {
		return this.replace(/^\s+|\s+$/g, "");
	};
	function preparePage() {
		document.getElementById("errorMsg").style.display = 'none';
	}
	function checkForEmpty() {
		document.getElementById("noResultMsg").innerHTML = "";
		if (document.getElementById("postalCode").value.trim() == "") {
			document.getElementById("errorMsg").style.display = 'block';
			return false;
		} else {
			document.getElementById("errorMsg").style.display = 'none';
			return true;
		}
	}
	

</script>

<div class="pageTitle">Find A Donor</div>
<s:if test="%{error ==1}">
 <!--<div align="center"> <font color="red"><b>No result found.</b></font> </div> -->
  <script>
 
   </script>
</s:if>
<!-- <div id="errorMsg" align="center">
		<font color="red"><b>Please provide postal code.</b></font>
	</div> -->
<s:form action="donor-search-by-postal-code" theme="simple"
		id="donorSearch" onsubmit="return checkForEmpty();" namespace="/search">
  <table class="tblDonorSearchForm" cellpadding="0" cellspacing="0" width="100%">
    <tr>
      <td valign="middle" align="left"><s:select list="bloodGroups" label="Blood Group"
						name="bloodGroup" value="%{bloodGroup}" cssClass="selectField" style="width:195px;"></s:select></td>
      <td valign="middle" align="left"><s:textfield name="postalCode" id="postalCode"
						label="Postal Code" value="%{postalCode}" onkeypress="searchKeyPress(event);" cssClass="textField required" style="width:195px;"></s:textfield></td>
      <td valign="middle" align="right" class="lst"><input type="submit"  value="Search" onclick="validateCriteria(true);" class="button">
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
