package it.unisa.uniclass.esami.model;

import it.unisa.uniclass.orari.model.Corso;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "appelli")
public class Appello implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate data;
    private Time oraInizio;
    private Time oraFine;
    private String aula;
    private String edificio;
    private Stato stato; //Aperto, chiuso

    @OneToMany(mappedBy = "appello", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppelloDocente> appelloDocenti = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "corso", nullable = false)
    private Corso corso;

    @OneToMany(mappedBy = "appello", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prenotazione> prenotazioni;


    public Appello(LocalDate data, Time oraInizio, String aula, Time oraFine, String edificio, List<AppelloDocente> appelloDocenti, Corso corso, Stato stato) {
        this.data = data;
        this.oraInizio = oraInizio;
        this.aula = aula;
        this.oraFine = oraFine;
        this.edificio = edificio;
        this.appelloDocenti = appelloDocenti;
        this.corso = corso;
        this.prenotazioni = new ArrayList<>();
        this.stato = stato;
    }

    public Appello(LocalDate data, Time oraInizio, Time oraFine, String aula, String edificio, Corso corso, Stato stato) {
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.aula = aula;
        this.edificio = edificio;
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

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
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
                ", edificio='" + edificio + '\'' +
                ", appelloDocenti=" + appelloDocenti +
                ", corso=" + corso +
                '}';
    }
}
