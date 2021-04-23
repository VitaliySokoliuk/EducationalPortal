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

<h3 class="w65 m-auto p">${readersCount} readers in this course</h3>
<c:if test = "${users != null && users != []}">
    <div class="w65 m-auto opacity">
        <table class="table table-striped">
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <c:if test = "${user.profilePicture == null}">
                        <td>
                            <img class="photo" src="https://clipartart.com/images250_/default-profile-picture-clipart-6.png" width="60px">
                        </td>
                    </c:if>
                    <c:if test = "${user.profilePicture != null}">
                        <td>
                            <img class="rounded-circle" src="/cabinet/download_photo?id=${user.id}" width="60px">
                        </td>
                    </c:if>
                    <td class="align-middle fs">${user.firstName} ${user.lastName}</td>
                    <td class="align-middle fs">${user.email}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>


</body>
</html>