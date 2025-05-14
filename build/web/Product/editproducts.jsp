<%-- 
    Document   : addproducts
    Created on : May 2, 2025, 11:30:36 PM
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


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Product : ${product.pro_name}</title>
</head>
<body>
<h1>Edit Product : ${product.pro_name}</h1>

			<c:if test="${not empty SuccessMsg }">
			  <p> ${SuccessMsg} </p>
			  <c:remove var="SuccessMsg"  scope="session" />
			</c:if>
			
			<c:if test="${not empty failedMsg }">
			  <p> ${failedMsg} </p>
			  <c:remove var="failedMsg"  scope="session" />
			</c:if>
			


<form action="${pageContext.request.contextPath}/edit_products" method="post" enctype="multipart/form-data">
        <input type="text" id="pro_id" name="pro_id" value="${product.pro_id}" hidden><br><br>
        
        <label for="pro_name">Product Name:</label>
        <input type="text" id="pro_name" name="pro_name" value="${product.pro_name}" required><br><br>

        <label for="pro_stock">Stock:</label>
        <input type="number" id="pro_stock" name="pro_stock" value="${product.pro_stock}" required><br><br>

        
		<label for="pro_category">Product Category:</label>
		<select id="pro_category" name="pro_category" required>
		    <option value="" disabled selected>--Select--</option>
		    <c:forEach var="cat" items="${categoryList}">
		        <option value="${cat.categoryId}">${cat.categoryName}</option>
		    </c:forEach>
		</select><br><br>
		
		<label for="pro_supplier">Product Supplier:</label>
		<select id="pro_supplier" name="pro_supplier" required>
		    <option value="" disabled selected>--Select--</option>
		    <c:forEach var="sup" items="${supplierList}">
		        <option value="${sup.supp_id}">${sup.supp_name}</option>
		    </c:forEach>
		</select><br><br>




        <label for="pro_photo">Upload Photo:</label>
        <input type="file" id="pro_photo" name="pro_photo" accept="image/*" required><br><br>

        <input type="submit" value="Submit">
    </form>

</body>
</html>