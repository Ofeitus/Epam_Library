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
        <a><i class="fa fa-phone"></i>&nbsp;&nbsp; +375(12)345-67-89 <br><i class="fa fa-mail-bulk"></i>&nbsp;&nbsp; sampleEmail@email.com</a>
        <div class="auth">
            <label class="auth-button">
                <c:if test="${sessionScope.user_id == null}">
                    <a id="log-link" href="?command=goto-log-in-page">Log in</a>
                </c:if>
                <c:if test="${sessionScope.user_id != null}">
                    <a id="log-link" href="?command=log-out">Log out</a>
                </c:if>
            </label>
            <div class="account">
                <label class="user-email">
                    <c:if test="${sessionScope.user_email != null}">
                        ${sessionScope.user_email}
                        &nbsp;
                        &nbsp;
                    </c:if>
                </label>
                <i class="fa fa-user"></i>
            </div>
        </div>
    </div>
</header>