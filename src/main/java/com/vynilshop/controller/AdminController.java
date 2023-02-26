/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.vynilshop.controller;

import com.vynilshop.service.AdminService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminController extends HttpServlet {

    private AdminService adminService;
    private HttpSession session;

    public AdminController() {
        adminService = new AdminService();
    }

    protected void adminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //get request parameters with stripping white spaces
        String username = request.getParameter("username").strip();
        String password = request.getParameter("password").strip();
        
        session = request.getSession();

        //validations
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("emptyData", "Please fill all the required details");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin-login.jsp");
            requestDispatcher.forward(request, response);
        } else {
            boolean result = adminService.login(username, password);
            System.out.println(result);
            if (result == true) {
                session.setAttribute("isAdminLoggedIn", true);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/admin-dashboard.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("loginError", "Invalid Credentials");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin-login.jsp");
                requestDispatcher.forward(request, response);
            }

        }

    }

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
        String action = request.getParameter("action");
        if (action.equals("Login")) {
            this.adminLogin(request, response);
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
