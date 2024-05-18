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
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.3.2/chart.js"></script>
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
                            <input id="amount" type="number" min="0.00" max="any" value="" step="0.01" class="form-control"
                                   name="${RequestParameter.AMOUNT}" required>
                        </label>
                    </div>
                    <div class="form-group" style="width: 33%;">
                        <label>Процент
                            <input id="percent" type="number" min="0.00" max="any" value="" step="0.001" class="form-control"
                                   name="${RequestParameter.PERCENT}" required>
                        </label>
                    </div>
                    <div id="credit-plan">

                    </div>
                    <div style="margin-bottom: 20px">
                        <canvas id="myChart" height="0"></canvas>
                    </div>
                    <div class="w-100 row justify-content-between">
                        <button onclick="calculatePlan()" type="button" class="h-50 col-3 btn">Посчитать график платежей</button>
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
    let chart = undefined;

    const monthDiff = (d1, d2) => {
        let months;
        months = (d2.getFullYear() - d1.getFullYear()) * 12;
        months -= d1.getMonth();
        months += d2.getMonth();
        return months <= 0 ? 0 : months;
    }

    const calculatePlan = () => {
        let rows = "";

        if (chart !== undefined) {
            chart.destroy();
        }

        const date1 = new Date(document.getElementById("from").value);
        const date2 = new Date(document.getElementById("to").value);
        const amount = Number(document.getElementById("amount").value);
        const percent = Number(document.getElementById("percent").value);

        if (document.getElementById("type-input").value == "1") {
            const n = monthDiff(date1, date2);
            const p = percent / 100 / 12;
            const k = p * Math.pow(1 + p, n) / (Math.pow(1 + p, n) - 1);

            const A = amount * k;

            // Итог
            const sum = A * n;
            const percents = sum - amount;
            const effectivePercent = percents / amount * 100;

            let xValues = [];
            let yValuesPercent = [];
            let yValuesBody = [];

            let remainder = amount;

            for (let i = 0; i < n; i++) {
                let yP = remainder * p;
                let yB = A - yP;
                remainder -= yB;
                rows += `<tr>`;
                rows += `<td>` + (i + 1) + `</td>`;
                rows += `<td>` + new Date(date1.setMonth(date1.getMonth() + 1)).toISOString().split('T')[0] + `</td>`;
                rows += `<td>` + A.toFixed(2) + `</td>`;
                rows += `<td>` + yP.toFixed(2) + `</td>`;
                rows += `<td>` + yB.toFixed(2) + `</td>`;
                rows += `<td>` + (Math.abs(remainder) < 0.01 ? 0 : remainder.toFixed(2)) + `</td>;`
                rows += `</tr>`;
                xValues.push(date1.toISOString().split('T')[0]);
                yValuesPercent.push(Math.round(yP * 100) / 100);
                yValuesBody.push(Math.round((yB) * 100) / 100);
            }

            rows += `<tr>`;
            rows += `<td></td>`;
            rows += `<td></td>`;
            rows += `<td>` + sum.toFixed(2) + `</td>`;
            rows += `<td>` + percents.toFixed(2) + ` (` + effectivePercent.toFixed(2) + `%)` + `</td>`;
            rows += `<td>` + amount.toFixed(2) + `</td>`;
            rows += `<td></td>;`
            rows += `</tr>`;

            const ctx = document.getElementById('myChart');

            const data = {
                labels: xValues,
                datasets: [
                    {
                        label: 'Тело',
                        data: yValuesBody,
                        borderColor: "#7777ff",
                        backgroundColor: "#7777ff",
                        fill: true
                    },
                    {
                        label: 'Проценты',
                        data: yValuesPercent,
                        borderColor: "#ffff77",
                        backgroundColor: "#ffff77",
                        fill: true
                    }
                ]
            };

            chart = new Chart(ctx, {
                type: 'line',
                data: data,
                options: {
                    responsive: true,
                    plugins: {
                        title: {
                            display: true,
                            text: "График платежей"
                        },
                        tooltip: {
                            mode: 'index'
                        },
                    },
                    interaction: {
                        mode: 'nearest',
                        axis: 'x',
                        intersect: false
                    },
                    scales: {
                        x: {
                            title: {
                                display: true,
                                text: 'Month'
                            }
                        },
                        y: {
                            stacked: true,
                            title: {
                                display: true,
                                text: 'Value'
                            }
                        }
                    }
                }
            });

        } else {
            const n = monthDiff(date1, date2);
            const p = percent / 100 / 12;

            const A = amount * p;

            const sum = amount + A * n;
            const percents = sum - amount;
            const effectivePercent = percents / amount * 100;

            for (let i = 0; i < n; i++) {
                rows += `<tr>`;
                rows += `<td>` + (i + 1) + `</td>`;
                rows += `<td>` + new Date(date1.setMonth(date1.getMonth() + i + 1)).toISOString().split('T')[0] + `</td>`;
                rows += `<td>` + (i === n - 1 ? (amount + A).toFixed(2) : A.toFixed(2)) + `</td>`;
                rows += `<td>` + A.toFixed(2) + `</td>`;
                rows += `<td>` + (i === n - 1 ? amount : 0) + `</td>`;
                rows += `<td>` + (i === n - 1 ? 0 : amount.toFixed(2)) + `</td>;`
                rows += `</tr>`;
            }

            rows += `<tr>`;
            rows += `<td></td>`;
            rows += `<td></td>`;
            rows += `<td>` + sum.toFixed(2) + `</td>`;
            rows += `<td>` + percents.toFixed(2) + ` (` + effectivePercent.toFixed(2) + `%)` + `</td>`;
            rows += `<td>` + amount.toFixed(2) + `</td>`;
            rows += `<td></td>;`
            rows += `</tr>`;
        }

        document.getElementById("credit-plan").innerHTML =
            `<table class="table table-bordered table-striped">
                <thead>
                <tr>
                    <th scope="col">№ платежа</th>
                    <th scope="col">Дата</th>
                    <th scope="col">Платёж</th>
                    <th scope="col">Проценты</th>
                    <th scope="col">Тело</th>
                    <th scope="col">Остаток</th>
                </tr>
                </thead>
                <tbody>
                    ` + rows + `
                </tbody>
             </table>`;
    }

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
