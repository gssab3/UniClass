package it.unisa.uniclass.conversazioni.controller;

import it.unisa.uniclass.conversazioni.model.Messaggio;
import it.unisa.uniclass.conversazioni.model.Topic;
import it.unisa.uniclass.conversazioni.service.MessaggioService;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.service.AccademicoService;
import jakarta.ejb.EJB;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "inviaMessaggioChat", value = "/inviaMessaggioChatServlet")
public class inviaMessaggioChatServlet extends HttpServlet {

    @EJB
    private MessaggioService messaggioService;

    @EJB
    private AccademicoService accademicoService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Email attuale (autore del messaggio)
        String emailSession = (String) session.getAttribute("utenteEmail");

        // Email di destinazione
        String emailDest = request.getParameter("emailInvio");
        System.out.println("lo sto inviando a :" + emailDest);

        // Messaggio da inviare
        String messaggio = request.getParameter("testo");

        System.out.println("guarda che mssaggio:" + messaggio);


        Accademico accademicoSelf = accademicoService.trovaEmailUniClass(emailSession);
        Accademico accademicoDest = accademicoService.trovaEmailUniClass(emailDest);


        Topic top = new Topic();
        top.setCorsoLaurea(accademicoSelf.getCorsoLaurea());
        top.setNome("VUOTO");

        // Crea un nuovo messaggio
        Messaggio messaggio1 = new Messaggio();
        messaggio1.setAutore(accademicoSelf);
        messaggio1.setDestinatario(accademicoDest);
        messaggio1.setBody(messaggio);
        messaggio1.setDateTime(LocalDateTime.now());
        messaggio1.setTopic(top);


        // Salva il messaggio
        Messaggio test = messaggioService.aggiungiMessaggio(messaggio1);

        // Recupera il messaggio appena creato per inviarlo come risposta
        String messaggioRisposta = test.getBody(); // Usa il corpo del messaggio appena creato
        String autoreRisposta = test.getAutore().getNome(); // Usa il nome dell'autore del messaggio
        String dataRisposta = test.getDateTime().toString(); // Usa la data del messaggio


        List<Messaggio> messaggi = messaggioService.trovaTutti();

        request.setAttribute("messaggi", messaggi);
        request.setAttribute("accademici", messaggioService.trovaMessaggeriDiUnAccademico(accademicoSelf.getMatricola()));
        response.sendRedirect("chatServlet?accademico="+accademicoDest.getEmail()+"&accademicoSelf="+accademicoSelf.getEmail());

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

