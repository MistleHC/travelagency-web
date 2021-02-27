<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <jsp:include page="home_head.jsp">
        <jsp:param name="titleName" value="Register"/>
    </jsp:include>
<body>
<div class="container-fluid">
    <jsp:include page="navbar.jsp"/>

    <div class="row check-b">
        <h1 class="text-center"><spring:message code="reg.title" text="Registration form" /></h1>
    </div>

    <div class="row check-b">
        <form:form action="/register" method="post" modelAttribute="userToRegister">

            <div class="mb-3">
                <label for="userName" class="form-label"><spring:message code="reg.name" /></label>
                <form:input path="name" type="text" class="form-control" id="userName" required="required" placeholder="Enter name"/>
                <div class="invalid-feedback d-block">
                    <form:errors path="name" />
                </div>
            </div>

            <div class="mb-3">
                <label for="userEmail" class="form-label"><spring:message code="login.email" text="Email address" /></label>
                <form:input path="email" type="email" class="form-control" id="userEmail" required="required" placeholder="name@example.com"/>
                <div class="invalid-feedback d-block">
                    <form:errors path="email" />
                </div>
            </div>

            <div class="mb-3">
                <label for="userPassword" class="form-label"><spring:message code="login.password" text="Password" /></label>
                <form:input path="password" type="password" class="form-control" id="userPassword" required="required" placeholder="Your password" />
                <div class="invalid-feedback d-block">
                    <form:errors path="password" />
                </div>
            </div>

            <div class="mb-3">
                <button class="btn btn-success" type="submit"><spring:message code="reg.reg" text="Register" /></button>
            </div>

        </form:form>
    </div>

</div>
</body>
</html>
