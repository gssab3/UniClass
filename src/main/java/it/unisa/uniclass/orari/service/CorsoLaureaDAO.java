package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.common.config.database.qualifier.UniclassDB;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class CorsoLaureaDAO {
    @Inject
    @UniclassDB
    private EntityManager emUniClass;

    public CorsoLaurea trovaCorsoLaurea(long id) {
        TypedQuery<CorsoLaurea> query = emUniClass.createNamedQuery(CorsoLaurea.TROVA_CORSOLAUREA, CorsoLaurea.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public CorsoLaurea trovaCorsoLaurea(String nome) {
        TypedQuery<CorsoLaurea> query = emUniClass.createNamedQuery(CorsoLaurea.TROVA_CORSOLAUREA_NOME, CorsoLaurea.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }

    public List<CorsoLaurea> trovaTutti() {
        TypedQuery<CorsoLaurea> query = emUniClass.createNamedQuery(CorsoLaurea.TROVA_TUTTI, CorsoLaurea.class);
        return query.getResultList();
    }

    public void aggiungiCorsoLaurea(CorsoLaurea corsoLaurea) {
        emUniClass.merge(corsoLaurea);
    }

    public void rimuoviCorsoLaurea(CorsoLaurea corsoLaurea) {
        emUniClass.remove(corsoLaurea);
    }
}
