
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
        <jsp:include page="includes/header.jsp" />
        <title>Vynil Shop - cart</title>
    </head>
    <body>
        <jsp:include page="includes/user-nav.jsp" />
        <div class="container">


            <h2 class="mt-4 mb-4">Shopping Cart</h2>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Item Id</th>
                        <th>Image</th>
                        <th>Item Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Remove</th>
                        
                    </tr>
                </thead>

                <tbody id='tbody'>

                </tbody>

                


            </table>
            <h3 id="total_price">Total Price: </h3>


        </div>

    </div>

 <!--<td>\${cartData[item].price} *\${cartData[item].qty}</td>-->

    <jsp:include page="includes/footer.jsp" />
    <script src="${pageContext.request.contextPath}/static/js/script.js"></script>
    <script>
      

        function showCart() {
            var cart = localStorage.getItem("cart");


            var html = "";
            var cartData = JSON.parse(cart);


            for (var item in cartData) {

                html += `
                        <tr>
                        <td>\${item}</td>
                        <td>
                                <img class='img-fluid' width='150px' src=data:image/jpeg;base64,\${cartData[item].image} />
                        </td>
                        <td>\${cartData[item].title}</td>
                        <td> <button class="btn btn-outline-primary" onclick="removeFromCart('\${item}','\${cartData[item].price}','\${cartData[item].title}','\${cartData[item].image}');showCart()" >-</button> <span style="padding:5px">\${cartData[item].qty}</span> <button onclick="addToCart('\${item}','\${cartData[item].price}','\${cartData[item].title}','\${cartData[item].image}');showCart()" class="btn btn-outline-primary">+</button> </td>
                        <td>\${cartData[item].price * cartData[item].qty}</td>
                        <td> <button onclick="remove('\${item}','\${cartData[item].price * cartData[item].qty}' , '\${cartData[item].qty}');showCart();updateCart();" class="btn btn-danger"><i class="fas fa-trash"></i></button> </td>
                </tr>
                `
            }
            document.getElementById('tbody').innerHTML = html;
        }


        
        window.addEventListener("load", (event) => {
            showCart();
        });

    </script>
</body>
</html>
