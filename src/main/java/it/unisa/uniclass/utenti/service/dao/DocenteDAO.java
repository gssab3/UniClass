package it.unisa.uniclass.utenti.service.dao;

import it.unisa.uniclass.utenti.model.Docente;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless(name = "DocenteDAO")
public class DocenteDAO implements DocenteRemote {

    @PersistenceContext(unitName="DBUniClassPU")
    private EntityManager emUniclass;

    @Override
    public Docente trovaDocenteUniClass(String matricola) {
        TypedQuery<Docente> query = emUniclass.createNamedQuery(Docente.TROVA_DOCENTE, Docente.class);
        query.setParameter("matricola", matricola);
        return query.getSingleResult();
    }

    @Override
    public List<Docente> trovaDocenteCorsoLaurea(String nomeCorsoLaurea) {
        TypedQuery<Docente> query = emUniclass.createNamedQuery(Docente.TROVA_DOCENTE_CORSOLAUREA, Docente.class);
        query.setParameter("nome", nomeCorsoLaurea);
        return query.getResultList();
    }

    @Override
    public List<Docente> trovaTuttiUniClass() {
        TypedQuery<Docente> query = emUniclass.createNamedQuery(Docente.TROVA_TUTTI, Docente.class);
        return query.getResultList();
    }

    @Override
    public Docente trovaEmailUniClass(String email) {
        TypedQuery<Docente> query = emUniclass.createNamedQuery(Docente.TROVA_EMAIL, Docente.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    @Override
    public void aggiungiDocente(Docente docente) {
        emUniclass.merge(docente);
    }

    @Override
    public void rimuoviDocente(Docente docente) {
        emUniclass.remove(docente);
    }
}
