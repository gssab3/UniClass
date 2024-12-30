package it.unisa.uniclass.conversazioni.model;

import it.unisa.uniclass.esami.model.Prenotazione;
import it.unisa.uniclass.utenti.model.Accademico;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conversazioni")
public class Conversazione implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "conversazione", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Messaggio> messaggi;

    @ManyToMany
    @JoinTable(
            name = "conversazione_accademico",
            joinColumns = @JoinColumn(name = "conversazione_id"),
            inverseJoinColumns = @JoinColumn(name = "accademico_id")
    )
    private List<Accademico> messaggeri;

    public Conversazione() {
        this.messaggi =  new ArrayList<>();
        this.messaggeri = new ArrayList<>();
    }

    public Conversazione(List<Accademico> messaggeri) {
        this.messaggeri = new ArrayList<>();
        this.messaggi = new ArrayList<>();
    }

    public List<Messaggio> getMessaggi() {
        return messaggi;
    }

    public void setMessaggi(List<Messaggio> messaggi) {
        this.messaggi = messaggi;
    }

    public List<Accademico> getMessaggeri() {
        return messaggeri;
    }

    public void setMessaggeri(List<Accademico> messaggeri) {
        this.messaggeri = messaggeri;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Conversazione{" +
                "id=" + id +
                ", messaggeri=" + messaggeri +
                '}';
    }
}
