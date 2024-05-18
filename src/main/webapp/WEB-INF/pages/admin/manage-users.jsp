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
        <h3>Зарегистрированные клиенты</h3>
        <form class="lab-piris" action="controller" method="post">
            <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.GOTO_ADD_CLIENT_PAGE}">
            <button type="submit" class="link-h-50 col-4 btn submit">Добавить клиента</button>
        </form>
    </div>
    <table class="table table-bordered table-striped">
        <thead>
        <tr>
            <th scope="col"><fmt:message key="manage-users.id" /></th>
            <th scope="col"><fmt:message key="manage-users.name" /></th>
            <th scope="col"><fmt:message key="manage-users.surname" /></th>
            <th scope="col">Дата рождения</th>
            <!--th scope="col"><fmt:message key="manage-users.phone-number" /></th-->
            <th scope="col"><fmt:message key="manage-users.email" /></th>
            <!--th scope="col"><fmt:message key="manage-users.role" /></th-->
            <th scope="col">Редактировать</th>
            <th scope="col"><fmt:message key="manage-users.deletion" /></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.users}" var="user">
            <tr>
                <td>${user.userId}</td>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.dateOfBirth}</td>
                <td>${user.email}</td>
                <!--
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
                -->
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
<script>
    <c:if test="${sessionScope.error != null}">
        Toastify({
            text: "${sessionScope.error}",
            duration: 5000,
            newWindow: true,
            close: true,
            gravity: "bottom", // `top` or `bottom`
            position: "center", // `left`, `center` or `right`
            stopOnFocus: true, // Prevents dismissing of toast on hover
            style: {
                background: "#ff4545",
            },
            onClick: function(){} // Callback after click
        }).showToast();
        ${sessionScope.remove("error")}
    </c:if>
</script>
</html>
