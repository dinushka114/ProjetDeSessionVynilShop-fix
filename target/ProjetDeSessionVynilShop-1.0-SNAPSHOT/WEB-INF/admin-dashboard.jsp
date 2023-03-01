<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../includes/admin-header.jsp" />
        <title>Admin</title>


    </head>

    <sql:setDataSource var="myData" driver="com.mysql.cj.jdbc.Driver"
                       url="jdbc:mysql://localhost:3306/monshopvynilvault" user="root"
                       password="123" />


    <sql:query var="products" dataSource="${myData}">
        SELECT count(*) as products_count FROM products;
    </sql:query>

    <sql:query var="users" dataSource="${myData}">
        SELECT count(*) as users_count FROM users;
    </sql:query>

    <sql:query var="orders" dataSource="${myData}">
        SELECT count(*) as orders_count FROM orders;
    </sql:query>

    <c:if test="${sessionScope.isAdminLoggedIn == null}">
        <c:redirect url="admin-login.jsp" />
    </c:if>

    <body class="sb-nav-fixed">

        <jsp:include page="../includes/admin-nav.jsp" />

        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <jsp:include page="../includes/admin-sidebar.jsp" />
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Dashboard</h1>

                        <div class="row">

                            <c:forEach var="count" items="${products.rows}">
                                <div class='col-sm-4'>
                                    <div class="card text-dark bg-warning mb-3 p-0">
                                        <div class="card-header">
                                            Total no of products
                                        </div>
                                        <div class="card-body">
                                            <h2 class="card-title">
                                                <c:out value='${empty count.products_count ? "Not Yet" : count.products_count}' />
                                            </h2>

                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                            <c:forEach var="count" items="${users.rows}">
                                <div class='col-sm-4'>
                                    <div class="card text-dark bg-success mb-3 p-0">
                                        <div class="card-header">
                                            Total no of customers
                                        </div>
                                        <div class="card-body">
                                            <h2 class="card-title">
                                                <c:out value='${empty count.users_count ? "Not Yet" : count.users_count}' />
                                            </h2>

                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                            
                            
                            <c:forEach var="count" items="${orders.rows}">
                                <div class='col-sm-4'>
                                    <div class="card text-dark bg-secondary mb-3 p-0">
                                        <div class="card-header">
                                            Total no of orders
                                        </div>
                                        <div class="card-body">
                                            <h2 class="card-title">
                                                <c:out value='${empty count.orders_count ? "Not Yet" : count.orders_count}' />
                                            </h2>

                                        </div>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>


                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">

                </footer>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/static/js/admin.js"></script>
    </body>

</html>
