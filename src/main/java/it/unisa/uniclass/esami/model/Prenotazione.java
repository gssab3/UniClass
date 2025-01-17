package it.unisa.uniclass.esami.model;

import it.unisa.uniclass.utenti.model.Studente;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import java.io.Serializable;
import java.time.LocalDate;

import static it.unisa.uniclass.esami.model.Prenotazione.*;

/**
 * Classe che rappresenta una prenotazione per un appello d'esame.
 * */
@Entity
@Table(name = "prenotazioni")
@NamedQueries({
        @NamedQuery(name = TROVA_STUDENTE, query = "SELECT p FROM Prenotazione p WHERE p.studente.matricola = :matricola"),
        @NamedQuery(name = TROVA_APPELLO, query = "SELECT p FROM Prenotazione p WHERE p.appello.id = :id"),
        @NamedQuery(name = TROVA_PRENOTAZIONE, query = "SELECT p FROM Prenotazione p WHERE p.id = :id"),
        @NamedQuery(name = TROVA_TUTTE, query = "SELECT p FROM Prenotazione p")
})
public class Prenotazione implements Serializable {

    /**
     * Nome della query per trovare una prenotazione per studente.
     * */
    public static final String TROVA_STUDENTE = "Prenotazione.trovaStudente";
    /**
     * Nome della query per trovare una prenotazione per appello.
     * */
    public static final String TROVA_APPELLO = "Prenotazione.trovaAppello";
    /**
     * Nome della query per trovare una specifica prenotazione tramite ID
     * */
    public static final String TROVA_PRENOTAZIONE = "Prenotazione.trovaPrenotazione";
    /**
     * Nome della query per trovare tutte le prenotazioni.
     * */
    public static final String TROVA_TUTTE = "Prenotazione.trovaTutte";

    /**
     * Identificatore univoco della prenotazione
     * */
    @Id @GeneratedValue
    private long id;

    /**
     * Lo studente associato alla prenotazione
     *
     * */
    @ManyToOne
    @JoinColumn(name = "studente", nullable = false)
    private Studente studente;

    /**
     * L'appello associato alla prenotazione
     * */
    @ManyToOne
    @JoinColumn(name = "appello", nullable = false)
    private Appello appello;

    /**
     * La data in cui la prenotazione è stata effettuata
     * */
    @Column(name = "dataPrenotazione", nullable = false)
    private LocalDate dataPrenotazione;

    /**
     * Costruttore predefinito della classe
     * */
    public Prenotazione() {}

    /**
     * Costruttore della classe che permette di creare una prenotazione specificando lo studente, l'appello e la data di prenotazione.
     *
     * @param studente  Lo studente che effettua la Prenotazione
     * @param appello   Appello a cui  è associata la prenotazione
     * @param dataPrenotazione  La data in cui è stata effettuata la prenotazione.
     * */
    public Prenotazione(Studente studente, Appello appello, LocalDate dataPrenotazione) {
        this.studente = studente;
        this.appello = appello;
        this.dataPrenotazione = dataPrenotazione;
    }

    /**
     * Restituisce lo studente assocaito alla prenotazione
     *
     * @return Lo studente della prenotazione
     * */
    public Studente getStudente() {
        return studente;
    }

    /**
     * Imposta lo studente associato alla prenotazione
     *
     * @param studente Lo student edella prenotazione
     * */
    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    /**
     * Restituisce l'appello associato alla prenotazione
     *
     * @return L'appello della prenotazione
     * */
    public Appello getAppello() {
        return appello;
    }

    /**
     * Imposta l'appello associato alla prenotazione
     *
     * @param appello L'appello della prenotazione
     * */
    public void setAppello(Appello appello) {
        this.appello = appello;
    }

    /**
     * Restituisce la data in cui la prenotazione è stata effettuata
     *
     * @return La data della prenotaizone
     * */
    public LocalDate getDataPrenotazione() {
        return dataPrenotazione;
    }

    /**
     * Imposta la data in cui la prenotazione è stata effettuata
     *
     * @param dataPrenotazione La data delle prenotazione
     * */
    public void setDataPrenotazione(LocalDate dataPrenotazione) {
        this.dataPrenotazione = dataPrenotazione;
    }

    /**
     * Restituisce l'identificatore univoco della prenotazione
     *
     * @return L'ID della prenotazione
     * */
    public long getId() {
        return id;
    }

    /**Restituisce una rappresentazione in formato stringa dell'oggetto Prenotazione
     *
     * @return La stringa rappresentativa della prenotazione
     * */
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
