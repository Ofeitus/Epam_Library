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
                    <c:if test="${sessionScope.user_role == 'MANAGER'}">
                        <th scope="col" rowspan="2"><fmt:message key="user-loans.return" /></th>
                    </c:if>
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
                                          value="${(now.time - loan.dueDate.time) / (1000*60*60*24)}"
                                          maxFractionDigits="0" />
                        <c:if test="${diff <= 0 and loan.loanStatus == 'ISSUED'}">
                            <c:if test="${diff > -5 and loan.loanStatus == 'ISSUED'}">
                                <i class="bi bi-exclamation-triangle-fill" style="font-size:16px; color: gold;"></i>
                            </c:if>
                            <fmt:message key="user-loans.days-to-return" />&nbsp;${-diff}
                        </c:if>
                        <c:if test="${diff > 0 and loan.loanStatus == 'ISSUED'}">
                            <i class="bi bi-exclamation-triangle-fill" style="font-size:16px; color: firebrick;"></i>
                            <fmt:message key="user-loans.days-overdue" />&nbsp;${diff}
                        </c:if>
                        ${loan.returnDate}
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${loan.loanStatus == 'ISSUED'}">
                                <i class="bi bi-circle-fill" style="color:royalblue"></i>
                                <fmt:message key="loan-status.issued" />
                            </c:when>
                            <c:when test="${loan.loanStatus == 'RETURNED'}">
                                <i class="bi bi-circle-fill" style="color:forestgreen"></i>
                                <fmt:message key="loan-status.returned" />
                            </c:when>
                            <c:when test="${loan.loanStatus == 'FINED'}">
                                <i class="bi bi-circle-fill" style="color:firebrick"></i>
                                <fmt:message key="loan-status.fined" />
                            </c:when>
                            <c:when test="${loan.loanStatus == 'PAID'}">
                                <i class="bi bi-circle-fill" style="color:forestgreen"></i>
                                <fmt:message key="loan-status.paid" />
                            </c:when>
                        </c:choose>
                    </td>
                    <c:if test="${sessionScope.user_role == 'MANAGER'}">
                        <td>
                            <c:if test="${loan.loanStatus == 'ISSUED'}">
                                <a href="?command=return-loaned-book&user-id=${loan.userId}&loan-id=${loan.loanId}">
                                    <i class="bi bi-journal-arrow-down" style="font-size: 18px"></i>
                                    <fmt:message key="user-loans.return" />
                                </a>
                            </c:if>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>
