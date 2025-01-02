package it.unisa.uniclass.esami.model;

import it.unisa.uniclass.orari.model.Ruolo;
import it.unisa.uniclass.utenti.model.Docente;
import jakarta.persistence.*;

import java.io.Serializable;

import static it.unisa.uniclass.esami.model.AppelloDocente.*;

@Entity
@Table(name = "appello_docente")
@NamedQueries({
    @NamedQuery(name = TROVA_APPELLO, query = "SELECT ad FROM AppelloDocente ad WHERE ad.appello.id = :id"),
    @NamedQuery(name = TROVA_DOCENTE, query = "SELECT ad FROM AppelloDocente ad WHERE ad.docente.matricola = :matricola")
})
public class AppelloDocente implements Serializable {

    public static final String TROVA_APPELLO = "AppelloDocente.trovaAppello";
    public static final String TROVA_DOCENTE = "AppelloDocente.trovaDocente";

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
