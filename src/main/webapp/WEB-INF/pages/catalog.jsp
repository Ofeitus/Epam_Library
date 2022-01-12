<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="tamplate/links.jsp" />
    <title><fmt:message key="catalog.catalog" /></title>
</head>
<body>
<jsp:include page="tamplate/header.jsp" />
<style>
    #catalog-page {
        border-bottom: 3px solid #5f5148;
    }
</style>
<div class="catalog-container">
    <h2><fmt:message key="catalog.catalog" /></h2>
    <div class="form-container">
        <h3 class="title"><fmt:message key="catalog.search-page" /></h3>
        <form class="form-horizontal" action="controller" method="get">
            <input type="hidden" name="command" value="search-books">
            <div class="form-group">
                <label><fmt:message key="catalog.search-request" /></label>
                <input type="text" name="search-request" value="" class="form-control" placeholder="<fmt:message key="catalog.search-request-placeholder" />">
            </div>
            <div class="form-group">
                <label><fmt:message key="catalog.category" /></label>
                <select class="form-control" name="category">
                    <c:forEach items="${requestScope.book_categories}" var="book_category">
                        <option value="${book_category.name}">${book_category.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group">
                <label><fmt:message key="catalog.author" /></label>
                <div class="input-group">
                    <input type="text" name="author-name" value="" class="form-control" placeholder="<fmt:message key="catalog.author-name-placeholder" />">
                    <input type="text" name="author-surname" value="" class="form-control" placeholder="<fmt:message key="catalog.author-surname-placeholder" />">
                </div>
            </div>
            <div class="form-group">
                <label><fmt:message key="catalog.publication-year" /></label>
                <div class="input-group">
                    <input type="number" name="year-from" class="form-control" placeholder="<fmt:message key="catalog.year-from-placeholder" />" required value="0">
                    <input type="number" name="year-to" class="form-control" placeholder="<fmt:message key="catalog.year-to-placeholder" />" required value="0">
                </div>
            </div>
            <c:if test="${sessionScope.error != null}">
                <div class="w-100 row justify-content-left">
                    <label id="search-error-message">${sessionScope.error}</label>
                </div>
            </c:if>
            <div class="w-100 row justify-content-between search-buttons">
                <button type="reset" class="h-50 col-2 btn reset"><fmt:message key="catalog.clear" /></button>
                <button type="submit" class="h-50 col-2 btn submit"><fmt:message key="catalog.search" /></button>
            </div>
        </form>
    </div>
    <c:if test="${requestScope.books != null}">
        <div class="books-container">
            <c:if test="${requestScope.books.size() == 0}">
                <h3><fmt:message key="catalog.no-search-results" /></h3>
            </c:if>
            <c:forEach items="${requestScope.books}" var="book">
                <div class="book-item">
                    <a class="book-link" href="?command=goto-book-details-page&book-isbn=${book.isbn}"></a>
                    <img onerror="$(this).attr('src', '${pageContext.request.contextPath}/images/book-placeholder.png');" src="${pageContext.request.contextPath}/images/books/${book.isbn}.jpg" alt="">
                    <a class="book-title">${book.title}</a>
                    <p class="book-authors">
                        <c:forEach items="${book.authors}" var="author">
                            ${author.name}&nbsp;${author.surname},&nbsp;
                        </c:forEach>
                        ${book.publicationYear}
                    </p>
                </div>
            </c:forEach>
        </div>
    </c:if>
</div>
<jsp:include page="tamplate/footer.jsp" />
</body>
</html>
