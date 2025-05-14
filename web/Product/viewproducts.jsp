<%-- 
    Document   : viewproducts
    Created on : May 2, 2025, 11:31:02 PM
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

<%@page isELIgnored="false" %>
<%@page import="com.DAO.ProductDAOImpl"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="java.util.List"%>
<%@page import="com.Entity.Products"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Products</title>
</head>
<body>

<h1>All Products</h1>


			<c:if test="${not empty SuccessMsg }">
			  <p> ${SuccessMsg} </p>
			  <c:remove var="SuccessMsg"  scope="session" />
			</c:if>
			
			<c:if test="${not empty failedMsg }">
			  <p> ${failedMsg} </p>
			  <c:remove var="failedMsg"  scope="session" />
			</c:if>
			
			


		<table>
		  <thead>
		    <tr>
		      <th>Id</th>
		      <th>Image</th>
		      <th>Product Name</th>
		      <th>Category</th>
		      <th>Supplier</th>
		      <th>Stock</th>
		      <th>User</th>
		      <th>Action</th>
		    </tr>
		  </thead>
		  <tbody>
		  
		  <% 
		  ProductDAOImpl dao=new ProductDAOImpl(DBConnect.getConn());
		  List<Products> list= dao.getAllProducts();
		  
		  for(Products p:list){
			  %>
			 
			<tr>
			  <td>  <%= p.getPro_id() %> </td>
		      <td> <img src="../images/products/<%=p.getPro_photo() %>"  style="width:50px; height:50px;"> </td>
		      <td> <%= p.getPro_name() %> </td>
		      <td> <%=p.getCategoryName() %></td>
		      <td> <%=p.getSuppName() %></td>
		      <td><%=p.getPro_stock() %></td>
		      <td><%= p.getUser() %></td>
		      <td>
		         <a href="${pageContext.request.contextPath}/edit_products?id=<%=p.getPro_id() %>">Edit</a>
		      </td>
		    </tr>
			  
			  <%
		  }
		  %>


		  </tbody>
		</table>

</body>
</html>