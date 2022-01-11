<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="now" class="java.util.Date" />
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
    <c:if test="${requestScope.loans.size() == 0}">
        <h3>
            <c:if test="${sessionScope.user_role == 'MANAGER'}">
                <a href="?command=goto-profile-page&user-id=${requestScope.user_id}">
                    <fmt:message key="user-reservations.member" />&nbsp;(id: ${requestScope.user_id})</a>&nbsp;-&nbsp;
            </c:if>
            <fmt:message key="user-loans.empty-loan" />
        </h3>
    </c:if>
    <c:if test="${requestScope.loans.size() > 0}">
        <h3>
            <c:if test="${sessionScope.user_role == 'MANAGER'}">
                <a href="?command=goto-profile-page&user-id=${requestScope.user_id}">
                    <fmt:message key="user-reservations.member" />&nbsp;(id: ${requestScope.user_id})</a>&nbsp;-&nbsp;
            </c:if>
            <fmt:message key="user-loans.loan" />
        </h3>
        <table class="table table-bordered table-striped">
            <thead>
                <tr>
                    <th scope="col" rowspan="2"><fmt:message key="user-loans.id" /></th>
                    <th scope="col" rowspan="1" colspan="2"><fmt:message key="user-loans.books" /></th>
                    <th scope="col" rowspan="2"><fmt:message key="user-loans.issue-date" /></th>
                    <th scope="col" rowspan="2"><fmt:message key="user-loans.due-date" /></th>
                    <th scope="col" rowspan="2"><fmt:message key="user-loans.return-date" /></th>
                    <th scope="col" rowspan="2"><fmt:message key="user-loans.loan-status" /></th>
                </tr>
                <tr>
                    <th scope="col" rowspan="1"><fmt:message key="user-loans.book-inventory-id" /></th>
                    <th scope="col" rowspan="1"><fmt:message key="user-loans.book-title" /></th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.loans}" var="loan">
                <tr>
                    <td>${loan.loanId}</td>
                    <td>${loan.inventoryId}</td>
                    <td><a href="?command=goto-book-details-page&book-isbn=${loan.book.isbn}">
                            ${loan.book.title}
                    </a></td>
                    <td>${loan.issueDate}</td>
                    <td>${loan.dueDate}</td>
                    <td>
                        <fmt:formatNumber var="diff"
                                          value="${(now.time - loan.dueDate.time) / (1000*60*60*24) - 1}"
                                          maxFractionDigits="0" />
                        <c:if test="${diff <= 0 and loan.loanStatus.toString() == 'ISSUED'}">
                            <c:if test="${diff > -5 and loan.loanStatus.toString() == 'ISSUED'}">
                                <i class="bi bi-exclamation-triangle-fill" style="font-size:16px; color: gold;"></i>
                            </c:if>
                            <fmt:message key="user-loans.days-to-return" />&nbsp;${-diff}
                        </c:if>
                        <c:if test="${diff > 0 and loan.loanStatus.toString() == 'ISSUED'}">
                            <i class="bi bi-exclamation-triangle-fill" style="font-size:16px; color: firebrick;"></i>
                            <fmt:message key="user-loans.days-overdue" />&nbsp;${diff}
                        </c:if>
                        ${loan.returnDate}
                    </td>
                    <td><i class="bi bi-circle-fill"
                    <c:choose>
                    <c:when test="${loan.loanStatus.toString() == 'ISSUED'}">
                        style="color:royalblue"
                    </c:when>
                    <c:when test="${loan.loanStatus.toString() == 'RETURNED'}">
                        style="color:forestgreen"
                    </c:when>
                    <c:when test="${loan.loanStatus.toString() == 'FINED'}">
                        style="color:firebrick"
                    </c:when>
                    <c:when test="${loan.loanStatus.toString() == 'PAID'}">
                        style="color:forestgreen"
                    </c:when>
                    </c:choose>
                    ></i>${loan.loanStatus}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>
