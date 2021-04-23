<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/forms.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<c:import url="../parts/header.jsp"/>

<div class="container rounded bg-white mt-5">
    <form action="/cabinet/editCourse" enctype="multipart/form-data" method="post">
        <div class="p-3 py-5">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <div class="d-flex flex-row align-items-center">
                    <h4>Edit course</h4>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-6">
                    <label>Title</label>
                    <input required type="text" maxlength="60" value="${course.title}" class="form-control" name="title">
                </div>
                <div class="col-md-6">
                    <c:if test = "${course.logoPicture == null}">
                        <img class="photo" src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="120px">
                    </c:if>
                    <c:if test = "${course.logoPicture != null}">
                        <img class="photo" src="/cabinet/downloadCourseLogo?id=${course.id}" width="120px">
                    </c:if>
                    <input type="file" name="logo">
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-6">
                    <label>Chapter</label>
                    <input required type="text" maxlength="30" value="${course.chapter.name}" class="form-control" name="chapter">
                </div>
                <div class="col-md-6">
                    <label>Topic</label>
                    <select class="form-control" name="topic">
                        <option value="${course.chapter.topic.name}">${course.chapter.topic.name}</option>
                        <c:forEach var="topic" items="${topics}">
                            <option value="${topic.name}">${topic.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-12">
                    <label>Description</label>
                    <textarea required class="form-control" name="description" maxlength="200"
                              cols="30" rows="3">${course.description}</textarea>
                </div>
            </div>
            <c:if test = "${!userIsPaid}">
            <div class="disabled">
                </c:if>
                <div class="row mt-3">
                    <div class="col-md-6">
                        <label>Accessibility</label> <br>
                        <c:if test = "${!course.paid}">
                            <input type="radio" name="paid" value="false" checked> Free course <br>
                            <input type="radio" name="paid" value="true"> Paid course
                        </c:if>
                        <c:if test = "${course.paid}">
                            <input type="radio" name="paid" value="false"> Free course <br>
                            <input type="radio" name="paid" value="true" checked> Paid course
                        </c:if>
                    </div>
                    <div class="col-md-6">
                        <label>Price</label>
                        <input type="number" class="form-control" value="${course.price}" step="0.1" name="price" min="0"/>
                    </div>
                </div>
                <c:if test = "${!userIsPaid}">
            </div>
            </c:if>
            <input type="hidden" name="id" value="${course.id}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="mt-5 text-right">
                <a href="/cabinet" class="btn btn-dark">Cancel</a>
                <a class="btn btn-dark" href="/cabinet/deleteCourse?courseId=${course.id}">Delete course</a>
                <button class="btn btn-dark" type="submit">Update course</button>
            </div>
        </div>
    </form>
</div>

</body>
</html>