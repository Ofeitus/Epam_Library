<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<header>
    <jsp:include page="links.jsp" />
</header>
<header class="header">
    <a id="home-link" href="?command=goto-home-page">WebLib</a>
    <div class="navbar-container">
        <ul class="navbar-menu">
            <li class="navbar-item" id="home-page">
                <a href="?command=goto-home-page" class="navbar-link"><fmt:message key="header.home" /></a>
            </li>
            <li class="navbar-item" id="about-page">
                <a href="#" class="navbar-link"><fmt:message key="header.about" /></a>
            </li>
            <li class="navbar-item" id="catalog-page">
                <a href="?command=goto-catalog-page" class="navbar-link"><fmt:message key="header.catalog" /></a>
            </li>
            <li class="navbar-item" id="contacts-page">
                <a href="#" class="navbar-link"><fmt:message key="header.contacts" /></a>
            </li>
        </ul>
    </div>
    <div class="info">
        <div class="locale">
            <div class="locale-button">
                <c:if test="${sessionScope.locale == 'ru'}">
                    <a <c:set var="user" value="${param.name}" scope="session" /> href="?command=set-locale&locale=ru">Рус</a>
                </c:if>
                <c:if test="${sessionScope.locale == 'en'}">
                    <a href="?command=set-locale&locale=en">Eng</a>
                </c:if>
            </div>
            <div class="locale-dropdown">
                <c:if test="${sessionScope.locale == 'ru'}">
                    <a href="?command=set-locale&locale=en">Eng</a>
                </c:if>
                <c:if test="${sessionScope.locale == 'en'}">
                    <a href="?command=set-locale&locale=ru">Рус</a>
                </c:if>
            </div>
        </div>
        <div class="auth">
            <c:if test="${sessionScope.user_id == null}">
                <label class="auth-button">
                    <a id="log-link" href="?command=goto-log-in-page"><fmt:message key="header.log-in" /></a>
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
                        <a href="?command=goto-profile-page"><fmt:message key="header.member-profile" /></a>
                        <hr>
                        <a href="?command=goto-user-loans-page"><fmt:message key="header.your-loan" /></a>
                        <a href="?command=goto-user-reservations-page"><fmt:message key="header.your-reservations" /></a>
                        <a href="?command=goto-user-fines-page"><fmt:message key="header.your-fines" /></a>
                    </c:if>
                    <c:if test="${sessionScope.user_role.toString() == 'MANAGER'}">
                        <a href="?command=goto-profile-page"><fmt:message key="header.manager-profile" /></a>
                        <hr>
                        <a href="?command=goto-inventory-book-page"><fmt:message key="header.inventory-book" /></a>
                        <a href="?command=goto-manage-members-page"><fmt:message key="header.manage-members" /></a>
                    </c:if>
                    <c:if test="${sessionScope.user_role.toString() == 'ADMIN'}">
                        <a href="?command=goto-profile-page"><fmt:message key="header.admin-profile" /></a>
                        <hr>
                        <a href="?command=goto-manage-users-page"><fmt:message key="header.manage-users" /></a>
                        <a href="#"><fmt:message key="header.loans-report" /></a>
                        <a href="#"><fmt:message key="header.fines-report" /></a>
                    </c:if>
                    <hr>
                    <a href="?command=log-out"><fmt:message key="header.log-out" /></a>
                </div>
            </c:if>
        </div>
    </div>
</header>