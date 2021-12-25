<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
        <h3><fmt:message key="user-reservations.empty-user-reservations" /></h3>
    </c:if>
    <c:if test="${requestScope.reservations.size() > 0}">
        <h3><fmt:message key="user-reservations.reservations" /></h3>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="user-reservations.id" /></th>
                <th scope="col"><fmt:message key="user-reservations.book-title" /></th>
                <th scope="col"><fmt:message key="user-reservations.date" /></th>
                <th scope="col"><fmt:message key="user-reservations.status" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.reservations}" var="reservation">
                <tr>
                    <td>${reservation.reservationId}</td>
                    <td><a href="?command=goto-book-details-page&book-isbn=${reservation.book.isbn}">
                            ${reservation.book.title}
                    </a></td>
                    <td>${reservation.date}</td>
                    <td><i class="bi bi-circle-fill"
                        <c:choose>
                            <c:when test="${reservation.reservationStatus.toString() == 'RESERVED'}">
                                style="color:gray"
                            </c:when>
                            <c:when test="${reservation.reservationStatus.toString() == 'READY_TO_ISSUE'}">
                                style="color:royalblue"
                            </c:when>
                            <c:when test="${reservation.reservationStatus.toString() == 'ISSUED'}">
                                style="color:forestgreen"
                            </c:when>
                        </c:choose>
                    ></i>${reservation.reservationStatus}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>
