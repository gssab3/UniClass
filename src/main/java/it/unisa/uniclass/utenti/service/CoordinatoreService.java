package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.exceptions.AlreadyExistentUserException;
import it.unisa.uniclass.common.exceptions.IncorrectUserSpecification;
import it.unisa.uniclass.common.exceptions.NotFoundUserException;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Coordinatore;
import it.unisa.uniclass.utenti.service.dao.CoordinatoreRemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * Classe di servizio per la gestione delle operazioni relative ai coordinatori.
 * Fornisce metodi per recuperare, aggiungere e rimuovere coordinatori.
 */
@Stateless
public class CoordinatoreService {

    private CoordinatoreRemote coordinatoreDao;

    /**
     * Costruttore di default che esegue il lookup JNDI del DAO.
     */
    public CoordinatoreService() {
        try {
            InitialContext ctx = new InitialContext();
            coordinatoreDao = (CoordinatoreRemote) ctx.lookup("java:global/UniClass/CoordinatoreDAO");
        } catch (NamingException e) {
            throw new RuntimeException("Errore durante il lookup di CoordinatoreDAO", e);
        }
    }

    /**
     * Trova un coordinatore nel database utilizzando la sua matricola.
     *
     * @param matricola La matricola del coordinatore da cercare.
     * @return L'oggetto Coordinatore corrispondente alla matricola.
     */
    public Coordinatore trovaCoordinatoreUniClass(String matricola) {
        try {
            return coordinatoreDao.trovaCoordinatoreUniClass(matricola);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trova i coordinatori nel database associati a un corso di laurea tramite il nome del corso.
     *
     * @param nomeCorsoLaurea Il nome del corso di laurea di cui trovare i coordinatori.
     * @return Una lista di oggetti Coordinatore associati al corso di laurea.
     */
    public List<Coordinatore> trovaCoordinatoriCorsoLaurea(String nomeCorsoLaurea) {
        return coordinatoreDao.trovaCoordinatoriCorsoLaurea(nomeCorsoLaurea);
    }

    /**
     * Trova un coordinatore nel database utilizzando la sua email.
     *
     * @param email L'email del coordinatore da cercare.
     * @return L'oggetto Coordinatore corrispondente all'email.
     */
    public Coordinatore trovaCoordinatoreEmailUniclass(String email) {
        try {
            return coordinatoreDao.trovaCoordinatoreEmailUniclass(email);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Recupera tutti i coordinatori presenti nel database.
     *
     * @return Una lista di tutti i coordinatori.
     */
    public List<Coordinatore> trovaTutti() {
        return coordinatoreDao.trovaTutti();
    }

    /**
     * Aggiunge un coordinatore nel database.
     *
     * @param coordinatore Il coordinatore da aggiungere.
     * @throws IncorrectUserSpecification Se le specifiche dell'utente sono errate.
     * @throws AlreadyExistentUserException Se l'utente è già presente nel database.
     * @throws NotFoundUserException Se l'utente non è stato trovato.
     */
    public void aggiungiCoordinatore(Coordinatore coordinatore) throws IncorrectUserSpecification, AlreadyExistentUserException, NotFoundUserException {
        Coordinatore trovaCoordinatoreEmailUniClass = coordinatoreDao.trovaCoordinatoreEmailUniclass(coordinatore.getEmail());
        Coordinatore trovaCoordinatoreUniClass = coordinatoreDao.trovaCoordinatoreUniClass(coordinatore.getMatricola());

        if ((trovaCoordinatoreUniClass == trovaCoordinatoreEmailUniClass) &&
                (trovaCoordinatoreUniClass == null)) {
            coordinatoreDao.aggiungiCoordinatore(coordinatore);
        } else if (trovaCoordinatoreEmailUniClass != null) {
            throw new AlreadyExistentUserException("Utente da aggiungere già presente");
        }
    }

    /**
     * Rimuove un coordinatore dal database.
     *
     * @param coordinatore Il coordinatore da rimuovere.
     * @throws NotFoundUserException Se l'utente non è stato trovato.
     */
    public void rimuoviCoordinatore(Coordinatore coordinatore) throws NotFoundUserException {
        if (trovaCoordinatoreEmailUniclass(coordinatore.getEmail()) != null) {
            AccademicoService accademicoService = new AccademicoService();
            Accademico accademico = accademicoService.trovaAccademicoUniClass(coordinatore.getMatricola());
            coordinatoreDao.rimuoviCoordinatore(coordinatore);
            accademicoService.rimuoviAccademico(accademico);
        } else {
            throw new NotFoundUserException("Utente da rimuovere non trovato.");
        }
    }
}