<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="home_head.jsp">
    <jsp:param name="titleName" value="Management"/>
</jsp:include>
<body>
<div class="container-fluid">

    <jsp:include page="navbar.jsp"/>

    <div class="bg-light">
        <h1 class="text-center">Orders</h1><br>
        <c:choose>
            <c:when test="${empty orders}">
                <h4>There no orders</h4>
            </c:when>
            <c:otherwise>
                <table class="table table-hover table-bordered">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Customer ID</th>
                        <th scope="col">Customer login</th>
                        <th scope="col">Tour name</th>
                        <th scope="col">Actions</th>
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
                                <div class="card-body row no-gutters align-items-center">
                                    <div class="col-md-2">
                                        <form action="/manage/paid" method="get">
                                            <input type="hidden" name="orderid" value="${order.id}" />
                                            <button class="btn btn-sm btn-success search-btn" type="submit">Paid</button>
                                        </form>
                                    </div>
                                    <div class="col-md-2">
                                        <form action="/manage/decline" method="get">
                                            <input type="hidden" name="orderid" value="${order.id}" />
                                            <button class="btn btn-sm btn-success search-btn" type="submit">Decline</button>
                                        </form>
                                    </div>
                                    <div class="col-md-2">
                                        <form action="/manage/delete" method="get">
                                            <input type="hidden" name="orderid" value="${order.id}" />
                                            <button class="btn btn-sm btn-success search-btn" type="submit">Delete</button>
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