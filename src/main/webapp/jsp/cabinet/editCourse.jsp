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
    <h2>Edit Course</h2>
    <form action="/cabinet/editCourse" enctype="multipart/form-data" method="post">
        <div class="form-group">
            <label for="title">Course Title:</label>
            <input required type="text" maxlength="60" value="${course.title}" class="form-control" id="title" name="title">
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
            <textarea required class="form-control" name="description" maxlength="200" id="description"
                      cols="30" rows="3">${course.description}</textarea>
        </div>
        <c:if test = "${!userIsPaid}">
            <div class="disabled">
        </c:if>
        <div class="form-group">
            <label for="paid">Accessibility:</label> <br>
            <c:if test = "${!course.paid}">
                <input type="radio" name="paid" id="paid" value="false" checked> Free course <br>
                <input type="radio" name="paid" value="true"> Paid course
            </c:if>
            <c:if test = "${course.paid}">
                <input type="radio" name="paid" id="paid" value="false"> Free course <br>
                <input type="radio" name="paid" value="true" checked> Paid course
            </c:if>
        </div>
        <div class="form-group">
            <label for="price">Price:</label> <br>
            <input type="number" value="${course.price}" step="0.1" id="price" name="price" min="0" />
        </div>
        <c:if test = "${!userIsPaid}">
            </div>
        </c:if>
        <input type="hidden" name="id" value="${course.id}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
    <div class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="/cabinet/deleteCourse?courseId=${course.id}">Delete</a>
    </div>
</div>

</body>
</html>