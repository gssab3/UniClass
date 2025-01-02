package it.unisa.uniclass.orari.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;

import static it.unisa.uniclass.orari.model.Lezione.*;

@Entity
@Table(name = "lezioni")
@NamedQueries({
        @NamedQuery(name = TROVA_LEZIONE, query = "SELECT l FROM Lezione l WHERE l.id = :id"),
        @NamedQuery(name = TROVA_LEZIONE_CORSO, query = "SELECT l FROM Lezione l WHERE l.corso.nome = :nomeCorso"),
        @NamedQuery(name = TROVA_LEZIONE_ORE, query = "SELECT l FROM Lezione l WHERE l.oraInizio = :oraInizio AND l.oraFine = :oraFine"),
        @NamedQuery(name = TROVA_LEZIONE_ORE_GIORNO, query = "SELECT l FROM Lezione l WHERE l.giorno = :giorno AND l.oraInizio = :oraInizio AND l.oraFine = :oraFine"),
        @NamedQuery(name = TROVA_LEZIONE_AULA, query = "SELECT l FROM Lezione l WHERE l.aula.nome = :nome"),
        @NamedQuery(name = TROVA_TUTTE, query = "SELECT l FROM Lezione l")
})
public class Lezione {

    public final static String TROVA_LEZIONE = "Lezione.trovaLezione";
    public final static String TROVA_LEZIONE_CORSO = "Lezione.trovaLezioneCorso";
    public final static String TROVA_LEZIONE_ORE = "Lezione.trovaLezioneOre";
    public final static String TROVA_LEZIONE_ORE_GIORNO = "Lezione.trovaLezioneOreGiorno";
    public static final String TROVA_LEZIONE_AULA = "Lezione.trovaLezioneAula";
    public static final String TROVA_TUTTE = "Lezione.trovaTutte";


    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate data;
    private Time oraInizio;
    private Time oraFine;
    @Enumerated(EnumType.STRING)
    private Giorno giorno;
    @ManyToOne
    @JoinColumn(name = "corso_id")
    private Corso corso;
    private String resto;
    @ManyToOne
    private Aula aula;

    public Lezione() {}

    public Lezione(LocalDate data, Time oraInizio, Time oraFine, Giorno giorno, String resto, Corso corso, Aula aula) {
        this.data = data;
        this.oraInizio = oraInizio;
        this.oraFine = oraFine;
        this.giorno = giorno;
        this.resto = resto;
        this.corso = corso;
        this.aula = aula;
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

    public Long getId() {
        return id;
    }

    public Corso getCorso() {
        return corso;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
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
                '}';
    }
}