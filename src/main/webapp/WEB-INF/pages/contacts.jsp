<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fmr" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'en'}"/>
<fmt:setBundle basename="locale"/>
<html>
<head>
    <jsp:include page="tamplate/links.jsp" />
    <title><fmt:message key="header.contacts" /></title>
    <link href="https://api.mapbox.com/mapbox-gl-js/v2.2.0/mapbox-gl.css" rel="stylesheet">
    <script src="https://api.mapbox.com/mapbox-gl-js/v2.2.0/mapbox-gl.js"></script>
</head>
<body>
<jsp:include page="tamplate/header.jsp" />
<style>
    #contacts-page {
        border-bottom: 3px solid #5f5148;
    }
</style>
<div class="contacts-container">
    <h2><fmt:message key="header.contacts" /></h2>
    <div class="wrapper">
        <div class="contacts">
            <p>
                <i class="bi bi-envelope-fill"></i>
                <a class="email-address" href="mailto:sampleEmail@email.com">&nbsp;&nbsp;sampleEmail@email.com</a>
            </p>
            <p>
                <i class="bi bi-telephone-fill"></i>
                <a class="phone-number" href="tel:+375015552968">&nbsp;&nbsp;+375015552968</a>
            </p>
            <hr>
            <p>
                <i class="bi bi-geo-alt-fill"></i>
                <a href="https://www.google.com/maps/place/%D0%9C%D0%B8%D0%BD%D1%81%D0%BA/@53.7670988,24.7025942,6.46z/data=!4m5!3m4!1s0x46dbcfd35b1e6ad3:0xb61b853ddb570d9!8m2!3d53.9006011!4d27.558972">
                    <fmt:message key="contacts.location" />
                </a>
            </p>
            <img src="${pageContext.request.contextPath}/images/map-pusheen.png" alt="">
        </div>
        <div class="location">
            <div id="map"></div>
        </div>
    </div>
</div>
<jsp:include page="tamplate/footer.jsp" />
</body>

<script>
    mapboxgl.accessToken = 'pk.eyJ1Ijoia2lzbG9yb2QiLCJhIjoiY2tvcHhsbDVwMHBzeTJ2c2o1djVzODY3eSJ9.S2bQVUWkOds89dtJzU-12Q';
    var map = new mapboxgl.Map({
        container: 'map',
        style: 'mapbox://styles/mapbox/streets-v11',
        center: [27.561824, 53.902287],
        zoom: 9
    });

    var marker = new mapboxgl.Marker()
        .setLngLat([27.561824, 53.902287])
        .addTo(map);
</script>
</html>
