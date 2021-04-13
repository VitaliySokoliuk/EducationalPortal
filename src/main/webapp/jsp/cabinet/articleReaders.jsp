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

<h3>${readersCount} readers in this article</h3>
<c:if test = "${users != null && users != []}">
    <table class="table table-striped">
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <c:if test = "${user.profilePicture == null}">
                    <td>
                        <img class="photo" src="https://clipartart.com/images250_/default-profile-picture-clipart-6.png" width="50px">
                    </td>
                </c:if>
                <c:if test = "${user.profilePicture != null}">
                    <td>
                        <img src="/cabinet/download_photo?id=${user.id}" width="50px">
                    </td>
                </c:if>
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