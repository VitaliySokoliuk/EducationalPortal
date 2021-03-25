<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm">
    <h5 class="my-0 mr-md-auto font-weight-normal">
        <a class="p-2 text-dark" href="/home">EduPortal</a>
    </h5>
    <div class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="/adminPanel">Admin panel</a>
    </div>
    <div class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="#">Materials</a>
    </div>
    <div class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="/myMaterials">My materials</a>
    </div>
    <div class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="/myMarks">My marks</a>
    </div>
    <div class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="/cabinet">My cabinet</a>
    </div>
    <div class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="/login">Sign In</a>
    </div>
    <form action="/logout" method="post">
        <input type="submit" class="btn btn-warning" value="Sign Out"/>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
</div>