package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.AnnoDidattico;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import it.unisa.uniclass.orari.service.dao.CorsoLaureaRemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe di servizio per la gestione delle operazioni relative ai corsi di laurea.
 * Fornisce metodi per recuperare, aggiungere e rimuovere corsi di laurea.
 */
@Stateless
public class CorsoLaureaService {

    private CorsoLaureaRemote corsoLaureaDAO;

    /**
     * Costruttore di default che esegue il lookup JNDI del DAO.
     */
    public CorsoLaureaService() {
        try {
            InitialContext ctx = new InitialContext();
            this.corsoLaureaDAO = (CorsoLaureaRemote) ctx.lookup("java:global/UniClass/CorsoLaureaDAO");
        } catch (NamingException e) {
            throw new RuntimeException("Errore durante il lookup di CorsoLaureaDAO.", e);
        }
    }

    /**
     * Trova un corso di laurea nel database utilizzando il suo ID.
     *
     * @param id L'ID del corso di laurea da cercare.
     * @return L'oggetto CorsoLaurea corrispondente all'ID.
     */
    public CorsoLaurea trovaCorsoLaurea(long id) {
        try {
            return corsoLaureaDAO.trovaCorsoLaurea(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trova un corso di laurea nel database utilizzando il suo nome.
     *
     * @param nome Il nome del corso di laurea da cercare.
     * @return L'oggetto CorsoLaurea corrispondente al nome.
     */
    public CorsoLaurea trovaCorsoLaurea(String nome) {
        try {
            return corsoLaureaDAO.trovaCorsoLaurea(nome);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Recupera tutti i corsi di laurea presenti nel database.
     *
     * @return Una lista di tutti i corsi di laurea.
     */
    public List<CorsoLaurea> trovaTutti() {
        try {
            return corsoLaureaDAO.trovaTutti();
        } catch (Exception e) {
            throw new RuntimeException("Errore durante il recupero dei corsi di laurea.", e);
        }
    }

    /**
     * Aggiunge o aggiorna un corso di laurea nel database.
     *
     * @param corsoLaurea Il corso di laurea da aggiungere o aggiornare.
     * @throws IllegalArgumentException Se il corso di laurea è null o non ha un nome valido.
     */
    public void aggiungiCorsoLaurea(CorsoLaurea corsoLaurea) {
        if (corsoLaurea == null || corsoLaurea.getNome() == null || corsoLaurea.getNome().isEmpty()) {
            throw new IllegalArgumentException("Il corso di laurea deve avere un nome valido.");
        }
        corsoLaureaDAO.aggiungiCorsoLaurea(corsoLaurea);
    }

    /**
     * Rimuove un corso di laurea dal database.
     *
     * @param corsoLaurea Il corso di laurea da rimuovere.
     * @throws IllegalArgumentException Se il corso di laurea è null.
     */
    public void rimuoviCorsoLaurea(CorsoLaurea corsoLaurea) {
        if (corsoLaurea == null) {
            throw new IllegalArgumentException("Il corso di laurea da rimuovere non può essere null.");
        }
        corsoLaureaDAO.rimuoviCorsoLaurea(corsoLaurea);
    }
}