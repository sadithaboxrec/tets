<%-- 
    Document   : location
    Created on : 10 May 2025, 23:40:27
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

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title> Stock Location Home Page</title>
</head>
<body>

<a href="addlocation.jsp">Add Location</a> <br><br>

<a href="viewlocation.jsp">View Locations</a> <br><br>

</body>
</html>
