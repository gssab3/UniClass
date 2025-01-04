package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RestoDAO {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @PersistenceContext(unitName = "DBUniversityPU")
    private EntityManager emUniversity;

    public List<Resto> trovaRestiCorsoLaurea(CorsoLaurea corsoLaurea){
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTI_CORSO, Resto.class);
        query.setParameter("nome", corsoLaurea.getNome());
        return  query.getResultList();
    }

    public List<Resto> trovaRestiCorsoLaurea(String nomeCorsoLaurea){
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTI_CORSO, Resto.class);
        query.setParameter("nome", nomeCorsoLaurea);
        return  query.getResultList();
    }

    public List<Resto> trovaResto(String nomeResto){
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTO_NOME, Resto.class);
        query.setParameter("nome", nomeResto);
        return  query.getResultList();
    }

    public Resto trovaResto(long id) {
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTO, Resto.class);
        query.setParameter("id", id);
        return  query.getSingleResult();
    }

    public List<Resto> trovaRestoNomeCorso(String nomeResto, CorsoLaurea corso){
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTO_NOME_CORSO, Resto.class);
        query.setParameter("nome", nomeResto);
        query.setParameter("nomeCorso", corso.getNome());
        return  query.getResultList();
    }

    public List<Resto> trovaRestoNomeCorso(String nomeResto, String nomeCorso){
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTO_NOME_CORSO, Resto.class);
        query.setParameter("nome", nomeResto);
        query.setParameter("nomeCorso", nomeCorso);
        return  query.getResultList();
    }

    public void aggiungiResto(Resto resto){
        emUniClass.merge(resto);
    }

    public void rimuoviResto(Resto resto){
        emUniClass.remove(resto);
    }
}
