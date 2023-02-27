/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.vynilshop.controller;

import com.vynilshop.model.Product;
import com.vynilshop.service.AdminService;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class AdminController extends HttpServlet {

    private AdminService adminService;
    private HttpSession session;
    private ServletFileUpload uploader = null;
    private static final String UPLOAD_DIR = "uploads";

    public AdminController() {
        adminService = new AdminService();
    }

    protected void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean result = adminService.deleteProduct(id);
        if (result == true) {
            request.setAttribute("productDelete", "Product Deleted successful");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/products.jsp");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("productDeleteError", "Something went wrong");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/products.jsp");
            requestDispatcher.forward(request, response);
        }

    }

    protected void addNewProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String artist = request.getParameter("artist");
        Double price = Double.valueOf(request.getParameter("price"));
        String genre = request.getParameter("genre");
        String description = request.getParameter("genre");
        int year = Integer.parseInt(request.getParameter("year"));
        Part image = request.getPart("image");

//        String applicationPath = request.getServletContext().getRealPath("");
//        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
//
//        // creates the save directory if it does not exists
//        File fileSaveDir = new File(uploadFilePath);
//        if (!fileSaveDir.exists()) {
//            fileSaveDir.mkdirs();
//        }
//        System.out.println("Upload File Directory=" + fileSaveDir.getAbsolutePath());
//
//        String fileName = null;
//        //Get all the parts from request and write it to the file on server
//        for (Part part : request.getParts()) {
//            fileName = getFileName(part);
//            part.write(uploadFilePath + File.separator + fileName);
//        }
//
//        request.setAttribute("message", fileName + " File uploaded successfully!");
//        getServletContext().getRequestDispatcher("/response.jsp").forward(
//                request, response);
        if (name == null || name.isEmpty() || artist == null || artist.isEmpty() || genre == null || genre.isEmpty() || description == null || description.isEmpty()) {
            request.setAttribute("emptyData", "Please fill all the required details");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/add-product.jsp");
            requestDispatcher.forward(request, response);
        } else {
            Product product = new Product(name, artist, price, genre, description, year);
            boolean result = adminService.addProduct(product);
            if (result == true) {
                request.setAttribute("productAdded", "Product Added successful");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/products.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("productAddedError", "Something went wrong");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("WEB-INF/add-product.jsp");
                requestDispatcher.forward(request, response);
            }
        }

    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= " + contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("image")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "";
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
        } else if (action.equals("Add Product")) {
            this.addNewProduct(request, response);
        } else if (action.equals("Delete Product")) {
            this.deleteProduct(request, response);
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
