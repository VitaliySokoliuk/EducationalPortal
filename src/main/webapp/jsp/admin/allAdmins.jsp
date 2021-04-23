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
    <h4>Add new admin</h4>
    <form action="/adminPanel/allAdmins" method="post">
        <label>User email</label>
        <input type="email" name = "email" placeholder="Input user's email" class="form-control">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-dark mt-2">Add admin</button><br>
    </form>
</div>

<c:if test="${!empty superAdmins}">
    <h3 class="w-50 m-auto p">All admins</h3>
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
                        <a class="black" href="/adminPanel/allAdmins/makeUser?adminId=${admin.id}">Delete admin</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>

</body>
</html>