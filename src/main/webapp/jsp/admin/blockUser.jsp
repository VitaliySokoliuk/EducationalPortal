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
    <h4>Block user</h4>
    <form action="/adminPanel/blockUser" method="get">
        <label>User email</label>
        <input type="email" name = "email" placeholder="Input user's email" class="form-control">
        <button type="submit" class="btn btn-dark mt-2">Find user</button><br>
    </form>
</div>

<c:if test="${user != null}">
    <div class="w-50 m-auto opacity">
        <table class="table table-striped">
            <tbody>
                <tr>
                    <c:if test = "${user.profilePicture == null}">
                        <td>
                            <img class="rounded" src="https://clipartart.com/images250_/default-profile-picture-clipart-6.png" width="50px">
                        </td>
                    </c:if>
                    <c:if test = "${user.profilePicture != null}">
                        <td>
                            <img class="rounded" src="/cabinet/download_photo?id=${user.id}" width="50px">
                        </td>
                    </c:if>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <c:if test = "${user.nonLocked}">
                        <td><a class="black" href="/adminPanel/blockUser/block?userId=${user.id}">block</a></td>
                    </c:if>
                    <c:if test = "${!user.nonLocked}">
                        <td><a class="black" href="/adminPanel/blockUser/unblock?userId=${user.id}">unblock</a></td>
                    </c:if>
                </tr>
            </tbody>
        </table>
    </div>
</c:if>

<c:if test = "${lockedUsers != null && lockedUsers != []}">
    <h3 class="w-50 m-auto p">Blocked users</h3>
    <div class="w-50 m-auto opacity">
        <table class="table table-striped">
            <thead>
            <tr>
                <td><strong>Topic name</strong></td>
                <td><strong>Email</strong></td>
                <td><strong>Role</strong></td>
                <td> </td>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${lockedUsers}">
                <tr>
                    <c:if test = "${user.profilePicture == null}">
                        <td>
                            <img class="rounded" src="https://clipartart.com/images250_/default-profile-picture-clipart-6.png" width="50px">
                        </td>
                    </c:if>
                    <c:if test = "${user.profilePicture != null}">
                        <td>
                            <img class="rounded" src="/cabinet/download_photo?id=${user.id}" width="50px">
                        </td>
                    </c:if>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td><a class="black" href="/adminPanel/blockUser/unblock?userId=${user.id}">unblock</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

</body>
</html>