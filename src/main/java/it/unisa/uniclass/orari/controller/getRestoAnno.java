package it.unisa.uniclass.orari.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.unisa.uniclass.orari.model.AnnoDidattico;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import it.unisa.uniclass.orari.service.AnnoDidatticoService;
import it.unisa.uniclass.orari.service.CorsoLaureaService;
import it.unisa.uniclass.orari.service.RestoService;
import it.unisa.uniclass.utenti.service.UtenteService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "getRestoAnno", value = "/getRestoAnno")
public class getRestoAnno extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String corsoLaurea = request.getParameter("corsoLaurea");
        CorsoLaureaService corsoLaureaService = new CorsoLaureaService();
        CorsoLaurea corsoL = corsoLaureaService.trovaCorsoLaurea("corsoLaurea");

        RestoService restoService = new RestoService();
        AnnoDidatticoService annoDidatticoService = new AnnoDidatticoService();

        List<Resto> resti = restoService.trovaRestiCorsoLaurea(corsoL);
        List<AnnoDidattico> anni = annoDidatticoService.trovaPerCorsoLaurea(corsoL);

        Map<String, Object> result = new HashMap<>();
        result.put("resti", resti);
        result.put("anni", anni);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try (PrintWriter out = response.getWriter()) {
            ObjectMapper mapper = new ObjectMapper();
            String jsonResponse = mapper.writeValueAsString(result);
            out.print(jsonResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}