<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <h5 class="my-0 mr-md-auto font-weight-normal">
        <a class="p-2 text-dark" href="/home">EduPortal</a>
    </h5>
    <c:if test = "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal != null}">
        <c:if test = "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.isAdmin()}">
            <div class="my-2 my-md-0 mr-md-3">
                <a class="p-2 text-dark" href="/adminPanel">Admin panel</a>
            </div>
        </c:if>
    </c:if>
    <div class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="/allMaterials">All materials</a>
    </div>
    <c:if test = "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal != null}">
        <div class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="/myMaterials">My materials</a>
        </div>
        <div class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="/myMarks">My marks</a>
        </div>
        <div class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="/cabinet">My cabinet</a>
        </div>
        <form action="/logout" method="post">
            <input type="submit" class="btn btn-warning" value="Sign Out"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </c:if>
    <c:if test = "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal == null}">
        <div class="my-2 my-md-0 mr-md-3">
            <a class="p-2 text-dark" href="/login">Sign In</a>
        </div>
    </c:if>
</div>