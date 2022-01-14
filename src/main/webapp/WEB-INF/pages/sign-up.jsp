<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="tamplate/links.jsp" />
    <title><fmt:message key="sign-up.sign-up" /></title>
</head>
<body>
<jsp:include page="tamplate/header.jsp" />
    <div class="container-fluid d-flex">
        <div class="w-100 row justify-content-md-center">
            <div class="col-md-5">
                <div class="form-container">
                    <h3 class="title"><fmt:message key="sign-up.sign-up" /></h3>
                    <form class="form-horizontal" action="controller" method="get">
                        <input type="hidden" name="command" value="sign-up">
                        <div class="form-group">
                            <label><fmt:message key="sign-up.name" /></label>
                            <input type="text" name="first-name" value="" class="form-control" placeholder="<fmt:message key="sign-up.name-placeholder" />" required>
                        </div>
                        <div class="form-group">
                            <label><fmt:message key="sign-up.surname" /></label>
                            <input type="text" name="second-name" value="" class="form-control" placeholder="<fmt:message key="sign-up.surname-placeholder" />" required>
                        </div>
                        <div class="form-group">
                            <label><fmt:message key="sign-up.email" /></label>
                            <input type="email" name="email" value="" class="form-control" placeholder="<fmt:message key="sign-up.email-placeholder" />" required>
                        </div>
                        <div class="form-group">
                            <label><fmt:message key="sign-up.password" /></label>
                            <input type="password" name="password" value="" class="form-control" placeholder="<fmt:message key="sign-up.password-placeholder" />" required>
                        </div>
                        <c:if test="${sessionScope.error != null}">
                            <div class="w-100 row justify-content-left">
                                <label class="error-message">${sessionScope.error}</label>
                                    ${sessionScope.remove("error")}
                            </div>
                        </c:if>
                        <div class="w-100 row justify-content-between">
                            <span class="col-5 sign-link">
                                <fmt:message key="sign-up.log-in-offer" />
                                <a href="?command=goto-log-in-page"><fmt:message key="sign-up.log-in" /></a></span>
                            <button class="h-50 col-4 btn submit"><fmt:message key="sign-up.create-account" /></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<jsp:include page="tamplate/footer.jsp" />
</body>
</html>
