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

@Stateless
public class StudenteService {

    private StudenteRemote studenteDao;

    public StudenteService() {
        try {
            InitialContext ctx = new InitialContext();
            studenteDao = (StudenteRemote) ctx.lookup("java:global/UniClass/StudenteDAO");
        }
        catch(NamingException e) {
            throw new RuntimeException("Errore durante il lookup di StudenteDAO", e);
        }
    }

    public Studente trovaStudenteUniClass(String matricola) {
        try {
            return studenteDao.trovaStudenteUniClass(matricola);
        }
        catch (NoResultException e) {
            return null;
        }
    }

    public List<Studente> trovaStudentiCorso(CorsoLaurea corsoLaurea) {
        return studenteDao.trovaStudentiCorso(corsoLaurea);
    }

    public List<Studente> trovaTuttiUniClass() {
        return studenteDao.trovaTuttiUniClass();
    }

    public Studente trovaStudenteEmailUniClass(String email) {
        try {
            return studenteDao.trovaStudenteEmailUniClass(email);
        }
        catch(NoResultException e) {
            return null;
        }
    }


    public void aggiungiStudente(Studente studente) throws IncorrectUserSpecification, NotFoundUserException, AlreadyExistentUserException {
        Studente trovaStudenteEmailUniClass = trovaStudenteEmailUniClass(studente.getEmail());
        Studente trovaStudenteUniClass = trovaStudenteUniClass(studente.getMatricola());

        if((trovaStudenteEmailUniClass == null) && (trovaStudenteUniClass == null)) {
            studenteDao.aggiungiStudente(studente);
        }
        else if(trovaStudenteEmailUniClass != null) {
            throw new AlreadyExistentUserException("Utente da aggiungere già presente.");
        }
    }

    public void rimuoviStudente(Studente studente) throws NotFoundUserException {
        if(trovaStudenteUniClass(studente.getMatricola()) != null) {
            AccademicoService accademicoService = new AccademicoService();
            Accademico accademico = accademicoService.trovaAccademicoUniClass(studente.getMatricola());
            studenteDao.rimuoviStudente(studente);
            accademicoService.rimuoviAccademico(accademico);
        }
        else {
            throw new NotFoundUserException("Utente da eliminare non trovato.");
        }
    }
}
