package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.utenti.model.Studente;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class StudenteDAO {

    EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("DBUniClassPU");
    EntityManager emUniClass = emf1.createEntityManager();

    /*
    EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("DBUniversityPU");
    EntityManager emUniversity = emf2.createEntityManager();

    public Studente trovaStudenteUniversity(String matricola) {
        TypedQuery<Studente> query = emUniversity.createNamedQuery(Studente.TROVA_STUDENTE, Studente.class);
        query.setParameter("matricola", matricola);
        return (Studente) query.getSingleResult();
    }
    */
    public Studente trovaStudenteUniClass(String matricola) {
        TypedQuery<Studente> query = emUniClass.createNamedQuery(Studente.TROVA_STUDENTE, Studente.class);
        query.setParameter("matricola", matricola);
        return (Studente) query.getSingleResult();
    }

    public List<Studente> trovaStudentiCorso(CorsoLaurea corsoLaurea) {
        TypedQuery<Studente> query = emUniClass.createNamedQuery(Studente.TROVA_STUDENTI_CORSO, Studente.class);
        query.setParameter("corso", corsoLaurea.getNome());
        return query.getResultList();
    }

    /*
    public List<Studente> trovaTuttiUniversity() {
        TypedQuery<Studente> query = emUniversity.createNamedQuery(Studente.TROVA_TUTTI, Studente.class);
        return query.getResultList();
    }

     */

    public List<Studente> trovaTuttiUniClass() {
        TypedQuery<Studente> query = emUniClass.createNamedQuery(Studente.TROVA_TUTTI, Studente.class);
        return query.getResultList();
    }

    public Studente trovaStudenteEmailUniClass(String email) {
        TypedQuery<Studente> query = emUniClass.createNamedQuery(Studente.TROVA_EMAIL, Studente.class);
        query.setParameter("email", email);
        return (Studente) query.getSingleResult();
    }
    /*
    public Studente trovaStudenteEmailUniversity(String email) {
        TypedQuery<Studente> query = emUniversity.createNamedQuery(Studente.TROVA_EMAIL, Studente.class);
        query.setParameter("email", email);
        return (Studente) query.getSingleResult();
    }

     */


    public void aggiungiStudente(Studente studente) {
            emUniClass.merge(studente);
    }

    public void rimuoviStudente(Studente studente) {
        emUniClass.remove(studente);
    }
}
