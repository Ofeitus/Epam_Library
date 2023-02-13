<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.epam.ofeitus.library.controller.constant.RequestParameter" %>
<%@ page import="com.epam.ofeitus.library.controller.command.CommandName" %>
<%@ page import="com.epam.ofeitus.library.entity.bank.Currency" %>

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
                <h3 class="title">Кредитный договор</h3>
                <form class="form-horizontal" action="controller" method="post">
                    <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.ADD_CREDIT_COMMAND}">
                    <div class="form-group" style="width: 33%;">
                        <label>Клиент
                            <select class="form-control" name="${RequestParameter.USER_ID}" id="client-input" required>
                                <c:forEach items="${requestScope.users}" var="user">
                                    <option value="${user.userId}">${user.surname}${" "}${user.name}${", идент. №"}${user.passportId}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Тип платежа
                            <select class="form-control" name="${RequestParameter.TYPE}" id="type-input" required>
                                <option value="1">Аннуитетный</option>
                                <option value="2">Дифференцированный</option>
                            </select>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Номер договора
                            <input type="number" min="0" max="any" value="" step="1" class="form-control"
                                   name="${RequestParameter.AGREEMENT_NUMBER}" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Дата начала
                            <input type="date" class="form-control" id="from" onchange="setTerm()"
                                   name="${RequestParameter.DATE_OF_START}" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Дата окончания
                            <input type="date" class="form-control" id="to" onchange="setTerm()"
                                   name="${RequestParameter.DATE_OF_END}" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Срок (дней)
                            <input type="number" class="form-control" id="term"
                                   name="${RequestParameter.TERM}" required disabled>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Валюта
                            <select class="form-control" name="${RequestParameter.CURRENCY}" required>
                                <c:forEach items="${Currency.values()}" var="currency">
                                    <option value="${currency}">${currency}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Сумма
                            <input type="number" min="0.00" max="any" value="" step="0.01" class="form-control"
                                   name="${RequestParameter.AMOUNT}" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Процент
                            <input type="number" min="0.00" max="any" value="" step="0.001" class="form-control"
                                   name="${RequestParameter.PERCENT}" required>
                        </label>
                    </div>
                    <div class="w-100 row justify-content-end">
                        <button type="submit" class="h-50 col-3 btn submit">Заключить договор</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../tamplate/footer.jsp" />
</body>

<script>
    const setTerm = () => {
        const date1 = new Date(document.getElementById("from").value);
        const date2 = new Date(document.getElementById("to").value);
        const diffTime = date2 - date1;
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
        if (diffDays < 1) {
            document.getElementById("from").value = null;
            document.getElementById("to").value = null;
        } else {
            document.getElementById("term").value = diffDays;
        }
    }
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
