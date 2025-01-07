package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.Corso;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless(name = "CorsoDAO")
public class CorsoDAO implements CorsoRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @Override
    public Corso trovaCorso(long id) {
        TypedQuery<Corso> query = emUniClass.createNamedQuery(Corso.TROVA_CORSO, Corso.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Corso> trovaCorsiCorsoLaurea(String nomeCorsoLaurea) {
        TypedQuery<Corso> query = emUniClass.createNamedQuery(Corso.TROVA_CORSI_CORSOLAUREA, Corso.class);
        query.setParameter("nomeCorsoLaurea", nomeCorsoLaurea);
        return query.getResultList();
    }

    @Override
    public List<Corso> trovaTutti() {
        TypedQuery<Corso> query = emUniClass.createNamedQuery(Corso.TROVA_TUTTE, Corso.class);
        return query.getResultList();
    }

    @Override
    public void aggiungiCorso(Corso corso) {
        emUniClass.merge(corso);
    }

    @Override
    public void rimuoviCorso(Corso corso) {
        emUniClass.remove(corso);
    }
}
