<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="../tamplate/links.jsp" />
    <title><fmt:message key="user-loans.user-loan" /></title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="table-container">
    <c:if test="${requestScope.books.size() == 0}">
        <h3><fmt:message key="user-loans.emnty-loan" /></h3>
    </c:if>
    <c:if test="${requestScope.books.size() > 0}">
        <h3><fmt:message key="user-loans.loan" /></h3>
        <table class="table table-hover table-striped">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="user-loans.id" /></th>
                <th scope="col"><fmt:message key="user-loans.books" /></th>
                <th scope="col"><fmt:message key="user-loans.issue-date" /></th>
                <th scope="col"><fmt:message key="user-loans.due-date" /></th>
                <th scope="col"><fmt:message key="user-loans.return-date" /></th>
                <th scope="col"><fmt:message key="user-loans.fine-amount" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.loans}" var="loan">
                <tr>
                    <td>${loan.loanId}</td>
                    <td>${loan.copiesOfBooks}</td>
                    <td>${loan.issueDate}</td>
                    <td>${loan.dueDate}</td>
                    <td>${loan.returnDate}</td>
                    <td>${loan.fineAmount}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>
