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
    private RestoDAO dao;

    public List<Resto> trovaRestiCorsoLaurea(CorsoLaurea corsoLaurea) {
        return dao.trovaRestiCorsoLaurea(corsoLaurea);
    }

    public List<Resto> trovaRestiCorsoLaurea(String nomeCorsoLaurea){
        return dao.trovaRestiCorsoLaurea(nomeCorsoLaurea);
    }

    public List<Resto> trovaResto(String nomeResto) {
        return dao.trovaResto(nomeResto);
    }

    public Resto trovaResto(long id) {
        try {
            return dao.trovaResto(id);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<Resto> trovaRestoNomeCorso(String nomeResto, CorsoLaurea corso) {
        return dao.trovaRestoNomeCorso(nomeResto, corso);
    }

    public List<Resto> trovaRestoNomeCorso(String nomeResto, String nomeCorso) {
        return dao.trovaRestoNomeCorso(nomeResto, nomeCorso);
    }

    public void aggiungiResto(Resto resto) {
        if(trovaResto(resto.getId()) == null) {
            dao.aggiungiResto(resto);
        }
    }

    public void rimuoviResto(Resto resto) {
        if(trovaResto(resto.getId()) != null) {
            dao.rimuoviResto(resto);
        }
    }
}
