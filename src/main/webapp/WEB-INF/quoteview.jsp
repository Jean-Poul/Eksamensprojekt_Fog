<!-- Header & imports -->
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/header.inc" %>
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

                    <!-- Customer info -->
                    <table class="table table-striped table-dark bg-primary table-hover">
                        <caption>Kunde information</caption>
                        <thead>
                        <tr>
                            <th class="text-center bg-dark" colspan="4">Kunde information
                            </th>
                        </tr>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Fornavn</th>
                            <th scope="col">Efternavn</th>
                            <th scope="col">Brugernavn</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>1</td>
                            <td>fornavn</td>
                            <td>efternavn</td>
                            <td>@bruger</td>
                        </tr>
                        </tbody>
                        <a class="card-link btn btn-dark" href="#">Opdater</a>
                    </table>
                    <!-- End customer info -->
                    <!-- Shipment info -->
                    <table class="table table-striped table-dark bg-primary table-hover">
                        <caption>Fragt adresse</caption>
                        <thead>
                        <tr>
                            <th class="text-center bg-dark" colspan="4">Fragt adresse
                            </th>
                        </tr>
                        <tr>
                            <th scope="col">Adresse</th>
                            <th scope="col">Postnummer</th>
                            <th scope="col">Email</th>
                            <th scope="col">Telefon</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>adresse</td>
                            <td>postnummer</td>
                            <td>email@email.com</td>
                            <td>123456</td>
                        </tr>
                        </tbody>
                        <a class="card-link btn btn-dark" href="#">Opdater</a>
                    </table>
                    <!-- End shipment info -->
                    <!-- Customer quote info -->
                    <table class="table table-striped table-dark bg-primary table-hover">
                        <caption>Kunde har efterspurgt</caption>
                        <thead>
                        <tr>
                            <th class="text-center bg-dark" colspan="4">Kunde har efterspurgt
                            </th>
                        </tr>
                        <tr>
                            <th scope="col">Carport bredde</th>
                            <th scope="col">Carport længde</th>
                            <th scope="col">Tag type</th>
                            <th scope="col">Tag vinkel</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>carport bredde</td>
                            <td>carport længde</td>
                            <td>tag type</td>
                            <td>tag vinkel</td>
                        </tr>
                        </tbody>
                        <a class="card-link btn btn-dark" href="#">Opdater</a>
                    </table>
                    <!-- End customer quote info -->
                    <!-- Customer add on info -->
                    <table class="table table-striped table-dark bg-primary table-hover">
                        <caption>Kunde tilføjelser</caption>
                        <thead>
                        <tr>
                            <th class="text-center bg-dark" colspan="4">Kunde tilføjelser
                            </th>
                        </tr>
                        <tr>
                            <th scope="col">Redskabsrum bredde</th>
                            <th scope="col">Redskabsrum længde</th>
                            <th scope="col">-</th>
                            <th scope="col">-</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>redskabsrum bredde</td>
                            <td>redskabsrum længde</td>
                            <td>-</td>
                            <td>-</td>
                        </tr>
                        </tbody>
                        <a class="card-link btn btn-dark" href="#">Opdater</a>
                    </table>
                    <!-- End customer add on info -->
                    <!-- Customer remark -->
                    <table class="table table-striped table-dark bg-primary table-hover">
                        <caption>Kunde tilføjelser</caption>
                        <thead>
                        <tr>
                            <th class="text-center bg-dark">Bemærkning
                            </th>
                        </tr>
                        <tr>
                            <th class="text-center bg-dark"><small>Overskrift</small>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                e Blimey fore pinnace walk the plank quarter line. Execution dock crimp take a caulk
                                tender
                                bilged on her anchor yawl spyglass case shot lass strike colors. Trysail grog blossom to
                                go
                                on account sutler maroon topmast landlubber or just lubber bilged on her anchor belay
                                broadside.
                                e Blimey fore pinnace walk the plank quarter line. Execution dock crimp take a caulk
                                tender
                                bilged on her anchor yawl spyglass case shot lass strike colors. Trysail grog blossom to
                                go
                                on account sutler maroon topmast landlubber or just lubber bilged on her anchor belay
                                broadside.
                            </td>
                        </tr>
                        </tbody>
                        <a class="card-link btn btn-dark" href="#">Opdater</a>
                    </table>
                    <!-- End customer remark -->
                    <!-- Total price -->
                    <table class="table table-striped table-dark bg-primary table-hover">
                        <caption>Total pris</caption>
                        <thead>
                        <tr>
                            <th class="text-center bg-dark" colspan="12">Pris
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td class="text-left" colspan="2">Total pris inkl. momse</td>
                            <td class="text-right" colspan="2">15.999,-</td>
                        </tr>
                        <tr>
                            <td colspan="12"></td>
                        </tr>
                        </tbody>
                        <a class="card-link btn btn-dark" href="#">Opdater</a>
                    </table>
                    <!-- End total price -->
                    <!-- Button choices -->
                    <div class="container d-flex justify-content-center">
                    <button type="button" class="btn btn-success col-2 mx-2">Godkend</button>
                    <button type="button" class="btn btn-primary col-2 mx-2">Se tegning</button>
                    <button type="button" class="btn btn-danger col-2 mx-2">Afvis</button>
                    </div>
                    <!-- End button choices -->

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
<%@include file="../includes/footer.inc" %>
<!-- End footer -->

