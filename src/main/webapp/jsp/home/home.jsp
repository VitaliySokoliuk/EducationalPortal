<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/block.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<c:import url="../parts/header.jsp"/>

<div class="inspiration">
    <img src="https://i.postimg.cc/tRWcj3zV/Edu-Portal-logo3.png" alt="logo" height="275px">
    <div class="quote">I hope you'll find EduPortal helpful no matter if you're a student that needs a boost of motivation
        or if you're a teacher of any kind that wants to inspire and make a change in the lives of others.</div>
</div>

<c:if test="${!empty courses}">
    <h3 class="h3">The most popular courses <a href="/allMaterials" class="span"> View all</a> </h3>
    <div class="container">
        <div class="row d-flex justify-content-between">
            <c:forEach var="course" items="${courses}">
                <div class="w-30 m-2">
                    <div class="row">
                        <div class="col-4 pt-3 bl">
                            <c:if test = "${course.logoPicture == null}">
                                <img src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="100px">
                            </c:if>
                            <c:if test = "${course.logoPicture != null}">
                                <img class="rounded" src="/downloadCourseLogo/${course.id}" width="100px">
                            </c:if>
                        </div>
                        <div class="col-8 pt-2 bl">
                            <h5>${course.title}</h5>
                            <span>Author
                                <span style="font-size: 16px">${course.author.firstName} ${course.author.lastName}</span>
                            </span> <br>
                            <c:if test = "${course.paid}">
                                <strong><em>Course is paid - ${course.price} UAH</em></strong>
                            </c:if>
                            <c:if test = "${!course.paid}">
                                <strong><em>Course is free</em></strong>
                            </c:if>
                            <br>
                            <span>Topic:
                                <a class="black" href="/allMaterials?topic=${course.chapter.topic.name}">
                                        ${course.chapter.topic.name}</a>
                                &#8594;
                                <a class="black" href="/allMaterials?topic=${course.chapter.topic.name}&title=${course.chapter.name}">
                                        ${course.chapter.name}</a>
                            </span>
                        </div>
                    </div>
                    <div class="bl row">
                        <span class="col-12">${course.description}</span>
                        <a class="btn btn-dark ml-3 mr-3 mt-1 mb-3" href="/courseDetails?id=${course.id}">Details</a>
                        <c:if test = "${isUserPresent == true && !course.paid}">
                            <a class="btn btn-dark mt-1 mb-3" href="/addCToList?id=${course.id}">Add to my list</a>
                        </c:if>
                        <c:if test = "${isUserPresent == true && course.paid}">
                            <a class="btn btn-dark mt-1 mb-3" href="/buyCourse?id=${course.id}">Buy course</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

</body>
</html>