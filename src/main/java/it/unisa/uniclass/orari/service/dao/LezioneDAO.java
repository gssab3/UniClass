package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.sql.Time;
import java.util.List;

/**
 * Classe DAO per la gestione delle entità Lezione nel database.
 * Fornisce metodi per recuperare, aggiungere e rimuovere lezioni.
 */
@Stateless(name = "LezioneDAO")
public class LezioneDAO implements LezioneRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    /**
     * Trova una lezione nel database utilizzando il suo ID.
     *
     * @param id L'ID della lezione da cercare.
     * @return L'oggetto Lezione corrispondente all'ID.
     */
    @Override
    public Lezione trovaLezione(long id) {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONE, Lezione.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Trova tutte le lezioni associate a un determinato corso.
     *
     * @param nomeCorso Il nome del corso.
     * @return Una lista di lezioni associate al corso.
     */
    @Override
    public List<Lezione> trovaLezioniCorso(String nomeCorso) {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONE_CORSO, Lezione.class);
        query.setParameter("nomeCorso", nomeCorso);
        return query.getResultList();
    }

    /**
     * Trova tutte le lezioni che si svolgono in una determinata fascia oraria.
     *
     * @param oraInizio L'ora di inizio.
     * @param oraFine L'ora di fine.
     * @return Una lista di lezioni che si svolgono nella fascia oraria specificata.
     */
    @Override
    public List<Lezione> trovaLezioniOre(Time oraInizio, Time oraFine) {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONE_ORE, Lezione.class);
        query.setParameter("oraInizio", oraInizio);
        query.setParameter("oraFine", oraFine);
        return query.getResultList();
    }

    /**
     * Trova tutte le lezioni che si svolgono in una determinata fascia oraria e giorno della settimana.
     *
     * @param oraInizio L'ora di inizio.
     * @param oraFine L'ora di fine.
     * @param giorno Il giorno della settimana.
     * @return Una lista di lezioni corrispondenti ai criteri.
     */
    @Override
    public List<Lezione> trovaLezioniOreGiorno(Time oraInizio, Time oraFine, Giorno giorno) {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONE_ORE, Lezione.class);
        query.setParameter("oraInizio", oraInizio);
        query.setParameter("oraFine", oraFine);
        query.setParameter("giorno", giorno);
        return query.getResultList();
    }

    /**
     * Trova tutte le lezioni che si svolgono in un'aula specifica.
     *
     * @param nome Il nome dell'aula.
     * @return Una lista di lezioni che si svolgono nell'aula specificata.
     */
    @Override
    public List<Lezione> trovaLezioniAule(String nome) {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONE_AULA, Lezione.class);
        query.setParameter("nome", nome);
        return query.getResultList();
    }

    /**
     * Recupera tutte le lezioni presenti nel database.
     *
     * @return Una lista di tutte le lezioni.
     */
    @Override
    public List<Lezione> trovaTutte() {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_TUTTE, Lezione.class);
        return query.getResultList();
    }

    /**
     * Aggiunge o aggiorna una lezione nel database.
     *
     * @param l La lezione da aggiungere o aggiornare.
     */
    @Override
    public void aggiungiLezione(Lezione l) {
        emUniClass.merge(l);
    }

    /**
     * Rimuove una lezione dal database.
     *
     * @param l La lezione da rimuovere.
     */
    @Override
    public void rimuoviLezione(Lezione l) {
        emUniClass.remove(l);
    }

    /**
     * Trova tutte le lezioni relative a un corso di laurea, resto e anno specifici.
     *
     * @param clid ID del corso di laurea.
     * @param reid ID del resto.
     * @param anid ID dell'anno accademico.
     * @return Una lista di lezioni corrispondenti ai criteri.
     */
    @Override
    public List<Lezione> trovaLezioniCorsoLaureaRestoAnno(long clid, long reid, int anid) {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONE_CORSO_RESTO_ANNO, Lezione.class);
        query.setParameter("corsoLaureaId", clid);
        query.setParameter("restoId", reid);
        query.setParameter("annoId", anid);

        for (Lezione lezione : query.getResultList()) {
            lezione.getDocenti().size(); // Forza il caricamento
        }
        return query.getResultList();
    }

    /**
     * Trova tutte le lezioni relative a un corso di laurea, resto, anno e semestre specifici.
     *
     * @param clid ID del corso di laurea.
     * @param reid ID del resto.
     * @param anid ID dell'anno accademico.
     * @param semestre Il semestre.
     * @return Una lista di lezioni corrispondenti ai criteri.
     */
    @Override
    public List<Lezione> trovaLezioniCorsoLaureaRestoAnnoSemestre(long clid, long reid, int anid, int semestre) {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONE_CORSO_RESTO_ANNO_SEMESTRE, Lezione.class);
        query.setParameter("corsoLaureaId", clid);
        query.setParameter("restoId", reid);
        query.setParameter("annoId", anid);
        query.setParameter("semestre", semestre);
        return query.getResultList();
    }

    /**
     * Trova tutte le lezioni tenute da un determinato docente.
     *
     * @param nomeDocente Il nome del docente.
     * @return Una lista di lezioni tenute dal docente specificato.
     */
    @Override
    public List<Lezione> trovaLezioniDocente(String nomeDocente) {
        TypedQuery<Lezione> query = emUniClass.createNamedQuery(Lezione.TROVA_LEZIONI_DOCENTE, Lezione.class);
        query.setParameter("nomeDocente", nomeDocente);
        return query.getResultList();
    }
}
