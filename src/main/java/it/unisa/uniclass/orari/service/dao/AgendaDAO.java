package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.Agenda;
import it.unisa.uniclass.utenti.model.Studente;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

/**
 * Classe DAO per la gestione delle entità Agenda nel database.
 * Fornisce metodi per recuperare, aggiungere e rimuovere agende.
 */
@Stateless(name = "AgendaDAO")
public class AgendaDAO implements AgendaRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    /**
     * Trova un'agenda nel database utilizzando il suo ID.
     *
     * @param id L'ID dell'agenda da cercare.
     * @return L'oggetto Agenda corrispondente all'ID.
     */
    @Override
    public Agenda trovaAgenda(long id) {
        TypedQuery<Agenda> query = emUniClass.createNamedQuery(Agenda.TROVA_AGENDA_ID, Agenda.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Trova un'agenda nel database associata a uno studente.
     *
     * @param studente Lo studente di cui trovare l'agenda.
     * @return L'oggetto Agenda associato allo studente.
     */
    @Override
    public Agenda trovaAgenda(Studente studente) {
        TypedQuery<Agenda> query = emUniClass.createNamedQuery(Agenda.TROVA_AGENDA_STUDENTE, Agenda.class);
        query.setParameter("matricola", studente.getMatricola());
        return query.getSingleResult();
    }

    /**
     * Recupera tutte le agende presenti nel database.
     *
     * @return Una lista di tutte le agende.
     */
    @Override
    public List<Agenda> trovaTutte() {
        TypedQuery<Agenda> query = emUniClass.createNamedQuery(Agenda.TROVA_TUTTE, Agenda.class);
        return query.getResultList();
    }

    /**
     * Aggiunge o aggiorna un'agenda nel database.
     *
     * @param agenda L'agenda da aggiungere o aggiornare.
     */
    @Override
    public void aggiungiAgenda(Agenda agenda) {
        emUniClass.merge(agenda);
    }

    /**
     * Rimuove un'agenda dal database.
     *
     * @param agenda L'agenda da rimuovere.
     */
    @Override
    public void rimuoviAgenda(Agenda agenda) {
        emUniClass.remove(agenda);
    }
}
