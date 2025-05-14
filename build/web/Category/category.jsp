<%-- 
    Document   : category
    Created on : May 2, 2025, 11:22:53 PM
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
<title> Category Home Page</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/add_category">Add Category</a> <br><br>

<a href="viewcategory.jsp">View Category</a> <br><br>

</body>
</html>