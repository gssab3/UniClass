package it.unisa.uniclass.utenti.service.dao;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.utenti.model.Studente;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless(name = "StudenteDAO")
public class StudenteDAO implements StudenteRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @Override
    public Studente trovaStudenteUniClass(String matricola) {
        TypedQuery<Studente> query = emUniClass.createNamedQuery(Studente.TROVA_STUDENTE, Studente.class);
        query.setParameter("matricola", matricola);
        return (Studente) query.getSingleResult();
    }

    @Override
    public List<Studente> trovaStudentiCorso(CorsoLaurea corsoLaurea) {
        TypedQuery<Studente> query = emUniClass.createNamedQuery(Studente.TROVA_STUDENTI_CORSO, Studente.class);
        query.setParameter("corso", corsoLaurea.getNome());
        return query.getResultList();
    }

    @Override
    public List<Studente> trovaTuttiUniClass() {
        TypedQuery<Studente> query = emUniClass.createNamedQuery(Studente.TROVA_TUTTI, Studente.class);
        return query.getResultList();
    }

    @Override
    public Studente trovaStudenteEmailUniClass(String email) {
        TypedQuery<Studente> query = emUniClass.createNamedQuery(Studente.TROVA_EMAIL, Studente.class);
        query.setParameter("email", email);
        return (Studente) query.getSingleResult();
    }

    @Override
    public void aggiungiStudente(Studente studente) {
            emUniClass.merge(studente);
    }

    @Override
    public void rimuoviStudente(Studente studente) {
        emUniClass.remove(studente);
    }
}
