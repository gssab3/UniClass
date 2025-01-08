package it.unisa.uniclass.common;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.service.CorsoLaureaService;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Utente;
import it.unisa.uniclass.utenti.service.UtenteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class ConversazioniServlet extends HttpServlet {


    ConversazioneService conversazioneService = new ConversazioniService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        conversazioneService = new ConversazioniService();

        String email = request.getParameter("utenteEmail");
        Utente user = UtenteService.retrieveEmail(email);

        Set<Accademico> messaggeri = conversazioneService.();
        List<CorsoLaurea> corsi = corsoLaureaService.trovaTutti();
        System.out.println(corsi);
        request.setAttribute("corsi", corsi);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}

}
