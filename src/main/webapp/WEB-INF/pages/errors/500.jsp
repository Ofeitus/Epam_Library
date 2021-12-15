<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="../tamplate/links.jsp" />
    <title>500</title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="error-container">
    <img src="${pageContext.request.contextPath}/images/500-pusheen.png" alt="">
    <h2>500</h2>
    <h3>Internal Server Error</h3>
    <h4>Server encountered an unexpected condition that prevented it from fulfilling the request</h4>
</div>
</body>
</html>
