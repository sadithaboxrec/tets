<%-- 
    Document   : login
    Created on : May 2, 2025, 11:12:13 PM
    Author     : DELL
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page isELIgnored="false" %>



<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Form</title>
</head>
<body>
    <h2>Login Form</h2>
    
    <c:if test="${not empty failedMsg}">
      <h5>${failedMsg}</h5>
      <c:remove var="failedMsg" scope="session"/>
    </c:if>
    
    
    <form action="LoginServlet" method="POST">
        <label for="username">User Name:</label><br>
        <input type="text" id="text" name="username" required><br><br>
        
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password" required><br><br>
        
        <button type="submit">Login</button>
    </form>
</body>
</html>

