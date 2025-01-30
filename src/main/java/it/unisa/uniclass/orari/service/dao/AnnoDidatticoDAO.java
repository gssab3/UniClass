package it.unisa.uniclass.orari.service.dao;

import it.unisa.uniclass.orari.model.AnnoDidattico;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

/**
 * Classe DAO per la gestione delle entità AnnoDidattico nel database.
 * Fornisce metodi per recuperare, aggiungere e rimuovere anni didattici.
 */
@Stateless(name = "AnnoDidatticoDAO")
public class AnnoDidatticoDAO implements AnnoDidatticoRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    /**
     * Trova un anno didattico nel database utilizzando l'anno specificato.
     *
     * @param anno L'anno da cercare.
     * @return Una lista di oggetti AnnoDidattico corrispondenti all'anno specificato.
     */
    @Override
    public List<AnnoDidattico> trovaAnno(String anno) {
        TypedQuery<AnnoDidattico> query = emUniClass.createNamedQuery(AnnoDidattico.TROVA_ANNO, AnnoDidattico.class);
        query.setParameter("anno", anno);
        return query.getResultList();
    }

    /**
     * Trova un anno didattico nel database utilizzando il suo ID.
     *
     * @param id L'ID dell'anno didattico da cercare.
     * @return L'oggetto AnnoDidattico corrispondente all'ID specificato.
     */
    @Override
    public AnnoDidattico trovaId(int id) {
        TypedQuery<AnnoDidattico> query = emUniClass.createNamedQuery(AnnoDidattico.TROVA_ID, AnnoDidattico.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Recupera tutti gli anni didattici presenti nel database.
     *
     * @return Una lista di tutti gli anni didattici.
     */
    @Override
    public List<AnnoDidattico> trovaTutti() {
        TypedQuery<AnnoDidattico> query = emUniClass.createNamedQuery(AnnoDidattico.TROVA_TUTTI, AnnoDidattico.class);
        return query.getResultList();
    }

    /**
     * Trova tutti gli anni didattici associati a un corso di laurea specifico.
     *
     * @param id L'ID del corso di laurea.
     * @return Una lista di anni didattici associati al corso di laurea specificato.
     */
    @Override
    public List<AnnoDidattico> trovaTuttiCorsoLaurea(long id) {
        TypedQuery<AnnoDidattico> query = emUniClass.createNamedQuery(AnnoDidattico.TROVA_ANNI_CORSOLAUREA, AnnoDidattico.class);
        query.setParameter("corsoId", id);
        return query.getResultList();
    }

    /**
     * Trova un anno didattico specifico associato a un corso di laurea.
     *
     * @param id   L'ID del corso di laurea.
     * @param anno L'anno didattico da cercare.
     * @return L'oggetto AnnoDidattico corrispondente ai parametri specificati.
     */
    @Override
    public AnnoDidattico trovaCorsoLaureaNome(long id, String anno) {
        TypedQuery<AnnoDidattico> query = emUniClass.createNamedQuery(AnnoDidattico.TROVA_ANNI_CORSOLAUREA_NOME, AnnoDidattico.class);
        query.setParameter("corsoId", id);
        query.setParameter("anno", anno);
        return query.getSingleResult();
    }

    /**
     * Aggiunge o aggiorna un anno didattico nel database.
     *
     * @param annoDidattico L'anno didattico da aggiungere o aggiornare.
     */
    @Override
    public void aggiungiAnno(AnnoDidattico annoDidattico) {
        emUniClass.merge(annoDidattico);
    }

    /**
     * Rimuove un anno didattico dal database.
     *
     * @param annoDidattico L'anno didattico da rimuovere.
     */
    @Override
    public void rimuoviAnno(AnnoDidattico annoDidattico) {
        emUniClass.remove(annoDidattico);
    }
}
