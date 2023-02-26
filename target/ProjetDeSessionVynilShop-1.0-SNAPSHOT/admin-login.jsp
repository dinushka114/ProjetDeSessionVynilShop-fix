
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp"%>
        <title>Admin Login</title>
    </head>
    <body>

        <div class="container">
            
            <h1>Admin login</h1>
            
            <form action="AdminController" method="POST">
                
                username
                <input type="text" name="username" />
                
                password
                <input type="text" name="password" />
                
                <input type="submit" name="action" value="Login" />
            </form>
           
        </div>

    </div>



    <%@include file="includes/footer.jsp"%>
</body>
</html>
