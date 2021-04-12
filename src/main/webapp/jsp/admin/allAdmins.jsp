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

<h4>Add new admin:</h4>
<form action="/adminPanel/allAdmins" method="post">
    <label>User email</label>
    <input type="email" name = "email" class="form-control">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <button type="submit" class="btn btn-dark">Add admin</button><br>
</form>

<div class="container">
    <table class="table table-striped">
        <thead>
        <tr align = "center">
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="superAdmin" items="${superAdmins}">
            <tr>
                <td>${superAdmin.firstName} ${superAdmin.lastName}</td>
                <td>${superAdmin.email}</td>
                <td>${superAdmin.role}</td>
                <td></td>
            </tr>
        </c:forEach>
        <c:forEach var="admin" items="${admins}">
            <tr>
                <td>${admin.firstName} ${admin.lastName}</td>
                <td>${admin.email}</td>
                <td>${admin.role}</td>
                <td>
                    <a href="/adminPanel/allAdmins/makeUser?adminId=${admin.id}">Delete admin</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>