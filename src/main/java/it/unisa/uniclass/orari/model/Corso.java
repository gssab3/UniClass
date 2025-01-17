package it.unisa.uniclass.orari.model;

import it.unisa.uniclass.esami.model.Appello;
import it.unisa.uniclass.utenti.model.Docente;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static it.unisa.uniclass.orari.model.Corso.*;

/**
 * Classe che rappresenta un corso universitario.
 * Un corso è associato a un corso di laurea, lezioni, docenti e appelli d'esame.
 * */
@Entity
@Table(name = "corsi")
@NamedQueries({
    @NamedQuery(name = TROVA_CORSO, query = "SELECT c FROM Corso c WHERE c.id = :id"),
    @NamedQuery(name = TROVA_TUTTE, query = "SELECT c FROM Corso c"),
    @NamedQuery(name = TROVA_CORSI_CORSOLAUREA, query = "SELECT c FROM Corso c WHERE c.corsoLaurea.nome = :nomeCorsoLaurea")
})
public class Corso implements Serializable {

    /**
     * Costante per identificare la query che cerca un corso per ID
     * */
    public static final String TROVA_CORSO = "Corso.trovaCorso";
    /**
     * Costante per identificare la query che restituisce tutti i corsi.
     * */
    public static final String TROVA_TUTTE = "Corso.trovaTutte";
    /**
     * Costante per identificare la query che restituisce i corsi di un determinato corso di laurea
     */
    public static final String TROVA_CORSI_CORSOLAUREA = "Corso.trovaCorsoLaurea";

    /**
     * Identificatore univoco del Corso
     * */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Corso di laurea a cui appartiene il corso
     * */
    @ManyToOne
    @JoinColumn(name = "corso_laurea_id")
    private CorsoLaurea corsoLaurea;

    /**
     * Lista delle lezioni associate al corso
     * */
    @OneToMany(mappedBy = "corso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lezione> lezioni;

    /**
     * Lista dei docenti che insegnano il corso
     * */
    @ManyToMany(mappedBy = "corsi")
    private List<Docente> docenti;

    /**
     * Lista degli appelli d'esame associati al corso
     * */
    @OneToMany(mappedBy = "corso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appello> appelli;

    /**
     * Anno didattico a cui appartiene il corso
     */
    @ManyToOne
    @JoinColumn(name = "anno_didattico_id", nullable = false)
    private AnnoDidattico annoDidattico;

    /**
     * Restituisce l'anno didattico del corso.
     *
     * @return Anno didattico del corso
     */
    public AnnoDidattico getAnnoDidattico() {
        return annoDidattico;
    }

    /**
     * Imposta l'anno didattico del corso.
     *
     * @param annoDidattico Anno didattico
     */
    public void setAnnoDidattico(AnnoDidattico annoDidattico) {
        this.annoDidattico = annoDidattico;
    }


    /**
     * Nome del Corso
     * */
    private String nome;

    /**
     * Costruttore che crea un corso con un nome specificato.
     *
     * @param nome Nome del corso.
     * */
    public Corso(String nome) {
        this.nome = nome;
        appelli = new ArrayList<>();
        lezioni = new ArrayList<>();
        docenti = new ArrayList<>();
    }

    /**
     * Costruttore di default per creare un corso vuoto
     * */
    public Corso() {
        appelli = new ArrayList<>();
        lezioni = new ArrayList<>();
        docenti = new ArrayList<>();
    }

    /**Restitusice la lista dei docenti associati al corso.
     *
     * @return Lista dei docenti
     * */
    public List<Docente> getDocenti() {
        return docenti;
    }

    /**
     * Imposta la lista dei docenti associati al corso.
     *
     * @param docenti Lista dei docenti.
     * */
    public void setDocenti(List<Docente> docenti) {
        this.docenti = docenti;
    }

    /**
     * Restituisce la lista degli appelli d'esame del corso.
     *
     * @return Lista degli appelli
     * */
    public List<Appello> getAppelli() {
        return appelli;
    }

    /**Imposta la lista degli appelli d'esame del corso.
     *
     * @param appelli Lista degli appelli
     * */

    public void setAppelli(List<Appello> appelli) {
        this.appelli = appelli;
    }

    /**
     * Restituisce l'ID del corso.
     *
     * @return ID del corso
     * */
    public Long getId() {
        return id;
    }

    /**
     * Restituisce il corso di laurea associato al corso.
     *
     * @return Corso di laurea
     * */
    public CorsoLaurea getCorsoLaurea() {
        return corsoLaurea;
    }

    /**
     * Restituisce la lista delle lezioni del corso.
     *
     * @return Lista delle lezioni.
     * */
    public List<Lezione> getLezioni() {
        return lezioni;
    }

    /**
     * Restituisce il nome del corso.
     *
     * @return Nome del corso.
     * */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il corso di laurea associato al corso.
     *
     * @param corsoLaurea Corso di laurea.
     * */
    public void setCorsoLaurea(CorsoLaurea corsoLaurea) {
        this.corsoLaurea = corsoLaurea;
    }

    /**
     * Imposta la lista delle lezioni del corso.
     *
     * @param lezioni Lista delle lezioni.
     * */
    public void setLezioni(List<Lezione> lezioni) {
        this.lezioni = lezioni;
    }

    /**
     * Imposta il nome del Corso
     *
     * @param nome Nome del corso
     * */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce una rappresentazione in formato stringa del corso.
     *
     * @return Stringa che rappresenta il corso
     * */
    @Override
    public String toString() {
        return "Corso{" +
                "id=" + id +
                ", corsoLaurea=" + corsoLaurea +
                ", docenti=" + docenti +
                ", nome='" + nome + '\'' +
                '}';
    }
}
