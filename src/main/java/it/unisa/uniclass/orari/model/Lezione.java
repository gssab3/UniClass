package it.unisa.uniclass.orari.model;

import it.unisa.uniclass.utenti.model.Docente;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

import static it.unisa.uniclass.orari.model.Lezione.*;

/**
 * Rappresenta una lezione nel sistema di gestione orari.
 * Contiene informazioni come la data, l'orario, il giorno, il corso associato, l'aula e altre proprietà rilevanti.
 * */

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
                "AND a.id = :annoId AND l.semestre = :semestre"),
        @NamedQuery(name = TROVA_LEZIONI_DOCENTE, query = "SELECT l FROM Lezione l JOIN l.docenti d WHERE d.nome = :nomeDocente")
})
public class Lezione implements Serializable {

    /**
     * Query per trovare una lezione tramite ID
     * */
    public final static String TROVA_LEZIONE = "Lezione.trovaLezione";
    /**
     * Query per trovare lezioni associate a un corso specifico.
     * */
    public final static String TROVA_LEZIONE_CORSO = "Lezione.trovaLezioneCorso";
    /**
     * Query per trovare lezioni in base all'orario di inizio e fine.
     * */
    public final static String TROVA_LEZIONE_ORE = "Lezione.trovaLezioneOre";
    /**
     * Query per trovare lezioni in base all'orario e al giorno
     * */
    public final static String TROVA_LEZIONE_ORE_GIORNO = "Lezione.trovaLezioneOreGiorno";
    /**
     * Query per trovare lezioni in base all'aula
     * */
    public static final String TROVA_LEZIONE_AULA = "Lezione.trovaLezioneAula";
    /**
     * Query per trovare tutte le lezioni.
     * */
    public static final String TROVA_TUTTE = "Lezione.trovaTutte";
    /**
     * Query per trovare lezioni in base al corso, resto e anno.
     * */
    public static final String TROVA_LEZIONE_CORSO_RESTO_ANNO = "Lezione.trovaLezioneCorsoRestoAnno";
    /**
     * Query per trovare lezioni in base al corso, resto, anno e semestre.
     * */
    public static final String TROVA_LEZIONE_CORSO_RESTO_ANNO_SEMESTRE = "Lezione.trovaLezioneCorsoRestoAnnoSemestre";
    /**
     * Query per trovare lezioni di uno specifico docente.
     * */
    public static final String TROVA_LEZIONI_DOCENTE = "Lezione.trovaLezioniDocente";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(mappedBy = "lezioni") // Relazione inversa
    private List<Agenda> agende;

    @ManyToMany(mappedBy = "lezioni")
    private List<Docente> docenti = new ArrayList<>();

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
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "aula_id")
    private Aula aula;

    /**
     *
     * Costruttore predefinito.
     * */
    public Lezione() {}

    /**
     * Costruttore con parametri.
     *
     * @param oraInizio L'orario di inizio.
     * @param semestre Il semestre considerato.
     * @param oraFine L'orario di fine.
     * @param giorno Il giorno della settimana,.
     * @param resto Informazioni aggiuntive sulla lezione.
     * @param corso Il corso associato.
     * @param aula L'aula della lezione
     * */

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

    public List<Docente> getDocenti() {
        return docenti;
    }

    public void setDocenti(List<Docente> docenti) {
        this.docenti = docenti;
    }

    public List<Agenda> getAgende() {
        return agende;
    }

    public void setAgende(List<Agenda> agende) {
        this.agende = agende;
    }

    /**
     * Ottiene il semestre in cui è presente della lezione.
     *
     * @return semestre della lezione
     * */
    public int getSemestre() {
        return semestre;
    }

    /**Imposta il semestre in cui è presente la lezione.
     *
     * @param semestre Semestre in cui è presente la lezione.
     * */
    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    /**
     * Ottiene l'ora di inizio della lezione.
     *
     * @return ora dell'inizio della lezione
     * */
    public Time getOraInizio() {
        return oraInizio;
    }

    /**Imposta l'ora di inizio della lezione.
     *
     * @param oraInizio L'ora di inzio della lezione.
     * */
    public void setOraInizio(Time oraInizio) {
        this.oraInizio = oraInizio;
    }

    /**
     * Ottiene l'ora di fine della lezione.
     *
     * @return ora di fine della lezione
     * */
    public Time getOraFine() {
        return oraFine;
    }

    /**Imposta l'ora di fine della lezione.
     *
     * @param oraFine L'ora di inzio della lezione.
     * */
    public void setOraFine(Time oraFine) {
        this.oraFine = oraFine;
    }

    @Enumerated(EnumType.STRING)

    /**
     * Ottiene il giorno della lezione.
     *
     * @return giorno della lezione
     * */
    public Giorno getGiorno() {
        return giorno;
    }

    /**Imposta il giorno della lezione.
     *
     * @param giorno Giorno della lezione.
     * */
    public void setGiorno(Giorno giorno) {
        this.giorno = giorno;
    }

    /**
     * Ottiene il resto in cui è presente la lezione.
     *
     * @return resto in cui è presente la lezione
     * */
    public Resto getResto() {
        return resto;
    }

    /**Imposta il resto in cui è presente la lezione.
     *
     * @param resto Resto in cui è presente la lezione.
     * */
    public void setResto(Resto resto) {
        this.resto = resto;
    }

    /**
     * Ottiene l'id della lezione.
     *
     * @return id della lezione
     * */
    public Long getId() {
        return id;
    }

    /**
     * Ottiene il corso in cui è presente la lezione.
     *
     * @return il corso in cui è presente la lezione
     * */
    public Corso getCorso() {
        return corso;
    }

    /**
     * Ottiene l'aula della lezione.
     *
     * @return aula della lezione
     * */
    public Aula getAula() {
        return aula;
    }

    /**Imposta l'aula in cui è presente la lezione.
     *
     * @param aula Aula in cui è presente la lezione.
     * */
    public void setAula(Aula aula) {
        this.aula = aula;
    }

    /**Imposta il corso in cui è presente la lezione.
     *
     * @param corso Corso in cui è presente la lezione.
     * */
    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto.
     *
     * @return Stringa rappresentativa della lezione.
     * */
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