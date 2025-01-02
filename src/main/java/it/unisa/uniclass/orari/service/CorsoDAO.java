package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.Corso;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class CorsoDAO {
    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    public Corso trovaCorso(long id) {
        TypedQuery<Corso> query = emUniClass.createNamedQuery(Corso.TROVA_CORSO, Corso.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Corso> trovaCorsiCorsoLaurea(String nomeCorsoLaurea) {
        TypedQuery<Corso> query = emUniClass.createNamedQuery(Corso.TROVA_CORSI_CORSOLAUREA, Corso.class);
        query.setParameter("nomeCorsoLaurea", nomeCorsoLaurea);
        return query.getResultList();
    }

    public List<Corso> trovaTutti() {
        TypedQuery<Corso> query = emUniClass.createNamedQuery(Corso.TROVA_TUTTE, Corso.class);
        return query.getResultList();
    }

    public void aggiungiCorso(Corso corso) {
        emUniClass.persist(corso);
    }

    public void rimuoviCorso(Corso corso) {
        emUniClass.remove(corso);
    }
}
