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
    <form action="/cabinet/createArticle" enctype="multipart/form-data" method="post">
        <div class="p-3 py-5">
            <div class="d-flex justify-content-between align-items-center mb-3">
                <div class="d-flex flex-row align-items-center">
                    <h4>Create article</h4>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-6">
                    <label>Title</label>
                    <input required type="text" maxlength="60" class="form-control" name="title">
                </div>
                <div class="col-md-6">
                    <label>Logo</label> <br>
                    <input type="file" name="logo">
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-6">
                    <label>Chapter</label>
                    <input required type="text" maxlength="30" class="form-control" name="chapter">
                </div>
                <div class="col-md-6">
                    <label>Topic</label>
                    <select class="form-control" name="topic">
                        <c:forEach var="topic" items="${topics}">
                            <option value="${topic.name}">${topic.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-12">
                    <label>Description</label>
                    <textarea required class="form-control" name="description" maxlength="200" cols="30" rows="3"></textarea>
                </div>
            </div>
            <div class="row mt-3">
                <div class="col-md-12">
                    <textarea name="content" id="content" cols="30" rows="10"></textarea>
                </div>
            </div>
            <c:if test = "${!userIsPaid}">
                <div class="disabled">
            </c:if>
                <div class="row mt-3">
                    <div class="col-md-6">
                        <label>Accessibility</label> <br>
                        <input type="radio" name="paid" value="false" checked> Free course <br>
                        <input type="radio" name="paid" value="true"> Paid course
                    </div>
                    <div class="col-md-6">
                        <label>Price</label>
                        <input type="number" class="form-control" step="0.1" name="price" min="0"/>
                    </div>
                </div>
            <c:if test = "${!userIsPaid}">
                </div>
            </c:if>
            <div class="row mt-3">
                <div class="col-md-6">
                    <label>Hometask</label>
                    <textarea class="form-control" minlength="10" maxlength="255" name="hometask" cols="30" rows="5"></textarea>
                </div>
                <div class="col-md-3">
                    <br>
                    <input type="checkbox" name="give_answers" value="true" > Allow answers
                </div>
                <div class="col-md-3">
                    <label>Max point</label> <br>
                    <input min="0" class="form-control" type="number" name="max_point">
                </div>
            </div>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="mt-5 text-right">
                <a href="/cabinet" class="btn btn-dark">Cancel</a>
                <button class="btn btn-dark" type="submit">Create article</button>
            </div>
        </div>
    </form>
</div>

<script src="<c:url value="/resources/ckeditor/ckeditor.js"/>"></script>
<script>
    CKEDITOR.replace('content')
</script>

</body>
</html>