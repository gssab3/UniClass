package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.config.database.qualifier.UniclassDB;
import it.unisa.uniclass.common.config.database.qualifier.UniversityDB;
import it.unisa.uniclass.common.exceptions.NotFoundUserException;
import it.unisa.uniclass.utenti.model.Accademico;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class AccademicoDAO {
    @Inject
    @UniclassDB
    private EntityManager emUniClass;

    @Inject
    @UniversityDB
    private EntityManager emUniversity;


    public Accademico trovaAccademicoUniClass(String matricola){
        TypedQuery<Accademico> query = emUniClass.createNamedQuery(Accademico.TROVA_ACCADEMICO, Accademico.class);
        query.setParameter("matricola", matricola);
        return query.getSingleResult();
    }

    public List<Accademico> trovaTuttiUniClass() {
        TypedQuery<Accademico> query = emUniClass.createNamedQuery(Accademico.TROVA_TUTTI, Accademico.class);
        return query.getResultList();
    }

    public Accademico trovaAccademicoUniversity(String matricola){
        TypedQuery<Accademico> query = emUniversity.createNamedQuery(Accademico.TROVA_ACCADEMICO, Accademico.class);
        query.setParameter("matricola", matricola);
        return query.getSingleResult();
    }

    public List<Accademico> trovaTuttiUniversity() {
        TypedQuery<Accademico> query = emUniversity.createNamedQuery(Accademico.TROVA_TUTTI, Accademico.class);
        return query.getResultList();
    }

    public Accademico trovaEmailUniClass(String email) {
        TypedQuery<Accademico> query = emUniClass.createNamedQuery(Accademico.TROVA_EMAIL, Accademico.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    public Accademico trovaEmailUniversity(String email) {
        TypedQuery<Accademico> query = emUniClass.createNamedQuery(Accademico.TROVA_EMAIL, Accademico.class);
        query.setParameter("email", email);
        return query.getSingleResult();
    }

    public void aggiungiAccademico(Accademico accademico) {
        emUniClass.merge(accademico);
    }

    public void rimuoviAccademico(Accademico accademico) {
        emUniClass.remove(accademico);
    }
}
