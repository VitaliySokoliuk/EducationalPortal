<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/search.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/block.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<c:import url="../parts/header.jsp"/>

<div class="s01">
    <form action="/allMaterials" method="get">
        <fieldset>
            <legend>Find materials</legend>
        </fieldset>
        <div class="inner-form">
            <div class="input-field first-wrap">
                <select name="topic">
                    <c:if test="${topicName != null}">
                        <option value="${topicName}">${topicName}</option>
                    </c:if>
                    <option value="">All topics</option>
                    <c:forEach var="topic" items="${topics}">
                        <option value="${topic.name}">${topic.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="input-field second-wrap">
                <input type="text" value="${title}" name="title" placeholder="Input title or chapter"/>
            </div>
            <div class="input-field third-wrap">
                <button class="btn-search" type="submit">Search</button>
            </div>
        </div>
    </form>
</div>

<c:if test="${!empty courses}">
    <h3 class="w-75 m-auto p">Courses</h3>
    <div class="container">
        <div class="row d-flex justify-content-between">
            <c:forEach var="course" items="${courses}">
                <div class="w-47 m-2">
                    <div class="row">
                        <div class="col-3 pt-3 bl">
                            <c:if test = "${course.logoPicture == null}">
                                <img src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="110px">
                            </c:if>
                            <c:if test = "${course.logoPicture != null}">
                                <img class="rounded" src="/downloadCourseLogo/${course.id}" width="110px">
                            </c:if>
                        </div>
                        <div class="col-9 pt-2 bl">
                            <h4>${course.title}</h4>
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

<c:if test="${!empty articles}">
    <h3 class="w-75 m-auto p">Articles</h3>
    <div class="container">
        <div class="row d-flex justify-content-between">
            <c:forEach var="article" items="${articles}">
                <div class="w-47 m-2">
                    <div class="row">
                        <div class="col-3 pt-3 bl">
                            <c:if test = "${article.logoPicture == null}">
                                <img src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="110px">
                            </c:if>
                            <c:if test = "${article.logoPicture != null}">
                                <img class="rounded" src="/downloadArticleLogo/${article.id}" width="110px">
                            </c:if>
                        </div>
                        <div class="col-9 pt-2 bl">
                            <h4>${article.title}</h4>
                            <span>Author
                                <span style="font-size: 16px">${article.author.firstName} ${article.author.lastName}</span>
                            </span> <br>
                            <c:if test = "${article.paid}">
                                <strong><em>Article is paid - ${article.price} UAH</em></strong>
                            </c:if>
                            <c:if test = "${!article.paid}">
                                <strong><em>Article is free</em></strong>
                            </c:if>
                            <br>
                            <span>Topic:
                                <a class="text-dark" href="/allMaterials?topic=${article.chapter.topic.name}">
                                        ${article.chapter.topic.name}</a>
                                &#8594;
                                <a class="text-dark" href="/allMaterials?topic=${article.chapter.topic.name}&title=${article.chapter.name}">
                                        ${article.chapter.name}</a>
                            </span>
                        </div>
                    </div>
                    <div class="bl row">
                        <span class="col-12">${article.description}</span>
                        <c:if test = "${!article.paid}">
                            <a class="btn btn-dark ml-3 mt-1 mb-3" href="/readArticle?id=${article.id}">Read</a>
                        </c:if>
                        <c:if test = "${isUserPresent == true && !article.paid}">
                            <a class="btn btn-dark ml-3 mt-1 mb-3" href="/addAToList?id=${article.id}">Add to my list</a>
                        </c:if>
                        <c:if test = "${article.paid}">
                            <a class="btn btn-dark ml-3 mt-1 mb-3" href="/allMaterials/articleDetails?id=${article.id}">Article details</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

</body>
</html>