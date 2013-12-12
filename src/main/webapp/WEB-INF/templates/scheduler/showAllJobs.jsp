<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<style type="text/css">
BODY
  {
      FONT-SIZE: 12px;
      FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
  }
TD
  {
      FONT-SIZE: 12px;
      FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
  }
H1
  {
      FONT-SIZE: 20px;
      FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
  }
H2
  {
      FONT-SIZE: 15px;
      FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
  }
H3
  {
      FONT-SIZE: 13px;
      FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif
  }

img {
  border: none;
}

.tableBorder
  {
    BORDER-RIGHT: #000000 1px solid;
    BORDER-TOP: 0px;
    BORDER-LEFT: 0px;
    BORDER-BOTTOM: #000000 1px solid
  }

.tdBorder
  {
    BORDER-RIGHT: 0px;
    BORDER-TOP: #000000 1px solid;
    BORDER-LEFT: #000000 1px solid;
    BORDER-BOTTOM: 0px
  }

.center{
    text-align:center;
}

.tdTitle
{
  background-color:#EEEEEE;
}

.tdTitleExpired
{
  background-color:#fff3f1;
}

.tdTitleFuture
{
  background-color:#f1faff;
}

.error
{
  color:red;
}

.info
{
  color:green;
}

.bold
{
    font-weight:bold;
}
</style>
</head>

<body>

	<h1>Jobs Information</h1>

	Current Time:
	<b><%= new java.util.Date() %></b>
	<br /> Scheduler State:
	<b><s:property value="schedulerStatus"/></b>
	<br />
	<br />
    <s:a action="createTriggersView">Schedule New Job</s:a> &nbsp; &nbsp; &nbsp;
     <s:a action="createNewGroup">Create New Group</s:a>&nbsp; &nbsp; &nbsp;
     <br/><br/>
	<table class="tableBorder" cellpadding="4" cellspacing="0">

		<tr>

			<td class="tdBorder bold center">Group</td>

			<td class="tdBorder bold center">Job</td>

			<td class="tdBorder bold center">Cron Expression</td>

			<td class="tdBorder bold center">Previous Fire Time</td>

			<td class="tdBorder bold center">Next Fire Time</td>

			<td class="tdBorder bold center">Stateful</td>

			<td class="tdBorder bold center">Interruptable</td>

			<td class="tdBorder bold center">Running</td>

			<td class="tdBorder bold center">Actions</td>

		</tr>
        <s:set var="previousGroupName" value="NA"/>
		<s:iterator value="jobDetailsDTOs" >
		
		<tr>

	
        <s:if test="#previousGroupName != groupName ">
			<td class="tdBorder" rowspan='<s:property value="jobLenght"/>'><s:property value="groupName"/></td>
		</s:if>
 <!--  error bold -->
			<td class="tdBorder "><s:property value="jobName"/></td>

			<td class="tdBorder"><s:property value="triggerExpression"/></td>

			<td class="tdBorder">
			 <s:if test="{previousFireTime.equals('') || previousFireTime == null}">
			  <s:date name="previousFireTime"/>
			  </s:if>
			  <s:else>
			     "NA"
			  </s:else>
			  </td>

			<td class="tdBorder">
			 <s:if test="{nextFireTime.equals('') || nextFireTime == null}">
				<s:date name="nextFireTime"/>
			</s:if>
			  <s:else>
			     "NA"
			  </s:else>
			</td>

			<td class="tdBorder center"><s:property value="statefulJob"/></td>

			<td class="tdBorder center"><s:property value="interruptableJob"/></td>

			<td class="tdBorder center"><s:property value="numInstances"/></td>
			<td class="tdBorder center"><a
				href="#">Exec</a>&nbsp;|&nbsp;<a
				href="#">Resume</a>

			</td>

		</tr>
		<s:set var="previousGroupName" value="groupName"/>
		</s:iterator>

</table>
