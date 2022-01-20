<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="tamplate/links.jsp" />
    <title><fmt:message key="header.home" />&nbsp;WebLib</title>
</head>
<body>
<jsp:include page="tamplate/header.jsp" />
<style>
    #home-page {
        border-bottom: 3px solid #5f5148;
    }
</style>
    <h2><fmt:message key="home.welcome" /></h2>
    <div class="home-container">
        <h3><fmt:message key="home.sign" /></h3>
        <img src="${pageContext.request.contextPath}/images/book-pusheen.png" alt="">
    </div>
<jsp:include page="tamplate/footer.jsp" />
</body>
</html>
