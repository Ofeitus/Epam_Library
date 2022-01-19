<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="../tamplate/links.jsp" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
    <title><fmt:message key="header.reports" /></title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="reports-container">
    <h2><fmt:message key="header.reports" /></h2>
    <div class="reports-forms">
        <div class="form-container" style="width: 60%">
            <h3 class="title"><fmt:message key="reports.period" /></h3>
            <form class="form-horizontal" action="controller" method="get">
                <input type="hidden" name="command" value="get-period-reports">
                <div class="form-group">
                    <label><fmt:message key="reports.from-date" /></label>
                    <input type="date" name="from-date" value="" class="form-control" required />
                </div>
                <div class="form-group">
                    <label><fmt:message key="reports.to-date" /></label>
                    <input type="date" name="to-date" value="" class="form-control" required />
                </div>
                <div class="w-100 row justify-content-end search-buttons">
                    <button type="submit" class="h-50 col-3 btn submit"><fmt:message key="reports.get-report" /></button>
                </div>
            </form>
        </div>
        <img style="align-self: center; height: 250px" src="${pageContext.request.contextPath}/images/reports-pusheen.png" alt="">
    </div>
    <div class="reports-forms">
        <c:set value="${requestScope.user_composition_report}" var="users_report" />
        <c:set value="${requestScope.books_stock_report}" var="books_report" />
        <div class="form-container" style="width: 44%">
            <h3 class="title"><fmt:message key="reports.user-composition" /></h3>
            <table class="report-data">
                <tr>
                    <th><fmt:message key="reports.role" /></th>
                    <th><fmt:message key="reports.count" /></th>
                </tr>
                <tr>
                    <td class="data-name"><b><fmt:message key="reports.total-users" /></b></td>
                    <td class="data-value">${users_report.totalCountTo}
                        <c:if test="${users_report.totalCountTo >= users_report.totalCountFrom}">
                            <span style="color: forestgreen">&nbsp;&nbsp;+${users_report.totalCountTo - users_report.totalCountFrom}</span>
                        </c:if>
                        <c:if test="${users_report.totalCountTo < users_report.totalCountFrom}">
                            <span style="color: firebrick">&nbsp;&nbsp;-${users_report.totalCountFrom - users_report.totalCountTo}</span>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td class="data-name"><b><fmt:message key="user-role.admin" /></b></td>
                    <td class="data-value">${users_report.adminCountTo}
                        <c:if test="${users_report.adminCountTo >= users_report.adminCountFrom}">
                            <span style="color: forestgreen">&nbsp;&nbsp;+${users_report.adminCountTo - users_report.adminCountFrom}</span>
                        </c:if>
                        <c:if test="${users_report.adminCountTo < users_report.adminCountFrom}">
                            <span style="color: firebrick">&nbsp;&nbsp;-${users_report.adminCountFrom - users_report.adminCountTo}</span>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td class="data-name"><b><fmt:message key="user-role.manager" /></b></td>
                    <td class="data-value">${users_report.managerCountTo}
                        <c:if test="${users_report.managerCountTo >= users_report.managerCountFrom}">
                            <span style="color: forestgreen">&nbsp;&nbsp;+${users_report.managerCountTo - users_report.managerCountFrom}</span>
                        </c:if>
                        <c:if test="${users_report.managerCountTo < users_report.managerCountFrom}">
                            <span style="color: firebrick">&nbsp;&nbsp;-${users_report.managerCountFrom - users_report.managerCountTo}</span>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td class="data-name"><b><fmt:message key="user-role.member" /></b></td>
                    <td class="data-value">${users_report.memberCountTo}
                        <c:if test="${users_report.memberCountTo >= users_report.memberCountFrom}">
                            <span style="color: forestgreen">&nbsp;&nbsp;+${users_report.memberCountTo - users_report.memberCountFrom}</span>
                        </c:if>
                        <c:if test="${users_report.memberCountTo < users_report.memberCountFrom}">
                            <span style="color: firebrick">&nbsp;&nbsp;-${users_report.memberCountFrom - users_report.memberCountTo}</span>
                        </c:if>
                    </td>
                </tr>
            </table>
            <h3 class="title"><fmt:message key="reports.members-count-dynamics" /></h3>
            <canvas id="users-composition-chart"></canvas>
        </div>
        <div class="form-container" style="width: 54%">
            <h3 class="title"><fmt:message key="reports.books-stock" /></h3>
            <table class="report-data">
                <tr>
                    <th><fmt:message key="reports.category" /></th>
                    <th><fmt:message key="reports.count" /></th>
                    <th><fmt:message key="reports.price" /></th>
                </tr>
                <tr>
                    <td class="data-name" style="width: 40%"><b><fmt:message key="reports.total-copies" /></b></td>
                    <td class="data-value" style="width: 25%">${books_report.totalCountTo}
                        <c:if test="${books_report.totalCountTo >= books_report.totalCountFrom}">
                            <span style="color: forestgreen">&nbsp;&nbsp;+${books_report.totalCountTo - books_report.totalCountFrom}</span>
                        </c:if>
                        <c:if test="${books_report.totalCountTo < books_report.totalCountFrom}">
                            <span style="color: firebrick">&nbsp;&nbsp;-${books_report.totalCountFrom - books_report.totalCountTo}</span>
                        </c:if>
                    </td>
                    <td class="data-value" style="width: 35%">${books_report.totalPriceTo}
                        <c:if test="${books_report.totalPriceTo >= books_report.totalPriceFrom}">
                            <span style="color: forestgreen">&nbsp;&nbsp;+${books_report.totalPriceTo - books_report.totalPriceFrom}</span>
                        </c:if>
                        <c:if test="${books_report.totalPriceTo < books_report.totalPriceFrom}">
                            <span style="color: firebrick">&nbsp;&nbsp;-${books_report.totalPriceFrom - books_report.totalPriceTo}</span>
                        </c:if>
                    </td>
                </tr>
            </table>
            <h3 class="title"><fmt:message key="reports.categories" /></h3>
            <table class="report-data">
                <tr>
                    <th><fmt:message key="reports.category" /></th>
                    <th><fmt:message key="reports.count" /></th>
                    <th><fmt:message key="reports.price" /></th>
                </tr>
                <c:forEach items="${requestScope.book_categories}" var="book_category" varStatus="i">
                    <tr>
                        <td class="data-name" style="width: 40%"><b>${book_category.name}</b></td>
                        <td class="data-value" style="width: 25%">${books_report.countByCategoryTo[i.index]}
                            <c:if test="${books_report.countByCategoryTo[i.index] >= books_report.countByCategoryFrom[i.index]}">
                                <span style="color: forestgreen">&nbsp;&nbsp;+${books_report.countByCategoryTo[i.index] - books_report.countByCategoryFrom[i.index]}</span>
                            </c:if>
                            <c:if test="${books_report.countByCategoryTo[i.index] < books_report.countByCategoryFrom[i.index]}">
                                <span style="color: firebrick">&nbsp;&nbsp;-${books_report.countByCategoryFrom[i.index] - books_report.countByCategoryTo[i.index]}</span>
                            </c:if>
                        </td>
                        <td class="data-value" style="width: 35%">${books_report.priceByCategoryTo[i.index]}
                            <c:if test="${books_report.priceByCategoryTo[i.index] >= books_report.priceByCategoryFrom[i.index]}">
                                <span style="color: forestgreen">&nbsp;&nbsp;+${books_report.priceByCategoryTo[i.index] - books_report.priceByCategoryFrom[i.index]}</span>
                            </c:if>
                            <c:if test="${books_report.priceByCategoryTo[i.index] < books_report.priceByCategoryFrom[i.index]}">
                                <span style="color: firebrick">&nbsp;&nbsp;-${books_report.priceByCategoryFrom[i.index] - books_report.priceByCategoryTo[i.index]}</span>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <c:set value="${requestScope.issue_report}" var="issue_report" />
    <div class="form-container">
        <div style="display: flex; justify-content: space-between">
            <div style="width: 50%">
                <h3 class="title"><fmt:message key="reports.issue-report" /></h3>
                <table class="report-data">
                    <tr>
                        <th><fmt:message key="reports.criteria" /></th>
                        <th><fmt:message key="reports.total" /></th>
                        <th><fmt:message key="reports.over-a-period" /></th>
                    </tr>
                    <tr>
                        <td class="data-name" style="width: 40%"><b><fmt:message key="reports.issued" /></b></td>
                        <td class="data-value" style="width: 25%">${issue_report.totalIssuedTo}</td>
                        <td class="data-value" style="width: 35%">${issue_report.totalIssuedTo - issue_report.totalIssuedFrom}</td>
                    </tr>
                    <tr>
                        <td class="data-name" style="width: 40%"><b><fmt:message key="reports.issued-through-reservation" /></b></td>
                        <td class="data-value" style="width: 25%">${issue_report.totalIssuedReservedTo}</td>
                        <td class="data-value" style="width: 35%">${issue_report.totalIssuedReservedTo - issue_report.totalIssuedReservedFrom}</td>
                    </tr>
                </table>
            </div>
            <div><canvas id="issuing-method-chart" style="height: 250px"></canvas></div>
        </div>
        <div style="display: flex; justify-content: space-between">
            <div style="width: 50%">
                <h3 class="title"><fmt:message key="reports.books-stock-status" /></h3>
                <table class="report-data">
                    <tr>
                        <th><fmt:message key="reports.status" /></th>
                        <th><fmt:message key="reports.count" /></th>
                    </tr>
                    <tr>
                        <td class="data-name"><b><fmt:message key="copy-of-book-status.available" /></b></td>
                        <td class="data-value">${issue_report.totalAvailable}</td>
                    </tr>
                    <tr>
                        <td class="data-name"><b><fmt:message key="copy-of-book-status.reserved" /></b></td>
                        <td class="data-value">${issue_report.totalReserved}</td>
                    </tr>
                    <tr>
                        <td class="data-name"><b><fmt:message key="copy-of-book-status.loaned" /></b></td>
                        <td class="data-value">${issue_report.totalLoaned}</td>
                    </tr>
                </table>
            </div>
            <div><canvas id="stock-status-chart" style="height: 250px; margin-top: 15px"></canvas></div>
        </div>
        <div style="display: flex; justify-content: space-between">
            <div style="width: 40%">
                <h3 class="title"><fmt:message key="issue-dynamics" /></h3>
                <table class="report-data">
                    <tr>
                        <th><fmt:message key="reports.criteria" /></th>
                        <th><fmt:message key="reports.count-per-month" /></th>
                    </tr>
                    <tr>
                        <td class="data-name"><b><fmt:message key="reports.average-issued" /></b></td>
                        <td class="data-value"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${(issue_report.totalIssuedTo - issue_report.totalIssuedFrom) * 2 / issue_report.dynamicsValues.size()}" /></td>
                    </tr>
                    <tr>
                        <td class="data-name"><b><fmt:message key="reports.average-issued-through-reservations" /></b></td>
                        <td class="data-value"><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${(issue_report.totalIssuedReservedTo - issue_report.totalIssuedReservedFrom) * 2 / issue_report.dynamicsValues.size()}" /></td>
                    </tr>
                </table>
            </div>
            <div><canvas id="issue-dynamics-chart" style="height: 270px; margin-top: 15px"></canvas></div>
        </div>
    </div>
