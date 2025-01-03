package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

@Stateless
public class CorsoLaureaService {
    @EJB
    private CorsoLaureaDAO corsoLaureaDAO;

    public CorsoLaurea retriveCorsoNome(String corsoLaurea){
        try {
            return corsoLaureaDAO.trovaCorsoLaurea(corsoLaurea);
        } catch (NoResultException e) {
            return null;
        }
    }
}
