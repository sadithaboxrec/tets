<%-- 
    Document   : addsuppliers
    Created on : May 2, 2025, 11:33:25 PM
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Supplier</title>
    </head>
    <body>
        <c:if test="${not empty SuccessMsg }">
            <p> ${SuccessMsg} </p>
            <c:remove var="SuccessMsg"  scope="session" />
	</c:if>
			
        <c:if test="${not empty failedMsg }">
            <p> ${failedMsg} </p>
            <c:remove var="failedMsg"  scope="session" />
	</c:if>
        
        <form action="${pageContext.request.contextPath}/add_supplier" method="post">
            
                <label for="supp_name">Supplier Name:</label>
                <input type="text" id="supp_name" name="supp_name" required><br><br>

                <label for="supp_email">Supplier Email</label>
                <input type="email" id="supp_email" name="supp_email" required><br><br>
                
                <label for="supp_cno">Supplier Number</label>
                <input type="tel" id="supp_cno" name="supp_cno" required><br><br>
                
                
                <input type="submit" value="Submit">
            
        </form>
        
    </body>
</html>
