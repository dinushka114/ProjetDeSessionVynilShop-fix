/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.vynilshop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dinushkapiyumal
 */
public class NavigationController extends HttpServlet {

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
        String action = request.getParameter("to");
        
        response.setHeader("Content-Security-Policy", "default-src 'self'");
        
        try {
            if (action.equals("adminProducts")) {
                // Forward to the products page
            } else if (action.equals("adminAddNewProduct")) {
                // Forward to the add-product page
            } else if (action.equals("adminUpdateProduct")) {
                // Forward to the update-product page
            } else if (action.equals("adminUsers")) {
                // Forward to the customers page
            } else if (action.equals("adminDashboard")) {
                // Forward to the admin-dashboard page
            } else if (action.equals("adminOrders")) {
                // Forward to the orders page
            } else if (action.equals("myOrders")) {
                // Forward to the my-orders page
            } else {
                // Handle unexpected action value
                throw new ServletException("Invalid action: " + action);
            }
        } catch (Exception e) {
            // Set the error message as an attribute
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());
            
            // Forward to the custom error page
            request.getRequestDispatcher("/error.jsp").forward(request, response);
            return; // Ensure that no further processing occurs after forwarding
        }
        
        // Continue with normal processing
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
        processRequest(request, response);
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
