package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.exceptions.AlreadyExistentUserException;
import it.unisa.uniclass.common.exceptions.IncorrectUserSpecification;
import it.unisa.uniclass.common.exceptions.NotFoundUserException;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.Coordinatore;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class CoordinatoreService {
    @EJB
    private CoordinatoreDAO dao;

    @EJB
    private AccademicoDAO accademicoDAO;

    public Coordinatore trovaCoordinatoreUniversity(String matricola) {
        return dao.trovaCoordinatoreUniversity(matricola);
    }

    public Coordinatore trovaCoordinatoreUniClass(String matricola) {
        return dao.trovaCoordinatoreUniClass(matricola);
    }

    public List<Coordinatore> trovaCoordinatoriCorsoLaurea(String nomeCorsoLaurea) {
        return dao.trovaCoordinatoriCorsoLaurea(nomeCorsoLaurea);
    }

    public Coordinatore trovaCoordinatoreEmailUniversity(String email) {
        return dao.trovaCoordinatoreEmailUniversity(email);
    }

    public Coordinatore trovaCoordinatoreEmailUniclass(String email) {
        return dao.trovaCoordinatoreEmailUniclass(email);
    }

    public List<Coordinatore> trovaTutti() {
        return dao.trovaTutti();
    }

    public void aggiungiCoordinatore(Coordinatore coordinatore) throws IncorrectUserSpecification, AlreadyExistentUserException, NotFoundUserException {
        Coordinatore trovaCoordinatoreEmailUniClass = dao.trovaCoordinatoreEmailUniclass(coordinatore.getEmail());
        Coordinatore trovaCoordinatoreUniversity = dao.trovaCoordinatoreUniversity(coordinatore.getMatricola());
        Coordinatore trovaCoordinatoreEmailUniversity = dao.trovaCoordinatoreEmailUniversity(coordinatore.getEmail());
        Coordinatore trovaCoordinatoreUniClass = dao.trovaCoordinatoreUniClass(coordinatore.getMatricola());

        if((trovaCoordinatoreEmailUniversity == trovaCoordinatoreUniversity) &&
        (trovaCoordinatoreEmailUniversity != null) &&
        (trovaCoordinatoreUniClass == trovaCoordinatoreEmailUniClass) &&
        (trovaCoordinatoreUniClass == null)) {
            dao.aggiungiCoordinatore(coordinatore);
        }
        else if(trovaCoordinatoreEmailUniversity != trovaCoordinatoreUniversity) {
            throw new IncorrectUserSpecification("Il coordinatore ha campi di due coordinatori differenti nel database universitario.");
        }
        else if(trovaCoordinatoreEmailUniClass != null) {
            throw new AlreadyExistentUserException("Utente da aggiungere già presente");
        }
        else if(trovaCoordinatoreEmailUniversity == null) {
            throw new NotFoundUserException("Utente da aggiungere non trovato nel DB Universitario");
        }
    }

    public void rimuoviCoordinatore(Coordinatore coordinatore) throws NotFoundUserException {
        if(trovaCoordinatoreEmailUniclass(coordinatore.getEmail()) != null) {
            Accademico accademico = accademicoDAO.trovaAccademicoUniversity(coordinatore.getMatricola());
            dao.rimuoviCoordinatore(coordinatore);
            accademicoDAO.rimuoviAccademico(accademico);
        }
        else {
            throw new NotFoundUserException("Utente da rimuovere non trovato.");
        }
    }
}
