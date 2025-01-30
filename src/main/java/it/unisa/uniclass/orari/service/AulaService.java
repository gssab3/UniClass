package it.unisa.uniclass.orari.service;

import it.unisa.uniclass.orari.model.Aula;
import it.unisa.uniclass.orari.service.dao.AulaRemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * Classe di servizio per la gestione delle operazioni relative alle aule.
 * Fornisce metodi per recuperare, aggiungere e rimuovere aule.
 */
@Stateless
public class AulaService {

    private AulaRemote aulaDao;

    /**
     * Costruttore di default che esegue il lookup JNDI del DAO.
     *
     * @throws NamingException Se si verifica un errore durante il lookup JNDI.
     */
    public AulaService() throws NamingException {
        try {
            InitialContext ctx = new InitialContext();
            this.aulaDao = (AulaRemote) ctx.lookup("java:global/UniClass/AulaDAO");
        } catch (NamingException e) {
            throw new RuntimeException("Errore durante il lookup di AulaDAO.", e);
        }
    }

    /**
     * Trova un'aula nel database utilizzando il suo ID.
     *
     * @param id L'ID dell'aula da cercare.
     * @return L'oggetto Aula corrispondente all'ID.
     */
    public Aula trovaAula(int id) {
        try {
            return aulaDao.trovaAula(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trova un'aula nel database utilizzando il suo nome.
     *
     * @param nome Il nome dell'aula da cercare.
     * @return L'oggetto Aula corrispondente al nome.
     */
    public Aula trovaAula(String nome) {
        try {
            return aulaDao.trovaAula(nome);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Recupera tutte le aule presenti nel database.
     *
     * @return Una lista di tutte le aule.
     */
    public List<Aula> trovaTutte() {
        return aulaDao.trovaTutte();
    }

    /**
     * Trova le aule nel database associate a un edificio.
     *
     * @param edificio Il nome dell'edificio di cui trovare le aule.
     * @return Una lista di oggetti Aula associati all'edificio.
     */
    public List<Aula> trovaAuleEdificio(String edificio) {
        return aulaDao.trovaAuleEdificio(edificio);
    }

    /**
     * Recupera tutti gli edifici presenti nel database.
     *
     * @return Una lista di tutti gli edifici.
     */
    public List<String> trovaEdifici() {
        return aulaDao.trovaEdifici();
    }

    /**
     * Aggiunge o aggiorna un'aula nel database.
     *
     * @param aula L'aula da aggiungere o aggiornare.
     * @throws IllegalArgumentException Se l'argomento 'aula' è null.
     */
    public void aggiungiAula(Aula aula) {
        if (aula == null) {
            throw new IllegalArgumentException("Argument 'aula' must not be null");
        }
        aulaDao.aggiungiAula(aula);
    }

    /**
     * Rimuove un'aula dal database.
     *
     * @param aula L'aula da rimuovere.
     * @throws IllegalArgumentException Se l'argomento 'aula' è null.
     */
    public void rimuoviAula(Aula aula) {
        if (aula == null) {
            throw new IllegalArgumentException("Argument 'aula' must not be null");
        }
        aulaDao.rimuoviAula(aula);
    }
}