package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless(name = "RestoDAO")
public class RestoDAO implements RestoRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @Override
    public List<Resto> trovaRestiCorsoLaurea(CorsoLaurea corsoLaurea){
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTI_CORSO, Resto.class);
        query.setParameter("nome", corsoLaurea.getNome());
        return  query.getResultList();
    }

    @Override
    public List<Resto> trovaRestiCorsoLaurea(String nomeCorsoLaurea){
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTI_CORSO, Resto.class);
        query.setParameter("nome", nomeCorsoLaurea);
        return  query.getResultList();
    }

    @Override
    public List<Resto> trovaResto(String nomeResto){
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTO_NOME, Resto.class);
        query.setParameter("nome", nomeResto);
        return  query.getResultList();
    }

    @Override
    public Resto trovaResto(long id) {
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTO, Resto.class);
        query.setParameter("id", id);
        return  query.getSingleResult();
    }

    @Override
    public Resto trovaRestoNomeCorso(String nomeResto, CorsoLaurea corso){
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTO_NOME_CORSO, Resto.class);
        query.setParameter("nome", nomeResto);
        query.setParameter("nomeCorso", corso.getNome());
        return  query.getSingleResult();
    }

    @Override
    public Resto trovaRestoNomeCorso(String nomeResto, String nomeCorso){
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTO_NOME_CORSO, Resto.class);
        query.setParameter("nome", nomeResto);
        query.setParameter("nomeCorso", nomeCorso);
        return  query.getSingleResult();
    }

    @Override
    public void aggiungiResto(Resto resto){
        emUniClass.merge(resto);
    }

    @Override
    public void rimuoviResto(Resto resto){
        emUniClass.remove(resto);
    }
}
