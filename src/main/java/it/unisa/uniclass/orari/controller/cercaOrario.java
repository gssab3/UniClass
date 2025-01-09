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


        //Prendo il resto
        RestoService restoService = new RestoService();
        Resto resto = restoService.trovaRestoNomeCorso(restoNome, corsoLaurea);

        //Prendo l'anno di quel certo corso (e l'anno ha un certo nome)
        AnnoDidatticoService annoDidatticoService = new AnnoDidatticoService();
        AnnoDidattico annoDidattico = annoDidatticoService.trovaTuttiCorsoLaureaNome(corsoLaurea.getId(),annoNome);

        //Prendo le lezioni di quel corsoLaurea, quel resto e quell'anno
        LezioneService lezioneService = new LezioneService();
        List<Lezione> lezioni = lezioneService.trovaLezioniCorsoLaureaRestoAnno(corsoLaurea.getId(),resto.getId(),annoDidattico.getId());

        System.out.println(lezioni);

        request.setAttribute("lezioni", lezioni);

        request.setAttribute("corsoLaurea", corsoLaurea);
        request.setAttribute("resto", resto);
        request.setAttribute("anno", annoDidattico);

        request.getRequestDispatcher("/OrarioSingolo.jsp").forward(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
