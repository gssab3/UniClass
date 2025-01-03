package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.exceptions.AlreadyExistentUserException;
import it.unisa.uniclass.common.exceptions.IncorrectUserSpecification;
import it.unisa.uniclass.common.exceptions.NotFoundUserException;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Studente;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class StudenteService {
    @EJB
    private StudenteDAO studenteDAO;

    @EJB
    private AccademicoDAO accademicoDAO;

    public Studente trovaStudenteUniversity(String matricola) {
        return studenteDAO.trovaStudenteUniversity(matricola);
    }

    public Studente trovaStudenteUniClass(String matricola) {
        return studenteDAO.trovaStudenteUniClass(matricola);
    }

    public List<Studente> trovaStudentiCorso(CorsoLaurea corsoLaurea) {
        return studenteDAO.trovaStudentiCorso(corsoLaurea);
    }

    public List<Studente> trovaTuttiUniversity() {
        return studenteDAO.trovaTuttiUniversity();
    }

    public List<Studente> trovaTuttiUniClass() {
        return studenteDAO.trovaTuttiUniClass();
    }

    public Studente trovaStudenteEmailUniClass(String email) {
        return studenteDAO.trovaStudenteEmailUniClass(email);
    }

    public Studente trovaStudenteEmailUniversity(String email) {
        return studenteDAO.trovaStudenteEmailUniversity(email);
    }

    public void aggiungiStudente(Studente studente) throws IncorrectUserSpecification, NotFoundUserException, AlreadyExistentUserException {
        Studente trovaStudenteEmailUniversity = trovaStudenteUniversity(studente.getEmail());
        Studente trovaStudenteUniversity = trovaStudenteUniversity(studente.getMatricola());
        Studente trovaStudenteEmailUniClass = trovaStudenteEmailUniClass(studente.getEmail());
        Studente trovaStudenteUniClass = trovaStudenteUniClass(studente.getMatricola());

        if((trovaStudenteEmailUniversity == trovaStudenteUniversity) &&
        (trovaStudenteEmailUniClass == null) &&
        (trovaStudenteUniClass == null) &&
        (trovaStudenteUniversity != null)) {
            studenteDAO.aggiungiStudente(studente);
        }
        else if(trovaStudenteEmailUniversity != trovaStudenteUniversity) {
            throw new IncorrectUserSpecification("Lo studente ha un'email ed una matricola di due studenti differenti. Contattare l'università o correggere l'inserimento");
        }
        else if(trovaStudenteEmailUniClass != null) {
            throw new AlreadyExistentUserException("Utente da aggiungere già presente.");
        }
        else if((trovaStudenteUniversity == null) || (trovaStudenteEmailUniClass == null)) {
            throw new NotFoundUserException("Utente non trovato nel Database Universitario.");
        }
    }

    public void rimuoviStudente(Studente studente) throws NotFoundUserException {
        if(trovaStudenteUniClass(studente.getMatricola()) != null) {
            Accademico accademico = accademicoDAO.trovaAccademicoUniClass(studente.getMatricola());
            studenteDAO.rimuoviStudente(studente);
            accademicoDAO.rimuoviAccademico(accademico);
        }
        else {
            throw new NotFoundUserException("Utente da eliminare non trovato.");
        }
    }
}
