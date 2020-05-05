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

            <a class="card-link btn btn-dark"
               href="FrontController?target=redirect&destination=quoteview">Forespørgsels oversigt
            </a>

            <div class="container container d-flex justify-content-center my-3">
                <div class="row">
                    <div class="col bg-dark text-white rounded shadow px-3 py-3">
                        <p>Antal forespørgsler: <b>${requestScope.qoutesum}</b></p>
                    </div>
                    <div class="col bg-dark text-white rounded shadow mx-5 px-3 py-3">
                        <p>Pris af solgte varer: <b>request på samlet værdi på solgte varer fra DB</b></p>
                    </div>
                    <div class="col bg-dark text-white rounded shadow px-3 py-3">
                        <p>Antal brugere: <b>${requestScope.usersum}</b></p>
                    </div>
                </div>
            </div>

            <div class="container">
                <c:forEach var="view" items="${requestScope.userqoutelist}">
                    <div class="input-group">
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
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">${view.customerId}</th>
                            <td>${view.name}</td>
                            <td>${view.address}</td>
                            <td>${view.zipcode}</td>
                            <td>${view.phone}</td>
                            <td>${view.email}</td>
                            <td>${view.comment}</td>
                        </tr>
                        </tbody>
                    </table>

                </c:forEach>
            </div>

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

        </section>
        <!-- End section -->

    </div>
    <!-- End row -->
</div>
<!-- End container -->

<!-- Footer -->
<%@include file="../includes/footer.inc" %>
<!-- End footer -->

