<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            <h1 class="text-center">Login form</h1>
        </div>

        <div class="row check-b">
            <form action="${pageContext.request.contextPath}/login" method="post">

                <div class="mb-3">
                    <label for="userEmail" class="form-label">Email address</label>
                    <input name="email" type="email" class="form-control" id="userEmail" placeholder="name@example.com"/>
                </div>

                <div class="mb-3">
                    <label for="userPassword" class="form-label">Password</label>
                    <input name="password" type="password" class="form-control" id="userPassword" placeholder="Your password" />
                </div>

                <div class="mb-3">
                    <button class="btn btn-success" type="submit">Login</button>
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
