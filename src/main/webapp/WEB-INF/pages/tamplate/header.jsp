<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <jsp:include page="links.jsp" />
</header>
<header class="header">
    <a id="home-link" href="?command=goto-home-page">Library</a>
    <div class="info">
        <a id="location" href="https://www.google.com/maps/place/%D0%9C%D0%B8%D0%BD%D1%81%D0%BA/@53.7670988,24.7025942,6.46z/data=!4m5!3m4!1s0x46dbcfd35b1e6ad3:0xb61b853ddb570d9!8m2!3d53.9006011!4d27.558972">
            <i class="fa fa-map-marker-alt"></i>&nbsp;&nbsp;Minsk, Belarus</a>
        <a id="contacts"><i class="fa fa-phone"></i>&nbsp;&nbsp; +375(12)345-67-89 <br><i class="fa fa-mail-bulk"></i>&nbsp;&nbsp; sampleEmail@email.com</a>
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
                <div class="dropdown-content">
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