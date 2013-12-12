<%@taglib prefix="s" uri="/struts-tags"%>


<s:form action="donor-search-by-area" theme="simple" id="donorSearch_areacomponent" namespace="/search">
	<div class="findDonorTabsCnt" id="findDonorArea">
		<div class="inputRow">
			<label>Group:</label>
			<s:select list="bloodGroups" cssClass="sg" name="bloodGroup"/>
			<%-- <select name="" id="" class="sg">
				<option value="">--Group--</option>
			</select> --%>
		</div>
		<div class="inputRow">
			<label>State:</label>

			<s:select list="states" cssClass="ss" name="state"
				onchange="searchAjaxCallDistrict();" id="stateList_"/>
			
		</div>
		<div class="inputRow">
			<label>District/City:</label>

			<s:select list="districts" cssClass="required" name="district"
				id="district_" headerKey="Select District" headerValue="-1"/>
			
		</div>
		<div class="submitRow">
			<div class="fr">
			   <s:submit type="submit"  value="Search" onclick="validateCriteria(false);" cssClass="button" />
			</div>

		</div>
	</div>
</s:form>

               
	
