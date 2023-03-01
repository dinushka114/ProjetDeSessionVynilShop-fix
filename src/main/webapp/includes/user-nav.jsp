<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav id="nav" class="navbar navbar-expand-lg  navbar-light bg-warning">
    <div class="container" id="yellow">
        <a href="index.jsp" id="id-Nav">
            <img src="${pageContext.request.contextPath}/static/images/logo_vinyle.jpg" class="logo" alt="logo vinyl vault" style=" width: 160px;height: 80px;border-radius: 5px;">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav" id="nav-items">
                <li class="nav-item"><a class="nav-link" href="index.jsp">Home</a></li>

                <li class="nav-item"> 
                    <a href="cart.jsp" class="btn btn-success">
                        Panier <span class="badge badge-danger" id="count"></span>
                        <span class="badge badge-danger" id="sum" style="display: none;"></span>
                    </a>
                </li>

            </ul>      
            <ul class="d-flex ms-auto navbar-nav">

                <li class="nav-item"><a class="nav-link" href="log-out">FR</a></li>
                <li class="nav-item"><a class="nav-link" href="log-out">EN</a></li>

                <c:choose>
                    <c:when test="${sessionScope.userId != null}">
                        <form action="CustomerController" method="POST">
                            <input class="btn btn-danger" type="submit" value="Logout" name="action" />
                        </form>
                        </c:when>
                        <c:when test="${sessionScope.userId == null}">
                        <li class="nav-item"><a class="nav-link btn btn-dark text-white" href="customer-login.jsp">Login</a></li>
                        </c:when>
                    </c:choose>




            </ul>



        </div>
    </div>
</nav>