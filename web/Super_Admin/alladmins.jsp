<%-- 
    Document   : alladmins
    Created on : May 2, 2025, 11:32:37 PM
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


    <%@page import="com.DAO.userDAOImpl"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Admins</title>
</head>
<body>
<%@ include file="../components/navbar.jsp" %>
<h1>All Admins</h1>


						<c:if test="${not empty SuccessMsg }">
			  <p> $ {SuccessMsg} </p>
			  <c:remove var="SuccessMsg"  scope="session" />
			</c:if>
			
			<c:if test="${not empty failedMsg }">
			  <p> $ {failedMsg} </p>
			  <c:remove var="failedMsg"  scope="session" />
			</c:if>
			


		<table>
		  <thead>
		    <tr>
		      <th>Id</th>
              <th>Name</th>
		      <th>Email</th>
		      <th>Telephone</th>
		      <th>Username</th>
		      <th>Status</th>
		      <th>Role</th>
		      <th>Photo</th>
		      <th>Edit Details</th>
		    </tr>
		  </thead>
		  <tbody>
		  
		  <% 
		  userDAOImpl dao=new userDAOImpl(DBConnect.getConn());
		  List<User> list= dao.getAllAdmins();
		  
		  for(User us:list){
			  %>
			 
			<tr>
			  <td><%=us.getUserId() %></td>
		      <td> <%=us.getName() %> </td>
		      <td> <%=us.getEmail() %></td>
		      <td> <%=us.getTelephone() %></td>
		     <td> <%=us.getUsername() %></td>
		      <td><%= us.getStatus() %></td>
		      <td><%=us.getRole() %> </td>
		      <td> <img src="../images/users/<%=us.getPhotoName() %>"  style="width:50px; height:50px;"> </td>
		      <td>
		         <a href="editusers.jsp?id=<%=us.getUserId() %>">Edit</a>
		      </td>
		    </tr>
			  
			  <%
		  }
		  %>


		  </tbody>
		</table>

</body>
</html>