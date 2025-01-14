package it.unisa.uniclass.conversazioni.controller;

import it.unisa.uniclass.conversazioni.model.Conversazione;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.service.CorsoLaureaService;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Utente;
import it.unisa.uniclass.utenti.service.AccademicoService;
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

        String email = request.getParameter("utenteEmail");

        AccademicoService accademicoService = new AccademicoService();
        Accademico accademicoSelf = accademicoService.trovaEmailUniClass(email);

        ConversazioneService conversazioneService = new ConversazioneService();
        List<Conversazione> conversazioni = conversazioneService.trovaConversazioneAccademico(accademicoSelf);


        List<Accademico> accademici = conversazioneService.trovaAccademiciConversazione(accademicoSelf);

        request.setAttribute("conversazioni", conversazioni);
        request.setAttribute("accademici", accademici);
        request.setAttribute("accademicoSelf", accademicoSelf);

        request.getRequestDispatcher("Conversazioni.jsp").forward(request, response);
    }
}
