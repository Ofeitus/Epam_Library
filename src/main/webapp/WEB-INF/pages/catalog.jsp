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
    <h2>Books</h2>
    <form class="search-form" action="controller" method="get">
        <input type="hidden" name="command" value="search-book">
        <div class="search-field">
            <input placeholder="isbn, title, category" name="search" value="" required>
        </div>
        <input class="search-button" type="submit" value='⌕'>
    </form>
    <div class="books-container">
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
</div>
</body>
</html>
