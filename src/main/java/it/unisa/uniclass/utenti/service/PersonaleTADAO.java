package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.utenti.model.PersonaleTA;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class PersonaleTADAO {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @PersistenceContext(unitName = "DBUniversityPU")
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

    public void aggiungiPersonale(PersonaleTA personaleTA) {
        emUniClass.persist(personaleTA);
    }

    public void rimuoviPersonale(PersonaleTA personaleTA) {
        emUniClass.remove(personaleTA);
    }
}
