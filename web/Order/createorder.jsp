<%-- 
    Document   : createorder
    Created on : May 2, 2025, 11:29:30 PM
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
<head><title>Create Order</title></head>
<body>

<h2>Create Order for Supplier ID: ${supplierId}</h2>

<form action="${pageContext.request.contextPath}/submit_order" method="POST">

  <input type="hidden" name="supplierId" value="${supplierId}" />
		
		  <table border="1">
		  
		    <tr>
				    <th>Product Name</th>
				    <th>Current Stock</th>
				    <th>Product Image</th>
				    <th>Order Quantity</th>
		    </tr>
		    
		    <c:forEach var="p" items="${productList}">
		    
		      <tr>
		        <td>${p.pro_name}</td>
		        <td>${p.pro_stock}</td>
		        <td>
		            <img src="${pageContext.request.contextPath}/images/products/${p.pro_photo}" style="width:50px; height:50px;" />
		        </td>
		        <td>
		          <input type="number" name="qty_${p.pro_id}" min="0" value="0" />
		        </td>
		      </tr>
		    </c:forEach>
		    
		  </table>
		
		  <input type="submit" value="Place Order" />
  
</form>

</body>
</html>
