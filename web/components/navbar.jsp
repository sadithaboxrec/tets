<%-- 
    Document   : navbar
    Created on : May 2, 2025, 11:24:09?PM
    Author     : DELL
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page isELIgnored="false" %>


<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-SgOJa3DmI69IUzQ2PVdRZhwQ+dy64/BUtbMJw1MZ8t5HZApcHrRKUc4W0kG879m7" crossorigin="anonymous">

  <style>
    body {
      margin: 0;
      font-family: Arial, sans-serif;
    }
    .navbar {
      background-color: #333;
      overflow: hidden;
      padding: 10px 20px;
    }
    .navbar a {
      float: left;
      color: #f2f2f2;
      text-align: center;
      padding: 10px 15px;
      text-decoration: none;
      font-size: 17px;
    }
    .navbar a:hover {
      background-color: #575757;
      color: white;
    }
  </style>
  
</head>
<body>

<c:if test="${empty userobj }">
<c:redirect url="../login.jsp"/>
</c:if>



  <div class="navbar">
    <a href="#">Home</a>
    <a href="#">About</a>
    
    <c:if test="${not empty userobj}">
        <a href="#">  ${userobj.name}   </a>
        <a href="#" data-bs-toggle="modal" data-bs-target="#exampleModal">LogOut</a>
    </c:if>
    
     <c:if test="${empty userobj}">
		    <a href="../login.jsp">Login</a>
		    <a href="../register.jsp">Register</a>
    </c:if>
    
  </div>



<!-- Logout modal -->



<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="exampleModalLabel">Logout Confirmation</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <h4>Do You want to LogOut?</h4>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <a href="../LogoutServlet" class="btn btn-primary" role="button">Log Out</a>
        
      </div>
      <div class="modal-footer">

      </div>
    </div>
  </div>
</div>






    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.5/dist/js/bootstrap.bundle.min.js" integrity="sha384-k6d4wzSIapyDyv1kpU366/PK5hCdSbCRGRCMv+eplOQJWyd1fbcAu9OCUj5zNLiq" crossorigin="anonymous"></script>
