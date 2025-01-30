package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.exceptions.AuthenticationException;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.PersonaleTA;
import it.unisa.uniclass.utenti.model.Utente;
import it.unisa.uniclass.utenti.service.dao.AccademicoDAO;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

/**
 * Classe di servizio per la gestione delle operazioni relative agli utenti.
 * Fornisce metodi per autenticare e recuperare utenti.
 */
@Stateless
public class UtenteService {

    /**
     * Recupera un utente dal database utilizzando la sua email e password.
     *
     * @param email L'email dell'utente da cercare.
     * @param password La password dell'utente da cercare.
     * @return L'oggetto Utente corrispondente all'email e alla password.
     * @throws AuthenticationException Se la password è errata.
     */
    public Utente retrieveByUserAndPassword(String email, String password) {
        try {
            PersonaleTAService personaleTAService = new PersonaleTAService();
            AccademicoService accademicoService = new AccademicoService();
            PersonaleTA personaleTA = (PersonaleTA) personaleTAService.trovaEmail(email);
            Accademico accademico = (Accademico) accademicoService.trovaEmailUniClass(email);
            if (personaleTA != null) {
                if (personaleTA.getPassword().equals(password)) {
                    return personaleTA;
                } else {
                    throw new AuthenticationException("Password errata");
                }
            } else if (accademico != null) {
                if (accademico.getPassword().equals(password)) {
                    return accademico;
                } else {
                    throw new AuthenticationException("Password errata");
                }
            }
            return null;
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Recupera un utente dal database utilizzando la sua email.
     *
     * @param email L'email dell'utente da cercare.
     * @return L'oggetto Utente corrispondente all'email.
     */
    public Utente retrieveByEmail(String email) {
        PersonaleTAService personaleTAService = new PersonaleTAService();
        AccademicoService accademicoService = new AccademicoService();
        PersonaleTA personaleTA = (PersonaleTA) personaleTAService.trovaEmail(email);
        Accademico accademico = (Accademico) accademicoService.trovaEmailUniClass(email);
        if (personaleTA != null) {
            return personaleTA;
        } else if (accademico != null) {
            return accademico;
        }
        return null;
    }
}