package com.nylon.second.servlets;

import com.nylon.second.servlets.beans.Coordinates;
import com.nylon.second.servlets.beans.HttpError;
import com.nylon.second.servlets.beans.Storage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ControllerServlet", value = "/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("operation").equals("insert")) {
            try {
                Coordinates coordinates = new Coordinates(
                        Double.parseDouble(request.getParameter("x")),
                        Double.parseDouble(request.getParameter("y")),
                        Double.parseDouble(request.getParameter("r"))
                );
                request.setAttribute("currentTime", request.getParameter("currentTime"));
                request.setAttribute("coordinates", coordinates);
            } catch (NumberFormatException e) {
                request.setAttribute("error", new HttpError(400, "<h1>Bad request:(</h1>"));
                getServletContext().getRequestDispatcher("/ErrorHandlerServlet").forward(request, response);
                return;
            }
            getServletContext().getRequestDispatcher("/AreaCheckServlet").forward(request, response);
            return;
        }
        else if (request.getParameter("operation").equals("clear")) {
            getServletContext().setAttribute("storage", new Storage());
            return;
        }

        request.setAttribute("error", new HttpError(404, "<h1>Page was not found:(</h1>"));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", new HttpError(404, "<h1>Page was not found:(</h1>"));
        getServletContext().getRequestDispatcher("/ErrorHandlerServlet").forward(request, response);
    }

//    private boolean validateCoords(Coordinates coordinates) {
//        return  coordinates.getX() >= -2 && coordinates.getX() <= 2 &&
//                coordinates.getY() >= -3 && coordinates.getY() <= 5 &&
//                coordinates.getR() >= 1 && coordinates.getR() <= 5;
//    }
}
