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
    <jsp:include page="../tamplate/links.jsp" />
    <title>Add client</title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="container-fluid d-flex">
    <div class="w-100 row justify-content-md-center">
        <div class="col-md-8">
            <div class="form-container">
                <h3 class="title">Добавить клиента</h3>
                <form class="form-horizontal" action="controller" method="post">
                    <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.ADD_CLIENT_COMMAND}">
                    <div class="form-group" style="width: 33%;">
                        <label>Фамилия
                            <input type="text" class="form-control" pattern="${ValidationPattern.NAME_PATTERN}"
                                   name="${RequestParameter.USER_SURNAME}" minlength="1" maxlength="100" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Имя
                            <input type="text" class="form-control"
                                   name="${RequestParameter.USER_NAME}" minlength="1" maxlength="100" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Отчество
                            <input type="text" class="form-control" pattern="${ValidationPattern.NAME_PATTERN}"
                                   name="${RequestParameter.USER_PATRONYMIC}" minlength="1" maxlength="100" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 50%;">
                        <label>Дата рождения
                            <input type="date" class="form-control"
                                   name="${RequestParameter.USER_DATE_OF_BIRTH}" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 50%">
                        <label>Пол</label>
                        <fieldset style="display: flex; flex-direction: row">
                            <input type="radio" id="male"
                                   name="${RequestParameter.USER_GENDER}" value="m" required>
                            <label for="male">М</label>
                            <input type="radio" id="female"
                                   name="${RequestParameter.USER_GENDER}" value="f" required>
                            <label for="female">Ж</label>
                        </fieldset>
                    </div>
                    <h3 class="title">Паспорт</h3>
                    <div class="form-group" style="width: 33%;">
                        <label>Серия паспорта
                            <input type="text" class="form-control"
                                   name="${RequestParameter.USER_PASSPORT_SERIES}" minlength="1" maxlength="100" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Номер паспорта
                            <input type="text" class="form-control" pattern="${ValidationPattern.PASSPORT_NUMBER}"
                                   name="${RequestParameter.USER_PASSPORT_NUMBER}" minlength="1" maxlength="100" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Идент. номер
                            <input type="text" class="form-control" pattern="${ValidationPattern.PASSPORT_ID}"
                                   name="${RequestParameter.USER_PASSPORT_ID}" minlength="1" maxlength="100" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 50%;">
                        <label>Кем выдан
                            <input type="text" class="form-control"
                                   name="${RequestParameter.USER_ISSUED_BY}" minlength="1" maxlength="100" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 50%;">
                        <label>Дата выдачи
                            <input type="date" class="form-control"
                                   name="${RequestParameter.USER_DATE_OF_ISSUING}" required>
                        </label>
                    </div>
                    <h3 class="title">Жильё</h3>
                    <div class="form-group" style="width: 33%">
                        <label>Место рождения
                            <input type="text" class="form-control"
                                   name="${RequestParameter.USER_PLACE_OF_BIRTH}" minlength="1" maxlength="100" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%">
                        <label>Город факт. проживания
                            <select id="select4" class="form-control" name="${RequestParameter.CITY_OF_LIVING}">
                                <option value="1" selected>Минск</option>
                                <option value="2">Столбцы</option>
                                <option value="3">Барановичи</option>
                                <option value="4">Вилейка</option>
                                <option value="5">Гомель</option>
                            </select>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Адрес факт. проживания
                            <input type="text" class="form-control"
                                   name="${RequestParameter.USER_ADDRESS}" minlength="1" maxlength="100" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Город прописки
                            <select id="select3" class="form-control" name="${RequestParameter.CITY_OF_REGISTRATION}">
                                <option value="1" selected>Минск</option>
                                <option value="2">Столбцы</option>
                                <option value="3">Барановичи</option>
                                <option value="4">Вилейка</option>
                                <option value="5">Гомель</option>
                            </select>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Адрес прописки
                            <input type="text" class="form-control"
                                   name="${RequestParameter.USER_ADDRESS_OF_REGISTRATION}" minlength="1" maxlength="100" required>
                        </label>
                    </div>
                    <h3 class="title">Контакты</h3>
                    <div class="form-group" style="width: 33%;">
                        <label>Телефон дом.
                            <input type="tel" class="form-control" pattern="${ValidationPattern.HOME_PHONE_PATTERN}"
                                   name="${RequestParameter.USER_PHONE_HOME}">
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Телефон моб.
                            <input type="tel" class="form-control" pattern="${ValidationPattern.MOBILE_PHONE_PATTERN}"
                                   name="${RequestParameter.USER_PHONE_MOBILE}">
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Email
                            <input type="email" class="form-control" pattern="${ValidationPattern.EMAIL_PATTERN}"
                                   name="${RequestParameter.USER_EMAIL}" minlength="1" maxlength="100">
                        </label>
                    </div>
                    <h3 class="title">Работа</h3>
                    <div class="form-group" style="width: 33%;">
                        <label>Место работы
                            <input type="text" class="form-control"
                                   name="${RequestParameter.USER_PLACE_OF_WORK}" minlength="1" maxlength="100">
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Должность
                            <input type="text" class="form-control"
                                   name="${RequestParameter.USER_JOB_TITLE}" minlength="1" maxlength="100">
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Ежемесячный доход
                            <input type="number" min="0.00" max="any" value="" step="0.01" class="form-control"
                                   name="${RequestParameter.USER_SALARY}">
                        </label>
                    </div>
                    <h3 class="title">Другое</h3>
                    <div class="form-group" style="width: 25%;">
                        <label>Семейное положение
                            <select id="select2" class="form-control" name="${RequestParameter.FAMILY_STATUS}">
                                <option value="1" selected>Не замужем, не женат</option>
                                <option value="2">Замужем, женат</option>
                                <option value="3">Разведён, разведена</option>
                                <option value="4">Вдова, вдовец</option>
                            </select>
                        </label>
                    </div>
                    <div class="form-group" style="width: 25%;">
                        <label>Инвалидность
                            <select id="select1" class="form-control" name="${RequestParameter.DISABILITY}">
                                <option value="1">Нет</option>
                                <option value="2">I группа</option>
                                <option value="3">II группа</option>
                                <option value="4">III группа</option>
                            </select>
                        </label>
                    </div>
                    <div class="form-group" style="width: 25%;">
                        <label>Пенсионер
                            <input type="checkbox"
                                   name="${RequestParameter.USER_PENSIONER}">
                        </label>
                    </div>
                    <div class="form-group" style="width: 25%;">
                        <label>Военнообязанный
                            <input type="checkbox"
                                   name="${RequestParameter.USER_CONSCRIPT}">
                        </label>
                    </div>
                    <div class="w-100 row justify-content-end">
                        <button type="submit" class="h-50 col-3 btn submit">Добавить</button>
                    </div>
                </form>
            </div>
        </div>
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
