package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.config.database.qualifier.UniclassDB;
import it.unisa.uniclass.common.config.database.qualifier.UniversityDB;
import it.unisa.uniclass.common.exceptions.NotFoundUserException;
import it.unisa.uniclass.utenti.model.Docente;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class DocenteDAO {
    @Inject
    @UniclassDB
    private EntityManager emUniClass;

    @Inject
    @UniversityDB
    private EntityManager emUniversity;

    public Docente trovaDocenteUniversity(String matricola) {
        TypedQuery<Docente> query = emUniversity.createNamedQuery(Docente.TROVA_DOCENTE, Docente.class);
        query.setParameter("matricola", matricola);
        return (Docente) query.getSingleResult();
    }

    public Docente trovaDocenteUniClass(String matricola) {
        TypedQuery<Docente> query = emUniClass.createNamedQuery(Docente.TROVA_DOCENTE, Docente.class);
        query.setParameter("matricola", matricola);
        return query.getSingleResult();
    }

    public List<Docente> trovaDocenteCorsoLaurea(String nomeCorsoLaurea) {
        TypedQuery<Docente> query = emUniClass.createNamedQuery(Docente.TROVA_DOCENTE_CORSOLAUREA, Docente.class);
        query.setParameter("nome", nomeCorsoLaurea);
        return query.getResultList();
    }

    public List<Docente> trovaTuttiUniversity() {
        TypedQuery<Docente> query = emUniversity.createNamedQuery(Docente.TROVA_TUTTI, Docente.class);
        return query.getResultList();
    }

    public List<Docente> trovaTuttiUniClass() {
        TypedQuery<Docente> query = emUniClass.createNamedQuery(Docente.TROVA_TUTTI, Docente.class);
        return query.getResultList();
    }

    public List<Docente> trovaTuttiDocenti() {
        TypedQuery<Docente> query = emUniClass.createNamedQuery(Docente.TROVA_TUTTI_DOCENTI, Docente.class);
        return query.getResultList();
    }

    public Docente trovaEmailUniversity(String email) {
        TypedQuery<Docente> query = emUniversity.createNamedQuery(Docente.TROVA_EMAIL, Docente.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    public Docente trovaEmailUniClass(String email) {
        TypedQuery<Docente> query = emUniClass.createNamedQuery(Docente.TROVA_EMAIL, Docente.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    public void aggiungiDocente(Docente docente) {
        emUniClass.merge(docente);
    }

    public void rimuoviDocente(Docente docente) {
        emUniClass.remove(docente);
    }
}
