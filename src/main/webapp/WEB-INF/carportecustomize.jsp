<!-- Header & imports -->
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/header.inc" %>
<!-- End header -->

<!-- Container -->
<div class="container min-vh-100">
    <!-- Row -->
    <!--<div class="row">-->

        <!-- Main -->
        <!--<main role="main">-->
            <!-- Section -->
            <!--<section class="col-12">-->
    <!-- Input form class="row" -->
    <form action="FrontController">
                <!-- Row -->
                <div class="row">



                        <!-- Column -->
                        <div class="col-12 col-sm-6 col-md-4">
                            <!-- Order option -->
                            <div class="form-group">
                                <label for="CarportWidth">Carport bredde</label>
                                <select class="form-control" id="CarportWidth">
                                    <option>Vælg bredde</option>
                                    <option>240 cm</option>
                                    <option>270 cm</option>
                                    <option>300 cm</option>
                                    <option>330 cm</option>
                                    <option>360 cm</option>
                                    <option>390 cm</option>
                                    <option>420 cm</option>
                                    <option>450 cm</option>
                                    <option>480 cm</option>
                                    <option>510 cm</option>
                                    <option>540 cm</option>
                                    <option>570 cm</option>
                                    <option>600 cm</option>
                                    <option>630 cm</option>
                                    <option>660 cm</option>
                                    <option>690 cm</option>
                                    <option>720 cm</option>
                                    <option>750 cm</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="CarportLength">Carport længde</label>
                                <select class="form-control" id="CarportLength">
                                    <option>Vælg længde</option>
                                    <option>240 cm</option>
                                    <option>270 cm</option>
                                    <option>300 cm</option>
                                    <option>330 cm</option>
                                    <option>360 cm</option>
                                    <option>390 cm</option>
                                    <option>420 cm</option>
                                    <option>450 cm</option>
                                    <option>480 cm</option>
                                    <option>510 cm</option>
                                    <option>540 cm</option>
                                    <option>570 cm</option>
                                    <option>600 cm</option>
                                    <option>630 cm</option>
                                    <option>660 cm</option>
                                    <option>690 cm</option>
                                    <option>720 cm</option>
                                    <option>750 cm</option>
                                    <option>780 cm</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="RoofOption">Tag type</label>
                                <select class="form-control" id="RoofOption">
                                    <option value="0">Carport med fladt tag</option>
                                    <option value="1">Carport med rejsning</option>
                                </select>
                            </div>

                            <!-- flat roof -->
                            <div id="flatRoof" style="display: block">
                                <div class="form-group">
                                    <label for="RoofFlat">Tag</label>
                                    <select class="form-control" id="RoofFlat">
                                        <option>Vælg tagtype/farve</option>
                                        <option>Plasttrapezplader</option>
                                        <option>Plastmo Ecolite blåtonet</option>
                                    </select>
                                </div>
                            </div>
                            <!-- End of flat roof -->

                            <!-- raised roof -->
                            <div id="raisedRoof" style="display: none">
                                <div class="form-group">
                                    <label for="RoofRaised">Tag</label>
                                    <select class="form-control" id="RoofRaised">
                                        <option>Vælg tagtype/farve</option>
                                        <option>Betontagsten - Rød</option>
                                        <option>Betontagsten - Teglrød</option>
                                        <option>Betontagsten - Brun</option>
                                        <option>Betontagsten – Sort</option>
                                        <option>Betontagsten – B&C protector: Sort</option>
                                        <option>Betontagsten – B&C protector: Skifer</option>
                                        <option>Betontagsten – B&C protector: Koralrød</option>
                                        <option>Betontagsten – B&C protector DANFLOCK: Sort</option>
                                        <option>Betontagsten – B&C protector DANFLOCK: Skifer</option>
                                        <option>Betontagsten – B&C protector DANFLOCK: Koralrød</option>
                                        <option>Eternittag B6 - Grå</option>
                                        <option>Eternittag B6 - Sort</option>
                                        <option>Eternittag B6 - Mokka (brun)</option>
                                        <option>Eternittag B6 – Rødbrun</option>
                                        <option>Eternittag B6 - Teglrød</option>
                                        <option>Eternittag B7 - Grå</option>
                                        <option>Eternittag B7 - Sort</option>
                                        <option>Eternittag B7 - Mokka (brun)</option>
                                        <option>Eternittag B7 - Rødbrun</option>
                                        <option>Eternittag B7 - Teglrød</option>
                                        <option>Eternittag B7 - Rødflammet</option>
                                        <option>Gammel Dansk – Rød</option>
                                        <option>Gammel Dansk – Mocca</option>
                                        <option>Gammel Dansk – Blådæmpet</option>
                                        <option>Gammel Dansk – Sortglaseret</option>
                                        <option>Gammel Dansk – Engoberet sort</option>
                                        <option>Gammel Dansk – Engoberet gul</option>
                                        <option>Nortegl – Engoberet sort</option>
                                        <option>Nortegl – Naturrød</option>
                                        <option>Nortegl – Engoberet gul</option>
                                        <option>Nortegl – Glaseret sort</option>
                                        <option>Nortegl – Engoberet flammet rød</option>
                                        <option>Hollander – Naturrød</option>
                                        <option>Hollander – Engoberet sortbrun</option>
                                        <option>Hollander – engoberet glaseret sort</option>
                                        <option>Hollander – engoberet antracit</option>
                                        <option>Hollander – engoberet brun</option>
                                        <option>Hollander – engoberet glaseret mat sort</option>
                                        <option>KDN VH – Naturrød</option>
                                        <option>KDN VH – Glaserede: sort</option>
                                        <option>KDN VH – Glaserede: sølvsort</option>
                                        <option>KDN VH – Glaserede: matsort</option>
                                        <option>KDN VH – Glaserede: lys brun</option>
                                        <option>KDN VH – Glaserede: mørk brun</option>
                                        <option>KDN VH – Glaserede: vinrød</option>
                                        <option>KDN VH – Glaserede: mørk rød</option>
                                        <option>Turmalin – Naturrød</option>
                                        <option>Turmalin – Engoberet rød</option>
                                        <option>Turmalin – Engoberet sort</option>
                                        <option>Turmalin – Engoberet lys grå</option>
                                        <option>Turmalin – Ædelengoberet kastanje</option>
                                        <option>Turmalin – mørk grå</option>
                                        <option>Turmalin – sort</option>
                                        <option>Dantegl NOVA – Engoberet naturrød</option>
                                        <option>Dantegl NOVA – Engoberet sort</option>
                                        <option>Dantegl NOVA – Ædelengoberet sort</option>
                                    </select>
                                </div>

                                <div class="form-group">
                                    <label for="RoofOptionDegrees">Taghældning</label>
                                    <select class="form-control" id="RoofOptionDegrees">
                                        <option>15 grader</option>
                                        <option>20 grader</option>
                                        <option selected>25 grader</option>
                                        <option>30 grader</option>
                                        <option>35 grader</option>
                                        <option>40 grader</option>
                                        <option>45 grader</option>
                                    </select>
                                </div>
                            </div>
                            <!-- End of raised roof -->

                            <div class="form-group">
                                <label for="ShedWidth">Redskabsrum bredde</label>
                                <select class="form-control" id="ShedWidth">
                                    <option>Ønsker ikke redskabsrum</option>
                                    <option>210 cm</option>
                                    <option>240 cm</option>
                                    <option>270 cm</option>
                                    <option>300 cm</option>
                                    <option>330 cm</option>
                                    <option>360 cm</option>
                                    <option>390 cm</option>
                                    <option>420 cm</option>
                                    <option>450 cm</option>
                                    <option>480 cm</option>
                                    <option>510 cm</option>
                                    <option>540 cm</option>
                                    <option>570 cm</option>
                                    <option>600 cm</option>
                                    <option>630 cm</option>
                                    <option>660 cm</option>
                                    <option>690 cm</option>
                                    <option>720 cm</option>
                                </select>
                            </div>

                            <div class="form-group">
                                <label for="ShedLength">Redskabsrum længde</label>
                                <select class="form-control" id="ShedLength">
                                    <option>Ønsker ikke redskabsrum</option>
                                    <option>150 cm</option>
                                    <option>180 cm</option>
                                    <option>210 cm</option>
                                    <option>240 cm</option>
                                    <option>270 cm</option>
                                    <option>300 cm</option>
                                    <option>330 cm</option>
                                    <option>360 cm</option>
                                    <option>390 cm</option>
                                    <option>420 cm</option>
                                    <option>450 cm</option>
                                    <option>480 cm</option>
                                    <option>510 cm</option>
                                    <option>540 cm</option>
                                    <option>570 cm</option>
                                    <option>600 cm</option>
                                    <option>630 cm</option>
                                    <option>660 cm</option>
                                    <option>690 cm</option>
                                </select>
                            </div>
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
            <!--</section>-->
            <!-- End section -->
        <!--</main>-->
        <!-- End main -->
    <!--</div>-->
    <!-- End row -->
</div>
<!-- End container -->

<!-- Footer -->
<%@include file="../includes/footer.inc" %>
<!-- End footer -->

<!-- script for raised/flat roof -->
<script>
    $(document).ready(function(){
        $('#RoofOption').on('change', function() {
            if ( this.value == '1')
            {
                $("#raisedRoof").show();
                $("#flatRoof").hide();
            }
            else
            {
                $("#raisedRoof").hide();
                $("#flatRoof").show();
            }
        });
    });
</script>