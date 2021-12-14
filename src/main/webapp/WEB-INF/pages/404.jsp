<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="tamplate/links.jsp" />
    <title>404</title>
</head>
<body>
<jsp:include page="tamplate/header.jsp" />
<div class="error-container">
    <img src="${pageContext.request.contextPath}/images/404-pusheen.png" alt="">
    <h2>404</h2>
    <h3>Not Found</h3>
    <h4>The requested url was not found on server</h4>
</div>
</body>
</html>
