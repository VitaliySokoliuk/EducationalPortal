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

<div class = "container w65 mt-3">
    <h4>Add new topic</h4>
    <form action="/adminPanel/addTopic" method="post">
        <label>Topic name</label>
        <input type="text" maxlength="30" name = "name" placeholder="Input topic name" class="form-control">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-dark mt-2">Add</button><br>
    </form>
</div>

<c:if test="${!empty topics}">
    <h3 class="w-50 m-auto p">All topics</h3>
    <div class="w-50 m-auto opacity">
        <table class="table table-striped">
            <thead>
            <tr>
                <td><strong>Topic name</strong></td>
                <td></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="elem" items="${topics}">
                <tr>
                    <td>${elem.name}</td>
                    <td><a class="black" href="deleteT/${elem.id}">Delete topic</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>


</body>
</html>