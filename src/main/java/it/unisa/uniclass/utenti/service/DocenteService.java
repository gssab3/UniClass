package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.exceptions.AlreadyExistentUserException;
import it.unisa.uniclass.common.exceptions.IncorrectUserSpecification;
import it.unisa.uniclass.common.exceptions.NotFoundUserException;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Docente;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class DocenteService {
    @EJB
    private DocenteDAO dao;

    @EJB
    private AccademicoDAO accademicoDAO;

    public Docente trovaDocenteUniversity(String matricola) {
        return dao.trovaDocenteUniversity(matricola);
    }

    public Docente trovaDocenteUniClass(String matricola) {
        return dao.trovaDocenteUniClass(matricola);
    }

    public List<Docente> trovaDocenteCorsoLaurea(String nomeCorsoLaurea) {
        return dao.trovaDocenteCorsoLaurea(nomeCorsoLaurea);
    }

    public List<Docente> trovaTuttiUniversity() {
        return dao.trovaTuttiUniversity();
    }

    public List<Docente> trovaTuttiUniClass() {
        return dao.trovaTuttiUniClass();
    }

    public List<Docente> trovaTuttiDocenti() {
        return dao.trovaTuttiDocenti();
    }

    public Docente trovaEmailUniversity(String email) {
        return dao.trovaEmailUniversity(email);
    }

    public Docente trovaEmailUniClass(String email) {
        return dao.trovaEmailUniClass(email);
    }

    public void aggiungiDocente(Docente docente) throws IncorrectUserSpecification, NotFoundUserException, AlreadyExistentUserException {
        Docente trovaEmailUniversity = dao.trovaEmailUniversity(docente.getEmail());
        Docente trovaDocenteUniversity = dao.trovaDocenteUniversity(docente.getMatricola());
        Docente trovaEmailUniclass = dao.trovaEmailUniClass(docente.getEmail());
        Docente trovaDocenteUniClass = dao.trovaDocenteUniClass(docente.getMatricola());
        if((trovaEmailUniversity == trovaDocenteUniversity) &&
        (trovaEmailUniversity != null) &&
        (trovaEmailUniclass == null) &&
        (trovaDocenteUniClass == null)) {
            dao.aggiungiDocente(docente);
        }
        else if(trovaEmailUniversity != trovaDocenteUniversity) {
            throw new IncorrectUserSpecification("Il docente desiderato ha campi di due docenti diversi all'interno dell'università. Contattare quest'ultima o riformulare la richiesta");
        }
        else if(trovaDocenteUniClass != null) {
            throw new AlreadyExistentUserException("Utente da aggiungere già presente");
        }
        else if((trovaEmailUniversity == null) || (trovaDocenteUniversity == null)) {
            throw new NotFoundUserException("Il docente desiderato non è presente nel Database Universitario");
        }
    }

    public void rimuoviDocente(Docente docente) throws NotFoundUserException {
        if(trovaDocenteUniClass(docente.getMatricola()) != null) {
            Accademico accademico = accademicoDAO.trovaAccademicoUniClass(docente.getMatricola());
            dao.rimuoviDocente(docente);
            accademicoDAO.rimuoviAccademico(accademico);
        }
        else {
            throw new NotFoundUserException("Il docente inserito non è presente nel Database universitario");
        }
    }
}
