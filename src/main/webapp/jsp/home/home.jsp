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
                            <img class="photo" src="/downloadCourseLogo/${course.id}" width="200px">
                        </td>
                    </c:if>
                    <br>
                    <p>${course.title}</p>
                    <p>${course.description}</p>
                    <a href="/courseDetails?id=${course.id}">Details</a>
                    <c:if test = "${isUserPresent == true}">
                        <a href="/addToList?id=${course.id}">Add to my list</a>
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
                            <img class="photo" src="/downloadArticleLogo/${article.id}" width="200px">
                        </td>
                    </c:if>
                    <br>
                    <p>${article.title}</p>
                    <p>${article.description}</p>
                    <a href="/readArticle?id=${article.id}">Read</a>
                    <c:if test = "${isUserPresent == true}">
                        <a href="/addToList?id=${article.id}">Add to my list</a>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>


</body>
</html>