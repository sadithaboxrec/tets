
package com.supplier.servlet;


import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.Entity.Supplier;
import com.DB.DBConnect;
import com.DAO.SupplierDAOImpl;


@WebServlet("/editSupplier")
public class EditSupplierServlet extends HttpServlet {

 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String supp_name = request.getParameter("supp_name");
            String supp_email = request.getParameter("supp_email");
            String supp_cno = request.getParameter("supp_cno");

            Supplier s = new Supplier();
            s.setSupp_id(id);
            s.setSupp_name(supp_name);
            s.setSupp_email(supp_email);
            s.setSupp_cno(supp_cno);

            SupplierDAOImpl dao = new SupplierDAOImpl(DBConnect.getConn());
            boolean f = dao.updateEditSupplier(s);

            HttpSession session = request.getSession();

            if (f) {
                session.setAttribute("SuccessMsg", "Supplier Updated Successfully.");
                response.sendRedirect("Suppliers/viewsupplier.jsp"); 
            } else {
                session.setAttribute("failedMsg", "Supplier Update Failed.");
                response.sendRedirect("Suppliers/viewsupplier.jsp");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}