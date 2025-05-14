<%-- 
    Document   : editcategory
    Created on : May 2, 2025, 11:23:10 PM
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
        <title>Edit Category</title>
    </head>
    <body>
        
        			
			<%
			   int id=Integer.parseInt(request.getParameter("id"));
			CategoryDAOImpl dao=new CategoryDAOImpl(DBConnect.getConn());
			Category c=dao.getCategoryById(id);
                        String StockLOC = new StklocationDAO(DBConnect.getConn()).getlockationById(id).getLocName();
                        
			%>
                        
                        
            <form action="${pageContext.request.contextPath}/editCategory" method="post">
                
                <input type="hidden" name="id" value="<%=c.getCategoryId() %>">
            
                <label for="categoryName">Category Name:</label>
                <input type="text" id="categoryName" name="categoryName" value="<%=c.getCategoryName() %>" required><br><br>

                <label for="categoryStatus">Category Status:</label>   
                <select id="categoryStatus" name="categoryStatus" required>
                                        <%
                                        if ("Active".equals(c.getCategoryStatus())) {
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
                 </select>
                 <br>
                <label for="pro_category">Warehouse Stock Location:</label>
		<select id="pro_category" name="pro_category" required>
		    <option value="" disabled selected><%=StockLOC %></option>
                <% 
                    StklocationDAO stckLocDAO = new StklocationDAO(DBConnect.getConn());  
                    List<stcklocation> stckLocList = stckLocDAO.getlockations();
                  
		  for(stcklocation loc:stckLocList){
		%>
                    
		        <option value="<%=loc.getLocId()%>"><%=loc.getLocName()%></option>
		
                <%
		  }
		%>
                </select><br><br>

                
                <input type="submit" value="Update">
            
        </form>
    </body>
</html>
