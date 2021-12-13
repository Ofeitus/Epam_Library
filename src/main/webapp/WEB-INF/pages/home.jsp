<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="tamplate/links.jsp" />
    <title>Library</title>
</head>
<body>
<jsp:include page="tamplate/header.jsp" />
    <h2>This is the test home page</h2>
    <table class="table table-hover table-striped">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">name</th>
            <th scope="col" >surname</th>
            <th scope="col">email</th>
            <th scope="col" >role</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <th scope="col">${user.userId}</th>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.email}</td>
                <td>${user.userRole}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
