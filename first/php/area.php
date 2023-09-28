<?php

function validateXCoord($x) {
    return isset($x) && is_numeric($x) && $x >= -5 && $x <= 3;
}

function validateYCoord($x) {
    return isset($x) && is_numeric($x) && $x >= -5 && $x <= 3;
}

function validateRCoord($x) {
    return isset($x) && is_numeric($x) && $x >= 1 && $x <= 4;
}

$x = $_POST['x'];
$y = $_POST['y'];
$r = $_POST['r'];

if (!validateXCoord($x) || !validateYCoord($y) || !validateRCoord($r)) {
    http_response_code(400);
    die("Invalid coordinates");
}

$hit = false;
if ($x > 0 && $y > 0) {
    $hit = ($x * $x + $y * $y) <= ($r * $r);
} else if ($x < 0 && $y > 0) {
    $hit = false;
} else if ($x < 0 && $y < 0) {
    $hit = $y >= -2 * $x - $r;
} else if ($x > 0 && $y < 0) {
    $hit = $x <= $r && $y >= -$r;
}

echo json_encode([
    "hit" => $hit
]);

?>