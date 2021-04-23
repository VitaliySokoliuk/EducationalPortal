<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/table.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<c:import url="../parts/header.jsp"/>

<c:if test="${!empty answersArticle}">
    <h3 class="w85 m-auto p">My answers</h3>
    <div class="w85 m-auto opacity">
        <table class="table table-striped">
            <thead>
                <tr>
                    <td>Article</td>
                    <td>Your response</td>
                    <td>File</td>
                    <td>Author's feedback</td>
                    <td>Your result</td>
                </tr>
            </thead>
            <tbody>
            <c:forEach var="answerArticle" items="${answersArticle}">
                <tr>
                    <td><a class="black" href="/readArticle?id=${answerArticle.value.id}">
                            ${answerArticle.value.title}</a>
                    </td>
                    <td>
                        <c:if test="${!empty answerArticle.key.response}">
                            <span>${answerArticle.key.response}</span>
                        </c:if>
                        <c:if test="${empty answerArticle.key.response}">
                            <span>none</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${answerArticle.key.answerFile.id != null}">
                            <span><a class="black" href="/cabinet/downloadAnswerFile?id=${answerArticle.key.answerFile.id}">
                                    ${answerArticle.key.answerFile.name}</a>
                            </span>
                        </c:if>
                        <c:if test="${answerArticle.key.answerFile.id == null}">
                            <span>none</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${answerArticle.key.feedback != null}">
                            ${answerArticle.key.feedback}
                        </c:if>
                        <c:if test="${answerArticle.key.feedback == null}">
                            <span>none</span>
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${answerArticle.key.mark != 0.0}">
                            <span>${answerArticle.key.mark} / ${answerArticle.key.hometask.maxMark}</span>
                        </c:if>
                        <c:if test="${answerArticle.key.mark == 0.0}">
                            <span>none</span>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

</body>
</html>