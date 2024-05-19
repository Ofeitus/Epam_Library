<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.epam.ofeitus.library.controller.constant.RequestParameter" %>
<%@ page import="com.epam.ofeitus.library.controller.command.CommandName" %>
<%@ page import="com.epam.ofeitus.library.service.validator.ValidationPattern" %>

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
                <h3 class="title"><fmt:message key="edit-book-data.edit-book-data" /></h3>
                <form class="form-horizontal" action="controller" method="post">
                    <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.EDIT_SUBJECT_COMMAND}">
                    <input type="hidden" name="${RequestParameter.SUBJECT_ID}" value="${requestScope.subject.id}">
                    <div class="form-group" style="width: 100%;">
                        <label><fmt:message key="edit-book-data.title" />
                            <input type="text" class="form-control"
                                   name="${RequestParameter.TITLE}" value="${requestScope.book.title}" minlength="1" maxlength="100" placeholder="<fmt:message key="edit-book-data.title-placeholder" />" required>
                        </label>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="edit-book-data.isbn" />
                            <input type="text" class="form-control" pattern="${ValidationPattern.ISBN_PATTERN}" title="<fmt:message key="validation-pattern.isbn" />"
                                   name="${RequestParameter.SUBJECT_ID}" value="${requestScope.subject.id}" placeholder="<fmt:message key="edit-book-data.isbn-placeholder" />" required disabled>
                        </label>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="edit-book-data.category" />
                            <select class="form-control" name="${RequestParameter.CATEGORY}">
                                <c:forEach items="${requestScope.book_categories}" var="book_category">
                                    <c:if test="${book_category.name == requestScope.book.category}">
                                        <option value="${book_category.name}" selected>${book_category.name}</option>
                                    </c:if>
                                    <c:if test="${book_category.name != requestScope.book.category}">
                                        <option value="${book_category.name}">${book_category.name}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </label>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="edit-book-data.publication-year" />
                            <input type="number" class="form-control"
                                   name="${RequestParameter.HOURS}" value="${requestScope.book.publicationYear}" min="0" placeholder="<fmt:message key="edit-book-data.publication-year-placeholder" />" required>
                        </label>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="edit-book-data.language" />
                            <input type="text" class="form-control" pattern="${ValidationPattern.NAME_PATTERN}" title="<fmt:message key="validation-pattern.language" />"
                                   name="${RequestParameter.SUBJECT_NAME}" value="${requestScope.book.language}" placeholder="<fmt:message key="edit-book-data.language-placeholder" />" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 100%">
                        <label><fmt:message key="edit-book-data.key-words" />
                            <input type="text" name="${RequestParameter.KEY_WORDS}" value="${requestScope.book.keyWords}" class="form-control"
                                   placeholder="<fmt:message key="edit-book-data.key-words-placeholder" />" required>
                        </label>
                    </div>
                    <div id="authors" class="form-group" style="width: 100%">
                        <label><fmt:message key="edit-book-data.authors" />
                            <c:forEach var="author" items="${requestScope.book.authors}">
                                <div class="author-data">
                                    <input type="text" class="form-control" pattern="${ValidationPattern.NAME_PATTERN}" title="<fmt:message key="validation-pattern.name" />"
                                           name="${RequestParameter.AUTHOR_NAME}" value="${author.name}" placeholder="<fmt:message key="edit-book-data.name" />" style="width: 42%" required>
                                    <input type="text" class="form-control" pattern="${ValidationPattern.NAME_PATTERN}" title="<fmt:message key="validation-pattern.name" />"
                                           name="${RequestParameter.AUTHOR_SURNAME}" value="${author.surname}" placeholder="<fmt:message key="edit-book-data.surname" />" style="width: 42%" required>
                                    <i onclick="deleteAuthor(this)" class="bi bi-dash" style="font-size: 32px;color: firebrick"></i>
                                </div>
                            </c:forEach>
                        </label>
                    </div>
                    <c:if test="${sessionScope.error != null}">
                        <div class="w-100 row justify-content-left">
                            <label class="error-message">${sessionScope.error}</label>
                                ${sessionScope.remove("error")}
                        </div>
                    </c:if>
                    <i id="addAuthor" class="bi bi-plus" style="font-size: 32px;color: forestgreen"></i>
                    <div class="w-100 row justify-content-end">
                        <button class="h-50 col-3 btn submit"><fmt:message key="edit-book-data.save-changes" /></button>
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
            "<input type=\"text\" class=\"form-control\" pattern=\"${ValidationPattern.NAME_PATTERN}\" title=\"<fmt:message key="validation-pattern.name" />\" " +
            "name=\"${RequestParameter.AUTHOR_NAME}\" placeholder=\"<fmt:message key="edit-book-data.name" />\" style=\"width: 42%\" required>"+
            "<input type=\"text\" class=\"form-control\" pattern=\"${ValidationPattern.NAME_PATTERN}\" title=\"<fmt:message key="validation-pattern.name" />\" " +
            "name=\"${RequestParameter.AUTHOR_SURNAME}\" placeholder=\"<fmt:message key="edit-book-data.surname" />\" style=\"width: 42%\" required>" +
            "<i onclick=\"deleteAuthor(this)\" class=\"bi bi-dash\" style=\"font-size: 32px;color: firebrick\"></i>" +
            "</div>");
    }
    function deleteAuthor(e) {
        e.parentNode.parentNode.removeChild(e.parentNode);
    }
    document.getElementById('addAuthor').onclick = addAuthor;
</script>

</html>
