package it.unisa.uniclass.utenti.service;

import it.unisa.uniclass.common.exceptions.NotFoundUserException;
import it.unisa.uniclass.utenti.model.Accademico;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class AccademicoDAO {
    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @PersistenceContext(unitName = "DBUniversityPU")
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

    public void aggiungiAccademico(Accademico accademico) throws NotFoundUserException {
        if(trovaAccademicoUniversity(accademico.getMatricola()) == null){
            throw new NotFoundUserException("Accademico non trovato.");
        }
        else
            emUniClass.persist(accademico);
    }

    public void rimuoviAccademico(Accademico accademico) {
        emUniClass.remove(accademico);
    }
}
