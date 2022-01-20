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
    <title><fmt:message key="profile.user-profile" /></title>
</head>
<body>
<jsp:include page="tamplate/header.jsp" />
<div class="profile-container">
    <div class="profile-data">
        <i id="avatar" class="bi bi-person-circle" style="color: #333333"></i>
        <div class="personal-data">
            <label class="name-surname">${requestScope.user.name}&nbsp;${requestScope.user.surname}</label>
            <table>
                <tr>
                    <td class="personal-data-name"><b><fmt:message key="profile.id" /></b></td>
                    <td class="personal-data-value">${requestScope.user.userId}</td>
                </tr>
                <tr>
                    <td class="personal-data-name"><b><fmt:message key="profile.email" />:</b></td>
                    <td class="personal-data-value">${requestScope.user.email}</td>
                </tr>
                <tr>
                    <td class="personal-data-name"><b><fmt:message key="profile.phone-number" />:</b></td>
                    <td class="personal-data-value">
                        <c:if test="${requestScope.user.phoneNumber == '' && requestScope.user.userId == sessionScope.user_id}">
                            <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_EDIT_PERSONAL_DATA_PAGE_COMMAND}"><fmt:message key="profile.add-phone-number" /></a>
                        </c:if>
                        <c:if test="${requestScope.user.phoneNumber != ''}">
                            ${requestScope.user.phoneNumber}
                        </c:if>
                    </td>
                </tr>
            </table>
        </div>
        <c:if test="${requestScope.user.userId == sessionScope.user_id}">
            <a class="edit-data-link" href="?${RequestParameter.COMMAND}=${CommandName.GOTO_EDIT_PERSONAL_DATA_PAGE_COMMAND}"><i class="bi bi-pencil-square"></i></a>
        </c:if>
    </div>
    <c:if test="${requestScope.user.userRole == 'MEMBER' || requestScope.user.userId != sessionScope.user_id}">
        <div class="w-100 row justify-content-md-center">
            <div class="col-md-4">
                <div class="form-container">
                    <h3 class="title"><fmt:message key="profile.loan" /></h3>
                    <form class="form-horizontal" action="controller" method="get">
                        <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.GOTO_USER_LOANS_PAGE_COMMAND}">
                        <input type="hidden" name="${RequestParameter.USER_ID}" value=${requestScope.user.userId}>
                        <c:if test="${requestScope.debts_count == 0}">
                            <div class="user-profile-info">
                                <i class="bi bi-check-circle-fill" style="color: forestgreen;"></i>
                                <label><fmt:message key="profile.no-debts" /></label>
                            </div>
                        </c:if>
                        <c:if test="${requestScope.debts_count > 0}">
                            <div class="user-profile-info">
                                <i class="bi bi-exclamation-triangle-fill" style="color: firebrick;"></i>
                                <label><fmt:message key="profile.debts" />&nbsp;(${requestScope.debts_count})</label>
                            </div>
                        </c:if>
                        <div class="w-100 row justify-content-end">
                            <button class="h-50 col-4 btn submit"><fmt:message key="profile.view" /></button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-container">
                    <h3 class="title"><fmt:message key="profile.reservations" /></h3>
                    <form class="form-horizontal" action="controller" method="get">
                        <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.GOTO_USER_RESERVATIONS_PAGE_COMMAND}">
                        <input type="hidden" name="${RequestParameter.USER_ID}" value=${requestScope.user.userId}>
                        <c:if test="${requestScope.ready_reservations_count == 0}">
                            <div class="user-profile-info">
                                <label><fmt:message key="profile.no-ready-reservations" /></label>
                            </div>
                        </c:if>
                        <c:if test="${requestScope.ready_reservations_count > 0}">
                            <div class="user-profile-info">
                                <i class="bi bi-exclamation-circle-fill" style="color: royalblue"></i>
                                <label><fmt:message key="profile.ready-reservations" />&nbsp;(${requestScope.ready_reservations_count})</label>
                            </div>
                        </c:if>
                        <div class="w-100 row justify-content-end">
                            <button class="h-50 col-4 btn submit"><fmt:message key="profile.view" /></button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-4">
                <div class="form-container">
                    <h3 class="title"><fmt:message key="profile.fines" /></h3>
                    <form class="form-horizontal" action="controller" method="get">
                        <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.GOTO_USER_FINES_PAGE_COMMAND}">
                        <input type="hidden" name="${RequestParameter.USER_ID}" value=${requestScope.user.userId}>
                        <c:if test="${requestScope.unpaid_fines_count == 0}">
                            <div class="user-profile-info">
                                <i class="bi bi-check-circle-fill" style="color: forestgreen;"></i>
                                <label><fmt:message key="profile.no-unpaid-fines" /></label>
                            </div>
                        </c:if>
                        <c:if test="${requestScope.unpaid_fines_count > 0}">
                            <div class="user-profile-info">
                                <i class="bi bi-exclamation-triangle-fill" style="color: firebrick;"></i>
                                <label><fmt:message key="profile.unpaid-fines" />&nbsp;(${requestScope.unpaid_fines_count})</label>
                            </div>
                        </c:if>
                        <div class="w-100 row justify-content-end">
                            <button class="h-50 col-4 btn submit"><fmt:message key="profile.view" /></button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </c:if>
</div>
<jsp:include page="tamplate/footer.jsp" />
</body>
</html>