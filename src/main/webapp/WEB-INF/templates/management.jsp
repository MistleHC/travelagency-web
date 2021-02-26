<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<jsp:include page="home_head.jsp">
    <jsp:param name="titleName" value="Management"/>
</jsp:include>
<body>
<div class="container-fluid">

    <jsp:include page="navbar.jsp"/>

    <div class="bg-light">
        <h1 class="text-center"><spring:message code="man.title" text="Orders" /></h1><br>
        <c:choose>
            <c:when test="${empty orders}">
                <h4>There no orders</h4>
            </c:when>
            <c:otherwise>
                <table class="table table-hover table-bordered">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col"><spring:message code="man.customer.id" text="Customer ID" /></th>
                        <th scope="col"><spring:message code="man.customer.login" text="Customer login" /></th>
                        <th scope="col"><spring:message code="man.tour.name" text="Tour name" /></th>
                        <th scope="col"><spring:message code="profile.o.price" text="Price" /></th>
                        <th scope="col"><spring:message code="man.actions" text="Actions" /></th>
                    </tr>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td>
                                    ${order.id}
                            </td>
                            <td>
                                    ${order.customer.id}
                            </td>
                            <td>
                                    ${order.customer.name}
                            </td>
                            <td>
                                    ${order.tour.name}
                            </td>
                            <td>
                                    ${order.tour.price - (order.tour.price * order.tour.discount / 100)} UAH
                            </td>
                            <td>
                                <div class="card-body row no-gutters align-items-center">
                                    <div class="col-md-2">
                                        <form action="/manage/paid" method="get">
                                            <input type="hidden" name="orderid" value="${order.id}" />
                                            <button class="btn btn-sm btn-success search-btn" type="submit"><spring:message code="man.a.paid" text="Paid" /></button>
                                        </form>
                                    </div>
                                    <div class="col-md-2">
                                        <form action="/manage/decline" method="get">
                                            <input type="hidden" name="orderid" value="${order.id}" />
                                            <button class="btn btn-sm btn-warning search-btn" type="submit"><spring:message code="man.a.decline" text="Decline" /></button>
                                        </form>
                                    </div>
                                    <div class="col-md-2">
                                        <form action="/manage/delete" method="get">
                                            <input type="hidden" name="orderid" value="${order.id}" />
                                            <button class="btn btn-sm btn-danger search-btn" type="submit"><spring:message code="man.a.delete" text="Delete" /></button>
                                        </form>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

</div>
</body>
</html>