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
    <title>Дисциплина</title>
</head>

<body>
<jsp:include page="tamplate/header.jsp" />
<style>
    #catalog-page {
        border-bottom: 3px solid #5f5148;
    }
</style>
<div class="details-container">
    <div class="subject-data">
        <div style="display: flex;justify-content: space-between">
            <h3 class="subject-title">${requestScope.subject.name}</h3>
            <div class="edit-subject-data-links">
                <a style="padding-top: 10px; display: flex; align-items: center;" href="?${RequestParameter.COMMAND}=${CommandName.GOTO_EDIT_SUBJECT_PAGE_COMMAND}&${RequestParameter.SUBJECT_ID}=${requestScope.subject.id}"><i class="bi bi-pencil-square" style="font-size: 30px;"></i>Редактировать</a>
                <form action="controller" method="post" style="margin-top: 15px; margin-left: 10px;">
                    <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.DELETE_SUBJECT_COMMAND}">
                    <input type="hidden" name="${RequestParameter.SUBJECT_ID}" value="${requestScope.subject.id}">
                    <button type="submit" class="link-button"><i class="bi bi-trash-fill" style="color: firebrick; font-size: 30px;"></i>Удалить</button>
                </form>
            </div>
        </div>
        <table>
            <tr>
                <td class="subject-field-name">id</td>&nbsp;
                <td class="subject-field-value">${requestScope.subject.id}</td>
            </tr>
            <tr>
                <td class="subject-field-name">Кол-во часов</td>&nbsp;
                <td class="subject-field-value">${requestScope.subject.hours}</td>
            </tr>
        </table>
    </div>
</div>
<jsp:include page="tamplate/footer.jsp" />
</body>
</html>
