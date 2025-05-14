package com.dispatch.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.DAO.DispatchDAOImpl;
import com.DB.DBConnect;

@WebServlet("/ShippedDispatch")
public class ShippedDispatchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShippedDispatchServlet() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int dispatchId = Integer.parseInt(request.getParameter("id"));

        DispatchDAOImpl dao = new DispatchDAOImpl(DBConnect.getConn());

        dao.markDispatchAsCompleted(dispatchId);

        response.sendRedirect("view_dispatches"); 

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
