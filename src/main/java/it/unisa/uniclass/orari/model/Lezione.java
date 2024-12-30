package it.unisa.uniclass.orari.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "lezioni")
public class Lezione {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate data;
    private Time oraInizio;
    private Time oraFine;
    private Giorno giorno;
    @ManyToOne
    @JoinColumn(name = "corso_id")
    private Corso corso;
    private String resto;
    private String edificio;

    public Lezione() {}

    public Lezione(LocalDate data, Time oraInizio, Time oraFine, Giorno giorno, String resto, String edificio) {
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.giorno = giorno;
        this.resto = resto;
        this.edificio = edificio;
    }

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

    @Enumerated(EnumType.STRING)
    public Giorno getGiorno() {
        return giorno;
    }

    public void setGiorno(Giorno giorno) {
        this.giorno = giorno;
    }

    public String getResto() {
        return resto;
    }

    public void setResto(String resto) {
        this.resto = resto;
    }

    public String getEdificio() {
        return edificio;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public Long getId() {
        return id;
    }

    public Corso getCorso() {
        return corso;
    }

    @Override
    public String toString() {
        return "Lezione{" +
                "id=" + id +
                ", data=" + data +
                ", oraInizio=" + oraInizio +
                ", oraFine=" + oraFine +
                ", giorno=" + giorno +
                ", corso=" + corso +
                ", resto='" + resto + '\'' +
                ", edificio='" + edificio + '\'' +
                '}';
    }
}