
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<sql:setDataSource var="myData" driver="com.mysql.cj.jdbc.Driver"
                   url="jdbc:mysql://localhost:3306/monshopvynilvault" user="root"
                   password="" />

<sql:query var="orders" dataSource="${myData}">
    select o.id, o.order_id, p.name , p.image , o.quantity , p.price , o.`date`  from orders o , products p where user_id = ${sessionScope.userIdNo} and  o.product_id = p.id ;
</sql:query>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Security-Policy" content="default-src 'self';">
        <jsp:include page="../includes/header.jsp" />
        <title>Vynil Shop</title>
    </head>
    <body>
        <jsp:include page="../includes/user-nav.jsp" />
        <div class="container">

            <h3 class="mt-4 mb-4">My Orders</h3>

            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Product Name</th>
                        <th>Image</th>
                        <th>Quantity</th>
                        <th>Unit Price</th>
                        <th>Ordered Date</th>
                    </tr>
                </thead>
                <tbody id="myTable">
                    <c:forEach var="order" items="${orders.rows}">
                        <tr id='<c:out value="${order.id}" />'>
                            <td><c:out value="${order.order_id}" /></td>
                            <td><c:out value="${order.name}" /></td>
                            <td>
                                <img class='img-fluid' width='150px' src="data:image/jpeg;base64,${order.image}" alt="alt"/>
                            </td>
                            <td><c:out value="${order.quantity}" /></td>
                            <td><c:out value="${order.price}" /></td>
                            <td>${fn:substring(order.date, 0, 10)}</td>


                        </tr>
                    </c:forEach>

                </tbody>
            </table>

        </div>

    </div>

    <jsp:include page="../includes/footer.jsp" />
</body>
</html>
