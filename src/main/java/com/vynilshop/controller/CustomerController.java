/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.vynilshop.controller;

import com.vynilshop.model.Order;
import com.vynilshop.service.CustomerService;
import com.vynilshop.util.Constants;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.regex.*;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

        // Create a SQL query using a prepared statement
        String sql = "SELECT * FROM users WHERE email = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            response.setHeader("Content-Security-Policy", "default-src 'self'");
            
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next() && isValidEmail(email)) {
                    int userId = resultSet.getInt("id");
                    session.setAttribute("userId", email);
                    session.setAttribute("userIdNo", userId);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    request.setAttribute("loginError", "Invalid Credentials");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer-login.jsp");
                    requestDispatcher.forward(request, response);
                }
            }
        } catch (SQLException e) {
            //Log the exception and display a generic error message to the user
            request.setAttribute("loginError", "An error occurred during login.");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer-login.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    public static boolean isValidEmail(String email) {
        // Define a regular expression pattern for a valid email address
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        // Check if the email matches the pattern
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        // Check if the email length does not exceed 320 characters
        return email.length() <= 320 && matcher.matches();
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
            request.setAttribute("passwordError", "Password and confirm password are not the same");
            errorCount += 1;
        }

        if (errorCount > 0) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer-register.jsp");
            requestDispatcher.forward(request, response);
        } else {
            try {
                // Create a SQL query using a prepared statement
                String sql = "INSERT INTO users (name, email, password) VALUES (?, ?, ?)";
                try (Connection connection = DriverManager.getConnection(Constants.URL, Constants.USERNAME, Constants.PASSWORD); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                    preparedStatement.setString(1, name);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, password);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        request.setAttribute("registerDone", "Registered successfully");
                    } else {
                        request.setAttribute("registerFail", "Something went wrong");
                    }

                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer-register.jsp");
                    requestDispatcher.forward(request, response);
                }
            } catch (SQLException e) {
                // Log the exception and display a generic error message to the user
                request.setAttribute("registerFail", "An error occurred during registration.");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("customer-register.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }

    protected void logoutCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        session = request.getSession();
        session.invalidate();
        response.sendRedirect("index.jsp");
    }

    protected void buyNow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String[] ids = request.getParameter("ids").split(",");
        String[] qtys = request.getParameter("qtys").split(",");
        ArrayList<Order> orders = new ArrayList();

        session = request.getSession();
        int uid = (int) session.getAttribute("userIdNo");
        System.out.println();

        for (int i = 0; i < ids.length; i++) {
            orders.add(new Order(Integer.parseInt(ids[i]), uid, Integer.parseInt(qtys[i])));
        }

        boolean result = customerService.makeOrder(orders, uid);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        if (result == true) {
            String res = String.format("{\"msg\":\"%s\",\"status\":\"%b\"}", "Order placed", true);
            JSONObject jsonRes = new JSONObject(res);
            out.print(jsonRes);
            out.flush();
        } else {
            String res = String.format("{\"msg\":\"%s\",\"status\":\"%b\"}", "Something went wrong", false);
            JSONObject jsonRes = new JSONObject(res);
            out.print(jsonRes);
            out.flush();
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
        try {
            response.setContentType("text/html;charset=UTF-8");
            String action = request.getParameter("action");
            System.out.println(action);
            switch (action) {
                case "Register":
                    this.registerCustomer(request, response);
                    break;
                case "Login":
                    this.loginCustomer(request, response);
                    break;
                case "Logout":
                    this.logoutCustomer(request, response);
                    break;
                case "Buy now":
                    this.buyNow(request, response);
                    break;
                default:
                    break;
            }
        } catch (IOException | ServletException e) {
            // Handle any exceptions and redirect to the error page
            request.setAttribute("exception", e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (IOException | ServletException e) {
            log("An error occurred while processing the GET request.", e); // Log the exception
            forwardToErrorPage(request, response, "An error occurred while processing the GET request."); // Forward to a custom error page
        }
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
            processRequest(request, response);
        } catch (IOException | ServletException e) {
            log("An error occurred while processing the POST request.", e); // Log the exception
            forwardToErrorPage(request, response, "An error occurred while processing the POST request."); // Forward to a custom error page
        }
    }

// Common method for forwarding to the error page
    private void forwardToErrorPage(HttpServletRequest request, HttpServletResponse response, String errorMessage) throws ServletException, IOException {
        request.setAttribute("errorMessage", errorMessage); // Pass the error message to the error page
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
        requestDispatcher.forward(request, response);
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
