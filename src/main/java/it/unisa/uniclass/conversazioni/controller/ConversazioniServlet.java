package it.unisa.uniclass.conversazioni.controller;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.service.CorsoLaureaService;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Utente;
import it.unisa.uniclass.utenti.service.UtenteService;
import it.unisa.uniclass.conversazioni.service.ConversazioneService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet("/Conversazioni")
public class ConversazioniServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConversazioneService conversazioneService = new ConversazioneService();
        CorsoLaureaService corsoLaureaService = new CorsoLaureaService();
        UtenteService utenteService = new UtenteService();
        CorsoLaureaService corsoService = new CorsoLaureaService();
        String email = request.getParameter("utenteEmail");
        Utente user = utenteService.retrieveByEmail(email);

        List<CorsoLaurea> corsi = corsoLaureaService.trovaTutti();
        System.out.println(corsi);
        request.setAttribute("corsi", corsi);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
