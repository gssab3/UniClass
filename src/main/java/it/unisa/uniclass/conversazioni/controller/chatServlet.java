package it.unisa.uniclass.conversazioni.controller;

import it.unisa.uniclass.conversazioni.model.Conversazione;
import it.unisa.uniclass.conversazioni.model.Messaggio;
import it.unisa.uniclass.conversazioni.service.ConversazioneService;
import it.unisa.uniclass.conversazioni.service.MessaggioService;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.service.AccademicoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "chatServlet", value = "/chatServlet")
public class chatServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //da rivedere

        String email = req.getParameter("accademico");
        String emailSelf = req.getParameter("accademicoSelf");

        AccademicoService accademicoService = new AccademicoService();
        Accademico accademico = accademicoService.trovaEmailUniClass(email);
        Accademico accademicoSelf = accademicoService.trovaEmailUniClass(emailSelf);

        ConversazioneService conversazioneService = new ConversazioneService();

        Conversazione conversazione = conversazioneService.trovaConversazioneDueAccademici(accademico, accademicoSelf);



        MessaggioService messaggioService = new MessaggioService();

        List<Messaggio> messaggi = messaggioService.trovaMessaggiConversazione(conversazione);

        req.setAttribute("messaggi", messaggi);
        req.setAttribute("accademico", accademico);
        req.setAttribute("accdemicoSelf", accademicoSelf);
        req.setAttribute("conversazione", conversazione);

        req.getRequestDispatcher("chat.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
