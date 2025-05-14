<%-- 
    Document   : sendproducts
    Created on : May 2, 2025, 11:26:25 PM
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

<h2>Send Products from Category ID: ${categoryId}</h2>

<form action="${pageContext.request.contextPath}/dispatchproducts" method="POST">
    <input type="hidden" name="categoryId" value="${categoryId}" />
    <label>Destination:</label>
    <input type="text" name="destination" required /><br><br>

    <table border="1">
        <tr>
            <th>Product</th>
            <th>Stock</th>
            <th>Image</th>
            <th>Send Quantity</th>
        </tr>

        <c:forEach var="p" items="${productList}">
            <tr>
                <td>${p.pro_name}</td>
                <td>${p.pro_stock}</td>
                <td><img src="${pageContext.request.contextPath}/images/products/${p.pro_photo}" width="50" /></td>
                <td>
                    <input type="number" name="qty_${p.pro_id}" value="0" min="0" />
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <input type="submit" value="Send Products" />
</form>


</body>
</html>