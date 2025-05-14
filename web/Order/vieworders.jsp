<%-- 
    Document   : vieworders
    Created on : May 2, 2025, 11:29:57 PM
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
                rights = seession_user.uvalidate("Supplier_Manager"); //Modify Here
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
    <title>Orders</title>
</head>
<body>

<h2>Order List</h2>

<table border="1" cellpadding="10" cellspacing="0">
    <tr>
        <th>Order ID</th>
        <th>Supplier ID</th>
        <th>Date</th>
        <th>Status</th>
        <th>Action</th>
    </tr>

    <c:forEach var="order" items="${orderList}">
        <tr>
            <td>${order.orderId}</td>
            <td>${order.supplierId}</td>
            <td>${order.orderDate}</td>
            <td>${order.status}</td>
            <td>
                <c:if test="${order.status == 'pending'}">
                    Pending
                </c:if>
                <c:if test="${order.status == 'received'}">
                    Received
                </c:if>
            </td>
        </tr>
    </c:forEach>

</table>

</body>
</html>
