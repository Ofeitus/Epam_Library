<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.epam.ofeitus.library.controller.constant.RequestParameter" %>
<%@ page import="com.epam.ofeitus.library.controller.command.CommandName" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="tamplate/links.jsp" />
    <title><fmt:message key="log-in.log-in-page" /></title>
</head>
<body>
<jsp:include page="tamplate/header.jsp" />
<div class="container-fluid d-flex">
    <div class="w-100 row justify-content-md-center">
        <div class="col-md-5">
            <div class="form-container">
                <h3 class="title"><fmt:message key="log-in.log-in-page" /></h3>
                <form class="form-horizontal" action="controller" method="post">
                    <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.LOG_IN_COMMAND}">
                    <div class="form-group">
                        <label><fmt:message key="log-in.email" />
                            <input type="email" name="${RequestParameter.EMAIL}" value="${requestScope.user_email}" class="form-control" placeholder="<fmt:message key="log-in.email-placeholder" />" required>
                        </label>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="log-in.password" />
                            <input type="password" name="${RequestParameter.PASSWORD}" value="" class="form-control" placeholder="<fmt:message key="log-in.password-placeholder" />" required>
                        </label>
                    </div>
                    <c:if test="${sessionScope.error != null}">
                        <div class="w-100 row justify-content-left">
                            <label class="error-message">${sessionScope.error}</label>
                            ${sessionScope.remove("error")}
                        </div>
                    </c:if>
                    <div class="w-100 row justify-content-between">
                        <div class="col-5 sign-link">
                            <fmt:message key="log-in.sign-up-offer" />
                            <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_SIGN_UP_PAGE_COMMAND}"><fmt:message key="log-in.sign-up" /></a>
                        </div>
                        <button class="h-50 col-3 btn submit"><fmt:message key="log-in.log-in" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="tamplate/footer.jsp" />
</body>
</html>
