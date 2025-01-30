package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

/**
 * Classe DAO per la gestione delle entità Resto nel database.
 * Fornisce metodi per recuperare, aggiungere e rimuovere resti.
 */
@Stateless(name = "RestoDAO")
public class RestoDAO implements RestoRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    /**
     * Trova i resti nel database associati a un corso di laurea.
     *
     * @param corsoLaurea Il corso di laurea di cui trovare i resti.
     * @return Una lista di oggetti Resto associati al corso di laurea.
     */
    @Override
    public List<Resto> trovaRestiCorsoLaurea(CorsoLaurea corsoLaurea) {
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTI_CORSO, Resto.class);
        query.setParameter("nome", corsoLaurea.getNome());
        return query.getResultList();
    }

    /**
     * Trova i resti nel database associati a un corso di laurea tramite il nome del corso.
     *
     * @param nomeCorsoLaurea Il nome del corso di laurea di cui trovare i resti.
     * @return Una lista di oggetti Resto associati al corso di laurea.
     */
    @Override
    public List<Resto> trovaRestiCorsoLaurea(String nomeCorsoLaurea) {
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTI_CORSO, Resto.class);
        query.setParameter("nome", nomeCorsoLaurea);
        return query.getResultList();
    }

    /**
     * Trova i resti nel database tramite il nome del resto.
     *
     * @param nomeResto Il nome del resto da cercare.
     * @return Una lista di oggetti Resto corrispondenti al nome.
     */
    @Override
    public List<Resto> trovaResto(String nomeResto) {
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTO_NOME, Resto.class);
        query.setParameter("nome", nomeResto);
        return query.getResultList();
    }

    /**
     * Trova un resto nel database utilizzando il suo ID.
     *
     * @param id L'ID del resto da cercare.
     * @return L'oggetto Resto corrispondente all'ID.
     */
    @Override
    public Resto trovaResto(long id) {
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTO, Resto.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Trova un resto nel database associato a un corso di laurea tramite il nome del resto e del corso.
     *
     * @param nomeResto Il nome del resto da cercare.
     * @param corso Il corso di laurea associato al resto.
     * @return L'oggetto Resto corrispondente al nome e al corso.
     */
    @Override
    public Resto trovaRestoNomeCorso(String nomeResto, CorsoLaurea corso) {
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTO_NOME_CORSO, Resto.class);
        query.setParameter("nome", nomeResto);
        query.setParameter("nomeCorso", corso.getNome());
        return query.getSingleResult();
    }

    /**
     * Trova un resto nel database associato a un corso di laurea tramite il nome del resto e del corso.
     *
     * @param nomeResto Il nome del resto da cercare.
     * @param nomeCorso Il nome del corso di laurea associato al resto.
     * @return L'oggetto Resto corrispondente al nome e al corso.
     */
    @Override
    public Resto trovaRestoNomeCorso(String nomeResto, String nomeCorso) {
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTO_NOME_CORSO, Resto.class);
        query.setParameter("nome", nomeResto);
        query.setParameter("nomeCorso", nomeCorso);
        return query.getSingleResult();
    }

    /**
     * Aggiunge o aggiorna un resto nel database.
     *
     * @param resto Il resto da aggiungere o aggiornare.
     */
    @Override
    public void aggiungiResto(Resto resto) {
        emUniClass.merge(resto);
    }

    /**
     * Rimuove un resto dal database.
     *
     * @param resto Il resto da rimuovere.
     */
    @Override
    public void rimuoviResto(Resto resto) {
        emUniClass.remove(resto);
    }
}