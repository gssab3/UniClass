package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.AnnoDidattico;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.NoResultException;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class AnnoDidatticoService {
    @EJB
    private AnnoDidatticoDAO dao;

    public List<AnnoDidattico> trovaAnno(int anno) {
        return dao.trovaAnno(anno);
    }

    public AnnoDidattico trovaId(int id) {
        try {
            return dao.trovaId(id);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<AnnoDidattico> trovaTutti() {
        return dao.trovaTutti();
    }

    public void aggiungiAnno(AnnoDidattico annoDidattico) {
        dao.aggiungiAnno(annoDidattico);
    }

    public void rimuoviAnno(AnnoDidattico annoDidattico) {
        dao.rimuoviAnno(annoDidattico);
    }

    public List<AnnoDidattico> trovaPerCorsoLaurea(CorsoLaurea corso) {
        List<AnnoDidattico> anni = dao.trovaTutti();
        List<AnnoDidattico> results = new ArrayList<AnnoDidattico>();
        for(AnnoDidattico annoDidattico : anni) {
            if(annoDidattico.getCorsiLaurea().contains(corso))
                results.add(annoDidattico);
        }
        return results;
    }
}
