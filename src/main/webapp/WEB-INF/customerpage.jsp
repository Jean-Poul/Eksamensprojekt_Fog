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
                <a class="btn btn-primary px-5" href="FrontController?target=redirect&destination=#">Byg selv</a>
                <a class="btn btn-secondary px-5" href="FrontController?target=redirect&destination=#">Standard carporte</a>

                <!-- Input form -->
                <form class="row" action="FrontController">

                    <!-- Column -->
                    <div class="col">
                    <!-- Order option -->
                    <div class="form-group">
                        <label for="CarportWidth">Carport bredde</label>
                        <select class="form-control" id="CarportWidth">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="CarportLength">Carport længde</label>
                        <select class="form-control" id="CarportLength">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="RoofOption">Tag type</label>
                        <select class="form-control" id="RoofOption">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>

                        <div class="form-group">
                            <label for="RoofOptionDegrees">Tag grader</label>
                            <select class="form-control" id="RoofOptionDegrees">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                        </div>

                    <div class="form-group">
                        <label for="ShedWidth">Redskabsrum bredde</label>
                        <select class="form-control" id="ShedWidth">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="ShedLength">Redskabsrum længde</label>
                        <select class="form-control" id="ShedLength">
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>
                    <!-- End order option -->
                    </div>
                    <!-- End column -->

                    <!-- Column -->
                    <div class="col">
                    <!-- User info -->
                    <div class="form-group">
                        <label for="Name">Navn</label>
                        <input type="text" class="form-control" id="Name" placeholder="navn">
                    </div>
                    <div class="form-group">
                        <label for="Address">Adresse</label>
                        <input type="text" class="form-control" id="Address" placeholder="adresse">
                    </div>
                    <div class="form-group">
                        <label for="ZipCodeCity">Postnummer og By</label>
                        <input type="text" class="form-control" id="ZipCodeCity" placeholder="postnummer og by">
                    </div>
                    <div class="form-group">
                        <label for="Telephone">Telefon</label>
                        <input type="text" class="form-control" id="Telephone" placeholder="telefon">
                    </div>
                    <div class="form-group">
                        <label for="Email">Email address</label>
                        <input type="text" class="form-control" id="Email" placeholder="email">
                    </div>
                    <!-- End user info -->
                    </div>
                    <!-- End column -->

                    <!-- Column -->
                    <div class="col">

                        <!-- Text area -->
                        <div class="form-group">
                            <label for="Comments">Bemærkninger</label>
                            <textarea class="form-control" id="Comments" rows="12"></textarea>
                        </div>
                        <!-- End text area -->

                        <!-- Submit button -->
                        <input class="btn btn-primary" type="submit" value="Send">
                        <!-- End submit button -->

                    </div>
                    <!-- End column -->

                </form>
                <!-- End form -->

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