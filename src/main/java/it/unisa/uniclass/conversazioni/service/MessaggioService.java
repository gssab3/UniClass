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

/**
 * Classe di servizio per la gestione delle operazioni relative ai messaggi.
 * Fornisce metodi per recuperare, aggiungere e rimuovere messaggi.
 */
@Stateless
public class MessaggioService {

    private MessaggioRemote messaggioDao;

    /**
     * Costruttore di default che esegue il lookup JNDI del DAO.
     */
    public MessaggioService() {
        try {
            InitialContext ctx = new InitialContext();
            messaggioDao = (MessaggioRemote) ctx.lookup("java:global/UniClass/MessaggioDAO");
        } catch (NamingException e) {
            throw new RuntimeException("Impossibile trovare il messaggioDAO", e);
        }
    }

    /**
     * Trova un messaggio nel database utilizzando il suo ID.
     *
     * @param id L'ID del messaggio da cercare.
     * @return L'oggetto Messaggio corrispondente all'ID.
     */
    public Messaggio trovaMessaggio(long id) {
        try {
            return messaggioDao.trovaMessaggio(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trova i messaggi inviati da un utente tramite la sua matricola.
     *
     * @param matricola La matricola dell'utente di cui trovare i messaggi inviati.
     * @return Una lista di oggetti Messaggio inviati dall'utente.
     */
    public List<Messaggio> trovaMessaggiInviati(String matricola) {
        return messaggioDao.trovaMessaggiInviati(matricola);
    }

    /**
     * Trova i messaggi ricevuti da un utente tramite la sua matricola.
     *
     * @param matricola La matricola dell'utente di cui trovare i messaggi ricevuti.
     * @return Una lista di oggetti Messaggio ricevuti dall'utente.
     */
    public List<Messaggio> trovaMessaggiRicevuti(String matricola) {
        return messaggioDao.trovaMessaggiRicevuti(matricola);
    }

    /**
     * Trova i messaggi scambiati tra due utenti tramite le loro matricole.
     *
     * @param matricola1 La matricola del primo utente.
     * @param matricola2 La matricola del secondo utente.
     * @return Una lista di oggetti Messaggio scambiati tra i due utenti.
     */
    public List<Messaggio> trovaMessaggi(String matricola1, String matricola2) {
        return messaggioDao.trovaMessaggi(matricola1, matricola2);
    }

    /**
     * Recupera tutti i messaggi presenti nel database.
     *
     * @return Una lista di tutti i messaggi.
     */
    public List<Messaggio> trovaTutti() {
        return messaggioDao.trovaTutti();
    }

    /**
     * Trova tutti gli avvisi presenti nel database.
     *
     * @return Una lista di tutti gli avvisi.
     */
    public List<Messaggio> trovaAvvisi() {
        return messaggioDao.trovaAvvisi();
    }

    /**
     * Trova gli avvisi nel database associati a un autore specifico.
     *
     * @param autore L'autore degli avvisi da cercare.
     * @return Una lista di oggetti Messaggio corrispondenti agli avvisi dell'autore.
     */
    public List<Messaggio> trovaAvvisiAutore(String autore) {
        return messaggioDao.trovaAvvisiAutore(autore);
    }

    /**
     * Trova i messaggi nel database inviati in una data specifica.
     *
     * @param dateTime La data e ora di invio dei messaggi da cercare.
     * @return Una lista di oggetti Messaggio inviati nella data specificata.
     */
    public List<Messaggio> trovaMessaggiData(LocalDateTime dateTime) {
        return messaggioDao.trovaMessaggiData(dateTime);
    }

    /**
     * Trova gli accademici che hanno inviato messaggi a un determinato accademico.
     *
     * @param matricola La matricola dell'accademico di cui trovare i messaggeri.
     * @return Una lista di oggetti Accademico che hanno inviato messaggi all'accademico specificato.
     */
    public List<Accademico> trovaMessaggeriDiUnAccademico(String matricola) {
        List<Messaggio> messaggi = messaggioDao.trovaMessaggiRicevuti(matricola);
        List<Accademico> accademici = new ArrayList<>();
        for (Messaggio messaggio : messaggi) {
            accademici.add(messaggio.getDestinatario());
        }
        return accademici;
    }

    /**
     * Trova i messaggi nel database associati a un topic specifico.
     *
     * @param topic Il topic di cui trovare i messaggi.
     * @return Una lista di oggetti Messaggio associati al topic.
     */
    public List<Messaggio> trovaTopic(Topic topic) {
        return messaggioDao.trovaTopic(topic);
    }

    /**
     * Aggiunge o aggiorna un messaggio nel database.
     *
     * @param messaggio Il messaggio da aggiungere o aggiornare.
     * @return L'oggetto Messaggio aggiunto o aggiornato.
     */
    public Messaggio aggiungiMessaggio(Messaggio messaggio) {
        if (messaggio != null) {
            messaggio = messaggioDao.aggiungiMessaggio(messaggio);
        }
        return messaggio;
    }

    /**
     * Rimuove un messaggio dal database.
     *
     * @param messaggio Il messaggio da rimuovere.
     */
    public void rimuoviMessaggio(Messaggio messaggio) {
        if (messaggio != null) {
            messaggioDao.rimuoviMessaggio(messaggio);
        }
    }
}