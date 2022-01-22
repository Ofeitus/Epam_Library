<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.epam.ofeitus.library.controller.constant.RequestParameter" %>
<%@ page import="com.epam.ofeitus.library.controller.command.CommandName" %>
<%@ page import="com.epam.ofeitus.library.service.validator.ValidationPattern" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="tamplate/links.jsp" />
    <title><fmt:message key="edit-personal-data.edit-personal-data" /></title>
</head>
<body>
<jsp:include page="tamplate/header.jsp" />
<div class="container-fluid d-flex">
    <div class="w-100 row justify-content-md-center">
        <div class="col-md-5">
            <div class="form-container">
                <h3 class="title"><fmt:message key="edit-personal-data.edit-personal-data" /></h3>
                <form class="form-horizontal" action="controller" method="post">
                    <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.EDIT_PERSONAL_DATA_COMMAND}">
                    <div class="form-group">
                        <label><fmt:message key="edit-personal-data.name" />
                            <input type="text" class="form-control" pattern="${ValidationPattern.NAME_PATTERN}" title="<fmt:message key="validation-pattern.name" />"
                                   name="${RequestParameter.USER_NAME}" value="${sessionScope.user_name}" placeholder="<fmt:message key="edit-personal-data.name-placeholder" />" required>
                        </label>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="edit-personal-data.surname" />
                            <input type="text" class="form-control" pattern="${ValidationPattern.NAME_PATTERN}" title="<fmt:message key="validation-pattern.name" />"
                                   name="${RequestParameter.USER_SURNAME}" value="${sessionScope.user_surname}" placeholder="<fmt:message key="edit-personal-data.surname-placeholder" />" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 100%">
                        <label><fmt:message key="edit-personal-data.phone-number" />
                            <input type="tel" class="form-control" pattern="${ValidationPattern.PHONE_PATTERN}" title="<fmt:message key="validation-pattern.phone-number" />" name="${RequestParameter.PHONE_NUMBER}" value="${sessionScope.user_phone_number}" placeholder="<fmt:message key="edit-personal-data.phone-number-placeholder" />">
                        </label>
                    </div>
                    <div class="w-100 row justify-content-end">
                        <button class="h-50 col-4 btn submit"><fmt:message key="edit-personal-data.save-changes" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="tamplate/footer.jsp" />
</body>
</html>
