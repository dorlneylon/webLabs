package com.nylon.second.servlets;

import com.nylon.second.servlets.beans.HttpError;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ErrorHandlerServlet", value = "/ErrorHandlerServlet")
public class ErrorHandlerServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpError httpError = (HttpError) request.getAttribute("error");
        response.setStatus(httpError.getStatusCode());
        response.getWriter().println(httpError.getErrorMessage());
    }
}
