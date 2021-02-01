<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <jsp:include page="sections/home_head.jsp">
        <jsp:param name="titleName" value="Home page"/>
    </jsp:include>
<body>
<div class="container-fluid">
    <jsp:include page="sections/navbar.jsp"/>



    <!-- SEARCH COMPONENT START -->
        <div class="row2">
            <form action="" class="card card-sm border-light" method="get">
                <div class="card-body row no-gutters align-items-center">
                    <div class="col-auto">
                        <i class="fas fa-search h4 text-body"></i>
                    </div>
                    <div class="col">
                        <label>
                            <select class="form-select" name="country">
                                <c:forEach items="${countries}" var="country">
                                    <option value="${country.name}">${country.name}</option>
                                </c:forEach>
                            </select>
                        </label>
                    </div>
                    <div class="col-auto">
                        <button class="btn btn-lg btn-success search-btn" type="submit">Search</button>
                    </div>
                </div>
            </form>
        </div>
    <!-- SEARCH COMPONENT END -->

    <div class="row">

        <c:choose>
            <c:when test="${empty tours}">
                <h4>No tours available right now :(</h4>
            </c:when>
            <c:otherwise>
                <c:forEach items="${tours}" var="tour">
                <div class="col-md-3 item-row">
                    <div class="ibox">
                        <div class="ibox-content product-box">
                            <div class="product-imitation">
                                [ INFO ]
                            </div>
                            <div class="product-desc">
                    <span class="product-price">
                            ${tour.price} UAH
                    </span>
                                <small class="text-muted">${tour.tourType.name}</small>
                                <a href="#" class="product-name">${tour.name}</a>

                                <div class="small m-t-xs">
                                        Max group size: ${tour.peoples}
                                </div>
                                <div class="small m-t-xs">
                                        ${tour.description}
                                </div>
                                <div class="m-t text-righ item-btn">
                                    <a href="#" class="btn btn-sm btn-outline btn-primary">Info<i class="fa fa-long-arrow-right"></i> </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>










<%--                <table class="table table-hover table-bordered">--%>
<%--                    <tr>--%>
<%--                        <th scope="col">ID</th>--%>
<%--                        <th scope="col">Name</th>--%>
<%--                        <th scope="col">Description</th>--%>
<%--                        <th scope="col">Country</th>--%>
<%--                        <th scope="col">Peoples</th>--%>
<%--                        <th scope="col">Tour Type</th>--%>
<%--                        <th scope="col">Hotel Type</th>--%>
<%--                        <th scope="col">Action</th>--%>
<%--                    </tr>--%>
<%--                    <c:forEach items="${tours}" var="tour">--%>
<%--                        <tr>--%>
<%--                            <td>${tour.id}</td>--%>
<%--                            <td>${tour.name}</td>--%>
<%--                            <td>${tour.description}</td>--%>
<%--                            <td>${tour.country}</td>--%>
<%--                            <td>${tour.peoples}</td>--%>
<%--                            <td>${tour.tourType.name}</td>--%>
<%--                            <td>${tour.hotelType.name}</td>--%>
<%--                            <td>--%>
<%--                                <a href="<c:url value="/book/${tour.id}"/>"--%>
<%--                                   class="btn btn-outline-secondary btn-sm">Book room in the hotel</a>--%>
<%--                            </td>--%>
<%--                        </tr>--%>
<%--                    </c:forEach>--%>
<%--                </table>--%>
            </c:otherwise>
        </c:choose>
    </div>


</div>
</body>
</html>