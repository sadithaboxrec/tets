<%-- 
    Document   : editusers
    Created on : May 2, 2025, 11:32:52 PM
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

    <%@page import="com.DAO.userDAOImpl"%>
<%@page import="com.DB.DBConnect"%>
<%@page import="com.Entity.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h2>User Registration Form</h2>

			<%
			int user_id = Integer.parseInt(request.getParameter("id"));

			userDAOImpl dao=new userDAOImpl(DBConnect.getConn());
			User us=dao.getUserById(user_id);
			%>


 		 <form action="../editUsers" method="POST">
 		 
 		         <input type="hidden" name="user_id" value="<%=us.getUserId() %>">
 		 
			    <label for="name">Full Name:</label><br>
			    <input type="text" id="name" name="name"  value="<%=us.getName() %>" required><br><br>
			
			    <label for="email">Email:</label><br>
			    <input type="email" id="email" name="email" value="<%=us.getEmail() %>" required><br><br>
			
			    <label for="telephone">Telephone:</label><br>
			    <input type="tel" id="telephone" name="telephone" value="<%=us.getTelephone() %>"><br><br>
			
				<label for="role">User Role:</label><br>
				<select id="role" name="role" required>
				  <%
				    String role = us.getRole();
				  %>
				  <option value=""><%= role%></option>
				  <option value="Manager">Manager</option>
				  <option value="Warehouse_Manager">Warehouse Manager</option>
				  <option value="Supplier_Manager">Supplier Manager</option>
				</select><br><br>

			    

			        
			     <label for="userStatus">Book Status:</label>
                 <select id="bookStatus" name="status" required>
						<%
						if ("active".equals(us.getStatus())) {
						%>
						  <option value="active">Active</option>
						  <option value="inactive">Inactive</option>
						<%
						} else {
						%>
						  <option value="inactive">Inactive</option>
						  <option value="active">Active</option>
						<%
						}
						%>
                </select><br><br>
			
			    <label for="username">User Name:</label><br>
			    <input type="text" id="username" name="username" value="<%=us.getUsername() %>" required><br><br>
			
			    <label for="password">Password:</label><br>
			    <input type="password" id="password" name="password"  value="<%=us.getPassword() %>"required><br><br>
			    


			
			    <input type="submit" value="Submit">
  </form>

</body>
</html>
