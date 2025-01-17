package it.unisa.uniclass.esami.model;

import it.unisa.uniclass.orari.model.Aula;
import it.unisa.uniclass.orari.model.Corso;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static it.unisa.uniclass.esami.model.Appello.*;

/**
 * Rappresenta un appello di esame associato a un corso e gestito da docenti.
 * Contiene informazioni su data, orari, stato, aula, corso e docenti associati.
 * */
@Entity
@Table(name = "appelli")
@NamedQueries({
        @NamedQuery(name = TROVA_APPELLO, query = "SELECT a FROM Appello a WHERE a.id = :id"),
        @NamedQuery(name = TROVA_APPELLO_AULA, query = "SELECT a FROM Appello a WHERE a.aula.nome = :nomeAula"),
        @NamedQuery(name = TROVA_APPELLO_CORSO, query = "SELECT a FROM Appello a WHERE a.corso.nome = :corso"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT a FROM Appello a")
})
public class Appello implements Serializable {
    /**
     * Nome della query per trovare un appello specifico in base all'ID
     * */
    public static final String TROVA_APPELLO_CORSO = "Appello.trovaAppelloCorso";
    /**
     * Nome della query per trovare un appello in base all'ID.
     * */
    public static final String TROVA_APPELLO = "Appello.trovaAppello";
    /**
     * Nome della query per trovare un appello in base al nome dell'aula.
     * */
    public static final String TROVA_APPELLO_AULA = "Appello.trovaAppelloAula";
    /**
     * Nome della query per ottenere tutti gli appelli.
     * */
    public static final String TROVA_TUTTI = "Appello.trovaTutti";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate data;
    private Time oraInizio;
    private Time oraFine;
    private Stato stato; //Aperto, chiuso

    @OneToMany(mappedBy = "appello", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppelloDocente> appelloDocenti = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "corso", nullable = false)
    private Corso corso;

    @OneToMany(mappedBy = "appello", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prenotazione> prenotazioni;

    @ManyToOne
    @JoinColumn(name = "aula_id") // Colonna che mappa l'aula
    private Aula aula;

    /**
     * Costruttore completo per creare un appello.
     *
     * @param data  La data dell'Appello
     * @param oraInizio  L'orario di inizio
     * @param aula  L'aula dove si terrà l'esame
     * @param oraFine   L'orario di fine
     * @param appelloDocenti     I docenti associati all'appello.
     * @param corso     Il corso associato all'appello
     * @param stato     Lo stato dell'appello (aperto o chiuso).
     * */
    public Appello(LocalDate data, Time oraInizio, Aula aula, Time oraFine, List<AppelloDocente> appelloDocenti, Corso corso, Stato stato) {
        this.data = data;
        this.oraInizio = oraInizio;
        this.aula = aula;
        this.oraFine = oraFine;
        this.appelloDocenti = appelloDocenti;
        this.corso = corso;
        this.prenotazioni = new ArrayList<>();
        this.stato = stato;
    }

    /**
     * Costruttore alternativo per creare un appello senza docenti associati.
     *
     * @param data  La data dell'Appello
     * @param oraInizio  L'orario di inizio
     * @param oraFine   L'orario di fine
     * @param aula  L'aula dove si terrà l'esame
     * @param corso     Il corso associato all'appello
     * @param stato     Lo stato dell'appello (aperto o chiuso).
     * */
    public Appello(LocalDate data, Time oraInizio, Time oraFine, Aula aula, Corso corso, Stato stato) {
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.aula = aula;
        this.corso = corso;
        this.appelloDocenti = new ArrayList<>();
        this.prenotazioni = new ArrayList<>();
        this.stato = stato;
    }

    /**
     * Costruttore di default per JPA.
     * */
    public Appello() {}

    /**
     * Restituisce la data dell'appello
     *
     * @return La data dell'appello
     * */
    public LocalDate getData() {
        return data;
    }

    /**
     * Imposta la data dell'appello
     *
     * @param data La data dell'appello
     * */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Restituisce l'orario di inizio dell'appello.
     *
     * @return L'orario di inizio.
     */
    public Time getOraInizio() {
        return oraInizio;
    }

    /**
     * Imposta l'orario di inizio dell'appello.
     *
     * @param oraInizio L'orario di inizio.
     */
    public void setOraInizio(Time oraInizio) {
        this.oraInizio = oraInizio;
    }

    /**
     * Restituisce l'orario di fine dell'appello.
     *
     * @return L'orario di fine.
     */
    public Time getOraFine() {
        return oraFine;
    }

    /**
     * Imposta l'orario di fine dell'appello.
     *
     * @param oraFine L'orario di fine.
     */
    public void setOraFine(Time oraFine) {
        this.oraFine = oraFine;
    }

    /**
     * Restituisce l'aula dell'appello.
     *
     * @return L'aula dell'appello.
     */
    public Aula getAula() {
        return aula;
    }

    /**
     * Imposta l'aula dell'appello.
     *
     * @param aula L'aula dell'appello.
     */
    public void setAula(Aula aula) {
        this.aula = aula;
    }

    /**
     * Restituisce la lista dei docenti associati all'appello.
     *
     * @return La lista dei docenti associati.
     */
    public List<AppelloDocente> getAppelloDocenti() {
        return appelloDocenti;
    }

    /**
     * Imposta la lista dei docenti associati all'appello.
     *
     * @param appelloDocenti La lista dei docenti associati.
     */
    public void setAppelloDocenti(List<AppelloDocente> appelloDocenti) {
        this.appelloDocenti = appelloDocenti;
    }

    /**
     * Restituisce il corso associato all'appello.
     *
     * @return Il corso associato.
     */
    public Corso getCorso() {
        return corso;
    }

    /**
     * Imposta il corso associato all'appello.
     *
     * @param corso Il corso associato.
     */
    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    /**
     * Restituisce l'ID dell'appello.
     *
     * @return L'ID dell'appello.
     */
    public Long getId() {
        return id;
    }

    /**
     * Restituisce la lista di prenotazioni associate.
     *
     * @return la lista di prenotazioni.
     */
    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    /**
     * Imposta una nuova lista di prenotazioni.
     *
     * @param prenotazioni la nuova lista di prenotazioni da impostare.
     */
    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    /**
     * Restituisce lo stato corrente.
     *
     * @return lo stato corrente.
     */
    public Stato getStato() {
        return stato;
    }

    /**
     * Imposta un nuovo stato.
     *
     * @param stato il nuovo stato da impostare.
     */
    public void setStato(Stato stato) {
        this.stato = stato;
    }

    /**
     * Restituisce una rappresentazione stringa dell'appello.
     *
     * @return Una stringa contenente i dettagli dell'appello.
     * */
    @Override
    public String toString() {
        return "Appello{" +
                "id=" + id +
                ", data=" + data +
                ", oraInizio=" + oraInizio +
                ", oraFine=" + oraFine +
                ", aula='" + aula + '\'' +
                ", appelloDocenti=" + appelloDocenti +
                ", corso=" + corso +
                '}';
    }
}
