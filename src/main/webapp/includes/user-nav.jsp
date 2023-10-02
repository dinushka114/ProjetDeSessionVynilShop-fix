<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}" />
<fmt:setBundle basename="ApplicationResource" />

<nav id="nav" class="navbar navbar-expand-lg  navbar-light bg-warning">
    <div class="container" id="yellow">
        <a href="index.jsp" id="id-Nav">
            <img src="${pageContext.request.contextPath}/static/images/logo_vinyle.jpg" class="logo" alt="logo vinyl vault" style=" width: 100px;height: 80px;border-radius: 5px;">
        </a>
        <button class="navbar-toggler" type="button" data-toggle="collapse"
                data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav" id="nav-items">
                <li class="nav-item"><a class="nav-link" href="index.jsp"><fmt:message key="navbar.home" /></a></li>

                <li class="nav-item"> 
                    <a href="cart.jsp" class="btn btn-success">
                        <fmt:message key="navbar.basket" />  <span class="badge badge-danger" id="count"></span>
                        <span class="badge badge-danger" id="sum"></span>
                    </a>
                </li>

            </ul>      
            <ul class="d-flex ms-auto navbar-nav">

                <jsp:include page="language-bar.jsp" />

                <c:choose>
                    <c:when test="${sessionScope.userId != null}">
                        <li class="nav-item"><a class="nav-link" href="NavigationController?to=myOrders"><fmt:message key="navbar.order" /></a></li>
                        <form action="CustomerController" method="POST">

                            <button class="btn btn-danger" type="submit" value="Logout" name="action"><fmt:message key="navbar.logout" /></button>
                        </form>

                    </c:when>
                    <c:when test="${sessionScope.userId == null}">
                        <li class="nav-item"><a class="nav-link btn btn-dark text-white" href="customer-login.jsp"><fmt:message key="navbar.login" /></a></li>
                        </c:when>
                    </c:choose>




            </ul>



        </div>
    </div>
</nav>