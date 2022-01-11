<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="../tamplate/links.jsp" />
    <title><fmt:message key="manage-members.manage-members" /></title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="manage-members-container">
    <h2><fmt:message key="manage-members.manage-members" /></h2>
    <div class="form-container">
        <h3 class="title"><fmt:message key="inventory-book.search-page" /></h3>
        <form class="form-horizontal" action="controller" method="get">
            <input type="hidden" name="command" value="search-members">
            <div class="form-group">
                <label><fmt:message key="manage-users.id" /></label>
                <input type="number" name="user-id" value="0" class="form-control" required placeholder="<fmt:message key="manage-users.id" />">
            </div>
            <div class="form-group">
                <label><fmt:message key="manage-users.email" /></label>
                <input type="text" name="email" value="" class="form-control" placeholder="<fmt:message key="manage-users.email-placeholder" />">
            </div>
            <div class="w-100 row justify-content-between search-buttons">
                <button type="reset" class="h-50 col-2 btn reset"><fmt:message key="inventory-book.clear" /></button>
                <button type="submit" class="h-50 col-2 btn submit"><fmt:message key="inventory-book.search" /></button>
            </div>
        </form>
    </div>
</div>
<div class="table-container" style="margin-top: 0">
    <h3><fmt:message key="manage-members.members" /></h3>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="manage-users.id" /></th>
            <th scope="col"><fmt:message key="manage-users.name" /></th>
            <th scope="col"><fmt:message key="manage-users.surname" /></th>
            <th scope="col"><fmt:message key="manage-users.phone-number" /></th>
            <th scope="col"><fmt:message key="manage-users.email" /></th>
            <th scope="col"><fmt:message key="manage-members.profile" /></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td>${user.userId}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.phoneNumber}</td>
                <td>${user.email}</td>
                <td><a href="?command=goto-profile-page&user-id=${user.userId}">
                    <i class="bi bi-person-circle" style="font-size: 18px"></i><fmt:message key="header.member-profile" /></a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>