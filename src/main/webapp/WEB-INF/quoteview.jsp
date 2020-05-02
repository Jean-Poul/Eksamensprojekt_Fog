<!-- Header & imports -->
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/header.inc" %>
<!-- End header -->

<!-- Container -->
<div class="container min-vh-100">
    <!-- Row -->
    <div class="row">

            <!-- Section -->
            <section class="col-12">

                    <!-- Customer info container -->
                    <div class="container px-0">
                        <!-- Customer info -->
                        <div class="accordion" id="cardid">

                            <div class="card">
                                <div class="card-header" id="idheading">
                                    <h5 class="mb-0">
                                        <a href="#idinfo"
                                           class="collapsed"
                                           data-toggle="collapse"
                                           aria-expanded="true"
                                           aria-controls="customerid"
                                        >ID</a>
                                    </h5>
                                </div><!-- card header -->

                                <div id="idinfo"
                                     class="collapse show"
                                     data-parent="#cardid"
                                     aria-labelledby="idheading"
                                >
                                    <div class="card-body">
                                        <p>id request her</p>
                                    </div>
                                    <a class="card-link btn btn-dark" href="#">Opdater</a>
                                </div><!-- card collapse -->
                            </div><!-- card -->

                        </div>

                        <div class="accordion" id="firstnameid">

                            <div class="card">
                                <div class="card-header" id="nameheading">
                                    <h5 class="mb-0">
                                        <a href="#nameinfo"
                                           class="collapsed"
                                           data-toggle="collapse"
                                           aria-expanded="true"
                                           aria-controls="customerfirstname"
                                        >Fornavn</a>
                                    </h5>
                                </div><!-- card header -->

                                <div id="nameinfo"
                                     class="collapse show"
                                     data-parent="#firstnameid"
                                     aria-labelledby="nameheading"
                                >
                                    <div class="card-body">
                                        <p>fornavn request her</p>
                                    </div>
                                    <a class="card-link btn btn-dark" href="#">Opdater</a>
                                </div><!-- card collapse -->
                            </div><!-- card -->

                        </div>

                        <div class="accordion" id="lastnameid">

                            <div class="card">
                                <div class="card-header" id="lastnameheading">
                                    <h5 class="mb-0">
                                        <a href="#lastnameinfo"
                                           class="collapsed"
                                           data-toggle="collapse"
                                           aria-expanded="true"
                                           aria-controls="customerlastname"
                                        >Efternavn</a>
                                    </h5>
                                </div><!-- card header -->

                                <div id="lastnameinfo"
                                     class="collapse show"
                                     data-parent="#lastnameid"
                                     aria-labelledby="lastnameheading"
                                >
                                    <div class="card-body">
                                        <p>efternavn request her</p>
                                    </div>
                                    <a class="card-link btn btn-dark" href="#">Opdater</a>
                                </div><!-- card collapse -->
                            </div><!-- card -->

                        </div>

                        <div class="accordion" id="userid">

                            <div class="card">
                                <div class="card-header" id="userheading">
                                    <h5 class="mb-0">
                                        <a href="#userinfo"
                                           class="collapsed"
                                           data-toggle="collapse"
                                           aria-expanded="true"
                                           aria-controls="username"
                                        >Brugernavn</a>
                                    </h5>
                                </div><!-- card header -->

                                <div id="userinfo"
                                     class="collapse show"
                                     data-parent="#userid"
                                     aria-labelledby="userheading"
                                >
                                    <div class="card-body">
                                        <p>brugernavn request her</p>
                                    </div>
                                    <a class="card-link btn btn-dark" href="#">Opdater</a>
                                </div><!-- card collapse -->
                            </div><!-- card -->

                        </div>


                    <!-- End customer info container -->
                    <!-- End customer info -->

                    <!-- Shipment info -->
                    <div class="accordion" id="shipmentinfoid">

                        <div class="card">
                            <div class="card-header" id="shipmentheading">
                                <h5 class="mb-0">
                                    <a href="#shipmentinfo"
                                       class="collapsed"
                                       data-toggle="collapse"
                                       aria-expanded="true"
                                       aria-controls="shipmentinfobox"
                                    >Fragt adresse</a>
                                </h5>
                            </div><!-- card header -->

                            <div id="shipmentinfo"
                                 class="collapse show"
                                 data-parent="#shipmentinfoid"
                                 aria-labelledby="shipmentheading"
                            >
                                <div class="card-body">
                                    <p>adresse request her</p>
                                    <p>postnummer request her</p>
                                    <p>email request her</p>
                                    <p>telefon request her</p>
                                </div>
                                <a class="card-link btn btn-dark" href="#">Opdater</a>
                            </div><!-- card collapse -->
                        </div><!-- card -->

                    </div>
                    <!-- End shipment info -->
                    <!-- Customer quote info -->
                    <div class="accordion" id="quoteid">

                        <div class="card">
                            <div class="card-header" id=quoteheading">
                                <h5 class="mb-0">
                                    <a href="#quoteinfo"
                                       class="collapsed"
                                       data-toggle="collapse"
                                       aria-expanded="true"
                                       aria-controls="quoteinfobox"
                                    >Kunde har efterspurgt</a>
                                </h5>
                            </div><!-- card header -->

                            <div id="quoteinfo"
                                 class="collapse show"
                                 data-parent="#quoteid"
                                 aria-labelledby="quoteheading"
                            >
                                <div class="card-body">
                                    <p>carport bredde request her</p>
                                    <p>carport længde request her</p>
                                    <p>tap type request her</p>
                                    <p>tag vinkel request her</p>
                                </div>
                                <a class="card-link btn btn-dark" href="#">Opdater</a>
                            </div><!-- card collapse -->
                        </div><!-- card -->

                    </div>
                    <!-- End customer quote info -->

                <!-- Customer add on info -->
                    <div class="accordion" id="addonid">

                        <div class="card">
                            <div class="card-header" id=addonheading">
                                <h5 class="mb-0">
                                    <a href="#addoninfo"
                                       class="collapsed"
                                       data-toggle="collapse"
                                       aria-expanded="true"
                                       aria-controls="addoninfobox"
                                    >Kunde tilføjelser</a>
                                </h5>
                            </div><!-- card header -->

                            <div id="addoninfo"
                                 class="collapse show"
                                 data-parent="#addonid"
                                 aria-labelledby="addonheading"
                            >
                                <div class="card-body">
                                    <p>redskabsrum bredde request her</p>
                                    <p>redskabsrum længde request her</p>
                                </div>
                                <a class="card-link btn btn-dark" href="#">Opdater</a>
                            </div><!-- card collapse -->
                        </div><!-- card -->

                    </div>
                <!-- End customer add on info -->
                <!-- Customer remark -->
                    <div class="accordion" id="remarkid">

                        <div class="card">
                            <div class="card-header" id=remarkheading">
                                <h5 class="mb-0">
                                    <a href="#remarkinfo"
                                       class="collapsed"
                                       data-toggle="collapse"
                                       aria-expanded="true"
                                       aria-controls="remarkinfobox"
                                    >Bemærkning</a>
                                </h5>
                            </div><!-- card header -->

                            <div id="remarkinfo"
                                 class="collapse show"
                                 data-parent="#remarkid"
                                 aria-labelledby="remarkheading"
                            >
                                <div class="card-body">
                                    <p>bemærkning request her</p>
                                </div>
                                <a class="card-link btn btn-dark" href="#">Opdater</a>
                            </div><!-- card collapse -->
                        </div><!-- card -->

                    </div>
                <!-- End customer remark -->
                <!-- Total price -->
                    <div class="accordion" id="priceid">

                        <div class="card">
                            <div class="card-header" id=priceheading">
                                <h5 class="mb-0">
                                    <a href="#priceinfo"
                                       class="collapsed"
                                       data-toggle="collapse"
                                       aria-expanded="true"
                                       aria-controls="priceinfobox"
                                    >Total pris</a>
                                </h5>
                            </div><!-- card header -->

                            <div id="priceinfo"
                                 class="collapse show"
                                 data-parent="#priceid"
                                 aria-labelledby="priceheading"
                            >
                                <div class="card-body">
                                    <p>Pris inkl. moms</p>
                                    <p><b>pris request her</b></p>
                                </div>
                                <a class="card-link btn btn-dark" href="#">Opdater</a>
                            </div><!-- card collapse -->
                        </div><!-- card -->

                    </div>

                    </div>

                <!-- End total price -->
                <!-- Button choices -->
                <div class="container d-flex justify-content-center my-3">
                    <button type="button" class="btn btn-success col-2 mx-2">Godkend</button>
                    <button type="button" class="btn btn-primary col-2 mx-2">Se tegning</button>
                    <button type="button" class="btn btn-danger col-2 mx-2">Afvis</button>
                </div>
                <!-- End button choices -->

    </section>
    <!-- End section -->

</div>
<!-- End row -->
</div>
<!-- End container -->

<!-- Footer -->
<%@include file="../includes/footer.inc" %>
<!-- End footer -->

