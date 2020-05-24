<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../../includes/headerlogout.inc" %>
<!-- End header -->
<c:forEach var="info" items="${requestScope.userProposition}">
    <!-- Page Content -->
    <div class="container" id="page-view">
        <div class="row">

            <div class="col-2"></div>
            <div class="col-8 view-on-print"><img heigth="100" width="100" src="images/logo.png" alt="Fog logo"></div>
            <div class="col-2"></div>

            <div class="col-lg-12 text-center">
                <h1 class="my-5">Vi har godkendt din forespørgsel</h1>
            </div>
            <div class="col-2"></div>
            <div class="col-8">
                <p class="float-right mt-5">${info.order_date}</p>
                <address class="mt-5">
                        ${info.name}<br>
                        ${info.address}<br>
                        ${info.zipcodeCity}<br>
                    tlf.: ${info.phone}<br>
                    mail: ${info.email}
                </address>
                <p class="mt-5">Kære ${info.name}, <br><br>
                    Din forespørgsel er på en carport med en bredde på ${info.carport_width} cm og en længde
                    på ${info.carport_length} cm.

                    <c:if test="${info.shed_width > 0}">
                        <span>Du har også valgt et redskabsrum med en bredde på ${info.shed_width} cm og en længde på ${info.shed_length} cm.</span>
                    </c:if>

                    Carporten er med ${info.roof_type} tag

                    <c:choose>
                        <c:when test="${info.pitch == 0}">

                            <span>og beklædt med ${info.roof_material}.</span>
                        </c:when>
                        <c:otherwise>

                            <span>på ${info.pitch} grader og beklædt med ${info.roof_material}.</span>
                        </c:otherwise>
                    </c:choose>
                </p>
                <b>Du har skrevet følgende i dine bemærkninger:</b>
                <c:choose>
                    <c:when test="${empty info.comments}">
                        <p class="blockquote-footer"><em>Der er ingen bemærkninger</em></p>
                    </c:when>
                    <c:otherwise>
                        <p>${info.comments}</p>
                    </c:otherwise>
                </c:choose>
                <p>Vi har valgt at fremsende dig et tilbud på din forespørgsel på <b>${info.total_price}</b> kr. <br>
                    Skitsetegning er vedhæftet denne email og
                    standardbyggevejledning medfølger.</p>
                <p class="mt-5 mb-5">Med venlig hilsen<br> Fog</p>

            </div>
            <div class="col-2">
                <button type="button" onclick="window.print();return false;" class="btn btn-info btn-sm hide-on-print">
                    Print <span class="fa fa-print"></span></button>
            </div>
        </div>

        <!-- SVG container -->
        <div class="container col-8">

            <!-- SVG drawing sideways with color -->
            <div class="col border-dark shadow my-5 p-5">
                <h5 class="text-primary text-center mb-5">Færdig udgave af forespørgsel</h5>
                <div class="container bg-light d-flex justify-content-center">
                        ${requestScope.svgDrawingSideways}
                </div>
            </div>
            <!-- End svg drawing sideways with color -->
        </div>
        <!-- End SVG container -->

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
</c:forEach>
<!-- Footer -->
<%@include file="../../includes/footer.inc" %>
<!-- End footer -->

