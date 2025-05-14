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


@WebServlet("/editUsers")
public class editUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		
		try {
			
			
			int userId = Integer.parseInt(request.getParameter("user_id"));

			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String telephone = request.getParameter("telephone");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String status = request.getParameter("status");
			String roleName = request.getParameter("role");


			
			int roleId = 0;
			switch (roleName) {
				case "Manager":
					roleId = 1;
					break;
				case "Warehouse_Manager":
					roleId = 2;
					break;
				case "Supplier_Manager":
					roleId = 3;
					break;
			}

			// Create User object and set data
			User us = new User();
			us.setUserId(userId);
			us.setName(name);
			us.setEmail(email);
			us.setTelephone(telephone);
			us.setUsername(username);
			us.setPassword(password);
			us.setStatus(status);
                        us.setRole(roleName);

			
			userDAOImpl dao = new userDAOImpl(DBConnect.getConn());
			boolean success = dao.updateUsers(us);

			HttpSession session = request.getSession();

			if (success) {
				session.setAttribute("SuccessMsg", "User updated successfully!");
				response.sendRedirect("Super_Admin/alladmins.jsp");
			} else {
				session.setAttribute("failedMsg", "Something went wrong while updating user.");
				response.sendRedirect("Super_Admin/editusers.jsp?id=" + userId);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
