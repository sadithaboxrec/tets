package com.category.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.Entity.Category;
import com.DB.DBConnect;
import com.DAO.CategoryDAOImpl;

@WebServlet("/editCategory")
public class EditCategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String categoryName = request.getParameter("categoryName");
            String categoryStatus = request.getParameter("categoryStatus");
            String stcklocatioId = request.getParameter("stkloc_id");

            Category c = new Category();
            c.setCategoryId(id);
            c.setCategoryName(categoryName);
            c.setCategoryStatus(categoryStatus);
            c.setLocId(Integer.parseInt(stcklocatioId));

            CategoryDAOImpl dao = new CategoryDAOImpl(DBConnect.getConn());
            boolean f = dao.updateEditCategory(c);

            if (f) {
                request.setAttribute("SuccessMsg", "Category Added Successfully");
                request.getRequestDispatcher("Category/viewcategory.jsp").forward(request, response);
            } else {
                request.setAttribute("failedMsg", "Something went wrong on server");
                request.getRequestDispatcher("Category/addcategory.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
