var redrawCounter = 0;

$(document).ready(function() {
    // remove href attribute of children of .ice-checkboxbutton
    $('.ice-checkboxbutton').children().removeAttr('href');
    $('.ui-slider.ui-slider-horizontal.ui-widget.ui-widget-content.ui-corner-all').css('width', '255px');
    $('.form__input').css('width', '250px');
    redrawGraph('R');
    // $("input.form__input").attr("value","");
    $("#graph").on("click", function() {
        var c = 0;
        var rval = 0;
        $(".ice-checkboxbutton").each(function() {
            var span = $(this).children().first().children().first();
            if (span.attr("aria-checked") === "true") {
                c++;
                rval = parseFloat($(this).children().first().next().html());
            }
        });
        if (c !== 1) {
            displayAlert();
            return;
        }
        // click on form button
        // console.log(event.pageX, event.pageY, $(this).offset().left, $(this).offset().top, rval);
        $("input.special__input").attr("value", (event.pageX - $(this).offset().left - 105)/85 * rval);
        $("input.form__input").attr("value",-(event.pageY - $(this).offset().top - 105)/85 * rval);
        // console.log($(".ui-sliderentry"), $(".form__input"));
        $(".form__button.shoot").click();
    });
    // on radius button click redraw graph
    $(".ice-checkboxbutton").on("click", function() {
        redrawCounter++;
        if (redrawCounter%2 === 1)
            redrawGraph($(this).children().first().next().html());
        else
            redrawGraph('R');
    });

    // // on any change of input which is a child of .ui_sliderentry copy results to .special__input
    // $(".form__row").first().on("click", function() {
    //     $(".special__input").attr("value", $(this).children().first().children().first().attr("value"));
    // });

    // on change of input attrubute "value" which is a child of .ui_sliderentry copy results to .special__input attribute "value"
    $(".form__row").first().hover(function() {
        $(".special__input").attr("value", $(".form__row").first().children().first().children().first().attr("value"));
    });

    $("body").on("mousemove", function() {
        $(".special__input").attr("value", $(".form__row").first().children().first().children().first().attr("value"));
    });
});

function displayAlert() {
    $("#alertContainer").addClass("active");
    setTimeout(function () {
        $("#alertContainer").removeClass("active");
    }, 2300);
}