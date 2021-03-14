<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/cabinet.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<c:import url="../parts/header.jsp"/>

    <div class="container">
        <c:if test = "${user.profilePicture == null}">
            <td>
                <img class="photo" src="https://clipartart.com/images250_/default-profile-picture-clipart-6.png" width="200px">
            </td>
        </c:if>
        <c:if test = "${user.profilePicture != null}">
            <td>
                <img class="photo" src="/cabinet/download_photo?id=${user.id}" width="200px">
            </td>
        </c:if>
        <p>First name: ${user.firstName}</p>
        <p>Last name: ${user.lastName}</p>
        <p>Email: ${user.email}</p>
    </div>

    <a href="/cabinet/updateUser">Update user data</a> <br>
    <a href="/cabinet/createArticle">Create article</a> <br>
    <a href="/cabinet/createCourse">Create course</a> <br>
    <hr>

    <c:if test="${!empty courses}">
        <div class="container">
            <h4>Courses:</h4>
            <c:forEach var="course" items="${courses}">
                <div class="container article">
                    <div>
                        <c:if test = "${course.logoPicture == null}">
                            <td>
                                <img class="photo" src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="200px">
                            </td>
                        </c:if>
                        <c:if test = "${course.logoPicture != null}">
                            <td>
                                <img class="photo" src="/cabinet/downloadCourseLogo?id=${course.id}" width="200px">
                            </td>
                        </c:if>
                        <br>
                        <p>${course.title}</p>
                        <p>Place in the ranking: undefined</p>
                        <p>Number of likes: undefined</p>
                        <a href="/cabinet/editCourse?id=${course.id}">Edit</a>
                        <a href="/cabinet/articlesInCourse?id=${course.id}">Articles</a>
                        <c:if test = "${course.visibility == false}">
                            <a href="/cabinet/courseReaders?id=${course.id}">Readers</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

    <c:if test="${!empty articles}">
        <div class="container">
            <h4>Articles:</h4>
            <c:forEach var="article" items="${articles}">
                <div class="container article">
                    <div>
                        <c:if test = "${article.logoPicture == null}">
                            <td>
                                <img class="photo" src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="200px">
                            </td>
                        </c:if>
                        <c:if test = "${article.logoPicture != null}">
                            <td>
                                <img class="photo" src="/cabinet/downloadArticleLogo?id=${article.id}" width="200px">
                            </td>
                        </c:if>
                        <br>
                        <p>${article.title}</p>
                        <p>Place in the ranking: undefined</p>
                        <p>Number of likes: undefined</p>
                        <a href="/cabinet/editArticle?id=${article.id}">Edit</a>
                        <c:if test = "${article.visibility == false}">
                            <a href="/cabinet/articleReaders?id=${article.id}">Readers</a>
                        </c:if>
                        <c:if test = "${article.giveAnswers == true}">
                            <a href="/cabinet/ArticleAnswers?id=${article.id}">Answers</a>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>
    </c:if>

</body>
</html>