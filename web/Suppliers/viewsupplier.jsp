<%-- 
    Document   : viewsupplier
    Created on : May 2, 2025, 11:34:25 PM
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

<%@page import="com.DAO.SupplierDAOImpl" %>

<%@page import="com.DB.DBConnect"%>
<%@page import="java.util.List"%>
<%@page import="com.Entity.Supplier"%>


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
                          <th>Supplier Id</th>
                           <th>Supplier Name</th>
                          <th>Supplier Email</th>
                          <th>Supplier Contact No</th>
                          <th>Action</th>
                        </tr>
		  </thead>
		  <tbody>
		  
		  <% 
		  SupplierDAOImpl dao=new SupplierDAOImpl(DBConnect.getConn());
		  List<Supplier> list= dao.getSupplier();
		  
		  for(Supplier s:list){
			  %>
			 
                    <tr>
                        <td><%= s.getSupp_id() %></td>
                        <td><%= s.getSupp_name() %></td>
                        <td><%= s.getSupp_email() %></td>
                        <td><%= s.getSupp_cno() %></td>
                        <td>
                            <a href="editsupplier.jsp?id=<%=s.getSupp_id()%>">Edit</a>
                        </td>
                    </tr>

			  
			  <%
		  }
		  %>
    </body>
</html>
