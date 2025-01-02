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

@Entity
@Table(name = "appelli")
@NamedQueries({
        @NamedQuery(name = TROVA_APPELLO, query = "SELECT a FROM Appello a WHERE a.id = :id"),
        @NamedQuery(name = TROVA_APPELLO_AULA, query = "SELECT a FROM Appello a WHERE a.aula.nome = :nomeAula"),
        @NamedQuery(name = TROVA_APPELLO_CORSO, query = "SELECT a FROM Appello a WHERE a.corso.nome = :corso"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT a FROM Appello a")
})
public class Appello implements Serializable {
    public static final String TROVA_APPELLO_CORSO = "Appello.trovaAppelloCorso";
    public static final String TROVA_APPELLO = "Appello.trovaAppello";
    public static final String TROVA_APPELLO_AULA = "Appello.trovaAppelloAula";
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

    public Appello() {}

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Time getOraInizio() {
        return oraInizio;
    }

    public void setOraInizio(Time oraInizio) {
        this.oraInizio = oraInizio;
    }

    public Time getOraFine() {
        return oraFine;
    }

    public void setOraFine(Time oraFine) {
        this.oraFine = oraFine;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public List<AppelloDocente> getAppelloDocenti() {
        return appelloDocenti;
    }

    public void setAppelloDocenti(List<AppelloDocente> appelloDocenti) {
        this.appelloDocenti = appelloDocenti;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    public Long getId() {
        return id;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

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
