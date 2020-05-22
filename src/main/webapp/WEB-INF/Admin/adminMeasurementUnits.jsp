<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../../includes/headerlogout.inc" %>
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
    form {
        display:inline;
        margin:0;
        padding:0;
    }
</style>

<h1 class="text-center mt-5">Admin</h1>
<div class="container mb-5 mt-5">
    <h2>Måleenheder</h2>
    <p>Her kan der rettes, slettes og tilføjes nye måleenheder til carport bredde og længe, og skur bredde og længde:</p>

    <!-- Delete row form -->
    <form name="delete" id="delete" action="FrontController" method="post">
        <input type="hidden" name="target" value="adminMeasurementUnitsDB">
        <input type="hidden" name="measurementUnitId" id="measurementUnitId" value="">
        <input type="hidden" name="queryChoice" value="3">
    </form>

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
        <c:forEach var="element" items="${requestScope.measurementUnits}">
            <tr id="tr_${element.measurementUnitsId}">
                <td>${element.units}</td>
                <td class="text-center chbx">${element.c_width}</td>
                <td class="text-center chbx">${element.c_length}</td>
                <td class="text-center chbx">${element.ts_width}</td>
                <td class="text-center chbx">${element.ts_length}</td>
                <td class="text-right">
                    <input type="hidden" name="target" value="adminMeasurementUnitsDB">
                    <input type="hidden" name="measurementUnitId" value="${element.measurementUnitsId}">
                    <input type="hidden" id="queryChoice" name="queryChoice" value="2">

                    <button class="add btn btn-sm btn-success" data-toggle="tooltip"><span class="fa fa-plus"></span> Tilføj</button>
                    <button type="button" class="edit btn btn-sm btn-warning" data-toggle="tooltip"><span class="fa fa-pencil"></span> ret</button>
                    <button type="submit" form="delete" id="${element.measurementUnitsId}" class="delete btn btn-sm btn-danger" data-toggle="tooltip" onclick="return confirm('Er du sikker på at du vil slette?')"><span class="fa fa-trash"></span> slet</button>

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
    $(document).ready(function() {
        // Change 1 and 0 on page load
        $('.chbx').each(function () {
            $("td:contains('1').chbx").html('<span class="fa fa-check"></span>');
            $("td:contains('0').chbx").html('');
        });
    });
</script>
<script>
    function changeChoice(val)
    {
        document.getElementById("queryChoice").value=val;
    }
</script>
<script type="text/javascript">
    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip();

        // Append table with add row form on add new button click
        $(".add-new").click(function(){
            $(this).attr("disabled", "disabled");
            $(".edit").attr("disabled", true);
            var index = $("table tbody tr:last-child").index();
            var row = '<tr id="tr_0">' +
                '<td><input type="text" class="form-control" name="units" id="units"></td>' +
                '<td class="text-center"><input type="checkbox" class="checkbox" value ="1" name="c_width"></td>' +
                '<td class="text-center"><input type="checkbox" class="checkbox" value ="1" name="c_length"></td>' +
                '<td class="text-center"><input type="checkbox" class="checkbox" value ="1" name="ts_width"></td>' +
                '<td class="text-center"><input type="checkbox" class="checkbox" value ="1" name="ts_length"></td>' +
                '<td class="text-right"><input type="hidden" name="target" value="adminMeasurementUnitsDB">' +
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
            var trId = $(this).closest('tr').attr('id'); // get id from tr onclick="submitRowAsForm('tr_0')"
            var input = $(this).parents("tr").find('input[type="text"]');
            var inputCheck = $(this).parents("tr").find('input[type="checkbox"]');
            input.each(function(){
                var number = input.val().trim();
                if (number === "" || number !== "" && !$.isNumeric(number)) {
                    $(this).addClass("error");
                    empty = true;
                } else {
                    $(this).removeClass("error");
                    // if validated submit row
                    submitRowAsForm(trId);
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

            $(this).parents("tr").find("td:eq(0)").each(function () {
                $(this).html('<input type="text" class="form-control" name="units" id="units" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(1)").each(function () {
                if($(this).is("td:empty")) {
                    $(this).html('<input type="checkbox" class="checkbox" value="1" name="c_width" id="c_width">');
                } else {
                    $(this).html('<input type="checkbox" class="checkbox" value="1" name="c_width" id="c_width" checked>');
                }
            });
            $(this).parents("tr").find("td:eq(2)").each(function () {
                if($(this).is("td:empty")) {
                    $(this).html('<input type="checkbox" class="checkbox" value="1" name="c_length" id="c_length">');
                } else {
                    $(this).html('<input type="checkbox" class="checkbox" value="1" name="c_length" id="c_length" checked>');
                }
            });
            $(this).parents("tr").find("td:eq(3)").each(function () {
                if($(this).is("td:empty")) {
                    $(this).html('<input type="checkbox" class="checkbox" value="1" name="ts_width" id="ts_width">');
                } else {
                    $(this).html('<input type="checkbox" class="checkbox" value="1" name="ts_width" id="ts_width" checked>');
                }
            });
            $(this).parents("tr").find("td:eq(4)").each(function () {
                if($(this).is("td:empty")) {
                    $(this).html('<input type="checkbox" class="checkbox" value="1" name="ts_length" id="ts_length">');
                } else {
                    $(this).html('<input type="checkbox" class="checkbox" value="1" name="ts_length" id="ts_length" checked>');
                }
            });

            $(this).parents("tr").find(".add, .edit").toggle();
            $(".add-new, .edit").attr("disabled", "disabled");
        });

        // Delete row on delete button click
        $(document).on("click", ".delete", function(){
            var uid = $(this).attr('id');
            $('#measurementUnitId').val(uid);
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

