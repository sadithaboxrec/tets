package com.product.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;

import com.DAO.CategoryDAOImpl;
import com.DAO.ProductDAOImpl;
import com.DAO.SupplierDAOImpl;
import com.DB.DBConnect;
import com.Entity.Category;
import com.Entity.Products;
import com.Entity.Supplier;


@WebServlet("/add_products")
@MultipartConfig
public class productAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public productAdd() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl(DBConnect.getConn());
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl(DBConnect.getConn());

        
        List<Category> categoryList = categoryDAO.getCategory();
        List<Supplier> supplierList = supplierDAO.getSupplier(); 

        
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("supplierList", supplierList);

        
        RequestDispatcher dispatcher = request.getRequestDispatcher("Product/addproducts.jsp");
        dispatcher.forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		try {
			
			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
			    String paramName = parameterNames.nextElement();
			    String paramValue = request.getParameter(paramName);
			    System.out.println(paramName + ": " + paramValue);
			}

			
			
			String pro_name = request.getParameter("pro_name");
			String catStr = request.getParameter("pro_category");
			String supStr = request.getParameter("pro_supplier");
			String stockStr = request.getParameter("pro_stock");

			System.out.println("pro_category = " + catStr);
			System.out.println("pro_supplier = " + supStr);
			System.out.println("pro_stock = " + stockStr);

			if (catStr == null || supStr == null || stockStr == null ||
			    catStr.isEmpty() || supStr.isEmpty() || stockStr.isEmpty()) {
			    request.setAttribute("failedMsg", "All fields are required!");
			    request.getRequestDispatcher("Product/addproducts.jsp").forward(request, response);
			    return;
			}

			int pro_category = Integer.parseInt(catStr);
			int pro_supplier = Integer.parseInt(supStr);
			int pro_stock = Integer.parseInt(stockStr);


		
			
			Part part=request.getPart("pro_photo");
			String fileName=part.getSubmittedFileName();
		
			
			Products p=new Products(pro_name,pro_category,pro_supplier,pro_stock,fileName,"manager");
			// System.out.println(b);
			
			
			ProductDAOImpl dao=new ProductDAOImpl(DBConnect.getConn());
			

			
			boolean f=dao.addProducts(p);
			
			HttpSession session=request.getSession();
			
			if(f) {
				
				// need to check error in this code, until below code work
//				  String path=getServletContext().getRealPath("")+"images/products";
//				  System.out.println(path);
//				  
//				  File file=new File(path);
//				  part.write(path + File.separator+ fileName);
				
				String path = getServletContext().getRealPath("") + "images/products";
				System.out.println("Upload Path: " + path);

				File uploadDir = new File(path);
				if (!uploadDir.exists()) {
				    uploadDir.mkdirs();  // Create the directory if it doesn't exist
				}

				String file = Paths.get(part.getSubmittedFileName()).getFileName().toString();
				part.write(path + File.separator + file);

				
				session.setAttribute("SuccessMsg", "Products Added Successfully");
				response.sendRedirect("Product/viewproducts.jsp");
			}else {
				session.setAttribute("failedMsg", "Something wrong on Server");
				response.sendRedirect("Product/addproducts.jsp");
			}
			
		}catch(	Exception e) {
			e.printStackTrace();
		}

	}

}
