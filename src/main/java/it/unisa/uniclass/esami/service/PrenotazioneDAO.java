package it.unisa.uniclass.esami.service;

import it.unisa.uniclass.esami.model.Prenotazione;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class PrenotazioneDAO {
    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    public List<Prenotazione> trovaPrenotazioniStudente(String matricola) {
        TypedQuery<Prenotazione> query = emUniClass.createNamedQuery(Prenotazione.TROVA_STUDENTE, Prenotazione.class);
        query.setParameter("matricola", matricola);
        return query.getResultList();
    }

    public List<Prenotazione> trovaPrenotazioniAppello(long id) {
        TypedQuery<Prenotazione> query = emUniClass.createNamedQuery(Prenotazione.TROVA_APPELLO, Prenotazione.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public Prenotazione trovaPrenotazione(long id) {
        TypedQuery<Prenotazione> query = emUniClass.createNamedQuery(Prenotazione.TROVA_PRENOTAZIONE,Prenotazione.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Prenotazione> trovaTutte() {
        TypedQuery<Prenotazione> query = emUniClass.createNamedQuery(Prenotazione.TROVA_TUTTE, Prenotazione.class);
        return query.getResultList();
    }

    public void aggiungiPrenotazione(Prenotazione prenotazione) {
        emUniClass.persist(prenotazione);
    }

    public void rimuoviPrenotazione(Prenotazione prenotazione) {
        emUniClass.remove(prenotazione);
    }
}
