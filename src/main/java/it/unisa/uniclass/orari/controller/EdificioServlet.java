package it.unisa.uniclass.orari.controller;

import it.unisa.uniclass.orari.model.Aula;
import it.unisa.uniclass.orari.service.AulaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "EdificioServlet", value = "/EdificioServlet")
public class EdificioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String edificio = req.getParameter("ed");

        AulaService aulaService = new AulaService();

        List<Aula> aule = aulaService.trovaAuleEdificio(edificio);

        req.setAttribute("aule", aule);
        req.setAttribute("ed", edificio);
        req.getRequestDispatcher("/edificio.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
