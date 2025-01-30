package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.exceptions.AlreadyExistentUserException;
import it.unisa.uniclass.common.exceptions.IncorrectUserSpecification;
import it.unisa.uniclass.common.exceptions.NotFoundUserException;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Docente;
import it.unisa.uniclass.utenti.service.dao.DocenteRemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * Classe di servizio per la gestione delle operazioni relative ai docenti.
 * Fornisce metodi per recuperare, aggiungere e rimuovere docenti.
 */
@Stateless
public class DocenteService {

    private DocenteRemote docenteDao;

    /**
     * Costruttore di default che esegue il lookup JNDI del DAO.
     */
    public DocenteService() {
        try {
            InitialContext ctx = new InitialContext();
            docenteDao = (DocenteRemote) ctx.lookup("java:global/UniClass/DocenteDAO");
        } catch (NamingException e) {
            throw new RuntimeException("Errore durante il lookup di DocenteDAO", e);
        }
    }

    /**
     * Trova un docente nel database utilizzando la sua matricola.
     *
     * @param matricola La matricola del docente da cercare.
     * @return L'oggetto Docente corrispondente alla matricola.
     */
    public Docente trovaDocenteUniClass(String matricola) {
        try {
            return docenteDao.trovaDocenteUniClass(matricola);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trova i docenti nel database associati a un corso di laurea tramite il nome del corso.
     *
     * @param nomeCorsoLaurea Il nome del corso di laurea di cui trovare i docenti.
     * @return Una lista di oggetti Docente associati al corso di laurea.
     */
    public List<Docente> trovaDocenteCorsoLaurea(String nomeCorsoLaurea) {
        return docenteDao.trovaDocenteCorsoLaurea(nomeCorsoLaurea);
    }

    /**
     * Recupera tutti i docenti presenti nel database.
     *
     * @return Una lista di tutti i docenti.
     */
    public List<Docente> trovaTuttiUniClass() {
        return docenteDao.trovaTuttiUniClass();
    }

    /**
     * Trova un docente nel database utilizzando la sua email.
     *
     * @param email L'email del docente da cercare.
     * @return L'oggetto Docente corrispondente all'email.
     */
    public Docente trovaEmailUniClass(String email) {
        try {
            return docenteDao.trovaEmailUniClass(email);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Aggiunge un docente nel database.
     *
     * @param docente Il docente da aggiungere.
     * @throws IncorrectUserSpecification Se le specifiche dell'utente sono errate.
     * @throws AlreadyExistentUserException Se l'utente è già presente nel database.
     * @throws NotFoundUserException Se l'utente non è stato trovato.
     */
    public void aggiungiDocente(Docente docente) throws IncorrectUserSpecification, NotFoundUserException, AlreadyExistentUserException {
        Docente trovaEmailUniclass = docenteDao.trovaEmailUniClass(docente.getEmail());
        Docente trovaDocenteUniClass = docenteDao.trovaDocenteUniClass(docente.getMatricola());
        if ((trovaEmailUniclass == null) && (trovaDocenteUniClass == null)) {
            docenteDao.aggiungiDocente(docente);
        } else if (trovaEmailUniclass != trovaDocenteUniClass) {
            throw new IncorrectUserSpecification("Il docente desiderato ha campi di due docenti diversi all'interno del database.");
        }
    }

    /**
     * Rimuove un docente dal database.
     *
     * @param docente Il docente da rimuovere.
     * @throws NotFoundUserException Se l'utente non è stato trovato.
     */
    public void rimuoviDocente(Docente docente) throws NotFoundUserException {
        if (trovaDocenteUniClass(docente.getMatricola()) != null) {
            AccademicoService accademicoService = new AccademicoService();
            Accademico accademico = accademicoService.trovaAccademicoUniClass(docente.getMatricola());
            docenteDao.rimuoviDocente(docente);
            accademicoService.rimuoviAccademico(accademico);
        } else {
            throw new NotFoundUserException("Il docente inserito non è presente nel Database universitario");
        }
    }
}