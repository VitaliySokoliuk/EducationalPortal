<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/base.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/block.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<c:import url="../parts/header.jsp"/>

<div class="container p-4 art_fon">
    <h2>${article.title}</h2>
    ${article.content}
    <c:if test = "${article.hometask != null}">
        <div class="container p-2">
            <h3>Additional task</h3>
            <h5 class="mb-2">${article.hometask.task}</h5>
            <c:if test = "${article.giveAnswers && answerAbility}">
                <form method="post" action="/readArticle" enctype="multipart/form-data">
                    <input class="mb-2" type="file" name="file">
                    <input type="text" maxlength="100" class="form-control w-50" placeholder="Your answer" name="response"> <br>
                    <input type="hidden" name="hometaskId" value="${article.hometask.id}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-dark">Send</button><br>
                </form>
            </c:if>
        </div>
    </c:if>
</div>

</body>
</html>