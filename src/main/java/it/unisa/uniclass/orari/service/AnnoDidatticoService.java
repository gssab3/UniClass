package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.AnnoDidattico;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import java.util.List;

@Stateless
public class AnnoDidatticoService {
    @EJB
    private AnnoDidatticoDAO annoDidatticoDAO;

    public List<AnnoDidattico> retrieveAnniCorso(CorsoLaurea corsoLaurea){
        try{
            return annoDidatticoDAO.trovaAnniCorsoLaurea(corsoLaurea);
        }catch (NoResultException e){
            return null;
        }
    }
}
