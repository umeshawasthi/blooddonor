<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{cronTriggerUIDTO.isWeekly()}">
	<s:label theme="simple">Daily:&nbsp;&nbsp;</s:label>
	<input type="radio" onclick="document.getElementById('weeklyinterval').value=1,document.getElementById('setweekskip_select').disabled=true" 
	name="setweekskip_frequency" checked="checked" title="Weekly"/><br/>
	<s:label theme="simple">Every :&nbsp;&nbsp;</s:label>
	<input type="radio"  name="setweekskip_frequency" onclick="document.getElementById('setweekskip_select').disabled=false"/>&nbsp;&nbsp;
	<s:select list="%{cronTriggerUIDTO.weeklyTriggerFrequencyRange}" name="cronTriggerDTO.setweekskip_select" disabled="true"
	onchange="document.getElementById('weeklyinterval').value=this.value" id="setweekskip_select"/>
	<s:label theme="simple">Days of week: :&nbsp;&nbsp;</s:label>
	<s:checkboxlist list="%{cronTriggerUIDTO.weekdays}" name="cronTriggerDTO.dayofweek"/>
	<s:hidden name="cronTriggerDTO.weeklyInterval" value="1" id="weeklyinterval"/>
</s:if>
