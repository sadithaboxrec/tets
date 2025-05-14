<%-- 
    Document   : viewlocation
    Created on : 10 May 2025, 22:58:49
    Author     : dines
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
		      <th>Location Name</th>
		      <th>Location Status</th>
                      <th>Action</th>
		    </tr>
		  </thead>
		  <tbody>
		  
		  <% 
		  StklocationDAO dao=new StklocationDAO(DBConnect.getConn());
		  List<stcklocation> list= dao.getlockations();
                  
		  for(stcklocation loc:list){
			  %>
			 
			<tr>
			  <td><%=loc.getLocId() %></td>
		      <td> <%=loc.getLocName()  %> </td>
                      <td> <%=loc.getLocStatus() %></td>
		      <td>
		         <a href="${pageContext.request.contextPath}/stocklocation/editlocation.jsp?id=<%=loc.getLocId()%>">Edit</a>
		      </td>
		    </tr>
			  
			  <%
		  }
		  %>
    </body>
</html>

