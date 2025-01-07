package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.common.config.database.qualifier.UniclassDB;
import it.unisa.uniclass.orari.model.AnnoDidattico;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Typed;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class AnnoDidatticoDAO {
    @Inject
    @UniclassDB
    private EntityManager emUniClass;

    public List<AnnoDidattico> trovaAnno(int anno) {
        TypedQuery<AnnoDidattico> query = emUniClass.createNamedQuery(AnnoDidattico.TROVA_ANNO, AnnoDidattico.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    public AnnoDidattico trovaId(int id) {
        TypedQuery<AnnoDidattico> query = emUniClass.createNamedQuery(AnnoDidattico.TROVA_ID, AnnoDidattico.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<AnnoDidattico> trovaTutti() {
        TypedQuery<AnnoDidattico> query = emUniClass.createNamedQuery(AnnoDidattico.TROVA_TUTTI, AnnoDidattico.class);
        return query.getResultList();
    }

    public void aggiungiAnno(AnnoDidattico annoDidattico) {
        emUniClass.merge(annoDidattico);
    }

    public void rimuoviAnno(AnnoDidattico annoDidattico) {
        emUniClass.remove(annoDidattico);
    }
}
