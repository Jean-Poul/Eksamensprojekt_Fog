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

            <div class="mt-5 mb-4">
                <h1>Vælg carport</h1>
            </div>

            <!-- Group cards -->
            <div class="card-group">
                <!-- Standard card -->
                <section class="card border-dark shadow mr-3">
                    <div class="card-body">
                        <img class="card-img img-fluid" src="images/filler.png" alt="Standard byg">
                        <h2 class="card-title">Carport enkelt </h2>
                        <h5 class="card-subtitle"> CPO01HR<br/>Enkelt carport på 3,90x7,80M med redskabsrum på 2,40x3,30M </h5>
                        <br/>
                        <p class="card-text">Enkelt carport med høj rejsning. 3,90 x 7,80 m.
                            <br/>Extra bred model.
                            3,30 x 2,40 m redskabsrum.
                            <br/>Højde; 3,10 m.
                            <br/>Trykimprægnerede stolper, stern og beklædning.
                            <br/>Leveres med: Søm, skruer, beslag og betontagstenstag.
                            <br/>Udførlig byggevejledning til carport og spær medfølger.
                            <br/>Betontagsten i sort med 30 års garanti.
                            <br/><br/>NB! Leveres som Byg-selv sæt - usamlet og ubehandlet!
                        </p>
                        <a class="card-link btn btn-dark btn-block"
                           href="FrontController?target=redirect&destination=carportstandard">Bestil -
                            pr. stk. 23.998,-</a>
                    </div>
                </section>
                <!-- End standard card -->

                <!-- Customize card -->
                <section class="card border-dark shadow ml-3">
                    <div class="card-body">
                        <img class="card-img img-fluid" src="images/bygselv.png" alt="Byg selv">

                        <h2 class="card-title">Vælg selv! </h2>
                        <h5 class="card-subtitle">carport med egne mål og materialer</h5>
                        <p class="card-text">

                            Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en skitsetegning på en carport indenfor vores standardprogram, der tilpasses dine specifikke ønsker.
                            <br/>
                            <br/>
                            Du kan vælge mellem følgende muligheder
                        <ul>
                            <li>Carportens længde</li>
                            <li>Carportens bredde</li>
                            <li>Rejst eller fladt tag</li>
                            <li>Tagets hældning</li>
                            <li>Tagtype, heriblandt 56 forskellige typer tagsten! </li>
                            <li>Redskabskur</li>
                        </ul>
                        Tilbud og skitsetegning fremsendes med post hurtigst muligt.
                            <br/>Ved bestilling medfølger standardbyggevejledning.
                            <br/>
                        <br/>
                            Klig på "Byg Selv" og Udfyld skemaet på næste side omhyggeligt

                        </p>
                        <form action="FrontController" method="POST">
                            <input type="hidden" name="target" value="carportCustomize">
                            <button class="btn btn-dark btn-block" type="submit">Byg selv</button>
                        </form>
                    </div>
                </section>
                <!-- End customize card -->
            </div>
            <!-- End group cards -->

        </section>
        <!-- End section -->
    </div>
    <!-- End row -->
</div>
<!-- End container -->


<!-- Footer -->
<%@include file="../includes/footer.inc" %>
<!-- End footer -->