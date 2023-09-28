<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.nylon.second.servlets.beans.Storage" %>
<jsp:useBean id="storage" scope="application" class="com.nylon.second.servlets.beans.Storage"/>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Lab 2</title>
    <link rel="stylesheet" href="css/main.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/js-cookie/3.0.1/js.cookie.min.js"></script>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap" rel="stylesheet">
    <script type="text/javascript" src="js/button.js"></script>
    <script type="text/javascript" src="js/validateForm.js"></script>
    <script type="text/javascript" src="js/onStart.js"></script>
</head>
<body>
<div class="bg">
    <div id="alertContainer">
        <div id="alert">
        <span id="alertText">
            <img id="icond" src="content/error.png"> You have entered incorrect values.
        </span>
        </div>
    </div>
    <table id="outer">
        <thead>
        <tr>
            <th id="header" colspan="3">
                <h2 name="name" class="text">Steputenko Ilya Sergeevich</h2>
                <h3 name="group" class="text">P3212 | 2241</h3>
            </th>
        </tr>
        </thead>
        <tbody>
        <tr id="firstRow">
            <td>
                <table id="materials">
                    <tr>
                        <td>
                            <div id="graph">
                            </div>
                            <img src="content/areas.png" alt="area">
                        </td>
                    </tr>
                    <tr id="formContainer">
                        <td>
                            <iframe name="votar" style="display:none;"></iframe>
                            <form onsubmit="sendData(); return false;" method="get" id="form" target="votar">
                                <div class="form">
                                    <div class="form__row">
                                        <div class="form__input">
                                            <input type="checkbox" name="x" id="x-1" value="-2">
                                            <label for="x-1">-2</label>
                                        </div>
                                        <div class="form__input">
                                            <input type="checkbox" name="x" id="x-2" value="-1.5">
                                            <label for="x-2">-1.5</label>
                                        </div>
                                        <div class="form__input">
                                            <input type="checkbox" name="x" id="x-3" value="-0.5">
                                            <label for="x-3">-0.5</label>
                                        </div>
                                        <div class="form__input">
                                            <input type="checkbox" name="x" id="x-4" value="0">
                                            <label for="x-4">0</label>
                                        </div>
                                        <div class="form__input">
                                            <input type="checkbox" name="x" id="x-5" value="0.5">
                                            <label for="x-5">0.5</label>
                                        </div>
                                        <div class="form__input">
                                            <input type="checkbox" name="x" id="x-6" value="1">
                                            <label for="x-6">1</label>
                                        </div>
                                        <div class="form__input">
                                            <input type="checkbox" name="x" id="x-7" value="1.5">
                                            <label for="x-7">1.5</label>
                                        </div>
                                        <div class="form__input">
                                            <input type="checkbox" name="x" id="x-8" value="2">
                                            <label for="x-8">2</label>
                                        </div>
                                    </div>
                                    <div class="form__row">
                                        <input type="text" name="y" id="y" class="form__input" placeholder="Y: (-3; 5)" required>
                                    </div>
                                    <div class="form__row">
                                        <div class="form__input">
                                            <input type="checkbox" name="r" id="r-1" value="1">
                                            <label for="r-1">1</label>
                                        </div>
                                        <div class="form__input">
                                            <input type="checkbox" name="r" id="r-2" value="2">
                                            <label for="r-2">2</label>
                                        </div>
                                        <div class="form__input">
                                            <input type="checkbox" name="r" id="r-3" value="3">
                                            <label for="r-3">3</label>
                                        </div>
                                        <div class="form__input">
                                            <input type="checkbox" name="r" id="r-4" value="4">
                                            <label for="r-4">4</label>
                                        </div>
                                        <div class="form__input">
                                            <input type="checkbox" name="r" id="r-5" value="5">
                                            <label for="r-5">5</label>
                                        </div>
                                    </div>
                                    <div class="form__row">
                                        <input type="submit" value="Shoot!" class="form__button">
                                    </div>
                                </div>
                            </form>
                            <button onclick="clearTable();" class="form__button clear">Clear</button>
                        </td>
                    </tr>
                </table>
            </td>
            <td>
                <table id="result">
                    <thead>
                    <tr>
                        <th>X</th>
                        <th>Y</th>
                        <th>R</th>
                        <th>Result</th>
                        <th>Execution time</th>
                        <th>Response time</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%=storage.getRows()%>
                    </tbody>
                </table>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</body>
</html>