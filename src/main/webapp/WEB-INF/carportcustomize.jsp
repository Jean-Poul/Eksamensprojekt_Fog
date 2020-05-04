<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/header.inc" %>
<!-- End header -->

<!-- Container -->
<div class="container min-vh-100">

    <form action="FrontController" method="POST" class="needs-validation" novalidate>
        <input type="hidden" name="target" value="carportCalcPage">
        <!-- Row -->
        <div class="row mt-4">
            <!-- Column -->
            <div class="col-12 col-sm-6 col-md-4">
                <!-- Order option -->
                <!-- Carport width -->
                <div class="form-group">
                    <label for="CarportWidth">Carport bredde</label>
                    <select class="custom-select" id="CarportWidth" name="carportWidth" required>
                        <option value="">Vælg bredde</option>
                        <c:forEach var="element" items="${requestScope.carportwidth}">
                            <option value="${element.carportWidthOption}">${element.carportWidthOption} cm</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- End carport width -->
                <!-- Carport length -->
                <div class="form-group">
                    <label for="CarportLength">Carport længde</label>
                    <select class="custom-select" id="CarportLength" name="carportLength" required>
                        <option value="">Vælg længde</option>
                        <c:forEach var="element" items="${requestScope.carportlength}">
                            <option value="${element.carportLengthOptions}">${element.carportLengthOptions} cm</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- End carport length -->
                <!-- Roof option -->
                <div class="form-group">
                    <label for="RoofOption">Tag type</label>
                    <select class="custom-select" id="RoofOption" name="roofOption">
                        <option value="0">Carport med fladt tag</option>
                        <option value="1">Carport med rejsning</option>
                    </select>
                </div>
                <!-- End roof option -->
                <!-- flat roof -->
                <div id="flatRoof" style="display: block">
                    <div class="form-group">
                        <label for="RoofFlat">Tag</label>
                        <select class="custom-select" id="RoofFlat" name="roofFlat" required>
                            <option value="">Vælg tagtype/farve</option>
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
                        <select class="custom-select" id="RoofRaised" name="roofRaised">
                            <option value="">Vælg tag type</option>
                            <c:forEach var="element" items="${requestScope.roofraised}">
                                <option value="${element.roofRaisedOptions}">${element.roofRaisedOptions}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <!-- End raised roof options -->
                    <!-- Roof degrees -->
                    <div class="form-group">
                        <label for="RoofOptionDegrees">Taghældning</label>
                        <select class="custom-select" id="RoofOptionDegrees" name="roofOptionDegrees">
                            <option value="0">Vælg hældning</option>
                            <c:forEach var="element" items="${requestScope.roofdegree}">
                                <option value="${element.roofDegreeOption}">${element.roofDegreeOption} grader</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <!-- End roof degrees -->
                <!-- Shed width -->
                <div class="form-group">
                    <label for="ShedWidth">Redskabsrum bredde</label>
                    <select class="custom-select selectpicker" id="ShedWidth" name="shedWidth">
                        <option value="">Ønsker ikke redskabsrum</option>
                        <c:forEach var="element" items="${requestScope.shedWidth}">
                            <option class="optionDisabled" value="${element.shedWidthOption}" disabled>${element.shedWidthOption} cm</option>
                        </c:forEach>
                    </select>
                </div>
                <!-- End shed width -->
                <!-- Shed length -->
                <div class="form-group">
                    <label for="ShedLength">Redskabsrum længde</label>
                    <select class="custom-select selectpicker" id="ShedLength" name="shedLength">
                        <option value="">Ønsker ikke redskabsrum</option>
                        <c:forEach var="element" items="${requestScope.shedLength}">
                            <option class="optionDisabled" value="${element.shedLengthOption}" disabled>${element.shedLengthOption} cm</option>
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
                    <input type="text" class="form-control" id="Name" placeholder="navn" name="name" required>
                </div>
                <div class="form-group">
                    <label for="Address">Adresse</label>
                    <input type="text" class="form-control" id="Address" placeholder="adresse" name="address" required>
                </div>
                <div class="form-group">
                    <label for="ZipCodeCity">Postnummer og By</label>
                    <input type="text" class="form-control" id="ZipCodeCity" placeholder="postnummer og by" name="zipcodeCity" required>
                </div>
                <div class="form-group">
                    <label for="Telephone">Telefon</label>
                    <input type="text" class="form-control" id="Telephone" placeholder="telefon" pattern="^\+?\d{0,13}" name="telephone" required>
                </div>
                <div class="form-group">
                    <label for="Email">Email address</label>
                    <input type="text" class="form-control" id="Email" placeholder="email" pattern="[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{1,63}"  name="email" required>
                </div>
                <!-- End user info -->
            </div>
            <!-- End column -->

            <!-- Column -->
            <div class="col-12 col-sm-12 col-md-4 mb-4">

                <!-- Text area -->
                <div class="form-group">
                    <label for="Comments">Bemærkninger</label>
                    <textarea class="form-control" id="Comments" rows="12" name="comments"></textarea>
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

