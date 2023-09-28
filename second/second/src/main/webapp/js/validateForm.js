function sendData() {
  let startTime = performance.now();
  let correctCheckBox = 1;
  let x, y, r;

  for (let i = 1; i <= 8; i++) {
  $("#x-"+i).parent().removeClass("error");
    if ($("#x-" + i).prop("checked")) {
      if (correctCheckBox == 1) {
        x = new Button($("#x-" + i).val(), -2, 2, "x-" + i);
        correctCheckBox++;
      }
      else {
        displayAlert();
        correctCheckBox++;
        $("#"+x.getText()).parent().addClass("error");
        $("#x-" + i).parent().addClass("error");
      }
    }
  }
  if (correctCheckBox > 2)
      return;
  y = new Button($("#y").val(), -3, 5, "y");
  correctCheckBox = 1;
    for (let i = 1; i <= 5; i++) {
    $("#r-" + i).parent().removeClass("error");
        if ($("#r-" + i).prop("checked")) {
            if (correctCheckBox == 1) {
                r = new Button($("#r-" + i).val(), 1, 5, "r-" + i);
                correctCheckBox++;
            }
            else {
                displayAlert();
                correctCheckBox++;
                $("#"+r.getText()).parent().addClass("error");
                $("#r-" + i).parent().addClass("error");
            }
        }
    }
  if (correctCheckBox > 2)
    return;

  var flag = true;
  [x, y, r].forEach(button => {
    if (button == null || !button.isValid()) {
      displayAlert();
      $("#" + button.getText()).addClass("error");
      flag = false;
      return;
    }
    $("#" + button.getText()).removeClass("error");
  });
  if (!flag) return;
    $.get("/second-1.0-SNAPSHOT/ControllerServlet", {
        operation: "insert",
        x: x.getCoord(),
        y: y.getCoord(),
        r: r.getCoord(),
        currentTime: Date.now()
    }).done(function (point) {
            drawPoint(point.x, point.y, point.r, point.result);
            drawTableRow(point.x, point.y, point.r, point.currentTime, point.executionTime, point.result);
        }
    ).fail(function (response) {
        displayAlert();
    });
}

function formatTime(date) {
    let hours = date.getHours();
    let minutes = date.getMinutes();
    let seconds = date.getSeconds();
    if (hours < 10) hours = "0" + hours;
    if (minutes < 10) minutes = "0" + minutes
    if (seconds < 10) seconds = "0" + seconds;
  return hours + ":" + minutes + ":" + seconds;
}

function drawPoint(x, y, r, result) {
    let point = $("<div></div>").addClass("point");
    point.css("margin-left", 105 + x/r * 85);
    point.css("margin-top", 105 + -y/r * 85);
    point.addClass(result ? "hit" : "miss");
    $("#graph").append(point);

}

function drawTableRow(x, y, r, currentTime, executionTime, result) {
    let table = $("#result tbody");
    let row = $("<tr></tr>");
    row.append($("<td></td>").text(x));
    row.append($("<td></td>").text(y));
    row.append($("<td></td>").text(r));
    row.append($("<td></td>").text(result));
    row.append($("<td></td>").text(currentTime));
    row.append($("<td></td>").text(executionTime));
    table.append(row);
}

function displayAlert() {
  $("#alertContainer").addClass("active");
  setTimeout(function () {
    $("#alertContainer").removeClass("active");
  }, 2300);
}

function clearTable() {
  $("#result tbody").empty();
  $.get("/second-1.0-SNAPSHOT/ControllerServlet", {
    operation:"clear"
  });
  $(".point").remove();
}