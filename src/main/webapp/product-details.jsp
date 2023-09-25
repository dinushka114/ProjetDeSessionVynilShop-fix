<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="org.owasp.encoder.Encode" %>
<%! 
  // Define a function to sanitize and escape HTML content
  public String sanitizeAndEscape(String input) {
      return Encode.forHtml(input);
  }
%>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="ApplicationResource" />

<sql:setDataSource var="myData" driver="com.mysql.cj.jdbc.Driver"
                   url="jdbc:mysql://localhost:3306/monshopvynilvault" user="root"
                   password="" />

<%
    try {
        // SQL query and parameterization
        String sqlQuery = "SELECT * FROM products WHERE id=?";
        String productId = request.getParameter("id");

        // Execute the SQL query
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/monshopvynilvault", "root", "");
            preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, productId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieve and display the product details
                String artist = sanitizeAndEscape(resultSet.getString("artist"));
                String genre = sanitizeAndEscape(resultSet.getString("genre"));
                String year = sanitizeAndEscape(resultSet.getString("year"));
                String name = sanitizeAndEscape(resultSet.getString("name"));
                String price = sanitizeAndEscape(resultSet.getString("price"));
                String description = sanitizeAndEscape(resultSet.getString("description"));
                String image = sanitizeAndEscape(resultSet.getString("image"));

%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="includes/header.jsp" />
        <title>Vynil Shop</title>
    </head>
    <body>
        <jsp:include page="includes/user-nav.jsp" />
        <div class="container">
            <section class="py-5">
                <div class="container px-4 px-lg-5 my-5">
                    <div class="row gx-4 gx-lg-5 align-items-center">
                        <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="./product-image/<%= image%>" alt="..." /></div>
                        <div class="col-md-6">
                            <div class="small mb-1"><%= artist%> - <%= genre%> in <%= year%></div>
                            <h1 class="display-5 fw-bolder"><%= name%></h1>
                            <div class="fs-5 mb-5">
                                <span><%= price%></span>
                            </div>
                            <p class="lead"><%= description%></p>
                            <div class="d-flex">
                                <button class="btn btn-outline-dark flex-shrink-0" onclick="addToCart('<%= productId%>', '<%= price%>', '<%= name%>', '<%= image%>')" type="button">
                                    <i class="bi-cart-fill me-1"></i>
                                    <fmt:message key="detailProduct.add" /> 
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </div>
        <jsp:include page="includes/footer.jsp" />
    </body>
</html>
<%
            } else {
                // Product not found, display an error message or redirect to an error page
                response.sendRedirect("error.jsp?errorMessage=Product not found");
            }
        } catch (SQLException e) {
            // Handle database error
            response.sendRedirect("error.jsp?errorMessage=Database error: " + Encode.forHtml(e.getMessage()));
        } finally {
            // Close database resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    } catch (Exception e) {
        // Handle any other unexpected exceptions
        response.sendRedirect("error.jsp?errorMessage=An error occurred: " + Encode.forHtml(e.getMessage()));
    }
%>
