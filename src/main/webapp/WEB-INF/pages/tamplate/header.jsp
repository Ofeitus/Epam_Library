<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <jsp:include page="links.jsp" />
</header>
<header class="header">
    <a id="home-link" href="?command=goto-home-page">Library</a>
    <div class="navbar-container">
        <ul class="navbar-menu">
            <li class="navbar-item" id="home-page">
                <a href="?command=goto-home-page" class="navbar-link">Home</a>
            </li>
            <li class="navbar-item" id="about-page">
                <a href="#" class="navbar-link">About</a>
            </li>
            <li class="navbar-item" id="catalog-page">
                <a href="?command=goto-catalog-page" class="navbar-link">Catalog</a>
            </li>
            <li class="navbar-item" id="contacts-page">
                <a href="#" class="navbar-link">Contacts</a>
            </li>
        </ul>
    </div>
    <div class="info">
        <div class="locale">
            <div class="locale-button">
                <c:if test="${sessionScope.locale == 'ru'}">
                    <a href="?command=set-locale&locale=ru">Рус</a>
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
                    <a id="log-link" href="?command=goto-log-in-page">Log in</a>
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
                        <a href="#">Member profile</a>
                        <hr>
                        <a href="#">Your loans</a>
                        <a href="#">Your reservations</a>
                        <a href="#">Your fines</a>
                    </c:if>
                    <c:if test="${sessionScope.user_role.toString() == 'MANAGER'}">
                        <a href="#">Manager profile</a>
                        <hr>
                        <a href="#">Manage books</a>
                        <a href="#">Manage loans</a>
                        <a href="#">Manage reservations</a>
                        <a href="#">Manage fines</a>
                    </c:if>
                    <c:if test="${sessionScope.user_role.toString() == 'ADMIN'}">
                        <a href="#">Admin profile</a>
                        <hr>
                        <a href="?command=goto-manage-users-page">Manage users</a>
                        <a href="#">Loans report</a>
                        <a href="#">Fines report</a>
                    </c:if>
                    <hr>
                    <a href="?command=log-out">Log out</a>
                </div>
            </c:if>
        </div>
    </div>
</header>