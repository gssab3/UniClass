package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.exceptions.NotFoundUserException;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.utenti.model.Studente;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class StudenteDAO {
    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @PersistenceContext(unitName = "DBUniversityPU")
    private EntityManager emUniversity;

    public Studente trovaStudenteUniversity(String matricola) {
        TypedQuery<Studente> query = emUniversity.createNamedQuery(Studente.TROVA_STUDENTE, Studente.class);
        query.setParameter("matricola", matricola);
        return (Studente) query.getSingleResult();
    }

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

    public List<Studente> trovaTuttiUniversity() {
        TypedQuery<Studente> query = emUniversity.createNamedQuery(Studente.TROVA_TUTTI, Studente.class);
        return query.getResultList();
    }

    public List<Studente> trovaTuttiUniClass() {
        TypedQuery<Studente> query = emUniClass.createNamedQuery(Studente.TROVA_TUTTI, Studente.class);
        return query.getResultList();
    }


    public void aggiungiStudente(Studente studente) throws NotFoundUserException {
        if (trovaStudenteUniversity(studente.getMatricola()) != null) {
            emUniClass.persist(studente);
        }
        else {
            throw new NotFoundUserException("Studente non presente nel Database Universitario.");
        }
    }

    public void rimuoviStudente(Studente studente) {
        emUniClass.remove(studente);
    }
}