</div>
<jsp:include page="../tamplate/footer.jsp" />
</body>

<script>
    var xValues = [];
    <c:forEach items="${users_report.dynamicsDates}" var="date">
        xValues.push("<fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />");
    </c:forEach>
    var yValues = [];
    <c:forEach items="${users_report.dynamicsValues}" var="value">
        yValues.push(${value});
    </c:forEach>

    new Chart("users-composition-chart", {
        type: "line",
        data: {
            labels: xValues,
            datasets: [{
                data: yValues,
                lineTension: 0,
                backgroundColor: "rgba(0,0,255,0.2)",
                borderColor: "rgba(0,0,255,0.6)",
            }]
        },
        options: {
            legend: {display: false},
        }
    });

    xValues = ["<fmt:message key="reports.through-reservation" />", "<fmt:message key="reports.in-library" />"];
    yValues = [${issue_report.totalIssuedReservedTo}, ${issue_report.totalIssuedTo - issue_report.totalIssuedReservedTo}];
    var barColors = [
        "#4169E1",
        "#228B22"
    ];

    new Chart("issuing-method-chart", {
        type: "pie",
        data: {
            labels: xValues,
            datasets: [{
                backgroundColor: barColors,
                data: yValues
            }]
        },
        options: {
            title: {
                display: true,
                text: "<fmt:message key="reports.issuing-method" />"
            }
        }
    });

    xValues = ["<fmt:message key="copy-of-book-status.available" />",
        "<fmt:message key="copy-of-book-status.reserved" />",
        "<fmt:message key="copy-of-book-status.loaned" />"];
    yValues = [${issue_report.totalAvailable}, ${issue_report.totalReserved}, ${issue_report.totalLoaned}];
    barColors = [
        "#808080",
        "#4169E1",
        "#228B22"
    ];

    new Chart("stock-status-chart", {
        type: "pie",
        data: {
            labels: xValues,
            datasets: [{
                backgroundColor: barColors,
                data: yValues
            }]
        },
        options: {
            title: {
                display: true,
                text: "<fmt:message key="reports.books-stock-status" />"
            }
        }
    });

    xValues = [];
    <c:forEach items="${issue_report.dynamicsDates}" var="date">
        xValues.push("<fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />");
    </c:forEach>
    yValues = [];
    var yValues2 = [];
    <c:forEach items="${issue_report.dynamicsValues}" varStatus="i" var="value">
        <c:if test="${i.index % 2 == 0}">
            yValues.push(${value});
        </c:if>
        <c:if test="${i.index % 2 != 0}">
            yValues2.push(${value});
        </c:if>
    </c:forEach>

    new Chart("issue-dynamics-chart", {
        type: "bar",
        data: {
            labels: xValues,
            datasets: [
                {
                data: yValues,
                backgroundColor: "#228B22"
            },
                {
                data: yValues2,
                backgroundColor: "#4169E1"
            }]
        },
        options: {
            legend: {display: false},
        }
    });

</script>

</html>