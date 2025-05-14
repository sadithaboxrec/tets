<%-- 
    Document   : dispatches
    Created on : May 2, 2025, 11:25:21 PM
    Author     : DELL
--%>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>

<!-- User Roll Validation -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.Entity.User"%>
<%
            String rights = null;
            User seession_user = (User) session.getAttribute("userobj");
            if(seession_user!=null){
                rights = seession_user.uvalidate("Warehouse_Manager"); //Modify Here
            }
%>
<c:set var="rights" value="<%= rights%>" />
<c:if test="${empty rights }">
    <script>
        document.location.href = "${pageContext.request.contextPath}/unauthorized.jsp";
    </script>
</c:if>
<!-- End of User Roll Validation --> 

<html>
<head><title>Dispatch History</title></head>
<body>

<h2>Dispatched Products</h2>

<table border="1" cellpadding="8">
    <tr>
        <th>ID</th>
        <th>Product</th>
        <th>Quantity</th>
        <th>Destination</th>
        <th>Date</th>
        <th>Status</th>
        <th>Action</th>
        
    </tr>

    <c:forEach var="d" items="${dispatchList}">
        <tr>
            <td>${d.dispatchId}</td>
            <td>${d.productName}</td>
            <td>${d.quantity}</td>
            <td>${d.destination}</td>
            <td>${d.dispatchDate}</td>
            
            <td>${d.status}</td>
			<td>
			    <c:if test="${d.status == 'preparing'}">
			        preparing
			    </c:if>
			    <c:if test="${d.status == 'completed'}">
			        Done
			    </c:if>
			</td>
            
            
        </tr>
    </c:forEach>
</table>

</body>
</html>
