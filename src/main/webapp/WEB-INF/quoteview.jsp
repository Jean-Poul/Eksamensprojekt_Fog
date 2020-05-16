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

            <!-- Back button -->
            <div class="container d-flex justify-content-center my-5">
                <form class="btn btn-primary col-2 mx-2" name="back" action="FrontController" method="POST">
                    <input type="hidden" name="target" value="returnAdmin">
                    <input class="btn text-white" type="submit" value="Tilbage">
                </form>
            </div>
            <!-- End back button -->

            <c:forEach var="info" items="${requestScope.userProposition}">
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
                        <div class="card-body d-flex">
                            <div class="col">

                                <p>Bruger id: <b>${info.user_proposition_id}</b></p>
                                <p>Fornavn: <b>${info.name}</b></p>
                                <p>Ordre dato: <b>${info.order_date}</b></p>
                                <p>Order status: <b>${info.status}</b></p>
                                <p>Adresse: <b>${info.address}</b></p>
                                <p>Postnummer: <b>${info.zipcodeCity}</b></p>
                                <p>Email: <b>${info.email}</b></p>
                                <p>Telefon: <b>${info.phone}</b></p>
                                <p>Bemærkning: <b>${info.comments}</b></p>

                            </div>
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
                                        <label for="quoteStatus">Status</label>
                                        <select class="custom-select" id="quoteStatus" name="status">
                                            <option>Vælg status</option>
                                            <option>Forespørgsel</option>
                                            <option>Tilbud</option>
                                            <option>Godkend</option>
                                            <option>Afvis</option>
                                            <option>Ordre</option>
                                        </select>
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
                                    <!-- Update button -->
                                    <input class="btn btn-dark btn-block" type="submit" value="Opdater"
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
                            <div class="col">
                                <p>Ordre id: <b>${info.orders_id}</b></p>
                                <p>Carport bredde: <b>${info.carport_width}</b></p>
                                <p>Carport længde: <b>${info.carport_length}</b></p>
                                <p>Tag type: <b>${info.roof_type}</b></p>
                                <p>Tag materiale: <b>${info.roof_material}</b></p>
                                <p>Tag vinkel: <b>${info.pitch}</b></p>
                                <p>Redskabsrum bredde: <b>${info.shed_width}</b></p>
                                <p>Redskabsrum længde: <b>${info.shed_length}</b></p>
                            </div>

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
                                            <option value="0">Carport med fladt tag</option>
                                            <option value="1">Carport med rejsning</option>
                                        </select>
                                    </div>

                                    <div id="flatRoof" style="display: block">
                                        <div class="form-group">
                                            <label for="RoofFlat">Tag</label>
                                            <select class="custom-select" id="RoofFlat" name="roofFlat" required>
                                                <option>Vælg materiale</option>
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
                                                <option>Vælg materiale</option>
                                                <c:forEach var="rRaised" items="${requestScope.roofRaised}">
                                                    <option value="${rRaised.roofRaisedOptions}">${rRaised.roofRaisedOptions}</option>
                                                </c:forEach>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label for="RoofOptionDegrees">Taghældning</label>
                                            <select class="custom-select" id="RoofOptionDegrees"
                                                    name="roofOptionDegrees">
                                                <option>Vælg hældning</option>
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
                                            <option value="${info.shed_width}">${info.shed_width}</option>
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
                                            <option value="${info.shed_length}">${info.shed_length}</option>
                                            <c:forEach var="sLength" items="${requestScope.shedLength}">
                                                <option class="optionDisabled" value="${sLength.shedLengthOption}"
                                                        disabled>${sLength.shedLengthOption} cm
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <!-- End select options -->
                                    <!-- Update button -->
                                    <input class="btn btn-dark btn-block" type="submit" value="Opdater"
                                           onclick="return confirm('Er du sikker på at du vil lave en opdatering?')"/>
                                    <!-- End update button -->
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

                    <div id="priceinfo"
                         class="collapse show"
                         data-parent="#priceid"
                         aria-labelledby="priceheading"
                    >
                        <div class="card-body d-flex">
                            <div class="col">
                                <p>Pris inkl. moms</p>
                                <p><b>pris request her: PRIS MANGLER</b></p>
                            </div>

                            <div class="col">
                                <form class="form-group card-link" name="" action="FrontController"
                                      method="post">

                                    <input type="hidden" name="target" value="">
                                    <div class="form-group">
                                        <label for="quotePrice">Pris</label>
                                        <input class="form-control" id="quotePrice" type="text" name="totalPrice"
                                               value="${requestScope.totalPrice}">
                                    </div>
                                    <!-- Update button -->
                                    <input class="btn btn-dark btn-block" type="submit" value="Opdater"
                                           onclick="return confirm('Er du sikker på at du vil lave en opdatering?')"/>
                                    <!-- End update button -->
                                </form>
                            </div>
                        </div>
                    </div><!-- card collapse -->
                </div><!-- card -->

            </div>
            <!-- End total price -->
            <!-- Navigate button choices -->
            <div class="container d-flex justify-content-center my-3">
                <form class="btn btn-success col-2 mx-2" name="" action="FrontController" method="POST">
                    <input type="hidden" name="target" value="">
                    <input class="btn text-white" type="submit" value="Se tilbud">
                </form>

                <form class="btn btn-primary col mx-2" name="drawingView" action="FrontController" method="POST">
                    <input type="hidden" name="target" value="drawing">
                    <input type="hidden" name="orderID" value="${info.orders_id}">
                    <input type="hidden" name="viewID" value="${info.user_proposition_id}">
                    <input class="btn text-white" type="submit" value="Se tegning & stykliste">
                </form>

                <form class="btn btn-danger col-2 mx-2" name="deleteQuote" action="FrontController" method="post">
                    <input type="hidden" name="target" value="rejectQuote">
                    <input type="hidden" name="quoteID" value="${info.user_proposition_id}">
                    <input class="btn text-white" type="submit" value="Slet forespørgsel"
                           onclick="return confirm('Er du sikker på at du vil slette?')"/>
                </form>
                </c:forEach>

            </div>
            <!-- End navigate button choices -->

        </section>
        <!-- End section -->
    </div>
    <!-- End row -->
</div>
<!-- End container -->

<!-- Footer -->
<%@include file="../includes/footer.inc" %>
<!-- End footer -->