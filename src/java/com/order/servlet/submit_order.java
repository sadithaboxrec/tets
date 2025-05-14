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
import com.Entity.Products;


@WebServlet("/submit_order")
public class submit_order extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
    public submit_order() {
        super();   
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int supplierId = Integer.parseInt(request.getParameter("supplierId"));

	    ProductDAOImpl productDAO = new ProductDAOImpl(DBConnect.getConn());
	    OrderDAOImpl orderDAO = new OrderDAOImpl(DBConnect.getConn());  
	    OrderItemDAOImpl itemDAO = new OrderItemDAOImpl(DBConnect.getConn());  

	    
	    List<Products> productList = productDAO.getProductsBySupplier(supplierId);

	    
	    int orderId = orderDAO.createOrder(supplierId);  

	   
	    for (Products p : productList) {
	        String paramName = "qty_" + p.getPro_id();
	        String qtyStr = request.getParameter(paramName);

	        if (qtyStr != null && !qtyStr.isEmpty()) {
	            int qty = Integer.parseInt(qtyStr);
	            if (qty > 0) {
	                
	                itemDAO.addOrderItem(orderId, p.getPro_id(), qty);
	            }
	        }
	    }

	    

	    response.sendRedirect(request.getContextPath() + "/ViewOrderServlet");


	}

}
