package it.unisa.uniclass.orari.service.dao;
import it.unisa.uniclass.orari.model.AnnoDidattico;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless(name = "AnnoDidatticoDAO")
public class AnnoDidatticoDAO implements AnnoDidatticoRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @Override
    public List<AnnoDidattico> trovaAnno(String anno) {
        TypedQuery<AnnoDidattico> query = emUniClass.createNamedQuery(AnnoDidattico.TROVA_ANNO, AnnoDidattico.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    @Override
    public AnnoDidattico trovaId(int id) {
        TypedQuery<AnnoDidattico> query = emUniClass.createNamedQuery(AnnoDidattico.TROVA_ID, AnnoDidattico.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<AnnoDidattico> trovaTutti() {
        TypedQuery<AnnoDidattico> query = emUniClass.createNamedQuery(AnnoDidattico.TROVA_TUTTI, AnnoDidattico.class);
        return query.getResultList();
    }



    @Override
    public void aggiungiAnno(AnnoDidattico annoDidattico) {
        emUniClass.merge(annoDidattico);
    }

    @Override
    public void rimuoviAnno(AnnoDidattico annoDidattico) {
        emUniClass.remove(annoDidattico);
    }
}
