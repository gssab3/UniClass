package it.unisa.uniclass.utenti.service.dao;

import it.unisa.uniclass.utenti.model.PersonaleTA;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface PersonaleTARemote {
    public PersonaleTA trovaPersonale(long id);
    public List<PersonaleTA> trovaTutti();
    public PersonaleTA trovaEmail(String email);
    public void aggiungiPersonale(PersonaleTA personaleTA);
    public void rimuoviPersonale(PersonaleTA personaleTA);
}
