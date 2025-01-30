package it.unisa.uniclass.conversazioni.service;

import it.unisa.uniclass.conversazioni.model.Topic;
import it.unisa.uniclass.conversazioni.service.dao.TopicRemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * Classe di servizio per la gestione delle operazioni relative ai topic.
 * Fornisce metodi per recuperare, aggiungere e rimuovere topic.
 */
@Stateless
public class TopicService {

    private TopicRemote topicDao;

    /**
     * Costruttore di default che esegue il lookup JNDI del DAO.
     */
    public TopicService() {
        try {
            InitialContext ctx = new InitialContext();
            topicDao = (TopicRemote) ctx.lookup("java:global/UniClass/TopicDAO");
        } catch (NamingException e) {
            throw new RuntimeException("Impossibile trovare il messaggioDAO", e);
        }
    }

    /**
     * Trova un topic nel database utilizzando il suo ID.
     *
     * @param id L'ID del topic da cercare.
     * @return L'oggetto Topic corrispondente all'ID.
     */
    public Topic trovaId(long id) {
        try {
            return topicDao.trovaId(id);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trova un topic nel database utilizzando il suo nome.
     *
     * @param nome Il nome del topic da cercare.
     * @return L'oggetto Topic corrispondente al nome.
     */
    public Topic trovaNome(String nome) {
        try {
            return topicDao.trovaNome(nome);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trova un topic nel database associato a un corso di laurea tramite il nome del corso.
     *
     * @param nome Il nome del corso di laurea di cui trovare il topic.
     * @return L'oggetto Topic corrispondente al corso di laurea.
     */
    public Topic trovaCorsoLaurea(String nome) {
        try {
            return topicDao.trovaCorsoLaurea(nome);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Trova un topic nel database associato a un corso tramite il nome del corso.
     *
     * @param nome Il nome del corso di cui trovare il topic.
     * @return L'oggetto Topic corrispondente al corso.
     */
    public Topic trovaCorso(String nome) {
        try {
            return topicDao.trovaCorso(nome);
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Recupera tutti i topic presenti nel database.
     *
     * @return Una lista di tutti i topic.
     */
    public List<Topic> trovaTutti() {
        return topicDao.trovaTutti();
    }

    /**
     * Aggiunge o aggiorna un topic nel database.
     *
     * @param topic Il topic da aggiungere o aggiornare.
     */
    public void aggiungiTopic(Topic topic) {
        topicDao.aggiungiTopic(topic);
    }

    /**
     * Rimuove un topic dal database.
     *
     * @param topic Il topic da rimuovere.
     */
    public void rimuoviTopic(Topic topic) {
        topicDao.rimuoviTopic(topic);
    }
}