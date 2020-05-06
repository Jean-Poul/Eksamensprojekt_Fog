<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/headerlogout.inc" %>
<!-- End header -->

<!-- Container -->
<div class="container min-vh-100">
    <!-- Row -->
    <div class="row">
        <!-- Section -->
        <section class="col-12">

            <!-- Counters -->
            <div class="container d-flex justify-content-center my-3">
                <div class="row">
                    <div class="col border-dark rounded shadow px-3 py-3">
                        <p>Antal forespørgsler: <b>${fn:length(requestScope.userpropositions)}</b></p>
                    </div>
                    <div class="col border-dark rounded shadow mx-5 px-3 py-3">
                        <p>Pris af solgte varer: <b>request på samlet værdi på solgte varer fra DB</b></p>
                    </div>
                    <div class="col border-dark rounded shadow px-3 py-3">
                        <p>Brugernavn: <b>${sessionScope.email}</b> <b>${sessionScope.user}</b></p>
                    </div>
                </div>
            </div>
            <!-- End counters -->

            <!-- Table over customer quotes -->
            <div class="container">
                        <div class="input-group w-25">
                        <input type="text" class="form-control" placeholder="Søg">
                        <div class="input-group-append">
                            <button class="btn btn-secondary" type="button">
                                <i class="fa fa-search">icon</i>
                            </button>
                        </div>
                        </div>



                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col">Kunde id:</th>
                        <th scope="col">Navn:</th>
                        <th scope="col">Adresse:</th>
                        <th scope="col">Postnummer:</th>
                        <th scope="col">Telefon:</th>
                        <th scope="col">Email:</th>
                        <th scope="col">Bemærkning:</th>
                        <th scope="col">&nbsp;</th>
                        <th scope="col">&nbsp;</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="view" items="${requestScope.userpropositions}">
                        <tr>
                            <th scope="row">${view.user_proposition_id}</th>
                            <td>${view.name}</td>
                            <td>${view.address}</td>
                            <td>${view.zipcodeCity}</td>
                            <td>${view.phone}</td>
                            <td>${view.email}</td>
                            <td>${view.comments}</td>
                            <td>
                                <!-- User overview button -->
                                <form class="btn btn-dark" name="viewQuote" action="FrontController"
                                      method="post">
                                    <input type="hidden" name="target" value="quoteView">
                                    <input type="hidden" name="viewID" value="${view.user_proposition_id}">
                                    <input type="submit" class="btn text-white px-0 py-0" value="Åben">
                                </form>
                            </td>
                            <td>
                                <form class="btn btn-dark" name="deleteQuote" action="FrontController"
                                      method="post">
                                    <input type="hidden" name="target" value="adminRejectQuote">
                                    <input type="hidden" name="quoteID" value="${view.user_proposition_id}">
                                    <input type="submit" class="btn text-white px-0 py-0" value="Fjern">
                                </form>
                                <!-- End user overview button -->
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

            </div>
            <!-- End table over customer quotes -->

            <!-- Table navigation -->
            <div class="container">
                <nav class="mt-5 tabel-nav" aria-label="Tabel navigation">
                    <ul class="pagination justify-content-center">
                        <li class="page-item"><a class="page-link" href="#"><</a></li>
                        <li class="page-item active"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item disabled"><a class="page-link" href="#">></a></li>
                    </ul>
                </nav>
            </div>
            <!-- End table navigation -->

            <!-- Create employee -->
            <div class="container d-flex justify-content-center">
                <div class="col-4"></div>
                <div class="col-4">
                <h3>Opret medarbejder</h3>
                <form name="register" action="FrontController" method="POST">
                    <input type="hidden" name="target" value="register">
                    <div class="form-group">
                        <label for="InputEmail">Email adresse</label>
                        <input type="email" class="form-control" id="InputEmail" aria-describedby="emailHelp"
                               name="email" value="somewhere@nowhere.com">
                        <small id="emailHelp" class="form-text text-muted">Vi deler din email med alle!</small>
                    </div>
                    <div class="form-group">
                        <label for="InputPassword1">Password</label>
                        <input type="password" class="form-control" id="InputPassword1" name="password1" value="bebop">
                    </div>
                    <div class="form-group">
                        <label for="InputPassword2">Gentag password</label>
                        <input type="password" class="form-control" id="InputPassword2" name="password2"
                               value="rocksteady">
                    </div>
                    <button type="submit" class="btn btn-primary">Opret</button>
                </form>
                </div>
                <div class="col-4"></div>
            </div>
            <!-- End employee -->

        </section>
        <!-- End section -->
    </div>
    <!-- End row -->
</div>
<!-- End container -->

<!-- Footer -->
<%@include file="../includes/footer.inc" %>
<!-- End footer -->

