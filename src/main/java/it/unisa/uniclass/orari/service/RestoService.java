package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import java.util.List;

@Stateless
public class RestoService {
    @EJB
    private RestoDAO restoDAO;

    public List<Resto> retrieveRestiCorso(CorsoLaurea corsoLaurea){
        try{
            return restoDAO.trovaRestiCorsoLaurea(corsoLaurea);
        }
        catch (NoResultException e){
            return null;
        }
    }
}
