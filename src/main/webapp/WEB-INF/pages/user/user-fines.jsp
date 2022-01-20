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
    <title><fmt:message key="user-fines.user-fines" /></title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="table-container">
    <c:if test="${requestScope.fines.size() == 0}">
        <h3>
            <c:if test="${sessionScope.user_role == 'MANAGER'}">
                <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_PROFILE_PAGE_COMMAND}&${RequestParameter.USER_ID}=${requestScope.user_id}">
                    <fmt:message key="user-reservations.member" />&nbsp;(id: ${requestScope.user_id})</a>&nbsp;-&nbsp;
            </c:if>
            <fmt:message key="user-fines.empty-user-fines" />
        </h3>
    </c:if>
    <c:if test="${requestScope.fines.size() > 0}">
        <h3>
            <c:if test="${sessionScope.user_role == 'MANAGER'}">
                <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_PROFILE_PAGE_COMMAND}&${RequestParameter.USER_ID}=${requestScope.user_id}">
                    <fmt:message key="user-reservations.member" />&nbsp;(id: ${requestScope.user_id})</a>&nbsp;-&nbsp;
            </c:if>
            <fmt:message key="user-fines.fines" />
        </h3>
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="user-fines.id" /></th>
                <th scope="col"><fmt:message key="user-fines.book-title" /></th>
                <th scope="col"><fmt:message key="user-fines.issue-date" /></th>
                <th scope="col"><fmt:message key="user-fines.due-date" /></th>
                <th scope="col"><fmt:message key="user-fines.return-date" /></th>
                <th scope="col"><fmt:message key="user-fines.fine-amount" /></th>
                <th scope="col"><fmt:message key="user-fines.fine-status" /></th>
                <c:if test="${sessionScope.user_role == 'MANAGER'}">
                    <th scope="col"><fmt:message key="user-fines.payment" /></th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.fines}" var="fine">
                <tr>
                    <td>${fine.loanId}</td>
                    <td><a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_BOOK_DETAILS_PAGE_COMMAND}&${RequestParameter.BOOK_ISBN}=${fine.book.isbn}">
                            ${fine.book.title}
                    </a></td>
                    <td>${fine.issueDate}</td>
                    <td>${fine.dueDate}</td>
                    <td>${fine.returnDate}</td>
                    <td>${fine.fineAmount}</td>
                    <td>
                        <c:choose>
                            <c:when test="${fine.loanStatus.toString() == 'FINED'}">
                                <i class="bi bi-circle-fill" style="color:firebrick"></i>
                                <fmt:message key="loan-status.fined" />
                            </c:when>
                            <c:when test="${fine.loanStatus.toString() == 'PAID'}">
                                <i class="bi bi-circle-fill" style="color:forestgreen"></i>
                                <fmt:message key="loan-status.paid" />
                            </c:when>
                        </c:choose>
                    </td>
                    <c:if test="${sessionScope.user_role == 'MANAGER'}">
                        <td>
                            <c:if test="${fine.loanStatus == 'FINED'}">
                                <a href="?${RequestParameter.COMMAND}=${CommandName.PAY_FINE_COMMAND}&${RequestParameter.LOAN_ID}=${fine.loanId}">
                                    <i class="bi bi-cash-stack" style="font-size: 18px"></i>
                                    <fmt:message key="user-fines.pay-fine" />
                                </a>
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