<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/header.inc" %>
<!-- End header & imports -->

<!-- Container -->
<div class="container min-vh-100">
    <!-- Row -->
    <div class="row">

            <section class="col-12">

                <h1>Fog første udkast</h1>
                <!-- Alert container -->
                <div class="alert alert-danger" role="alert">
                    <!-- Error message -->
                    <c:if test="${requestScope.error!= null}">

                        <h2>Der opstod en fejl</h2>
                        ${requestScope.error}

                    </c:if>
                    <!-- End error message -->
                </div>
                <!-- End alert container -->


            </section>

    </div>
    <!-- End row -->
</div>
<!-- End container -->

<!-- Footer -->
<%@include file="../includes/footer.inc" %>
<!-- End footer -->