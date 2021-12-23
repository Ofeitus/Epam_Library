<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="../tamplate/links.jsp" />
    <title><fmt:message key="user-profile.user-profile" /></title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="profile-container">
    <div class="profile-data">
        <i id="avatar" class="bi bi-person-circle" style="color: #333333"></i>
        <div class="personal-data">
            <label class="name-surname">${sessionScope.user_name}&nbsp;${sessionScope.user_surname}</label>
            <label class="email"><b><fmt:message key="user-profile.email" />:</b>&nbsp;&nbsp;${sessionScope.user_email}</label>
        </div>
    </div>
    <div class="w-100 row justify-content-md-center">
        <div class="col-md-4">
            <div class="form-container">
                <h3 class="title"><fmt:message key="user-profile.loan" /></h3>
                <form class="form-horizontal" action="controller" method="get">
                    <input type="hidden" name="command" value="goto-user-loans-page">
                    <c:if test="${requestScope.debts_count == 0}">
                        <div class="user-profile-info">
                            <i class="bi bi-check-circle-fill" style="color: forestgreen;"></i>
                            <label><fmt:message key="user-profile.no-debts" /></label>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.debts_count > 0}">
                        <div class="user-profile-info">
                            <i class="bi bi-exclamation-triangle-fill" style="color: firebrick;"></i>
                            <label><fmt:message key="user-profile.debts" />&nbsp;(${requestScope.debts_count})</label>
                        </div>
                    </c:if>
                    <div class="w-100 row justify-content-end">
                        <button class="h-50 col-4 btn submit"><fmt:message key="user-profile.view" /></button>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-container">
                <h3 class="title"><fmt:message key="user-profile.reservations" /></h3>
                <form class="form-horizontal" action="controller" method="get">
                    <input type="hidden" name="command" value="goto-user-reservations-page">
                    <c:if test="${requestScope.ready_reservations_count == 0}">
                        <div class="user-profile-info">
                            <label><fmt:message key="user-profile.no-ready-reservations" /></label>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.ready_reservations_count > 0}">
                        <div class="user-profile-info">
                            <i class="bi bi-exclamation-circle-fill" style="color: royalblue"></i>
                            <label><fmt:message key="user-profile.ready-reservations" />&nbsp;(${requestScope.ready_reservations_count})</label>
                        </div>
                    </c:if>
                    <div class="w-100 row justify-content-end">
                        <button class="h-50 col-4 btn submit"><fmt:message key="user-profile.view" /></button>
                    </div>
                </form>
            </div>
        </div>
        <div class="col-md-4">
            <div class="form-container">
                <h3 class="title"><fmt:message key="user-profile.fines" /></h3>
                <form class="form-horizontal" action="controller" method="get">
                    <input type="hidden" name="command" value="goto-user-fines-page">
                    <c:if test="${requestScope.unpaid_fines_count == 0}">
                        <div class="user-profile-info">
                            <i class="bi bi-check-circle-fill" style="color: forestgreen;"></i>
                            <label><fmt:message key="user-profile.no-unpaid-fines" /></label>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.unpaid_fines_count > 0}">
                        <div class="user-profile-info">
                            <i class="bi bi-exclamation-triangle-fill" style="color: firebrick;"></i>
                            <label><fmt:message key="user-profile.unpaid-fines" />&nbsp;(${requestScope.fines})</label>
                        </div>
                    </c:if>
                    <div class="w-100 row justify-content-end">
                        <button class="h-50 col-4 btn submit"><fmt:message key="user-profile.view" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>