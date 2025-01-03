package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.utenti.model.PersonaleTA;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import java.util.List;

@Stateless
public class PersonaleTAService {

    @EJB
    private PersonaleTADAO personaleTADAO;

    public PersonaleTA trovaPersonale(long id) {
        return personaleTADAO.trovaPersonale(id);
    }

    public List<PersonaleTA> trovaTutti() {
        return personaleTADAO.trovaTutti();
    }

    public PersonaleTA trovaEmail(String email) {
        return personaleTADAO.trovaEmail(email);
    }

    public void aggiungiPersonaleTA(PersonaleTA personaleTA) {
        personaleTADAO.aggiungiPersonale(personaleTA);
    }

    public void rimuoviPersonaleTA(PersonaleTA personaleTA) {
        personaleTADAO.rimuoviPersonale(personaleTA);
    }
}
