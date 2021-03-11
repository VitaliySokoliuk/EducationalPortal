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
<c:import url="/jsp/parts/header.jsp"/>

<div class = "container mt-3">
    <h4>Add new topic:</h4>
    <form action="/adminPanel/addTopic" method="post">
        <label>Topic name:</label>
        <input type="text" name = "name" class="form-control">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-dark">Add</button><br>
    </form>
    <c:if test="${!empty topics}">
        <h4>Topics:</h4>
        <table class="table table-striped">
            <tbody>
            <c:forEach var="elem" items="${topics}">
                <tr>
                    <td>${elem.name}</td>
                    <td><a href="deleteT/${elem.id}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

    <c:if test="${!empty chapters}">
        <h4>Chapters:</h4>
        <table class="table table-striped">
            <tbody>
            <c:forEach var="elem" items="${chapters}">
                <tr>
                    <td>${elem.name}</td>
                    <td>${elem.topic.name}</td>
                    <td><a href="deleteC/${elem.id}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>

</div>


</body>
</html>