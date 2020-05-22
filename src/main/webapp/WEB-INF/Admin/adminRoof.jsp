<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../../includes/headerlogout.inc" %>
<!-- End header -->
<style>
#roof_type, #roof_category {
padding: 0px !important;
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
    <h2>Tagbeklædning</h2>
    <p>Her kan der rettes, slettes og tilføjes nye tag beklædninger til carport:</p>
    <table class="table table-sm">
        <thead>
        <tr>
            <th>Tag type</th>
            <th>Tag kategori</th>
            <th>Tag materiale</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>fladt</td>
            <td>let</td>
            <td>Plastmo Ecolite blåtonet</td>
            <td class="text-right">
                <button type="submit" class="add btn btn-sm btn-success" data-toggle="tooltip"><span class="fa fa-plus"></span> Tilføj</button>
                <button class="edit btn btn-sm btn-warning" data-toggle="tooltip"><span class="fa fa-pencil"></span> ret</button>
                <button type="submit" class="delete btn btn-sm btn-danger" data-toggle="tooltip"><span class="fa fa-trash"></span> slet</button>
            </td>
        </tr>
        <tr>
            <td>rejst</td>
            <td>let</td>
            <td>Eternittag B6 - Mokka (brun)</td>
            <td class="text-right">
                <button type="submit" class="add btn btn-sm btn-success" data-toggle="tooltip"><span class="fa fa-plus"></span> Tilføj</button>
                <button class="edit btn btn-sm btn-warning" data-toggle="tooltip"><span class="fa fa-pencil"></span> ret</button>
                <button type="submit" class="delete btn btn-sm btn-danger" data-toggle="tooltip"><span class="fa fa-trash"></span> slet</button>
            </td>
        </tr>
        <tr>
            <td>rejst</td>
            <td>tung</td>
            <td>Hollander – engoberet glaseret mat sort</td>
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
<%@include file="../../includes/footer.inc" %>
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
                '<td><select class="form-control" name="roof_type" id="roof_type">' +
                '<option value="fladt">fladt</option>' +
                '<option value="rejst">rejst</option>' +
                '</select></td>' +
                '<td><select class="form-control" name="roof_category" id="roof_category">' +
                '<option value="let">let</option>' +
                '<option value="tung">tung</option>' +
                '</select></td>' +
                '<td><input type="text" class="form-control" name="roof_material" id="roof_material"></td>' +
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
            var selectBox1 = $(this).parents("tr").find("#roof_type");
            var selectBox2 = $(this).parents("tr").find("#roof_category");

            input.each(function(){

                if(!$(this).val()){
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

                selectBox1.each(function () {
                    if($(this).val() === 'fladt') {
                        $(this).parent("td").html('fladt');
                    } else {
                        $(this).parent("td").html('rejst');
                    }
                });

                selectBox2.each(function () {
                    if($(this).val() === 'let') {
                        $(this).parent("td").html('let');
                    } else {
                        $(this).parent("td").html('tung');
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
                if($(this).text() === 'fladt') {
                    $(this).html('<select class="form-control" name="roof_type" id="roof_type">' +
                        '<option value="fladt" selected>fladt</option>' +
                        '<option value="rejst">rejst</option>' +
                        '</select></td>');
                } else {
                    $(this).html('<select class="form-control" name="roof_type" id="roof_type">' +
                        '<option value="fladt">fladt</option>' +
                        '<option value="rejst" selected>rejst</option>' +
                        '</select></td>');
                }

            });
            $(this).parents("tr").find("td:eq(1)").each(function () {
                if($(this).text() === 'let') {
                    $(this).html('<select class="form-control" name="roof_category" id="roof_category">' +
                        '<option value="let" selected>let</option>' +
                        '<option value="tung">tung</option>' +
                        '</select></td>');
                } else {
                    $(this).html('<select class="form-control" name="roof_category" id="roof_category">' +
                        '<option value="let">let</option>' +
                        '<option value="tung" selected>tung</option>' +
                        '</select></td>');
                }

            });
            $(this).parents("tr").find("td:eq(2)").each(function () {
                $(this).html('<input type="text" class="form-control" name="roof_material" id="roof_material" value="' + $(this).text() + '">');
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