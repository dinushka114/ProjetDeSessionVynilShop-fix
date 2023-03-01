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

    <sql:query var="users" dataSource="${myData}">
        SELECT * FROM users;
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
                        <h1 class="mt-4">Customers</h1>
                      
                        
                        <c:if test="${customerDelete != null}">
                            <div class="alert alert-success mt-2 alert-dismissible" role="alert">
                                <c:out value='${customerDelete}' />
                            </div>
                        </c:if>
                        
                        <c:if test="${customerDeleteError != null}">
                            <div class="alert alert-danger mt-2 alert-dismissible" role="alert">
                                <c:out value='${customerDeleteError}' />
                            </div>
                        </c:if>

                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Name</th>
                                    <th>Email</th>
                                    <th>Password</th>
                                    <th>Delete</th>
                                </tr>
                            </thead>
                            <tbody id="myTable">

                                <c:forEach var="user" items="${users.rows}">
                                    <tr id='<c:out value="${user.id}" />'>
                                        <td><c:out value="${user.id}" /></td>
                                        <td><c:out value="${user.name}" /></td>
                                        <td><c:out value="${user.email}" /></td>
                                        <td><c:out value="${user.password}" /></td>
                                
                                      
                                        <td> 

                                            <form action="AdminController" method="POST">
                                                <input type="hidden" name="id" value='<c:out value='${user.id}'  />' />
                                                <input type="submit" class="btn btn-danger" name="action" value="Delete Customer" />
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
