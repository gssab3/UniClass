package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.Agenda;
import it.unisa.uniclass.orari.service.dao.AgendaRemote;
import it.unisa.uniclass.utenti.model.Studente;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * Classe di servizio per la gestione delle operazioni relative alle agende.
 * Fornisce metodi per recuperare, aggiungere e rimuovere agende.
 */
@Stateless
public class AgendaService {

    private AgendaRemote agendaRemote;

    /**
     * Costruttore di default che esegue il lookup JNDI del DAO.
     */
    public AgendaService() {
        try {
            InitialContext ctx = new InitialContext();
            this.agendaRemote = (AgendaRemote) ctx.lookup("java:global/UniClass/AgendaDAO");
        } catch (NamingException e) {
            throw new RuntimeException("Errore durante il lookup di AgendaDAO", e);
        }
    }

    /**
     * Trova un'agenda nel database utilizzando il suo ID.
     *
     * @param id L'ID dell'agenda da cercare.
     * @return L'oggetto Agenda corrispondente all'ID.
     */
    public Agenda trovaAgenda(long id) {
        try {
            return agendaRemote.trovaAgenda(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trova un'agenda nel database associata a uno studente.
     *
     * @param studente Lo studente di cui trovare l'agenda.
     * @return L'oggetto Agenda associato allo studente.
     */
    public Agenda trovaAgenda(Studente studente) {
        try {
            return agendaRemote.trovaAgenda(studente);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Recupera tutte le agende presenti nel database.
     *
     * @return Una lista di tutte le agende.
     */
    public List<Agenda> trovaTutte() {
        return agendaRemote.trovaTutte();
    }

    /**
     * Aggiunge o aggiorna un'agenda nel database.
     *
     * @param agenda L'agenda da aggiungere o aggiornare.
     */
    public void aggiungiAgenda(Agenda agenda) {
        if (agenda != null) {
            agendaRemote.aggiungiAgenda(agenda);
        } else {
            throw new RuntimeException("Impossibile creare l'agenda.");
        }
    }

    /**
     * Rimuove un'agenda dal database.
     *
     * @param agenda L'agenda da rimuovere.
     */
    public void rimuoviAgenda(Agenda agenda) {
        if (agenda != null) {
            agendaRemote.rimuoviAgenda(agenda);
        } else {
            throw new RuntimeException("Impossibile eliminare l'agenda. Agenda = null");
        }
    }
}