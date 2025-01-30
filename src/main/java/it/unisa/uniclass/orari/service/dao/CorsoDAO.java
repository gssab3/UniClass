package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.Corso;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

/**
 * Classe DAO per la gestione delle entità Corso nel database.
 * Fornisce metodi per recuperare, aggiungere e rimuovere corsi.
 */
@Stateless(name = "CorsoDAO")
public class CorsoDAO implements CorsoRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    /**
     * Trova un corso nel database utilizzando il suo ID.
     *
     * @param id L'ID del corso da cercare.
     * @return L'oggetto Corso corrispondente all'ID specificato.
     */
    @Override
    public Corso trovaCorso(long id) {
        TypedQuery<Corso> query = emUniClass.createNamedQuery(Corso.TROVA_CORSO, Corso.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Trova tutti i corsi associati a un determinato corso di laurea.
     *
     * @param nomeCorsoLaurea Il nome del corso di laurea.
     * @return Una lista di corsi appartenenti al corso di laurea specificato.
     */
    @Override
    public List<Corso> trovaCorsiCorsoLaurea(String nomeCorsoLaurea) {
        TypedQuery<Corso> query = emUniClass.createNamedQuery(Corso.TROVA_CORSI_CORSOLAUREA, Corso.class);
        query.setParameter("nomeCorsoLaurea", nomeCorsoLaurea);
        return query.getResultList();
    }

    /**
     * Recupera tutti i corsi presenti nel database.
     *
     * @return Una lista di tutti i corsi.
     */
    @Override
    public List<Corso> trovaTutti() {
        TypedQuery<Corso> query = emUniClass.createNamedQuery(Corso.TROVA_TUTTE, Corso.class);
        return query.getResultList();
    }

    /**
     * Aggiunge o aggiorna un corso nel database.
     *
     * @param corso Il corso da aggiungere o aggiornare.
     */
    @Override
    public void aggiungiCorso(Corso corso) {
        emUniClass.merge(corso);
    }

    /**
     * Rimuove un corso dal database.
     *
     * @param corso Il corso da rimuovere.
     */
    @Override
    public void rimuoviCorso(Corso corso) {
        emUniClass.remove(corso);
    }
}
