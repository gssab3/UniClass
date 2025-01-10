package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.PersonaleTA;
import it.unisa.uniclass.utenti.service.dao.PersonaleTARemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

@Stateless
public class PersonaleTAService {

    private PersonaleTARemote personaleTAdao;

    public PersonaleTAService() {
        try {
            InitialContext ctx = new InitialContext();
            personaleTAdao = (PersonaleTARemote) ctx.lookup("java:global/UniClass/PersonaleTADAO");
        }
        catch(NamingException e) {
            throw new RuntimeException("Errore durante il lookup di PersonaleTADAO", e);
        }
    }

    public PersonaleTA trovaPersonale(long id) {
        try {
            return personaleTAdao.trovaPersonale(id);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<PersonaleTA> trovaTutti() {
        return personaleTAdao.trovaTutti();
    }

    public PersonaleTA trovaEmail(String email) {
        try {
            return personaleTAdao.trovaEmail(email);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public PersonaleTA trovaEmailPass(String email, String pass) {
        try{
            return  personaleTAdao.trovaEmailPassword(email, pass);
        }catch(Exception e) {
            return null;
        }
    }

    public void aggiungiPersonaleTA(PersonaleTA personaleTA) {
        personaleTAdao.aggiungiPersonale(personaleTA);
    }

    public void rimuoviPersonaleTA(PersonaleTA personaleTA) {
        personaleTAdao.rimuoviPersonale(personaleTA);
    }
}
