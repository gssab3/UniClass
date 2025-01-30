package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.Aula;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

/**
 * Classe DAO per la gestione delle entità Aula nel database.
 * Fornisce metodi per recuperare, aggiungere e rimuovere aule.
 */
@Stateless(name = "AulaDAO")
public class AulaDAO implements AulaRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    /**
     * Trova un'aula nel database utilizzando il suo ID.
     *
     * @param id L'ID dell'aula da cercare.
     * @return L'oggetto Aula corrispondente all'ID specificato.
     */
    @Override
    public Aula trovaAula(int id) {
        TypedQuery<Aula> query = emUniClass.createNamedQuery(Aula.TROVA_AULA, Aula.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Trova un'aula nel database utilizzando il suo nome.
     *
     * @param nome Il nome dell'aula da cercare.
     * @return L'oggetto Aula corrispondente al nome specificato.
     */
    @Override
    public Aula trovaAula(String nome) {
        TypedQuery<Aula> query = emUniClass.createNamedQuery(Aula.TROVA_AULANOME, Aula.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }

    /**
     * Recupera tutte le aule presenti nel database.
     *
     * @return Una lista di tutte le aule.
     */
    @Override
    public List<Aula> trovaTutte() {
        TypedQuery<Aula> query = emUniClass.createNamedQuery(Aula.TROVA_TUTTE, Aula.class);
        return query.getResultList();
    }

    /**
     * Trova tutte le aule situate in un determinato edificio.
     *
     * @param edificio Il nome dell'edificio.
     * @return Una lista di aule situate nell'edificio specificato.
     */
    @Override
    public List<Aula> trovaAuleEdificio(String edificio) {
        TypedQuery<Aula> query = emUniClass.createNamedQuery(Aula.TROVA_AULA_EDIFICIO, Aula.class);
        query.setParameter("edificio", edificio);
        return query.getResultList();
    }

    /**
     * Recupera l'elenco degli edifici che contengono aule.
     *
     * @return Una lista di nomi di edifici.
     */
    @Override
    public List<String> trovaEdifici() {
        TypedQuery<String> query = emUniClass.createNamedQuery(Aula.TROVA_EDIFICI, String.class);
        return query.getResultList();
    }

    /**
     * Aggiunge o aggiorna un'aula nel database.
     *
     * @param aula L'aula da aggiungere o aggiornare.
     */
    @Override
    public void aggiungiAula(Aula aula) {
        emUniClass.merge(aula);
    }

    /**
     * Rimuove un'aula dal database.
     *
     * @param aula L'aula da rimuovere.
     */
    @Override
    public void rimuoviAula(Aula aula) {
        emUniClass.remove(aula);
    }
}
