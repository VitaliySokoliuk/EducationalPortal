<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
    <title>Login</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>

<div class="container">
    <form action="/spring_security_check" method="post">
        <div class="form-group">
            <label for="login">Email</label>
            <input type="email" class="form-control" id="login" placeholder="Enter email" name="email">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" placeholder="Password" name="password">
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit" class="btn btn-primary">Submit</button>
        <c:if test="${param.error ne null}">
            <div class="alert-danger">Invalid username and password.</div>
        </c:if>
        <c:if test="${param.logout ne null}">
            <div class="alert-normal">You have been logged out.</div>
        </c:if>
    </form>

    <p>Press <a href="/registration">this</a> if you want sign up</p>
</div>

</body>
</html>
