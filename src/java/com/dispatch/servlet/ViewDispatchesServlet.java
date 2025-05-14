package com.dispatch.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.DAO.DispatchDAOImpl;
import com.DB.DBConnect;
import com.Entity.Dispatch;
import com.Entity.User;


@WebServlet("/view_dispatches")
public class ViewDispatchesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ViewDispatchesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DispatchDAOImpl dao = new DispatchDAOImpl(DBConnect.getConn());
        List<Dispatch> dispatchList = dao.getAllDispatches();
        request.setAttribute("dispatchList", dispatchList);
        
        //user Validation at servlet
        HttpSession session = request.getSession(false);
        User seession_user = (User) session.getAttribute("userobj");
        
            switch (seession_user.getRole()) {
                case "Manager":
                    request.getRequestDispatcher("Dispatch/dispatches.jsp").forward(request, response);
                    break;
                case "Warehouse_Manager": //receiving
                    request.getRequestDispatcher("Dispatch/dispatches_act.jsp").forward(request, response);
                    break;
                case "Supplier_Manager": //sending
                    request.getRequestDispatcher("/unauthorized.jsp").forward(request, response);
		break;
                
                default:
                    request.getRequestDispatcher("/unauthorized.jsp").forward(request, response);
                break;
		}
        //end of user validation at servlet
            
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	}

}
