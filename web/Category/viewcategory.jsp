<%-- 
    Document   : viewcategory
    Created on : May 2, 2025, 11:23:24 PM
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

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page isELIgnored="false" %>

<%@ page import="com.DAO.CategoryDAOImpl" %>
<%@ page import="com.DAO.StklocationDAO" %>

<%@page import="com.DB.DBConnect"%>
<%@page import="java.util.List"%>
<%@page import="com.Entity.Category"%>
<%@page import="com.Entity.stcklocation"%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All Categories </title>
    </head>
    <body>
        <h1>Category List!</h1>
        
        
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
		      <th>Category Name</th>
		      <th>Category Status</th>
                      <th>Warehouse Store Location</th>
                      <th>Action</th>
		    </tr>
		  </thead>
		  <tbody>
		  
		  <% 
		  CategoryDAOImpl dao=new CategoryDAOImpl(DBConnect.getConn());
		  List<Category> list= dao.getCategory();
                  
		  for(Category c:list){
			  %>
			 
			<tr>
			  <td><%=c.getCategoryId() %></td>
		      <td> <%= c.getCategoryName() %> </td>
                      <td> <%=c.getCategoryStatus() %></td>
		      <td> <%= new StklocationDAO(DBConnect.getConn()).getlockationById(c.getLocId()).getLocName() %></td>
		      <td>
		         <a href="editcategory.jsp?id=<%=c.getCategoryId()%>">Edit</a>
		      </td>
		    </tr>
			  
			  <%
		  }
		  %>
    </body>
</html>
