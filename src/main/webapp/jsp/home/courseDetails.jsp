<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/details.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/block.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<c:import url="../parts/header.jsp"/>

<div class="container details">
    <div class="row">
        <div class="pl-3 pt-3 pb-3">
            <c:if test = "${course.logoPicture == null}">
                <img src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="160px">
            </c:if>
            <c:if test = "${course.logoPicture != null}">
                <img class="rounded" src="/downloadCourseLogo/${course.id}" width="160px">
            </c:if>
        </div>
        <div class="col-9 pt-2">
            <h3>${course.title}</h3>
            <c:if test = "${course.paid}">
                <strong><em class="font">Course is paid - ${course.price} UAH</em></strong>
            </c:if>
            <c:if test = "${!course.paid}">
                <strong><em class="font">Course is free</em></strong>
            </c:if>
            <br>
            <span class="font">Topic
                <a class="black font" href="/allMaterials?topic=${course.chapter.topic.name}">
                    ${course.chapter.topic.name}</a>
                &#8594;
                <a class="black font" href="/allMaterials?topic=${course.chapter.topic.name}&title=${course.chapter.name}">
                    ${course.chapter.name}</a>
            </span> <br>
            <span class="font">${course.description}</span>
        </div>
    </div>
</div>

<c:if test = "${courseArticles != null && courseArticles != []}">
    <h3 class="w-75 m-auto p">Articles</h3>
    <div class="container">
        <div class="row d-flex justify-content-between">
            <c:forEach var="article" items="${courseArticles}">
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
                        <c:if test = "${isAbleToSee}">
                            <a class="btn btn-dark ml-3 mt-1 mb-3" href="/readArticle?id=${article.id}">Read</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

</body>
</html>