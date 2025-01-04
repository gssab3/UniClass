package it.unisa.uniclass.esami.service;

import it.unisa.uniclass.esami.model.AppelloDocente;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class AppelloDocenteDAO {
    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    public List<AppelloDocente> trovaDocente(String matricola) {
        TypedQuery<AppelloDocente> query = emUniClass.createNamedQuery(AppelloDocente.TROVA_DOCENTE, AppelloDocente.class);
        query.setParameter("matricola", matricola);
        return query.getResultList();
    }

    public List<AppelloDocente> trovaAppello(long id) {
        TypedQuery<AppelloDocente> query = emUniClass.createNamedQuery(AppelloDocente.TROVA_APPELLO, AppelloDocente.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public void aggiungiAppelloDocente(AppelloDocente appelloDocente) {
        emUniClass.merge(appelloDocente);
    }

    public void rimuoviAppelloDocente(AppelloDocente appelloDocente) {
        emUniClass.remove(appelloDocente);
    }
}
