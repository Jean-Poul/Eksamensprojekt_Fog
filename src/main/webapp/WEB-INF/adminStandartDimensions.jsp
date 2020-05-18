<!-- Header & imports -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@include file="../includes/headerlogout.inc" %>
<!-- End header -->
<style>

    .add-new {
        font-weight: bold;
    }
    table.table th:first-child {
        #width: 100px;
    }
    table.table th:last-child {
        width: 80px;
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
    <h2>Standardmål</h2>
    <p>Her kan der rettes standardmål til udregning af carport og skur:</p>
    <table class="table table-sm">
        <thead>
        <tr>
            <th>Bund lægte spænd</th>
            <th>Bund lægter</th>
            <th>Top lægte gab</th>
            <th>Gns. lægte spænd</th>
            <th>Tagsten længde</th>
            <th>Tagsten bredde</th>
            <th>Tag trapes længde</th>
            <th>Tag trapes bredde</th>
            <th>Skur beklædningsplade dimension</th>
            <th>Bjælke dimension tung</th>
            <th>Bjælke dimension let</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>35</td>
            <td>2</td>
            <td>3</td>
            <td>30</td>
            <td>25</td>
            <td>20</td>
            <td>240</td>
            <td>109</td>
            <td>19x100</td>
            <td>125 x 125</td>
            <td>100 x 100</td>
            <td class="text-right">
                <button type="submit" class="add btn btn-sm btn-success" data-toggle="tooltip"><span class="fa fa-plus"></span> Tilføj</button>
                <button class="edit btn btn-sm btn-warning" data-toggle="tooltip"><span class="fa fa-pencil"></span> ret</button>
            </td>
        </tr>

        </tbody>
    </table>
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
                '<td><input type="text" class="form-control" name="bottom_lathspan" id="bottom_lathspan"></td>' +
                '<td><input type="text" class="form-control" name="bottom_laths" id="bottom_laths"></td>' +
                '<td><input type="text" class="form-control" name="top_lath_gap" id="top_lath_gap"></td>' +
                '<td><input type="text" class="form-control" name="avg_lath_span" id="avg_lath_span"></td>' +
                '<td><input type="text" class="form-control" name="roof_tile_length" id="roof_tile_length"></td>' +
                '<td><input type="text" class="form-control" name="roof_tile_width" id="roof_tile_width"></td>' +
                '<td><input type="text" class="form-control" name="roof_trapez_length" id="roof_trapez_length"></td>' +
                '<td><input type="text" class="form-control" name="roof_trapez_width" id="roof_trapez_width"></td>' +
                '<td><input type="text" class="form-control" name="shed_claddeing_board_dim" id="shed_claddeing_board_dim"></td>' +
                '<td><input type="text" class="form-control" name="beam_dimension_heavy" id="beam_dimension_heavy"></td>' +
                '<td><input type="text" class="form-control" name="beam_dimension_light" id="beam_dimension_light"></td>' +
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
            var bottom_lathspan = $(this).parents("tr").find("#bottom_lathspan");
            var bottom_laths = $(this).parents("tr").find("#bottom_laths");
            var top_lath_gap = $(this).parents("tr").find("#top_lath_gap");
            var avg_lath_span = $(this).parents("tr").find("#avg_lath_span");
            var roof_tile_length = $(this).parents("tr").find("#roof_tile_length");
            var roof_tile_width = $(this).parents("tr").find("#roof_tile_width");
            var roof_trapez_length = $(this).parents("tr").find("#roof_trapez_length");
            var roof_trapez_width = $(this).parents("tr").find("#roof_trapez_width");

            input.each(function(){
                var bottom_lathspans = bottom_lathspan.val();
                var bottom_lathss = bottom_laths.val();
                var top_lath_gaps = top_lath_gap.val();
                var avg_lath_spans = avg_lath_span.val();
                var roof_tile_lengths = roof_tile_length.val();
                var roof_tile_widths = roof_tile_width.val();
                var roof_trapez_lengths = roof_trapez_length.val();
                var roof_trapez_widths = roof_trapez_width.val();

                if(!$(this).val()){
                    $(this).addClass("error");
                    empty = true;
                } else {
                    $(this).removeClass("error");
                }

                if(!$.isNumeric(bottom_lathspans)) {
                    $("#bottom_lathspan").addClass("error");
                    empty = true;
                } else {
                    $(this).removeClass("error");
                }

                if(!$.isNumeric(bottom_lathss)) {
                    $("#bottom_laths").addClass("error");
                    empty = true;
                } else {
                    $(this).removeClass("error");
                }

                if(!$.isNumeric(top_lath_gaps)) {
                    $("#top_lath_gap").addClass("error");
                    empty = true;
                } else {
                    $(this).removeClass("error");
                }

                if(!$.isNumeric(avg_lath_spans)) {
                    $("#avg_lath_span").addClass("error");
                    empty = true;
                } else {
                    $(this).removeClass("error");
                }

                if(!$.isNumeric(roof_tile_lengths)) {
                    $("#roof_tile_length").addClass("error");
                    empty = true;
                } else {
                    $(this).removeClass("error");
                }

                if(!$.isNumeric(roof_tile_widths)) {
                    $("#roof_tile_width").addClass("error");
                    empty = true;
                } else {
                    $(this).removeClass("error");
                }

                if(!$.isNumeric(roof_trapez_lengths)) {
                    $("#roof_trapez_length").addClass("error");
                    empty = true;
                } else {
                    $(this).removeClass("error");
                }

                if(!$.isNumeric(roof_trapez_widths)) {
                    $("#roof_trapez_width").addClass("error");
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
                //$(".add-new, .edit").removeAttr("disabled");
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
                $(this).html('<input type="text" class="form-control" name="bottom_lathspan" id="bottom_lathspan" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(1)").each(function () {
                $(this).html('<input type="text" class="form-control" name="bottom_laths" id="bottom_laths" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(2)").each(function () {
                $(this).html('<input type="text" class="form-control" name="top_lath_gap" id="top_lath_gap" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(3)").each(function () {
                $(this).html('<input type="text" class="form-control" name="avg_lath_span" id="avg_lath_span" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(4)").each(function () {
                $(this).html('<input type="text" class="form-control" name="roof_tile_length" id="roof_tile_length" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(5)").each(function () {
                $(this).html('<input type="text" class="form-control" name="roof_tile_width" id="roof_tile_width" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(6)").each(function () {
                $(this).html('<input type="text" class="form-control" name="roof_trapez_length" id="roof_trapez_length" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(7)").each(function () {
                $(this).html('<input type="text" class="form-control" name="roof_trapez_width" id="roof_trapez_width" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(8)").each(function () {
                $(this).html('<input type="text" class="form-control" name="shed_claddeing_board_dim" id="shed_claddeing_board_dim" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(9)").each(function () {
                $(this).html('<input type="text" class="form-control" name="beam_dimension_heavy" id="beam_dimension_heavy" value="' + $(this).text() + '">');
            });
            $(this).parents("tr").find("td:eq(10)").each(function () {
                $(this).html('<input type="text" class="form-control" name="beam_dimension_light" id="beam_dimension_light" value="' + $(this).text() + '">');
            });

            $(this).parents("tr").find(".add, .edit").toggle();
            //$(".add-new, .edit").attr("disabled", "disabled");
        });
        // Delete row on delete button click
        $(document).on("click", ".delete", function(){
            $(this).parents("tr").remove();
            $(".add-new, .edit").removeAttr("disabled");
        });
    });
</script>