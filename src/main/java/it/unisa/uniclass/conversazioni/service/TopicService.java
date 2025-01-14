package it.unisa.uniclass.conversazioni.service;

import it.unisa.uniclass.conversazioni.model.Topic;
import it.unisa.uniclass.conversazioni.service.dao.TopicRemote;
import jakarta.ejb.Stateless;
import jakarta.persistence.NoResultException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

@Stateless
public class TopicService {
    private TopicRemote topicDao;

    public TopicService() {
        try {
            InitialContext ctx = new InitialContext();
            topicDao = (TopicRemote) ctx.lookup("java:global/UniClass/TopicDAO");
        }
        catch(NamingException e) {
            throw new RuntimeException("Impossibile trovare il messaggioDAO", e);
        }
    }

    public Topic trovaId(long id) {
        try {
            return topicDao.trovaId(id);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public Topic trovaNome(String nome) {
        try {
            return topicDao.trovaNome(nome);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public Topic trovaCorsoLaurea(String nome) {
        try {
            return topicDao.trovaCorsoLaurea(nome);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public Topic trovaCorso(String nome) {
        try {
            return topicDao.trovaCorso(nome);
        }
        catch(NoResultException e) {
            return null;
        }
    }

    public List<Topic> trovaTutti() {
        return trovaTutti();
    }

    public void aggiungiTopic(Topic topic) {
        topicDao.aggiungiTopic(topic);
    }

    public void rimuoviTopic(Topic topic) {
        topicDao.rimuoviTopic(topic);
    }
}
