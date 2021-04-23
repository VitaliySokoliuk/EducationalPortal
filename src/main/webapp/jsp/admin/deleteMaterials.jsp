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

<div class = "container w65 mt-3 mb-3">
    <h4>Delete materials</h4>
    <form action="/adminPanel/deleteMaterials" method="get">
        <label>Input title or chapter</label>
        <input type="text" name = "search" placeholder="Input title or chapter" class="form-control">
        <button type="submit" class="btn btn-dark mt-2">Find materials</button><br>
    </form>
</div>

<c:if test = "${!empty courses}">
    <h3 class="w-75 m-auto p">Courses</h3>
    <div class="w-75 m-auto opacity">
        <table class="table table-striped">
            <thead>
            <tr>
                <td><strong>Logo</strong></td>
                <td><strong>Title</strong></td>
                <td><strong>Topic and chapter</strong></td>
                <td> </td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="course" items="${courses}">
                <tr>
                    <c:if test = "${course.logoPicture == null}">
                        <td>
                            <img class="rounded" src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="50px">
                        </td>
                    </c:if>
                    <c:if test = "${course.logoPicture != null}">
                        <td>
                            <img class="rounded" src="/downloadCourseLogo/${course.id}" width="50px">
                        </td>
                    </c:if>
                    <td>${course.title}</td>
                    <td>${course.chapter.topic.name}&#8594;${course.chapter.name}</td>
                    <td>
                        <a class="black" href="/adminPanel/deleteMaterials/delCourse?courseId=${course.id}&search=${search}">delete course</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

<c:if test = "${!empty articles}">
    <h3 class="w-75 m-auto p">Articles</h3>
    <div class="w-75 m-auto opacity">
        <table class="table table-striped">
            <thead>
            <tr>
                <td><strong>Logo</strong></td>
                <td><strong>Title</strong></td>
                <td><strong>Topic and chapter</strong></td>
                <td> </td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="article" items="${articles}">
                <tr>
                    <c:if test = "${article.logoPicture == null}">
                        <td>
                            <img class="rounded" src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="50px">
                        </td>
                    </c:if>
                    <c:if test = "${article.logoPicture != null}">
                        <td>
                            <img class="rounded" src="/downloadArticleLogo/${article.id}" width="50px">
                        </td>
                    </c:if>
                    <td>${article.title}</td>
                    <td>${article.chapter.topic.name}&#8594;${article.chapter.name}</td>
                    <td>
                        <a class="black" href="/adminPanel/deleteMaterials/delArticle?articleId=${article.id}&search=${search}">delete article</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

</body>
</html>