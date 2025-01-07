package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.common.config.database.qualifier.UniclassDB;
import it.unisa.uniclass.orari.model.Aula;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class AulaDAO {
    @Inject
    @UniclassDB
    private EntityManager emUniClass;

    public Aula trovaAula(int id) {
        TypedQuery<Aula> query = emUniClass.createNamedQuery(Aula.TROVA_AULA, Aula.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public Aula trovaAula(String nome) {
        TypedQuery<Aula> query = emUniClass.createNamedQuery(Aula.TROVA_AULANOME, Aula.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }

    public List<Aula> trovaTutte() {
        TypedQuery<Aula> query = emUniClass.createNamedQuery(Aula.TROVA_TUTTE, Aula.class);
        return query.getResultList();
    }

    public List<Aula> trovaAuleEdificio(String edificio) {
        TypedQuery<Aula> query = emUniClass.createNamedQuery(Aula.TROVA_AULA_EDIFICIO, Aula.class);
        query.setParameter("edificio", edificio);
        return query.getResultList();
    }

    public void aggiungiAula(Aula aula) {
        emUniClass.merge(aula);
    }

    public void rimuoviAula(Aula aula) {
        emUniClass.remove(aula);
    }
}
