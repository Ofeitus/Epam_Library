<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.epam.ofeitus.library.controller.constant.RequestParameter" %>
<%@ page import="com.epam.ofeitus.library.controller.command.CommandName" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="../tamplate/links.jsp" />
    <title><fmt:message key="user-reservations.user-reservations" /></title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="table-container">
    <c:if test="${requestScope.reservations.size() == 0}">
        <h3>
            <c:if test="${sessionScope.user_role == 'MANAGER'}">
                <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_PROFILE_PAGE_COMMAND}&${RequestParameter.USER_ID}=${requestScope.user_id}">
                    <fmt:message key="user-reservations.member" />&nbsp;(id: ${requestScope.user_id})</a>&nbsp;-&nbsp;
            </c:if>
            <fmt:message key="user-reservations.empty-user-reservations" />
        </h3>
    </c:if>
    <c:if test="${requestScope.reservations.size() > 0}">
        <h3>
            <c:if test="${sessionScope.user_role == 'MANAGER'}">
                <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_PROFILE_PAGE_COMMAND}&${RequestParameter.USER_ID}=${requestScope.user_id}">
                    <fmt:message key="user-reservations.member" />&nbsp;(id: ${requestScope.user_id})</a>&nbsp;-&nbsp;
            </c:if>
            <fmt:message key="user-reservations.reservations" />
        </h3>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="user-reservations.id" /></th>
                <th scope="col"><fmt:message key="user-reservations.inventory-id" /></th>
                <th scope="col"><fmt:message key="user-reservations.book-title" /></th>
                <th scope="col"><fmt:message key="user-reservations.date" /></th>
                <th scope="col"><fmt:message key="user-reservations.status" /></th>
                <th scope="col"><fmt:message key="user-reservations.cancel" /></th>
                <c:if test="${sessionScope.user_role == 'MANAGER'}">
                    <th scope="col"><fmt:message key="user-reservations.issue" /></th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.reservations}" var="reservation">
                <tr>
                    <td>${reservation.reservationId}</td>
                    <td>${reservation.inventoryId}</td>
                    <td><a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_BOOK_DETAILS_PAGE_COMMAND}&${RequestParameter.BOOK_ISBN}=${reservation.book.isbn}">
                            ${reservation.book.title}
                    </a></td>
                    <td>${reservation.date}</td>
                    <td>
                        <c:choose>
                            <c:when test="${reservation.reservationStatus == 'RESERVED'}">
                                <i class="bi bi-circle-fill" style="color:gray"></i>
                                <fmt:message key="reservation-status.reserved" />
                            </c:when>
                            <c:when test="${reservation.reservationStatus == 'READY_TO_ISSUE'}">
                                <i class="bi bi-circle-fill" style="color:royalblue"></i>
                                <fmt:message key="reservation-status.ready-to-issue" />
                            </c:when>
                            <c:when test="${reservation.reservationStatus == 'ISSUED'}">
                                <i class="bi bi-circle-fill" style="color:forestgreen"></i>
                                <fmt:message key="reservation-status.issued" />
                            </c:when>
                        </c:choose>
                    </td>
                    <td style="text-align: center">
                        <c:if test="${reservation.reservationStatus != 'ISSUED'}">
                            <form action="controller" method="post">
                                <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.CANCEL_RESERVATION_COMMAND}">
                                <input type="hidden" name="${RequestParameter.RESERVATION_ID}" value="${reservation.reservationId}">
                                <button type="submit" class="link-button"><i class="bi bi-trash-fill" style="font-size: 20px;color: firebrick"></i></button>
                            </form>
                        </c:if>
                    </td>
                    <c:if test="${sessionScope.user_role == 'MANAGER'}">
                        <td>
                            <c:if test="${reservation.reservationStatus != 'ISSUED'}">
                                <form action="controller" method="post">
                                    <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.ISSUE_RESERVED_BOOK_COMMAND}">
                                    <input type="hidden" name="${RequestParameter.RESERVATION_ID}" value="${reservation.reservationId}">
                                    <button type="submit" class="link-button"><i class="bi bi-journal-arrow-up" style="font-size: 18px"></i>
                                        <span><fmt:message key="user-reservations.issue-for-loan" /></span></button>
                                </form>
                            </c:if>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pages-navigation">
            <c:if test="${requestScope.current_page != 1}">
                <a href="${sessionScope.url_without_page}&${RequestParameter.PAGE}=${requestScope.current_page - 1}"><i class="bi bi-arrow-left-circle-fill"></i></a>
            </c:if>
            &nbsp;<span><fmt:message key="catalog.page" /> ${requestScope.current_page} <fmt:message key="catalog.of" /> ${requestScope.pages_count}</span>&nbsp;
            <c:if test="${requestScope.current_page < requestScope.pages_count}">
                <a href="${sessionScope.url_without_page}&${RequestParameter.PAGE}=${requestScope.current_page + 1}"><i class="bi bi-arrow-right-circle-fill"></i></a>
            </c:if>
        </div>
    </c:if>
</div>
<jsp:include page="../tamplate/footer.jsp" />
</body>
</html>
