package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.conversazioni.model.Conversazione;
import it.unisa.uniclass.utenti.model.Accademico;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import java.util.Collection;
import java.util.List;

@Stateless
public class AccademicoService {
    @EJB
    private AccademicoDAO dao;

    public Accademico trovaAccademicoUniClass(String matricola) {
        try {
            return dao.trovaAccademicoUniClass(matricola);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<Accademico> trovaTuttiUniClass() {
        return dao.trovaTuttiUniClass();
    }

    public Accademico trovaAccademicoUniversity(String matricola) {
        try {
            return dao.trovaAccademicoUniversity(matricola);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<Accademico> trovaTuttiUniversity() {
        return dao.trovaTuttiUniversity();
    }

    public Accademico trovaEmailUniClass(String email) {
        try {
            return dao.trovaEmailUniClass(email);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public Accademico trovaEmailUniversity(String email) {
        try {
            return dao.trovaEmailUniversity(email);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public void aggiungiAccademico(Accademico accademico) {
        dao.aggiungiAccademico(accademico);
    }

    public void rimuoviAccademico(Accademico accademico) {
        dao.rimuoviAccademico(accademico);
    }
}
