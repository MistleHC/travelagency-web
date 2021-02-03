<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="home_head.jsp">
    <jsp:param name="titleName" value="Customer details"/>
</jsp:include>
<body>
<div class="container-fluid">

    <jsp:include page="navbar.jsp"/>

    <div class="bg-light">
        <h1 class="text-center hd-t">Profile</h1><br>
        <div class="rowa">
            <h4 class="text-left">Name: ${customer.name}</h4>
            <h4 class="text-left">Email: ${customer.email}</h4>
        </div>
    </div>

    <div class="bg-light">
        <h1 class="text-center">Orders of ${customer.name}</h1><br>
        <c:choose>
            <c:when test="${empty orders}">
                <h4>User has no orders</h4>
            </c:when>
            <c:otherwise>
                <table class="table table-hover table-bordered">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Tour name</th>
                        <th scope="col">Tour type</th>
                        <th scope="col">Hotel type</th>
                        <th scope="col">Status</th>
                    </tr>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td>${order.id}</td>
                            <td>
                                    ${order.tour.name}
                            </td>
                            <td>
                                    ${order.tour.tourType.name}
                            </td>
                            <td>
                                    ${order.tour.hotelType.name}
                            </td>
                            <td>
                                    ${order.status.title}
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