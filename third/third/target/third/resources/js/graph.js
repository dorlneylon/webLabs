const canvas = document.getElementById("graphImage");
const ctx = canvas.getContext("2d");

canvas.height = canvas.width;
let w = canvas.width,
    h = canvas.height;

const hatchWidth = 20 / 2;
const hatchGap = 56;

let rValue = "R";
function redrawGraph(r) {
    ctx.clearRect(0, 0, w, h);

    ctx.lineWidth = 2;
    ctx.strokeStyle = "grey";

    // y axis
    ctx.beginPath();
    ctx.moveTo(w / 2, 0);
    ctx.lineTo(w / 2 - 10, 15);
    ctx.moveTo(w / 2, 0);
    ctx.lineTo(w / 2 + 10, 15);
    ctx.moveTo(w / 2, 0);
    ctx.lineTo(w / 2, h);
    ctx.stroke();
    ctx.closePath();

    // x axis
    ctx.beginPath();
    ctx.moveTo(w, h / 2);
    ctx.lineTo(w - 15, h / 2 - 10);
    ctx.moveTo(w, h / 2);
    ctx.lineTo(w - 15, h / 2 + 10);
    ctx.moveTo(w, h / 2);
    ctx.lineTo(0, h / 2);
    ctx.stroke();
    ctx.closePath();

    // Рисуем первую четверть (квадрат)
    ctx.beginPath();
    ctx.moveTo(w / 2 - hatchWidth, h / 2 - hatchGap);
    ctx.lineTo(w / 2 + hatchWidth, h / 2 - hatchGap);
    ctx.moveTo(w / 2 - hatchWidth, h / 2 - hatchGap * 2);
    ctx.lineTo(w / 2 + hatchWidth, h / 2 - hatchGap * 2);
    ctx.moveTo(w / 2 - hatchWidth, h / 2 + hatchGap);
    ctx.lineTo(w / 2 + hatchWidth, h / 2 + hatchGap);
    ctx.moveTo(w / 2 - hatchWidth, h / 2 + hatchGap * 2);
    ctx.lineTo(w / 2 + hatchWidth, h / 2 + hatchGap * 2);
    ctx.moveTo(w / 2 - hatchGap, h / 2 - hatchWidth);
    ctx.lineTo(w / 2 - hatchGap, h / 2 + hatchWidth);
    ctx.moveTo(w / 2 - hatchGap * 2, h / 2 - hatchWidth);
    ctx.lineTo(w / 2 - hatchGap * 2, h / 2 + hatchWidth);
    ctx.moveTo(w / 2 + hatchGap, h / 2 - hatchWidth);
    ctx.lineTo(w / 2 + hatchGap, h / 2 + hatchWidth);
    ctx.moveTo(w / 2 + hatchGap * 2, h / 2 - hatchWidth);
    ctx.lineTo(w / 2 + hatchGap * 2, h / 2 + hatchWidth);
    ctx.stroke();
    ctx.closePath();

    ctx.fillStyle = "#4040bf55";
    ctx.fillRect(150,38,114,112);

    ctx.beginPath();
    ctx.moveTo(150, 95);
    ctx.lineTo(95, 149);
    ctx.lineTo(149, 150);
    ctx.closePath();

    ctx.moveTo(150, 150);
    ctx.arc(150,150,113,-Math.PI,(-3/2)*Math.PI,1);
    ctx.fill();

    ctx.strokeStyle = "#4040bf55";
    // ctx.stroke();
    ctx.closePath();

    const fontSize = hatchGap / 3.5;
    ctx.fillStyle = "black";

    ctx.font = `500 ${fontSize * 1.4}px Roboto`;
    ctx.fillText("y", w / 2 - hatchWidth * 2.8, 15);
    ctx.fillText("x", w - 20, h / 2 - hatchWidth * 2.4);

    let label1, label2;
    if (isNaN(r)) {
        label1 = r + "/2";
        label2 = r;
    } else {
        label1 = r / 2;
        label2 = r;
    }
    rValue = label2;

    ctx.font = `800 ${fontSize}px Roboto`;
    ctx.fillText(label1, w / 2 + hatchGap - 3, h / 2 + hatchWidth * 2.8);
    ctx.fillText(label2, w / 2 + hatchGap * 2 - 3, h / 2 + hatchWidth * 2.8);
    ctx.fillText("-" + label1, w / 2 - hatchGap - 7, h / 2 + hatchWidth * 2.8);
    ctx.fillText(
        "-" + label2,
        w / 2 - hatchGap * 2 - 7,
        h / 2 + hatchWidth * 2.8
    );

    ctx.fillText(label1, w / 2 + hatchWidth * 2, h / 2 - hatchGap + 3);
    ctx.fillText(label2, w / 2 + hatchWidth * 2, h / 2 - hatchGap * 2 + 3);
    ctx.fillText("-" + label1, w / 2 + hatchWidth * 2, h / 2 + hatchGap + 3);
    ctx.fillText("-" + label2, w / 2 + hatchWidth * 2, h / 2 + hatchGap * 2 + 3);
}

// Вызываем функцию redrawGraph с радиусом R
redrawGraph("R")