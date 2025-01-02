package it.unisa.uniclass.esami.service;

import it.unisa.uniclass.esami.model.Appello;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class AppelloDAO {
    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    public Appello trovaAppello(long id) {
        TypedQuery<Appello> query = emUniClass.createNamedQuery(Appello.TROVA_APPELLO, Appello.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Appello> trovaAppelliAula(String nomeaula) {
        TypedQuery<Appello> query = emUniClass.createNamedQuery(Appello.TROVA_APPELLO_AULA, Appello.class);
        query.setParameter("nomeAula", nomeaula);
        return query.getResultList();
    }

    public List<Appello> trovaAppelliCorso(String nomeCorso) {
        TypedQuery<Appello> query = emUniClass.createNamedQuery(Appello.TROVA_APPELLO_CORSO, Appello.class);
        query.setParameter("corso", nomeCorso);
        return query.getResultList();
    }

    public List<Appello> trovaTutti() {
        TypedQuery<Appello> query = emUniClass.createNamedQuery(Appello.TROVA_TUTTI, Appello.class);
        return query.getResultList();
    }

    public void aggiungiAppello(Appello appello) {
        emUniClass.persist(appello);
    }

    public void rimuoviAppello(Appello appello) {
        emUniClass.remove(appello);
    }
}
