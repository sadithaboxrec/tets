<%-- 
    Document   : selectcategory
    Created on : May 2, 2025, 11:26:08 PM
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
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/send_order" method="GET">

    <label for="category">Select Category:</label>
    
		<select name="cat_id" required>
		    <option value="" disabled selected>--Select--</option>
		    <c:forEach var="cat" items="${categoryList}">
		        <option value="${cat.categoryId}">${cat.categoryName}</option>
		    </c:forEach>
		</select>

    <br><br>
    
    <input type="submit" value="Next">
</form>


</body>
</html>
