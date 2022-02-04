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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title><fmt:message key="inventory-book.inventory-book" /></title>
</head>
<body>
<jsp:include page="../tamplate/header.jsp" />
<div class="inventory-book-container">
    <h2><fmt:message key="inventory-book.inventory-book" /></h2>
    <div class="inventory-book-forms">
        <div class="form-container" style="width: 33%">
            <h3 class="title"><fmt:message key="inventory-book.search-page" /></h3>
            <form class="form-horizontal" action="controller" method="get">
                <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.SEARCH_COPIES_OF_BOOKS_COMMAND}">
                <div class="form-group" style="width: 100%">
                    <label><fmt:message key="inventory-book.status" />
                        <select class="form-control" name="${RequestParameter.STATUS}">
                            <option value=""><fmt:message key="inventory-book.all-copies" /></option>
                            <option value="${RequestParameter.STATUS_EXISTING}" selected><fmt:message key="inventory-book.only-existing" /></option>
                            <option value="${RequestParameter.STATUS_WRITTEN_OFF}"><fmt:message key="inventory-book.only-written-off" /></option>
                        </select>
                    </label>
                </div>
                <div class="form-group">
                    <label><fmt:message key="inventory-book.inventory-id" />
                        <input type="number" name="${RequestParameter.INVENTORY_ID}" value="" min="1" class="form-control" placeholder="<fmt:message key="inventory-book.inventory-id-placeholder" />">
                    </label>
                </div>
                <div class="form-group">
                    <label><fmt:message key="inventory-book.book-isbn" />
                        <input type="text" class="form-control" pattern="${ValidationPattern.ISBN_PATTERN}" title="<fmt:message key="validation-pattern.isbn" />"
                               name="${RequestParameter.BOOK_ISBN}" value="" placeholder="<fmt:message key="inventory-book.book-isbn-placeholder" />">
                    </label>
                </div>
                <div class="w-100 row justify-content-between search-buttons">
                    <button type="reset" class="h-50 col-3 btn reset"><fmt:message key="inventory-book.clear" /></button>
                    <button type="submit" class="h-50 col-3 btn submit"><fmt:message key="inventory-book.search" /></button>
                </div>
            </form>
        </div>
        <div class="form-container" style="width: 65%">
            <div style="display: flex">
                <form style="width: 50%" class="form-horizontal" action="controller" method="post">
                    <h3 class="title"><fmt:message key="inventory-book.arrival" /></h3>
                    <input id="arrival-command" type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.WRITE_IN_COPIES_OF_BOOK_COMMAND}">
                    <div class="form-group" id="write-in-method">
                        <label><fmt:message key="inventory-book.write-in-method" />
                            <select id="method-select" class="form-control" name="write-in-method" onchange="changeMethod()">
                                <option value="existing-book" selected><fmt:message key="inventory-book.existing-book" /></option>
                                <option value="new-book"><fmt:message key="inventory-book.new-book" /></option>
                            </select>
                        </label>
                    </div>
                    <div class="form-group existing-book">
                        <label><fmt:message key="inventory-book.book-isbn" />
                            <input id="existing-book-isbn" type="text" class="form-control" pattern="^([\d]{10}|[\d]{13})$" title="<fmt:message key="validation-pattern.isbn" />"
                                   name="${RequestParameter.BOOK_ISBN}" value="" placeholder="<fmt:message key="inventory-book.book-isbn-placeholder" />">
                        </label>
                    </div>
                    <div class="form-group existing-book">
                        <label><fmt:message key="inventory-book.price" />
                            <input id="price" type="number" name="${RequestParameter.PRICE}" value="0.00" step="0.01" min="0" class="form-control" required placeholder="<fmt:message key="inventory-book.price-placeholder" />">
                        </label>
                    </div>
                    <div class="form-group existing-book">
                        <label><fmt:message key="inventory-book.number-of-copies" />
                            <input id="number-of-copies" type="number" name="${RequestParameter.COPIES_COUNT}" value="0" min="0" class="form-control" required placeholder="<fmt:message key="inventory-book.number-of-copies-placeholder" />">
                        </label>
                    </div>
                    <c:if test="${sessionScope.error != null}">
                        <div class="w-100 row justify-content-left">
                            <label class="error-message">${sessionScope.error}</label>
                                ${sessionScope.remove("error")}
                        </div>
                    </c:if>
                    <div class="w-100 row justify-content-end search-buttons">
                        <button type="submit" id="submit-arrival" class="h-50 col-4 btn submit"><fmt:message key="inventory-book.write-in" /></button>
                    </div>
                </form>
                <div style="margin: 10px"></div>
                <form style="width: 50%" class="form-horizontal" action="controller" method="post">
                    <h3 class="title"><fmt:message key="inventory-book.writing-off" /></h3>
                    <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.WRITE_OFF_COPIES_OF_BOOKS_COMMAND}">
                    <div class="form-group">
                        <label><fmt:message key="inventory-book.from-inv-id" />
                            <input type="number" name="${RequestParameter.INVENTORY_ID_FROM}" value="" min="1" class="form-control" placeholder="<fmt:message key="inventory-book.inventory-id-placeholder" />" required>
                        </label>
                    </div>
                    <div class="form-group">
                        <label><fmt:message key="inventory-book.to-inv-id" />
                            <input type="number" name="${RequestParameter.INVENTORY_ID_TO}" value="" min="1" class="form-control" placeholder="<fmt:message key="inventory-book.inventory-id-placeholder" />" required>
                        </label>
                    </div>
                    <div class="w-100 row justify-content-end search-buttons">
                        <button type="submit" id="submit-writing-off" class="h-50 col-4 btn submit"><fmt:message key="inventory-book.write-off" /></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="table-container" style="margin: 0 10% 50px 10%">
    <c:if test="${requestScope.copies_of_books.size() == 0}">
        <h3><fmt:message key="inventory-book.no-search-results" /></h3>
    </c:if>
    <c:if test="${requestScope.copies_of_books.size() > 0}">
        <table class="table table-bordered table-striped">
            <thead>
            <tr>
                <th scope="col"><fmt:message key="inventory-book.receipt-date" /></th>
                <th scope="col"><fmt:message key="inventory-book.inventory-id" /></th>
                <th scope="col"><fmt:message key="inventory-book.author" /></th>
                <th scope="col"><fmt:message key="inventory-book.book-title" /></th>
                <th scope="col"><fmt:message key="inventory-book.book-isbn" /></th>
                <th scope="col"><fmt:message key="inventory-book.publication-year" /></th>
                <th scope="col"><fmt:message key="inventory-book.price" /></th>
                <th scope="col"><fmt:message key="inventory-book.status" /></th>
                <th scope="col"><fmt:message key="inventory-book.delete" /></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.copies_of_books}" var="copy_of_book">
                <tr>
                    <td>${copy_of_book.receiptDate}</td>
                    <td>${copy_of_book.inventoryId}</td>
                    <td>
                        <c:forEach items="${copy_of_book.book.authors}" var="author" varStatus="i">
                            ${author.name}&nbsp;${author.surname}
                            <c:if test="${i.index < copy_of_book.book.authors.size() - 1}">
                                ,&nbsp;
                            </c:if>
                        </c:forEach>
                    </td>
                    <td>
                        <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_BOOK_DETAILS_PAGE_COMMAND}&${RequestParameter.BOOK_ISBN}=${copy_of_book.bookIsbn}">
                            ${copy_of_book.book.title}
                        </a>
                    </td>
                    <td>${copy_of_book.book.isbn}</td>
                    <td>${copy_of_book.book.publicationYear}</td>
                    <td>${copy_of_book.price}</td>
                    <td>
                        <c:choose>
                            <c:when test="${copy_of_book.copyOfBookStatus == 'AVAILABLE'}">
                                <i class="bi bi-circle-fill" style="color:#808080"></i>
                                <fmt:message key="copy-of-book-status.available" />
                            </c:when>
                            <c:when test="${copy_of_book.copyOfBookStatus == 'READING_ROOM'}">
                                <!-- TODO Reading room -->
                                <i class="bi bi-circle-fill" style="color:lawngreen"></i>
                                <fmt:message key="copy-of-book-status.reading-room" />
                            </c:when>
                            <c:when test="${copy_of_book.copyOfBookStatus == 'RESERVED'}">
                                <i class="bi bi-circle-fill" style="color:#4169e1"></i>
                                <c:if test="${copy_of_book.userId != 0}">
                                    <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_USER_RESERVATIONS_PAGE_COMMAND}&${RequestParameter.USER_ID}=${copy_of_book.userId}">
                                        <fmt:message key="copy-of-book-status.reserved" /></a>
                                </c:if>
                                <c:if test="${copy_of_book.userId == 0}">
                                    <fmt:message key="copy-of-book-status.reserved" />
                                </c:if>
                            </c:when>
                            <c:when test="${copy_of_book.copyOfBookStatus == 'LOANED'}">
                                <i class="bi bi-circle-fill" style="color:#228b22"></i>
                                <c:if test="${copy_of_book.userId != 0}">
                                    <a href="?${RequestParameter.COMMAND}=${CommandName.GOTO_USER_LOANS_PAGE_COMMAND}&${RequestParameter.USER_ID}=${copy_of_book.userId}">
                                        <fmt:message key="copy-of-book-status.loaned" />
                                    </a>
                                </c:if>
                                <c:if test="${copy_of_book.userId == 0}">
                                    <fmt:message key="copy-of-book-status.loaned" />
                                </c:if>
                            </c:when>
                            <c:when test="${copy_of_book.copyOfBookStatus == 'WRITTEN_OFF'}">
                                <i class="bi bi-circle-fill" style="color:firebrick"></i>
                                <fmt:message key="copy-of-book-status.written-off" />
                            </c:when>
                        </c:choose>
                    </td>
                    <td style="text-align: center">
                        <c:if test="${copy_of_book.canBeDeleted}">
                            <form action="controller" method="post">
                                <input type="hidden" name="${RequestParameter.COMMAND}" value="${CommandName.DELETE_COPY_OF_BOOK_COMMAND}">
                                <input type="hidden" name="${RequestParameter.INVENTORY_ID}" value="${copy_of_book.inventoryId}">
                                <button type="submit" class="link-button"><i class="bi bi-trash-fill" style="font-size: 20px;color: firebrick"></i></button>
                            </form>
                        </c:if>
                        <c:if test="${!copy_of_book.canBeDeleted}">
                            <a title="<fmt:message key="inventory-book.cant-delete" />"><i class="bi bi-trash-fill" style="font-size: 20px;color: gray"></i></a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pages-navigation">
            <c:if test="${requestScope.current_page != 1}">
                <a href="${sessionScope.url_without_page}&${RequestParameter.PAGE}=${requestScope.current_page - 1}"><i class="bi bi-arrow-left-circle-fill"></i></a>
            </c:if>
            &nbsp;<span><fmt:message key="catalog.page" /> ${requestScope.current_page} <fmt:message key="catalog.of" /> ${requestScope.pages_count}</span>&nbsp;
            <c:if test="${requestScope.current_page < requestScope.pages_count}">
                <a href="${sessionScope.url_without_page}&${RequestParameter.PAGE}=${requestScope.current_page + 1}"><i class="bi bi-arrow-right-circle-fill"></i></a>
            </c:if>
        </div>
    </c:if>
