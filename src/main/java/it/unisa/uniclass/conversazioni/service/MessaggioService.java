package it.unisa.uniclass.conversazioni.service;

import it.unisa.uniclass.conversazioni.model.Messaggio;
import it.unisa.uniclass.conversazioni.model.Topic;
import it.unisa.uniclass.conversazioni.service.dao.MessaggioRemote;
import it.unisa.uniclass.utenti.model.Accademico;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import java.util.List;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Stateless
public class MessaggioService {

    private MessaggioRemote messaggioDao;

    public MessaggioService() {
        try {
            InitialContext ctx = new InitialContext();
            messaggioDao = (MessaggioRemote) ctx.lookup("java:global/UniClass/MessaggioDAO");
        }
        catch (NamingException e) {
            throw new RuntimeException("Impossibile trovare il messaggioDAO", e);
        }
    }

    public Messaggio trovaMessaggio(long id) {
        try {
            return messaggioDao.trovaMessaggio(id);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<Messaggio> trovaMessaggiInviati(String matricola) {
        return messaggioDao.trovaMessaggiInviati(matricola);
    }

    public List<Messaggio> trovaMessaggiRicevuti(String matricola) {
        return messaggioDao.trovaMessaggiRicevuti(matricola);
    }

    public List<Messaggio> trovaMessaggi(String matricola1, String matricola2) {
        return messaggioDao.trovaMessaggi(matricola1, matricola2);
    }

    public List<Messaggio> trovaTutti() {
        return messaggioDao.trovaTutti();
    }

    public List<Messaggio> trovaAvvisi() {
        return messaggioDao.trovaAvvisi();
    }

    public List<Messaggio> trovaAvvisiAutore(String autore) {
        return messaggioDao.trovaAvvisiAutore(autore);
    }

    public List<Messaggio> trovaMessaggiData(LocalDateTime dateTime) {
        return messaggioDao.trovaMessaggiData(dateTime);
    }

    public List<Accademico> trovaMessaggeriDiUnAccademico(String matricola) {
        List<Messaggio> messaggi = messaggioDao.trovaMessaggiRicevuti(matricola);
        List<Accademico> accademici = new ArrayList<>();
        for(Messaggio messaggio : messaggi) {
            accademici.add(messaggio.getDestinatario());
        }
        return accademici;
    }

    public List<Messaggio> trovaTopic(Topic topic) {
        return messaggioDao.trovaTopic(topic);
    }

    public Messaggio aggiungiMessaggio(Messaggio messaggio) {
        if (messaggio != null) {
            messaggio = messaggioDao.aggiungiMessaggio(messaggio);
        }
        return messaggio;
    }
    public void rimuoviMessaggio(Messaggio messaggio) {
        if (messaggio != null) {
            messaggioDao.rimuoviMessaggio(messaggio);
        }
    }
}
