<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="../tamplate/links.jsp" />
    <title><fmt:message key="user-fines.user-fines" /></title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="table-container">
    <c:if test="${requestScope.fines.size() == 0}">
        <h3><fmt:message key="user-fines.empty-user-fines" /></h3>
    </c:if>
    <c:if test="${requestScope.fines.size() > 0}">
        <h3><fmt:message key="user-fines.fines" /></h3>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th scope="col" rowspan="1"><fmt:message key="user-fines.id" /></th>
                <th scope="col" rowspan="1"><fmt:message key="user-fines.book-title" /></th>
                <th scope="col" rowspan="1"><fmt:message key="user-fines.issue-date" /></th>
                <th scope="col" rowspan="1"><fmt:message key="user-fines.due-date" /></th>
                <th scope="col" rowspan="1"><fmt:message key="user-fines.return-date" /></th>
                <th scope="col" rowspan="1"><fmt:message key="user-fines.fine-amount" /></th>
                <th scope="col" rowspan="1"><fmt:message key="user-fines.fine-status" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.fines}" var="fine">
                <tr>
                    <td>${fine.loanId}</td>
                    <td><a href="?command=goto-book-details-page&book-isbn=${fine.book.isbn}">
                            ${fine.book.title}
                    </a></td>
                    <td>${fine.issueDate}</td>
                    <td>${fine.dueDate}</td>
                    <td>${fine.returnDate}</td>
                    <td>${fine.fineAmount}</td>
                    <td>${fine.loanStatus}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>