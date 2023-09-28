function start() {
  let startTime = performance.now();
  let x = new Button($("#x").val(), -5, 3, "x"),
      y = new Button($("#y").val(), -5, 3, "y"),
      r = new Button($("#r").val(), 1, 5, "r");

  if (parseFloat(x.getCoord()) == 25 && parseFloat(y.getCoord()) == 10 && parseFloat(r.getCoord()) == 2009) {
    $("#background-video").css("visibility", "visible");
    $("#header h2").css("color", "#fff");
    $("#header h3").css("color", "#fff");
    $("video").trigger("play");
    return;
  }

  var flag = true;
  [x, y, r].forEach(button => {
    if (!button.isValid()) {
      displayAlert();
      $("#" + button.getText()).addClass("error");
      flag = false;
      return;
    }
    $("#" + button.getText()).removeClass("error");
  });
  if (!flag) return;

  $.post("php/area.php", $("#form").serialize(), function (data) {
    let result = JSON.parse(data);
    let executionTime = formatTime(new Date());
    let row = "<tr class='fade-in'>";
    [x, y, r].forEach(button => {
      row += `<td>${button.getCoord()}</td>`;
    });
    row += `<td>${result.hit}</td><td>${executionTime}</td><td>${((performance.now()-startTime)/1000).toFixed(3)}</td></tr>`;
    $("#result tbody").append(row);
    setTimeout(function () {
      $(".fade-in").addClass("active");
    }, 10);
    const tableData = getTableData();
    Cookies.set("tableData", JSON.stringify(tableData));
  });
}

function getTableData() {
  const data = [];
  $("#result tbody tr").each(function () {
    const rowData = [];
    $(this).find("td").each(function () {
      rowData.push($(this).text());
    });
    data.push(rowData);
  });
  return data;
}

function formatTime(date) {
  let hours = String(date.getHours()).padStart(2, '0');
  let minutes = String(date.getMinutes()).padStart(2, '0');
  let seconds = String(date.getSeconds()).padStart(2, '0');

  return hours + ":" + minutes + ":" + seconds;
}

function displayAlert() {
  $("#alertContainer").addClass("active");
  setTimeout(function () {
    $("#alertContainer").removeClass("active");
  }, 2300);
}

function clearTable() {
  $("#result tbody").empty();
  Cookies.remove("tableData");
}