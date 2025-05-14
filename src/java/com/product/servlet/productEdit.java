/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.product.servlet;

import com.DAO.CategoryDAOImpl;
import com.DAO.ProductDAOImpl;
import com.DAO.SupplierDAOImpl;
import com.DB.DBConnect;
import com.Entity.Category;
import com.Entity.Products;
import com.Entity.Supplier;
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
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author dines
 */

@WebServlet(name = "editproducts", urlPatterns = {"/edit_products"})
@MultipartConfig(
    fileSizeThreshold   = 1024 * 1024 * 50,  // 1MB
    maxFileSize         = 1024 * 1024 * 50,  // 5MB
    maxRequestSize      = 1024 * 1024 * 50 // 10M
)
public class productEdit extends HttpServlet {
    Logger logger = Logger.getLogger("edit_products");
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl(DBConnect.getConn());
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl(DBConnect.getConn());
        ProductDAOImpl ProductDAO = new ProductDAOImpl(DBConnect.getConn());

        
        List<Category> categoryList = categoryDAO.getCategory();
        List<Supplier> supplierList = supplierDAO.getSupplier(); 

        String id = request.getParameter("id");
        
        Products product =  ProductDAO.getProductById(Integer.parseInt(id));
        
        request.setAttribute("product", product);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("supplierList", supplierList);

        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Product/editproducts.jsp");
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAOImpl categoryDAO = new CategoryDAOImpl(DBConnect.getConn());
        SupplierDAOImpl supplierDAO = new SupplierDAOImpl(DBConnect.getConn());
        ProductDAOImpl ProductDAO = new ProductDAOImpl(DBConnect.getConn());

        
        List<Category> categoryList = categoryDAO.getCategory();
        List<Supplier> supplierList = supplierDAO.getSupplier(); 

        String id = request.getParameter("id");
        
        Products product =  ProductDAO.getProductById(Integer.parseInt(id));
        
        request.setAttribute("product", product);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("supplierList", supplierList);

        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/Product/editproducts.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
		
		try {
			
			Enumeration<String> parameterNames = request.getParameterNames();
			while (parameterNames.hasMoreElements()) {
			    String paramName = parameterNames.nextElement();
			    String paramValue = request.getParameter(paramName);
			    System.out.println(paramName + ": " + paramValue);
			}

			
			
			String pro_name = request.getParameter("pro_name");
                        String proidStr = request.getParameter("pro_id");
			String catStr = request.getParameter("pro_category");
			String supStr = request.getParameter("pro_supplier");
			String stockStr = request.getParameter("pro_stock");

			System.out.println("pro_category = " + catStr);
			System.out.println("pro_supplier = " + supStr);
			System.out.println("pro_stock = " + stockStr);

			if (catStr == null || supStr == null || stockStr == null || proidStr == null || 
			    catStr.isEmpty() || supStr.isEmpty() || stockStr.isEmpty() || proidStr.isEmpty()) {
			    request.setAttribute("failedMsg", "All fields are required!");
			    request.getRequestDispatcher("Product/addproducts.jsp").forward(request, response);
			    return;
			}

                        int pro_id = Integer.parseInt(proidStr);
			int pro_category = Integer.parseInt(catStr);
			int pro_supplier = Integer.parseInt(supStr);
			int pro_stock = Integer.parseInt(stockStr);


		
			
			Part tmppart=request.getPart("pro_photo");
			String fileName=tmppart.getSubmittedFileName();
		
			
			Products p=new Products(pro_name,pro_category,pro_supplier,pro_stock,fileName,"manager");
                        p.setPro_id(pro_id);
			// System.out.println(b);
			
			
			ProductDAOImpl dao=new ProductDAOImpl(DBConnect.getConn());
			

			
			boolean f=dao.editProducts(p);
			
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

//				File uploadDir = new File(path);
//				if (!uploadDir.exists()) {
//				    uploadDir.mkdirs();  // Create the directory if it doesn't exist
//				}

//				String file = Paths.get(part.getSubmittedFileName()).getFileName().toString();
//				part.write(path + File.separator + file);
                                
                                
                                         String uploadPath = getServletContext().getRealPath("") + "images/products";
         
                                        File uploadDir = new File(uploadPath);
                                        if (!uploadDir.exists()) {
                                            uploadDir.mkdirs();
                                        }

                                        try {

                                            for (Part part : request.getParts()) {
                                                if ("pro_photo".equals(part.getName())) {
                                                     logger.severe("upload: ");
//                                                                String mimeType = part.getContentType();

                                                String filePath = uploadPath +File.separator+ fileName;
                                                try (InputStream fileContent = part.getInputStream()) {
                                                    Files.copy(fileContent, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
                                                    request.setAttribute("done", "File uploaded successfully!");
                                                    logger.severe("File uploaded successfully!");
                                                } catch (Exception ex) {
                                                    request.setAttribute("error", "File upload failed: " + ex.getMessage());
                                                    logger.severe("File upload failed:"+ ex);
                                                }
                                                }
                                            }


                                        } catch (Exception e) {
                                            request.setAttribute("error", "File upload failed: " + e.getMessage());
                                            logger.severe("File upload failed: " + e.getMessage());
                                        }
                                

				
				session.setAttribute("SuccessMsg", "Products Edited Successfully");
				response.sendRedirect("Product/viewproducts.jsp");
			}else {
				session.setAttribute("failedMsg", "Something wrong on Server");
				response.sendRedirect("Product/addproducts.jsp");
			}
			
		}catch(	Exception e) {
			e.printStackTrace();
		}

	}
        
    

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

