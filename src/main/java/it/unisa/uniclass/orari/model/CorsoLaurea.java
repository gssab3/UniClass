package it.unisa.uniclass.orari.model;

import it.unisa.uniclass.utenti.model.Studente;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static it.unisa.uniclass.orari.model.CorsoLaurea.*;

/**
 * Classe che rappresenta un Corso di Laurea.
 * Contiene informazioni relative ai corsi, anni didattici e resti associati.
 * */
@Entity
@NamedQueries({
        @NamedQuery(name = TROVA_CORSOLAUREA, query = "SELECT c FROM CorsoLaurea c WHERE c.id = :id"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT c FROM CorsoLaurea c"),
        @NamedQuery(name = TROVA_CORSOLAUREA_NOME, query = "SELECT c FROM CorsoLaurea c WHERE c.nome = :nome")
})
public class CorsoLaurea implements Serializable {

    /**
     * Nome della query per trovare un corso di laurea tramite ID.
     * */
    public static final String TROVA_CORSOLAUREA = "CorsoLaurea.trovaCorsoLaurea";
    /**
     * Nome della query per trovare tutti i corsi di laurea
     * */
    public static final String TROVA_TUTTI = "CorsoLaurea.trovaTutti";
    /**
     * Nome per trovare un corso di laurea tramite nome.
     * */
    public static final String TROVA_CORSOLAUREA_NOME = "CorsoLaurea.trovaCorsoLaureaNome";

    /**
     * Identificativo unico del corso di laurea.
     * */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Lista dei corsi associati a questo corso di laurea
     * */
    @OneToMany(mappedBy = "corsoLaurea", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Corso> corsi = new ArrayList<>();

    @Column(nullable = false, unique = true)
    /**
     * Nome del corso di laurea
     * */
    private String nome;

    /**
     * Lista dei resti associati al corso di laurea
     * */
    @OneToMany(mappedBy = "corsoLaurea", cascade = CascadeType.ALL)
    private List<Resto> resti = new ArrayList<>(); // I resti associati al corso di laurea

    /**
     * Lista degli anni didattici assocaiti al corso di laurea
     * */
    @ManyToMany
    @JoinTable(
            name = "corso_laurea_anno_didattico",
            joinColumns = @JoinColumn(name = "corso_laurea_id"),
            inverseJoinColumns = @JoinColumn(name = "anno_didattico_id")
    )
    private List<AnnoDidattico> anniDidattici = new ArrayList<>();

    /**
     * Lista degli studenti iscritti al corso di laurea.
     */
    @OneToMany(mappedBy = "corsoLaurea", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Studente> studenti = new ArrayList<>();

    /**
     * Costruttore con il nome del corso di laurea
     *
     * @param nome Nome del corso di laurea
     * */
    public CorsoLaurea(String nome) {
        this.nome = nome;
        this.corsi = new ArrayList<Corso>();
    }

    /**
     * Costruttore con nome, resti e anni didattici.
     *
     * @param nome Nome del corso di laurea
     * @param resti Lista dei resti associati
     * @param anniDidattici Lista degli anni didattici associati.
     * */
    public CorsoLaurea(String nome, List<Resto> resti, List<AnnoDidattico> anniDidattici) {
        this.nome = nome;
        this.corsi = new ArrayList<Corso>();
        this.resti = resti;
        this.anniDidattici = anniDidattici;
    }

    /**
     * Costruttore di default.
     * Inizializza liste vuote e il nome a null.
     * */
    public CorsoLaurea() {
        this.corsi = new ArrayList<>();
        this.nome = null;
        this.resti = new ArrayList<>();
    }

    /**
     * Restituisce la lista dei resti associati
     *
     * @return Lista dei resti.
     * */
    public List<Resto> getResti() {
        return resti;
    }

    /**Imposta la lista dei resti associati
     *
     * @param resti Lista dei resti da associare.
     * */
    public void setResti(List<Resto> resti) {
        this.resti = resti;
    }

    /**
     * Restituisce la lista degli anni didattici associati.
     *
     * @return Lista degli anni didattici.
     */
    public List<AnnoDidattico> getAnniDidattici() {
        return anniDidattici;
    }

    /**
     * Imposta la lista degli anni didattici associati.
     *
     * @param anniDidattici Lista degli anni didattici da associare.
     */
    public void setAnniDidattici(List<AnnoDidattico> anniDidattici) {
        this.anniDidattici = anniDidattici;
    }

    /**
     * Restituisce l'ID del corso di laurea.
     *
     * @return ID del corso di laurea.
     * */
    public Long getId() {
        return id;
    }

    /**
     * Restituisce la lista dei corsi associati
     *
     * @return Lista dei corsi.
     * */
    public List<Corso> getCorsi() {
        return corsi;
    }

    /**
     * Restituisce il nome del corso di laurea.
     *
     * @return Nome del corso di laurea.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del corso di laurea.
     *
     * @param nome Nome del corso di laurea da impostare.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Imposta la lista dei corsi associati.
     *
     * @param corsi Lista dei corsi da associare.
     */
    public void setCorsi(List<Corso> corsi) {
        this.corsi = corsi;
    }

    /**
     * Rappresentazione testuale dell'oggetto CorsoLaurea.
     *
     * @return Stringa che descrive il corso di luarea.
     * */
    @Override
    public String toString() {
        return "CorsoLaurea{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    /**
     * Restituisce la lista degli studenti iscritti al corso di laurea.
     *
     * @return Lista degli studenti.
     */
    public Collection<Studente> getStudenti() {
        return studenti;
    }

    /**
     * Imposta la lista degli studenti iscritti al corso di laurea.
     *
     * @param studenti Lista degli studenti da associare.
     */
    public void setStudenti(List<Studente> studenti) {
        this.studenti = studenti;
    }
}
