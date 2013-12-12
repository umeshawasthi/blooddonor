<%@taglib prefix="s" uri="/struts-tags"%>


<s:form action="donor-search-by-postal-code" theme="simple" id="donorSearch_postal_component" onsubmit="return checkForEmpty();" namespace="/search">
	<div class="findDonorTabsCnt" id="findDonorPO">
		<div class="inputRow">
			<label>Blood Group:</label>
			<s:select list="bloodGroups" name="bloodGroup" value="%{bloodGroup}"
				cssClass="sg" />
		</div>
		<div class="inputRow">
			<label>Postal Code:</label>
			<s:textfield name="postalCode" id="postalCode" cssClass="required"
				value="%{postalCode}" onkeypress="searchKeyPress(event);"/>
		</div>

		<div class="submitRow fr">
			<div class="fr">
				<s:submit value="Search" onclick="return validateCriteria(false);" cssClass="button"/>
				</div>
			</div>
		</div>

</s:form>
