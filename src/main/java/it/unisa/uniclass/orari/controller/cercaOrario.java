package it.unisa.uniclass.orari.controller;

import it.unisa.uniclass.orari.model.AnnoDidattico;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Lezione;
import it.unisa.uniclass.orari.model.Resto;
import it.unisa.uniclass.orari.service.AnnoDidatticoService;
import it.unisa.uniclass.orari.service.CorsoLaureaService;
import it.unisa.uniclass.orari.service.LezioneService;
import it.unisa.uniclass.orari.service.RestoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "cercaOrarioServlet", value = "/cercaOrario")
public class cercaOrario extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String corsoNome = request.getParameter("corsoLaurea");
        String restoNome = request.getParameter("resto");
        String annoNome = request.getParameter("anno");


        CorsoLaureaService corsoLaureaService = new CorsoLaureaService();
        CorsoLaurea corsoLaurea = corsoLaureaService.trovaCorsoLaurea(corsoNome);

        RestoService restoService = new RestoService();
        List<Resto> resti = restoService.trovaResto(restoNome);
        Resto resto = null;
        for (Resto r : resti) {
            if(corsoLaurea.getResti().contains(r)) {
                resto = r;
            }
        }

        AnnoDidatticoService annoDidatticoService = new AnnoDidatticoService();
        List<AnnoDidattico> anni = annoDidatticoService.trovaAnno(annoNome);
        AnnoDidattico anno = null;
        for (AnnoDidattico a : anni) {
            if (corsoLaurea.getAnniDidattici().contains(a)){
                anno = a;
            }
        }

        LezioneService lezioneService = new LezioneService();
        List<Lezione> lezioni = lezioneService.trovaCorsoRestoAnno(corsoLaurea,resto,anno);

        request.setAttribute("lezioni", lezioni);

        request.setAttribute("corsoLaurea", corsoLaurea);
        request.setAttribute("resto", resto);
        request.setAttribute("anno", anno);

        request.getRequestDispatcher("/OrarioSingolo.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
