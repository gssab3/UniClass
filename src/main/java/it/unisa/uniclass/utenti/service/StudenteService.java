package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.exceptions.AlreadyExistentUserException;
import it.unisa.uniclass.common.exceptions.IncorrectUserSpecification;
import it.unisa.uniclass.common.exceptions.NotFoundUserException;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Studente;
import it.unisa.uniclass.utenti.service.dao.StudenteRemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * Classe di servizio per la gestione delle operazioni relative agli studenti.
 * Fornisce metodi per recuperare, aggiungere e rimuovere studenti.
 */
@Stateless
public class StudenteService {

    private StudenteRemote studenteDao;

    /**
     * Costruttore di default che esegue il lookup JNDI del DAO.
     */
    public StudenteService() {
        try {
            InitialContext ctx = new InitialContext();
            studenteDao = (StudenteRemote) ctx.lookup("java:global/UniClass/StudenteDAO");
        } catch (NamingException e) {
            throw new RuntimeException("Errore durante il lookup di StudenteDAO", e);
        }
    }

    /**
     * Trova uno studente nel database utilizzando la sua matricola.
     *
     * @param matricola La matricola dello studente da cercare.
     * @return L'oggetto Studente corrispondente alla matricola.
     */
    public Studente trovaStudenteUniClass(String matricola) {
        try {
            return studenteDao.trovaStudenteUniClass(matricola);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trova gli studenti nel database associati a un corso di laurea.
     *
     * @param corsoLaurea Il corso di laurea di cui trovare gli studenti.
     * @return Una lista di oggetti Studente associati al corso di laurea.
     */
    public List<Studente> trovaStudentiCorso(CorsoLaurea corsoLaurea) {
        return studenteDao.trovaStudentiCorso(corsoLaurea);
    }

    /**
     * Recupera tutti gli studenti presenti nel database.
     *
     * @return Una lista di tutti gli studenti.
     */
    public List<Studente> trovaTuttiUniClass() {
        return studenteDao.trovaTuttiUniClass();
    }

    /**
     * Trova uno studente nel database utilizzando la sua email.
     *
     * @param email L'email dello studente da cercare.
     * @return L'oggetto Studente corrispondente all'email.
     */
    public Studente trovaStudenteEmailUniClass(String email) {
        try {
            return studenteDao.trovaStudenteEmailUniClass(email);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Aggiunge uno studente nel database.
     *
     * @param studente Lo studente da aggiungere.
     * @throws IncorrectUserSpecification Se le specifiche dell'utente sono errate.
     * @throws AlreadyExistentUserException Se l'utente è già presente nel database.
     * @throws NotFoundUserException Se l'utente non è stato trovato.
     */
    public void aggiungiStudente(Studente studente) throws IncorrectUserSpecification, NotFoundUserException, AlreadyExistentUserException {
        Studente trovaStudenteEmailUniClass = trovaStudenteEmailUniClass(studente.getEmail());
        Studente trovaStudenteUniClass = trovaStudenteUniClass(studente.getMatricola());

        if ((trovaStudenteEmailUniClass == null) && (trovaStudenteUniClass == null)) {
            studenteDao.aggiungiStudente(studente);
        } else if (trovaStudenteEmailUniClass != null) {
            throw new AlreadyExistentUserException("Utente da aggiungere già presente.");
        }
    }

    /**
     * Rimuove uno studente dal database.
     *
     * @param studente Lo studente da rimuovere.
     * @throws NotFoundUserException Se l'utente non è stato trovato.
     */
    public void rimuoviStudente(Studente studente) throws NotFoundUserException {
        if (trovaStudenteUniClass(studente.getMatricola()) != null) {
            AccademicoService accademicoService = new AccademicoService();
            Accademico accademico = accademicoService.trovaAccademicoUniClass(studente.getMatricola());
            studenteDao.rimuoviStudente(studente);
            accademicoService.rimuoviAccademico(accademico);
        } else {
            throw new NotFoundUserException("Utente da eliminare non trovato.");
        }
    }
}