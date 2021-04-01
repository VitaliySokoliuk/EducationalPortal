<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<c:import url="../parts/header.jsp"/>

<div class="container">
    <div>
        <c:if test = "${article.logoPicture == null}">
            <td>
                <img class="photo" src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="200px">
            </td>
        </c:if>
        <c:if test = "${article.logoPicture != null}">
            <td>
                <img class="photo" src="/downloadArticleLogo/${article.id}" width="200px">
            </td>
        </c:if>
        <br>
        <p>${article.title}</p>
        <p>${article.description}</p>
        <p>Author ${author.firstName} ${author.lastName} </p>
        <c:if test = "${article.paid}">
            <p>Article is paid - ${article.price} UAH</p>
        </c:if>
        <c:if test = "${!article.paid}">
            <p>Article is free</p>
        </c:if>
        <p>Topic: <a href="/allMaterials?topic=${article.chapter.topic.name}">
            ${article.chapter.topic.name}</a> &#8594; ${article.chapter.name}</p>
        <c:if test = "${isAbleToSee}">
            <a href="/readArticle?id=${article.id}">Read</a>
        </c:if>
        <c:if test = "${!isAbleToSee}">
            <a href="/buyArticle?id=${article.id}">Buy article</a>
        </c:if>
    </div>
</div>



</body>
</html>