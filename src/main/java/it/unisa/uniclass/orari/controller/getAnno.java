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

@WebServlet(name = "getAnno", value = "/getAnno")
public class getAnno extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String corsoLaurea = request.getParameter("corsoLaurea");
        if (corsoLaurea == null || corsoLaurea.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        CorsoLaureaService corsoLaureaService = new CorsoLaureaService();
        CorsoLaurea corsoL = corsoLaureaService.trovaCorsoLaurea(corsoLaurea);

        AnnoDidatticoService annoDidatticoService = new AnnoDidatticoService();
        List<AnnoDidattico> anni = annoDidatticoService.trovaPerCorsoLaurea(corsoL);

        // Creazione della risposta JSON
        JSONArray jsonArray = new JSONArray();
        for (AnnoDidattico anno : anni) {
            Map<String, String> map = new HashMap<>();
            map.put("id", String.valueOf(anno.getId()));
            map.put("nome", anno.getAnno());
            jsonArray.put(map);
        }

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonArray.toString());
    }
}
