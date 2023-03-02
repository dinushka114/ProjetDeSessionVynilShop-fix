<%@ taglib prefix= "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="en_US" scope="session" />
<fmt:setBundle basename="ApplicationResource" />

<li class="nav-item">
    <form action="LanguageController" method="POST">
        <input type="submit" name="action" value="FR" class="btn btn-primary m-1" />
    </form>


</li>
<li class="nav-item">
    <form action="LanguageController" method="POST">
        <input type="submit" name="action" value="EN" class="btn btn-primary m-1" />
    </form>
</li>
