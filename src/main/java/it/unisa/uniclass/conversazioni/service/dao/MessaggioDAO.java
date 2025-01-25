package it.unisa.uniclass.conversazioni.service.dao;

import it.unisa.uniclass.conversazioni.model.Messaggio;
import it.unisa.uniclass.conversazioni.model.Topic;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Stateless(name = "MessaggioDAO")
public class MessaggioDAO implements MessaggioRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;


    @Override
    public Messaggio trovaMessaggio(long id) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_MESSAGGIO, Messaggio.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public List<Messaggio> trovaMessaggiInviati(String matricola) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_MESSAGGI_INVIATI, Messaggio.class);
        query.setParameter("matricola", matricola);
        return query.getResultList();
    }

    @Override
    public List<Messaggio> trovaMessaggiInviatiConversazione(String matricola, long id) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_MESSAGGI_INVIATI_CONVERSAZIONE, Messaggio.class);
        query.setParameter("matricola", matricola);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public List<Messaggio> trovaMessaggeri(String matricola1, String matricola2) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_MESSAGGI_MESSAGGERI, Messaggio.class);
        query.setParameter("autore", matricola1);
        query.setParameter("destinatario", matricola2);
        return query.getResultList();
    }

    @Override
    public List<Messaggio> trovaTutti() {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_TUTTI, Messaggio.class);
        return query.getResultList();
    }

    @Override
    public List<Messaggio> trovaAvvisi() {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_AVVISI, Messaggio.class);
        return query.getResultList();
    }

    @Override
    public List<Messaggio> trovaAvvisiAutore(String autore) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_AVVISI_AUTORE, Messaggio.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    @Override
    public List<Messaggio> trovaMessaggiData(LocalDateTime dateTime) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_MESSAGGI_DATA, Messaggio.class);
        query.setParameter("dateTime", dateTime);
        return query.getResultList();
    }

    @Override
    public List<Messaggio> trovaTopic(Topic topic) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_TOPIC, Messaggio.class);
        query.setParameter("topic", topic);
        return query.getResultList();
    }

    @Override
    public void aggiungiMessaggio(Messaggio messaggio) {
        emUniClass.merge(messaggio);
    }

    @Override
    public void rimuoviMessaggio(Messaggio messaggio) {
        emUniClass.remove(messaggio);
    }
}
