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
    <form action="/cabinet/editArticle" enctype="multipart/form-data" method="post">
        <div class="form-group">
            <label for="title">Article Title:</label>
            <input required type="text" maxlength="60" value="${article.title}" class="form-control" id="title" name="title">
        </div>
        <div class="form-group">
            <label for="logo">Article logo:</label>
            <c:if test = "${article.logoPicture == null}">
                <td>
                    <img class="photo" src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png" width="200px">
                </td>
            </c:if>
            <c:if test = "${article.logoPicture != null}">
                <td>
                    <img class="photo" src="/cabinet/downloadArticleLogo?id=${article.id}" width="200px">
                </td>
            </c:if>
            <input type="file" id="logo" name="logo">
        </div>
        <div class="form-group">
            <label for="chapter">Chapter:</label>
            <input required type="text" class="form-control" value="${article.chapter.name}" id="chapter" name="chapter">
        </div>
        <div class="form-group">
            <label for="topic">Topic:</label>
            <select class="form-control" id="topic" name="topic">
                <option value="${article.chapter.topic.name}">${article.chapter.topic.name}</option>
                <c:forEach var="topic" items="${topics}">
                    <option value="${topic.name}">${topic.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <textarea required class="form-control" name="description" maxlength="200"  id="description"
                      cols="30" rows="3">${article.description}</textarea>
        </div>
        <textarea name="content" id="content" cols="30" rows="10">${article.content}</textarea>
        <c:if test = "${!userIsPaid}">
            <div class="disabled">
        </c:if>
            <div class="form-group">
                <label for="paid">Accessibility:</label> <br>
                <c:if test = "${!article.paid}">
                    <input type="radio" name="paid" id="paid" value="false" checked> Free article <br>
                    <input type="radio" name="paid" value="true"> Paid article
                </c:if>
                <c:if test = "${article.paid}">
                    <input type="radio" name="paid" id="paid" value="false"> Free article <br>
                    <input type="radio" name="paid" value="true" checked> Paid article
                </c:if>
            </div>
            <div class="form-group">
                <label for="price">Price:</label> <br>
                <input type="number" value="${article.price}" step="0.1" id="price" name="price" min="0" />
            </div>
        <c:if test = "${!userIsPaid}">
            </div>
        </c:if>
        <div class="form-group">
            <label for="hometask">Hometask:</label>
            <textarea class="form-control" minlength="10" maxlength="255" name="hometask" id="hometask"
                      cols="30" rows="5">${article.hometask.task}</textarea>
        </div>
        <div class="form-group">
            <c:if test = "${article.giveAnswers == true}">
                <input type="checkbox" checked name="give_answers"> Allow answers
            </c:if>
            <c:if test = "${article.giveAnswers == false}">
                <input type="checkbox" name="give_answers"> Allow answers
            </c:if>
        </div>
        <div class="form-group">
            <label for="max_point">Max point:</label>
            <input min="0" type="number" value="${article.hometask.maxMark}" id="max_point" name="max_point">
        </div>
        <input type="hidden" name="id" value="${article.id}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Update</button>
    </form>
</div>


<script src="<c:url value="/resources/ckeditor/ckeditor.js"/>"></script>
<script>
    CKEDITOR.replace('content')
</script>

</body>
</html>