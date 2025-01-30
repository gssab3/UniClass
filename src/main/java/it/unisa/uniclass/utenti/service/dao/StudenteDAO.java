package it.unisa.uniclass.utenti.service.dao;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.utenti.model.Studente;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

/**
 * Classe DAO per la gestione delle entità Studente nel database.
 * Fornisce metodi per recuperare, aggiungere e rimuovere studenti.
 */
@Stateless(name = "StudenteDAO")
public class StudenteDAO implements StudenteRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    /**
     * Trova uno studente nel database utilizzando la sua matricola.
     *
     * @param matricola La matricola dello studente da cercare.
     * @return L'oggetto Studente corrispondente alla matricola.
     */
    @Override
    public Studente trovaStudenteUniClass(String matricola) {
        TypedQuery<Studente> query = emUniClass.createNamedQuery(Studente.TROVA_STUDENTE, Studente.class);
        query.setParameter("matricola", matricola);
        return (Studente) query.getSingleResult();
    }

    /**
     * Trova gli studenti nel database associati a un corso di laurea.
     *
     * @param corsoLaurea Il corso di laurea di cui trovare gli studenti.
     * @return Una lista di oggetti Studente associati al corso di laurea.
     */
    @Override
    public List<Studente> trovaStudentiCorso(CorsoLaurea corsoLaurea) {
        TypedQuery<Studente> query = emUniClass.createNamedQuery(Studente.TROVA_STUDENTI_CORSO, Studente.class);
        query.setParameter("corso", corsoLaurea.getNome());
        return query.getResultList();
    }

    /**
     * Recupera tutti gli studenti presenti nel database.
     *
     * @return Una lista di tutti gli studenti.
     */
    @Override
    public List<Studente> trovaTuttiUniClass() {
        TypedQuery<Studente> query = emUniClass.createNamedQuery(Studente.TROVA_TUTTI, Studente.class);
        return query.getResultList();
    }

    /**
     * Trova uno studente nel database utilizzando la sua email.
     *
     * @param email L'email dello studente da cercare.
     * @return L'oggetto Studente corrispondente all'email.
     */
    @Override
    public Studente trovaStudenteEmailUniClass(String email) {
        TypedQuery<Studente> query = emUniClass.createNamedQuery(Studente.TROVA_EMAIL, Studente.class);
        query.setParameter("email", email);
        return (Studente) query.getSingleResult();
    }

    /**
     * Aggiunge o aggiorna uno studente nel database.
     *
     * @param studente Lo studente da aggiungere o aggiornare.
     */
    @Override
    public void aggiungiStudente(Studente studente) {
        emUniClass.merge(studente);
    }

    /**
     * Rimuove uno studente dal database.
     *
     * @param studente Lo studente da rimuovere.
     */
    @Override
    public void rimuoviStudente(Studente studente) {
        emUniClass.remove(studente);
    }
}