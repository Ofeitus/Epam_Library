<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.epam.ofeitus.library.controller.constant.RequestParameter" %>
<%@ page import="com.epam.ofeitus.library.controller.command.CommandName" %>

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
<div class="table-container" style="margin-top: 2rem">
    <div style="display: flex; justify-content: space-between">
        <h3>Дисциплины</h3>
        <a style="padding-top: 5px; display: flex; align-items: center;" href="?${RequestParameter.COMMAND}=${CommandName.GOTO_ADD_NEW_SUBJECT_PAGE_COMMAND}"><i class="bi bi-plus" style="font-size: 40px;"></i>Добавить</a>
    </div>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">Имя</th>
            <th scope="col">Кол-во часов</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.subjects}" var="subject">
            <tr>
                <td>${subject.id}</td>
                <td>
                    <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_SUBJECT_DETAILS_PAGE_COMMAND}&${RequestParameter.SUBJECT_ID}=${subject.id}">
                        ${subject.name}
                    </a>
                </td>
                <td>${subject.hours}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<jsp:include page="tamplate/footer.jsp" />
</body>
</html>
