
package com.supplier.servlet;

import com.DAO.SupplierDAOImpl;
import com.DB.DBConnect;
import com.Entity.Supplier;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/add_supplier")
public class SupplierAdd extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String supp_name = request.getParameter("supp_name");
            String supp_email = request.getParameter("supp_email");
            String supp_cno = request.getParameter("supp_cno");
            
            Supplier s= new Supplier(supp_name,supp_email,supp_cno);
            System.out.println(s);
            
            SupplierDAOImpl dao = new SupplierDAOImpl(DBConnect.getConn());
            boolean f = dao.addSupplier(s);
            
            HttpSession session = request.getSession();

            if (f) {
                session.setAttribute("SuccessMsg", "Supplier Added Successfully");
                response.sendRedirect("Suppliers/viewsupplier.jsp");
            } else {
                session.setAttribute("failedMsg", "Something went wrong on server");
                response.sendRedirect("Suppliers/viewsupplier.jsp");
            }
    
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

