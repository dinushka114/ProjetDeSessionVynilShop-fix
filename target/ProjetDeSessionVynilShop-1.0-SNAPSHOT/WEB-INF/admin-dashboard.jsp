<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="../includes/header.jsp"%>
        <title>Admin</title>
    </head>

    <c:if test="${sessionScope.isAdminLoggedIn == null}">
        <c:redirect url="admin-login.jsp" />
    </c:if>

    <body>
        <h1>Admin Dashboard</h1>
    <%@include file="../includes/footer.jsp"%>
    </body>
    
</html>
