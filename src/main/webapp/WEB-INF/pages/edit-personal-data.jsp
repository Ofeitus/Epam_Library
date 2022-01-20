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
                            <input type="text" name="${RequestParameter.FIRST_NAME}" value="${sessionScope.user_name}" class="form-control" placeholder="<fmt:message key="edit-personal-data.name-placeholder" />" required>
                        </label>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="edit-personal-data.surname" />
                            <input type="text" name="${RequestParameter.SECOND_NAME}" value="${sessionScope.user_surname}" class="form-control" placeholder="<fmt:message key="edit-personal-data.surname-placeholder" />" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 100%">
                        <label><fmt:message key="edit-personal-data.phone-number" />
                            <input type="tel" name="${RequestParameter.PHONE_NUMBER}" value="${sessionScope.user_phone_number}" class="form-control" placeholder="<fmt:message key="edit-personal-data.phone-number-placeholder" />">
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
