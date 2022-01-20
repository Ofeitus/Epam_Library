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
    <title><fmt:message key="edit-book-data.edit-book-data" /></title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="container-fluid d-flex">
    <div class="w-100 row justify-content-md-center">
        <div class="col-md-5">
            <div class="form-container">
                <h3 class="title"><fmt:message key="add-new-book.add-new-book" /></h3>
                <form class="form-horizontal" action="controller" method="post">
                    <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.WRITE_IN_COPIES_OF_NEW_BOOK_COMMAND}">
                    <div class="form-group" style="width: 100%;">
                        <label><fmt:message key="edit-book-data.title" />
                            <input type="text" name="${RequestParameter.TITLE}" value="" class="form-control"
                                   placeholder="<fmt:message key="edit-book-data.title-placeholder" />" required>
                        </label>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="edit-book-data.isbn" />
                            <input type="text" name="${RequestParameter.BOOK_ISBN}" value="" class="form-control"
                                   placeholder="<fmt:message key="edit-book-data.isbn-placeholder" />" required>
                        </label>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="edit-book-data.category" />
                            <select class="form-control" name="${RequestParameter.CATEGORY}">
                                <c:forEach items="${requestScope.book_categories}" var="book_category">
                                    <option value="${book_category.name}">${book_category.name}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="edit-book-data.publication-year" />
                            <input type="number" name="${RequestParameter.PUBLICATION_YEAR}" value="" class="form-control"
                                   placeholder="<fmt:message key="edit-book-data.publication-year-placeholder" />" required>
                        </label>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="edit-book-data.language" />
                            <input type="text" name="${RequestParameter.LANGUAGE}" value="" class="form-control"
                                   placeholder="<fmt:message key="edit-book-data.language-placeholder" />" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 100%">
                        <label><fmt:message key="edit-book-data.key-words" />
                            <input type="text" name="${RequestParameter.KEY_WORDS}" value="" class="form-control"
                                   placeholder="<fmt:message key="edit-book-data.key-words-placeholder" />" required>
                        </label>
                    </div>
                    <div id="authors" class="form-group" style="width: 100%">
                        <label><fmt:message key="edit-book-data.authors" /></label>
                    </div>
                    <p id="addAuthor" class="bi bi-plus" style="font-size: 32px;color: forestgreen"></p>
                    <div class="form-group">
                        <label><fmt:message key="inventory-book.price" />
                            <input id="price" type="number" name="${RequestParameter.PRICE}" value="0.00" step="0.01" class="form-control" required placeholder="<fmt:message key="inventory-book.price-placeholder" />">
                        </label>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="add-new-book.copies-count" />
                            <input type="number" name="${RequestParameter.COPIES_COUNT}" value="" class="form-control"
                                   placeholder="<fmt:message key="add-new-book.copies-count-placeholder" />" required>
                        </label>
                    </div>
                    <div class="w-100 row justify-content-end">
                        <button class="h-50 col-3 btn submit"><fmt:message key="add-new-book.write-in" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../tamplate/footer.jsp" />
</body>

<script>
    function addAuthor() {
        $('#authors').append(
            "<div class=\"author-data\">" +
            "<input type=\"text\" name=\"${RequestParameter.AUTHOR_NAME}\" class=\"form-control\" " +
            "placeholder=\"<fmt:message key="edit-book-data.name" />\" style=\"width: 42%\" required>"+
            "<input type=\"text\" name=\"${RequestParameter.AUTHOR_SURNAME}\"  class=\"form-control\" " +
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