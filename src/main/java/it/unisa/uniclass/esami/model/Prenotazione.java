package it.unisa.uniclass.esami.model;

import it.unisa.uniclass.utenti.model.Studente;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import java.io.Serializable;
import java.time.LocalDate;

import static it.unisa.uniclass.esami.model.Prenotazione.*;

@Entity
@Table(name = "prenotazioni")
@NamedQueries({
        @NamedQuery(name = TROVA_STUDENTE, query = "SELECT p FROM Prenotazione p WHERE p.studente.matricola = :matricola"),
        @NamedQuery(name = TROVA_APPELLO, query = "SELECT p FROM Prenotazione p WHERE p.appello.id = :id"),
        @NamedQuery(name = TROVA_PRENOTAZIONE, query = "SELECT p FROM Prenotazione p WHERE p.id = :id"),
        @NamedQuery(name = TROVA_TUTTE, query = "SELECT p FROM Prenotazione p")
})
public class Prenotazione implements Serializable {

    public static final String TROVA_STUDENTE = "Prenotazione.trovaStudente";
    public static final String TROVA_APPELLO = "Prenotazione.trovaAppello";
    public static final String TROVA_PRENOTAZIONE = "Prenotazione.trovaPrenotazione";
    public static final String TROVA_TUTTE = "Prenotazione.trovaTutte";

    @Id @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "studente", nullable = false)
    private Studente studente;

    @ManyToOne
    @JoinColumn(name = "appello", nullable = false)
    private Appello appello;

    @Column(name = "dataPrenotazione", nullable = false)
    private LocalDate dataPrenotazione;

    public Prenotazione() {}

    public Prenotazione(Studente studente, Appello appello, LocalDate dataPrenotazione) {
        this.studente = studente;
        this.appello = appello;
        this.dataPrenotazione = dataPrenotazione;
    }

    public Studente getStudente() {
        return studente;
    }

    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    public Appello getAppello() {
        return appello;
    }

    public void setAppello(Appello appello) {
        this.appello = appello;
    }

    public LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }

    public void setDataPrenotazione(LocalDate dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Prenotazione{" +
                "id=" + id +
                ", studente=" + studente +
                ", appello=" + appello +
                ", dataPrenotazione=" + dataPrenotazione +
                '}';
    }
}
