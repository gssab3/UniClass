package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.utenti.model.PersonaleTA;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import java.util.List;

@Stateless
public class PersonaleTAService {

    @EJB
    private PersonaleTADAO personaleTADAO;

    public PersonaleTA trovaPersonale(long id) {
        try {
            return personaleTADAO.trovaPersonale(id);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<PersonaleTA> trovaTutti() {
        return personaleTADAO.trovaTutti();
    }

    public PersonaleTA trovaEmail(String email) {
        try {
            return personaleTADAO.trovaEmail(email);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public void aggiungiPersonaleTA(PersonaleTA personaleTA) {
        personaleTADAO.aggiungiPersonale(personaleTA);
    }

    public void rimuoviPersonaleTA(PersonaleTA personaleTA) {
        personaleTADAO.rimuoviPersonale(personaleTA);
    }
}
