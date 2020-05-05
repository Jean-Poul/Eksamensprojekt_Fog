<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/header.inc" %>
<!-- End header & imports -->

<!-- Container -->
<div class="container min-vh-100">
    <!-- Row -->
    <div class="row">

        <!-- Main -->
        <main role="main">
            <!-- Section -->
            <section class="col-12">

                <h1>Fog første udkast</h1>
                <!-- Login form -->
                <div class="container">
                    <h3>Login</h3>
                    <form name="login" action="FrontController" method="POST">
                        <input type="hidden" name="target" value="login">
                        Email:<br>
                        <input type="text" name="email" value="admin@fog.dk">
                        <br>
                        Password:<br>
                        <input type="password" name="password" value="1234">
                        <br>
                        <input type="submit" value="Submit">
                    </form>
                </div>
                <!-- End login form -->

            </section>
            <!-- End section -->
        </main>
        <!-- End main -->
    </div>
    <!-- End row -->
</div>
<!-- End container -->

<!-- Footer -->
<%@include file="../includes/footer.inc" %>
<!-- End footer -->