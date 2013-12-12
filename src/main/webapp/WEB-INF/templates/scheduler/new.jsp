<%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@taglib uri="/struts-tags" prefix="s"%>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>Pending Donation </title>

        </head>
        <body>
      
       <s:form theme="simple" id="salaryDetails" name="salaryDetails">
    	<table>
        <tr>
            <td>Name</td>
            <td>Basic</td>
            //etc//
        </tr>

        <s:iterator value="annextures" var="annex">
            <tr>
                <td><s:textfield name="emplName" value="%{name}"/></td>
              
            </tr>
        </s:iterator>
    </table>
</s:form>

        </body>
    </html>
