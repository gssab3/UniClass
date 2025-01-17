package it.unisa.uniclass.orari.model;

import it.unisa.uniclass.utenti.model.Studente;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static it.unisa.uniclass.orari.model.Agenda.*;

/**
 * Classe rappresentante un'Agenda associata a uno studente.
 * Contiene informazioni sulle lezioni e il relativo studente.
 * Mappata come entità per il framework JPA.
 * */
@Entity
@Table(name = "agende")
@NamedQueries({
        @NamedQuery(name = TROVA_AGENDA_ID, query = "SELECT a FROM Agenda a WHERE a.id = :id"),
        @NamedQuery(name = TROVA_AGENDA_STUDENTE, query = "SELECT a FROM Agenda a WHERE a.studente.matricola = :matricola"),
        @NamedQuery(name = TROVA_TUTTE, query = "SELECT a FROM Agenda a")
})
public class Agenda implements Serializable {

    /**
     * Nome delle query per trovare un'agenda tramite il suo ID.
     * */
    public static final String TROVA_AGENDA_ID = "Agenda.trovaAgendaId";
    /**
     * Nome della query per trovare un'agenda associata a uno studente tramite la matricola.
     * */
    public static final String TROVA_AGENDA_STUDENTE = "Agenda.trovaAgendaStudente";
    /**
     * Nome della query per trovare tutte le agende
     * */
    public static final String TROVA_TUTTE = "Agenda.trovaTutte";

    /**
     * Identificativo univoco dell'agenda. Generato automaticamente
     * */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**Studente associato all'agenda.
     * */
    @OneToOne
    @JoinColumn(name = "studente")
    private Studente studente;

    /**
     * Lista di lezioni associate all'agenda.
     * */
    @ManyToMany
    @JoinTable(
            name = "agenda_lezione", // Nome della tabella di join
            joinColumns = @JoinColumn(name = "agenda_id"), // Colonna che fa riferimento ad Agenda
            inverseJoinColumns = @JoinColumn(name = "lezione_id") // Colonna che fa riferimento a Lezione
    )
    private List<Lezione> lezioni = new ArrayList<>() ;

    /**
     * Costruttore predefinito. Inizializza la lista di lezioni come vuota.
     * */
    public Agenda() {
    }

    /**
     * Costruttore che associa un'agenda a uno specifico studente.
     *
     * @param studente Lo studente associato.
     * */
    public Agenda(Studente studente) {
        this.studente = studente;
        this.lezioni = Collections.emptyList();
    }

    /**
     * Restituisce lo studente associato all'agenda.
     *
     * @return Lo studente.
     * */
    public Studente getStudente() {
        return studente;
    }

    /**
     * Imposta lo studenre associato all'agenda.
     *
     * @param studente Lo studente da associare
     * */
    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    /**
     * Restituisce la lista di lezioni associate all'agenda
     *
     * @return Lista di lezioni.
     * */
    public List<Lezione> getLezioni() {
        return lezioni;
    }

    /**
     * Imposta la lista di lezioni associate all'agenda.
     *
     * @param lezioni La nuova lista di lezioni
     * */
    public void setLezioni(List<Lezione> lezioni) {
        this.lezioni = lezioni;
    }

    /**
     * Restituisce l'Identificativo univoco dell'Agenda
     *
     * @return l'ID dell'Agenda
     * */
    public long getId() {
        return id;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Agenda.
     *
     * @return Una stringa contenente i dettagli dell'Agenda.
     * */
    @Override
    public String toString() {
        return "Agenda{" +
                "id=" + id +
                ", studente=" + studente +
                '}';
    }
}