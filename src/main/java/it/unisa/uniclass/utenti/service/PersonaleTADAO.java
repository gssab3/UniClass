package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.config.database.qualifier.UniclassDB;
import it.unisa.uniclass.common.config.database.qualifier.UniversityDB;
import it.unisa.uniclass.utenti.model.PersonaleTA;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class PersonaleTADAO {

    @Inject
    @UniclassDB
    private EntityManager emUniClass;

    @Inject
    @UniversityDB
    private EntityManager emUniversity;

    public PersonaleTA trovaPersonale(long id) {
        TypedQuery<PersonaleTA> query = emUniClass.createNamedQuery(PersonaleTA.TROVA_PERSONALE, PersonaleTA.class);
        query.setParameter("id", id);
        return (PersonaleTA) query.getSingleResult();
    }

    public List<PersonaleTA> trovaTutti() {
        TypedQuery<PersonaleTA> query = emUniClass.createNamedQuery(PersonaleTA.TROVA_TUTTI, PersonaleTA.class);
        return query.getResultList();
    }

    public PersonaleTA trovaEmail(String email) {
        TypedQuery<PersonaleTA> query = emUniClass.createNamedQuery(PersonaleTA.TROVA_EMAIL, PersonaleTA.class);
        query.setParameter("email", email);
        return (PersonaleTA) query.getSingleResult();
    }

    public void aggiungiPersonale(PersonaleTA personaleTA) {
        emUniClass.merge(personaleTA);
    }

    public void rimuoviPersonale(PersonaleTA personaleTA) {
        emUniClass.remove(personaleTA);
    }
}
