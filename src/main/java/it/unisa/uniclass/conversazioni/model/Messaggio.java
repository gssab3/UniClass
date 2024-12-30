package it.unisa.uniclass.conversazioni.model;

import it.unisa.uniclass.utenti.model.Accademico;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "messaggi")
public class Messaggio implements Serializable {
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
