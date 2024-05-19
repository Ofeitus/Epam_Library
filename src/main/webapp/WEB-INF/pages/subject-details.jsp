<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.epam.ofeitus.library.controller.constant.RequestParameter" %>
<%@ page import="com.epam.ofeitus.library.controller.command.CommandName" %>
<%@ page import="com.epam.ofeitus.library.entity.user.constituent.UserRole" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="tamplate/links.jsp" />
    <title><fmt:message key="book-details.book-details" /></title>
</head>

<body>
<jsp:include page="tamplate/header.jsp" />
<style>
    #catalog-page {
        border-bottom: 3px solid #5f5148;
    }
</style>
<div class="details-container">
    <div class="img-container">
        <img onerror="$(this).attr('src', '${pageContext.request.contextPath}/images/book-placeholder.png');" src="${pageContext.request.contextPath}/images/books/${requestScope.subject.id}.jpg" alt="">
    </div>
    <div class="book-data">
        <div style="display: flex;justify-content: space-between">
            <h3 class="book-title">${requestScope.book.title}</h3>
            <c:if test="${sessionScope.user_role == UserRole.MANAGER}">
                <div class="edit-book-data-links">
                    <a class="edit-data-link" style="padding-top: 5px" href="?${RequestParameter.COMMAND}=${CommandName.GOTO_EDIT_SUBJECT_PAGE_COMMAND}&${RequestParameter.SUBJECT_ID}=${requestScope.subject.id}"><i class="bi bi-pencil-square"></i></a>
                    <c:if test="${requestScope.copies_count > 0}">
                        <a class="edit-data-link" title="<fmt:message key="book-details.cant-delete" />" style="padding-top: 5px;"><i class="bi bi-trash-fill"></i></a>
                    </c:if>
                    <c:if test="${requestScope.copies_count == 0}">
                        <form action="controller" method="post" style="margin-top: 10px">
                            <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.DELETE_SUBJECT_COMMAND}">
                            <input type="hidden" name="${RequestParameter.SUBJECT_ID}" value="${requestScope.subject.id}">
                            <button type="submit" class="link-button"><i class="bi bi-trash-fill edit-data-link" style="color: firebrick"></i></button>
                        </form>
                    </c:if>
                </div>
            </c:if>
        </div>
        <table>
            <tr>
                <td class="book-field-name"><fmt:message key="book-details.ISBN" /></td>&nbsp;
                <td class="book-field-value">${requestScope.subject.id}</td>
            </tr>
            <tr>
                <td class="book-field-name"><fmt:message key="book-details.category" /></td>&nbsp;
                <td class="book-field-value">${requestScope.book.category}</td>
            </tr>
            <tr>
                <td class="book-field-name"><fmt:message key="book-details.authors" /></td>
                <td class="book-field-value">
                    <c:forEach items="${requestScope.book.authors}" var="author" varStatus="i">
                        ${author.name}&nbsp;${author.surname}
                        <c:if test="${i.index < requestScope.book.authors.size() - 1}">
                            ,&nbsp;
                        </c:if>
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td class="book-field-name"><fmt:message key="book-details.publication-year" /></td>
                <td class="book-field-value">${requestScope.book.publicationYear}</td>
            </tr>
            <tr>
                <td class="book-field-name"><fmt:message key="book-details.language" /></td>
                <td class="book-field-value">${requestScope.book.language}</td>
            </tr>
            <tr>
                <td class="book-field-name"><fmt:message key="book-details.key-words" /></td>
                <td class="book-field-value">${requestScope.book.keyWords}</td>
            </tr>
        </table>
        <c:if test="${sessionScope.user_role == UserRole.GUEST || sessionScope.user_role == UserRole.MEMBER}">
            <div class="form-container">
                <h3 class="title"><fmt:message key="book-details.reservation" /></h3>
                <form class="form-horizontal" action="controller" method="post">
                    <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.RESERVE_BOOK_COMMAND}">
                    <input type="hidden" name="${RequestParameter.SUBJECT_ID}" value="${requestScope.subject.id}">
                    <c:if test="${requestScope.available_copies_count > 0}">
                        <div class="reservation-info">
                            <label><fmt:message key="book-details.available-copies" />&nbsp;${requestScope.available_copies_count}</label>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.available_copies_count == 0}">
                        <div class="reservation-info">
                            <i class="bi bi-exclamation-triangle-fill" style="color: gold;"></i>
                            <label><fmt:message key="book-details.no-available-copies" /></label>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.reserved_books_count + requestScope.issued_books_count >= requestScope.max_member_books}">
                        <div class="reservation-info">
                            <i class="bi bi-exclamation-triangle-fill" style="color: gold;"></i>
                            <label><fmt:message key="book-details.reservation-limit" /></label>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.user_id == null}">
                        <div class="reservation-info">
                            <i class="bi bi-exclamation-circle-fill" style="color: royalblue;"></i>
                            <label><a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_LOG_IN_PAGE_COMMAND}"><fmt:message key="book-details.log-in-" /></a><fmt:message key="book-details.-to-reserve" /></label>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.error != null}">
                        <div class="w-100 row justify-content-left">
                            <label class="error-message">${sessionScope.error}</label>
                                ${sessionScope.remove("error")}
                        </div>
                    </c:if>
                    <div class="w-100 row justify-content-end">
                        <c:if test="${sessionScope.user_id != null and
                                requestScope.reserved_books_count + requestScope.issued_books_count < requestScope.max_member_books and
                                requestScope.available_copies_count > 0}">
                            <button class="h-50 col-4 btn submit"><fmt:message key="book-details.reserve" /></button>
                        </c:if>
                        <c:if test="${sessionScope.user_id == null or
                                requestScope.reserved_books_count + requestScope.issued_books_count >= requestScope.max_member_books or
                                requestScope.available_copies_count == 0}">
                            <button class="h-50 col-4 btn submit" disabled><fmt:message key="book-details.reserve" /></button>
                        </c:if>
                    </div>
                </form>
            </div>
        </c:if>
        <c:if test="${sessionScope.user_role == UserRole.MANAGER}">
            <div class="form-container">
                <h3 class="title"><fmt:message key="book-details.copies" /></h3>
                <form class="form-horizontal" action="controller" method="get">
                    <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.SEARCH_COPIES_OF_BOOKS_COMMAND}">
                    <input type="hidden" name="${RequestParameter.SUBJECT_ID}" value="${requestScope.subject.id}">
                    <input type="hidden" name="${RequestParameter.INVENTORY_ID}" value="0">
                    <input type="hidden" name="${RequestParameter.STATUS}" value="${RequestParameter.STATUS_EXISTING}">
                    <c:if test="${requestScope.copies_count > 0}">
                        <div class="reservation-info">
                            <label><fmt:message key="book-details.number-of-copies" />&nbsp;${requestScope.copies_count}</label>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.copies_count == 0}">
                        <div class="reservation-info">
                            <i class="bi bi-exclamation-triangle-fill" style="color: gold;"></i>
                            <label><fmt:message key="book-details.no-copies" /></label>
                        </div>
                    </c:if>
                    <div class="w-100 row justify-content-end">
                        <c:if test="${requestScope.copies_count > 0}">
                            <button class="h-50 col-3 btn submit"><fmt:message key="book-details.view" /></button>
                        </c:if>
                        <c:if test="${requestScope.copies_count == 0}">
                            <button class="h-50 col-3 btn submit" disabled><fmt:message key="book-details.view" /></button>
                        </c:if>
                    </div>
                </form>
            </div>
            <div class="form-container">
                <h3 class="title"><fmt:message key="book-details.issue" /></h3>
                <form class="form-horizontal" action="controller" method="post">
                    <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.ISSUE_BY_USER_ID_COMMAND}">
                    <input type="hidden" name="${RequestParameter.SUBJECT_ID}" value="${requestScope.subject.id}">
                    <c:if test="${requestScope.available_copies_count > 0}">
                        <div class="reservation-info">
                            <label><fmt:message key="book-details.available-copies" />&nbsp;${requestScope.available_copies_count}</label>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.available_copies_count == 0}">
                        <div class="reservation-info">
                            <i class="bi bi-exclamation-triangle-fill" style="color: gold;"></i>
                            <label><fmt:message key="book-details.no-available-copies" /></label>
                        </div>
                    </c:if>
                    <div class="form-group" style="width: 100%">
                        <label><fmt:message key="book-details.member-id" />
                            <input type="number" name="${RequestParameter.USER_ID}" value="" min="1" class="form-control" required placeholder="<fmt:message key="manage-users.id" />">
                        </label>
                    </div>
                    <c:if test="${sessionScope.error != null}">
                        <div class="w-100 row justify-content-left">
                            <label class="error-message">${sessionScope.error}</label>
                                ${sessionScope.remove("error")}
                        </div>
                    </c:if>
                    <div class="w-100 row justify-content-end">
                        <c:if test="${requestScope.available_copies_count > 0}">
                            <button class="h-50 col-3 btn submit"><fmt:message key="book-details.issue-book" /></button>
                        </c:if>
                        <c:if test="${requestScope.available_copies_count == 0}">
                            <button class="h-50 col-3 btn submit" disabled><fmt:message key="book-details.issue-book" /></button>
                        </c:if>
                    </div>
                </form>
            </div>
        </c:if>
    </div>
</div>
<jsp:include page="tamplate/footer.jsp" />
</body>
</html>
