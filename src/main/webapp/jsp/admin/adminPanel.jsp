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

<a href="/adminPanel/subjects">Chapters and topics</a> <br>

<c:if test = "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.isSuperAdmin()}">
    <a href="/adminPanel/allAdmins">All admins</a> <br>
</c:if>

<a href="/adminPanel/blockUser">Block user</a> <br>

<a href="/adminPanel/accessToMaterials">Remove paid materials from the user</a> <br>

<a href="/adminPanel/deleteMaterials">Remove materials</a> <br>

</body>
</html>