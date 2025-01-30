package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

/**
 * Classe DAO per la gestione delle entità CorsoLaurea nel database.
 * Fornisce metodi per recuperare, aggiungere e rimuovere corsi di laurea.
 */
@Stateless(name = "CorsoLaureaDAO")
public class CorsoLaureaDAO implements CorsoLaureaRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    /**
     * Trova un corso di laurea nel database utilizzando il suo ID.
     *
     * @param id L'ID del corso di laurea da cercare.
     * @return L'oggetto CorsoLaurea corrispondente all'ID specificato.
     */
    @Override
    public CorsoLaurea trovaCorsoLaurea(long id) {
        TypedQuery<CorsoLaurea> query = emUniClass.createNamedQuery(CorsoLaurea.TROVA_CORSOLAUREA, CorsoLaurea.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Trova un corso di laurea nel database utilizzando il suo nome.
     *
     * @param nome Il nome del corso di laurea.
     * @return L'oggetto CorsoLaurea corrispondente al nome specificato.
     */
    @Override
    public CorsoLaurea trovaCorsoLaurea(String nome) {
        TypedQuery<CorsoLaurea> query = emUniClass.createNamedQuery(CorsoLaurea.TROVA_CORSOLAUREA_NOME, CorsoLaurea.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }

    /**
     * Recupera tutti i corsi di laurea presenti nel database.
     *
     * @return Una lista di tutti i corsi di laurea.
     */
    @Override
    public List<CorsoLaurea> trovaTutti() {
        TypedQuery<CorsoLaurea> query = emUniClass.createNamedQuery(CorsoLaurea.TROVA_TUTTI, CorsoLaurea.class);
        return query.getResultList();
    }

    /**
     * Aggiunge o aggiorna un corso di laurea nel database.
     *
     * @param corsoLaurea Il corso di laurea da aggiungere o aggiornare.
     */
    @Override
    public void aggiungiCorsoLaurea(CorsoLaurea corsoLaurea) {
        emUniClass.merge(corsoLaurea);
    }

    /**
     * Rimuove un corso di laurea dal database.
     *
     * @param corsoLaurea Il corso di laurea da rimuovere.
     */
    @Override
    public void rimuoviCorsoLaurea(CorsoLaurea corsoLaurea) {
        emUniClass.remove(corsoLaurea);
    }
}
