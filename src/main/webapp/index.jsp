
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<sql:setDataSource var="myData" driver="com.mysql.cj.jdbc.Driver"
                   url="jdbc:mysql://localhost:3306/monshopvynilvault" user="root"
                   password="123" />

<sql:query var="products" dataSource="${myData}">
    SELECT * FROM products;
</sql:query>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp"%>
        <title>Vynil Shop</title>
    </head>
    <body>
        <%@include file="includes/user-nav.jsp"%>
        <div class="container">




            <div class="card-header my-3" id="card-header">Tous les Produits</div>
            <div class="row" id="max-wd">

                <c:forEach var="product" items="${products.rows}">
                    <!-- card -->
                    <div class="col-md-3 my-3">
                        <div class="card w-100">
                            <img class="card-img-top" src="data:image/jpeg;base64,${product.image}"
                                 alt="Card image cap">
                            <div class="card-body">


                                <h5 class="card-title">${product.name}</h5>
                                <h6 class="price">${product.price}</h6>
                                <h6 class="category">${product.artist}</h6>
                                <div class="mt-3 d-flex justify-content-between">
                                    <a class="btn btn-outline-dark" href="add-to-cart?id=1">Ajouter</a>
                                    <a class="btn btn-primary" href="order-now?quantity=1&id=1" id="btn-hover">Acheter</a>
                                </div>
                                <br>
                                <a class="btn btn-link" href="detailProduit.jsp?id=1">Details</a>
                            </div>
                        </div>
                    </div>
                    <!-- card end -->



                </c:forEach>


            </div>
        </div>

    </div>



    <%@include file="includes/footer.jsp"%>
</body>
</html>
