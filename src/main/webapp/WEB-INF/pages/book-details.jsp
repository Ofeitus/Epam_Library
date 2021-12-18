<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="tamplate/links.jsp" />
    <title>Book details</title>
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
        <img src="${pageContext.request.contextPath}/images/books/${requestScope.book.isbn}.jpg" alt="">
    </div>
    <div class="book-data">
        <h3 class="book-title">${requestScope.book.title}</h3>
        <table>
            <tr>
                <td class="book-field-name">ISBN</td>&nbsp;
                <td class="book-field-value">${requestScope.book.isbn}</td>
            </tr>
            <tr>
                <td class="book-field-name">Authors</td>
                <td class="book-field-value">
                    <c:forEach items="${requestScope.book.authors}" var="author">
                        ${author.name}&nbsp;${author.surname},&nbsp;
                    </c:forEach>
                </td>
            </tr>
            <tr>
                <td class="book-field-name">Publication year</td>
                <td class="book-field-value">${requestScope.book.publicationYear}</td>
            </tr>
            <tr>
                <td class="book-field-name">Language</td>
                <td class="book-field-value">${requestScope.book.language}</td>
            </tr>
            <tr>
                <td class="book-field-name">Key words</td>
                <td class="book-field-value">${requestScope.book.keyWords}</td>
            </tr>
        </table>
    </div>
</div>
</body>
</html>
