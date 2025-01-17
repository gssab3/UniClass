package it.unisa.uniclass.esami.model;

import it.unisa.uniclass.orari.model.Ruolo;
import it.unisa.uniclass.utenti.model.Docente;
import jakarta.persistence.*;

import java.io.Serializable;

import static it.unisa.uniclass.esami.model.AppelloDocente.*;

/**
 * Entità che rappresenta la relazione tra un appello e un docente.
 * Ogni associazione include il ruolo del docente nell'appello.
 * */
@Entity
@Table(name = "appello_docente")
@NamedQueries({
    @NamedQuery(name = TROVA_APPELLO, query = "SELECT ad FROM AppelloDocente ad WHERE ad.appello.id = :id"),
    @NamedQuery(name = TROVA_DOCENTE, query = "SELECT ad FROM AppelloDocente ad WHERE ad.docente.matricola = :matricola")
})
public class AppelloDocente implements Serializable {

    /**
     * Costante per identificare la query che trova un appello dato il suo ID
     * */
    public static final String TROVA_APPELLO = "AppelloDocente.trovaAppello";
    /**
     * Costante per identificare la query che trova un docente dato il suo numero di matricola.
     * */
    public static final String TROVA_DOCENTE = "AppelloDocente.trovaDocente";

    /**
     * Identificativo unico dell'associazione AppelloDocente
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Appello associato a questa relazione.
     * Non può essere null.
     * */
    @ManyToOne
    @JoinColumn(name = "appello_id", nullable = false)
    private Appello appello;

    /**
     * Docente assocaito a questa relaizone.
     * Non può essere null.
     * */
    @ManyToOne
    @JoinColumn(name = "docente_id", nullable = false)
    private Docente docente;

    /**
     * Ruolo del docente dell'appello (es. PRESIDENTE o DOCENTE)
     * */
    @Enumerated(EnumType.STRING)
    private Ruolo ruolo; // Ruolo: PRESIDENTE o DOCENTE

    /**
     * Costruttore vuoto richiesto per JPA.
     * */
    public AppelloDocente() {}

    /**
     * Costruttore che inizializza un'isatnza di AppelloDocente
     *
     * @param appello Appello associato
     * @param docente Docente associato
     * @param ruolo Ruolo del docente nell'appello
     * */
    public AppelloDocente(Appello appello, Docente docente, Ruolo ruolo) {
        this.appello = appello;
        this.docente = docente;
        this.ruolo = ruolo;
    }

    /**
     * Restituisce l'ID dell'associazione.
     *
     * @return Identificativo univoco
     * */
    public Long getId() {
        return id;
    }

    /**
     * Restituisce l'appello associato
     *
     * @return L'Appello
     * */
    public Appello getAppello() {
        return appello;
    }

    /**
     * Imposta l'appello associato
     *
     * @param appello Nuovo appello
     * */
    public void setAppello(Appello appello) {
        this.appello = appello;
    }

    /**
     * Restituisce il docete associato
     *
     * @return Il docente
     * */
    public Docente getDocente() {
        return docente;
    }

    /**
     * Imposta il docente associato
     *
     * @param docente Nuovo docente.
     * */
    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    /**
     * Restituisce il ruolo del docente nell'appello.
     *
     * @return Il ruolo
     * */
    public Ruolo getRuolo() {
        return ruolo;
    }

    /**
     * Imposta il ruolo del docente nell'appello.
     *
     * @param ruolo Nuovo ruolo
     * */
    public void setRuolo(Ruolo ruolo) {
        this.ruolo = ruolo;
    }

    /**
     * Restituisce una rappresentazione testuale dell'istanza.
     *
     * @return Stringa che rappresenta l'oggetto.
     * */
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
