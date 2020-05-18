<!-- script for raised/flat roof + validate -->

$(document).ready(function () {
    $('#RoofOption').on('change', function () {
        // if roof option have value 1, show div #raisedRoof and add attribute required
        // on select box #RoofRaised and #RoofOptionDegrees. Then hide div #flatRoof and
        // remove attribute required.
        if (this.value == '1') {
            $("#raisedRoof").show();
            $("#RoofRaised").attr("required", true);
            $("#RoofOptionDegrees").attr("required", true);
            $("#flatRoof").hide();
            $("#RoofFlat").attr("required", false);
        } else {
            // else hide div #raisedRoof and remove attribute required from #RoofRaised and
            // #RoofOptionDegrees. Then show div #flatRoof and add attribute required to #RoofFlat
            $("#raisedRoof").hide();
            $("#RoofRaised").attr("required", false);
            $("#RoofOptionDegrees").attr("required", false);
            $("#flatRoof").show();
            $("#RoofFlat").attr("required", true);
        }
    });
});


<!-- validation of shed option -->

$(document).ready(function () {
    $("#ShedWidth,#ShedLength").on('change', function () {

        // get shed width and/or length value
        var sw = $('#ShedWidth').val();
        var sl = $('#ShedLength').val();

        // if only shed width or shed length have value or both have value
        if((sw === '' && sl !== '') || (sw !== '' && sl === '') || (sw !== '' && sl !== '')) {
            // set attribute required to select box
            $("#ShedWidth").attr("required", true);
            $("#ShedLength").attr("required", true);
        } else {
            // remove attribute required from select box
            $("#ShedWidth").attr("required", false);
            $("#ShedLength").attr("required", false);
        }
    });
});


<!-- validate shed width option, so shed width is lower than carport width -->

$(document).ready(function () {
    $("#CarportWidth").on('change', function () {

        // get selected value from carport width
        var cw = $("#CarportWidth").val();

        // find selected value from shed width
        var sw = $("#ShedWidth option:selected").val();

        // create array off all values from shed width
        var values = [];
        $("#ShedWidth option").each(function() {
            values.push( $(this).attr('value') );
        });

        // if shed width is larger than carport width, reset shed width select box
        if(cw <= sw) {
            $("#ShedWidth option").prop('selected', function() {
                return this.defaultSelected;
            });
        }

        // Enable all options
        $("#ShedWidth option").prop("disabled", false).removeClass('optionDisabled');

        // loop through array and add 'disable' on option higher than carport width
        var i;
        for (i = 0; i < values.length; ++i) {
            if(values[i] >= cw){
                $("#ShedWidth option[value*='" + values[i] + "']").prop('disabled',true).addClass('optionDisabled');

            }
        }

    });
});


<!-- validate shed length option, so shed length is lower than carport length -->

$(document).ready(function () {
    $("#CarportLength").on('change', function () {

        // get selected value from carport length
        var cl = $("#CarportLength").val();

        // find selected value from shed length
        var sl = $("#ShedLength option:selected").val();

        // create array off all values from shed length
        var values = [];
        $("#ShedLength option").each(function() {
            values.push( $(this).attr('value') );
        });

        // if shed length is larger than carport length, reset shed length select box
        if(cl <= sl) {
            $("#ShedLength option").prop('selected', function() {
                return this.defaultSelected;
            });
        }

        // Enable all options
        $("#ShedLength option").prop("disabled", false).removeClass('optionDisabled');

        // loop through array and add 'disable' on option higher than carport length
        var i;
        for (i = 0; i < values.length; ++i) {
            if(values[i] >= cl){
                $("#ShedLength option[value*='" + values[i] + "']").prop('disabled',true).addClass('optionDisabled');

            }
        }

    });
});


<!-- user-validation -->

// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
    'use strict';
    window.addEventListener('load', function() {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();





