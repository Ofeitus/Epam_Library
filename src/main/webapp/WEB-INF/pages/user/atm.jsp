<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.epam.ofeitus.library.controller.constant.RequestParameter" %>
<%@ page import="com.epam.ofeitus.library.controller.command.CommandName" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <link rel='stylesheet' type='text/css' media="screen" href="static/slinky.min.css"/>
    <title><fmt:message key="user-fines.user-fines" /></title>
</head>
<body>
    <div id="menu">
        <ul>
            <li>
                <a href="#">ATM</a>
            </li>
            <li>
                <a href="#">Снять наличные</a>
                <ul>
                    <li>
                        <a href="#">Снятие наличных</a>
                    </li>
                    <li>
                        <label>
                            Введите сумму
                            <input type="number"/>
                        </label>
                    </li>
                </ul>
            </li>
            <li>
                <a href="#">Платежи</a>
                <ul>
                    <li>
                        <a href="#">Мобильная связь</a>
                        <ul>
                            <li>
                                <a href="#">Платежи за мобильную связь</a>
                            </li>
                            <li>
                                <label>
                                    Выборите оператора
                                    <select>
                                        <option>A1</option>
                                        <option>МТС</option>
                                        <option>Life:)</option>
                                    </select>
                                </label>
                            </li>
                            <li>
                                <label>
                                    Введите сумму
                                    <input type="number"/>
                                </label>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
    </div>
<script src="static/slinky.min.js"></script>
<script>
    window.slinky = $("#menu").slinky();
</script>
</body>
</html>