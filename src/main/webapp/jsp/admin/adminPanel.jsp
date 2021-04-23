<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>EduPortal</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<c:url value="/resources/css/adminPanel.css"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
<c:import url="../parts/header.jsp"/>

<div class="container">
    <div class="row text-center">
        <div class="col-xl-3 col-sm-6 mb-5">
            <div class="bg-white rounded shadow-sm py-5 px-4">
                <img src="https://media.istockphoto.com/vectors/simple-web-icon-in-vector-chapters-vector-id489907927" alt="" width="130" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
                <h5 class="mb-0"><a href="/adminPanel/subjects">Chapters and topics</a></h5>
            </div>
        </div>
        <c:if test = "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.isSuperAdmin()}">
            <div class="col-xl-3 col-sm-6 mb-5">
                <div class="bg-white rounded shadow-sm py-5 px-4">
                    <img src="https://previews.123rf.com/images/ylivdesign/ylivdesign2004/ylivdesign200402733/144989837-global-admin-icon-outline-global-admin-vector-icon-for-web-design-isolated-on-white-background.jpg" alt="" width="130" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
                    <h5 class="mb-0"><a href="/adminPanel/allAdmins">All admins</a></h5>
                </div>
            </div>
        </c:if>
        <div class="col-xl-3 col-sm-6 mb-5">
            <div class="bg-white rounded shadow-sm py-5 px-4">
                <img src="https://static.thenounproject.com/png/1064293-200.png" alt="" width="130" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
                <h5 class="mb-0"><a href="/adminPanel/blockUser">Block user</a></h5>
            </div>
        </div>
        <div class="col-xl-3 col-sm-6 mb-5">
            <div class="bg-white rounded shadow-sm py-5 px-4">
                <img src="https://i.postimg.cc/Pxd4LqWn/115747777-no-or-stop-sign-reject-book-line-icon-decline-read-sign-delete-article-caution-prohibited.jpg" alt="" width="130" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
                <h5 class="mb-0"><a href="/adminPanel/deleteMaterials">Remove materials</a></h5>
            </div>
        </div>
        <div class="col-xl-3 col-sm-6 mb-5">
            <div class="bg-white rounded shadow-sm py-5 px-4">
                <img src="https://i.postimg.cc/x1S6mYV5/125986224-reject-book-icon-decline-read-sign-delete-article-quality-design-element-classic-style-ico.jpg" alt="" width="130" class="img-fluid rounded-circle mb-3 img-thumbnail shadow-sm">
                <h5 class="mb-0"><a href="/adminPanel/accessToMaterials">Remove paid materials from user</a></h5>
            </div>
        </div>
    </div>
</div>

</body>
</html>