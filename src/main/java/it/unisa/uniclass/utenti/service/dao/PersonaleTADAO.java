package it.unisa.uniclass.utenti.service.dao;

import it.unisa.uniclass.utenti.model.PersonaleTA;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

/**
 * Classe DAO per la gestione delle entità PersonaleTA nel database.
 * Fornisce metodi per recuperare, aggiungere e rimuovere personale tecnico-amministrativo.
 */
@Stateless(name = "PersonaleTADAO")
public class PersonaleTADAO implements PersonaleTARemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    /**
     * Trova un membro del personale tecnico-amministrativo nel database utilizzando il suo ID.
     *
     * @param id L'ID del personale da cercare.
     * @return L'oggetto PersonaleTA corrispondente all'ID.
     */
    @Override
    public PersonaleTA trovaPersonale(long id) {
        TypedQuery<PersonaleTA> query = emUniClass.createNamedQuery(PersonaleTA.TROVA_PERSONALE, PersonaleTA.class);
        query.setParameter("id", id);
        return (PersonaleTA) query.getSingleResult();
    }

    /**
     * Recupera tutti i membri del personale tecnico-amministrativo presenti nel database.
     *
     * @return Una lista di tutti i membri del personale tecnico-amministrativo.
     */
    @Override
    public List<PersonaleTA> trovaTutti() {
        TypedQuery<PersonaleTA> query = emUniClass.createNamedQuery(PersonaleTA.TROVA_TUTTI, PersonaleTA.class);
        return query.getResultList();
    }

    /**
     * Trova un membro del personale tecnico-amministrativo nel database utilizzando la sua email.
     *
     * @param email L'email del personale da cercare.
     * @return L'oggetto PersonaleTA corrispondente all'email.
     */
    @Override
    public PersonaleTA trovaEmail(String email) {
        try {
            TypedQuery<PersonaleTA> query = emUniClass.createNamedQuery(PersonaleTA.TROVA_EMAIL, PersonaleTA.class);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

    /**
     * Trova un membro del personale tecnico-amministrativo nel database utilizzando la sua email e password.
     *
     * @param email L'email del personale da cercare.
     * @param password La password del personale da cercare.
     * @return L'oggetto PersonaleTA corrispondente all'email e alla password.
     */
    @Override
    public PersonaleTA trovaEmailPassword(String email, String password) {
        try {
            TypedQuery<PersonaleTA> query = emUniClass.createNamedQuery(PersonaleTA.TROVA_EMAIL_PASSWORD, PersonaleTA.class);
            query.setParameter("email", email);
            query.setParameter("password", password);
            return query.getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            return null;
        }
    }

    /**
     * Aggiunge o aggiorna un membro del personale tecnico-amministrativo nel database.
     *
     * @param personaleTA Il personale da aggiungere o aggiornare.
     */
    @Override
    public void aggiungiPersonale(PersonaleTA personaleTA) {
        emUniClass.merge(personaleTA);
    }

    /**
     * Rimuove un membro del personale tecnico-amministrativo dal database.
     *
     * @param personaleTA Il personale da rimuovere.
     */
    @Override
    public void rimuoviPersonale(PersonaleTA personaleTA) {
        emUniClass.remove(personaleTA);
    }
}