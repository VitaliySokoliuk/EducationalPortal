<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/base.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<c:import url="../parts/header.jsp"/>

<div class="container rounded bg-white mt-5">
    <form action="/cabinet/updateUser" enctype="multipart/form-data" method="post">
        <div class="row">
            <div class="col-md-4 border-right">
                <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                    <c:if test = "${user.profilePicture == null}">
                        <img class="rounded-circle mt-5" src="https://clipartart.com/images250_/default-profile-picture-clipart-6.png" width="200px">
                    </c:if>
                    <c:if test = "${user.profilePicture != null}">
                        <img class="rounded-circle mt-5" src="download_photo?id=${user.id}" width="200px">
                    </c:if>
                    <input type="file" name="photo"><br>
                </div>
            </div>
            <div class="col-md-8">
                <div class="p-3 py-5">
                    <div class="d-flex justify-content-between align-items-center mb-3">
                        <div class="d-flex flex-row align-items-center">
                            <h4>Edit profile</h4>
                        </div>
                    </div>
                    <div class="row mt-2">
                        <div class="col-md-6">
                            <label>First name</label>
                            <input type="text" name = "firstName" maxlength="30" value="${user.firstName}" class="form-control">
                        </div>
                        <div class="col-md-6">
                            <label>Last name</label>
                            <input type="text" name = "lastName" maxlength="30" value="${user.lastName}" class="form-control">
                        </div>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-6">
                            <label>Password</label>
                            <input type="password" name = "pass1" placeholder="Input new password" class="form-control">
                        </div>
                        <div class="col-md-6">
                            <label>Confirm password</label>
                            <input type="password" name = "pass2" placeholder="Confirm password" class="form-control">
                        </div>
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="mt-5 text-right">
                        <a href="/cabinet" class="btn btn-dark">Cancel</a>
                        <button class="btn btn-dark" type="submit">Save Profile</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

</body>
</html>