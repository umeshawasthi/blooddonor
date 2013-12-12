<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:if test="%{cronTriggerUIDTO.isMonthly()}">
<s:label theme="simple">Day:&nbsp;&nbsp;</s:label>
<s:select list="%{cronTriggerUIDTO.monthlyTriggerFrequencyRange}" name="cronTriggerDTO.daysofmonth" id="daysofmonth"/>
</s:if>