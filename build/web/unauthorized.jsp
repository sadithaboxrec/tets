<%-- 
    Document   : unauthorized
    Created on : 10 May 2025, 23:13:19
    Author     : dines
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Unauthorized Section </title>
    </head>
    <body>
        <h1>Unauthorized Section, Please Login with authorized user.</h1>
         <a href="${pageContext.request.contextPath}/LogoutServlet" class="btn btn-primary" role="button">Log Out</a>
    </body>
</html>
