package it.unisa.uniclass.conversazioni.controller;

import it.unisa.uniclass.conversazioni.model.Messaggio;
import it.unisa.uniclass.conversazioni.service.MessaggioService;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.service.CorsoLaureaService;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Utente;
import it.unisa.uniclass.utenti.service.AccademicoService;
import it.unisa.uniclass.utenti.service.UtenteService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@WebServlet(name = "ConversazioniServlet", value = "/Conversazioni")
public class ConversazioniServlet extends HttpServlet {

    @EJB
    private MessaggioService messaggioService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

     @Override
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         HttpSession session = request.getSession();
         String email = session.getAttribute("utenteEmail").toString();


         AccademicoService accademicoService = new AccademicoService();
         Accademico accademicoSelf = accademicoService.trovaEmailUniClass(email);

         //Messaggi ricevuti dall'accademicoSelf
         List<Messaggio> messaggiRicevuti = messaggioService.trovaMessaggiRicevuti(email);
         //Messaggi inviati
         List<Messaggio> messaggiInviati = messaggioService.trovaMessaggiInviati(email);

         List<Messaggio> messaggi = messaggioService.trovaAvvisi();

         request.setAttribute("accademicoSelf", accademicoSelf);
         request.setAttribute("messaggiRicevuti", messaggiRicevuti);
         request.setAttribute("messaggiInviati", messaggiInviati);
         request.setAttribute("messaggi", messaggi);

         request.getRequestDispatcher("Conversazioni.jsp").forward(request, response);
     }
}
