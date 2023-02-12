<%@ page contentType="text/html; charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.epam.ofeitus.library.controller.constant.RequestParameter" %>
<%@ page import="com.epam.ofeitus.library.controller.command.CommandName" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<header class="header">
    <a id="home-link" href="?${RequestParameter.COMMAND}=${CommandName.GOTO_HOME_PAGE_COMMAND}">WebLib</a>
    <div class="navbar-container">
        <ul class="navbar-menu">
            <li class="navbar-item" id="home-page">
                <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_HOME_PAGE_COMMAND}" class="navbar-link"><fmt:message key="header.home" /></a>
            </li>
            <li class="navbar-item" id="about-page">
                <a href="#" class="navbar-link"><fmt:message key="header.about" /></a>
            </li>
            <li class="navbar-item" id="catalog-page">
                <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_CATALOG_PAGE_COMMAND}" class="navbar-link"><fmt:message key="header.catalog" /></a>
            </li>
            <li class="navbar-item" id="contacts-page">
                <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_CONTACTS_PAGE_COMMAND}" class="navbar-link"><fmt:message key="header.contacts" /></a>
            </li>
        </ul>
    </div>
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
                    <c:if test="${sessionScope.user_role.toString() == 'MEMBER'}">
                        <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_PROFILE_PAGE_COMMAND}"><fmt:message key="header.member-profile" /></a>
                        <hr>
                        <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_USER_LOANS_PAGE_COMMAND}"><fmt:message key="header.your-loan" /></a>
                        <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_USER_RESERVATIONS_PAGE_COMMAND}"><fmt:message key="header.your-reservations" /></a>
                        <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_USER_FINES_PAGE_COMMAND}"><fmt:message key="header.your-fines" /></a>
                        <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_ATM_PAGE_COMMAND}">Банкомат</a>
                    </c:if>
                    <c:if test="${sessionScope.user_role.toString() == 'MANAGER'}">
                        <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_PROFILE_PAGE_COMMAND}"><fmt:message key="header.manager-profile" /></a>
                        <hr>
                        <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_INVENTORY_BOOK_PAGE_COMMAND}"><fmt:message key="header.inventory-book" /></a>
                        <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_MANAGE_RESERVATIONS_PAGE_COMMAND}"><fmt:message key="header.manage-reservations" /></a>
                        <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_MANAGE_MEMBERS_PAGE_COMMAND}"><fmt:message key="header.manage-members" /></a>
                    </c:if>
                    <c:if test="${sessionScope.user_role.toString() == 'ADMIN'}">
                        <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_PROFILE_PAGE_COMMAND}"><fmt:message key="header.admin-profile" /></a>
                        <hr>
                        <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_MANAGE_USERS_PAGE_COMMAND}"><fmt:message key="header.manage-users" /></a>
                        <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_REPORTS_PAGE_COMMAND}"><fmt:message key="header.reports" /></a>
                    </c:if>
                    <hr>
                    <a href="?${RequestParameter.COMMAND}=${CommandName.LOG_OUT_COMMAND}"><fmt:message key="header.log-out" /></a>
                </div>
            </c:if>
        </div>
    </div>
</header>