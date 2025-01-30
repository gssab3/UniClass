package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.PersonaleTA;
import it.unisa.uniclass.utenti.service.dao.PersonaleTARemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * Classe di servizio per la gestione delle operazioni relative al personale tecnico-amministrativo.
 * Fornisce metodi per recuperare, aggiungere e rimuovere personale tecnico-amministrativo.
 */
@Stateless
public class PersonaleTAService {

    private PersonaleTARemote personaleTAdao;

    /**
     * Costruttore di default che esegue il lookup JNDI del DAO.
     */
    public PersonaleTAService() {
        try {
            InitialContext ctx = new InitialContext();
            personaleTAdao = (PersonaleTARemote) ctx.lookup("java:global/UniClass/PersonaleTADAO");
        } catch (NamingException e) {
            throw new RuntimeException("Errore durante il lookup di PersonaleTADAO", e);
        }
    }

    /**
     * Trova un membro del personale tecnico-amministrativo nel database utilizzando il suo ID.
     *
     * @param id L'ID del personale da cercare.
     * @return L'oggetto PersonaleTA corrispondente all'ID.
     */
    public PersonaleTA trovaPersonale(long id) {
        try {
            return personaleTAdao.trovaPersonale(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Recupera tutti i membri del personale tecnico-amministrativo presenti nel database.
     *
     * @return Una lista di tutti i membri del personale tecnico-amministrativo.
     */
    public List<PersonaleTA> trovaTutti() {
        return personaleTAdao.trovaTutti();
    }

    /**
     * Trova un membro del personale tecnico-amministrativo nel database utilizzando la sua email.
     *
     * @param email L'email del personale da cercare.
     * @return L'oggetto PersonaleTA corrispondente all'email.
     */
    public PersonaleTA trovaEmail(String email) {
        try {
            return personaleTAdao.trovaEmail(email);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trova un membro del personale tecnico-amministrativo nel database utilizzando la sua email e password.
     *
     * @param email L'email del personale da cercare.
     * @param pass La password del personale da cercare.
     * @return L'oggetto PersonaleTA corrispondente all'email e alla password.
     */
    public PersonaleTA trovaEmailPass(String email, String pass) {
        try {
            return personaleTAdao.trovaEmailPassword(email, pass);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Aggiunge o aggiorna un membro del personale tecnico-amministrativo nel database.
     *
     * @param personaleTA Il personale da aggiungere o aggiornare.
     */
    public void aggiungiPersonaleTA(PersonaleTA personaleTA) {
        personaleTAdao.aggiungiPersonale(personaleTA);
    }

    /**
     * Rimuove un membro del personale tecnico-amministrativo dal database.
     *
     * @param personaleTA Il personale da rimuovere.
     */
    public void rimuoviPersonaleTA(PersonaleTA personaleTA) {
        personaleTAdao.rimuoviPersonale(personaleTA);
    }
}