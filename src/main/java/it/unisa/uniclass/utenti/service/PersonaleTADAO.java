package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.utenti.model.PersonaleTA;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class PersonaleTADAO {

    EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("DBUniClassPU");
    EntityManager emUniClass = emf1.createEntityManager();

    /*
    EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("DBUniversityPU");
    EntityManager emUniversity = emf2.createEntityManager();
    */
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
