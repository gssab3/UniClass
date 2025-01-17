package it.unisa.uniclass.conversazioni.model;

import it.unisa.uniclass.utenti.model.Accademico;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import static it.unisa.uniclass.conversazioni.model.Messaggio.*;

/**
 * Rappresenta un messaggio scambiato tra utenti in una conversazione.
 * E' persistito in un database tramite JPA.
 * */
@Entity
@Table(name = "messaggi")
@NamedQueries({
        @NamedQuery(name = TROVA_MESSAGGIO, query = "SELECT m FROM Messaggio m WHERE m.id = :id"),
        @NamedQuery(name = TROVA_MESSAGGI_INVIATI, query = "SELECT m FROM Messaggio m WHERE m.autore.matricola = :matricola"),
        @NamedQuery(name = TROVA_MESSAGGI_INVIATI_CONVERSAZIONE, query = "SELECT m FROM Messaggio m WHERE m.autore.matricola = :matricola AND m.conversazione.id = :id"),
        @NamedQuery(name = TROVA_MESSAGGI_MESSAGGERI, query = "SELECT m FROM Messaggio m WHERE m.autore.matricola = :autore AND m.destinatario.matricola = :destinatario OR m.autore.matricola = :destinatario AND m.destinatario.matricola = :autore"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT m FROM Messaggio m"),
        @NamedQuery(name = TROVA_AVVISI, query = "SELECT m FROM Messaggio m WHERE m.topic <> null"),
        @NamedQuery(name = TROVA_AVVISI_AUTORE, query = "SELECT m FROM Messaggio m WHERE m.topic <> null AND m.autore.matricola = :autore"),
        @NamedQuery(name = TROVA_MESSAGGI_DATA, query = "SELECT m FROM Messaggio m WHERE m.dateTime = :dateTime")
})
public class Messaggio implements Serializable {

    /**
     * Nome della query per trovare un messaggio tramite ID
     * */
    public static final String TROVA_MESSAGGIO = "Messaggio.trovaMessaggio";
    /**
     * Nome della query per trovare i messaggi inviati da un autore.
     * */
    public static final String TROVA_MESSAGGI_INVIATI = "Messaggio.trovaMessaggiInviati";
    /**
     * Nome della query per trovare i messaggi inviati in una conversazione specifica.
     * */
    public static final String TROVA_MESSAGGI_INVIATI_CONVERSAZIONE = "Messaggio.trovaMessaggiInviatiConversazione";
    /**
     * Nome della query per trovare i messaggi tra due specifici utenti.
     * */
    public static final String TROVA_MESSAGGI_MESSAGGERI = "Messaggio.trovaMessaggiMessaggeri";
    /**
     * Nome della query per trovare tutti i messsaggi.
     * */
    public static final String TROVA_TUTTI = "Messaggio.trovaTutti";
    /**
     * Nome della query per trovare tutti gli avvisi.
     * */
    public static final String TROVA_AVVISI = "Messaggio.trovaAvvisi";
    /**
     * Nome della query per trovare gli avvisi creati da un autore specifico.
     * */
    public static final String TROVA_AVVISI_AUTORE = "Messaggio.trovaAvvisiAutore";
    /**
     * Nome della query per trovare i messaggi in base alla data e ora.
     * */
    public static final String TROVA_MESSAGGI_DATA = "Messaggio.trovaMessaggiData";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Topic del messaggio (può essere null).
     * */
    private String topic;

    /**
     * Data e ore di creazione del messaggio.
     * */
    private LocalDateTime dateTime;

    /**
     * Corpo del messaggio. Non può essere null.
     * */
    @Column(nullable = false)
    private String body;

    /**
     * Autore del messaggio. E' un riferimento a un oggetto di tipo Accademico.
     * */
    @ManyToOne
    @JoinColumn(name = "autore")
    private Accademico autore;

    /**
     * Conversazione a cui appartiene il messaggio.
     * */
    @ManyToOne
    @JoinColumn(name = "conversazione_id")
    private Conversazione conversazione;

    /**
     * Destinatario del messaggio. E' un riferimento a un oggetto di tipo Accademico.
     * */
    @ManyToOne
    @JoinColumn(name = "destinatario")
    private Accademico destinatario;

    //Getter e Setter con Javadoc
    /**
     * Restituisce il destinatario del messaggio.
     *
     * @return Il destinatario
     * */
    public Accademico getDestinatario() {
        return destinatario;
    }

    /**
     * Imposta il destinatario del messaggio
     *
     * @param destinatario Il destinatario da impostare.
     * */
    public void setDestinatario(Accademico destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * Restituisce l'autore del messaggio.
     *
     * @return L'autore.
     * */
    public Accademico getAutore() {
        return autore;
    }

    /**
     * Imposta l'autore del messaggio
     *
     * @param autore L'autore da impostare.
     * */
    public void setAutore(Accademico autore) {
        this.autore = autore;
    }

    /**
     * Costruttore vuoto necessario per JPA.
     * */
    public Messaggio() {}

    /**
     * Costruttore per inizializzare un messaggio con i dati principali.
     *
     * @param autore    L'autore del Messaggio
     * @param destinatario  Il destinatario del Messaggio
     * @param topic Il topic del messaggio (opzionale)
     * @param body  Il corpo del Messaggio
     * @param dateTime  La data e ora di creazione
     * */
    public Messaggio(Accademico autore, Accademico destinatario, String topic, String body, LocalDateTime dateTime) {
        this.autore = autore;
        this.destinatario = destinatario;
        this.topic = topic;
        this.body = body;
        this.dateTime = dateTime;
    }

    /**
     * Restituisce il topic del messaggio.
     *
     * @return Il topic.
     * */
    public String getTopic() {
        return topic;
    }

    /**
     * Imposta il topic del messaggio.
     *
     * @param topic Il topic da impostare.
     * */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * Restituisce la data e ora di creazione del messaggio.
     *
     * @return La data e ora.
     *
     * */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Imposta la data e ora di creazione del messaggio.
     *
     * @param dateTime La data e ora da impostare.
     * */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * Restituisce il corpo del messaggio.
     *
     * @return Il corpo.
     * */
    public String getBody() {
        return body;
    }

    /**
     * Imposta il corpo del messaggio.
     *
     * @param body Il corpo da impostare.
     * */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Rappresentazione testuale dell'oggetto Messaggio.
     *
     * @return Una stringa che descrive il messaggio.
     * */
    @Override
    public String toString() {
        return "Messaggio{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", dateTime=" + dateTime +
                ", body='" + body + '\'' +
                ", autore=" + autore +
                ", destinatario=" + destinatario +
                '}';
    }
}
