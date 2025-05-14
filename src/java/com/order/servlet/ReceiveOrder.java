package com.order.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.DAO.OrderDAOImpl;
import com.DAO.OrderItemDAOImpl;
import com.DAO.ProductDAOImpl;
import com.DB.DBConnect;
import com.Entity.OrderItem;


@WebServlet("/receive_order")
public class ReceiveOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public ReceiveOrder() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int orderId = Integer.parseInt(request.getParameter("order_id"));

        OrderItemDAOImpl itemDAO = new OrderItemDAOImpl(DBConnect.getConn());
        ProductDAOImpl productDAO = new ProductDAOImpl(DBConnect.getConn());
        OrderDAOImpl orderDAO = new OrderDAOImpl(DBConnect.getConn());

        
        List<OrderItem> items = itemDAO.getItemsByOrderId(orderId);

        for (OrderItem item : items) {
            int productId = item.getProductId();
            int qty = item.getQuantity();

            
            productDAO.increaseStock(productId, qty);
        }

        
        orderDAO.markAsReceived(orderId);

        
        response.sendRedirect(request.getContextPath() + "/ViewOrderServlet");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}
