<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="../tamplate/links.jsp" />
    <title><fmt:message key="header.manage-users" /></title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="manage-members-container">
    <h2><fmt:message key="manage-users.manage-users" /></h2>
    <div class="manage-members-forms">
        <div class="form-container" style="width: 60%">
            <h3 class="title"><fmt:message key="inventory-book.search-page" /></h3>
            <form class="form-horizontal" action="controller" method="get">
                <input type="hidden" name="command" value="search-users">
                <div class="form-group" style="width: 100%">
                    <label><fmt:message key="manage-users.role" /></label>
                    <select class="form-control" name="user-role">
                        <option value="ALL" selected><fmt:message key="manage-users.all-users" /></option>
                        <option value="ADMIN"><fmt:message key="user-role.admin" /></option>
                        <option value="MANAGER"><fmt:message key="user-role.manager" /></option>
                        <option value="MEMBER"><fmt:message key="user-role.member" /></option>
                    </select>
                </div>
                <div class="form-group">
                    <label><fmt:message key="manage-users.id" /></label>
                    <input type="number" name="user-id" value="0" class="form-control" required placeholder="<fmt:message key="manage-users.id" />">
                </div>
                <div class="form-group">
                    <label><fmt:message key="manage-users.email" /></label>
                    <input type="text" name="email" value="" class="form-control" placeholder="<fmt:message key="manage-users.email-placeholder" />">
                </div>
                <div class="w-100 row justify-content-between search-buttons">
                    <button type="reset" class="h-50 col-3 btn reset"><fmt:message key="inventory-book.clear" /></button>
                    <button type="submit" class="h-50 col-2 btn submit"><fmt:message key="inventory-book.search" /></button>
                </div>
            </form>
        </div>
        <img style="align-self: center; height: 250px" src="${pageContext.request.contextPath}/images/manager-pusheen.png" alt="">
    </div>
</div>
<div class="table-container" style="margin-top: 0">
    <h3><fmt:message key="manage-users.registered-users" /></h3>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="manage-users.id" /></th>
            <th scope="col"><fmt:message key="manage-users.name" /></th>
            <th scope="col"><fmt:message key="manage-users.surname" /></th>
            <th scope="col"><fmt:message key="manage-users.phone-number" /></th>
            <th scope="col"><fmt:message key="manage-users.email" /></th>
            <th scope="col"><fmt:message key="manage-users.role" /></th>
            <th scope="col"><fmt:message key="manage-users.deletion" /></th>
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
                <td>
                    <c:choose>
                        <c:when test="${user.userRole == 'ADMIN'}">
                            <i class="bi bi-circle-fill" style="color:firebrick"></i>
                        </c:when>
                        <c:when test="${user.userRole == 'MANAGER'}">
                            <i class="bi bi-circle-fill" style="color:royalblue"></i>
                        </c:when>
                        <c:when test="${user.userRole == 'MEMBER'}">
                            <i class="bi bi-circle-fill" style="color:gray"></i>
                        </c:when>
                    </c:choose>
                    <c:if test="${user.userId != sessionScope.user_id}">
                        <select onchange="window.location.href=this.value;">
                            <option data-content="<i class='fa fa-circle red'></i>" <c:if test="${user.userRole == 'ADMIN'}">selected</c:if>
                                    value="?command=set-role&user-id=${user.userId}&role-id=1">
                                <fmt:message key="user-role.admin" />
                            </option>
                            <option data-content="<i class='fa fa-circle red'></i>" <c:if test="${user.userRole == 'MANAGER'}">selected</c:if>
                                    value="?command=set-role&user-id=${user.userId}&role-id=2">
                                <fmt:message key="user-role.manager" />
                            </option>
                            <option data-content="<i class='fa fa-circle red'></i>" <c:if test="${user.userRole == 'MEMBER'}">selected</c:if>
                                    value="?command=set-role&user-id=${user.userId}&role-id=3">
                                <fmt:message key="user-role.member" />
                            </option>
                        </select>
                    </c:if>
                    <c:if test="${user.userId == sessionScope.user_id}">
                        <c:choose>
                            <c:when test="${user.userRole == 'ADMIN'}">
                                <fmt:message key="user-role.admin" />
                            </c:when>
                            <c:when test="${user.userRole == 'MANAGER'}">
                                <fmt:message key="user-role.manager" />
                            </c:when>
                            <c:when test="${user.userRole == 'MEMBER'}">
                                <fmt:message key="user-role.member" />
                            </c:when>
                        </c:choose>
                    </c:if>
                </td>
                <td style="text-align: center">
                    <c:if test="${!user.deleted}">
                        <a href="?command=delete-user&user-id=${user.userId}&page=${requestScope.current_page}">
                            <i class="bi bi-trash-fill" style="font-size: 20px;color: firebrick"></i>
                        </a>
                    </c:if>
                    <c:if test="${user.deleted}">
                        <i class="bi bi-arrow-clockwise" style="font-size: 20px;color: royalblue"></i>
                        <a href="?command=restore-user&user-id=${user.userId}&page=${requestScope.current_page}">
                            <fmt:message key="manage-users.restore" />
                        </a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="pages-navigation">
        <c:if test="${requestScope.current_page != 1}">
            <a href="${sessionScope.url_without_page}${"&page="}${requestScope.current_page - 1}"><i class="bi bi-arrow-left-circle-fill"></i></a>
        </c:if>
        &nbsp;<span><fmt:message key="catalog.page" /> ${requestScope.current_page} <fmt:message key="catalog.of" /> ${requestScope.pages_count}</span>&nbsp;
        <c:if test="${requestScope.current_page < requestScope.pages_count}">
            <a href="${sessionScope.url_without_page}${"&page="}${requestScope.current_page + 1}"><i class="bi bi-arrow-right-circle-fill"></i></a>
        </c:if>
    </div>
</div>
<jsp:include page="../tamplate/footer.jsp" />
</body>
</html>
