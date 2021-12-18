<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar">
    <div class="navbar-container">
        <div class="navbar-toggle" id="mobile-menu">
            <span class="bar"></span>
            <span class="bar"></span>
            <span class="bar"></span>
        </div>
        <ul class="navbar-menu">
            <li class="navbar-item" id="home-page">
                <a href="?command=goto-home-page" class="navbar-link">Home</a>
            </li>
            <li class="navbar-item">
                <a href="#" class="navbar-link" id="about-page">About</a>
            </li>
            <li class="navbar-item">
                <a href="?command=goto-catalog-page" class="navbar-link" id="catalog-page">Catalog</a>
            </li>
            <li class="navbar-item">
                <a href="#" class="navbar-link" id="contacts-page">Contacts</a>
            </li>
        </ul>
    </div>
</nav>