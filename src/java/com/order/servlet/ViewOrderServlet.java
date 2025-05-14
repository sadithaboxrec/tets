package com.order.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.DAO.OrderDAOImpl;
import com.DB.DBConnect;
import com.Entity.Order;
import com.Entity.User;
import jakarta.servlet.http.HttpSession;

@WebServlet("/ViewOrderServlet")
public class ViewOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViewOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OrderDAOImpl orderDAO = new OrderDAOImpl(DBConnect.getConn());

        List<Order> orderList = orderDAO.getAllOrders();  
        request.setAttribute("orderList", orderList);

        //user Validation at servlet
        HttpSession session = request.getSession(false);
        User seession_user = (User) session.getAttribute("userobj");
        
            switch (seession_user.getRole()) {
                case "Manager":
                    request.getRequestDispatcher("Order/vieworders.jsp").forward(request, response);
                    break;
                case "Supplier_Manager": //receiving
                    request.getRequestDispatcher("Order/vieworders.jsp").forward(request, response);
                    break;
                case "Warehouse_Manager": //sending
                    request.getRequestDispatcher("Order/vieworders_act.jsp").forward(request, response);
		break;
                
                default:
                    request.getRequestDispatcher("/unauthorized.jsp").forward(request, response);
                break;
		}
        //end of user validation at servlet
        
        request.getRequestDispatcher("Order/vieworders.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
