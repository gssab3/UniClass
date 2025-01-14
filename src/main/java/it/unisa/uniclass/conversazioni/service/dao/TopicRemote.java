package it.unisa.uniclass.conversazioni.service.dao;

import it.unisa.uniclass.conversazioni.model.Topic;

import java.util.List;

public interface TopicRemote {
    public Topic trovaId(long id);
    public Topic trovaNome(String nome);
    public Topic trovaCorsoLaurea(String nome);
    public List<Topic> trovaTutti();
    public Topic trovaCorso(String nome);
    public void aggiungiTopic(Topic topic);
    public void rimuoviTopic(Topic topic);
}
