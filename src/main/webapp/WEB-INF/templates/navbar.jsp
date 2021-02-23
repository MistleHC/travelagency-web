<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<script>
    function goto(form) {
        var index = form.select.selectedIndex;
        if (form.select.options[index].value != "0") {
            location = form.select.options[index].value;
        }
    }
</script>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">Travel Agency</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item active">
                <a class="nav-link" href="<c:url value="/"/>"><spring:message code="nav.home" text="Home" /></a>
            </li>
            <sec:authorize access="!isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/register"/>"><spring:message code="nav.register" text="Register" /></a>
                </li>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/login"/>"><spring:message code="nav.login" text="Login" /></a>
                </li>
            </sec:authorize>
            <sec:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/manage"/>"><spring:message code="nav.management" text="Management" /></a>
                </li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/profile"/>"><spring:message code="nav.profile" text="Profile" /></a>
                </li>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/logout"/>"><spring:message code="nav.logout" text="Logout" /></a>
                </li>
            </sec:authorize>
        </ul>
    </div>
    <form name="form1" class="card card-sm border-light nav-link inf-btn">
        <label>
            <select name="select" onchange="goto(this.form)" size="1" class="form-select form-select-sm sel-tx">
                <option value="">Lang...</option>
                <option value="${pageContext.request.contextPath}/?lang=en-US">ENG</option>
                <option value="${pageContext.request.contextPath}/?lang=ru">RU</option>
            </select>
        </label>
    </form>
</nav>
