<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/header.inc" %>
<!-- End header & imports -->

<!-- Container -->
<div class="container min-vh-100">
    <!-- Row -->
    <div class="row">
        <!-- Section -->
        <section class="col-12">

            <!-- Alert container -->
            <div class="alert alert-danger my-5 shadow" role="alert">
                <!-- Error message -->
                <c:if test="${requestScope.error!= null}">

                    <h3 class="text-center my-3">Der opstod en fejl</h3>
                    <p class="text-center my-5">${requestScope.error}</p>

                </c:if>
                <!-- End error message -->
            </div>
            <!-- End alert container -->


        </section>
        <!-- End section -->
    </div>
    <!-- End row -->
</div>
<!-- End container -->

<!-- Footer -->
<%@include file="../includes/footer.inc" %>
<!-- End footer -->