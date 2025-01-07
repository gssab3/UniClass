package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless(name = "CorsoLaureaDAO")
public class CorsoLaureaDAO implements CorsoLaureaRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @Override
    public CorsoLaurea trovaCorsoLaurea(long id) {
        TypedQuery<CorsoLaurea> query = emUniClass.createNamedQuery(CorsoLaurea.TROVA_CORSOLAUREA, CorsoLaurea.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public CorsoLaurea trovaCorsoLaurea(String nome) {
        TypedQuery<CorsoLaurea> query = emUniClass.createNamedQuery(CorsoLaurea.TROVA_CORSOLAUREA_NOME, CorsoLaurea.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }

    @Override
    public List<CorsoLaurea> trovaTutti() {
        TypedQuery<CorsoLaurea> query = emUniClass.createNamedQuery(CorsoLaurea.TROVA_TUTTI, CorsoLaurea.class);
        return query.getResultList();
    }

    @Override
    public void aggiungiCorsoLaurea(CorsoLaurea corsoLaurea) {
        emUniClass.merge(corsoLaurea);
    }

    @Override
    public void rimuoviCorsoLaurea(CorsoLaurea corsoLaurea) {
        emUniClass.remove(corsoLaurea);
    }
}
