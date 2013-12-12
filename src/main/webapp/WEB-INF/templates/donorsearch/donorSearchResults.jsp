<%@taglib prefix="s" uri="/struts-tags"%>

<div class="flW">
	<div class="pageSubTitle">Donor Results</div>
	<table class="datatable" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<th width="5%" align="left"><input type="checkbox"
				style="visibility: hidden;" /></th>
			<th width="25%" align="left">Name</th>
			<th width="30%" align="center">Email</th>
			<th width="18%" align="left">Contact No</th>
			<th width="27%" align="left">Location</th>
		</tr>
		<s:if test="%{error ==1}">
	
			<tr>
				<td colspan="5"><div
						style="padding: 15px 0; color: #ff0000; font-weight: bold; text-align: center;">
						Sorry we are unable to find any donor.<br/><br/>
						Please <s:a action="blood-request" namespace="/request" cssStyle="color: blue;">register your blood request	</s:a> ,we will try to find donor as soon as possible.
						</div>
				      </td>
			</tr>
		</s:if>
		<s:iterator var="donorsInfo" value="donorsInfo" status="indice">
			<s:set name="donorInfo" value="donorsInfo[#indice.index]" />

		
				<tr>

					<s:if test="%{getSelectedDonorsIdList().contains(#donorInfo[0])}">
						<td align="left"><s:checkbox theme="simple"
								id="%{#donorInfo[0]}" name="donorIDs" value="true"
								fieldValue="%{#donorInfo[0]}"
								onclick="$('#statusMsg').html('');"
								onchange="myFunction(this.value,this.id);" /></td>
					</s:if>
					<s:else>
						<td align="left"><s:checkbox theme="simple"
								id="%{#donorInfo[0]}" name="donorIDs"
								fieldValue="%{#donorInfo[0]}"
								onclick="$('#statusMsg').html('');"
								onchange="myFunction(this.value,this.id);" /></td>
					</s:else>

					<td align="left"><s:property value="#donorInfo[1]" /></td>
					<td align="center"><s:property value="#donorInfo[2]" /></td>
					<td align="left"><s:property value="#donorInfo[3]" /></td>
					<td align="left"><s:property value="#donorInfo[4]" /></td>
				</tr>
			
		</s:iterator>
	</table>
</div>
<s:if test="%{donorsInfo !=null}">
	<s:if test="%{not(donorsInfo.isEmpty())}">
		<div class="flW sendES">
			<div class="fl inputField1">
				<input type="text" value="Enter Your Email" name="" id="toMail"
					class="inputText"
					onblur="if(this.value=='')this.value='Enter Your Email';"
					onfocus="if(this.value=='Enter Your Email')this.value='';"
					onclick="$('#statusMsgMail').html('');" /> <img
					src="${webRoot}/media/images/btn-sendemail.gif" alt="SEND EMAIL"
					id="btnClick" />
			</div>
			<div class="fl inputField2">
				<input type="text" value="Enter Your Mobile No" name="" id=""
					class="inputText"
					onblur="if(this.value=='')this.value='Enter Your Mobile No';"
					onfocus="if(this.value=='Enter Your Mobile No')this.value='';" /> <img
					src="${webRoot}/media/images/btn-sendsms.gif" alt="SEND SMS" />
			</div>
			<div id="statusMsgMail"></div>
			<div id="backgroundPopup" class="popupOverlayBg"></div>
			<div id="popupContact" style="display: none;" class="popupContainer">
				<img alt="" src="${webRoot}/media/images/ajax-loader.gif" /><br />
				sending email...
			</div>
		</div>
	</s:if>
</s:if>
<script>
	var checkedItems = new Array();
	function myFunction(val, id) {

		if (checkedItems.length <= 0) {
			var tempList = $("#selectedDonorsIds").val();
			var list;
			if (tempList.length > 0) {
				list = tempList.split(",");
				for ( var i = 0; i < list.length; i++)
					checkedItems.push(list[i]);

			}

		}
		if ($('#' + id).is(":checked")) {
			checkedItems.push(id);

		} else {
			var index = checkedItems.indexOf(id);
			checkedItems.splice(index, 1);

		}

		$("#selectedDonorsIds").val(checkedItems);

	}
</script>