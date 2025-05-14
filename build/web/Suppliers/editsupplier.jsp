<%-- 
    Document   : editsupplier
    Created on : May 2, 2025, 11:33:41 PM
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

<%@ page import="com.DAO.SupplierDAOImpl" %>

<%@page import="com.DB.DBConnect"%>
<%@page import="com.Entity.Supplier"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Category</title>
    </head>
    <body>
        
        			
			<%
			   int id=Integer.parseInt(request.getParameter("id"));
			SupplierDAOImpl dao=new SupplierDAOImpl(DBConnect.getConn());
			Supplier s=dao.getSupplierById(id);
			%>
                        
                        
            <form action="${pageContext.request.contextPath}/editSupplier" method="post">
                
                <input type="hidden" name="id" value="<%=s.getSupp_id() %>">
            
                <label for="supp_name">Supplier Name:</label>
                <input type="text" id="supp_name" name="supp_name" value="<%=s.getSupp_name() %>" required><br><br>

                <label for="supp_email">Supplier Email</label>   
                <input type="email" id="supp_email" name="supp_email" value="<%=s.getSupp_email() %>" required><br><br>
                
                <label for="supp_cno">Supplier Number</label>   
                <input type="tel" id="supp_cno" name="supp_cno" value="<%=s.getSupp_cno() %>" required><br><br>
                
                <input type="submit" value="Update">
            
        </form>
    </body>
</html>
