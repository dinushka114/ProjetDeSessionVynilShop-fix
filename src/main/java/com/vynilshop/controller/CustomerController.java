/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.vynilshop.controller;

import com.vynilshop.model.User;
import com.vynilshop.service.CustomerService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.regex.*;
import javax.servlet.http.HttpSession;

public class CustomerController extends HttpServlet {

    private CustomerService customerService;
    private HttpSession session;

    public CustomerController() {
        customerService = new CustomerService();
    }

    protected void loginCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        session = request.getSession();

        boolean result = customerService.loginCustomer(email, password);
        if (result == true) {
            session.setAttribute("userId", email);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("loginError", "Invalid Credentials");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer-login.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    protected void registerCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cpassword = request.getParameter("cpassword");

        int errorCount = 0;

        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);

        if (name == null || name.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty() || cpassword == null || cpassword.isEmpty()) {
            request.setAttribute("emptyData", "Please fill all the required details");
            errorCount += 1;

        }

        if (matcher.matches() == false) {
            request.setAttribute("emailError", "Your email is invalid");
            errorCount += 1;
        }

        if (!password.equals(cpassword)) {
            request.setAttribute("passwordError", "Password and confirm password are not same");
            errorCount += 1;
        }

        if (errorCount > 0) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer-register.jsp");
            requestDispatcher.forward(request, response);
        } else {
            User user = new User(name, email, password);
            boolean res = customerService.registerCustomer(user);
            if (res) {
                request.setAttribute("registerDone", "Registered successful");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer-register.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("registerFail", "Something went wrong");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer-register.jsp");
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
        if (action.equals("Register")) {
            this.registerCustomer(request, response);
        } else if (action.equals("Login")) {
            this.loginCustomer(request, response);
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
