<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <title>Error</title>
</head>
<body>
    <h1>An error occurred</h1>
    <p>
        Error Message: <%= request.getAttribute("javax.servlet.error.message") %>
    </p>
</body>
</html>
