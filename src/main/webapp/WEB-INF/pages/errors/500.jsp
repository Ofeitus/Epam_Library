<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
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
    <h3><fmt:message key="500.error" /></h3>
    <h4><fmt:message key="500.message" /></h4>
</div>
<jsp:include page="../tamplate/footer.jsp" />
</body>
</html>
