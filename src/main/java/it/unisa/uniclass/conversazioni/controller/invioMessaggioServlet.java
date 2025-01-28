package it.unisa.uniclass.conversazioni.controller;

import it.unisa.uniclass.conversazioni.model.Conversazione;
import it.unisa.uniclass.conversazioni.model.Messaggio;
import it.unisa.uniclass.conversazioni.model.Topic;
import it.unisa.uniclass.conversazioni.service.ConversazioneService;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.service.AccademicoService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@WebServlet(name = "invioMessaggio", value = "/invioMessaggioServlet")
public class invioMessaggioServlet extends HttpServlet {

    @EJB
    private ConversazioneService conversazioneService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //Email attuale (autore del messaggio)
        String emailSession = (String) session.getAttribute("utenteEmail");

        //Email di destinazione
        String emailDest = request.getParameter("email");

        //Messaggio da inviare quando ci si trova al di sotto in conversazioni
        String messaggio = request.getParameter("testo");

        //Topic da inviare quando ci si trova nel lato Docente/Coordinatore e c'è un topic inviato
        String topic = (String) request.getParameter("topic");


        AccademicoService accademicoService = new AccademicoService();
        Accademico accademicoSelf = accademicoService.trovaEmailUniClass(emailSession);
        Accademico accademicoDest = accademicoService.trovaEmailUniClass(emailDest);

        Topic top = new Topic();
        if(topic != null) {
            top.setNome(topic);
            top.setCorsoLaurea(accademicoSelf.getCorsoLaurea());
        }

        if(conversazioneService.trovaConversazioneDueAccademici(accademicoDest, accademicoSelf) == null) {
            Set<Accademico> messaggeri = new HashSet<>();
            messaggeri.add(accademicoSelf);
            messaggeri.add(accademicoDest);

            Messaggio messaggio1 = new Messaggio();
            messaggio1.setAutore(accademicoSelf);
            messaggio1.setDestinatario(accademicoDest);
            messaggio1.setBody(messaggio);
            messaggio1.setDateTime(LocalDateTime.now());
            if(topic != null) {
                messaggio1.setTopic(top);
            }
            Conversazione conversazione = new Conversazione();
            conversazione.setMessaggeri(messaggeri);

            conversazione.getMessaggi().add(messaggio1);
            conversazioneService.aggiungiConversazione(conversazione);
        }
        else {
            Conversazione conversazione = conversazioneService.trovaConversazioneDueAccademici(accademicoDest, accademicoSelf);
            Messaggio messaggio1 = new Messaggio();
            messaggio1.setAutore(accademicoSelf);
            messaggio1.setDestinatario(accademicoDest);
            messaggio1.setBody(messaggio);
            messaggio1.setDateTime(LocalDateTime.now());
            if(topic != null) {
                messaggio1.setTopic(top);
            }
            conversazione.getMessaggi().add(messaggio1);
            conversazioneService.aggiungiConversazione(conversazione);
        }

        response.sendRedirect("Conversazioni.jsp");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
