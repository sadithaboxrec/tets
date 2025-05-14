<%-- 
    Document   : home
    Created on : May 2, 2025, 11:28:24 PM
    Author     : DELL
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- User Roll Validation -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="com.Entity.User"%>
<%
            String roll = null;
            User seession_user = (User) session.getAttribute("userobj");
            if(seession_user!=null){
                roll = seession_user.getRole();
            }
%>
<c:set var="roll" value="<%= roll%>" />
<!-- End of User Roll Validation --> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manager Home</title>
</head>
<body>
<%@ include file="../components/navbar.jsp" %>
<h1>Dashboard</h1>
<c:if test="${roll == 'SU'}">
    <!-- SUPER USER --> 
  users>
  <a href="${pageContext.request.contextPath}/Super_Admin/addUsers.jsp">Add Users </a>  <br><Br><br>
  <a href="${pageContext.request.contextPath}/Super_Admin/alladmins.jsp">All Admins </a>  <br><Br><br>
  <a href="${pageContext.request.contextPath}/Super_Admin/addUsers.jsp">Add Users </a>  <br><Br><br>                              
</c:if>

  <c:if test="${roll == 'Manager'}">
    <!-- Manager -->
    Products >
    <a href="${pageContext.request.contextPath}/add_products">Add Products</a>
    <a href="${pageContext.request.contextPath}/Product/viewproducts.jsp">View Products</a>

    Stock Locations >
    <a href="${pageContext.request.contextPath}/stocklocation/addlocation.jsp">Add Location</a> <br><br>
    <a href="${pageContext.request.contextPath}/stocklocation/viewlocation.jsp">View Locations</a> <br><br>

    Categories >
    <a href="${pageContext.request.contextPath}/add_category">Add Category</a> <br><br>
    <a href="${pageContext.request.contextPath}/Category/viewcategory.jsp">View Category</a> <br><br>

    Supplier >
    <a href="${pageContext.request.contextPath}/Suppliers/addsupplier.jsp">Add Supplier</a> <br><br>
    <a href="${pageContext.request.contextPath}/Suppliers/viewsupplier.jsp">View Supplier</a> <br><br>

    Dispatch >
    <a href="${pageContext.request.contextPath}/send_order">Dispatch Products</a>
  </c:if>

    <c:if test="${roll == 'Supplier_Manager'}">
        <!-- Supplier_Manager -->
        Orders >
        <a href="${pageContext.request.contextPath}/add_orders">Add Orders </a>  <br><br>
        <a href="${pageContext.request.contextPath}/ViewOrderServlet">View Orders</a> <br><br><!-- view only -->
    </c:if>

<c:if test="${roll == 'Warehouse_Manager'}">
    <!-- Warehouse_Manager -->

    Orders >
    <a href="${pageContext.request.contextPath}/ViewOrderServlet">View Orders</a> <br><br><!-- Editable -->

    Dispatch >
    <a href="${pageContext.request.contextPath}/view_dispatches">View Dispatching Orders</a><!-- Editable -->
</c:if>

   









</body>
</html>