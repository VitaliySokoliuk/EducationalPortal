<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
<body>
<c:import url="../parts/header.jsp"/>

<c:if test="${!empty coursesAddedByAuthor}">
    <div class="container">
        <h4>Courses which you have bought:</h4>
        <c:forEach var="course" items="${coursesAddedByAuthor}">
            <div class="container article">
                <div>
                    <c:if test = "${course.logoPicture == null}">
                        <td>
                            <img class="photo" src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="200px">
                        </td>
                    </c:if>
                    <c:if test = "${course.logoPicture != null}">
                        <td>
                            <img class="photo" src="/downloadCourseLogo/${course.id}" width="200px">
                        </td>
                    </c:if>
                    <br>
                    <p>${course.title}</p>
                    <p>${course.description}</p>
                    <a href="/courseDetails?id=${course.id}">Details</a>
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
</c:if>

<c:if test="${!empty coursesAddedByUser}">
    <div class="container">
        <h4>Courses to which you have been added personally:</h4>
        <c:forEach var="course" items="${coursesAddedByUser}">
            <div class="container article">
                <div>
                    <c:if test = "${course.logoPicture == null}">
                        <td>
                            <img class="photo" src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="200px">
                        </td>
                    </c:if>
                    <c:if test = "${course.logoPicture != null}">
                        <td>
                            <img class="photo" src="/downloadCourseLogo/${course.id}" width="200px">
                        </td>
                    </c:if>
                    <br>
                    <p>${course.title}</p>
                    <p>${course.description}</p>
                    <a href="/courseDetails?id=${course.id}">Details</a>
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
</c:if>

<c:if test="${!empty articlesAddedByAuthor}">
    <div class="container">
        <h4>Articles which you have bought:</h4>
        <c:forEach var="article" items="${articlesAddedByAuthor}">
            <div class="container article">
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
                    <a href="/readArticle?id=${article.id}">Read</a>
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
</c:if>

<c:if test="${!empty articlesAddedByUser}">
    <div class="container">
        <h4>Articles to which you have been added personally:</h4>
        <c:forEach var="article" items="${articlesAddedByUser}">
            <div class="container article">
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
                    <a href="/readArticle?id=${article.id}">Read</a>
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
</c:if>


</body>
</html>