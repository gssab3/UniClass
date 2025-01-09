package it.unisa.uniclass.orari.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import static it.unisa.uniclass.orari.model.Lezione.*;

@Entity
@Table(name = "lezioni")
@NamedQueries({
        @NamedQuery(name = TROVA_LEZIONE, query = "SELECT l FROM Lezione l WHERE l.id = :id"),
        @NamedQuery(name = TROVA_LEZIONE_CORSO, query = "SELECT l FROM Lezione l WHERE l.corso.nome = :nomeCorso"),
        @NamedQuery(name = TROVA_LEZIONE_ORE, query = "SELECT l FROM Lezione l WHERE l.oraInizio = :oraInizio AND l.oraFine = :oraFine"),
        @NamedQuery(name = TROVA_LEZIONE_ORE_GIORNO, query = "SELECT l FROM Lezione l WHERE l.giorno = :giorno AND l.oraInizio = :oraInizio AND l.oraFine = :oraFine"),
        @NamedQuery(name = TROVA_LEZIONE_AULA, query = "SELECT l FROM Lezione l WHERE l.aula.nome = :nome"),
        @NamedQuery(name = TROVA_TUTTE, query = "SELECT l FROM Lezione l"),
        @NamedQuery(name = TROVA_LEZIONE_CORSO_RESTO_ANNO,
                query = "SELECT l FROM Lezione l " +
                        "JOIN l.corso c " +
                        "JOIN c.corsoLaurea cl " +
                        "JOIN l.resto r " +
                        "JOIN c.annoDidattico a " +
                        "WHERE cl.id = :corsoLaureaId " +
                        "AND r.id = :restoId " +
                        "AND a.id = :annoId"),
        @NamedQuery(name = TROVA_LEZIONE_CORSO_RESTO_ANNO_SEMESTRE, query = "SELECT l FROM Lezione l " +
                "JOIN l.corso c " +
                "JOIN c.corsoLaurea cl " +
                "JOIN l.resto r " +
                "JOIN c.annoDidattico a " +
                "WHERE cl.id = :corsoLaureaId " +
                "AND r.id = :restoId " +
                "AND a.id = :annoId AND l.semestre = :semestre")

})
public class Lezione implements Serializable {

    public final static String TROVA_LEZIONE = "Lezione.trovaLezione";
    public final static String TROVA_LEZIONE_CORSO = "Lezione.trovaLezioneCorso";
    public final static String TROVA_LEZIONE_ORE = "Lezione.trovaLezioneOre";
    public final static String TROVA_LEZIONE_ORE_GIORNO = "Lezione.trovaLezioneOreGiorno";
    public static final String TROVA_LEZIONE_AULA = "Lezione.trovaLezioneAula";
    public static final String TROVA_TUTTE = "Lezione.trovaTutte";
    public static final String TROVA_LEZIONE_CORSO_RESTO_ANNO = "Lezione.trovaLezioneCorsoRestoAnno";
    public static final String TROVA_LEZIONE_CORSO_RESTO_ANNO_SEMESTRE = "Lezione.trovaLezioneCorsoRestoAnnoSemestre";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "lezioni") // Relazione inversa
    private List<Agenda> agende;

    private int semestre; //1 o 2
    private Time oraInizio;
    private Time oraFine;
    @Enumerated(EnumType.STRING)
    private Giorno giorno;
    @ManyToOne
    @JoinColumn(name = "corso_id")
    private Corso corso;
    @ManyToOne
    @JoinColumn(name = "resto_id")
    private Resto resto;
    @ManyToOne
    private Aula aula;

    public Lezione() {}

    public Lezione(int semestre, Time oraInizio, Time oraFine, Giorno giorno, Resto resto, Corso corso, Aula aula) {
        this.oraInizio = oraInizio;
        this.semestre = semestre;
        this.oraFine = oraFine;
        this.giorno = giorno;
        this.resto = resto;
        this.corso = corso;
        this.aula = aula;
        this.agende = new ArrayList<>();
    }

    public List<Agenda> getAgende() {
        return agende;
    }

    public void setAgende(List<Agenda> agende) {
        this.agende = agende;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
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

    public Resto getResto() {
        return resto;
    }

    public void setResto(Resto resto) {
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
                ", semestre=" + semestre +
                ", oraInizio=" + oraInizio +
                ", oraFine=" + oraFine +
                ", giorno=" + giorno +
                ", corso=" + corso +
                ", resto=" + resto +
                ", aula=" + aula +
                '}';
    }
}