package it.unisa.uniclass.utenti.service.dao;

import it.unisa.uniclass.utenti.model.Docente;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

/**
 * Classe DAO per la gestione delle entità Docente nel database.
 * Fornisce metodi per recuperare, aggiungere e rimuovere docenti.
 */
@Stateless(name = "DocenteDAO")
public class DocenteDAO implements DocenteRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniclass;

    /**
     * Trova un docente nel database utilizzando la sua matricola.
     *
     * @param matricola La matricola del docente da cercare.
     * @return L'oggetto Docente corrispondente alla matricola.
     */
    @Override
    public Docente trovaDocenteUniClass(String matricola) {
        TypedQuery<Docente> query = emUniclass.createNamedQuery(Docente.TROVA_DOCENTE, Docente.class);
        query.setParameter("matricola", matricola);
        return query.getSingleResult();
    }

    /**
     * Trova i docenti nel database associati a un corso di laurea tramite il nome del corso.
     *
     * @param nomeCorsoLaurea Il nome del corso di laurea di cui trovare i docenti.
     * @return Una lista di oggetti Docente associati al corso di laurea.
     */
    @Override
    public List<Docente> trovaDocenteCorsoLaurea(String nomeCorsoLaurea) {
        TypedQuery<Docente> query = emUniclass.createNamedQuery(Docente.TROVA_DOCENTE_CORSOLAUREA, Docente.class);
        query.setParameter("nome", nomeCorsoLaurea);
        return query.getResultList();
    }

    /**
     * Recupera tutti i docenti presenti nel database.
     *
     * @return Una lista di tutti i docenti.
     */
    @Override
    public List<Docente> trovaTuttiUniClass() {
        TypedQuery<Docente> query = emUniclass.createNamedQuery(Docente.TROVA_TUTTI, Docente.class);
        return query.getResultList();
    }

    /**
     * Trova un docente nel database utilizzando la sua email.
     *
     * @param email L'email del docente da cercare.
     * @return L'oggetto Docente corrispondente all'email.
     */
    @Override
    public Docente trovaEmailUniClass(String email) {
        TypedQuery<Docente> query = emUniclass.createNamedQuery(Docente.TROVA_EMAIL, Docente.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    /**
     * Aggiunge o aggiorna un docente nel database.
     *
     * @param docente Il docente da aggiungere o aggiornare.
     */
    @Override
    public void aggiungiDocente(Docente docente) {
        emUniclass.merge(docente);
    }

    /**
     * Rimuove un docente dal database.
     *
     * @param docente Il docente da rimuovere.
     */
    @Override
    public void rimuoviDocente(Docente docente) {
        emUniclass.remove(docente);
    }
}