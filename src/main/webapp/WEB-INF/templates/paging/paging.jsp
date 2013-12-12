<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:if test='pagingObject.totalProfiles > "1" '>
<div class="pagerRowBot">
	<div class="pagination">
		<ul>
			<%-- <span>page <s:property value="%{pagingObject.page_number}"/> of <s:property value="%{pagingObject.totalProfiles}"/></span> --%>

			<s:if test='pagingObject.page_number > "1" '>
				<li><s:a href="#" cssClass="previous"
						onclick="nextpage(%{pagingObject.previous});">Previous</s:a>
				</li>
				<li><s:a href="#" onclick="nextpage('1');" title="1">1</s:a>
				</li>
			</s:if>
			<s:else>
				<%--  <span class="inactive"><< Previous</span> --%>
				<li><span class="current" title="1">1</span>
				</li>
			</s:else>

			<s:if test='pagingObject.page_number > "2" '>
				<s:if
					test='(pagingObject.page_number == "3") && (pagingObject.totalProfiles == "3") '>
					<li><s:a href="#"
							onclick="nextpage('%{pagingObject.page_number-1}');">
							<s:property value="%{pagingObject.page_number-1}" />
						</s:a>
					</li>
				</s:if>
				<s:else>
					<li><span class="dots"> ... </span>
					</li>
				</s:else>
				<s:if
					test='(pagingObject.page_number == pagingObject.totalProfiles) && (pagingObject.page_number > "3")'>
					<li><s:a href="#"
							onclick="nextpage('%{pagingObject.page_number-2}');">
							<s:property value="%{pagingObject.page_number-2}" />
						</s:a>
					</li>
					<li><s:a href="#"
							onclick="nextpage('%{pagingObject.page_number-1}');">
							<s:property value="%{pagingObject.page_number-1}" />
						</s:a>
					</li>
				</s:if>

			</s:if>
			<s:if
				test='(pagingObject.page_number != "1") && (pagingObject.page_number != pagingObject.totalProfiles)'>
				<s:if test='(pagingObject.page_number != "2")'>
					<li><s:a href="#"
							onclick="nextpage('%{pagingObject.page_number-1}');">
							<s:property value="%{pagingObject.page_number-1}" />
						</s:a>
					</li>
				</s:if>
				<li><span class="current"><s:property
							value="pagingObject.page_number" />
				</span>
				<li>
			</s:if>
			<s:if
				test="%{pagingObject.page_number < ((pagingObject.totalProfiles)-1)}">
				<li><s:a href="#"
						onclick="nextpage('%{pagingObject.page_number+1}');">
						<s:property value="%{pagingObject.page_number+1}" />
					</s:a>
				</li>
				<s:if
					test='(pagingObject.page_number == "1") && (pagingObject.totalProfiles > "3"))'>
					<li><s:a href="#"
							onclick="nextpage('%{pagingObject.page_number+2}');">
							<s:property value="%{pagingObject.page_number+2}" />
						</s:a>
					</li>
				</s:if>
				<s:if
					test='(pagingObject.page_number != "3") && (pagingObject.totalProfiles != "3") '>
					<li><span class="dots"> ... </span>
					</li>
				</s:if>
			</s:if>

			<s:if test="%{(totalCount) > (pagingObject.record_size)}">
				<s:if
					test='%{(pagingObject.page_number) == (pagingObject.totalProfiles)}'>
					<li><span class="current"><s:property
								value="pagingObject.page_number" />
					</span>
					<li>
				</s:if>
				<s:else>
					<li><s:a href="#"
							onclick="nextpage('%{pagingObject.totalProfiles}');">
							<s:property value="%{pagingObject.totalProfiles}" />
						</s:a>
					</li>
				</s:else>
			</s:if>
			<s:if
				test='%{(pagingObject.page_number) < (pagingObject.totalProfiles)}'>
				<li><s:a href="#" cssClass="next"
						onclick="nextpage('%{pagingObject.next}');">Next</s:a>
				</li>
			</s:if>

			<s:else>
				<s:if
					test='(pagingObject.totalProfiles > "1") && (pagingObject.page_number !=pagingObject.totalProfiles)  '>
					<li><span class="disabled">Next</span>
					</li>
				</s:if>
			</s:else>
			<%-- </s:else> --%>
		</ul>
	</div>
</div>
</s:if>
<s:hidden name="totalCount" value="%{totalCount}" />
<s:hidden name="p" value="1" id="p" />
<s:hidden name="pageSize" value="%{pagingObject.record_size}"
	id="pageSize" />