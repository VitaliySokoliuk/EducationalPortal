<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/table.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<c:import url="../parts/header.jsp"/>

<c:if test = "${articlesInCourse != null && articlesInCourse != []}">
    <h3 class="w-75 m-auto p">Articles in course</h3>
    <div class="w-75 m-auto opacity">
        <table class="table table-striped">
            <tbody>
            <c:forEach var="article" items="${articlesInCourse}">
                <tr>
                    <td>${article.title}</td>
                    <td><a class="btn btn-dark" href="/cabinet/contentArticle?id=${article.id}">Look</a></td>
                    <td><a class="btn btn-dark" href="/cabinet/editArticle?id=${article.id}">Update</a></td>
                    <td><a class="btn btn-dark" href="/cabinet/delArticleFromCourse?aId=${article.id}&cId=${courseId}">Delete from course</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
<c:if test = "${articlesInCourse == null || articlesInCourse == []}">
    <h3 class="w-75 m-auto p">This course does not contain any article</h3>
</c:if>
<c:if test = "${anotherArticle != null && anotherArticle != []}">
    <h3 class="w-75 m-auto p">Another articles</h3>
    <div class="w-75 m-auto opacity">
        <table class="table table-striped">
            <tbody>
            <c:forEach var="article" items="${anotherArticle}">
                <tr>
                    <td>${article.title}</td>
                    <td><a class="btn btn-dark" href="/cabinet/contentArticle?id=${article.id}">Look</a></td>
                    <td><a class="btn btn-dark" href="/cabinet/editArticle?id=${article.id}">Update</a></td>
                    <td><a class="btn btn-dark" href="/cabinet/addArticleToCourse?aId=${article.id}&cId=${courseId}">Add to course</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

</body>
</html>