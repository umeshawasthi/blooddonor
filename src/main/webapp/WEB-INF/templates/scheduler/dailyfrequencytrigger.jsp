<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{cronTriggerUIDTO.isDaily()}">
<s:label theme="simple">Daily:&nbsp;&nbsp;</s:label>
<input type="radio" onclick="document.getElementById('daily_setdayskip').value=1,document.getElementById('setdayskip_select').disabled=true" name="cronTriggerDTO.setdayskip" checked="checked" title="Daily"/><br/>
<s:label theme="simple">Every :&nbsp;&nbsp;</s:label>
<input type="radio"  name="cronTriggerDTO.setdayskip" onclick="document.getElementById('setdayskip_select').disabled=false"/>&nbsp;&nbsp;
<s:select list="%{cronTriggerUIDTO.dailyTriggerFrequencyRange}" name="cronTriggerDTO.setdayskip_select" disabled="true"
onchange="document.getElementById('daily_setdayskip').value=this.value" id="setdayskip_select"/>
</s:if>
<s:hidden name="cronTriggerDTO.daySkipFrequency" value="1" id="daily_setdayskip"/>