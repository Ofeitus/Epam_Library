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
        <div class="form-container" style="width: 49%">
            <h3 class="title"><fmt:message key="reports.user-composition" /></h3>
            <table class="report-data">
                <tr>
                    <th><b><fmt:message key="reports.role" /></b></th>
                    <th><b><fmt:message key="reports.count" /></b></th>
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
        <div class="form-container" style="width: 49%">
            <h3 class="title"><fmt:message key="reports.books-stock" /></h3>
            <table class="report-data">
                <tr>
                    <th><b><fmt:message key="reports.category" /></b></th>
                    <th><b><fmt:message key="reports.count" /></b></th>
                    <th><b><fmt:message key="reports.price" /></b></th>
                </tr>
                <tr>
                    <td class="data-name" style="width: 50%"><b><fmt:message key="reports.total-copies" /></b></td>
                    <td class="data-value" style="width: 25%">${books_report.totalCountTo}
                        <c:if test="${books_report.totalCountTo >= books_report.totalCountFrom}">
                            <span style="color: forestgreen">&nbsp;&nbsp;+${books_report.totalCountTo - books_report.totalCountFrom}</span>
                        </c:if>
                        <c:if test="${books_report.totalCountTo < books_report.totalCountFrom}">
                            <span style="color: firebrick">&nbsp;&nbsp;-${books_report.totalCountFrom - books_report.totalCountTo}</span>
                        </c:if>
                    </td>
                    <td class="data-value" style="width: 25%">${books_report.totalPrice}</td>
                </tr>
            </table>
            <h3 class="title"><fmt:message key="reports.categories" /></h3>
            <table class="report-data">
                <tr>
                    <th><b><fmt:message key="reports.category" /></b></th>
                    <th><b><fmt:message key="reports.count" /></b></th>
                    <th><b><fmt:message key="reports.price" /></b></th>
                </tr>
                <c:forEach items="${requestScope.book_categories}" var="book_category" varStatus="i">
                    <tr>
                        <td class="data-name" style="width: 50%"><b>${book_category.name}</b></td>
                        <td class="data-value" style="width: 25%">${books_report.countByCategoryTo[i.index]}
                            <c:if test="${books_report.countByCategoryTo[i.index] >= books_report.countByCategoryFrom[i.index]}">
                                <span style="color: forestgreen">&nbsp;&nbsp;+${books_report.countByCategoryTo[i.index] - books_report.countByCategoryFrom[i.index]}</span>
                            </c:if>
                            <c:if test="${books_report.countByCategoryTo[i.index] < books_report.countByCategoryFrom[i.index]}">
                                <span style="color: firebrick">&nbsp;&nbsp;-${books_report.countByCategoryFrom[i.index] - books_report.countByCategoryTo[i.index]}</span>
                            </c:if>
                        </td>
                        <td class="data-value" style="width: 25%">${books_report.priceByCategory[i.index]}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<jsp:include page="../tamplate/footer.jsp" />
</body>

<script>
    var xValues = [];
    <c:forEach items="${requestScope.user_composition_report.dynamicsDates}" var="date">
        xValues.push("<fmt:formatDate value="${date}" pattern="yyyy-MM-dd" />");
    </c:forEach>
    var yValues = [];
    <c:forEach items="${requestScope.user_composition_report.dynamicsValues}" var="value">
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
</script>

</html>