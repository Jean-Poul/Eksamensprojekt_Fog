<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/header.inc" %>
<!-- End header -->

<!-- Container -->
<div class="container min-vh-100">
    <!-- Row -->
    <div class="row">

        <!-- Section -->
        <section class="col-12">

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
                            <p>id request her</p>
                            <p>fornavn request her</p>
                            <p>efternavn request her</p>
                            <p>brugernavn request her</p>
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


            <!-- End total price -->
            <!-- Button choices -->
            <div class="container d-flex justify-content-center my-3">
                <button type="button" class="btn btn-success col-2 mx-2">Godkend</button>
                <button type="button" class="btn btn-primary col-2 mx-2">Se tegning</button>

                <form class="btn btn-danger col-2 mx-2" name="deleteQuote" action="FrontController" method="post">
                    <input type="hidden" name="target" value="rejectQuote">
                    <c:forEach var="delete" items="${requestScope.rejectcustomerquotes}">
                        <input type="hidden" name="quoteId" value="${delete.customerId}">
                    </c:forEach>
                    <input type="submit" class="btn text-white" value="Afvis"
                           onclick="return confirm('Er du sikker på at du vil slette?')"/>
                </form>

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

