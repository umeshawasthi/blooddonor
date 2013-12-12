<%@taglib prefix="s" uri="/struts-tags"%>

<s:form action="donor-search-by-postal-code" theme="simple" id="donorSearch_postal_component" onsubmit="return checkForEmpty();" namespace="/search">
  <div class="findDonorTabsCnt" id="findDonorPO">
    <div class="inputRow">
      <div class="inputLabel">Blood Group:</div>
      <div class="inputField">
        <div class="flW">
          <s:select list="bloodGroups" name="bloodGroup"  value="%{bloodGroup}"
				cssClass="selectField" />
        </div>
      </div>
    </div>
    <div class="inputRow">
      <div class="inputLabel">Postal Code:</div>
      <div class="inputField">
        <div class="flW">
          <s:textfield name="postalCode" id="postalCode" cssClass="textField"
				value="%{postalCode}" onkeypress="searchKeyPress(event);"/>
        </div>
      </div>
    </div>
    <div class="inputRow">
      <div class="fr">
        <s:submit value="Search" onclick="return validateInputs('postalCode',false);" cssClass="button"/>
      </div>
    </div>
  </div>
</s:form>
