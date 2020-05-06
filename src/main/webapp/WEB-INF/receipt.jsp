<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/header.inc" %>
<!-- End header -->

<!-- Page Content -->
<div class="container" id="page-view">
    <div class="row">

        <div class="col-2"></div>
        <div class="col-8 view-on-print"><img heigth="100" width="100" src="images/logo.png" alt="Fog logo"></div>
        <div class="col-2"></div>

        <div class="col-lg-12 text-center">
            <h1 class="mt-5">Tak for din forespørgsel</h1>
        </div>
        <div class="col-2"></div>
        <div class="col-8">
            <p class="float-right mt-5">${requestScope.date}</p>
            <address class="mt-5">
                ${requestScope.name}<br>
                ${requestScope.address}<br>
                ${requestScope.zipcodeCity}<br>
                tlf.: ${requestScope.telephone}<br>
                mail: ${requestScope.email}
            </address>
            <p class="mt-5">Din forespørgsel er på en carport med en bredde på ${requestScope.carportWidth} cm og en længde på ${requestScope.carportLength} cm.

                <c:if test="${requestScope.shedWidth > 0}">
                    <span>Du har også valgt et redskabsrum med en bredde på ${requestScope.shedWidth} cm og en længde på ${requestScope.shedLength} cm.</span>
                </c:if>

                Carporten er med ${requestScope.roofType} tag

                <c:choose>
                    <c:when test="${requestScope.roofOption == 0}">

                        <span>og beklædt med ${requestScope.roofFlat}.</span>
                    </c:when>
                    <c:otherwise>

                        <span >på ${requestScope.roofOptionDegrees} grader og beklædt med ${requestScope.roofRaised}.</span>
                    </c:otherwise>
                </c:choose>
            </p>
            <b>Du har skrevet følgende i dine bemærkninger:</b>
            <c:choose>
                <c:when test="${empty requestScope.commentsTrimmed}">
                    <p class="blockquote-footer"><em>Der er ingen bemærkninger</em></p>
                </c:when>
                <c:otherwise>
                    <p>${requestScope.commentsTrimmed}</p>
                </c:otherwise>
            </c:choose>
            <p>Tilbud og skitsetegning fremsendes via e-mail hurtigst muligt.
                Standardbyggevejledning medfølger ved bestilling.</p>
            <p class="mt-5 mb-5">Med venlig hilsen<br> Fog</p>

        </div>
        <div class="col-2"><button type="button" onclick="window.print();return false;" class="btn btn-info btn-sm hide-on-print">Print <span class="fa fa-print"></span></button></div>
    </div>

    <div class="row">
        <div class="col-2"></div>
        <div class="col-8 view-on-print">
            <div class="print-footer container-fluid footer font-small py-3">
                <!-- Copyright -->
                <div class="text-center">
                    <!-- Adresse -->
                    <span><b>Adresse: </b>Johannes Fog A/S - Firskovvej 20 - 2800 Lyngby - CVR-nr. 16314439 &bull;
                        <!-- Telefon -->
                        <b>Telefon: </b>45 87 10 01 &bull;
                        <!-- Email -->
                        <b>Email:</b> info@johannesfog.dk &bull;
                    </span>
                    <span>
                        &copy; 2020 All rights reserved: Johannesfog.dk
                    </span>
                </div>
                <!-- Copyright -->
            </div>
        </div>
        <div class="col-2"></div>
    </div>

</div>
<!-- End container -->

<!-- Footer -->
<%@include file="../includes/footer.inc" %>
<!-- End footer -->

