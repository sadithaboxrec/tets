<%-- 
    Document   : addcategory
    Created on : May 2, 2025, 11:19:20 PM
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
        <title>ADD CATEGORY</title>
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
        
        <form action="${pageContext.request.contextPath}/add_category" method="post">
            
                <label for="categoryName">Category Name:</label>
                <input type="text" id="categoryName" name="categoryName" required><br><br>

                <label for="categoryStatus">Category Status:</label>
                <select id="categoryStatus" name="categoryStatus" required>
                    <option selected>--Select--</option>
                    <option value="active">Active</option>
                    <option value="inactive">Inactive</option>
                </select><br><br>
                
                <label for="stkloc">Warehouse Stock Location:</label>
		<select id="stkloc_id" name="stkloc_id" required>
		    <option value="" disabled selected>--Select--</option>
                    
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
                <input type="submit" value="Submit">
            
        </form>
        
        
        
    </body>
</html>
