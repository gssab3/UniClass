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

@Entity
@Table(name = "conversazioni")
@NamedQueries({
        @NamedQuery(name = TROVA_CONVERSAZIONE, query = "SELECT c FROM Conversazione c WHERE c.id = :id"),
        @NamedQuery(name = TROVA_TUTTE, query = "SELECT c FROM Conversazione c")
})
public class Conversazione implements Serializable {

    public static final String TROVA_CONVERSAZIONE = "Conversazione.trovaConversazione";
    public static final String TROVA_TUTTE = "Conversazione.trovaTutte";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "conversazione", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Messaggio> messaggi =  new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "conversazione_messaggeri",
            joinColumns = @JoinColumn(name = "conversazione_id"),
            inverseJoinColumns = @JoinColumn(name = "accademico_id")
    )
    private Set<Accademico> messaggeri = new HashSet<>();

    public Conversazione() {}

    public Long getId() {
        return id;
    }

    public List<Messaggio> getMessaggi() {
        return messaggi;
    }

    public Set<Accademico> getMessaggeri() {
        return messaggeri;
    }

    public void setMessaggi(List<Messaggio> messaggi) {
        this.messaggi = messaggi;
    }

    public void setMessaggeri(Set<Accademico> messaggeri) {
        this.messaggeri = messaggeri;
    }

    @Override
    public String toString() {
        return "Conversazione{" +
                "id=" + id +
                ", messaggeri=" + messaggeri +
                '}';
    }
}
