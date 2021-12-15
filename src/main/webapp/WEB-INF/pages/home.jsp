<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="tamplate/links.jsp" />
    <title>Library</title>
</head>
<body>
<jsp:include page="tamplate/header.jsp" />
    <h2>Welcome to Web Library!</h2>
    <div class="home-container">
        <form action="controller" method="get">
            <input type="hidden" name="command" value="goto-catalog-page">
            <button type="submit" class="btn submit">View catalog</button>
        </form>
    </div>
</body>
</html>
