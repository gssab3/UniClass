package it.unisa.uniclass.utenti.service.dao;

import it.unisa.uniclass.utenti.model.Coordinatore;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

/**
 * Classe DAO per la gestione delle entità Coordinatore nel database.
 * Fornisce metodi per recuperare, aggiungere e rimuovere coordinatori.
 */
@Stateless(name = "CoordinatoreDAO")
public class CoordinatoreDAO implements CoordinatoreRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    /**
     * Trova un coordinatore nel database utilizzando la sua email.
     *
     * @param email L'email del coordinatore da cercare.
     * @return L'oggetto Coordinatore corrispondente all'email.
     */
    @Override
    public Coordinatore trovaCoordinatoreEmailUniclass(String email) {
        TypedQuery<Coordinatore> query = emUniClass.createNamedQuery(Coordinatore.TROVA_EMAIL, Coordinatore.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    /**
     * Trova un coordinatore nel database utilizzando la sua matricola.
     *
     * @param matricola La matricola del coordinatore da cercare.
     * @return L'oggetto Coordinatore corrispondente alla matricola.
     */
    @Override
    public Coordinatore trovaCoordinatoreUniClass(String matricola) {
        TypedQuery<Coordinatore> query = emUniClass.createQuery(Coordinatore.TROVA_COORDINATORE, Coordinatore.class);
        query.setParameter("matricola", matricola);
        return query.getSingleResult();
    }

    /**
     * Trova i coordinatori nel database associati a un corso di laurea tramite il nome del corso.
     *
     * @param nomeCorsoLaurea Il nome del corso di laurea di cui trovare i coordinatori.
     * @return Una lista di oggetti Coordinatore associati al corso di laurea.
     */
    @Override
    public List<Coordinatore> trovaCoordinatoriCorsoLaurea(String nomeCorsoLaurea) {
        TypedQuery<Coordinatore> query = emUniClass.createQuery(Coordinatore.TROVA_COORDINATORE, Coordinatore.class);
        query.setParameter("nomeCorsoLaurea", nomeCorsoLaurea);
        return query.getResultList();
    }

    /**
     * Recupera tutti i coordinatori presenti nel database.
     *
     * @return Una lista di tutti i coordinatori.
     */
    @Override
    public List<Coordinatore> trovaTutti() {
        TypedQuery<Coordinatore> query = emUniClass.createNamedQuery(Coordinatore.TROVA_TUTTI, Coordinatore.class);
        return query.getResultList();
    }

    /**
     * Aggiunge o aggiorna un coordinatore nel database.
     *
     * @param coordinatore Il coordinatore da aggiungere o aggiornare.
     */
    @Override
    public void aggiungiCoordinatore(Coordinatore coordinatore) {
        emUniClass.merge(coordinatore);
    }

    /**
     * Rimuove un coordinatore dal database.
     *
     * @param coordinatore Il coordinatore da rimuovere.
     */
    @Override
    public void rimuoviCoordinatore(Coordinatore coordinatore) {
        emUniClass.remove(coordinatore);
    }
}