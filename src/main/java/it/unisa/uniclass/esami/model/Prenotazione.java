package it.unisa.uniclass.esami.model;

import it.unisa.uniclass.utenti.model.Studente;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "prenotazioni")
public class Prenotazione implements Serializable {
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
