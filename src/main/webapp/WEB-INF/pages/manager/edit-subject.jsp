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
    <title>Редактирование дисциплины</title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="container-fluid d-flex">
    <div class="w-100 row justify-content-md-center">
        <div class="col-md-5">
            <div class="form-container">
                <h3 class="title">Редактирование дисциплины</h3>
                <form class="form-horizontal" action="controller" method="post">
                    <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.EDIT_SUBJECT_COMMAND}">
                    <input type="hidden" name="${RequestParameter.SUBJECT_ID}" value="${requestScope.subject.id}">
                    <div class="form-group" style="width: 100%;">
                        <label>Имя
                            <input type="text" class="form-control"
                                   name="${RequestParameter.SUBJECT_NAME}" value="${requestScope.subject.name}" minlength="1" maxlength="100" placeholder="Имя" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 100%;">
                        <label>Кол-во часов
                            <input type="number" class="form-control"
                                   name="${RequestParameter.HOURS}" value="${requestScope.subject.hours}" min="0" placeholder="Кол-во часов" required>
                        </label>
                    </div>
                    <c:if test="${sessionScope.error != null}">
                        <div class="w-100 row justify-content-left">
                            <label class="error-message">${sessionScope.error}</label>
                                ${sessionScope.remove("error")}
                        </div>
                    </c:if>
                    <div class="w-100 row justify-content-end">
                        <button class="h-50 col-3 btn submit"><fmt:message key="edit-subject-data.save-changes" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../tamplate/footer.jsp" />
</body>

</html>