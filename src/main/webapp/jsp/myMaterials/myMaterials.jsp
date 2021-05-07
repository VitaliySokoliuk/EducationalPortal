<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/base.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/block.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
<body>
<c:import url="../parts/header.jsp"/>

<c:if test="${!empty coursesUserBought}">
    <h3 class="w-75 m-auto p">Courses which you bought</h3>
    <div class="container">
        <div class="row d-flex justify-content-between">
            <c:forEach var="course" items="${coursesUserBought}">
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
                                <a class="text-dark" href="/allMaterials?topic=${course.chapter.topic.name}">
                                        ${course.chapter.topic.name}</a>
                                &#8594;
                                <a class="text-dark" href="/allMaterials?topic=${course.chapter.topic.name}&title=${course.chapter.name}">
                                        ${course.chapter.name}</a>
                            </span>
                        </div>
                    </div>
                    <div class="bl row">
                        <span class="col-12">${course.description}</span>
                        <a class="btn btn-dark ml-3 mr-3 mt-1 mb-3" href="/courseDetails?id=${course.id}">Details</a>
                        <a class="col align-self-center" href="/myMaterials/likeC?courseId=${course.id}">
                            <c:if test = "${course.meLiked}">
                                <i class="fas fa-heart"></i>
                            </c:if>
                            <c:if test = "${!course.meLiked}">
                                <i class="far fa-heart"></i>
                            </c:if>
                                ${course.likes}
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

<c:if test="${!empty coursesAddedByUser}">
    <h3 class="w-75 m-auto p">Courses to which you have joined personally</h3>
    <div class="container">
        <div class="row d-flex justify-content-between">
            <c:forEach var="course" items="${coursesAddedByUser}">
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
                                <a class="text-dark" href="/allMaterials?topic=${course.chapter.topic.name}">
                                        ${course.chapter.topic.name}</a>
                                &#8594;
                                <a class="text-dark" href="/allMaterials?topic=${course.chapter.topic.name}&title=${course.chapter.name}">
                                        ${course.chapter.name}</a>
                            </span>
                        </div>
                    </div>
                    <div class="bl row">
                        <span class="col-12">${course.description}</span>
                        <a class="btn btn-dark ml-3 mr-3 mt-1 mb-3" href="/courseDetails?id=${course.id}">Details</a>
                        <a class="col align-self-center" href="/myMaterials/likeC?courseId=${course.id}">
                            <c:if test = "${course.meLiked}">
                                <i class="fas fa-heart"></i>
                            </c:if>
                            <c:if test = "${!course.meLiked}">
                                <i class="far fa-heart"></i>
                            </c:if>
                                ${course.likes}
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

<c:if test="${!empty articlesUserBought}">
    <h3 class="w-75 m-auto p">Articles which you bought</h3>
    <div class="container">
        <div class="row d-flex justify-content-between">
            <c:forEach var="article" items="${articlesUserBought}">
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
                        <a class="btn btn-dark ml-3 mr-3 mt-1 mb-3" href="/readArticle?id=${article.id}">Read</a>
                        <a class="col align-self-center" href="/myMaterials/likeA?articleId=${article.id}">
                            <c:if test = "${article.meLiked}">
                                <i class="fas fa-heart"></i>
                            </c:if>
                            <c:if test = "${!article.meLiked}">
                                <i class="far fa-heart"></i>
                            </c:if>
                                ${article.likes}
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

<c:if test="${!empty articlesAddedByUser}">
    <h3 class="w-75 m-auto p">Articles to which you have joined personally</h3>
    <div class="container">
        <div class="row d-flex justify-content-between">
            <c:forEach var="article" items="${articlesAddedByUser}">
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
                        <a class="btn btn-dark ml-3 mr-3 mt-1 mb-3" href="/readArticle?id=${article.id}">Read</a>
                        <a class="col align-self-center" href="/myMaterials/likeA?articleId=${article.id}">
                            <c:if test = "${article.meLiked}">
                                <i class="fas fa-heart"></i>
                            </c:if>
                            <c:if test = "${!article.meLiked}">
                                <i class="far fa-heart"></i>
                            </c:if>
                                ${article.likes}
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</c:if>

</body>
</html>