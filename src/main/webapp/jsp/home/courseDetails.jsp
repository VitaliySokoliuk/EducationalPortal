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
<p>Topic: ${course.chapter.topic.name} &#8594; ${course.chapter.name}</p>
<c:if test = "${courseArticles != null && courseArticles != []}">
    <div class="container">
        <h3>Articles in course</h3>
        <c:forEach var="article" items="${courseArticles}">
            <div class="container article">
                <div>
                    <c:if test = "${article.logoPicture == null}">
                        <td>
                            <img class="photo" src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="100px">
                        </td>
                    </c:if>
                    <c:if test = "${article.logoPicture != null}">
                        <td>
                            <img class="photo" src="/downloadArticleLogo/${article.id}" width="100px">
                        </td>
                    </c:if>
                    <br>
                    <p>${article.title}</p>
                    <p>${article.description}</p>
                    <c:if test = "${isAbleToSee}">
                        <a href="/readArticle?id=${article.id}">Read</a>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>

</body>
</html>