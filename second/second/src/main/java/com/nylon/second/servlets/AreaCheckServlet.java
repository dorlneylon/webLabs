package com.nylon.second.servlets;

import com.nylon.second.servlets.beans.Coordinates;
import com.nylon.second.servlets.beans.HttpError;
import com.nylon.second.servlets.beans.Row;
import com.nylon.second.servlets.beans.Storage;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
// import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "AreaCheckServlet", value = "/AreaCheckServlet")
public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Coordinates coordinates = (Coordinates)request.getAttribute("coordinates");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentTime = LocalDateTime.now().format(formatter);
        Long startTime = Long.parseLong(request.getParameter("currentTime"));
        double executionTime = (System.currentTimeMillis() - startTime) / 1000000.0;
        request.setAttribute("currentTime", currentTime);
        Storage storage = (Storage)getServletContext().getAttribute("storage");
        if (storage == null)
            storage = new Storage();
        Row row = new Row(coordinates.getX(), coordinates.getY(), coordinates.getR(), isHit(coordinates), currentTime, executionTime);
        storage.add(row);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(row));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", new HttpError(404, "<h1>Page was not found:(</h1>"));
    }

    private boolean isHit(Coordinates coordinates) {
        return isQCircleHit(coordinates) || isRectangleHit(coordinates) || isTriangleHit(coordinates);
    }

    private boolean isTriangleHit(Coordinates coordinates) {
        boolean isSecondQuarter = coordinates.getX() <= 0 && coordinates.getY() >= 0;
        return isSecondQuarter && (coordinates.getY() <= coordinates.getX() + coordinates.getR()/2);
    }

    private boolean isRectangleHit(Coordinates coordinates) {
        boolean isFourthQuarter = coordinates.getX() >= 0 && coordinates.getY() <= 0;
        return isFourthQuarter && coordinates.getY() >= -coordinates.getR() && coordinates.getX() <= coordinates.getR();
    }

    private boolean isQCircleHit(Coordinates coordinates) {
        boolean isFirstQuarter = coordinates.getX() >= 0 && coordinates.getY() >= 0;
        return isFirstQuarter && (coordinates.getX() * coordinates.getX() + coordinates.getY() * coordinates.getY() <= coordinates.getR() * coordinates.getR());
    }
}
