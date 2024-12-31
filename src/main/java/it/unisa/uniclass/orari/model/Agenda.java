package it.unisa.uniclass.orari.model;

import it.unisa.uniclass.utenti.model.Studente;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "agende")
public class Agenda implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    @JoinColumn(name = "studente")
    private Studente studente;

    @OneToMany(mappedBy = "agenda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lezione> lezioni;

    public Agenda() {
        this.lezioni = Collections.emptyList();
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
                ", lezioni=" + lezioni +
                '}';
    }
}