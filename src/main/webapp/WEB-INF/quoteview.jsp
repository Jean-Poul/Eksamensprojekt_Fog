<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/headerlogout.inc" %>
<!-- End header -->


<!-- Container -->
<div class="container min-vh-100">
    <!-- Row -->
    <div class="row">

        <!-- Section -->
        <section class="col-12">
            <c:forEach var="info" items="${requestScope.userproposition}">
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
                                >Kunde info</a>
                            </h5>
                        </div><!-- card header -->

                        <div id="idinfo"
                             class="collapse show"
                             data-parent="#cardid"
                             aria-labelledby="idheading"
                        >
                            <div class="card-body">
                                <p>bruger forspørgsel id request her: ${info.user_proposition_id}</p>
                                <p>fornavn request her: ${info.name}</p>
                                <p>ordre id request her: ${info.orders_id}</p>
                                <p>ordre dato request her: ${info.order_date}</p>
                                <p>order status request her: ${info.status}</p>
                            </div>
                            <a class="card-link btn btn-dark" href="#">Opdater</a>
                        </div><!-- card collapse -->
                    </div><!-- card -->

                </div>
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
                                <p>adresse request her: ${info.address}</p>
                                <p>postnummer request her: ${info.zipcodeCity}</p>
                                <p>email request her: ${info.email}</p>
                                <p>telefon request her: ${info.phone}</p>
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
                                <p>carport bredde request her: ${info.carport_width}</p>
                                <p>carport længde request her: ${info.carport_length}</p>
                                <p>tap type request her: ${info.roof_type}</p>
                                <p>tag vinkel request her: ${info.pitch}</p>
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
                                <p>redskabsrum bredde request her: ${info.shed_width}</p>
                                <p>redskabsrum længde request her: ${info.shed_length}</p>
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
                                <p>bemærkning request her: ${info.comments}</p>
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
                                <p><b>pris request her: PRIS MANGLER</b></p>
                            </div>
                            <a class="card-link btn btn-dark" href="#">Opdater</a>
                        </div><!-- card collapse -->
                    </div><!-- card -->

                </div>


            <!-- End total price -->
            <!-- Button choices -->
            <div class="container d-flex justify-content-center my-3">
                <button type="button" class="btn btn-success col-2 mx-2">Godkend</button>
                <button type="button" class="btn btn-primary col-2 mx-2">Se tegning</button>

                    <form class="btn btn-danger col-2 mx-2" name="deleteQuote" action="FrontController" method="post">
                        <input type="hidden" name="target" value="rejectQuote">
                        <input type="hidden" name="quoteID" value="${info.user_proposition_id}">
                        <input type="submit" class="btn text-white" value="Afvis"
                               onclick="return confirm('Er du sikker på at du vil slette?')"/>
                    </form>
                </c:forEach>

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

