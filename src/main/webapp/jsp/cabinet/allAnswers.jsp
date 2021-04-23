<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/answers.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<c:import url="../parts/header.jsp"/>

<c:if test="${!empty answers}">
    <div class="w-75 grey-bg container-fluid">
        <div class="container">
            <div class="row">
                <div class="col-12 mt-3 mb-1">
                    <h4 class="text-uppercase">All answers</h4>
                </div>
            </div>
            <div class="row d-flex justify-content-around">
                <c:forEach var="answer" items="${answers}">
                    <div class="answ m-3 p-3 pt-2">
                        <span>From ${answer.user.firstName} ${answer.user.lastName}</span> <br>
                        <span>Response: </span>
                        <c:if test="${!empty answer.response}">
                            <span>${answer.response}</span> <br>
                        </c:if>
                        <c:if test="${answer.answerFile.id != null}">
                            <a class="black" href="/cabinet/downloadAnswerFile?id=${answer.answerFile.id}">${answer.answerFile.name}</a>
                        </c:if>
                        <hr style="border: none; background-color: #4A0054; height: 3px;">
                        <form action="/cabinet/confirmAnswer/${answer.id}" method="get">
                            <label>Feedback </label>
                            <input type="text" maxlength="100" name="feedback" class="form-control" value="${answer.feedback}"/>
                            <span class="mt-2">Mark </span>
                            <input type="number" class="w-25 mt-2 inp" step="0.1" value="${answer.mark}" name="mark"
                                   min="0" max="${answer.hometask.maxMark}"/> / ${answer.hometask.maxMark} <br>
                            <input type="hidden" name="articleId" value="${articleId}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-dark mt-2">Send</button><br>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</c:if>


</body>
</html>