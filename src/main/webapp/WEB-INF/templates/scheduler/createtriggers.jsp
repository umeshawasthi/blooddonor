<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@taglib uri="/struts-tags" prefix="s"%>
    <!DOCTYPE html>
 <s:a action="createTriggersView">Schedule New Job</s:a> &nbsp; &nbsp; &nbsp;
 <s:a action="createNewGroup">Create New Group</s:a>&nbsp; &nbsp; &nbsp;
     <br/><br/>
 <style type="text/css">   
			   .picker
				{
   					 height:16px;
    				 //width:16px;
   					 background:url("static/media/images/cal.gif") no-repeat;
    				 //margin-left:-19px;
    				 cursor:pointer;
    				 //border:none;		  
				}
			</style>
        <s:form theme="simple" id="createTrigger" name="createTrigger" action="createTrigger">
          <s:if test="hasActionMessages()">
       		  <div>
       				<s:actionmessage cssStyle="color:red;"/>
       		</div>
    	  </s:if>
    	  <table>
     <div>
    	    <span>Job ID:</span>
    	    <s:textfield name="cronTriggerDTO.tiggerId"  id="trigger_id" />
    	   
    	   </div>
     <tr>
      <td>Group:&nbsp;
       <s:select list="%{groupModelsList}" listKey="groupName" listValue="groupName" cssStyle="width:150px;" name="jobDTO.jobGroupName" id="jobGroupName" label="Job Group" headerValue="Please select a  Group"/>
      </td>
     </tr>
     <tr>
      <td>Job Class&nbsp;
       <s:select list="%{jobClassName}"  cssStyle="width:150px;" name="jobDTO.jobClassName" id="jobClassName" label="Job Class" headerValue="Please select job class"/>
      </td>
     </tr>
     <tr>
      <td>jobDetails&nbsp;
      <s:textarea name="jobDTO.jobDetails" id="className" ></s:textarea>
     </td>
     </tr>
     <tr>
      <td>Class Name&nbsp;<s:textfield name="jobDTO.className" id="className" label="Class Name"/></td>
     </tr>
     
     <tr>
      <td>Method Name&nbsp;<s:textfield name="jobDTO.methodName" id="methodName" label="Method Name"/></td>
     </tr>
    </table>
    	  
    	  
    	   <br/>
    	  
    	  <div>
    	   <span>Execution Interval</span><br/>
    	   
    	   <s:radio theme="vertical" name="cronTriggerDTO.executionInterval" list="%{executionInterval}"  onclick="getInputOptions(this);"/>
    	  </div>
    	  <br/>
    	  <div>
    	   <span>Please choose time and day</span><br/>
    	    <s:label>Start Time:&nbsp;&nbsp;</s:label>
    	    <s:textfield name="cronTriggerDTO.startDate" id="startDate" cssClass="picker" />
    	    <s:textfield name="cronTriggerDTO.startTime" id="startTime"/>
    	    <span>(Time should be in HH:MM:SS:AM/PM)</span> 
    	  </div><br/>
    	  
    	  <label>Frequency:</label>
    	  <div id="trigger_frequency">
    	  	
    	  </div>
    	  <br/>
    	  <br/>
    	   
    	  <s:submit value="Schedule Job"/>
       </s:form>

        </body>
       <script type="text/javascript">
        $(function() {
    		$( "#startDate" ).datepicker();
    		//$( "#startDate" ).formatDate('mm-dd-yyyy');
    	});
        function getInputOptions(selectedValue){  
        	 
        	if(selectedValue.value != "Once"  ){
				
        		$.ajax({
					type : "GET",
					url : "createTemplate?selectedExecutionInterval="+selectedValue.value,
				    success : function(html) {
					 var jsp=html;
				     $("#trigger_frequency").html(jsp);
					 $("#trigger_frequency").show();
				 },
				error:function(){alert("Communication error!");}
			});
    	  }
        	else{
        		$("#trigger_frequency").html("");
        	}
        }
        
      </script>
  