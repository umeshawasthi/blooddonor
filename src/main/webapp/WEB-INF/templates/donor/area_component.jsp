<%@taglib prefix="s" uri="/struts-tags"%>

<s:form action="donor-search-by-area" theme="simple" id="donorSearch_areacomponent" namespace="/search">
  <div class="findDonorTabsCnt" id="findDonorArea">
    <div class="inputRow">
      <div class="inputLabel">Group:</div>
      <div class="inputField">
        <div class="flW">
          <s:select list="bloodGroups" cssClass="selectField" name="bloodGroup"/>
        </div>
      </div>
    </div>
    <div class="inputRow">
      <div class="inputLabel">State:</div>
      <div class="inputField">
        <div class="flW">
          <s:select list="states" cssClass="selectField" name="state"
				onchange="searchAjaxCallDistrict();" id="stateList_"  headerKey="-1" headerValue="Please Select State"/>
        </div>
      </div>
    </div>
    <div class="inputRow">
      <div class="inputLabel">District/City:</div>
      <div class="inputField">
        <div class="flW">
          <s:select list="districts" cssClass="selectField" name="district"
				id="district_"  headerKey="-1" headerValue="Please Select District"/>
        </div>
      </div>
    </div>
    <div class="inputRow">
      <div class="fr">
        <s:submit type="submit"  value="Search" onclick="return validateInputs('district_',false);" cssClass="button" />
      </div>
    </div>
  </div>
</s:form>
