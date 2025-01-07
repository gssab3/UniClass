package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.Agenda;
import it.unisa.uniclass.utenti.model.Studente;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless(name = "AgendaDAO")
public class AgendaDAO implements AgendaRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @Override
    public Agenda trovaAgenda(long id) {
        TypedQuery<Agenda> query = emUniClass.createNamedQuery(Agenda.TROVA_AGENDA_ID, Agenda.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Agenda trovaAgenda(Studente studente) {
        TypedQuery<Agenda> query = emUniClass.createNamedQuery(Agenda.TROVA_AGENDA_STUDENTE, Agenda.class);
        query.setParameter("matricola", studente.getMatricola());
        return query.getSingleResult();
    }

    @Override
    public List<Agenda> trovaTutte() {
        TypedQuery<Agenda> query = emUniClass.createNamedQuery(Agenda.TROVA_TUTTE, Agenda.class);
        return query.getResultList();
    }

    @Override
    public void aggiungiAgenda(Agenda agenda) {
        emUniClass.merge(agenda);
    }

    @Override
    public void rimuoviAgenda(Agenda agenda) {
        emUniClass.remove(agenda);
    }
}
