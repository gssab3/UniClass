package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.exceptions.AuthenticationException;
import it.unisa.uniclass.utenti.model.Accademico;
import it.unisa.uniclass.utenti.model.PersonaleTA;
import it.unisa.uniclass.utenti.model.Tipo;
import it.unisa.uniclass.utenti.model.Utente;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

@Stateless
public class UtenteService {
    @EJB
    private AccademicoDAO accademicoDAO;

    @EJB
    private PersonaleTAService personaleTAService;


    public Utente retrieveByUserAndPassword(String email, String password) {
        PersonaleTA personaleTA= (PersonaleTA) personaleTAService.trovaEmail(email);
        Accademico accademico = (Accademico) accademicoDAO.trovaEmailUniClass(email);
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
}
