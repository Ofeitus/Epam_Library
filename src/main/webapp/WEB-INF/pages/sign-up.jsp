<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.epam.ofeitus.library.controller.constant.RequestParameter" %>
<%@ page import="com.epam.ofeitus.library.controller.command.CommandName" %>
<%@ page import="com.epam.ofeitus.library.service.validator.ValidationPattern" %>

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
                    <form class="form-horizontal" action="controller" method="post">
                        <--input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.SIGN_UP_COMMAND}">
                        <div class="form-group">
                            <label><fmt:message key="sign-up.name" />
                                <input class="form-control" type="text" pattern="${ValidationPattern.NAME_PATTERN}" title="<fmt:message key="validation-pattern.name" />"
                                       name="${RequestParameter.USER_NAME}" value="" placeholder="<fmt:message key="sign-up.name-placeholder" />" required>
                            </label>
                        </div>
                        <div class="form-group">
                            <label><fmt:message key="sign-up.surname" />
                                <input class="form-control" type="text" pattern="${ValidationPattern.NAME_PATTERN}" title="<fmt:message key="validation-pattern.name" />"
                                       name="${RequestParameter.USER_SURNAME}" value="" placeholder="<fmt:message key="sign-up.surname-placeholder" />" required>
                            </label>
                        </div>
                        <div class="form-group">
                            <label><fmt:message key="sign-up.email" />
                                <input class="form-control" type="email" name="${RequestParameter.EMAIL}" value="" placeholder="<fmt:message key="sign-up.email-placeholder" />" required>
                            </label>
                        </div>
                        <div class="form-group">
                            <label><fmt:message key="sign-up.password" />
                                <input class="form-control" type="password" pattern="${ValidationPattern.PASSWORD_PATTERN}" title="<fmt:message key="validation-pattern.password" />"
                                       name="${RequestParameter.PASSWORD}" value="" placeholder="<fmt:message key="sign-up.password-placeholder" />" required>
                            </label>
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
                                <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_LOG_IN_PAGE_COMMAND}"><fmt:message key="sign-up.log-in" /></a></span>
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
