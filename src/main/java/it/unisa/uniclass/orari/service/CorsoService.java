package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.Corso;
import it.unisa.uniclass.orari.service.dao.CorsoRemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * Classe di servizio per la gestione delle operazioni relative ai corsi.
 * Fornisce metodi per recuperare, aggiungere e rimuovere corsi.
 */
@Stateless
public class CorsoService {

    private CorsoRemote corsoDao;

    /**
     * Costruttore di default che esegue il lookup JNDI del DAO.
     *
     * @throws NamingException Se si verifica un errore durante il lookup JNDI.
     */
    public CorsoService() throws NamingException {
        try {
            InitialContext ctx = new InitialContext();
            this.corsoDao = (CorsoRemote) ctx.lookup("java:global/UniClass/CorsoDAO");
        } catch (NamingException e) {
            throw new RuntimeException("Errore durante il lookup di CorsoDAO", e);
        }
    }

    /**
     * Trova un corso nel database utilizzando il suo ID.
     *
     * @param id L'ID del corso da cercare.
     * @return L'oggetto Corso corrispondente all'ID.
     */
    public Corso trovaCorso(long id) {
        try {
            return corsoDao.trovaCorso(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trova i corsi nel database associati a un corso di laurea tramite il nome del corso.
     *
     * @param nomeCorsoLaurea Il nome del corso di laurea di cui trovare i corsi.
     * @return Una lista di oggetti Corso associati al corso di laurea.
     */
    public List<Corso> trovaCorsiCorsoLaurea(String nomeCorsoLaurea) {
        return corsoDao.trovaCorsiCorsoLaurea(nomeCorsoLaurea);
    }

    /**
     * Recupera tutti i corsi presenti nel database.
     *
     * @return Una lista di tutti i corsi.
     */
    public List<Corso> trovaTutti() {
        return corsoDao.trovaTutti();
    }

    /**
     * Aggiunge o aggiorna un corso nel database.
     *
     * @param corso Il corso da aggiungere o aggiornare.
     */
    public void aggiungiCorso(Corso corso) {
        corsoDao.aggiungiCorso(corso);
    }

    /**
     * Rimuove un corso dal database.
     *
     * @param corso Il corso da rimuovere.
     */
    public void rimuoviCorso(Corso corso) {
        corsoDao.rimuoviCorso(corso);
    }
}