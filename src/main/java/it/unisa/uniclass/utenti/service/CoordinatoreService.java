package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.exceptions.AlreadyExistentUserException;
import it.unisa.uniclass.common.exceptions.IncorrectUserSpecification;
import it.unisa.uniclass.common.exceptions.NotFoundUserException;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Coordinatore;
import it.unisa.uniclass.utenti.service.dao.AccademicoDAO;
import it.unisa.uniclass.utenti.service.dao.AccademicoRemote;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

@Stateless
public class CoordinatoreService {

    private CoordinatoreRemote coordinatoreDao;

    public CoordinatoreService() {
        try {
            InitialContext ctx = new InitialContext();
            coordinatoreDao = (CoordinatoreRemote) ctx.lookup("java:global/UniClass/CoordinatoreDAO");
        }
        catch(NamingException e) {
            throw new RuntimeException("Errore durante il lookup di CoordinatoreDAO", e);
        }
    }

    public Coordinatore trovaCoordinatoreUniClass(String matricola) {
        try {
            return coordinatoreDao.trovaCoordinatoreUniClass(matricola);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<Coordinatore> trovaCoordinatoriCorsoLaurea(String nomeCorsoLaurea) {
        return coordinatoreDao.trovaCoordinatoriCorsoLaurea(nomeCorsoLaurea);
    }


    public Coordinatore trovaCoordinatoreEmailUniclass(String email) {
        try {
            return coordinatoreDao.trovaCoordinatoreEmailUniclass(email);
        }
        catch(NoResultException e) {
            return null;
        }
    }
    public List<Coordinatore> trovaTutti() {
        return coordinatoreDao.trovaTutti();
    }

    public void aggiungiCoordinatore(Coordinatore coordinatore) throws IncorrectUserSpecification, AlreadyExistentUserException, NotFoundUserException {
        Coordinatore trovaCoordinatoreEmailUniClass = coordinatoreDao.trovaCoordinatoreEmailUniclass(coordinatore.getEmail());
        Coordinatore trovaCoordinatoreUniClass = coordinatoreDao.trovaCoordinatoreUniClass(coordinatore.getMatricola());

        if((trovaCoordinatoreUniClass == trovaCoordinatoreEmailUniClass) &&
        (trovaCoordinatoreUniClass == null)) {
            coordinatoreDao.aggiungiCoordinatore(coordinatore);
        }
        else if(trovaCoordinatoreEmailUniClass != null) {
            throw new AlreadyExistentUserException("Utente da aggiungere già presente");
        }
    }

    public void rimuoviCoordinatore(Coordinatore coordinatore) throws NotFoundUserException {
        if(trovaCoordinatoreEmailUniclass(coordinatore.getEmail()) != null) {
            AccademicoService accademicoService = new AccademicoService();
            Accademico accademico = accademicoService.trovaAccademicoUniClass(coordinatore.getMatricola());
            coordinatoreDao.rimuoviCoordinatore(coordinatore);
            accademicoService.rimuoviAccademico(accademico);
        }
        else {
            throw new NotFoundUserException("Utente da rimuovere non trovato.");
        }
    }
}
