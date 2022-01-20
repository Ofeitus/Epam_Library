<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="../tamplate/links.jsp" />
    <title>403</title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="error-container">
    <img style="width: 250px" src="${pageContext.request.contextPath}/images/403-pusheen.png" alt="">
    <h2>403</h2>
    <h3><fmt:message key="403.forbidden" /></h3>
    <h4><fmt:message key="403.message" /></h4>
</div>
<jsp:include page="../tamplate/footer.jsp" />
</body>
</html>
