<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.ofeitus.library.controller.constant.RequestParameter" %>
<%@ page import="com.epam.ofeitus.library.controller.command.CommandName" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<header class="header">
    <a id="home-link">WebLib</a>
    <div class="info">
        <div class="locale">
            <div class="locale-button">
                <c:if test="${sessionScope.locale == 'ru'}">
                    <a <c:set var="user" value="${param.name}" scope="session" /> href="?${RequestParameter.COMMAND}=${CommandName.SET_LOCALE_COMMAND}&${RequestParameter.LOCALE}=ru">Рус</a>
                </c:if>
                <c:if test="${sessionScope.locale == 'en'}">
                    <a href="?${RequestParameter.COMMAND}=${CommandName.SET_LOCALE_COMMAND}&${RequestParameter.LOCALE}=en">Eng</a>
                </c:if>
            </div>
            <div class="locale-dropdown">
                <c:if test="${sessionScope.locale == 'ru'}">
                    <a href="?${RequestParameter.COMMAND}=${CommandName.SET_LOCALE_COMMAND}&${RequestParameter.LOCALE}=en">Eng</a>
                </c:if>
                <c:if test="${sessionScope.locale == 'en'}">
                    <a href="?${RequestParameter.COMMAND}=${CommandName.SET_LOCALE_COMMAND}&${RequestParameter.LOCALE}=ru">Рус</a>
                </c:if>
            </div>
        </div>
        <div class="auth">
            <c:if test="${sessionScope.user_id == null}">
                <label class="auth-button">
                    <a id="log-link" href="?${RequestParameter.COMMAND}=${CommandName.GOTO_LOG_IN_PAGE_COMMAND}"><fmt:message key="header.log-in" /></a>
                </label>
            </c:if>
            <c:if test="${sessionScope.user_id != null}">
                <div class="account">
                    ${sessionScope.user_email}&nbsp;&nbsp;
                    <i class="fa fa-user"></i>
                </div>
                <div class="account-dropdown">
                    <hr>
                    <a href="?${RequestParameter.COMMAND}=${CommandName.LOG_OUT_COMMAND}"><fmt:message key="header.log-out" /></a>
                </div>
            </c:if>
        </div>
    </div>
</header>