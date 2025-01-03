package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class RestoDAO {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @PersistenceContext(unitName = "DBUniversityPU")
    private EntityManager emUniversity;

    public List<Resto> trovaRestiCorsoLaurea(CorsoLaurea corsoLaurea){
        TypedQuery<Resto> query = emUniClass.createNamedQuery(Resto.TROVA_RESTI_CORSO, Resto.class);
        query.setParameter("nome", corsoLaurea.getNome());
        return  query.getResultList();
    }

}
