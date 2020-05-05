<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/header.inc" %>
<!-- End header -->

<!-- Container -->
<div class="container min-vh-100">
    <!-- Row -->
    <div class="row">
        <div col-2></div>
        <div col-8>
            <h1>Tak for din forspørgelse</h1>
            <p>${requestScope.name}</p>
            <p>${requestScope.address}</p>
            <p>${requestScope.zipcodeCity}</p>

        </div>
        <div col-2></div>
    </div>
    <!-- End row -->
</div>
<!-- End container -->

<!-- Footer -->
<%@include file="../includes/footer.inc" %>
<!-- End footer -->

