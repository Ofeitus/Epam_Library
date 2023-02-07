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
<div class="manage-members-container">
    <h2><fmt:message key="header.manage-users" /></h2>
    <div class="manage-members-forms">
        <div class="form-container" style="width: 60%">
            <h3 class="title"><fmt:message key="inventory-book.search-page" /></h3>
            <form class="form-horizontal" action="controller" method="get">
                <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.SEARCH_MEMBERS_COMMAND}">
                <div class="form-group" style="width: 100%">
                    <label><fmt:message key="manage-users.role" />
                        <select class="form-control" name="${RequestParameter.USER_ROLE_ID}">
                            <option value="0" selected><fmt:message key="manage-users.all-users" /></option>
                            <option value="1"><fmt:message key="user-role.admin" /></option>
                            <option value="2"><fmt:message key="user-role.manager" /></option>
                            <option value="3"><fmt:message key="user-role.member" /></option>
                        </select>
                    </label>
                </div>
                <div class="form-group">
                    <label><fmt:message key="manage-users.id" />
                        <input type="number" name="${RequestParameter.USER_ID}" value="0" class="form-control" required placeholder="<fmt:message key="manage-users.id" />">
                    </label>
                </div>
                <div class="form-group">
                    <label><fmt:message key="manage-users.email" />
                        <input type="email" name="${RequestParameter.EMAIL}" value="" class="form-control" placeholder="<fmt:message key="manage-users.email-placeholder" />">
                    </label>
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
    <div style="display: flex; justify-content: space-between">
        <h3><fmt:message key="manage-users.registered-users" /></h3>
        <form class="lab-piris" action="controller" method="post">
            <input type="hidden" name="${RequestParameter.COMMAND}" value="goto-add-client-page">
            <button type="submit" class="link-h-50 col-3 btn submit">Add client</button>
        </form>
    </div>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="manage-users.id" /></th>
            <th scope="col"><fmt:message key="manage-users.name" /></th>
            <th scope="col"><fmt:message key="manage-users.surname" /></th>
            <th scope="col"><fmt:message key="manage-users.phone-number" /></th>
            <th scope="col"><fmt:message key="manage-users.email" /></th>
            <th scope="col"><fmt:message key="manage-users.role" /></th>
            <th scope="col">Edit</th>
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
                    <c:if test="${user.userRole != UserRole.ADMIN}">
                        <label>
                            <form action="controller" method="post">
                                <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.SET_ROLE_COMMAND}">
                                <input type="hidden" name="${RequestParameter.USER_ID}" value="${user.userId}">
                                <select onchange="this.form.submit()" name="${RequestParameter.USER_ROLE_ID}">
                                    <option data-content="<i class='fa fa-circle red'></i>" <c:if test="${user.userRole == 'MANAGER'}">selected</c:if> value="2">
                                        <fmt:message key="user-role.manager" />
                                    </option>
                                    <option data-content="<i class='fa fa-circle red'></i>" <c:if test="${user.userRole == 'MEMBER'}">selected</c:if> value="3">
                                        <fmt:message key="user-role.member" />
                                    </option>
                                </select>
                            </form>
                        </label>
                    </c:if>
                    <c:if test="${user.userRole == UserRole.ADMIN}">
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
                    <form action="controller" method="post">
                        <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.GOTO_EDIT_CLIENT_PAGE_COMMAND}">
                        <input type="hidden" name="${RequestParameter.USER_ID}" value="${user.userId}">
                        <button type="submit" class="link-button"><i class="bi bi-pencil-fill" style="font-size: 20px;color: royalblue"></i></button>
                    </form>
                </td>
                <td style="text-align: center">
                    <c:if test="${!user.deleted and user.userId != sessionScope.user_id}">
                        <form action="controller" method="post">
                            <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.DELETE_USER_COMMAND}">
                            <input type="hidden" name="${RequestParameter.USER_ID}" value="${user.userId}">
                            <button type="submit" class="link-button"><i class="bi bi-trash-fill" style="font-size: 20px;color: firebrick"></i></button>
                        </form>
                    </c:if>
                    <c:if test="${user.deleted and user.userId != sessionScope.user_id}">
                        <i class="bi bi-arrow-clockwise" style="font-size: 20px;color: royalblue"></i>
                        <a href="?${RequestParameter.COMMAND}=${CommandName.RESTORE_USER_COMMAND}&${RequestParameter.USER_ID}=${user.userId}">
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
