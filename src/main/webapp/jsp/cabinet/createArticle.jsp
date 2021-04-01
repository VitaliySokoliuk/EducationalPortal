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
    <h2>New Article</h2>
    <form action="/cabinet/createArticle" enctype="multipart/form-data" method="post">
        <div class="form-group">
            <label for="title">Article Title:</label>
            <input required type="text" class="form-control" id="title" name="title">
        </div>
        <div class="form-group">
            <label for="logo">Article logo:</label>
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
            <textarea required class="form-control" name="description" maxlength="60" id="description" cols="30" rows="3"></textarea>
        </div>
        <textarea name="content" id="content" cols="30" rows="10"></textarea>
        <c:if test = "${!userIsPaid}">
            <div class="disabled">
        </c:if>
                <div class="form-group">
                    <label for="paid">Accessibility:</label> <br>
                    <input type="radio" name="paid" id="paid" value="false" checked> Free article <br>
                    <input type="radio" name="paid" value="true"> Paid article
                </div>
                <div class="form-group">
                    <label for="price">Price:</label> <br>
                    <input type="number" step="0.1" id="price" name="price" min="0" />
                </div>
        <c:if test = "${!userIsPaid}">
            </div>
        </c:if>
        <div class="form-group">
            <label for="hometask">Hometask:</label>
            <textarea class="form-control" minlength="10" maxlength="255" name="hometask" id="hometask" cols="30" rows="5"></textarea>
        </div>
        <div class="form-group">
            <input type="checkbox" name="give_answers" value="true" > Allow answers
        </div>
        <div class="form-group">
            <label for="max_point">Max point:</label>
            <input min="0" type="number" id="max_point" name="max_point">
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Create</button>

    </form>
</div>


<script src="<c:url value="/resources/ckeditor/ckeditor.js"/>"></script>
<script>
    CKEDITOR.replace('content')
</script>

</body>
</html>