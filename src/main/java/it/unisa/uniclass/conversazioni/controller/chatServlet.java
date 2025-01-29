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

        String email = req.getParameter("accademico");
        String emailSelf = req.getParameter("accademicoSelf");

        AccademicoService accademicoService = new AccademicoService();
        Accademico accademico = accademicoService.trovaEmailUniClass(email);
        Accademico accademicoSelf = accademicoService.trovaEmailUniClass(emailSelf);

        List<Messaggio> messaggi = messaggioService.trovaMessaggi(email, emailSelf);
        List<Messaggio> messaggiInviati = new ArrayList<>();
        List<Messaggio> messaggiRicevuti = new ArrayList<>();
        for(Messaggio messaggio : messaggi) {
            if(messaggio.getDestinatario().getMatricola().equals(accademicoSelf.getMatricola())) {
                messaggiRicevuti.add(messaggio);
            }
            if(messaggio.getAutore().getMatricola().equals(accademicoSelf.getMatricola())) {
                messaggiInviati.add(messaggio);
            }
        }

        req.setAttribute("messaggi", messaggi);
        req.setAttribute("messaggiInviati", messaggiInviati);
        req.setAttribute("messaggiRicevuti", messaggiRicevuti);
        req.setAttribute("accademico", accademico);
        req.setAttribute("accdemicoSelf", accademicoSelf);

        req.getRequestDispatcher("chat.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
