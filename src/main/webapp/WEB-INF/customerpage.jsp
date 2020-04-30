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
                <h1>Fog første udkast</h1>
            </div>

            <!-- Group cards -->
            <div class="card-group">
                <!-- Standard card -->
                <section class="card border-dark">
                    <div class="card-body">
                        <img class="card-img img-fluid" src="images/filler.png" alt="Standard byg">
                        <h2 class="card-title">Overskrift</h2>
                        <h5 class="card-subtitle">Overskrift</h5>
                        <p class="card-text">Rum marooned jack Plate Fleet hogshead league gaff coffer barque lugger.
                            Six pounders nipperkin blow the man down provost nipper dead men tell no tales careen black
                            spot Sail ho brig. Driver capstan Chain Shot rope's end squiffy marooned Pirate Round jury
                            mast bucko quarter.
                            Port jury mast matey long boat list spike reef gaff trysail mutiny. Long clothes cackle
                            fruit Sea Legs keelhaul warp bucko pirate tack Nelsons folly fore. Chandler snow piracy
                            dance the hempen jig no prey, no pay six pounders square-rigged league stern poop deck.
                            Provost chantey rutters ay
                            e Blimey fore pinnace walk the plank quarter line. Execution dock crimp take a caulk tender
                            bilged on her anchor yawl spyglass case shot lass strike colors. Trysail grog blossom to go
                            on account sutler maroon topmast landlubber or just lubber bilged on her anchor belay
                            broadside.
                            Trysail grog blossom to go on account sutler maroon topmast landlubber or just lubber bilged
                            on her anchor belay broadside.
                        </p>
                        <a class="card-link btn btn-dark"
                           href="FrontController?target=redirect&destination=carportstandard">Standard</a>
                    </div>
                </section>
                <!-- End standard card -->

                <!-- Customize card -->
                <section class="card border-dark">
                    <div class="card-body">
                        <img class="card-img img-fluid" src="images/filler.png" alt="Byg selv">
                        <h2 class="card-title">Overskrift</h2>
                        <h5 class="card-subtitle">Overskrift</h5>
                        <p class="card-text">Rum marooned jack Plate Fleet hogshead league gaff coffer barque lugger.
                            Six pounders nipperkin blow the man down provost nipper dead men tell no tales careen black
                            spot Sail ho brig. Driver capstan Chain Shot rope's end squiffy marooned Pirate Round jury
                            mast bucko quarter.
                            Port jury mast matey long boat list spike reef gaff trysail mutiny. Long clothes cackle
                            fruit Sea Legs keelhaul warp bucko pirate tack Nelsons folly fore. Chandler snow piracy
                            dance the hempen jig no prey, no pay six pounders square-rigged league stern poop deck.
                            Provost chantey rutters ay
                            e Blimey fore pinnace walk the plank quarter line. Execution dock crimp take a caulk tender
                            bilged on her anchor yawl spyglass case shot lass strike colors. Trysail grog blossom to go
                            on account sutler maroon topmast landlubber or just lubber bilged on her anchor belay
                            broadside.
                            Trysail grog blossom to go on account sutler maroon topmast landlubber or just lubber bilged
                            on her anchor belay broadside.
                        </p>
                        <form action="FrontController" method="POST">
                            <input type="hidden" name="target" value="carportcustomize">
                            <button class="btn btn-dark" type="submit">Byg selv</button>
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