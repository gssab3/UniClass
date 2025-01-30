package it.unisa.uniclass.conversazioni.controller;

import it.unisa.uniclass.conversazioni.model.Messaggio;
import it.unisa.uniclass.conversazioni.service.MessaggioService;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "chatServlet", value = "/chatServlet")
public class chatServlet extends HttpServlet {

    @EJB
    private MessaggioService messaggioService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //da rivedere
        HttpSession session = req.getSession();


        String email =  req.getParameter("accademico");
        System.out.println("CHAT SERVLET email"+email);
        String emailSelf =  req.getParameter("accademicoSelf");
        System.out.println("CHAT SERVLET mia email"+email);

        AccademicoService accademicoService = new AccademicoService();
        Accademico accademico = accademicoService.trovaEmailUniClass(email);
        System.out.println("accademico altro: "+ accademico);
        Accademico accademicoSelf = accademicoService.trovaEmailUniClass(emailSelf);
        System.out.println("accademico self : "+ accademicoSelf);

        List<Messaggio> messaggigi = messaggioService.trovaTutti();
        System.out.println("tutti i messaggi: " + messaggigi);

        //List<Messaggio> messaggi = messaggioService.trovaMessaggi(email, emailSelf);
        //System.out.println("soli messaggi: " + messaggi);

        List<Messaggio> messaggiInviati = new ArrayList<>();
        List<Messaggio> messaggiRicevuti = new ArrayList<>();
        for(Messaggio messaggio : messaggigi) {
            if(messaggio.getDestinatario().getMatricola().equals(accademicoSelf.getMatricola())) {
                messaggiRicevuti.add(messaggio);
            }
            if(messaggio.getAutore().getMatricola().equals(accademicoSelf.getMatricola())) {
                messaggiInviati.add(messaggio);
            }
        }
        System.out.println("messaggi inviati: " + messaggiInviati);
        System.out.println("messaggi ricevuti: " + messaggiRicevuti);


        req.setAttribute("messaggigi", messaggigi);
        session.setAttribute("messaggigi", messaggigi);
        //req.setAttribute("messaggi", messaggi);
        req.setAttribute("messaggiInviati", messaggiInviati);
        req.setAttribute("messaggiRicevuti", messaggiRicevuti);
        req.setAttribute("accademico", accademico);
        session.setAttribute("accademico",accademico);
        session.setAttribute("accademicoSelf", accademicoSelf);
        req.setAttribute("accdemicoSelf", accademicoSelf);

        resp.sendRedirect(req.getContextPath() + "/chat.jsp");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
