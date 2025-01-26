package it.unisa.uniclass.utenti.controller;

import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.service.AccademicoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "GetAttivati", value = "/GetAttivati")
public class GetAttivati extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        AccademicoService accademicoService = new AccademicoService();

        List<Accademico> attivati = accademicoService.trovaAttivati(true);

        JSONArray jsonArray = new JSONArray();

        for (Accademico accademico : attivati) {
            JSONObject jsonUtente = new JSONObject();
            jsonUtente.put("email",accademico.getEmail());

            jsonArray.put(jsonUtente);
        }

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        resp.getWriter().write(jsonArray.toString());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
