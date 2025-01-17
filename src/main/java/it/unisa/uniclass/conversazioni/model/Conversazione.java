package it.unisa.uniclass.conversazioni.model;

import it.unisa.uniclass.esami.model.Prenotazione;
import it.unisa.uniclass.utenti.model.Accademico;
import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static it.unisa.uniclass.conversazioni.model.Conversazione.*;

/**
 * Rappresenta una conversazione nel sistema. Ogni conversazione può contenere
 * una lista di messaggi e un insieme di accademici partecipanti.
 * E' una classe annotata per l'utilizzo con JPA.
 * */
@Entity
@Table(name = "conversazioni")
@NamedQueries({
        @NamedQuery(name = TROVA_CONVERSAZIONE, query = "SELECT c FROM Conversazione c WHERE c.id = :id"),
        @NamedQuery(name = TROVA_TUTTE, query = "SELECT c FROM Conversazione c")
})
public class Conversazione implements Serializable {

    public static final String TROVA_CONVERSAZIONE = "Conversazione.trovaConversazione";
    public static final String TROVA_TUTTE = "Conversazione.trovaTutte";

    /**
     * Identificativo univoco della conversazione.
     * Generato automaticamnete dalla strategia {@code GenerationType.AUTO}
     * */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Lista di messaggi associati alla conversazione.
     * Ogni messaggio è mappato con relazione uno-a-molti e rimosso se eliminato dalla conversazione.
     * */
    @OneToMany(mappedBy = "conversazione", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Messaggio> messaggi =  new ArrayList<>();

    /**
     * Insieme di accademici partecipanti alla conversazione.
     * Mappato con realzione molti-a-molti.
     * */
    @ManyToMany
    @JoinTable(
            name = "conversazione_messaggeri",
            joinColumns = @JoinColumn(name = "conversazione_id"),
            inverseJoinColumns = @JoinColumn(name = "accademico_id")
    )
    private Set<Accademico> messaggeri = new HashSet<>();

    /**
     * Costruttore di default per la classe Conversazione
     * */
    public Conversazione() {}

    /**
     * Restituisce l'ID della conversazione.
     *
     * @return l'ID della conversazione.
     * */
    public Long getId() {
        return id;
    }

    /**
     * Restituisce la lista di messaggi nella conversazione.
     *
     * @return lista di messaggi.
     * */
    public List<Messaggio> getMessaggi() {
        return messaggi;
    }

    /**
     * Restituisce l'insieme di accademici partecipanti alla conversazione.
     *
     * @return insieme di accademici.
     * */
    public Set<Accademico> getMessaggeri() {
        return messaggeri;
    }

    /**
     * Imposta la lista di messaggi nella conversazione.
     *
     * @param messaggi lista di messaggi da impostare.
     * */
    public void setMessaggi(List<Messaggio> messaggi) {
        this.messaggi = messaggi;
    }

    /**
     * Imposta l'insieme di accademici partecipanti alla conversazione.
     *
     * @param messaggeri insieme di accademici da impostare.
     * */
    public void setMessaggeri(Set<Accademico> messaggeri) {
        this.messaggeri = messaggeri;
    }

    /**
     * Rappresentazione in forma di stringa della conversazione.
     *
     * @return stringa che rappresenta la conversazione, includendo l'ID e l'elenco degli accademici partecipanti.
     * */
    @Override
    public String toString() {
        return "Conversazione{" +
                "id=" + id +
                ", messaggeri=" + messaggeri +
                '}';
    }
}
