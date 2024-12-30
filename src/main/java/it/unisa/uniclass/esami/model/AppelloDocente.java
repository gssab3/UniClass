package it.unisa.uniclass.esami.model;

import it.unisa.uniclass.orari.model.Ruolo;
import it.unisa.uniclass.utenti.model.Docente;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "appello_docente")
public class AppelloDocente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "appello_id", nullable = false)
    private Appello appello;

    @ManyToOne
    @JoinColumn(name = "docente_id", nullable = false)
    private Docente docente;

    @Enumerated(EnumType.STRING)
    private Ruolo ruolo; // Ruolo: PRESIDENTE o DOCENTE

    public AppelloDocente() {}

    public AppelloDocente(Appello appello, Docente docente, Ruolo ruolo) {
        this.appello = appello;
        this.docente = docente;
        this.ruolo = ruolo;
    }

    public Long getId() {
        return id;
    }

    public Appello getAppello() {
        return appello;
    }

    public void setAppello(Appello appello) {
        this.appello = appello;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Ruolo getRuolo() {
        return ruolo;
    }

    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    @Override
    public String toString() {
        return "AppelloDocente{" +
                "id=" + id +
                ", appello=" + (appello != null ? appello.getId() : "null") +
                ", docente=" + (docente != null ? docente.getNome() + " " + docente.getCognome() : "null") +
                ", ruolo=" + ruolo +
                '}';
    }
}
