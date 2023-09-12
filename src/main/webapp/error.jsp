<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Security-Policy" content="default-src 'self';">
        <title>Error</title>
    </head>
    <body>
        <h1>An error occurred</h1>
        <p>
            Error Message: <%= request.getAttribute("javax.servlet.error.message")%>
        </p>
    </body>
</html>
