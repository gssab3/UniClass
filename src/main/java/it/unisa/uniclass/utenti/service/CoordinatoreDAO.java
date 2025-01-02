package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.exceptions.NotFoundUserException;
import it.unisa.uniclass.utenti.model.Coordinatore;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class CoordinatoreDAO {
    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @PersistenceContext(unitName = "DBUniversityPU")
    private EntityManager emUniversity;

    public Coordinatore trovaCoordinatoreUniversity(String matricola) {
        TypedQuery<Coordinatore> query = emUniversity.createQuery(Coordinatore.TROVA_COORDINATORE, Coordinatore.class);
        query.setParameter("matricola", matricola);
        return query.getSingleResult();
    }

    public Coordinatore trovaCoordinatoreUniClass(String matricola) {
        TypedQuery<Coordinatore> query = emUniClass.createQuery(Coordinatore.TROVA_COORDINATORE, Coordinatore.class);
        query.setParameter("matricola", matricola);
        return query.getSingleResult();
    }

    public List<Coordinatore> trovaCoordinatoriCorsoLaurea(String nomeCorsoLaurea) {
        TypedQuery<Coordinatore> query = emUniClass.createQuery(Coordinatore.TROVA_COORDINATORE, Coordinatore.class);
        query.setParameter("nomeCorsoLaurea", nomeCorsoLaurea);
        return query.getResultList();
    }

    public List<Coordinatore> trovaTutti() {
        TypedQuery<Coordinatore> query = emUniClass.createNamedQuery(Coordinatore.TROVA_TUTTI, Coordinatore.class);
        return query.getResultList();
    }

    public void aggiungiCoordinatore(Coordinatore coordinatore) throws NotFoundUserException {
        if(trovaCoordinatoreUniversity(coordinatore.getMatricola()) != null) {
            emUniClass.persist(coordinatore);
        }
        else
            throw new NotFoundUserException("Coordinatore non trovato");
    }

    public void rimuoviCoordinatore(Coordinatore coordinatore) {
        emUniClass.remove(coordinatore);
    }
}