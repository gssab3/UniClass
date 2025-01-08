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

@Stateless
public class DocenteService {

    private DocenteRemote docenteDao;

    public DocenteService() {
        try {
            InitialContext ctx = new InitialContext();
            docenteDao = (DocenteRemote) ctx.lookup("java:global/UniClass/DocenteDAO");
        }
        catch(NamingException e) {
            throw new RuntimeException("Errore durante il lookup di DocenteDAO", e);
        }
    }


    public Docente trovaDocenteUniClass(String matricola) {
        try {
            return docenteDao.trovaDocenteUniClass(matricola);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<Docente> trovaDocenteCorsoLaurea(String nomeCorsoLaurea) {
        return docenteDao.trovaDocenteCorsoLaurea(nomeCorsoLaurea);
    }


    public List<Docente> trovaTuttiUniClass() {
        return docenteDao.trovaTuttiUniClass();
    }

    public Docente trovaEmailUniClass(String email) {
        try {
            return docenteDao.trovaEmailUniClass(email);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public void aggiungiDocente(Docente docente) throws IncorrectUserSpecification, NotFoundUserException, AlreadyExistentUserException {
        Docente trovaEmailUniclass = docenteDao.trovaEmailUniClass(docente.getEmail());
        Docente trovaDocenteUniClass = docenteDao.trovaDocenteUniClass(docente.getMatricola());
        if((trovaEmailUniclass == null) && (trovaDocenteUniClass == null)) {
            docenteDao.aggiungiDocente(docente);
        }
        else if(trovaEmailUniclass != trovaDocenteUniClass) {
            throw new IncorrectUserSpecification("Il docente desiderato ha campi di due docenti diversi all'interno del database.");
        }
    }

    public void rimuoviDocente(Docente docente) throws NotFoundUserException {
        if(trovaDocenteUniClass(docente.getMatricola()) != null) {
            AccademicoService accademicoService = new AccademicoService();
            Accademico accademico = accademicoService.trovaAccademicoUniClass(docente.getMatricola());
            docenteDao.rimuoviDocente(docente);
            accademicoService.rimuoviAccademico(accademico);
        }
        else {
            throw new NotFoundUserException("Il docente inserito non è presente nel Database universitario");
        }
    }
}
