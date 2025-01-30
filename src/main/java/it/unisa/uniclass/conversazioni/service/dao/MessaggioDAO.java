package it.unisa.uniclass.conversazioni.service.dao;

import it.unisa.uniclass.conversazioni.model.Messaggio;
import it.unisa.uniclass.conversazioni.model.Topic;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DAO (Data Access Object) per la gestione dei messaggi nel sistema.
 * Fornisce metodi per recuperare, aggiungere e rimuovere messaggi dal database.
 */
@Stateless(name = "MessaggioDAO")
public class MessaggioDAO implements MessaggioRemote {

    @PersistenceContext(unitName = "DBUniClassPU")
    private EntityManager emUniClass;

    /**
     * Trova un messaggio specifico dato il suo ID.
     * @param id Identificativo del messaggio.
     * @return Il messaggio corrispondente all'ID specificato.
     */
    @Override
    public Messaggio trovaMessaggio(long id) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_MESSAGGIO, Messaggio.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     * Recupera tutti i messaggi inviati da un determinato utente.
     * @param matricola Matricola dell'utente mittente.
     * @return Lista di messaggi inviati dall'utente.
     */
    @Override
    public List<Messaggio> trovaMessaggiInviati(String matricola) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_MESSAGGI_INVIATI, Messaggio.class);
        query.setParameter("matricola", matricola);
        return query.getResultList();
    }

    /**
     * Recupera tutti i messaggi ricevuti da un determinato utente.
     * @param matricola Matricola dell'utente destinatario.
     * @return Lista di messaggi ricevuti dall'utente.
     */
    @Override
    public List<Messaggio> trovaMessaggiRicevuti(String matricola) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_MESSAGGI_RICEVUTI, Messaggio.class);
        query.setParameter("matricola", matricola);
        return query.getResultList();
    }

    /**
     * Recupera i messaggi scambiati tra due utenti.
     * @param matricola1 Matricola del primo utente.
     * @param matricola2 Matricola del secondo utente.
     * @return Lista di messaggi scambiati tra i due utenti.
     */
    @Override
    public List<Messaggio> trovaMessaggi(String matricola1, String matricola2) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_MESSAGGI_MESSAGGERI, Messaggio.class);
        query.setParameter("autore", matricola1);
        query.setParameter("destinatario", matricola2);
        return query.getResultList();
    }

    /**
     * Recupera tutti i messaggi presenti nel database.
     * @return Lista di tutti i messaggi.
     */
    @Override
    public List<Messaggio> trovaTutti() {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_TUTTI, Messaggio.class);
        return query.getResultList();
    }

    /**
     * Recupera tutti gli avvisi presenti nel sistema.
     * @return Lista di avvisi.
     */
    @Override
    public List<Messaggio> trovaAvvisi() {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_AVVISI, Messaggio.class);
        return query.getResultList();
    }

    /**
     * Recupera tutti gli avvisi pubblicati da un determinato autore.
     * @param autore Nome dell'autore degli avvisi.
     * @return Lista di avvisi pubblicati dall'autore.
     */
    @Override
    public List<Messaggio> trovaAvvisiAutore(String autore) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_AVVISI_AUTORE, Messaggio.class);
        query.setParameter("autore", autore);
        return query.getResultList();
    }

    /**
     * Recupera tutti i messaggi inviati in una determinata data.
     * @param dateTime Data di riferimento.
     * @return Lista di messaggi inviati in quella data.
     */
    @Override
    public List<Messaggio> trovaMessaggiData(LocalDateTime dateTime) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_MESSAGGI_DATA, Messaggio.class);
        query.setParameter("dateTime", dateTime);
        return query.getResultList();
    }

    /**
     * Recupera tutti i messaggi appartenenti a un determinato topic.
     * @param topic Il topic di riferimento.
     * @return Lista di messaggi associati al topic.
     */
    @Override
    public List<Messaggio> trovaTopic(Topic topic) {
        TypedQuery<Messaggio> query = emUniClass.createNamedQuery(Messaggio.TROVA_TOPIC, Messaggio.class);
        query.setParameter("topic", topic);
        return query.getResultList();
    }

    /**
     * Aggiunge un nuovo messaggio o aggiorna un messaggio esistente.
     * @param messaggio Messaggio da aggiungere o aggiornare.
     * @return Il messaggio aggiunto o aggiornato.
     */
    @Override
    public Messaggio aggiungiMessaggio(Messaggio messaggio) {
        if(messaggio.getId() == null) {
            emUniClass.persist(messaggio);
        }
        else {
            emUniClass.merge(messaggio);
        }
        emUniClass.flush();
        System.out.println("Messaggio dopo flush: " + messaggio);
        return messaggio;
    }

    /**
     * Rimuove un messaggio dal database.
     * @param messaggio Messaggio da rimuovere.
     */
    @Override
    public void rimuoviMessaggio(Messaggio messaggio) {
        emUniClass.remove(messaggio);
    }
}
