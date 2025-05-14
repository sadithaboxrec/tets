package com.order.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.DAO.ProductDAOImpl;
import com.DAO.SupplierDAOImpl;
import com.DB.DBConnect;
import com.Entity.Products;
import com.Entity.Supplier;


@WebServlet("/add_orders")
public class orderAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
    public orderAdd() {
        super();
 
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl(DBConnect.getConn());
        ProductDAOImpl productDAO = new ProductDAOImpl(DBConnect.getConn());

        
        String supplierIdParam = request.getParameter("pro_supplier");

        if (supplierIdParam != null) {
            int supplierId = Integer.parseInt(supplierIdParam);

          
            List<Products> productList = productDAO.getProductsBySupplier(supplierId);

            request.setAttribute("productList", productList);
            request.setAttribute("supplierId", supplierId);

            RequestDispatcher dispatcher = request.getRequestDispatcher("Order/createorder.jsp");
            dispatcher.forward(request, response);
        } else {
           
            List<Supplier> supplierList = supplierDAO.getSupplier();
            request.setAttribute("supplierList", supplierList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Order/addorder.jsp");
            dispatcher.forward(request, response);
        }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
