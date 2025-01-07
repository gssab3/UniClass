package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.utenti.model.Coordinatore;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.*;

import java.util.List;

@Stateless(name = "CoordinatoreDAO")
public class CoordinatoreDAO implements CoordinatoreRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @Override
    public Coordinatore trovaCoordinatoreEmailUniclass(String email) {
        TypedQuery<Coordinatore> query = emUniClass.createNamedQuery(Coordinatore.TROVA_EMAIL, Coordinatore.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    @Override
    public Coordinatore trovaCoordinatoreUniClass(String matricola) {
        TypedQuery<Coordinatore> query = emUniClass.createQuery(Coordinatore.TROVA_COORDINATORE, Coordinatore.class);
        query.setParameter("matricola", matricola);
        return query.getSingleResult();
    }

    @Override
    public List<Coordinatore> trovaCoordinatoriCorsoLaurea(String nomeCorsoLaurea) {
        TypedQuery<Coordinatore> query = emUniClass.createQuery(Coordinatore.TROVA_COORDINATORE, Coordinatore.class);
        query.setParameter("nomeCorsoLaurea", nomeCorsoLaurea);
        return query.getResultList();
    }

    @Override
    public List<Coordinatore> trovaTutti() {
        TypedQuery<Coordinatore> query = emUniClass.createNamedQuery(Coordinatore.TROVA_TUTTI, Coordinatore.class);
        return query.getResultList();
    }

    @Override
    public void aggiungiCoordinatore(Coordinatore coordinatore) {
        emUniClass.merge(coordinatore);
    }

    @Override
    public void rimuoviCoordinatore(Coordinatore coordinatore) {
        emUniClass.remove(coordinatore);
    }
}
