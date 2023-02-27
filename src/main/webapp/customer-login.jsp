
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp"%>
        <title>Customer Login</title>
    </head>
    <body>

        <div class="container">

            <div class="ol-xl-5 col-lg-6 col-md-8 col-sm-10 mx-auto mt-lg-5">
                <h1>Customer login</h1>

                <c:if test="${emptyData != null}">
                    <div class="alert alert-danger mt-2 alert-dismissible" role="alert">
                        <c:out value='${emptyData}' />
               
                    </div>
                </c:if>

                <c:if test="${loginError != null}">
                    <div class="alert alert-danger mt-2 alert-dismissible" role="alert">
                        <c:out value='${loginError}' />
                     
                    </div>
                </c:if>

                <form action="CustomerController" method="POST">

                    <div class="mb-3">
                        <label for="name" class="form-label">Email</label> <input
                            type="text" class="form-control" id="nameInput" name='email'
                            required />
                    </div>

                    <div class="mb-3">
                        <label for="password" class="form-label">Password</label> <input
                            type='password' class="form-control" id="passwordInput"
                            name='password' required />
                    </div>

                    <div class='mb-3'>
                        <input type='submit' class='btn btn-primary w-100 mb-3' name='action'
                               value='Login'>
                        
                        <a href="customer-register.jsp">don't have an account?</a>
                        
                    </div>
                </form>

            </div>
        </div>

    </div>



    <%@include file="includes/footer.jsp"%>
</body>
</html>