</div>
<jsp:include page="../tamplate/footer.jsp" />
</body>

<script>
    let selectForm = document.getElementById('write-in-method');
    let select = document.getElementById('method-select');
    let subForms = document.getElementsByClassName('existing-book');
    let arrivalCommand = document.getElementById('arrival-command');
    let submitArrival = document.getElementById('submit-arrival');
    arrivalCommand.setAttribute('value', '${CommandName.WRITE_IN_COPIES_OF_BOOK_COMMAND}');
    submitArrival.innerHTML = "<fmt:message key="inventory-book.write-in" />";

    function changeMethod() {
        if (select.value === "existing-book") {
            selectForm.setAttribute('style', 'width:50%');
            subForms[0].setAttribute('style', 'display:inline-block');
            subForms[1].setAttribute('style', 'display:inline-block');
            subForms[2].setAttribute('style', 'display:inline-block');
            arrivalCommand.setAttribute('value', '${CommandName.WRITE_IN_COPIES_OF_BOOK_COMMAND}');
            submitArrival.innerHTML = "<fmt:message key="inventory-book.write-in" />";
        } else {
            selectForm.setAttribute('style', 'width:100%');
            subForms[0].setAttribute('style', 'display:none');
            subForms[1].setAttribute('style', 'display:none');
            subForms[2].setAttribute('style', 'display:none');
            arrivalCommand.setAttribute('value', '${CommandName.GOTO_ADD_NEW_BOOK_PAGE_COMMAND}');
            submitArrival.innerHTML = "<fmt:message key="inventory-book.add-new-book" />";
        }
    }
</script>

</html>
