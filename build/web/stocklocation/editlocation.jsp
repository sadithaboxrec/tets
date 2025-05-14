<%-- 
    Document   : editlocation
    Created on : 10 May 2025, 22:58:22
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
        <title>Edit Location</title>
    </head>
    <body>
        
        			
			<%
			   int id=Integer.parseInt(request.getParameter("id"));
			StklocationDAO dao=new StklocationDAO(DBConnect.getConn());
			stcklocation loc=dao.getlockationById(id);
			%>
                        
                        
            <form action="${pageContext.request.contextPath}/editstcklocation" method="post">
                
                <input type="hidden" name="id" value="<%=loc.getLocId() %>">
            
                 <label for="Location Name">Location Name:</label>
                <input type="text" id="stcklocationName" name="stcklocationName" value="<%=loc.getLocName() %>" required><br><br>

                <label for="Location Status">Location Status:</label>   
                <select id="stcklocationstatus" name="stcklocationstatus" required>
                                        <%
                                        if ("active".equals(loc.getLocStatus())) {
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
                <input type="submit" value="Update">
            
        </form>
    </body>
</html>
