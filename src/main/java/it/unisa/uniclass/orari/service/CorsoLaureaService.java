package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import java.util.List;

@Stateless
public class CorsoLaureaService {
    @EJB
    private CorsoLaureaDAO corsoLaureaDAO;

    public CorsoLaurea trovaCorsoLaurea(long id) {
        try {
            return corsoLaureaDAO.trovaCorsoLaurea(id);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public CorsoLaurea trovaCorsoLaurea(String nome) {
        try {
            return corsoLaureaDAO.trovaCorsoLaurea(nome);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<CorsoLaurea> trovaTutti() {
        return corsoLaureaDAO.trovaTutti();
    }

    public void aggiungiCorsoLaurea(CorsoLaurea corsoLaurea) {
        if(trovaCorsoLaurea(corsoLaurea.getNome()) == null) {
            corsoLaureaDAO.aggiungiCorsoLaurea(corsoLaurea);
        }
    }

    public void rimuoviCorsoLaurea(CorsoLaurea corsoLaurea) {
        corsoLaureaDAO.rimuoviCorsoLaurea(corsoLaurea);
    }
}
