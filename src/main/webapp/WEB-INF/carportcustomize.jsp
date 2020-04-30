<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/header.inc" %>
<!-- End header -->

<!-- Container -->
<div class="container min-vh-100">

    <form action="FrontController" method="POST">
        <!-- Row -->
        <div class="row mt-4">
            <!-- Column -->
            <div class="col-12 col-sm-6 col-md-4">
                <!-- Order option -->
                <!-- Carport width -->
                <div class="form-group">
                    <label for="CarportWidth">Carport bredde</label>
                    <select class="form-control" id="CarportWidth">
                        <option>Vælg bredde</option>
                        <c:forEach var="element" items="${requestScope.carportwidth}">
                            <option value="${element.carportWidthOption}">${element.carportWidthOption}</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- End carport width -->
                <!-- Carport length -->
                <div class="form-group">
                    <label for="CarportLength">Carport længde</label>
                    <select class="form-control" id="CarportLength">
                        <option>Vælg længde</option>
                        <c:forEach var="element" items="${requestScope.carportlength}">
                            <option value="${element.carportLengthOptions}">${element.carportLengthOptions}</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- End carport length -->
                <!-- Roof option -->
                <div class="form-group">
                    <label for="RoofOption">Tag type</label>
                    <select class="form-control" id="RoofOption">
                        <option value="0">Carport med fladt tag</option>
                        <option value="1">Carport med rejsning</option>
                    </select>
                </div>
                <!-- End roof option -->
                <!-- flat roof -->
                <div id="flatRoof" style="display: block">
                    <div class="form-group">
                        <label for="RoofFlat">Tag</label>
                        <select class="form-control" id="RoofFlat">
                            <option>Vælg tagtype/farve</option>
                            <c:forEach var="element" items="${requestScope.roofflat}">
                                <option value="${element.roofFlatOptions}">${element.roofFlatOptions}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <!-- End of flat roof -->
                <!-- Raised roof options -->
                <div id="raisedRoof" style="display: none">
                    <div class="form-group">
                        <label for="RoofRaised">Tag</label>
                        <select class="form-control" id="RoofRaised">
                            <option>Vælg tag type</option>
                            <c:forEach var="element" items="${requestScope.roofraised}">
                                <option value="${element.roofRaisedOptions}">${element.roofRaisedOptions}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <!-- End raised roof options -->
                    <!-- Roof degrees -->
                    <div class="form-group">
                        <label for="RoofOptionDegrees">Taghældning</label>
                        <select class="form-control" id="RoofOptionDegrees">
                            <option>Vælg hældning</option>
                            <c:forEach var="element" items="${requestScope.roofdegree}">
                                <option value="${element.roofDegreeOption}">${element.roofDegreeOption}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <!-- End roof degrees -->
                <!-- Shed width -->
                <div class="form-group">
                    <label for="ShedWidth">Redskabsrum bredde</label>
                    <select class="form-control" id="ShedWidth">
                        <option>Ønsker ikke redskabsrum</option>
                        <c:forEach var="element" items="${requestScope.shedWidth}">
                            <option value="${element.shedWidthOption}">${element.shedWidthOption}</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- End shed width -->
                <!-- Shed length -->
                <div class="form-group">
                    <label for="ShedLength">Redskabsrum længde</label>
                    <select class="form-control" id="ShedLength">
                        <option>Ønsker ikke redskabsrum</option>
                        <c:forEach var="element" items="${requestScope.shedLength}">
                            <option value="${element.shedLengthOption}">${element.shedLengthOption}</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- End shed length -->
                <!-- End order option -->
            </div>
            <!-- End column -->

            <!-- Column -->
            <div class="col-12 col-sm-6 col-md-4">
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
            <div class="col-12 col-sm-12 col-md-4 mb-4">

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

        </div>
        <!-- End row -->
    </form>
    <!-- End form -->
</div>
<!-- End container -->

<!-- Footer -->
<%@include file="../includes/footer.inc" %>
<!-- End footer -->

<!-- script for raised/flat roof -->
<script>
    $(document).ready(function () {
        $('#RoofOption').on('change', function () {
            if (this.value == '1') {
                $("#raisedRoof").show();
                $("#flatRoof").hide();
            } else {
                $("#raisedRoof").hide();
                $("#flatRoof").show();
            }
        });
    });
</script>