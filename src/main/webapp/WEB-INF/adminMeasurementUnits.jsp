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
        width: 100px;
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
    <h2>Måleenheder</h2>
    <p>Her kan der rettes, slettes og tilføjes nye måleenheder til carport bredde og længe, og skur bredde og længde:</p>
    <table class="table table-sm">
        <thead>
        <tr>
            <th>Mål</th>
            <th class="text-center">Carport bredde</th>
            <th class="text-center">Carport længde</th>
            <th class="text-center">Skur bredde</th>
            <th class="text-center">Skur længde</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>180</td>
            <td class="text-center"></td>
            <td class="text-center"></td>
            <td class="text-center"></td>
            <td class="text-center"><span class="fa fa-check"></span></td>
            <td class="text-right">
                <button type="submit" class="add btn btn-sm btn-success" data-toggle="tooltip"><span class="fa fa-plus"></span> Tilføj</button>
                <button class="edit btn btn-sm btn-warning" data-toggle="tooltip"><span class="fa fa-pencil"></span> ret</button>
                <button type="submit" class="delete btn btn-sm btn-danger" data-toggle="tooltip"><span class="fa fa-trash"></span> slet</button>
            </td>
        </tr>
        <tr>
            <td>210</td>
            <td class="text-center"></td>
            <td class="text-center"></td>
            <td class="text-center"><span class="fa fa-check"></span></td>
            <td class="text-center"><span class="fa fa-check"></span></td>
            <td class="text-right">
                <button type="submit" class="add btn btn-sm btn-success" data-toggle="tooltip"><span class="fa fa-plus"></span> Tilføj</button>
                <button class="edit btn btn-sm btn-warning" data-toggle="tooltip"><span class="fa fa-pencil"></span> ret</button>
                <button type="submit" class="delete btn btn-sm btn-danger" data-toggle="tooltip"><span class="fa fa-trash"></span> slet</button>
            </td>
        </tr>
        <tr>
            <td>240</td>
            <td class="text-center"><span class="fa fa-check"></span></td>
            <td class="text-center"><span class="fa fa-check"></span></td>
            <td class="text-center"><span class="fa fa-check"></span></td>
            <td class="text-center"><span class="fa fa-check"></span></td>
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
                '<td><input type="text" class="form-control" name="unit" id="unit"></td>' +
                '<td class="text-center"><input type="checkbox" class="checkbox" name="c_width"></td>' +
                '<td class="text-center"><input type="checkbox" class="checkbox" name="c_length"></td>' +
                '<td class="text-center"><input type="checkbox" class="checkbox" name="ts_width"></td>' +
                '<td class="text-center"><input type="checkbox" class="checkbox" name="ts_length"></td>' +
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
            var inputCheck = $(this).parents("tr").find('input[type="checkbox"]');
            input.each(function(){
                var number = input.val().trim();
                if (number === "" || number !== "" && !$.isNumeric(number)) {
                    //if(!$(this).val()){
                    $(this).addClass("error");
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
                inputCheck.each(function(){
                    if($(this).is(':checked')) {
                        $(this).parent("td").html('<span class="fa fa-check"></span>');
                    } else {
                        $(this).parent("td").html('');
                    }
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
                $(this).html('<input type="text" class="form-control" pattern="[0-9]+" name="unit" id="unit" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(1)").each(function () {
                if($(this).is("td:empty")) {
                    $(this).html('<input type="checkbox" class="checkbox" name="c_width" id="c_width">');
                } else {
                    $(this).html('<input type="checkbox" class="checkbox" name="c_width" id="c_width" checked>');
                }
            });
            $(this).parents("tr").find("td:eq(2)").each(function () {
                if($(this).is("td:empty")) {
                    $(this).html('<input type="checkbox" class="checkbox" name="c_length" id="c_length">');
                } else {
                    $(this).html('<input type="checkbox" class="checkbox" name="c_length" id="c_length" checked>');
                }
            });
            $(this).parents("tr").find("td:eq(3)").each(function () {
                if($(this).is("td:empty")) {
                    $(this).html('<input type="checkbox" class="checkbox" name="ts_width" id="ts_width">');
                } else {
                    $(this).html('<input type="checkbox" class="checkbox" name="ts_width" id="ts_width" checked>');
                }
            });
            $(this).parents("tr").find("td:eq(4)").each(function () {
                if($(this).is("td:empty")) {
                    $(this).html('<input type="checkbox" class="checkbox" name="ts_length" id="ts_length">');
                } else {
                    $(this).html('<input type="checkbox" class="checkbox" name="ts_length" id="ts_length" checked>');
                }
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