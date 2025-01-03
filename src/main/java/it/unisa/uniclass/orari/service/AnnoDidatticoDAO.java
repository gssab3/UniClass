package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.AnnoDidattico;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class AnnoDidatticoDAO {
    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @PersistenceContext(unitName = "DBUniversityPU")
    private EntityManager emUniversity;

    public List<AnnoDidattico> trovaAnniCorsoLaurea(CorsoLaurea corsoLaurea){
        TypedQuery<AnnoDidattico> query = emUniClass.createNamedQuery(AnnoDidattico.TROVA_ANNI_CORSO, AnnoDidattico.class);
        query.setParameter("nome", corsoLaurea.getNome());
        return query.getResultList();
    }

}
