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

<c:if test="${!empty answers}">
    <div class="container">
        <h4>Answers:</h4>
        <c:forEach var="answer" items="${answers}">
            <div class="container">
                <div>
                    ${answer.user.firstName} ${answer.user.lastName}
                    <c:if test="${!empty answer.response}">
                        <p>Response: ${answer.response}</p>
                    </c:if>
                    <c:if test="${answer.answerFile.id != null}">
                        <p>File: <a href="/cabinet/downloadAnswerFile?id=${answer.answerFile.id}">File</a></p>
                    </c:if>
                        <form action="/cabinet/confirmAnswer/${answer.id}" method="get">
                            <label for="feedback">Feedback:</label>
                            <input type="text" name="feedback" id="feedback" value="${answer.feedback}"/>
                            <label for="feedback">Mark:</label>
                            <input type="number" id="mark" step="0.1" value="${answer.mark}" name="mark"
                                   min="0" max="${answer.hometask.maxMark}"/> / ${answer.hometask.maxMark} <br>
                            <input type="hidden" name="articleId" value="${articleId}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-dark">Send</button><br>
                        </form>
                </div>
            </div>
        </c:forEach>
    </div>
</c:if>


</body>
</html>