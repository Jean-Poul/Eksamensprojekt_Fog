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

                <div class="col rounded-lg shadow px-3 py-3 text-center">
                    <p class="text-primary">Antal forespørgsler</p> <br>
                    <h3><span class="badge badge-primary">${fn:length(requestScope.userpropositions)}</span></h3>
                </div>
                <div class="col rounded-lg shadow mx-5 px-3 py-3 text-center">
                    <p class="text-primary">Pris af solgte vare</p> <br> <b>request på samlet værdi på solgte varer fra
                    DB</b>
                </div>
                <div class="col rounded-lg shadow px-3 py-3 text-center">
                    <p class="text-primary">Brugernavn</p> <br>
                    <h3><span class="badge badge-primary">${sessionScope.email}</span></h3>
                </div>

            </div>
            <!-- End counters -->

            <div class="container my-5">
                <!-- Table over customer quotes -->
                <table class="table table-striped" id="myTable">
                    <thead>
                    <tr>
                        <th scope="col">Kunde id:</th>
                        <th scope="col">Navn:</th>
                        <th scope="col">Adresse:</th>
                        <th scope="col">Postnummer:</th>
                        <th scope="col">Telefon:</th>
                        <th scope="col">Email:</th>
                        <th scope="col">Bemærkning:</th>
                        <th scope="col"></th>
                        <th scope="col"></th>
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
                                <!-- End user overview button -->
                            </td>
                            <td>
                                <!-- Delete user proposition -->
                                <form class="btn btn-dark" name="deleteQuote" action="FrontController"
                                      method="post">
                                    <input type="hidden" name="target" value="adminRejectQuote">
                                    <input type="hidden" name="quoteID" value="${view.user_proposition_id}">
                                    <input type="submit" class="btn text-white px-0 py-0" value="Fjern"
                                           onclick="return confirm('Er du sikker på at du vil slette?')"/>
                                </form>
                                <!-- End delete user proposition button -->
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <!-- End table over customer quotes -->
            </div>

            <!-- Create employee -->
            <div class="container d-flex justify-content-center mb-3">

                <div class="col-4 p-3 rounded-lg shadow">
                    <h3 class="text-primary text-center">Opret medarbejder</h3>
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
                            <input type="password" class="form-control" id="InputPassword1" name="password1"
                                   value="bebop">
                        </div>
                        <div class="form-group">
                            <label for="InputPassword2">Gentag password</label>
                            <input type="password" class="form-control" id="InputPassword2" name="password2"
                                   value="rocksteady">
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Opret</button>
                    </form>

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

<script>
    $(document).ready(function () {
        $('#myTable').DataTable();
    });
</script>