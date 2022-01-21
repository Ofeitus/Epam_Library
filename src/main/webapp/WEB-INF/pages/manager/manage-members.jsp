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
    <title><fmt:message key="manage-members.manage-members" /></title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="manage-members-container">
    <h2><fmt:message key="manage-members.manage-members" /></h2>
    <div class="manage-members-forms">
        <div class="form-container" style="width: 60%">
            <h3 class="title"><fmt:message key="inventory-book.search-page" /></h3>
            <form class="form-horizontal" action="controller" method="get">
                <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.SEARCH_MEMBERS_COMMAND}">
                <div class="form-group">
                    <label><fmt:message key="manage-users.id" />
                        <input type="number" name="${RequestParameter.USER_ID}" value="0" min="0" class="form-control" required placeholder="<fmt:message key="manage-users.id" />">
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
                <td>
                    <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_PROFILE_PAGE_COMMAND}&${RequestParameter.USER_ID}=${user.userId}">
                        <i class="bi bi-person-circle" style="font-size: 18px"></i><fmt:message key="header.member-profile" />
                    </a>
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