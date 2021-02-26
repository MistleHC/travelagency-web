<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<jsp:include page="home_head.jsp">
    <jsp:param name="titleName" value="Customer details"/>
</jsp:include>
<body>
<div class="container-fluid">

    <jsp:include page="navbar.jsp"/>

    <div class="bg-light">
        <h1 class="text-center hd-t"><spring:message code="profile.title" text="Profile" /></h1><br>
        <div class="row-p col" id="block-p1">
            <h4 class="text-left"><spring:message code="profile.login" text="Login:" /> ${customer.name}</h4>
            <h4 class="text-left"><spring:message code="profile.name" text="Full name:" /> ${customer.fullName}</h4>
            <h4 class="text-left"><spring:message code="profile.email" text="Email:" /> ${customer.email}</h4>
            <h4 class="text-left"><spring:message code="profile.about" text="About me:" /> ${customer.aboutMe}</h4>
        </div>
        <div class="col" id="block-p2">
            <a href="#" data-toggle="modal" data-target="#modaledit" class="btn btn-lg btn-outline btn-primary"><spring:message code="profile.edit" text="Edit" /><i class="fa fa-long-arrow-right"></i> </a>
        </div>
    </div>

    <div class="bg-light">
        <h1 class="text-center"><spring:message code="profile.orders" text="Orders" /></h1><br>
        <c:choose>
            <c:when test="${empty orders}">
                <h4>User has no orders</h4>
            </c:when>
            <c:otherwise>
                <table class="table table-hover table-bordered">
                    <tr>
                        <th scope="col"><spring:message code="profile.o.tourid" text="ID" /></th>
                        <th scope="col"><spring:message code="profile.o.name" text="Tour name" /></th>
                        <th scope="col"><spring:message code="profile.o.type" text="Tour type" /></th>
                        <th scope="col"><spring:message code="profile.o.hotel" text="Hotel type" /></th>
                        <th scope="col"><spring:message code="profile.o.price" text="Price" /></th>
                        <th scope="col"><spring:message code="profile.o.status" text="Status" /></th>
                        <th scope="col"><spring:message code="profile.o.actions" text="Actions" /></th>
                    </tr>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <td>
                                    ${order.id}
                            </td>
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
                                    ${order.tour.price - (order.tour.price * order.tour.discount / 100)} UAH
                            </td>
                            <td>
                                    ${order.status.title}
                            </td>
                            <td>
                                <c:if test = "${order.status.id == 1}">
                                    <form action="/order/delete" method="get">
                                        <input type="hidden" name="orderid" value="${order.id}" />
                                        <button class="btn btn-sm btn-success search-btn" type="submit"><spring:message code="profile.o.cancel" text="Cancel" /></button>
                                    </form>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

    <!-- Modal - Tour creation -->
    <div class="modal fade" id="modaledit">
        <div class="modal-dialog modal-dialog-centered">
            <form action="${pageContext.request.contextPath}/profile/edit" method="post">
                <div class="modal-content">
                    <!-- Modal Header -->
                    <div class="modal-header">
                        <h4 class="modal-title"><spring:message code="profile.e.edit" text="Edit profile" /></h4> <button type="button" class="close cl-btn" data-dismiss="modal">&times;</button>
                    </div> <!-- Modal body -->
                    <div class="modal-body">
                        <div>
                            <div>
                                <div class="product-desc">
                                    <label for="userFullName" class="form-label"><spring:message code="profile.name" text="Full name:" /></label>
                                    <input name="userFullName" type="text" class="form-control" id="userFullName" placeholder="Name"/>
                                    <label for="userDescription" class="form-label"><spring:message code="profile.about" text="About me:" /></label>
                                    <textarea rows = "4" cols = "60" name = "userDescription" id="userDescription"></textarea><br>
                                </div>
                            </div>
                        </div>
                    </div> <!-- Modal footer -->
                    <sec:authorize access="isAuthenticated()">
                        <div class="modal-footer">
                            <button class="btn btn-lg btn-success search-btn" type="submit"><spring:message code="profile.edit" text="Edit" /></button>
                        </div>
                    </sec:authorize>
                </div>
            </form>
        </div>
    </div>

</div>
</body>
</html>