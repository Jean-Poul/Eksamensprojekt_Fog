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
    <h1>Fog første udkast</h1>
     <p>
         Rum marooned jack Plate Fleet hogshead league gaff coffer barque lugger. Six pounders nipperkin blow the man down provost nipper dead men tell no tales careen black spot Sail ho brig. Driver capstan Chain Shot rope's end squiffy marooned Pirate Round jury mast bucko quarter.
         Port jury mast matey long boat list spike reef gaff trysail mutiny. Long clothes cackle fruit Sea Legs keelhaul warp bucko pirate tack Nelsons folly fore. Chandler snow piracy dance the hempen jig no prey, no pay six pounders square-rigged league stern poop deck.
         Provost chantey rutters aye Blimey fore pinnace walk the plank quarter line. Execution dock crimp take a caulk tender bilged on her anchor yawl spyglass case shot lass strike colors. Trysail grog blossom to go on account sutler maroon topmast landlubber or just lubber bilged on her anchor belay broadside.
         Trysail grog blossom to go on account sutler maroon topmast landlubber or just lubber bilged on her anchor belay broadside.
     </p>
        <form name="testBeregning" action="FrontController" method="POST">
            <input type="hidden" name="target" value="CarportCalcPage">
            <input class="btn btn-info" type="submit" value="Så test mig dog! Så laver jeg en sysout">
        </form>
        </article>
                <!-- End article -->

                <!-- Section -->
            <section class="col">
                <!-- Carousel -->
                <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
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

                    <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>

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

