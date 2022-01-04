<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="../tamplate/links.jsp" />
    <title><fmt:message key="edit-book-data.edit-book-data" /></title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="container-fluid d-flex">
    <div class="w-100 row justify-content-md-center">
        <div class="col-md-5">
            <div class="form-container">
                <h3 class="title"><fmt:message key="add-new-book.add-new-book" /></h3>
                <form class="form-horizontal" action="controller" method="get">
                    <input type="hidden" name="command" value="write-in-copies-of-new-book">
                    <div class="form-group" style="width: 100%;">
                        <label><fmt:message key="edit-book-data.title" /></label>
                        <input type="text" name="title" value="" class="form-control"
                               placeholder="<fmt:message key="edit-book-data.title-placeholder" />" required>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="edit-book-data.isbn" /></label>
                        <input type="text" name="book-isbn" value="" class="form-control"
                               placeholder="<fmt:message key="edit-book-data.isbn-placeholder" />" required>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="edit-book-data.category" /></label>
                        <select class="form-control" name="category">
                            <c:forEach items="${requestScope.book_categories}" var="book_category">
                                <option value="${book_category.name}">${book_category.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="edit-book-data.publication-year" /></label>
                        <input type="number" name="publication-year" value="" class="form-control"
                               placeholder="<fmt:message key="edit-book-data.publication-year-placeholder" />" required>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="edit-book-data.language" /></label>
                        <input type="text" name="language" value="" class="form-control"
                               placeholder="<fmt:message key="edit-book-data.language-placeholder" />" required>
                    </div>
                    <div class="form-group" style="width: 100%">
                        <label><fmt:message key="edit-book-data.key-words" /></label>
                        <input type="text" name="key-words" value="" class="form-control"
                               placeholder="<fmt:message key="edit-book-data.key-words-placeholder" />" required>
                    </div>
                    <div id="authors" class="form-group" style="width: 100%">
                        <label><fmt:message key="edit-book-data.authors" /></label>
                    </div>
                    <i id="addAuthor" class="bi bi-plus" style="font-size: 32px;color: forestgreen"></i>
                    <div class="form-group" style="width: 100%">
                        <label><fmt:message key="add-new-book.copies-count" /></label>
                        <input type="number" name="copies-count" value="" class="form-control"
                               placeholder="<fmt:message key="add-new-book.copies-count-placeholder" />" required>
                    </div>
                    <div class="w-100 row justify-content-end">
                        <button class="h-50 col-4 btn submit"><fmt:message key="add-new-book.write-in" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>

<script>
    function addAuthor() {
        $('#authors').append(
            "<div class=\"author-data\">" +
            "<input type=\"text\" name=\"author-name\" class=\"form-control\" " +
            "placeholder=\"<fmt:message key="edit-book-data.name" />\" style=\"width: 42%\" required>"+
            "<input type=\"text\" name=\"author-surname\"  class=\"form-control\" " +
            "placeholder=\"<fmt:message key="edit-book-data.surname" />\" style=\"width: 42%\" required>" +
            "<i onclick=\"deleteAuthor(this)\" class=\"bi bi-dash\" style=\"font-size: 32px;color: firebrick\"></i>" +
            "</div>");
    }
    function deleteAuthor(e) {
        e.parentNode.parentNode.removeChild(e.parentNode);
    }
    document.getElementById('addAuthor').onclick = addAuthor;
</script>

</html>