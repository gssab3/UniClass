package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.Agenda;
import it.unisa.uniclass.utenti.model.Studente;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class AgendaDAO {
    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    public Agenda trovaAgenda(long id) {
        TypedQuery<Agenda> query = emUniClass.createNamedQuery(Agenda.TROVA_AGENDA_ID, Agenda.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public Agenda trovaAgenda(Studente studente) {
        TypedQuery<Agenda> query = emUniClass.createNamedQuery(Agenda.TROVA_AGENDA_STUDENTE, Agenda.class);
        query.setParameter("matricola", studente.getMatricola());
        return query.getSingleResult();
    }

    public List<Agenda> trovaTutte() {
        TypedQuery<Agenda> query = emUniClass.createNamedQuery(Agenda.TROVA_TUTTE, Agenda.class);
        return query.getResultList();
    }

    public void aggiungiAgenda(Agenda agenda) {
        emUniClass.persist(agenda);
    }

    public void rimuoviAgenda(Agenda agenda) {
        emUniClass.remove(agenda);
    }
}
