package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.*;
import it.unisa.uniclass.orari.service.dao.CorsoLaureaRemote;
import it.unisa.uniclass.orari.service.dao.LezioneDAO;
import it.unisa.uniclass.orari.service.dao.LezioneRemote;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe di servizio per la gestione delle operazioni relative alle lezioni.
 * Fornisce metodi per recuperare, aggiungere e rimuovere lezioni.
 */
@Stateless
public class LezioneService {

    private LezioneRemote lezioneDao;

    /**
     * Costruttore di default che esegue il lookup JNDI del DAO.
     */
    public LezioneService() {
        try {
            InitialContext ctx = new InitialContext();
            this.lezioneDao = (LezioneRemote) ctx.lookup("java:global/UniClass/LezioneDAO");
        } catch (NamingException e) {
            throw new RuntimeException("Errore durante il lookup di LezioneDAO.", e);
        }
    }

    /**
     * Trova una lezione nel database utilizzando il suo ID.
     *
     * @param id L'ID della lezione da cercare.
     * @return L'oggetto Lezione corrispondente all'ID.
     */
    public Lezione trovaLezione(long id) {
        try {
            return lezioneDao.trovaLezione(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trova le lezioni nel database associate a un corso tramite il nome del corso.
     *
     * @param nomeCorso Il nome del corso di cui trovare le lezioni.
     * @return Una lista di oggetti Lezione associati al corso.
     */
    public List<Lezione> trovaLezioniCorso(String nomeCorso) {
        return lezioneDao.trovaLezioniCorso(nomeCorso);
    }

    /**
     * Trova le lezioni nel database che si svolgono in un intervallo di tempo specifico.
     *
     * @param oraInizio L'ora di inizio delle lezioni da cercare.
     * @param oraFine L'ora di fine delle lezioni da cercare.
     * @return Una lista di oggetti Lezione che si svolgono nell'intervallo di tempo specificato.
     */
    public List<Lezione> trovaLezioniOre(Time oraInizio, Time oraFine) {
        return lezioneDao.trovaLezioniOre(oraInizio, oraFine);
    }

    /**
     * Trova le lezioni nel database che si svolgono in un intervallo di tempo specifico in un determinato giorno.
     *
     * @param oraInizio L'ora di inizio delle lezioni da cercare.
     * @param oraFine L'ora di fine delle lezioni da cercare.
     * @param giorno Il giorno in cui si svolgono le lezioni da cercare.
     * @return Una lista di oggetti Lezione che si svolgono nell'intervallo di tempo specificato nel giorno specificato.
     */
    public List<Lezione> trovaLezioniOreGiorno(Time oraInizio, Time oraFine, Giorno giorno) {
        return lezioneDao.trovaLezioniOreGiorno(oraInizio, oraFine, giorno);
    }

    /**
     * Trova le lezioni nel database associate a un'aula tramite il nome dell'aula.
     *
     * @param nome Il nome dell'aula di cui trovare le lezioni.
     * @return Una lista di oggetti Lezione associati all'aula.
     */
    public List<Lezione> trovaLezioniAule(String nome) {
        return lezioneDao.trovaLezioniAule(nome);
    }

    /**
     * Recupera tutte le lezioni presenti nel database.
     *
     * @return Una lista di tutte le lezioni.
     */
    public List<Lezione> trovaTutte() {
        return lezioneDao.trovaTutte();
    }

    /**
     * Trova le lezioni nel database associate a un corso di laurea, un resto e un anno specifici.
     *
     * @param clid L'ID del corso di laurea.
     * @param rid L'ID del resto.
     * @param annoid L'ID dell'anno.
     * @return Una lista di oggetti Lezione associati al corso di laurea, resto e anno specificati.
     */
    public List<Lezione> trovaLezioniCorsoLaureaRestoAnno(long clid, long rid, int annoid) {
        return lezioneDao.trovaLezioniCorsoLaureaRestoAnno(clid, rid, annoid);
    }

    /**
     * Trova le lezioni nel database associate a un corso di laurea, un resto, un anno e un semestre specifici.
     *
     * @param clid L'ID del corso di laurea.
     * @param rid L'ID del resto.
     * @param annoid L'ID dell'anno.
     * @param semestre Il semestre di cui trovare le lezioni.
     * @return Una lista di oggetti Lezione associati al corso di laurea, resto, anno e semestre specificati.
     */
    public List<Lezione> trovaLezioniCorsoLaureaRestoAnnoSemestre(long clid, long rid, int annoid, int semestre) {
        return lezioneDao.trovaLezioniCorsoLaureaRestoAnnoSemestre(clid, rid, annoid, semestre);
    }

    /**
     * Trova le lezioni nel database associate a un docente tramite il nome del docente.
     *
     * @param nomeDocente Il nome del docente di cui trovare le lezioni.
     * @return Una lista di oggetti Lezione associati al docente.
     */
    public List<Lezione> trovaLezioniDocente(String nomeDocente) {
        return lezioneDao.trovaLezioniDocente(nomeDocente);
    }

    /**
     * Aggiunge o aggiorna una lezione nel database.
     *
     * @param lezione La lezione da aggiungere o aggiornare.
     */
    public void aggiungiLezione(Lezione lezione) {
        lezioneDao.aggiungiLezione(lezione);
    }

    /**
     * Rimuove una lezione dal database.
     *
     * @param lezione La lezione da rimuovere.
     */
    public void rimuoviLezione(Lezione lezione) {
        lezioneDao.rimuoviLezione(lezione);
    }
}