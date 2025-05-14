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
import com.DAO.StklocationDAO;
import com.Entity.stcklocation;
import jakarta.servlet.RequestDispatcher;
import java.util.List;

@WebServlet("/add_category")
public class CategoryAdd extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
        StklocationDAO stckLocDAO = new StklocationDAO(DBConnect.getConn());  
        List<stcklocation> stckLocList = stckLocDAO.getlockations();
        if(stckLocList != null){
            request.setAttribute("stocklocationlist", stckLocList);
        }else{
            request.setAttribute("stocklocationlist", "null");
        }
        
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Category/addcategory.jsp");
        dispatcher.forward(request, response);

	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String categoryName = request.getParameter("categoryName");
            String categoryStatus = request.getParameter("categoryStatus");
            String stcklocatioId = request.getParameter("stkloc_id");


            Category c = new Category(categoryName, categoryStatus, Integer.parseInt(stcklocatioId));
            System.out.println(c);

            CategoryDAOImpl dao = new CategoryDAOImpl(DBConnect.getConn());
            boolean f = dao.addCategory(c);

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