<!-- script for raised/flat roof + validate -->
<script>
    $(document).ready(function () {
        $('#RoofOption').on('change', function () {
            // if roof option have value 1, show div #raisedRoof and add attribute required
            // on select box #RoofRaised and #RoofOptionDegrees. Then hide div #flatRoof and
            // remove attribute required.
            if (this.value == '1') {
                $("#raisedRoof").show();
                $("#RoofRaised").attr("required", true);
                $("#RoofOptionDegrees").attr("required", true);
                $("#flatRoof").hide();
                $("#RoofFlat").attr("required", false);
            } else {
            // else hide div #raisedRoof and remove attribute required from #RoofRaised and
            // #RoofOptionDegrees. Then show div #flatRoof and add attribute required to #RoofFlat
                $("#raisedRoof").hide();
                $("#RoofRaised").attr("required", false);
                $("#RoofOptionDegrees").attr("required", false);
                $("#flatRoof").show();
                $("#RoofFlat").attr("required", true);
            }
        });
    });
</script>

<!-- validation of shed option -->
<script>
    $(document).ready(function () {
        $("#ShedWidth,#ShedLength").on('change', function () {

            // get shed width and/or length value
            var sw = $('#ShedWidth').val();
            var sl = $('#ShedLength').val();

            // if only shed width or shed length have value or both have value
            if((sw === '' && sl !== '') || (sw !== '' && sl === '') || (sw !== '' && sl !== '')) {
                // set attribute required to select box
                $("#ShedWidth").attr("required", true);
                $("#ShedLength").attr("required", true);
            } else {
                // remove attribute required from select box
                $("#ShedWidth").attr("required", false);
                $("#ShedLength").attr("required", false);
            }
        });
    });
</script>

<!-- validate shed width option, so shed width is lower than carport width -->
<script>
    $(document).ready(function () {
        $("#CarportWidth").on('change', function () {

            // get selected value from carport width
            var cw = $("#CarportWidth").val();

            // find selected value from shed width
            var sw = $("#ShedWidth option:selected").val();

            // create array off all values from shed width
            var values = [];
            $("#ShedWidth option").each(function() {
                values.push( $(this).attr('value') );
            });

            // if shed width is larger than carport width, reset shed width select box
            if(cw <= sw) {
                $("#ShedWidth option").prop('selected', function() {
                    return this.defaultSelected;
                });
            }

            // Enable all options
            $("#ShedWidth option").prop("disabled", false).removeClass('optionDisabled');

            // loop through array and add 'disable' on option higher than carport width
            var i;
            for (i = 0; i < values.length; ++i) {
                if(values[i] >= cw){
                    $("#ShedWidth option[value*='" + values[i] + "']").prop('disabled',true).addClass('optionDisabled');

                }
            }

        });
    });
</script>

<!-- validate shed length option, so shed length is lower than carport length -->
<script>
    $(document).ready(function () {
        $("#CarportLength").on('change', function () {

            // get selected value from carport length
            var cl = $("#CarportLength").val();

            // find selected value from shed length
            var sl = $("#ShedLength option:selected").val();

            // create array off all values from shed length
            var values = [];
            $("#ShedLength option").each(function() {
                values.push( $(this).attr('value') );
            });

            // if shed length is larger than carport length, reset shed length select box
            if(cl <= sl) {
                $("#ShedLength option").prop('selected', function() {
                    return this.defaultSelected;
                });
            }

            // Enable all options
            $("#ShedLength option").prop("disabled", false).removeClass('optionDisabled');

            // loop through array and add 'disable' on option higher than carport length
            var i;
            for (i = 0; i < values.length; ++i) {
                if(values[i] >= cl){
                    $("#ShedLength option[value*='" + values[i] + "']").prop('disabled',true).addClass('optionDisabled');

                }
            }

        });
    });
</script>

<!-- user-validation -->
<script>
    // Example starter JavaScript for disabling form submissions if there are invalid fields
    (function() {
        'use strict';
        window.addEventListener('load', function() {
            // Fetch all the forms we want to apply custom Bootstrap validation styles to
            var forms = document.getElementsByClassName('needs-validation');
            // Loop over them and prevent submission
            var validation = Array.prototype.filter.call(forms, function(form) {
                form.addEventListener('submit', function(event) {
                    if (form.checkValidity() === false) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        }, false);
    })();
</script>