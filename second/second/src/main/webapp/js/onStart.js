$(document).ready(function () {
    $("#result tbody tr").each(function () {
        let x = $(this).children().eq(0).text();
        let y = $(this).children().eq(1).text();
        let r = $(this).children().eq(2).text();
        let result = $(this).children().eq(3).text() == "true";
        drawPoint(x, y, r, result);
    });
    $("#graph").click(function (event) {
        let r;
        correctCheckBox = true;
        for (let i = 1; i <= 5; i++) {
            if ($("#r-" + i).prop("checked")) {
                if (correctCheckBox) {
                    r = new Button($("#r-" + i).val(), 1, 5, "r-" + i);
                    correctCheckBox = false;
                }
                else {
                    displayAlert();
                    $("#r-" + i).prop("checked", false);
                    return;
                }
            }
        }
        if (correctCheckBox) {
            displayAlert();
            return;
        }
        let x = (event.pageX - $("#graph").offset().left - 105) / 85 * r.getCoord();
        let y = -(event.pageY - $("#graph").offset().top - 105) / 85 * r.getCoord();
        drawPoint(x, y, r.getCoord(), true);
        $.get("/second-1.0-SNAPSHOT/ControllerServlet", {
            operation: "insert",
            x: x,
            y: y,
            r: r.getCoord(),
            currentTime: Date.now()
        }).done(function (point) {
                     drawPoint(point.x, point.y, point.r, point.result);
                     drawTableRow(point.x, point.y, point.r, point.currentTime, point.executionTime, point.result);
                 }
         ).fail(function (response) {
             displayAlert();
         });
    });
});