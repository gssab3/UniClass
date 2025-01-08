package it.unisa.uniclass.orari.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisa.uniclass.orari.model.AnnoDidattico;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import it.unisa.uniclass.orari.service.AnnoDidatticoService;
import it.unisa.uniclass.orari.service.CorsoLaureaService;
import it.unisa.uniclass.orari.service.RestoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "getResto", value = "/getResto")
public class getResto extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String corsoLaurea = request.getParameter("corsoLaurea");
        RestoService restoService = new RestoService();
        // Recupera i resti associati al corso di laurea
        List<Resto> resti = restoService.trovaRestiCorsoLaurea("corsoLaurea")

        // Imposta il content type e invia i dati come JSON
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(resti));
    }
}