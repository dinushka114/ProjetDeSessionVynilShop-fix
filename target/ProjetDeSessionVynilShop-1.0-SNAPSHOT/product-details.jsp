
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<sql:setDataSource var="myData" driver="com.mysql.cj.jdbc.Driver"
                   url="jdbc:mysql://localhost:3306/monshopvynilvault" user="root"
                   password="123" />

<sql:query var="product" dataSource="${myData}">
    SELECT * FROM products where id=${param.id};
</sql:query>
<!DOCTYPE html>
<html>
    <head>
     
        <jsp:include page="includes/header.jsp" />
        <title>Vynil Shop</title>
    </head>
    <body>

        <jsp:include page="includes/user-nav.jsp" />
        <div class="container">

            <c:forEach var="val" items="${product.rows}">

                <section class="py-5">
                    <div class="container px-4 px-lg-5 my-5">
                        <div class="row gx-4 gx-lg-5 align-items-center">
                            <div class="col-md-6"><img class="card-img-top mb-5 mb-md-0" src="data:image/jpeg;base64,${val.image}" alt="..." /></div>
                            <div class="col-md-6">
                                <div class="small mb-1">${val.artist} - ${val.genre} in ${val.year}</div>
                                <h1 class="display-5 fw-bolder">${val.name}</h1>
                                <div class="fs-5 mb-5">
                                    <!--<span class="text-decoration-line-through">$45.00</span>-->
                                    <span>${val.price}</span>
                                </div>
                                <p class="lead">${val.description}</p>
                                <div class="d-flex">
                                    <button class="btn btn-outline-dark flex-shrink-0" onclick="addToCart('${val.id}','${val.price}','${val.name}','${val.image}')" type="button">
                                        <i class="bi-cart-fill me-1"></i>
                                        Add to cart
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

            </c:forEach>

        </div>

    </div>



    <jsp:include page="includes/footer.jsp" />
</body>
</html>
