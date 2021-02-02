<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01
Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

    <link href="${pageContext.request.contextPath}/resources/styles/home.css" rel="stylesheet">

    <script type="text/javascript">
        var imageURLs = [
            "${pageContext.request.contextPath}/resources/images/1.jpg"
            , "${pageContext.request.contextPath}/resources/images/2.jpg"
            , "${pageContext.request.contextPath}/resources/images/3.jpg"
            , "${pageContext.request.contextPath}/resources/images/4.jpg"
            , "${pageContext.request.contextPath}/resources/images/5.jpg"
            , "${pageContext.request.contextPath}/resources/images/6.jpg"
            , "${pageContext.request.contextPath}/resources/images/7.jpg"
            , "${pageContext.request.contextPath}/resources/images/8.jpg"
            , "${pageContext.request.contextPath}/resources/images/9.jpg"
            , "${pageContext.request.contextPath}/resources/images/10.jpg"
            , "${pageContext.request.contextPath}/resources/images/11.jpg"
            , "${pageContext.request.contextPath}/resources/images/12.jpg"
        ];
        function getImageTag() {
            var img = '<img src=\"';
            var randomIndex = Math.floor(Math.random() * imageURLs.length);
            img += imageURLs[randomIndex];
            img += '\" alt=\"Item image\"';
            img += '\" class=\"img-i\"/>';
            return img;
        }
    </script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

    <title>${param.titleName}</title>
</head>