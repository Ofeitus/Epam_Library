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
<div class="table-container">
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