<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <jsp:include page="tamplate/links.jsp" />
    <title>Sign up</title>
</head>
<body>
<jsp:include page="tamplate/header.jsp" />
    <div class="container-fluid d-flex">
        <div class="w-100 row justify-content-md-center">
            <div class="col-md-5">
                <div class="form-container">
                    <h3 class="title">Sign up</h3>
                    <form class="form-horizontal" action="controller" method="get">
                        <input type="hidden" name="command" value="sign-up">
                        <div class="form-group">
                            <label>First name</label>
                            <input type="text" name="first-name" value="" class="form-control" placeholder="First Name" required>
                        </div>
                        <div class="form-group">
                            <label>Second name</label>
                            <input type="text" name="second-name" value="" class="form-control" placeholder="Second Address" required>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" name="email" value="" class="form-control" placeholder="Email" required>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" name="password" value="" class="form-control" placeholder="Password" required>
                        </div>
                        <c:if test="${sessionScope.error != null}">
                            <div class="w-100 row justify-content-left">
                                <label id="login-error-message">${sessionScope.error}</label>
                            </div>
                        </c:if>
                        <div class="w-100 row justify-content-between">
                            <span class="col-5 sign-link">Already have an account? Click here to
                                <a href="?command=goto-log-in-page">Log in</a></span>
                            <button class="h-50 col-4 btn submit">Create Account</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
