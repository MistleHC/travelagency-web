<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <jsp:include page="home_head.jsp">
        <jsp:param name="titleName" value="Login"/>
    </jsp:include>
<body>
<div class="container-fluid">
    <jsp:include page="navbar.jsp"/>

    <div class="row2">
        <div class="row check-b">
            <h1 class="text-center"><spring:message code="login.title" text="Login form" /></h1>
        </div>

        <div class="row check-b">
            <form action="${pageContext.request.contextPath}/login" method="post">

                <div class="mb-3">
                    <label for="userEmail" class="form-label"><spring:message code="login.email" text="Email address" /></label>
                    <input name="email" type="email" class="form-control" id="userEmail" placeholder="name@example.com"/>
                </div>

                <div class="mb-3">
                    <label for="userPassword" class="form-label"><spring:message code="login.password" text="Password" /></label>
                    <input name="password" type="password" class="form-control" id="userPassword" placeholder="Your password" />
                </div>

                <div class="mb-3">
                    <button class="btn btn-success" type="submit"><spring:message code="login.log" text="Login" /></button>
                </div>

            </form>
        </div>

        <div class="row mb-4 check-b">
            <h3 class="text-left text-danger">${error}</h3>
            <h3 class="text-left text-info">${message}</h3>
        </div>
    </div>

</div>
</body>
</html>
