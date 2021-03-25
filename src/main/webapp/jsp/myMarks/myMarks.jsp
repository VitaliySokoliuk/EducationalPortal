<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/home.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<c:import url="../parts/header.jsp"/>

<c:if test="${!empty answersArticle}">
    <div class="container">
        <h4>My answers:</h4>
        <c:forEach var="answerArticle" items="${answersArticle}">
            <div class="container">
                <div>
                    <a href="/readArticle?id=${answerArticle.value.id}">${answerArticle.value.title}</a>
                    <c:if test="${!empty answerArticle.key.response}">
                        <p>Response: ${answerArticle.key.response}</p>
                    </c:if>
                    <c:if test="${answerArticle.key.answerFile.id != null}">
                        <p>File: <a href="/cabinet/downloadAnswerFile?id=${answerArticle.key.answerFile.id}">File</a></p>
                    </c:if>
                    <c:if test="${answerArticle.key.mark != 0.0}">
                        <p>Your result ${answerArticle.key.mark}</p>
                    </c:if>
                    <c:if test="${answerArticle.key.mark == 0.0}">
                        <p>Wait until teacher check your answer</p>
                    </c:if>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>


</body>
</html>