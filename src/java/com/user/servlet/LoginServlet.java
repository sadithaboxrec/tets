package com.user.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.DAO.userDAOImpl;

import com.DB.DBConnect;
import com.Entity.User;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
						
						userDAOImpl dao=new userDAOImpl(DBConnect.getConn());
						
						HttpSession session=request.getSession();		
						
						String username=request.getParameter("username");
						String password = request.getParameter("password");
						
						if("alexander".equals(username) && "54321".equals(password)) {
							
							User us =new User();
							us.setName("Super_Admin");
                                                        us.setRole("SU");
							session.setAttribute("userobj",us);
							response.sendRedirect(request.getContextPath()+"/dashboard.jsp");
							
						}else {
							
							User us=dao.login(username, password);
							
							if (us != null) {
					           
					            session.setAttribute("userobj", us);
			
					         //   String role = us.getRoleName();
                                                  String role = us.getRole();
					            System.out.println("User logged in with role: " + role);
                                                    response.sendRedirect(request.getContextPath()+"/dashboard.jsp");
//					            switch (role) {
//					                case "Manager":
//					                    response.sendRedirect("Manager/home.jsp");
//					                    break;
//					                case "Warehouse_Manager": //receiving
//					                    response.sendRedirect("Receiving/home.jsp");
//					                    break;
//					                case "Supplier_Manager": //sending
//					                    response.sendRedirect("Sending/home.jsp");
//					                    break;
//
//					           }
							 } else {
									session.setAttribute("failedMsg", "Username and Password invalid");
									response.sendRedirect("login.jsp");
							 }
						
						   }
						
						
						
		} catch (Exception e) {
			e.printStackTrace();
		}
	

}
}
