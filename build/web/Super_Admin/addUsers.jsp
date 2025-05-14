<%-- 
    Document   : addUsers
    Created on : May 2, 2025, 11:32:24 PM
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

<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../components/navbar.jsp" %>

<h2>User Registration Form</h2>

			<c:if test="${not empty SuccessMsg }">
			  <p> $ {SuccessMsg} </p>
			  <c:remove var="SuccessMsg"  scope="session" />
			</c:if>
			
			<c:if test="${not empty failedMsg }">
			  <p> $ {failedMsg} </p>
			  <c:remove var="failedMsg"  scope="session" />
			</c:if>

 		 <form action="../userRegister" method="POST"  enctype="multipart/form-data">
 		 
			    <label for="name">Full Name:</label><br>
			    <input type="text" id="name" name="name" required><br><br>
			
			    <label for="email">Email:</label><br>
			    <input type="email" id="email" name="email" required><br><br>
			
			    <label for="telephone">Telephone:</label><br>
			    <input type="tel" id="telephone" name="telephone"><br><br>
			
			    <label for="role">User Role:</label><br>
			    <select id="role" name="role" required>
			      <option value="">--Select Role--</option>
			      <option value="Manager">Manager</option>
			      <option value="Warehouse_Manager">Warehouse Manager</option>
			      <option value="Supplier_Manager">Supplier Manager</option>
			    </select><br><br>
                            
                            
			    
			     <label for="userStatus">User Status:</label>
			        <select id="userStatus" name="status" required>
			            <option selected>--Select--</option>
			            <option value="active">Active</option>
			            <option value="inactive">Inactive</option>
			        </select><br><br>
			
			    <label for="username">User Name:</label><br>
			    <input type="text" id="username" name="username" required><br><br>
			
			    <label for="password">Password:</label><br>
			    <input type="password" id="password" name="password" required><br><br>
			    
			    <label for="userPhoto">Upload Photo:</label>
                 <input type="file" id="userPhoto" name="userPhoto" accept="image/*" required><br><br>
			
			    <input type="checkbox" id="terms" name="check" required>
			    <label for="check"> Adding the User Confirmed</label><br><br>

			
			    <input type="submit" value="Submit">
  </form>

</body>
</html>