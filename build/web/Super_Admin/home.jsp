<%-- 
    Document   : home
    Created on : May 2, 2025, 11:32:58 PM
    Author     : DELL
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
                rights = seession_user.uvalidate("SU"); //Modify Here
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
<title>Super Admin</title>
</head>
<body>

<%@ include file="../components/navbar.jsp" %>


<h1>Super Admin Home</h1>

<a href="addUsers.jsp">Add Users </a>  <br><Br><br>
<a href="alladmins.jsp">All Admins </a>  <br><Br><br>
<a href="addUsers.jsp">Add Users </a>  <br><Br><br>
</body>
</html>