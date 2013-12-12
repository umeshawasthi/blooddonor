<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<<style>
.wpapi_pagination {
clear:both;
padding:20px 0;
position:relative;
font-size:11px;
line-height:13px;
}
 
.wpapi_pagination span, .wpapi_pagination a {
display:block;
float:left;
margin: 2px 2px 2px 0;
padding:6px 9px 5px 9px;
text-decoration:none;
width:auto;
color:#fff;
background: #555;
}
 
.wpapi_pagination a:hover{
color:#fff;
background: #3279BB;
}
 
.wpapi_pagination .current{
padding:6px 9px 5px 9px;
background: #3279BB;
color:#fff;
}
</style>

            <center><div class="wpapi_pagination">
            <span>page <s:property value="%{pagingObject.page_number}"/> of <s:property value="%{pagingObject.totalProfiles}"/></span>
			
			<%-- <s:if test='pagingObject.totalProfiles <= "3" '>
			     <s:if test='pagingObject.page_number == "1" '>
			        <span class="current">1</span>
			        <s:a href="#" onclick="nextpage('%{pagingObject.page_number+1}');"><s:property value="%{pagingObject.page_number+1}"/></s:a>
			        <s:a href="#" onclick="nextpage('%{pagingObject.page_number+2}');"><s:property value="%{pagingObject.page_number+2}"/></s:a>
			     </s:if>
			    <s:elseif test='pagingObject.page_number == "2" '>
			         <s:a href="#" onclick="nextpage('%{pagingObject.page_number-1}');"><s:property value="%{pagingObject.page_number-1}"/></s:a>
			         <span class="current">2</span>
			        <s:a href="#" onclick="nextpage('%{pagingObject.page_number+1}');"><s:property value="%{pagingObject.page_number+1}"/></s:a>
			    </s:elseif>
			    <s:else>
			          <s:a href="#" onclick="nextpage('%{pagingObject.page_number-2}');"><s:property value="%{pagingObject.page_number-2}"/></s:a>
			          <s:a href="#" onclick="nextpage('%{pagingObject.page_number-1}');"><s:property value="%{pagingObject.page_number-1}"/></s:a>
			          <span class="current">3</span>
			    </s:else>
			</s:if>
			<s:else> --%>
			<s:if test='pagingObject.page_number > "1" '>
				<s:a href="#" onclick="nextpage(%{pagingObject.previous});"><< Previous</s:a>
				<s:a href="#" onclick="nextpage('1');">1</s:a>
			</s:if> 
			<s:else>
				<%--  <span class="inactive"><< Previous</span> --%>
				 <span class="current">1</span>
			</s:else>
			
			
			 <s:if test='pagingObject.page_number > "2" '>
				  <s:if test='(pagingObject.page_number == "3") && (pagingObject.totalProfiles == "3") '>
				      <s:a href="#" onclick="nextpage('%{pagingObject.page_number-1}');"><s:property value="%{pagingObject.page_number-1}"/></s:a>
				  </s:if>
				  <s:else>
						<span> ... </span>
				   </s:else>
			 	   	 	<s:if test='(pagingObject.page_number == pagingObject.totalProfiles) && (pagingObject.page_number > "3")'>
						<s:a href="#" onclick="nextpage('%{pagingObject.page_number-2}');"><s:property value="%{pagingObject.page_number-2}"/></s:a>
						<s:a href="#" onclick="nextpage('%{pagingObject.page_number-1}');"><s:property value="%{pagingObject.page_number-1}"/></s:a>
					 </s:if>
					
			</s:if>
		    <s:if test='(pagingObject.page_number != "1") && (pagingObject.page_number != pagingObject.totalProfiles)'>
				<s:if test='(pagingObject.page_number != "2")'>
					<s:a href="#" onclick="nextpage('%{pagingObject.page_number-1}');"><s:property value="%{pagingObject.page_number-1}"/></s:a>
				</s:if>
				<span class="current"><s:property value="pagingObject.page_number" /></span>
			</s:if> 
			<s:if test="%{pagingObject.page_number < ((pagingObject.totalProfiles)-1)}">
			 	<s:a href="#" onclick="nextpage('%{pagingObject.page_number+1}');"><s:property value="%{pagingObject.page_number+1}"/></s:a>
			   	<s:if test='(pagingObject.page_number == "1") && (pagingObject.totalProfiles > "3"))'>
					<s:a href="#" onclick="nextpage('%{pagingObject.page_number+2}');"><s:property value="%{pagingObject.page_number+2}"/></s:a>
				 </s:if>
					<s:if test='(pagingObject.page_number != "3") && (pagingObject.totalProfiles != "3") '>
					 <span> ...</span>
					</s:if>
			  </s:if> 
		  
		    <s:if test="%{(totalCount) > (pagingObject.record_size)}">
			    <s:if test='%{(pagingObject.page_number) == (pagingObject.totalProfiles)}'>
			     <span class="current"><s:property value="pagingObject.page_number" /></span>
			</s:if>
			<s:else>
			    <s:a href="#" onclick="nextpage('%{pagingObject.totalProfiles}');"><s:property value="%{pagingObject.totalProfiles}"/></s:a>
			    </s:else>
			</s:if>
				<s:if test='%{(pagingObject.page_number) < (pagingObject.totalProfiles)}'>
			    <s:a href="#" onclick="nextpage('%{pagingObject.next}');">Next >></s:a>
			</s:if> 
			
			<s:else>
		     	  <s:if test='(pagingObject.totalProfiles > "1") && (pagingObject.page_number !=pagingObject.totalProfiles)  '>
		     	   <span class="disabled">Next >></span>
		     	  </s:if>
			</s:else>
		<%-- </s:else> --%>
	</div></center>

<div id="page_size" style="float: right;margin-top: 20px" class="wpapi_pagination">
<s:if test='pagingObject.record_size == "5"'>
<span class="current">5</span>
<s:a href="#" onclick="setPageSize('10');">10</s:a>
</s:if>
<s:else>
<s:a href="#" onclick="setPageSize('5');">5</s:a>
<span class="current">10</span>

</s:else>


<span style="border:medium none;">per page</span>
</div>


<s:hidden name="totalCount" value="%{totalCount}"/>
<s:hidden name="p" value="1" id="p"/>
<s:hidden name="pageSize" value="%{pagingObject.record_size}" id="pageSize"/>