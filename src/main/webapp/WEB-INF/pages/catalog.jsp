<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="tamplate/links.jsp" />
    <title>Books catalog</title>
</head>
<body>
<jsp:include page="tamplate/header.jsp" />
<div class="catalog-container">
    <h2>Catalog</h2>
    <div class="form-container">
        <h3 class="title">Search</h3>
        <form class="form-horizontal" action="controller" method="get">
            <input type="hidden" name="command" value="search-books">
            <div class="form-group">
                <label>ISBN/Title</label>
                <input type="text" name="isbn-title" value="" class="form-control" placeholder="isbn/title">
            </div>
            <div class="form-group">
                <label>Category</label>
                <input type="text" name="category" value="" class="form-control" placeholder="category">
            </div>
            <div class="form-group">
                <label>Author</label>
                <div class="input-group">
                    <input type="text" name="author-name" value="" class="form-control" placeholder="name">
                    <input type="text" name="author-surname" value="" class="form-control" placeholder="surname">
                </div>
            </div>
            <div class="form-group">
                <label>Publication year</label>
                <div class="input-group">
                    <input type="number" name="year-from" class="form-control" placeholder="from" required value="0">
                    <input type="number" name="year-to" class="form-control" placeholder="to" required value="0">
                </div>
            </div>
            <c:if test="${sessionScope.error != null}">
                <div class="w-100 row justify-content-left">
                    <label id="search-error-message">${sessionScope.error}</label>
                </div>
            </c:if>
            <div id="search-buttons" class="w-100 row justify-content-between">
                <button type="reset" class="h-50 col-2 btn reset">Clear</button>
                <button type="submit" class="h-50 col-2 btn submit">Search</button>
            </div>
        </form>
    </div>
    <c:if test="${requestScope.books != null}">
        <div class="books-container">
            <c:if test="${requestScope.books.size() == 0}">
                <h3>No search results</h3>
            </c:if>
            <c:forEach items="${requestScope.books}" var="book">
                <div class="book-item">
                    <img src="${pageContext.request.contextPath}/images/books/${book.isbn}.jpg" alt="">
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
</body>
</html>
