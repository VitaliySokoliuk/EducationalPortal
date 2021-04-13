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

<form action="/adminPanel/accessToMaterials" method="get">
    <label>User email</label>
    <input type="email" name = "email" class="form-control">
    <button type="submit" class="btn btn-dark">Find user</button><br>
</form>

<c:if test = "${user != null}">
    <table class="table table-striped">
        <tbody>
        <tr>
            <c:if test = "${user.profilePicture == null}">
                <td>
                    <img class="photo" src="https://clipartart.com/images250_/default-profile-picture-clipart-6.png" width="50px">
                </td>
            </c:if>
            <c:if test = "${user.profilePicture != null}">
                <td>
                    <img src="/cabinet/download_photo?id=${user.id}" width="50px">
                </td>
            </c:if>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
        </tr>
        </tbody>
    </table>
</c:if>

<c:if test="${!empty courses}">
    <div class="container">
        <h4>User's courses:</h4>
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
                        <a href="/adminPanel/accessToMaterials/delCourse?userId=${user.id}&courseId=${course.id}">delete access</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

<c:if test="${!empty articles}">
    <div class="container">
        <h4>User's articles:</h4>
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
                        <a href="/adminPanel/accessToMaterials/delArticle?userId=${user.id}&articleId=${article.id}">delete access</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

</body>
</html>