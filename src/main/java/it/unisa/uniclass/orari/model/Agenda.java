package it.unisa.uniclass.orari.model;

import it.unisa.uniclass.utenti.model.Studente;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static it.unisa.uniclass.orari.model.Agenda.*;

@Entity
@Table(name = "agende")
@NamedQueries({
        @NamedQuery(name = TROVA_AGENDA_ID, query = "SELECT a FROM Agenda a WHERE a.id = :id"),
        @NamedQuery(name = TROVA_AGENDA_STUDENTE, query = "SELECT a FROM Agenda a WHERE a.studente.matricola = :matricola"),
        @NamedQuery(name = TROVA_TUTTE, query = "SELECT a FROM Agenda a")
})
public class Agenda implements Serializable {

    public static final String TROVA_AGENDA_ID = "Agenda.trovaAgendaId";
    public static final String TROVA_AGENDA_STUDENTE = "Agenda.trovaAgendaStudente";
    public static final String TROVA_TUTTE = "Agenda.trovaTutte";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "studente")
    private Studente studente;

    @ManyToMany
    @JoinTable(
            name = "agenda_lezione", // Nome della tabella di join
            joinColumns = @JoinColumn(name = "agenda_id"), // Colonna che fa riferimento ad Agenda
            inverseJoinColumns = @JoinColumn(name = "lezione_id") // Colonna che fa riferimento a Lezione
    )
    private List<Lezione> lezioni = new ArrayList<>() ;

    public Agenda() {
    }

    public Agenda(Studente studente) {
        this.studente = studente;
        this.lezioni = Collections.emptyList();
    }

    public Studente getStudente() {
        return studente;
    }

    public void setStudente(Studente studente) {
        this.studente = studente;
    }

    public List<Lezione> getLezioni() {
        return lezioni;
    }

    public void setLezioni(List<Lezione> lezioni) {
        this.lezioni = lezioni;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "id=" + id +
                ", studente=" + studente +
                '}';
    }
}