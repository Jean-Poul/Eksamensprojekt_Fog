<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../../includes/headerlogout.inc" %>
<!-- End header -->

<!-- Container -->
<div class="container min-vh-100">
    <!-- Row -->
    <div class="row">
        <!-- Section -->
        <section class="col-12">
            <c:forEach var="back" items="${requestScope.userProposition}">
            <!-- Table container -->
            <div class="container mt-5">
                <!-- Back button -->

                <form class="form-group" name="back" action="FrontController" method="POST">
                    <input type="hidden" name="target" value="quoteView">
                    <input type="hidden" name="viewID" value="${back.user_proposition_id}">
                    <input type="hidden" name="orderID" value="${back.orders_id}">
                    <input class="btn btn-primary text-white" type="submit" value="Tilbage">
                </form>

                <!-- End back button -->
                <!-- Table over customer quotes -->
                <table class="table table-striped" id="myTableDrawings">
                    <thead>
                    <tr>
                        <th scope="col">Materiale:</th>
                        <th scope="col">Længde:</th>
                        <th scope="col">Mål/Enhed:</th>
                        <th scope="col">Beskrivelse:</th>
                        <th scope="col">Total pris:</th>
                        <th class="text-primary" scope="col">Rediger antal</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="edit" items="${requestScope.itemList}">
                        <tr>
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
                                    <input type="hidden" name="price" value="${edit.total_price}">
                                    <div class="form-group">
                                        <input class="form-control" type="number" name="quantity"
                                               value="${edit.quantity}">
                                    </div>

                                    <!-- Update button -->
                                    <input class="form-control btn btn-success btn-block" type="submit" value="Rediger"
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
            <div class="container">

                <!-- SVG drawing sideways with color -->
                <div class="col border-dark shadow my-5 p-5">
                    <h5 class="text-primary text-center mb-5">Færdig udgave af forespørgsel</h5>
                    <div class="container bg-light d-flex justify-content-center">
                        ${requestScope.svgDrawingSideways}
                    </div>
                </div>
                <!-- End svg drawing sideways with color -->

                <!-- SVG drawing sideways -->
                <div class="col border-dark shadow my-5 p-5">
                    <h5 class="text-primary text-center mb-5">Arbejdstegning fra siden</h5>
                    <div class="container bg-light d-flex justify-content-center">
                        ${requestScope.svgDrawingSidewaysBlueprint}
                    </div>
                </div>
                <!-- End sideways -->

                <div class="col border-dark shadow my-5 p-5">
                    <!-- SVG drawing from top -->
                    <h5 class="text-primary text-center mb-5">Arbejdstegning ovenfra</h5>
                    <div class="container bg-light d-flex justify-content-center">
                        ${requestScope.svgDrawing}
                    </div>
                </div>
                <!-- End svg drawing from top -->
                <!-- SVG drawing from the front -->
                <div class="col border-dark shadow my-5 p-5">
                    <h5 class="text-primary text-center mb-5">Arbejdstegning forfra</h5>
                    <div class="container bg-light d-flex justify-content-center">
                        ${requestScope.svgDrawingFront}
                    </div>
                </div>
                <!-- End svg drawing from the front -->
            </div>
            <!-- End SVG container -->
        </section>
        <!-- End section -->
    </div>
    <!-- End row -->
</div>
<!-- End container -->

<!-- Footer -->
<%@include file="../../includes/footer.inc" %>
<!-- End footer -->

<!-- item list layout/functions for table -->

<script>
    $(document).ready(function () {
        $('#myTableDrawings').DataTable();
    });
</script>



