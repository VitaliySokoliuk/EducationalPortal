<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<c:import url="/jsp/parts/header.jsp"/>

<form action="/adminPanel/deleteMaterials" method="get">
    <label>Input title or chapter</label>
    <input type="text" name = "search" class="form-control">
    <button type="submit" class="btn btn-dark">Find materials</button><br>
</form>

<c:if test="${!empty courses}">
    <div class="container">
        <h4>Courses:</h4>
        <table class="table table-striped">
            <tbody>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <c:if test = "${course.logoPicture == null}">
                        <td>
                            <img class="photo" src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="100px">
                        </td>
                    </c:if>
                    <c:if test = "${course.logoPicture != null}">
                        <td>
                            <img class="photo" src="/downloadCourseLogo/${course.id}" width="100px">
                        </td>
                    </c:if>
                    <td>${course.title}</td>
                    <td>${course.description}</td>
                    <td>${course.chapter.topic.name}&#8594;${course.chapter.name}</td>
                    <td>
                        <a href="/adminPanel/deleteMaterials/delCourse?courseId=${course.id}&search=${search}">delete course</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

<c:if test="${!empty articles}">
    <div class="container">
        <h4>Articles:</h4>
        <table class="table table-striped">
            <tbody>
            <c:forEach var="article" items="${articles}">
                <tr>
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
                    <td>${article.title}</td>
                    <td>${article.description}</td>
                    <td>${article.chapter.topic.name}&#8594;${article.chapter.name}</td>
                    <td>
                        <a href="/adminPanel/deleteMaterials/delArticle?articleId=${article.id}&search=${search}">delete article</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

</body>
</html>