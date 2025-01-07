package it.unisa.uniclass.conversazioni.service;

import it.unisa.uniclass.conversazioni.model.Messaggio;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class MessaggioDAO {

    EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("DBUniClassPU");
    EntityManager emUniClass = emf1.createEntityManager();


    public Messaggio trovaMessaggio(long id) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_MESSAGGIO, Messaggio.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Messaggio> trovaMessaggiInviati(String matricola) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_MESSAGGI_INVIATI, Messaggio.class);
        query.setParameter("matricola", matricola);
        return query.getResultList();
    }

    public List<Messaggio> trovaMessaggiInviatiConversazione(String matricola, long id) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_MESSAGGI_INVIATI_CONVERSAZIONE, Messaggio.class);
        query.setParameter("matricola", matricola);
        query.setParameter("id", id);
        return query.getResultList();
    }

    public List<Messaggio> trovaMessaggeri(String matricola1, String matricola2) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_MESSAGGI_MESSAGGERI, Messaggio.class);
        query.setParameter("autore", matricola1);
        query.setParameter("destinatario", matricola2);
        return query.getResultList();
    }

    public List<Messaggio> trovaTutti() {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_TUTTI, Messaggio.class);
        return query.getResultList();
    }

    public List<Messaggio> trovaAvvisi() {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_AVVISI, Messaggio.class);
        return query.getResultList();
    }

    public List<Messaggio> trovaAvvisiAutore(String autore) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_AVVISI_AUTORE, Messaggio.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    public List<Messaggio> trovaMessaggiData(LocalDateTime dateTime) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_MESSAGGI_DATA, Messaggio.class);
        query.setParameter("dateTime", dateTime);
        return query.getResultList();
    }

    public void aggiungiMessaggio(Messaggio messaggio) {
        emUniClass.merge(messaggio);
    }

    public void rimuoviMessaggio(Messaggio messaggio) {
        emUniClass.remove(messaggio);
    }
}
