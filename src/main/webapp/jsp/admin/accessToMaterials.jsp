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

<div class = "container w65 mt-3">
    <h4>Remove access to paid material to user</h4>
    <form action="/adminPanel/accessToMaterials" method="get">
        <label>User email</label>
        <input type="email" name = "email" placeholder="Input user's email" class="form-control">
        <button type="submit" class="btn btn-dark mt-2 mb-3">Find user</button><br>
    </form>
</div>

<c:if test="${user != null}">
    <div class="w-50 m-auto opacity">
        <table class="table table-striped">
            <tbody>
            <tr>
                <c:if test = "${user.profilePicture == null}">
                    <td>
                        <img class="rounded" src="https://clipartart.com/images250_/default-profile-picture-clipart-6.png" width="50px">
                    </td>
                </c:if>
                <c:if test = "${user.profilePicture != null}">
                    <td>
                        <img class="rounded" src="/cabinet/download_photo?id=${user.id}" width="50px">
                    </td>
                </c:if>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
            </tr>
            </tbody>
        </table>
    </div>
</c:if>

<c:if test = "${!empty courses}">
    <h3 class="w-75 m-auto p">User's courses</h3>
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
                        <a class="black" href="/adminPanel/accessToMaterials/delCourse?userId=${user.id}&courseId=${course.id}">delete access</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

<c:if test = "${!empty articles}">
    <h3 class="w-75 m-auto p">User's articles</h3>
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
                        <a class="black" href="/adminPanel/accessToMaterials/delArticle?userId=${user.id}&articleId=${article.id}">delete access</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

</body>
</html>