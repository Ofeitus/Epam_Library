<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="../tamplate/links.jsp" />
    <title><fmt:message key="inventory-book.inventory-book" /></title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="catalog-container">
    <h2><fmt:message key="inventory-book.inventory-book" /></h2>
    <div class="form-container">
        <h3 class="title"><fmt:message key="inventory-book.search-page" /></h3>
        <form class="form-horizontal" action="controller" method="get">
            <input type="hidden" name="command" value="search-copies-of-books">
            <div class="form-group">
                <label><fmt:message key="inventory-book.book-isbn" /></label>
                <input type="text" name="book-isbn" value="" class="form-control" placeholder="<fmt:message key="inventory-book.book-isbn-placeholder" />">
            </div>
            <div class="form-group">
                <label><fmt:message key="inventory-book.inventory-id" /></label>
                <input type="number" name="inventory-id" value="0" class="form-control" placeholder="<fmt:message key="inventory-book.inventory-id-placeholder" />">
            </div>
            <c:if test="${sessionScope.error != null}">
                <div class="w-100 row justify-content-left">
                    <label id="search-error-message">${sessionScope.error}</label>
                </div>
            </c:if>
            <div id="search-buttons" class="w-100 row justify-content-between">
                <button type="reset" class="h-50 col-2 btn reset"><fmt:message key="inventory-book.clear" /></button>
                <button type="submit" class="h-50 col-2 btn submit"><fmt:message key="inventory-book.search" /></button>
            </div>
        </form>
    </div>
</div>
<div class="table-container" style="margin-top: 0">
    <c:if test="${requestScope.books.size() == 0}">
        <h3><fmt:message key="inventory-book.no-search-results" /></h3>
    </c:if>
    <table class="table table-bordered">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="inventory-book.receipt-date" /></th>
            <th scope="col"><fmt:message key="inventory-book.inventory-id" /></th>
            <th scope="col"><fmt:message key="inventory-book.book-title" /></th>
            <th scope="col"><fmt:message key="inventory-book.book-isbn" /></th>
            <th scope="col"><fmt:message key="inventory-book.publication-year" /></th>
            <th scope="col"><fmt:message key="inventory-book.status" /></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.copies_of_books}" var="copy_of_book">
            <tr>
                <td>${copy_of_book.receiptDate}</td>
                <td>${copy_of_book.inventoryId}</td>
                <td>${copy_of_book.book.title}</td>
                <td>${copy_of_book.book.isbn}</td>
                <td>${copy_of_book.book.publicationYear}</td>
                <td><i class="bi bi-circle-fill"
                    <c:choose>
                        <c:when test="${copy_of_book.copyOfBookStatus == 'AVAILABLE'}">
                            style="color:gray"
                        </c:when>
                        <c:when test="${copy_of_book.copyOfBookStatus == 'READING_ROOM'}">
                            style="color:lawngreen"
                        </c:when>
                        <c:when test="${copy_of_book.copyOfBookStatus == 'RESERVED'}">
                            style="color:royalblue"
                        </c:when>
                        <c:when test="${copy_of_book.copyOfBookStatus == 'LOANED'}">
                            style="color:forestgreen"
                        </c:when>
                    </c:choose>
                ></i>${copy_of_book.copyOfBookStatus}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
