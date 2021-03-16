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

    <c:if test = "${user.profilePicture == null}">
        <td>
            <img class="photo" src="https://clipartart.com/images250_/default-profile-picture-clipart-6.png" width="200px">
        </td>
    </c:if>
    <c:if test = "${user.profilePicture != null}">
        <td>
            <img src="download_photo?id=${user.id}" width="200px">
        </td>
    </c:if>
    <div class = "container mt-3">
        <form action="/cabinet/updateUser" enctype="multipart/form-data" method="post">
            <label>Your photo:</label>
            <input type="file" name="photo"><br>
            <label>First name:</label>
            <input type="text" name = "firstName" value="${user.firstName}" class="form-control"><br>
            <label>Last name:</label>
            <input type="text" name = "lastName" value="${user.lastName}" class="form-control"><br>
<%--            <label>Password:</label>--%>
<%--            <input type="password" name = "password" placeholder="Input new password" class="form-control"><br>--%>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-dark">Update</button><br>
        </form>
    </div>

</body>
</html>