package it.unisa.uniclass.conversazioni.service.dao;

import it.unisa.uniclass.conversazioni.model.Topic;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * Classe DAO (Data Access Object) per la gestione delle operazioni CRUD sui topic.
 */
@Stateless(name = "TopicDAO")
public class TopicDAO implements TopicRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    /**
     * Trova un topic in base al suo ID.
     *
     * @param id Identificativo univoco del topic.
     * @return Il topic corrispondente all'ID fornito.
     */
    @Override
    public Topic trovaId(long id) {
        TypedQuery<Topic> query = emUniClass.createNamedQuery(Topic.TROVA_ID, Topic.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Trova un topic in base al suo nome.
     *
     * @param nome Nome del topic.
     * @return Il topic corrispondente al nome fornito.
     */
    @Override
    public Topic trovaNome(String nome) {
        TypedQuery<Topic> query = emUniClass.createNamedQuery(Topic.TROVA_NOME, Topic.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }

    /**
     * Trova un topic associato a un corso di laurea.
     *
     * @param nome Nome del corso di laurea.
     * @return Il topic associato al corso di laurea indicato.
     */
    @Override
    public Topic trovaCorsoLaurea(String nome) {
        TypedQuery<Topic> query = emUniClass.createNamedQuery(Topic.TROVA_CORSOLAUREA, Topic.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }

    /**
     * Trova un topic associato a un corso specifico.
     *
     * @param nome Nome del corso.
     * @return Il topic associato al corso indicato.
     */
    @Override
    public Topic trovaCorso(String nome) {
        TypedQuery<Topic> query = emUniClass.createNamedQuery(Topic.TROVA_CORSO, Topic.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }

    /**
     * Restituisce una lista di tutti i topic disponibili.
     *
     * @return Lista di tutti i topic.
     */
    @Override
    public List<Topic> trovaTutti() {
        TypedQuery<Topic> query = emUniClass.createNamedQuery(Topic.TROVA_TUTTI, Topic.class);
        return query.getResultList();
    }

    /**
     * Aggiunge un nuovo topic o aggiorna un topic esistente nel database.
     *
     * @param topic Il topic da aggiungere o aggiornare.
     */
    @Override
    public void aggiungiTopic(Topic topic) {
        if (topic.getId() == null) {
            emUniClass.persist(topic);
        } else {
            emUniClass.merge(topic);
        }
        emUniClass.flush();
    }

    /**
     * Rimuove un topic dal database.
     *
     * @param topic Il topic da rimuovere.
     */
    @Override
    public void rimuoviTopic(Topic topic) {
        emUniClass.remove(topic);
    }
}
