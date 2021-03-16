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

<h4>Add new user:</h4>
<form action="/cabinet/addUserArticle" method="post">
    <label>User email</label>
    <input type="email" name = "email" class="form-control">
    <input type="hidden" name="articleId" value="${articleId}">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit" class="btn btn-dark">Add user</button><br>
</form>

<c:if test = "${users != null && users != []}">
    <h3>Users in article</h3>
    <table class="table table-striped">
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
                <td>${user.email}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>


</body>
</html>