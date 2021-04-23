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

<div class="row py-5 px-4">
    <div class="col-md-8 mx-auto">
        <div class="bg-white shadow rounded overflow-hidden">
            <div class="px-5 pt-0 pb-4 cover">
                <div class="media align-items-end profile-head">
                    <div class="profile mr-4">
                        <c:if test = "${user.profilePicture == null}">
                            <img alt="..." width="250" class="rounded mb-2 img-thumbnail" src="https://clipartart.com/images250_/default-profile-picture-clipart-6.png">
                        </c:if>
                        <c:if test = "${user.profilePicture != null}">
                            <img alt="..." width="250" class="rounded mb-2 img-thumbnail" src="/cabinet/download_photo?id=${user.id}">
                        </c:if>
                        <a href="/cabinet/updateUser" class="btn btn-outline-dark btn-sm btn-block">Edit profile</a>
                    </div>
                    <div class="media-body mb-5 text-white">
                        <h3 class="mt-0 mb-0">${user.firstName} ${user.lastName}</h3>
                        <p class="mb-4">${user.email}</p>
                    </div>
                </div>
            </div>
            <div class="bg-light p-4 d-flex justify-content-end text-center">
                <ul class="list-inline mb-0">
                    <li class="list-inline-item">
                        <h4 class="font-weight-bold mb-0 d-block">${likes}</h4><span class="text-muted">Likes</span>
                    </li>
                    <li class="list-inline-item">
                        <h4 class="font-weight-bold mb-0 d-block">${numOfCourses}</h4><span class="text-muted">Courses</span>
                    </li>
                    <li class="list-inline-item">
                        <h4 class="font-weight-bold mb-0 d-block">${numOfArticles}</h4><span class="text-muted">Articles</span>
                    </li>
                </ul>
            </div>
            <div class="d-flex justify-content-between ml-4 mr-4">
                <a href="/cabinet/createCourse" class="btn btn-outline-dark btn-sm btn-block m-2 p-2"><h5>Create course</h5></a>
                <a href="/cabinet/createArticle" class="btn btn-outline-dark btn-sm btn-block m-2 p-2"><h5>Create article</h5></a>
                <a href="/cabinet/allAnswers" class="btn btn-outline-dark btn-sm btn-block m-2 p-2"><h5>All readers answers</h5></a>
            </div>
            <div class="py-4 px-4">
                <c:if test="${!empty courses}">
                    <div class="d-flex align-items-center justify-content-between mb-3">
                        <h5 class="mb-0">My Courses</h5>
                    </div>
                    <div class="row mt-0">
                        <c:forEach var="course" items="${courses}">
                            <div class="col-lg-6 mb-2 pr-lg-1">
                                <div class="card float-left">
                                    <div class="row">
                                        <div class="col-sm-7">
                                            <div class="card-block">
                                                <h5 class="card-title">${course.title}</h5>
                                                <span>Number of likes: ${course.likes}</span> <br>
                                                <c:if test = "${course.paid}">
                                                    <span>Course is paid - ${course.price} UAH</span>
                                                </c:if>
                                                <c:if test = "${!course.paid}">
                                                    <span>Course is free</span>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 p-2 pr-4 pl-1">
                                            <c:if test = "${course.logoPicture == null}">
                                                <img class="d-block w-100" src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png">
                                            </c:if>
                                            <c:if test = "${course.logoPicture != null}">
                                                <img class="d-block w-100" src="/cabinet/downloadCourseLogo?id=${course.id}">
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="d-flex mt-0">
                                        <a class="btn btn-outline-dark btn-sm btn-block m-2 fon" href="/cabinet/editCourse?id=${course.id}">Edit</a>
                                        <a class="btn btn-outline-dark btn-sm btn-block m-2 fon" href="/cabinet/articlesInCourse?id=${course.id}">Articles</a>
                                        <c:if test = "${course.paid}">
                                            <a class="btn btn-outline-dark btn-sm btn-block m-2 fon" href="/cabinet/courseReaders?id=${course.id}">Readers</a>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
                <c:if test="${!empty articles}">
                    <div class="d-flex align-items-center justify-content-between mb-3">
                        <h5 class="mb-0">My Articles</h5>
                    </div>
                    <div class="row mt-0">
                        <c:forEach var="article" items="${articles}">
                            <div class="col-lg-6 mb-2 pr-lg-1">
                                <div class="card float-left">
                                    <div class="row">
                                        <div class="col-sm-7">
                                            <div class="card-block">
                                                <h5 class="card-title">${article.title}</h5>
                                                <span>Number of likes: ${article.likes}</span> <br>
                                                <c:if test = "${article.paid}">
                                                    <span>Article is paid - ${article.price} UAH</span>
                                                </c:if>
                                                <c:if test = "${!article.paid}">
                                                    <span>Article is free</span>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="col-sm-5 p-2 pr-4 pl-1">
                                            <c:if test = "${article.logoPicture == null}">
                                                <img class="d-block w-100" src="https://images.vexels.com/media/users/3/147152/isolated/preview/70b2451ccef7efd91f7fde69fe25d1c9-newspaper-article-icon-by-vexels.png">
                                            </c:if>
                                            <c:if test = "${article.logoPicture != null}">
                                                <img class="d-block w-100" src="/cabinet/downloadArticleLogo?id=${article.id}">
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="d-flex mt-0">
                                        <a class="btn btn-outline-dark btn-sm btn-block m-2 fon" href="/cabinet/editArticle?id=${article.id}">Edit</a>
                                        <c:if test = "${article.paid}">
                                            <a class="btn btn-outline-dark btn-sm btn-block m-2 fon" href="/cabinet/articleReaders?id=${article.id}">Readers</a>
                                        </c:if>
                                        <c:if test = "${article.giveAnswers}">
                                            <a class="btn btn-outline-dark btn-sm btn-block m-2 fon" href="/cabinet/articleAnswers?id=${article.id}">Answers</a>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </c:if>
            </div>
        </div>
    </div>
</div>

</body>
</html>