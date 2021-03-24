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

<h2>${article.title}</h2>

${article.content}

<c:if test = "${article.hometask != null}">
    <h3>Additional task</h3>
    ${article.hometask.task}
    <c:if test = "${article.giveAnswers == true && answerAbility == true}">
        <form method="post" action="/readArticle" enctype="multipart/form-data">
            <input type="file" name="file">
            <input type="text" name="response">
            <input type="hidden" name="hometaskId" value="${article.hometask.id}">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-dark">Send</button><br>
        </form>
    </c:if>
</c:if>


</body>
</html>