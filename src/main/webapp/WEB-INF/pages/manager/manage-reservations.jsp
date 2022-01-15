<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="../tamplate/links.jsp" />
    <title><fmt:message key="header.manage-reservations" /></title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<h2 style="margin: 25px 0"><fmt:message key="header.manage-reservations" /></h2>
<div class="table-container" style="margin-top: 20px">
    <c:if test="${requestScope.reservations.size() == 0}">
        <h3><fmt:message key="manage-reservations.no-unconfirmed-reservations" /></h3>
    </c:if>
    <c:if test="${requestScope.reservations.size() > 0}">
        <h3><fmt:message key="manage-reservations.unconfirmed-reservations" /></h3>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="user-reservations.id" /></th>
                <th scope="col"><fmt:message key="user-reservations.inventory-id" /></th>
                <th scope="col"><fmt:message key="user-reservations.book-title" /></th>
                <th scope="col"><fmt:message key="user-reservations.date" /></th>
                <th scope="col"><fmt:message key="user-reservations.cancel" /></th>
                <th scope="col"><fmt:message key="manage-reservations.confirm" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.reservations}" var="reservation">
                <tr>
                    <td>${reservation.reservationId}</td>
                    <td>${reservation.inventoryId}</td>
                    <td><a href="?command=goto-book-details-page&book-isbn=${reservation.book.isbn}">
                            ${reservation.book.title}
                    </a></td>
                    <td>${reservation.date}</td>
                    <td style="text-align: center">
                        <a href="?command=cancel-reservation&redirect-command=goto-manage-reservations-page&reservation-id=${reservation.reservationId}&page=${requestScope.current_page}">
                            <i class="bi bi-trash-fill" style="font-size: 20px;color: firebrick"></i></a>
                    </td>
                    <td>
                        <a href="?command=confirm-reservation&reservation-id=${reservation.reservationId}&page=${requestScope.current_page}">
                            <i class="bi bi-check-circle-fill" style="font-size: 18px"></i>
                            <fmt:message key="manage-reservations.confirm-reservation" />
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pages-navigation">
            <c:if test="${requestScope.current_page != 1}">
                <a href="${sessionScope.url_without_page}${"&page="}${requestScope.current_page - 1}"><i class="bi bi-arrow-left-circle-fill"></i></a>
            </c:if>
            &nbsp;<span><fmt:message key="catalog.page" /> ${requestScope.current_page} <fmt:message key="catalog.of" /> ${requestScope.pages_count}</span>&nbsp;
            <c:if test="${requestScope.current_page < requestScope.pages_count}">
                <a href="${sessionScope.url_without_page}${"&page="}${requestScope.current_page + 1}"><i class="bi bi-arrow-right-circle-fill"></i></a>
            </c:if>
        </div>
    </c:if>
</div>
<jsp:include page="../tamplate/footer.jsp" />
</body>
</html>