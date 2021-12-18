<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}"/>
<fmt:bundle basename="labels"/>
<html>
<head>
    <jsp:include page="tamplate/links.jsp" />
    <title>Library</title>
</head>
<body>
<jsp:include page="tamplate/header.jsp" />
<style>
    #home-page {
        border-bottom: 3px solid #5f5148;
    }
</style>
    <h2>Welcome to WebLib!</h2>
    <div class="home-container">
    </div>
</body>
</html>
