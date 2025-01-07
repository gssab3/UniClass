package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.Aula;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless(name = "AulaDAO")
public class AulaDAO implements AulaRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @Override
    public Aula trovaAula(int id) {
        TypedQuery<Aula> query = emUniClass.createNamedQuery(Aula.TROVA_AULA, Aula.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Aula trovaAula(String nome) {
        TypedQuery<Aula> query = emUniClass.createNamedQuery(Aula.TROVA_AULANOME, Aula.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }

    @Override
    public List<Aula> trovaTutte() {
        TypedQuery<Aula> query = emUniClass.createNamedQuery(Aula.TROVA_TUTTE, Aula.class);
        return query.getResultList();
    }

    @Override
    public List<Aula> trovaAuleEdificio(String edificio) {
        TypedQuery<Aula> query = emUniClass.createNamedQuery(Aula.TROVA_AULA_EDIFICIO, Aula.class);
        query.setParameter("edificio", edificio);
        return query.getResultList();
    }

    @Override
    public void aggiungiAula(Aula aula) {
        emUniClass.merge(aula);
    }

    @Override
    public void rimuoviAula(Aula aula) {
        emUniClass.remove(aula);
    }
}
