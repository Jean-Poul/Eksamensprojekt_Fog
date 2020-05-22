<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../../includes/headerlogout.inc" %>
<!-- End header -->


<!-- Container -->
<div class="container min-vh-100">
    <!-- Row -->
    <div class="row">
        <!-- Section -->
        <section class="col-12">
            <c:forEach var="info" items="${requestScope.userProposition}">
            <!-- Customer info -->
            <div class="accordion mt-5" id="cardid">
                <!-- Back button -->
                <form class="form-group" name="back" action="FrontController" method="POST">
                    <input type="hidden" name="target" value="returnAdmin">
                    <input class="btn btn-primary text-white" type="submit" value="Tilbage">
                </form>
                <!-- End back button -->

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
                        <div class="card-body d-flex">
                            <!-- Left column -->
                            <div class="col">

                                <p class="text-primary">Bruger id:</p>
                                <p class="text-primary">Fornavn:</p>
                                <p class="text-primary">Ordre dato:</p>
                                <p class="text-primary">Adresse:</p>
                                <p class="text-primary">Postnummer:</p>
                                <p class="text-primary">Email:</p>
                                <p class="text-primary">Telefon:</p>
                                <p class="text-primary">Bemærkning:</p>
                                <p class="text-primary">Order status:</p>

                            </div>
                            <!-- End left column -->
                            <!-- Middle column -->
                            <div class="col bg-light">

                                <p><b>${info.user_proposition_id}</b></p>
                                <p><b>${info.name}</b></p>
                                <p><b>${info.order_date}</b></p>
                                <p><b>${info.address}</b></p>
                                <p><b>${info.zipcodeCity}</b></p>
                                <p><b>${info.email}</b></p>
                                <p><b>${info.phone}</b></p>
                                <p><b>${info.comments}</b>.</p>
                                <p><b>${info.status}</b></p>

                            </div>
                            <!-- End middle column -->
                            <!-- Right column -->
                            <div class="col">
                                <form class="form-group card-link" name="updateCustomerInfo" action="FrontController"
                                      method="post">

                                    <input type="hidden" name="target" value="updateQuoteUser">
                                    <input type="hidden" name="quoteID" value="${info.user_proposition_id}">
                                    <input type="hidden" name="orderID" value="${info.orders_id}">

                                    <div class="form-group">
                                        <label for="quoteName">Fornavn</label>
                                        <input class="form-control" id="quoteName" type="text" name="name"
                                               value="${info.name}">
                                    </div>

                                    <div class="form-group">
                                        <label for="quoteAddress">Adresse</label>
                                        <input class="form-control" id="quoteAddress" type="text" name="address"
                                               value="${info.address}">
                                    </div>
                                    <div class="form-group">
                                        <label for="quoteZipcode">Postnummer</label>
                                        <input class="form-control" id="quoteZipcode" type="text" name="zipcode"
                                               value="${info.zipcodeCity}">
                                    </div>

                                    <div class="form-group">
                                        <label for="quoteEmail">Email</label>
                                        <input class="form-control" id="quoteEmail" type="text" name="email"
                                               value="${info.email}">
                                    </div>

                                    <div class="form-group">
                                        <label for="quotePhone">Telefon</label>
                                        <input class="form-control" id="quotePhone" type="text" name="phone"
                                               value="${info.phone}">
                                    </div>

                                    <div class="form-group">
                                        <label for="quoteComment">Bemærkning</label>
                                        <input class="form-control" id="quoteComment" type="text" name="comments"
                                               value="${info.comments}">
                                    </div>

                                    <div class="form-group">
                                        <label for="quoteStatus">Status</label>
                                        <select class="custom-select" id="quoteStatus" name="status">
                                            <option value="${info.status}">${info.status}</option>
                                            <option>Forespørgsel</option>
                                            <option>Tilbud</option>
                                            <option>Godkend</option>
                                            <option>Afvis</option>
                                            <option>Ordre</option>
                                        </select>
                                    </div>
                                    <!-- End right column -->
                                    <!-- Update button -->
                                    <input class="btn btn-success btn-block" type="submit" value="Opdater"
                                           onclick="return confirm('Er du sikker på at du vil lave en opdatering?')"/>
                                    <!-- End update button -->
                                </form>
                            </div>

                        </div>

                    </div><!-- card collapse -->
                </div><!-- card -->

            </div>
            <!-- End customer info -->

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
                        <div class="card-body d-flex">
                            <!-- Left column -->
                            <div class="col">

                                <p class="text-primary">Ordre id:</p>
                                <p class="text-primary">Carport bredde:</p>
                                <p class="text-primary">Carport længde:</p>
                                <p class="text-primary">Tag type:</p>
                                <p class="text-primary">Tag materiale:</p>
                                <p class="text-primary">Tag vinkel:</p>
                                <p class="text-primary">Redskabsrum bredde:</p>
                                <p class="text-primary">Redskabsrum længde:</p>

                            </div>
                            <!-- End left column -->
                            <!-- Middle column -->
                            <div class="col bg-light">

                                <p><b>${info.orders_id}</b></p>
                                <p><b>${info.carport_width}</b></p>
                                <p><b>${info.carport_length}</b></p>
                                <p><b>${info.roof_type}</b></p>
                                <p><b>${info.roof_material}</b></p>
                                <p><b>${info.pitch}</b></p>
                                <p><b>${info.shed_width}</b></p>
                                <p><b>${info.shed_length}</b></p>

                            </div>
                            <!-- End middle column -->
                            <!-- Right column -->
                            <div class="col">
                                <form class="form-group card-link" name="updateCustomerOrder" action="FrontController"
                                      method="post">
                                    <input type="hidden" name="target" value="updateQuoteOrder">
                                    <input type="hidden" name="orderID" value="${info.orders_id}">
                                    <input type="hidden" name="quoteID" value="${info.user_proposition_id}">

                                    <!-- Select options -->
                                    <div class="form-group">
                                        <label for="CarportWidth">Carport bredde</label>
                                        <select class="custom-select" id="CarportWidth" name="carportWidth">
                                            <option value="${info.carport_width}">${info.carport_width}</option>
                                            <c:forEach var="cWidth" items="${requestScope.carportWidth}">
                                                <option value="${cWidth.carportWidthOption}">${cWidth.carportWidthOption}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="CarportLength">Carport længde</label>
                                        <select class="custom-select" id="CarportLength" name="carportLength">
                                            <option value="${info.carport_length}">${info.carport_length}</option>
                                            <c:forEach var="cLength" items="${requestScope.carportLength}">
                                                <option value="${cLength.carportLengthOptions}">${cLength.carportLengthOptions}</option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="RoofOption">Tag type</label>
                                        <select class="custom-select" id="RoofOption" name="roofOption">
                                            <option value="${info.roof_type}">${info.roof_type}</option>
                                            <option value="0">Carport med fladt tag</option>
                                            <option value="1">Carport med rejsning</option>
                                        </select>
                                    </div>

                                    <div id="flatRoof" style="display: block">
                                        <div class="form-group">
                                            <label for="RoofFlat">Tag</label>
                                            <select class="custom-select" id="RoofFlat" name="roofFlat" required>
                                                <option value="${info.roof_material}">${info.roof_material}</option>
                                                <c:forEach var="rFlat" items="${requestScope.roofFlat}">
                                                    <option value="${rFlat.roofFlatOptions}">${rFlat.roofFlatOptions}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div id="raisedRoof" style="display: none">
                                        <div class="form-group">
                                            <label for="RoofRaised">Tag</label>
                                            <select class="custom-select" id="RoofRaised" name="roofRaised">
                                                <option value="${info.roof_material}">${info.roof_material}</option>
                                                <c:forEach var="rRaised" items="${requestScope.roofRaised}">
                                                    <option value="${rRaised.roofRaisedOptions}">${rRaised.roofRaisedOptions}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label for="RoofOptionDegrees">Taghældning</label>
                                            <select class="custom-select" id="RoofOptionDegrees"
                                                    name="roofOptionDegrees">
                                                <option value="${info.pitch}">${info.pitch}</option>
                                                <c:forEach var="degree" items="${requestScope.roofDegree}">
                                                    <option value="${degree.roofDegreeOption}">${degree.roofDegreeOption}
                                                        grader
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <label for="ShedWidth">Redskabsrum bredde</label>
                                        <select class="custom-select selectpicker" id="ShedWidth" name="shedWidth">
                                            <option value="${info.shed_width}">${info.shed_width} cm</option>
                                            <option value="0">0 cm</option>
                                            <c:forEach var="sWidth" items="${requestScope.shedWidth}">
                                                <option class="optionDisabled" value="${sWidth.shedWidthOption}"
                                                        disabled>${sWidth.shedWidthOption} cm
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <div class="form-group">
                                        <label for="ShedLength">Redskabsrum længde</label>
                                        <select class="custom-select selectpicker" id="ShedLength" name="shedLength">
                                            <option value="${info.shed_length}">${info.shed_length} cm</option>
                                            <option value="0">0 cm</option>
                                            <c:forEach var="sLength" items="${requestScope.shedLength}">
                                                <option class="optionDisabled" value="${sLength.shedLengthOption}"
                                                        disabled>${sLength.shedLengthOption} cm
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <!-- End select options -->
                                    <!-- Update button -->
                                    <input class="btn btn-success btn-block" type="submit" value="Opdater"
                                           onclick="return confirm('Er du sikker på at du vil lave en opdatering?')"/>
                                    <!-- End update button -->
                                    <!-- End right column -->
                                </form>
                            </div>

                        </div>

                    </div><!-- card collapse -->
                </div><!-- card -->

            </div>
            <!-- End customer quote info -->


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

                    <div id="priceinfo" class="collapse show" data-parent="#priceid" aria-labelledby="priceheading">
                        <div class="card-body d-flex">
                            <!-- Left column -->
                            <div class="col">

                                <p class="text-primary">Pris inkl. moms og dækningsgrad</p>

                            </div>
                            <!-- End left column -->
                            <!-- Middle column -->
                            <div class="col bg-light">

                            </div>
                            <!-- End middle column -->
                            <!-- Right column -->
                            <div class="col">
                            <form class="form-group card-link" name="updateCustomerPrice" action="FrontController" method="post">
                                <input type="hidden" name="target" value="updateQuotePrice">
                                <input type="hidden" name="orderID" value="${info.orders_id}">
                                <input type="hidden" name="quoteID" value="${info.user_proposition_id}">
                                <div class="container">
                                    <label for="quoteCoverage">Dækningsgrad</label>
                                    <input class="form-control" id="quoteCoverage" type="text" name="quoteCoverage"
                                           value="${info.coverage}">
                                </div>

                                <div class="container">
                                    <div class="form-group">
                                        <label for="quotePrice">Pris</label>
                                        <input class="form-control" id="quotePrice" type="text" name="totalPrice"
                                               value="${requestScope.totalPrice}" disabled>
                                    </div>
                                    <!-- Update button -->
                                    <input class="btn btn-success btn-block" type="submit" value="Opdater"
                                           onclick="return confirm('Er du sikker på at du vil lave en opdatering?')"/>
                                    <!-- End update button -->
                                </div>
                                    <!-- End right column -->
                            </form>

                        </div>

                    </div>

                </div><!-- card collapse -->
            </div><!-- card -->

    </div>
            <!-- End total price -->
            <!-- Navigate button choices -->
            <div class="container d-flex justify-content-center my-5">

                <form class="form-group" name="" action="FrontController" method="POST">
                    <input type="hidden" name="target" value="adminReceipt">
                    <input type="hidden" name="orderID" value="${info.orders_id}">
                    <input type="hidden" name="viewID" value="${info.user_proposition_id}">
                    <input class="btn btn-success text-white" type="submit" value="Se tilbud">
                </form>

        <form class="form-group mx-5" name="drawingView" action="FrontController" method="POST">
            <input type="hidden" name="target" value="drawing">
            <input type="hidden" name="orderID" value="${info.orders_id}">
            <input type="hidden" name="viewID" value="${info.user_proposition_id}">
            <input class="btn btn-info text-white" type="submit" value="Se tegning & stykliste">
        </form>

        <form class="form-group" name="deleteQuote" action="FrontController" method="post">
            <input type="hidden" name="target" value="rejectQuote">
            <input type="hidden" name="quoteID" value="${info.user_proposition_id}">
            <input class="btn btn-danger text-white" type="submit" value="Slet forespørgsel"
                   onclick="return confirm('Er du sikker på at du vil slette?')"/>
        </form>

    </div>
    <!-- End navigate button choices -->
    </c:forEach>
    </section>
    <!-- End section -->
</div>
<!-- End row -->
</div>
<!-- End container -->

<!-- Footer -->
<%@include file="../../includes/footer.inc" %>
<!-- End footer -->