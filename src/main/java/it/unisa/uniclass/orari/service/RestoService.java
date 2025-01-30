package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import it.unisa.uniclass.orari.service.dao.RestoRemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * Classe di servizio per la gestione delle operazioni relative ai resti.
 * Fornisce metodi per recuperare, aggiungere e rimuovere resti.
 */
@Stateless
public class RestoService {

    private RestoRemote restoDao;

    /**
     * Costruttore di default che esegue il lookup JNDI del DAO.
     */
    public RestoService() {
        try {
            InitialContext ctx = new InitialContext();
            this.restoDao = (RestoRemote) ctx.lookup("java:global/UniClass/RestoDAO");
        } catch (NamingException e) {
            throw new RuntimeException("Errore durante il lookup di RestoDAO", e);
        }
    }

    /**
     * Trova i resti nel database associati a un corso di laurea.
     *
     * @param corsoLaurea Il corso di laurea di cui trovare i resti.
     * @return Una lista di oggetti Resto associati al corso di laurea.
     */
    public List<Resto> trovaRestiCorsoLaurea(CorsoLaurea corsoLaurea) {
        return restoDao.trovaRestiCorsoLaurea(corsoLaurea);
    }

    /**
     * Trova i resti nel database associati a un corso di laurea tramite il nome del corso.
     *
     * @param nomeCorsoLaurea Il nome del corso di laurea di cui trovare i resti.
     * @return Una lista di oggetti Resto associati al corso di laurea.
     */
    public List<Resto> trovaRestiCorsoLaurea(String nomeCorsoLaurea) {
        return restoDao.trovaRestiCorsoLaurea(nomeCorsoLaurea);
    }

    /**
     * Trova i resti nel database tramite il nome del resto.
     *
     * @param nomeResto Il nome del resto da cercare.
     * @return Una lista di oggetti Resto corrispondenti al nome.
     */
    public List<Resto> trovaResto(String nomeResto) {
        return restoDao.trovaResto(nomeResto);
    }

    /**
     * Trova un resto nel database utilizzando il suo ID.
     *
     * @param id L'ID del resto da cercare.
     * @return L'oggetto Resto corrispondente all'ID.
     */
    public Resto trovaResto(long id) {
        try {
            return restoDao.trovaResto(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trova un resto nel database associato a un corso di laurea tramite il nome del resto e del corso.
     *
     * @param nomeResto Il nome del resto da cercare.
     * @param corso Il corso di laurea associato al resto.
     * @return L'oggetto Resto corrispondente al nome e al corso.
     */
    public Resto trovaRestoNomeCorso(String nomeResto, CorsoLaurea corso) {
        try {
            return restoDao.trovaRestoNomeCorso(nomeResto, corso);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trova un resto nel database associato a un corso di laurea tramite il nome del resto e del corso.
     *
     * @param nomeResto Il nome del resto da cercare.
     * @param nomeCorso Il nome del corso di laurea associato al resto.
     * @return L'oggetto Resto corrispondente al nome e al corso.
     */
    public Resto trovaRestoNomeCorso(String nomeResto, String nomeCorso) {
        try {
            return restoDao.trovaRestoNomeCorso(nomeResto, nomeCorso);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Aggiunge o aggiorna un resto nel database.
     *
     * @param resto Il resto da aggiungere o aggiornare.
     */
    public void aggiungiResto(Resto resto) {
        if (resto != null) {
            restoDao.aggiungiResto(resto);
        }
    }

    /**
     * Rimuove un resto dal database.
     *
     * @param resto Il resto da rimuovere.
     */
    public void rimuoviResto(Resto resto) {
        if (resto != null) {
            restoDao.rimuoviResto(resto);
        }
    }
}