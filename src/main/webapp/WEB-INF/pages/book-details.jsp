<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
        <img onerror="$(this).attr('src', '${pageContext.request.contextPath}/images/book-placeholder.png');" src="${pageContext.request.contextPath}/images/books/${requestScope.book.isbn}.jpg" alt="">
    </div>
    <div class="book-data">
        <div style="display: flex">
            <h3 class="book-title">${requestScope.book.title}</h3>
            <c:if test="${sessionScope.user_role == 'MANAGER'}">
                <a class="edit-data-link" style="padding-top: 5px" href="?command=goto-edit-book-data-page&book-isbn=${requestScope.book.isbn}"><i class="bi bi-pencil-square"></i></a>
            </c:if>
        </div>
        <table>
            <tr>
                <td class="book-field-name"><fmt:message key="book-details.ISBN" /></td>&nbsp;
                <td class="book-field-value">${requestScope.book.isbn}</td>
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
        <c:if test="${sessionScope.user_role == null || sessionScope.user_role == 'MEMBER'}">
            <div class="form-container">
                <h3 class="title"><fmt:message key="book-details.reservation" /></h3>
                <form class="form-horizontal" action="controller" method="get">
                    <input type="hidden" name="command" value="reserve-book">
                    <input type="hidden" name="book-isbn" value="${requestScope.book.isbn}">
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
                    <c:if test="${requestScope.reserved_books_count + requestScope.issued_books_count >= 5}">
                        <div class="reservation-info">
                            <i class="bi bi-exclamation-triangle-fill" style="color: gold;"></i>
                            <label><fmt:message key="book-details.reservation-limit" /></label>
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.user_id == null}">
                        <div class="reservation-info">
                            <i class="bi bi-exclamation-circle-fill" style="color: royalblue;"></i>
                            <label><a href="?command=goto-log-in-page"><fmt:message key="book-details.log-in-" /></a><fmt:message key="book-details.-to-reserve" /></label>
                        </div>
                    </c:if>
                    <div class="w-100 row justify-content-end">
                        <c:if test="${sessionScope.user_id != null and
                                requestScope.reserved_books_count + requestScope.issued_books_count < 5 and
                                requestScope.available_copies_count > 0}">
                            <button class="h-50 col-4 btn submit"><fmt:message key="book-details.reserve" /></button>
                        </c:if>
                        <c:if test="${sessionScope.user_id == null or
                                requestScope.reserved_books_count + requestScope.issued_books_count >= 5 or
                                requestScope.available_copies_count == 0}">
                            <button class="h-50 col-4 btn submit" disabled><fmt:message key="book-details.reserve" /></button>
                        </c:if>
                    </div>
                </form>
            </div>
        </c:if>
        <c:if test="${sessionScope.user_role == 'MANAGER'}">
            <div class="form-container">
                <h3 class="title"><fmt:message key="book-details.copies" /></h3>
                <form class="form-horizontal" action="controller" method="get">
                    <input type="hidden" name="command" value="search-copies-of-books">
                    <input type="hidden" name="book-isbn" value="${requestScope.book.isbn}">
                    <input type="hidden" name="inventory-id" value="0">
                    <input type="hidden" name="status" value="only-existing">
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
                            <button class="h-50 col-4 btn submit"><fmt:message key="book-details.view" /></button>
                        </c:if>
                        <c:if test="${requestScope.copies_count == 0}">
                            <button class="h-50 col-4 btn submit" disabled><fmt:message key="book-details.view" /></button>
                        </c:if>
                    </div>
                </form>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
