<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../../includes/header.inc" %>
<!-- End header & imports -->

<!-- Container -->
<div class="container min-vh-100">
    <!-- Row -->
    <div class="row">
        <!-- Section -->
        <section class="col-12">

            <!-- Login form -->
            <div class="container d-flex justify-content-center mt-5">

                <div class="col-4 p-5 rounded-lg shadow">
                    <h3 class="text-primary text-center">Login</h3>
                    <form name="login" action="FrontController" method="POST">
                        <input type="hidden" name="target" value="login">
                        <div class="form-group">
                            <label for="InputEmail">Email adresse</label>
                            <input type="email" class="form-control" id="InputEmail" aria-describedby="emailHelp"
                                   name="email" value="admin@fog.dk">
                            <small id="emailHelp" class="form-text text-muted">Brugernavn</small>
                        </div>
                        <div class="form-group">
                            <label for="InputPassword">Password</label>
                            <input type="password" class="form-control" id="InputPassword" name="password" value="Gruppe666/">
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Log ind</button>
                    </form>
                </div>

            </div>
            <!-- End login form -->

        </section>
        <!-- End section -->
    </div>
    <!-- End row -->
</div>
<!-- End container -->

<!-- Footer -->
<%@include file="../../includes/footer.inc" %>
<!-- End footer -->