package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.exceptions.AlreadyExistentUserException;
import it.unisa.uniclass.common.exceptions.IncorrectUserSpecification;
import it.unisa.uniclass.common.exceptions.NotFoundUserException;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Docente;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import java.util.List;

@Stateless
public class DocenteService {
    @EJB
    private DocenteDAO dao;

    @EJB
    private AccademicoDAO accademicoDAO;

    public Docente trovaDocenteUniversity(String matricola) {
        try {
            return dao.trovaDocenteUniversity(matricola);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public Docente trovaDocenteUniClass(String matricola) {
        try {
            return dao.trovaDocenteUniClass(matricola);
        }
        catch(NoResultException e) {
            return null;
        }
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
        try {
            return dao.trovaEmailUniversity(email);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public Docente trovaEmailUniClass(String email) {
        try {
            return dao.trovaEmailUniClass(email);
        }
        catch(NoResultException e) {
            return null;
        }
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
