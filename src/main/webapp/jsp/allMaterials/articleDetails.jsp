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
            <c:if test = "${article.logoPicture == null}">
                <img src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="160px">
            </c:if>
            <c:if test = "${article.logoPicture != null}">
                <img class="rounded" src="/downloadArticleLogo/${article.id}" width="160px">
            </c:if>
        </div>
        <div class="col-9 pt-2">
            <h3>${article.title}</h3>
            <span class="font">
                Author ${article.author.firstName} ${article.author.lastName}
            </span> <br>
            <c:if test = "${article.paid}">
                <strong><em class="font">Article is paid - ${article.price} UAH</em></strong>
            </c:if>
            <c:if test = "${!article.paid}">
                <strong><em class="font">Article is free</em></strong>
            </c:if>
            <br>
            <span class="font">Topic
                <a class="black font" href="/allMaterials?topic=${article.chapter.topic.name}">
                    ${article.chapter.topic.name}</a>
                &#8594;
                <a class="black font" href="/allMaterials?topic=${article.chapter.topic.name}&title=${article.chapter.name}">
                    ${article.chapter.name}</a>
            </span> <br>
            <span class="font">${article.description}</span> <br>
            <c:if test = "${isAbleToSee}">
                <a class="btn btn-dark mt-1 mb-3" href="/readArticle?id=${article.id}">Read</a>
            </c:if>
            <c:if test = "${!isAbleToSee}">
                <a class="btn btn-dark mt-1 mb-3" href="/buyArticle?id=${article.id}">Buy article</a>
            </c:if>
        </div>
    </div>
</div>

</body>
</html>