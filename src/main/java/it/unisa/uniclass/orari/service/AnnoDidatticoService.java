package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.AnnoDidattico;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import it.unisa.uniclass.orari.service.dao.AnnoDidatticoRemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe di servizio per la gestione delle operazioni relative agli anni didattici.
 * Fornisce metodi per recuperare, aggiungere e rimuovere anni didattici.
 */
@Stateless
public class AnnoDidatticoService {

    private AnnoDidatticoRemote annoDidatticoDao;

    /**
     * Costruttore di default che esegue il lookup JNDI del DAO.
     */
    public AnnoDidatticoService() {
        try {
            InitialContext ctx = new InitialContext();
            this.annoDidatticoDao = (AnnoDidatticoRemote) ctx.lookup("java:global/UniClass/AnnoDidatticoDAO");
        } catch (NamingException e) {
            throw new RuntimeException("Errore durante il lookup di AnnoDidatticoDAO.", e);
        }
    }

    /**
     * Trova gli anni didattici nel database utilizzando l'anno.
     *
     * @param anno L'anno didattico da cercare.
     * @return Una lista di oggetti AnnoDidattico corrispondenti all'anno.
     */
    public List<AnnoDidattico> trovaAnno(String anno) {
        return annoDidatticoDao.trovaAnno(anno);
    }

    /**
     * Trova un anno didattico nel database utilizzando il suo ID.
     *
     * @param id L'ID dell'anno didattico da cercare.
     * @return L'oggetto AnnoDidattico corrispondente all'ID.
     */
    public AnnoDidattico trovaId(int id) {
        try {
            return annoDidatticoDao.trovaId(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Recupera tutti gli anni didattici presenti nel database.
     *
     * @return Una lista di tutti gli anni didattici.
     */
    public List<AnnoDidattico> trovaTutti() {
        return annoDidatticoDao.trovaTutti();
    }

    /**
     * Aggiunge o aggiorna un anno didattico nel database.
     *
     * @param annoDidattico L'anno didattico da aggiungere o aggiornare.
     */
    public void aggiungiAnno(AnnoDidattico annoDidattico) {
        annoDidatticoDao.aggiungiAnno(annoDidattico);
    }

    /**
     * Rimuove un anno didattico dal database.
     *
     * @param annoDidattico L'anno didattico da rimuovere.
     */
    public void rimuoviAnno(AnnoDidattico annoDidattico) {
        annoDidatticoDao.rimuoviAnno(annoDidattico);
    }

    /**
     * Trova tutti gli anni didattici nel database associati a un corso di laurea tramite l'ID del corso.
     *
     * @param id L'ID del corso di laurea di cui trovare gli anni didattici.
     * @return Una lista di oggetti AnnoDidattico associati al corso di laurea.
     */
    public List<AnnoDidattico> trovaTuttiCorsoLaurea(long id) {
        return annoDidatticoDao.trovaTuttiCorsoLaurea(id);
    }

    /**
     * Trova un anno didattico nel database associato a un corso di laurea tramite l'ID del corso e l'anno.
     *
     * @param id L'ID del corso di laurea.
     * @param anno L'anno didattico da cercare.
     * @return L'oggetto AnnoDidattico corrispondente all'ID del corso e all'anno.
     */
    public AnnoDidattico trovaTuttiCorsoLaureaNome(long id, String anno) {
        try {
            return annoDidatticoDao.trovaCorsoLaureaNome(id, anno);
        } catch (NoResultException e) {
            return null;
        }
    }
}