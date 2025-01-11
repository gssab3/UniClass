package it.unisa.uniclass.utenti.service.dao;

import it.unisa.uniclass.utenti.model.PersonaleTA;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless(name = "PersonaleTADAO")
public class PersonaleTADAO implements PersonaleTARemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @Override
    public PersonaleTA trovaPersonale(long id) {
        TypedQuery<PersonaleTA> query = emUniClass.createNamedQuery(PersonaleTA.TROVA_PERSONALE, PersonaleTA.class);
        query.setParameter("id", id);
        return (PersonaleTA) query.getSingleResult();
    }

    @Override
    public List<PersonaleTA> trovaTutti() {
        TypedQuery<PersonaleTA> query = emUniClass.createNamedQuery(PersonaleTA.TROVA_TUTTI, PersonaleTA.class);
        return query.getResultList();
    }

    @Override
    public PersonaleTA trovaEmail(String email) {
        TypedQuery<PersonaleTA> query = emUniClass.createNamedQuery(PersonaleTA.TROVA_EMAIL, PersonaleTA.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    @Override
    public PersonaleTA trovaEmailPassword(String email, String password){
        TypedQuery<PersonaleTA> query = emUniClass.createNamedQuery(PersonaleTA.TROVA_EMAIL_PASSWORD, PersonaleTA.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        return query.getSingleResult();
    }

    @Override
    public void aggiungiPersonale(PersonaleTA personaleTA) {
        emUniClass.merge(personaleTA);
    }

    @Override
    public void rimuoviPersonale(PersonaleTA personaleTA) {
        emUniClass.remove(personaleTA);
    }
}
