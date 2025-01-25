package it.unisa.uniclass.conversazioni.service.dao;

import it.unisa.uniclass.conversazioni.model.Topic;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

@Stateless(name = "TopicDAO")
public class TopicDAO implements TopicRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    @Override
    public Topic trovaId(long id) {
        TypedQuery<Topic> query = emUniClass.createNamedQuery(Topic.TROVA_ID, Topic.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Topic trovaNome(String nome) {
        TypedQuery<Topic> query = emUniClass.createNamedQuery(Topic.TROVA_NOME, Topic.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }

    @Override
    public Topic trovaCorsoLaurea(String nome) {
        TypedQuery<Topic> query = emUniClass.createNamedQuery(Topic.TROVA_CORSOLAUREA, Topic.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }

    @Override
    public Topic trovaCorso(String nome) {
        TypedQuery<Topic> query = emUniClass.createNamedQuery(Topic.TROVA_CORSO, Topic.class);
        query.setParameter("nome", nome);
        return query.getSingleResult();
    }

    @Override
    public List<Topic> trovaTutti() {
        TypedQuery<Topic> query = emUniClass.createNamedQuery(Topic.TROVA_TUTTI, Topic.class);
        return query.getResultList();
    }

    @Override
    public void aggiungiTopic(Topic topic) {
        emUniClass.merge(topic);
    }

    @Override
    public void rimuoviTopic(Topic topic) {
        emUniClass.remove(topic);
    }
}
