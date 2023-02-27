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
                        <h1 class="mt-4">Products</h1>
                        <ol class="breadcrumb mb-4">
                            <a href="NavigationController?to=adminAddNewProduct" class="btn btn-outline-success">Add new</a>
                        </ol>

                        <form action="AdminController" method="POST" enctype="multipart/form-data">
                            <h3>Add product</h3>

                            <c:if test="${emptyData != null}">
                                <div class="alert alert-danger mt-2 alert-dismissible" role="alert">
                                    <c:out value='${emptyData}' />
                                </div>
                            </c:if>


                            <c:if test="${productAddedError != null}">
                                <div class="alert alert-danger mt-2 alert-dismissible" role="alert">
                                    <c:out value='${productAddedError}' />
                                </div>
                            </c:if>
                            

                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Name</label>
                                <input type="text" class="form-control" id="name" aria-describedby="emailHelp" name="name">
                            </div>

                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Artist</label>
                                <input type="text" class="form-control" id="name" aria-describedby="emailHelp" name="artist">
                            </div>

                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Price</label>
                                <input type="text" class="form-control" id="name" aria-describedby="emailHelp" name="price">
                            </div>

                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Image</label>
                                <input type="file" class="form-control" id="name" aria-describedby="emailHelp" name="image">
                            </div>

                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Genre</label>
                                <input type="text" class="form-control" id="name" aria-describedby="emailHelp" name="genre">
                            </div>

                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Description</label>
                                <!--                                <input type="text" class="form-control" id="name" aria-describedby="emailHelp" name="description">-->
                                <textarea name="description" class="form-control"></textarea>
                            </div>

                            <div class="mb-3">
                                <label for="exampleInputEmail1" class="form-label">Year</label>
                                <input type="text" class="form-control" id="name" aria-describedby="emailHelp" name="year">
                            </div>

                            <input type="submit" class="btn btn-primary" name="action" value="Add Product" />
                        </form>
                    </div>
                </main>
                <footer class="py-4 bg-light mt-auto">

                </footer>
            </div>
        </div>

        <script src="${pageContext.request.contextPath}/static/js/script.js"></script>
    </body>

</html>
