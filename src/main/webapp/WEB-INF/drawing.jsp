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
            <c:forEach var="back" items="${requestScope.userProposition}">
                <!-- Back button -->
                <div class="container d-flex justify-content-center my-5">
                    <form class="btn btn-primary col-2 mx-2" name="back" action="FrontController" method="POST">
                        <input type="hidden" name="target" value="quoteView">
                        <input type="hidden" name="viewID" value="${back.user_proposition_id}">
                        <input type="hidden" name="orderID" value="${back.orders_id}">
                        <input class="btn text-white" type="submit" value="Tilbage">
                    </form>
                </div>
                <!-- End back button -->

            <!-- Table container -->
            <div class="container my-5">
                <!-- Table over customer quotes -->
                <table class="table table-striped" id="myTable">
                    <thead>
                    <tr>
                        <th scope="col">Ordrelinje id:</th>
                        <th scope="col">Ordre id:</th>
                        <th scope="col">Materiale:</th>
                        <th scope="col">Antal:</th>
                        <th scope="col">Mål/Enhed:</th>
                        <th scope="col">Beskrivelse:</th>
                        <th scope="col">Total pris:</th>
                        <th class="text-primary" scope="col">Rediger antal</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="edit" items="${requestScope.itemList}">
                        <tr>
                            <th scope="row">${edit.orderline_id}</th>
                            <th>${edit.orders_id}</th>
                            <td>${edit.material_type}</td>
                            <td>${edit.quantity}</td>
                            <td>${edit.unit}</td>
                            <td>${edit.description}</td>
                            <td>${edit.total_price}</td>
                            <td>
                                <!-- Item list edit button -->
                                <form class="form-group" name="editList" action="FrontController"
                                      method="post">
                                    <input type="hidden" name="target" value="editItemList">
                                    <input type="hidden" name="viewID" value="${back.user_proposition_id}">
                                    <input type="hidden" name="orderID" value="${edit.orders_id}">
                                    <input type="hidden" name="orderLineID" value="${edit.orderline_id}">
                                    <div class="form-group">
                                        <input class="form-control" type="number" name="quantity"
                                               value="${edit.quantity}">
                                    </div>

                                    <!-- Update button -->
                                    <input class="form-control btn btn-dark btn-block" type="submit" value="Rediger"
                                           onclick="return confirm('Er du sikker på at du vil redigere?')"/>
                                    <!-- End update button -->
                                </form>
                                <!-- End item list edit button -->
                            </td>
                        </tr>
                    </c:forEach>
                    </c:forEach>
                    </tbody>
                </table>
                <!-- End table over customer quotes -->
            </div>
            <!-- End table container -->

            <!-- SVG container -->
            <div class="container d-flex justify-content-center">
                <!-- SVG drawing from top -->
                <div class="container border-dark shadow">
                    <h5 class="text-primary">Set ovenfra</h5>
                    ${requestScope.svgDrawing}
                </div>
                <!-- End svg drawing from top -->
                <!-- SVG drawing from the front -->
                <div class="container border-dark shadow">
                    <h5 class="text-primary">Set forfra</h5>
                    ${requestScope.svgDrawingFront}
                </div>
                <!-- End svg drawing from the front -->
            </div>
            <div class="container d-flex justify-content-center">
                <!-- SVG drawing sideways -->
                <div class="container border-dark shadow">
                    <h5 class="text-primary">Set fra siden blueprint</h5>
                    ${requestScope.svgDrawingSidewaysBlueprint}
                </div>
                <!-- End sideways -->
                <!-- SVG drawing sideways with color -->
                <div class="container border-dark shadow">
                    <h5 class="text-primary">Set fra siden</h5>
                    ${requestScope.svgDrawingSideways}
                </div>
                <!-- End svg drawing sideways with color -->
            </div>
            <!-- End svg container -->

        </section>
        <!-- End section -->
    </div>
    <!-- End row -->
</div>
<!-- End container -->

<!-- Footer -->
<%@include file="../includes/footer.inc" %>
<!-- End footer -->






