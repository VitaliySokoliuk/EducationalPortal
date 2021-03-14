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
<c:import url="../parts/header.jsp"/>

<div class="container">
    <h2>Edit Course</h2>
    <form action="/cabinet/editCourse" enctype="multipart/form-data" method="post">
        <div class="form-group">
            <label for="title">Course Title:</label>
            <input required type="text" value="${course.title}" class="form-control" id="title" name="title">
        </div>
        <div class="form-group">
            <label for="logo">Course logo:</label>
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
            <input type="file" id="logo" name="logo">
        </div>
        <div class="form-group">
            <label for="chapter">Chapter:</label>
            <input required type="text" value="${course.chapter.name}" class="form-control" id="chapter" name="chapter">
        </div>
        <div class="form-group">
            <label for="topic">Topic:</label>
            <select class="form-control" id="topic" name="topic">
                <option value="${course.chapter.topic.name}">${course.chapter.topic.name}</option>
                <c:forEach var="topic" items="${topics}">
                    <option value="${topic.name}">${topic.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea required class="form-control" name="description" maxlength="60" id="description"
                      cols="30" rows="3">${course.description}</textarea>
        </div>

        <div class="form-group">
            <label for="visibility">Accessibility:</label> <br>
            <c:if test = "${course.visibility == true}">
                <input type="radio" name="visibility" id="visibility" value="true" checked> Public course <br>
                <input type="radio" name="visibility" value="false"> Private course
            </c:if>
            <c:if test = "${course.visibility == false}">
                <input type="radio" name="visibility" id="visibility" value="true"> Public course <br>
                <input type="radio" name="visibility" value="false" checked> Private course
            </c:if>
        </div>
        <input type="hidden" name="id" value="${course.id}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>

</body>
</html>