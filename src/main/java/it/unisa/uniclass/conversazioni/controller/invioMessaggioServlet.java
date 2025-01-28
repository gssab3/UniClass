package it.unisa.uniclass.conversazioni.controller;

import it.unisa.uniclass.conversazioni.model.Conversazione;
import it.unisa.uniclass.conversazioni.model.Messaggio;
import it.unisa.uniclass.conversazioni.service.ConversazioneService;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.service.AccademicoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "invioMessaggioServlet", value = "/invioMessaggio")
public class invioMessaggioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String emailSession = (String) session.getAttribute("utenteEmail");
        String emailDest = request.getParameter("email");
        String messaggio = request.getParameter("testo");



        AccademicoService accademicoService = new AccademicoService();
        Accademico accademicoSelf = accademicoService.trovaEmailUniClass(emailSession);
        Accademico accademicoDest = accademicoService.trovaEmailUniClass(emailDest);
        Set<Accademico> messaggeri = new HashSet<>();
        messaggeri.add(accademicoSelf);
        messaggeri.add(accademicoDest);

        Messaggio messaggio1 = new Messaggio();

        Conversazione conversazione = new Conversazione();
        conversazione.setMessaggeri(messaggeri);

        conversazione.getMessaggi().add(messaggio);




    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
