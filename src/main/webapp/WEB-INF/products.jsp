<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
    <head>
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin.css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
        <title>Admin</title>


    </head>

    <sql:setDataSource var="myData" driver="com.mysql.cj.jdbc.Driver"
                       url="jdbc:mysql://localhost:3306/monshopvynilvault" user="root"
                       password="123" />

    <sql:query var="products" dataSource="${myData}">
        SELECT * FROM products;
    </sql:query>

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
                        <h1 class="mt-4">Products</h1>
                        <ol class="breadcrumb mb-4">
                            <a href="NavigationController?to=adminAddNewProduct" class="btn btn-outline-success">Add new</a>
                        </ol>

                        <c:if test="${productAdded != null}">
                            <div class="alert alert-success mt-2 alert-dismissible" role="alert">
                                <c:out value='${productAdded}' />
                            </div>
                        </c:if>

                        <c:if test="${productUpdated != null}">
                            <div class="alert alert-success mt-2 alert-dismissible" role="alert">
                                <c:out value='${productUpdated}' />
                            </div>
                        </c:if>

                        <c:if test="${productDelete != null}">
                            <div class="alert alert-success mt-2 alert-dismissible" role="alert">
                                <c:out value='${productDelete}' />
                            </div>
                        </c:if>

                        <c:if test="${productDeleteError != null}">
                            <div class="alert alert-danger mt-2 alert-dismissible" role="alert">
                                <c:out value='${productDeleteError}' />
                            </div>
                        </c:if>

                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Artist</th>
                                    <th>Price</th>
                                    <th>Image</th>
                                    <th>Genre</th>
                                    <th>Description</th>
                                    <th>Year</th>
                                    <th>Update</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody id="myTable">

                                <c:forEach var="product" items="${products.rows}">
                                    <tr id='<c:out value="${product.id}" />'>
                                        <td><c:out value="${product.id}" /></td>
                                        <td><c:out value="${product.name}" /></td>
                                        <td><c:out value="${product.artist}" /></td>
                                        <td><c:out value="${product.price}" /></td>
                                        <td>
                                            <img class='img-fluid' width='150px' src="data:image/jpeg;base64,${product.image}" alt="alt"/>
                                        </td>
                                        <td><c:out value="${product.genre}" /></td>
                                        <td><c:out value="${product.description}" /></td>
                                        <td><c:out value="${product.year}" /></td>
                                        <td> <a class="btn btn-warning" href="NavigationController?to=adminUpdateProduct&id=<c:out value='${product.id}'/>" >Update</a> </td>
                                        <td> 

                                            <form action="AdminController" method="POST">
                                                <input type="hidden" name="id" value='<c:out value='${product.id}'  />' />
                                                <input type="submit" class="btn btn-danger" name="action" value="Delete Product" />
                                            </form>

                                        </td>
                                    </tr>
                                </c:forEach>

                            </tbody>
                        </table>

                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">

                </footer>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/static/js/script.js"></script>
        <script>
            $(document).ready(function () {
                $("#myInput").on("keyup", function () {
                    var value = $(this).val().toLowerCase();
                    $("#myTable tr").filter(function () {
                        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                    });
                });
            });
        </script>
    </body>

</html>
