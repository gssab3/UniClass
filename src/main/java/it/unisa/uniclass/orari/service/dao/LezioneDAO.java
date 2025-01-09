package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.*;
import jakarta.ejb.Stateless;
import jakarta.enterprise.inject.Typed;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.List;

@Stateless(name = "LezioneDAO")
public class LezioneDAO implements LezioneRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;



    @Override
    public Lezione trovaLezione(long id){
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONE, Lezione.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Lezione> trovaLezioniCorso(String nomeCorso) {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONE_CORSO, Lezione.class);
        query.setParameter("nomeCorso", nomeCorso);
        return query.getResultList();
    }

    @Override
    public List<Lezione> trovaLezioniOre(Time oraInizio, Time oraFine) {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONE_ORE, Lezione.class);
        query.setParameter("oraInizio", oraInizio);
        query.setParameter("oraFine", oraFine);
        return query.getResultList();
    }

    @Override
    public List<Lezione> trovaLezioniOreGiorno(Time oraInizio, Time oraFine, Giorno giorno) {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONE_ORE, Lezione.class);
        query.setParameter("oraInizio", oraInizio);
        query.setParameter("oraFine", oraFine);
        query.setParameter("giorno", giorno);
        return query.getResultList();
    }

    @Override
    public List<Lezione> trovaLezioniAule(String nome) {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONE_AULA, Lezione.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    @Override
    public List<Lezione> trovaTutte() {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_TUTTE, Lezione.class);
        return query.getResultList();
    }

    @Override
    public void aggiungiLezione(Lezione l) {
        emUniClass.merge(l);
    }

    @Override
    public void rimuoviLezione(Lezione l) {
        emUniClass.remove(l);
    }

    @Override
    public List<Lezione> trovaLezioniCorsoLaureaRestoAnno(long clid, long reid, int anid) {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONE_CORSO_RESTO_ANNO, Lezione.class);
        query.setParameter("corsoLaureaId", clid);
        query.setParameter("restoId", reid);
        query.setParameter("annoId", anid);
        return query.getResultList();
    }

    @Override
    public List<Lezione> trovaLezioniCorsoLaureaRestoAnnoSemestre(long clid, long reid, int anid, int semestre) {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONE_CORSO_RESTO_ANNO_SEMESTRE, Lezione.class);
        query.setParameter("corsoLaureaId", clid);
        query.setParameter("restoId", reid);
        query.setParameter("annoId", anid);
        query.setParameter("semestre", semestre);
        return query.getResultList();
    }

    @Override
    public List<Lezione> trovaLezioniDocente(String nomeDocente) {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONI_DOCENTE, Lezione.class);
        query.setParameter("nomeDocente", nomeDocente);
        return query.getResultList();
    }
}
