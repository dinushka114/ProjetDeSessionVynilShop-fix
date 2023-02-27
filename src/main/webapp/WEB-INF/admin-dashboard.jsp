<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin.css"/>
        <title>Admin</title>


    </head>

    <c:if test="${sessionScope.isAdminLoggedIn == null}">
        <c:redirect url="admin-login.jsp" />
    </c:if>

    <body class="sb-nav-fixed">

        <%@include file="../includes/admin-nav.jsp"%>

        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <%@include file="../includes/admin-sidebar.jsp"%>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Dashboard</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Dashboard</li>
                        </ol>


                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">
                  
                </footer>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/static/js/script.js"></script>
    </body>

</html>
