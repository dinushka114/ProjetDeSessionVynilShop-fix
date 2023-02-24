
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="includes/header.jsp"%>
        <title>Vynil Shop</title>
    </head>
    <body>

        <div class="container">
            
            <form action="TestServlet" method="POST">
                <input type="submit" value="GO" />
            </form>

            
            <div class="card-header my-3" id="card-header">Tous les Produits</div>
            <div class="row" id="max-wd">
                
                
                <!-- card -->
                <div class="col-md-3 my-3">
                    <div class="card w-100">
                        <img class="card-img-top" src="product-image"
                             alt="Card image cap">
                        <div class="card-body">


                            <h5 class="card-title">title</h5>
                            <h6 class="price">price</h6>
                            <h6 class="category">Artist:kasun</h6>
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
              
                
           
                
               
                
            </div>
        </div>

    </div>



    <%@include file="includes/footer.jsp"%>
</body>
</html>
