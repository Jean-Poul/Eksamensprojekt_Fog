<!-- Header & imports -->
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="includes/header.inc" %>
<!-- End header -->

<!-- Container -->
<div class="container min-vh-100">
    <!-- Row -->
    <div class="row">

        <!-- Main -->
        <main role="main">
            <!-- Section -->
            <section class="col-12">

                <!-- Row -->
                <div class="row">

                    <!-- Article -->
                    <article role="article" class="col mt-5">


                        <h2>Køb din nye carport hos Fog! </h2>
                        <p>
                        Vælg mellem vores standard Byg-Selv model eller lad os give dig et konkret tilbud på en carport i nøjagtig de mål, som du ønsker - med eller uden redskabsrum.


                        <br/>
                        <h5> Standard modeller </h5>
                        Leveres som Byg-selv sæt - usamlet og ubehandlet!
                        Altid kvalitetsmaterialer.
                        Udførlig byggevejledning til carport og spær medfølger.

                        Levering i hele Danmark inden for ca. 10 hverdage.
                        <br/>
                        <br/>

                        <h5>Carport i tilpassede mål?</h5>
                        Med et specialudviklet computerprogram kan vi lynhurtigt beregne prisen og udskrive en skitsetegning på en carport indenfor vores standardprogram - i de mål du ønsker.
                        Tilbud og skitsetegning fremsendes med post hurtigst muligt.
                        </p>

                        <br>
                        <form name="testDrawing" action="FrontController" method="POST">
                            <input type="hidden" name="target" value="drawing">
                            <input class="btn btn-info" type="submit" value="test drawing">
                        </form>
                        <br>
                        <form name="testDrawingFront" action="FrontController" method="POST">
                            <input type="hidden" name="target" value="drawingFront">
                            <input class="btn btn-info" type="submit" value="test drawingFront">
                        </form>
                        <br>

                    </article>
                    <!-- End article -->

                    <!-- Section -->
                    <section class="col mt-5">
                        <!-- Carousel -->
                        <div id="carouselExampleIndicators" class="carousel slide carousel-fade" data-ride="carousel">
                            <ol class="carousel-indicators">
                                <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                                <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                                <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
                            </ol>
                            <div class="carousel-inner">
                                <div class="carousel-item active">
                                    <img class="d-block w-100" src="images/logo.png" alt="First slide">
                                    <div class="carousel-caption d-none d-md-block">
                                        <h5>Overskrift</h5>
                                        <p>Køb køb køb køb ALT</p>
                                    </div>
                                </div>
                                <div class="carousel-item">
                                    <img class="d-block w-100" src="images/logo.png" alt="Second slide">
                                    <div class="carousel-caption d-none d-md-block">
                                        <h5>Overskrift</h5>
                                        <p>Køb køb køb køb ALT</p>
                                    </div>
                                </div>
                                <div class="carousel-item">
                                    <img class="d-block w-100" src="images/logo.png" alt="Third slide">
                                    <div class="carousel-caption d-none d-md-block">
                                        <h5>Overskrift</h5>
                                        <p>Køb køb køb køb ALT</p>
                                    </div>
                                </div>

                                <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button"
                                   data-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Previous</span>
                                </a>
                                <a class="carousel-control-next" href="#carouselExampleIndicators" role="button"
                                   data-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="sr-only">Next</span>
                                </a>
                            </div>
                        </div>
                        <!-- End carousel -->
                    </section>
                    <!-- End section -->
                </div>
                <!-- End row -->

            </section>
            <!-- End section -->
        </main>
        <!-- End main -->
    </div>
    <!-- End row -->
</div>
<!-- End container -->

<!-- Footer -->
<%@include file="includes/footer.inc" %>
<!-- End footer -->

