package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.exceptions.AuthenticationException;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.PersonaleTA;
import it.unisa.uniclass.utenti.model.Utente;
import it.unisa.uniclass.utenti.service.dao.AccademicoDAO;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

@Stateless
public class UtenteService {

    public Utente retrieveByUserAndPassword(String email, String password) {
        try {
            PersonaleTAService personaleTAService = new PersonaleTAService();
            AccademicoService accademicoService = new AccademicoService();
            PersonaleTA personaleTA= (PersonaleTA) personaleTAService.trovaEmail(email);
            Accademico accademico = (Accademico) accademicoService.trovaEmailUniClass(email);
            if(personaleTA != null) {
                if(personaleTA.getPassword().equals(password)) {
                    return personaleTA;
                }
                else {
                    throw new AuthenticationException("Password errata");
                }
            }
            else if(accademico != null) {
                if(accademico.getPassword().equals(password)) {
                    return accademico;
                }
                else {
                    throw new AuthenticationException("Password errata");
                }
            }
            return null;
        }
        catch(NoResultException e) {
            return null;
        }
    }
}
