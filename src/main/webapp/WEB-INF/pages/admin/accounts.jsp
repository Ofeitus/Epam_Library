<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.epam.ofeitus.library.controller.constant.RequestParameter" %>
<%@ page import="com.epam.ofeitus.library.controller.command.CommandName" %>
<%@ page import="com.epam.ofeitus.library.entity.user.constituent.UserRole" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="../tamplate/links.jsp" />
    <title><fmt:message key="header.manage-users" /></title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="table-container" style="margin-top: 50px">
    <div style="display: flex; justify-content: space-between">
        <h3>Зарегистрированные счета</h3>
    </div>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">Название</th>
            <th scope="col">Тип</th>
            <th scope="col">Номер</th>
            <th scope="col">Код</th>
            <th scope="col">Дебет/Кредит</th>
            <th scope="col">Баланс</th>
            <th scope="col">Тип клиента</th>
            <th scope="col">ФИО клиента</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.accounts}" var="account">
            <tr>
                <td>${account.id}</td>
                <td>${account.name}</td>
                <td>${account.type.getName()}</td>
                <td>${account.number}</td>
                <td>${account.code}</td>
                <td>${account.purpose.getName()}</td>
                <td>${account.balance}</td>
                <td>${account.clientType.getName()}</td>
                <td>${account.client.surname}${" "}${account.client.name}${" "}${account.client.patronymic}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pages-navigation">
        <c:if test="${requestScope.current_page != 1}">
            <a href="${sessionScope.url_without_page}&${RequestParameter.PAGE}=${requestScope.current_page - 1}"><i class="bi bi-arrow-left-circle-fill"></i></a>
        </c:if>
        &nbsp;<span><fmt:message key="catalog.page" /> ${requestScope.current_page} <fmt:message key="catalog.of" /> ${requestScope.pages_count}</span>&nbsp;
        <c:if test="${requestScope.current_page < requestScope.pages_count}">
            <a href="${sessionScope.url_without_page}&${RequestParameter.PAGE}=${requestScope.current_page + 1}"><i class="bi bi-arrow-right-circle-fill"></i></a>
        </c:if>
    </div>
</div>
<jsp:include page="../tamplate/footer.jsp" />
</body>
</html>
