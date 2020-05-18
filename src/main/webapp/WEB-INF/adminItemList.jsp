<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/headerlogout.inc" %>
<!-- End header -->
<style>
    .checkbox {
        width: 16px;
        height: 16px;
    }
    .add-new {
        font-weight: bold;
    }
    table.table th:first-child {
        #width: 100px;
    }
    table.table th:last-child {
        width: 135px;
    }
    table.table .form-control {
        height: 32px;
        line-height: 32px;
        box-shadow: none;
        border-radius: 2px;
    }
    table.table .form-control.error {
        border-color: #f50000;
    }
    table.table td .add {
        display: none;
    }
</style>

<h1 class="text-center mt-5">Admin</h1>
<div class="container mb-5 mt-5">
    <h2>Vareliste</h2>
    <p>Her kan der rettes, slettes og tilføjes nye varer til carport, skur og tag:</p>
    <table class="table table-sm">
        <thead>
        <tr>
            <th>Materiale type</th>
            <th>Materiale</th>
            <th>Beskrivelse</th>
            <th>Antal</th>
            <th>Enhed</th>
            <th>Pris pr. enhed</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>Tilbehør til spær:</td>
            <td>universal 190 mm højre</td>
            <td>Til montering af spær på rem</td>
            <td>1</td>
            <td></td>
            <td>46.00</td>
            <td class="text-right">
                <button type="submit" class="add btn btn-sm btn-success" data-toggle="tooltip"><span class="fa fa-plus"></span> Tilføj</button>
                <button class="edit btn btn-sm btn-warning" data-toggle="tooltip"><span class="fa fa-pencil"></span> ret</button>
                <button type="submit" class="delete btn btn-sm btn-danger" data-toggle="tooltip"><span class="fa fa-trash"></span> slet</button>
            </td>
        </tr>
        <tr>
            <td>Tilbehør til tværgående løsholter:</td>
            <td>vinkelbeslag 35mm</td>
            <td>til montering af løsholter (stk. pr. løsholter - afhænger af højde)</td>
            <td>2</td>
            <td></td>
            <td>7.00</td>
            <td class="text-right">
                <button type="submit" class="add btn btn-sm btn-success" data-toggle="tooltip"><span class="fa fa-plus"></span> Tilføj</button>
                <button class="edit btn btn-sm btn-warning" data-toggle="tooltip"><span class="fa fa-pencil"></span> ret</button>
                <button type="submit" class="delete btn btn-sm btn-danger" data-toggle="tooltip"><span class="fa fa-trash"></span> slet</button>
            </td>
        </tr>
        <tr>
            <td>Tilbehør til stolper:</td>
            <td>bræddebolt 10 x 120 mm.</td>
            <td>Til montering af rem på stolper (stk. pr stolpe, 4 i samling)</td>
            <td>2</td>
            <td>stk</td>
            <td>11.70</td>
            <td class="text-right">
                <button type="submit" class="add btn btn-sm btn-success" data-toggle="tooltip"><span class="fa fa-plus"></span> Tilføj</button>
                <button class="edit btn btn-sm btn-warning" data-toggle="tooltip"><span class="fa fa-pencil"></span> ret</button>
                <button type="submit" class="delete btn btn-sm btn-danger" data-toggle="tooltip"><span class="fa fa-trash"></span> slet</button>
            </td>
        </tr>
        </tbody>
    </table>
    <button class="btn btn-sm btn-success add-new"><span class="fa fa-plus"></span> Tilføj ny</button>
</div>

<!-- Footer -->
<%@include file="../includes/footer.inc" %>
<!-- End footer -->

<script type="text/javascript">
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();
        var actions = $("table td:last-child").html();
        // Append table with add row form on add new button click
        $(".add-new").click(function(){
            $(this).attr("disabled", "disabled");
            $(".edit").attr("disabled", true);
            var index = $("table tbody tr:last-child").index();
            var row = '<tr>' +
                '<td><input type="text" class="form-control" name="material_type" id="material_type"></td>' +
                '<td><input type="text" class="form-control" name="material" id="material"></td>' +
                '<td><input type="text" class="form-control" name="description" id="description"></td>' +
                '<td><input type="text" class="form-control" name="amounts" id="amounts"></td>' +
                '<td><input type="text" class="form-control" name="unit" id="unit"></td>' +
                '<td><input type="text" class="form-control" name="price_per_unit" id="price_per_unit"></td>' +
                '<td class="text-right">' + actions + '</td>' +
                '</tr>';
            $("table").append(row);
            $("table tbody tr").eq(index + 1).find(".add, .edit").toggle();
            $('[data-toggle="tooltip"]').tooltip();
        });
        // Add row on add button click
        $(document).on("click", ".add", function(){
            var empty = false;
            var input = $(this).parents("tr").find('input[type="text"]');
            var unit = $(this).parents("tr").find("#unit");
            var amounts = $(this).parents("tr").find("#amounts");
            var pricePU = $(this).parents("tr").find("#price_per_unit");

            input.not(unit).each(function(){
                var intRegex = /^\d+$/;
                var floatRegex = /^((\d+(\.\d *)?)|((\d*\.)?\d+))$/;
                var amount = amounts.val();
                var price_per_unit = pricePU.val();

                if(!$(this).val()){
                    $(this).addClass("error");
                    empty = true;
                } else {
                    $(this).removeClass("error");
                }

                if(!$.isNumeric(amount)) {
                    $("#amounts").addClass("error");
                    empty = true;
                } else {
                    $(this).removeClass("error");
                }

                if(!$.isNumeric(price_per_unit)) {
                    $("#price_per_unit").addClass("error");
                    empty = true;
                } else {
                    $(this).removeClass("error");
                }
            });

            $(this).parents("tr").find(".error").first().focus();
            if(!empty){
                input.each(function(){
                    $(this).parent("td").html($(this).val());
                });

                $(this).parents("tr").find(".add, .edit").toggle();
                $(".add-new, .edit").removeAttr("disabled");
            }

        });
        // Edit row on edit button click
        $(document).on("click", ".edit", function(){
            /*
            $(this).parents("tr").find("td:not(:last-child)").each(function(){
                $(this).html('<input type="text" class="form-control" value="' + $(this).text() + '">');
            });
            */
            $(this).parents("tr").find("td:eq(0)").each(function () {
                $(this).html('<input type="text" class="form-control" name="material_type" id="material_type" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(1)").each(function () {
                $(this).html('<input type="text" class="form-control" name="material" id="material" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(2)").each(function () {
                $(this).html('<input type="text" class="form-control" name="description" id="description" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(3)").each(function () {
                $(this).html('<input type="text" class="form-control" name="amounts" id="amounts" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(4)").each(function () {
                $(this).html('<input type="text" class="form-control" name="unit" id="unit" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(5)").each(function () {
                $(this).html('<input type="text" class="form-control" name="price_per_unit" id="price_per_unit" value="' + $(this).text() + '">');
            });

            $(this).parents("tr").find(".add, .edit").toggle();
            $(".add-new, .edit").attr("disabled", "disabled");
        });
        // Delete row on delete button click
        $(document).on("click", ".delete", function(){
            $(this).parents("tr").remove();
            $(".add-new, .edit").removeAttr("disabled");
        });
    });
</script>