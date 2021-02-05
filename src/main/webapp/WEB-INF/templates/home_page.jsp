<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <jsp:include page="home_head.jsp">
        <jsp:param name="titleName" value="Home page"/>
    </jsp:include>
<body>

<div class="container-fluid">
    <jsp:useBean id="pagedListHolder" scope="request" type="org.springframework.beans.support.PagedListHolder" />
    <c:url value="/" var="pagedLink">
        <c:param name="p" value="~" />
    </c:url>

    <jsp:include page="navbar.jsp"/>



        <div class="row2">
            <form action="" class="card card-sm border-light" method="get">
                <div class="card-body row no-gutters align-items-center">
                    <div class="col-auto">
                        <i class="fas fa-search h4 text-body"></i>
                    </div>
                    <div class="col">
                        <h3 class="h-in">Country:</h3>
                        <label>
                            <select class="form-select form-select-lg sel-tx" name="country">
                                <option value="all">All</option>
                                <c:forEach items="${countries}" var="country">
                                    <option value="${country.name}">${country.name}</option>
                                </c:forEach>
                            </select>
                        </label>
                        <h3 class="h-in">Hotel:</h3>
                        <label>
                            <select class="form-select form-select-lg sel-tx" name="hotel">
                                <option value="all">All</option>
                                <c:forEach items="${hotels}" var="hotel">
                                    <option value="${hotel.name}">${hotel.name}</option>
                                </c:forEach>
                            </select>
                        </label>
                        <h3 class="h-in">Price: </h3>
                        <label>
                            <input type="text" class="form-control" name="lowerprice" placeholder="More than (UAH)" pattern="[0-9]{0,10}">
                        </label>
                        <label>
                            <input type="text" class="form-control" name="higherprice" placeholder="Less than (UAH)" pattern="[0-9]{0,10}">
                        </label>
                        <h3 class="h-in">Group size:</h3>
                        <label>
                            <input type="text" class="form-control" name="lowergroup" placeholder="Less than..." pattern="[0-9]{0,2}">
                        </label>
                    </div>
                    <div class="col-auto">
                        <button class="btn btn-lg btn-success search-btn" type="submit">Search</button>
                    </div>
                </div>
            </form>
        </div>

    <div class="row">

        <c:choose>
            <c:when test="${empty tours}">
                <h4>No tours available right now :(</h4>
            </c:when>
            <c:otherwise>
                <c:forEach items="${pagedListHolder.pageList}" var="tour">

                    <!-- Modal -->
                    <div class="modal fade" id="modal${tour.id}">
                        <div class="modal-dialog modal-dialog-centered">
                            <div class="modal-content">
                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title">${tour.name}</h4> <button type="button" class="close cl-btn" data-dismiss="modal">&times;</button>
                                </div> <!-- Modal body -->
                                <div class="modal-body">
                                    <div>
                                        <div>
                                            <div class="product-imitation">
                                                <script type="text/javascript">
                                                    document.write(getImageTag());
                                                </script>
                                            </div>
                                            <div class="product-desc">
                                                <span class="product-price">
                                                        ${tour.price} UAH
                                                </span>
                                                <h4 class="pro-name">${tour.name}</h4><br>
                                                <p class="prod-name">Tour type: ${tour.tourType.name}</p>
                                                <p class="prod-name">Hotel type: ${tour.hotelType.name}</p>
                                                <p class="prod-name">Max group size: ${tour.peoples}</p><br>
                                                <p class="prod-name">${tour.description}</p><br>
                                                <p class="prod-name"></p>
                                                <p class="prod-name">Price: ${tour.price}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div> <!-- Modal footer -->
                                <sec:authorize access="isAuthenticated()">
                                <div class="modal-footer">
                                    <form action="/order" method="get">
                                        <input type="hidden" name="tourid" value="${tour.id}" />
                                        <button class="btn btn-lg btn-success search-btn" type="submit">Buy</button>
                                    </form>
                                    <sec:authorize access="hasAnyRole('MANAGER', 'ADMIN')">
                                        <form action="/tour/hot" method="get">
                                            <input type="hidden" name="tourid" value="${tour.id}" />
                                            <button class="btn btn-lg btn-warning search-btn" type="submit">Set as hot!</button>
                                        </form>
                                        <form action="/tour/de-hot" method="get">
                                            <input type="hidden" name="tourid" value="${tour.id}" />
                                            <button class="btn btn-lg btn-warning search-btn" type="submit">Set as not hot!</button>
                                        </form>
                                    </sec:authorize>
                                    <sec:authorize access="hasAnyRole('ADMIN')">
                                        <form action="/tour/delete" method="get">
                                            <input type="hidden" name="tourid" value="${tour.id}" />
                                            <button class="btn btn-lg btn-danger search-btn" type="submit">Delete</button>
                                        </form>
                                    </sec:authorize>
                                </div>
                                </sec:authorize>
                            </div>
                        </div>
                    </div>

                <div class="col-md-3 item-row">
                    <div class="ibox">
                        <div class="ibox-content product-box">
                            <div data-toggle="modal" data-target="#modal${tour.id}" class="product-imitation" id="imgcont">
                                <script type="text/javascript">
                                    document.write(getImageTag());
                                </script>
                            </div>
                            <div class="product-desc">
                    <span class="product-price">
                            ${tour.price} UAH
                    </span>
                                <small class="text-muted">${tour.tourType.name}</small>
                                <a href="#" data-toggle="modal" data-target="#modal${tour.id}" class="product-name">${tour.name}</a>

                                <div class="small m-t-xs">
                                        Max group size: ${tour.peoples}
                                </div>
                                <div class="small m-t-xs">
                                        ${tour.description}
                                </div>
                                <div class="m-t text-righ item-btn">
                                    <a href="#" data-toggle="modal" data-target="#modal${tour.id}" class="btn btn-sm btn-outline btn-primary">Info<i class="fa fa-long-arrow-right"></i> </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                </c:forEach>
            <div class="pagging">
                <tg:paging pagedListHolder="${pagedListHolder}" pagedLink="${pagedLink}" />
            </div>
            </c:otherwise>
        </c:choose>
    </div>

</div>

</body>
</html>