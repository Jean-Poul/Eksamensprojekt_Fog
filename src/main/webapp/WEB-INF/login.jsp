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

                <table>
                    <tr>
                        <td>Login</td>
                        <td>
                            <form name="login" action="FrontController" method="POST">
                                <input type="hidden" name="target" value="login">
                                Email:<br>
                                <input type="text" name="email" value="someone@nowhere.com">
                                <br>
                                Password:<br>
                                <input type="password" name="password" value="sesam">
                                <br>
                                <input type="submit" value="Submit">
                            </form>
                        </td>
                        <td>Or Register</td>
                        <td>
                            <form name="register" action="FrontController" method="POST">
                                <input type="hidden" name="target" value="register">
                                Email:<br>
                                <input type="text" name="email" value="someone@nowhere.com">
                                <br>
                                Password:<br>
                                <input type="password" name="password1" value="sesam">
                                <br>
                                Retype Password:<br>
                                <input type="password" name="password2" value="sesam">
                                <br>
                                <input type="submit" value="Submit">
                            </form>
                        </td>
                    </tr>
                </table>


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