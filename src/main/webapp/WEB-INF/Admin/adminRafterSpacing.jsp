<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../../includes/headerlogout.inc" %>
<!-- End header -->
<style>
    #category {
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
    <h2>Spærafstand</h2>
    <p>Her kan der rettes, slettes og tilføjes nye måleenheder til spærafstanden:</p>

    <!-- Delete row form -->
    <form name="delete" id="delete" action="FrontController" method="post">
        <input type="hidden" name="target" value="adminRafterSpacingDB">
        <input type="hidden" name="rafterSpacingId" id="rafterSpacingId" value="">
        <input type="hidden" name="queryChoice" value="3">
    </form>

    <table class="table table-sm">
        <thead>
        <tr>
            <th>Kategori</th>
            <th>Spær dimension</th>
            <th>Spærafstand</th>
            <th>Spærlængde</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="element" items="${requestScope.rafterSpacing}">
            <tr id="tr_${element.rafter_spacing_id}">
                <td>${element.category}</td>
                <td>${element.beam_dimension}</td>
                <td>${element.beam_spacing}</td>
                <td>${element.rafter_length}</td>
                <td class="text-right">
                    <input type="hidden" name="target" value="adminRafterSpacingDB">
                    <input type="hidden" name="rafterSpacingId" value="${element.rafter_spacing_id}">
                    <input type="hidden" id="queryChoice" name="queryChoice" value="2">

                    <button type="submit" class="add btn btn-sm btn-success" data-toggle="tooltip"><span class="fa fa-plus"></span> Tilføj</button>
                    <button class="edit btn btn-sm btn-warning" data-toggle="tooltip"><span class="fa fa-pencil"></span> ret</button>
                    <button type="submit" form="delete" id="${element.rafter_spacing_id}" class="delete btn btn-sm btn-danger" data-toggle="tooltip" onclick="return confirm('Er du sikker på at du vil slette?')"><span class="fa fa-trash"></span> slet</button>
                </td>
            </tr>
        </c:forEach>

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
        //var actions = $("table td:last-child").html();
        // Append table with add row form on add new button click
        $(".add-new").click(function(){
            $(this).attr("disabled", "disabled");
            $(".edit").attr("disabled", true);
            var index = $("table tbody tr:last-child").index();
            var row = '<tr id="tr_0">' +
                '<td><select class="form-control" name="category" id="category">' +
                '<option value="let">let</option>' +
                '<option value="tung">tung</option>' +
                '</select></td>' +
                '<td><input type="text" class="form-control" name="beam_dimension" id="beam_dimension"></td>' +
                '<td><input type="text" class="form-control" name="beam_spacing" id="beam_spacing"></td>' +
                '<td><input type="text" class="form-control" name="rafter_length" id="rafter_length"></td>' +
                '<td class="text-right"><input type="hidden" name="target" value="adminRafterSpacingDB">' +
                '<input type="hidden" id="queryChoice" name="queryChoice" value="1">' +
                '<button class="add btn btn-sm btn-success mr-1" data-toggle="tooltip"><span class="fa fa-plus"></span> Tilføj</button>' +
                '<button type="submit" form="delete" id="0" class="delete btn btn-sm btn-danger" data-toggle="tooltip" onclick="return confirm(\'Er du sikker på at du vil slette?\')"><span class="fa fa-trash"></span> slet</button></td>' +
                '</tr>';
            $("table").append(row);
            $("table tbody tr").eq(index + 1).find(".add, .edit").toggle();
            $('[data-toggle="tooltip"]').tooltip();
        });
        // Add row on add button click
        $(document).on("click", ".add", function(){
            var empty = false;
            var trId = $(this).closest('tr').attr('id'); // get id from tr
            var input = $(this).parents("tr").find('input[type="text"]');
            var selectBox = $(this).parents("tr").find("select");
            var beamSpacing = $(this).parents("tr").find("#beam_spacing");
            var rafterLength = $(this).parents("tr").find("#rafter_length");

            input.each(function(){
                var beamSpacings = beamSpacing.val();
                var rafterLengths = rafterLength.val();

                if(!$(this).val()){
                    $(this).addClass("error");
                    empty = true;
                } else {
                    $(this).removeClass("error");
                }

                if(!$.isNumeric(beamSpacings)) {
                    $("#beam_spacing").addClass("error");
                    empty = true;
                } else {
                    $(this).removeClass("error");
                }

                if(!$.isNumeric(rafterLengths)) {
                    $("#rafter_length").addClass("error");
                    empty = true;
                } else {
                    $(this).removeClass("error");
                }
            });

            $(this).parents("tr").find(".error").first().focus();
            if(!empty){
                // if validated submit row
                submitRowAsForm(trId);

                input.each(function(){
                    $(this).parent("td").html($(this).val());
                });

                selectBox.each(function () {
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
                if($(this).text() === 'let') {
                    $(this).html('<select class="form-control" name="category" id="category">' +
                        '<option value="let" selected>let</option>' +
                        '<option value="tung">tung</option>' +
                        '</select></td>');
                } else {
                    $(this).html('<select class="form-control" name="category" id="category">' +
                        '<option value="let">let</option>' +
                        '<option value="tung" selected>tung</option>' +
                        '</select></td>');
                }

            });
            $(this).parents("tr").find("td:eq(1)").each(function () {
                $(this).html('<input type="text" class="form-control" name="beam_dimension" id="beam_dimension" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(2)").each(function () {
                $(this).html('<input type="text" class="form-control" name="beam_spacing" id="beam_spacing" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(3)").each(function () {
                $(this).html('<input type="text" class="form-control" name="rafter_length" id="rafter_length" value="' + $(this).text() + '">');
            });

            $(this).parents("tr").find(".add, .edit").toggle();
            $(".add-new, .edit").attr("disabled", "disabled");
        });
        // Delete row on delete button click
        $(document).on("click", ".delete", function(){
            var uid = $(this).attr('id');
            $('#rafterSpacingId').val(uid);
            //$(this).parents("tr").remove();
            //$(".add-new, .edit").removeAttr("disabled");
        });
    });
</script>

<script>
    function submitRowAsForm(idRow) {
        form = document.createElement("form"); // CREATE A NEW FORM TO DUMP ELEMENTS INTO FOR SUBMISSION
        form.method = "post"; // CHOOSE FORM SUBMISSION METHOD, "GET" OR "POST"
        form.action = "FrontController"; // TELL THE FORM WHAT PAGE TO SUBMIT TO
        $("#"+idRow+" td").children().each(function() { // GRAB ALL CHILD ELEMENTS OF <TD>'S IN THE ROW IDENTIFIED BY idRow, CLONE THEM, AND DUMP THEM IN OUR FORM
            if(this.type.substring(0,6) == "select") { // JQUERY DOESN'T CLONE <SELECT> ELEMENTS PROPERLY, SO HANDLE THAT
                input = document.createElement("input"); // CREATE AN ELEMENT TO COPY VALUES TO
                input.type = "hidden";
                input.name = this.name; // GIVE ELEMENT SAME NAME AS THE <SELECT>
                input.value = this.value; // ASSIGN THE VALUE FROM THE <SELECT>
                form.appendChild(input);
            } else { // IF IT'S NOT A SELECT ELEMENT, JUST CLONE IT.
                $(this).clone().appendTo(form);
            }

        });
        form.style.display = "none"; // needed for firefox
        document.body.appendChild(form); // needed for firefox
        form.submit(); // NOW SUBMIT THE FORM THAT WE'VE JUST CREATED AND POPULATED
    }
</script>