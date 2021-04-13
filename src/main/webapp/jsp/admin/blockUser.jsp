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

<form action="/adminPanel/blockUser" method="get">
    <label>User email</label>
    <input type="email" name = "email" class="form-control">
    <button type="submit" class="btn btn-dark">Find user</button><br>
</form>
<c:if test = "${user != null}">
    <table class="table table-striped">
        <tbody>
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
                <c:if test = "${user.nonLocked}">
                    <td><a href="/adminPanel/blockUser/block?userId=${user.id}">block</a></td>
                </c:if>
                <c:if test = "${!user.nonLocked}">
                    <td><a href="/adminPanel/blockUser/unblock?userId=${user.id}">unblock</a></td>
                </c:if>
            </tr>
        </tbody>
    </table>
</c:if>

<c:if test = "${lockedUsers != null && lockedUsers != []}">
    <h3>Blocked users</h3>
    <table class="table table-striped">
        <tbody>
        <c:forEach var="user" items="${lockedUsers}">
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
                <td><a href="/adminPanel/blockUser/unblock?userId=${user.id}">unblock</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>