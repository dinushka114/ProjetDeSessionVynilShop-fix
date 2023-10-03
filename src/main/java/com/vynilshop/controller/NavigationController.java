package com.vynilshop.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.encoder.Encode;

public class NavigationController extends HttpServlet {
    
    private String sanitizeInput(String input) {
        // Use OWASP Java Encoder to HTML-encode the input
        if (input != null) {
            return Encode.forHtml(input);
        }
        return null;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = sanitizeInput(request.getParameter("to"));

        try {
            if (action != null) {
                switch (action) {
                    case "adminProducts":
                        forwardToPage("WEB-INF/products.jsp", request, response);
                        break;
                    case "adminAddNewProduct":
                    case "adminUpdateProduct":
                        forwardToPage("WEB-INF/add-product.jsp", request, response);
                        break;
                    case "adminUsers":
                        forwardToPage("WEB-INF/customers.jsp", request, response);
                        break;
                    case "adminDashboard":
                        forwardToPage("WEB-INF/admin-dashboard.jsp", request, response);
                        break;
                    case "adminOrders":
                        forwardToPage("WEB-INF/orders.jsp", request, response);
                        break;
                    case "myOrders":
                        forwardToPage("WEB-INF/my-orders.jsp", request, response);
                        break;
                    default:
                        // Handle unexpected action value
                        throw new ServletException("Invalid action: " + action);
                }
            }
        } catch (Exception e) {
            // Log the error for debugging purposes
            e.printStackTrace();

            // Set the error message as an attribute
            request.setAttribute("errorMessage", "An error occurred: " + e.getMessage());

            // Forward to the custom error page
            forwardToPage("/error.jsp", request, response);
        }
    }
    
    private void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
