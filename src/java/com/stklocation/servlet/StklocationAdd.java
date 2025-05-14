/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.stklocation.servlet;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.Entity.stcklocation;
import com.DB.DBConnect;
import com.DAO.StklocationDAO;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 *
 * @author dines
 */
@WebServlet(name = "StklocationAdd", urlPatterns = {"/add_stklocation"})
public class StklocationAdd extends HttpServlet {
Logger logger = Logger.getLogger("add_stklocation");
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet StklocationAdd</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet StklocationAdd at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
        processRequest(request, response);
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
            String loc_name = request.getParameter("stcklocationName");
            String Loc_status = request.getParameter("stcklocationstatus");
            
                
            stcklocation loc = new stcklocation(loc_name, Loc_status);
            System.out.println(loc);
            
            logger.severe(loc.getLocName());

            StklocationDAO dao = new StklocationDAO(DBConnect.getConn());
            boolean f = dao.addLocation(loc);
            
            

            if (f) {
                request.setAttribute("SuccessMsg", "Category Added Successfully");
                request.getRequestDispatcher("stocklocation/viewlocation.jsp").forward(request, response);
            } else {
                request.setAttribute("failedMsg", "Something went wrong on server");
                request.getRequestDispatcher("stocklocation/addlocation.jsp").forward(request, response);
            }

        } catch (Exception e) {
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
