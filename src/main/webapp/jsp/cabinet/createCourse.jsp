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
    <h2>New Course</h2>
    <form action="/cabinet/createCourse" enctype="multipart/form-data" method="post">
        <div class="form-group">
            <label for="title">Course Title:</label>
            <input required type="text" maxlength="60" class="form-control" id="title" name="title">
        </div>
        <div class="form-group">
            <label for="logo">Course logo:</label>
            <input type="file" id="logo" name="logo">
        </div>
        <div class="form-group">
            <label for="chapter">Chapter:</label>
            <input required type="text" class="form-control" id="chapter" name="chapter">
        </div>
        <div class="form-group">
            <label for="topic">Topic:</label>
            <select class="form-control" id="topic" name="topic">
                <c:forEach var="topic" items="${topics}">
                    <option value="${topic.name}">${topic.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea required class="form-control" name="description" maxlength="200" id="description" cols="30" rows="3"></textarea>
        </div>
        <c:if test = "${!userIsPaid}">
            <div class="disabled">
        </c:if>
        <div class="form-group">
            <label for="paid">Accessibility:</label> <br>
            <input type="radio" name="paid" id="paid" value="false" checked> Free course <br>
            <input type="radio" name="paid" value="true"> Paid course
        </div>
        <div class="form-group">
            <label for="price">Price:</label> <br>
            <input type="number" step="0.1" id="price" name="price" min="0" />
        </div>
        <c:if test = "${!userIsPaid}">
            </div>
        </c:if>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Create</button>
    </form>
</div>

</body>
</html>