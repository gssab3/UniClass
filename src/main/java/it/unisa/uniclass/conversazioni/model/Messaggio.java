package it.unisa.uniclass.conversazioni.model;

import it.unisa.uniclass.utenti.model.Accademico;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import static it.unisa.uniclass.conversazioni.model.Messaggio.*;

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

    public static final String TROVA_MESSAGGIO = "Messaggio.trovaMessaggio";
    public static final String TROVA_MESSAGGI_INVIATI = "Messaggio.trovaMessaggiInviati";
    public static final String TROVA_MESSAGGI_INVIATI_CONVERSAZIONE = "Messaggio.trovaMessaggiInviatiConversazione";
    public static final String TROVA_MESSAGGI_MESSAGGERI = "Messaggio.trovaMessaggiMessaggeri";
    public static final String TROVA_TUTTI = "Messaggio.trovaTutti";
    public static final String TROVA_AVVISI = "Messaggio.trovaAvvisi";
    public static final String TROVA_AVVISI_AUTORE = "Messaggio.trovaAvvisiAutore";
    public static final String TROVA_MESSAGGI_DATA = "Messaggio.trovaMessaggiData";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String topic;

    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(name = "autore")
    private Accademico autore;

    @ManyToOne
    @JoinColumn(name = "conversazione_id")
    private Conversazione conversazione;

    @ManyToOne
    @JoinColumn(name = "destinatario")
    private Accademico destinatario;

    public Accademico getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Accademico destinatario) {
        this.destinatario = destinatario;
    }

    public Accademico getAutore() {
        return autore;
    }

    public void setAutore(Accademico autore) {
        this.autore = autore;
    }

    public Messaggio() {}

    public Messaggio(Accademico autore, Accademico destinatario, String topic, String body, LocalDateTime dateTime) {
        this.autore = autore;
        this.destinatario = destinatario;
        this.topic = topic;
        this.body = body;
        this.dateTime = dateTime;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

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
