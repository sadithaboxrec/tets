<%-- 
    Document   : addlocation
    Created on : 10 May 2025, 23:40:18
    Author     : dines
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!-- User Roll Validation -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.Entity.User"%>
<%
            String rights = null;
            User seession_user = (User) session.getAttribute("userobj");
            if(seession_user!=null){
                rights = seession_user.uvalidate("Manager"); //Modify Here
            }
%>
<c:set var="rights" value="<%= rights%>" />
<c:if test="${empty rights }">
    <script>
        document.location.href = "${pageContext.request.contextPath}/unauthorized.jsp";
    </script>
</c:if>
<!-- End of User Roll Validation --> 

<%@page isELIgnored="false" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD Warehouse Store Location</title>
    </head>
    <body>
        
        <c:if test="${not empty SuccessMsg }">
			  <p> ${SuccessMsg} </p>
			  <c:remove var="SuccessMsg"  scope="session" />
	</c:if>
			
        <c:if test="${not empty failedMsg }">
			  <p> ${failedMsg} </p>
			  <c:remove var="failedMsg"  scope="session" />
	</c:if>
        
        <form action="${pageContext.request.contextPath}/add_stklocation" method="post">
            
                <label for="Location Name">Location Name:</label>
                <input type="text" id="stcklocationName" name="stcklocationName" required><br><br>
                
                <label for="Location Status">Location Status:</label>
                <select id="stcklocationstatus" name="stcklocationstatus" required>
                    <option selected>--Select--</option>
                    <option value="active">Active</option>
                    <option value="inactive">Inactive</option>
                </select>
                
                <input type="submit" value="Submit">
            
        </form>
        
        
        
    </body>
</html>
