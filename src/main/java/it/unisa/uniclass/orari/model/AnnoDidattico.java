package it.unisa.uniclass.orari.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static it.unisa.uniclass.orari.model.AnnoDidattico.*;

/**
 * Rappresenta un anno didattico associato a uno o più corsi di laurea.
 * Utilizza annotazioni JPA per la mappatura con una tabella del database.
 * */
@Entity
@Table(name = "anni")
@NamedQueries({
        @NamedQuery(name = TROVA_ANNO, query = "SELECT a FROM AnnoDidattico a WHERE a.anno = :anno"),
        @NamedQuery(name = TROVA_ID, query = "SELECT a FROM AnnoDidattico a WHERE a.id = :id"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT a FROM AnnoDidattico a"),
        @NamedQuery(name = TROVA_ANNI_CORSOLAUREA, query = "SELECT a FROM AnnoDidattico a JOIN a.corsiLaurea c WHERE c.id = :corsoId"),
        @NamedQuery(name = TROVA_ANNI_CORSOLAUREA_NOME, query = "SELECT a FROM AnnoDidattico a JOIN a.corsiLaurea c WHERE c.id = :corsoId AND a.anno = :anno")
})
public class AnnoDidattico implements Serializable {

    /**
     * Nome della query per trovare un anno didattico in base al valore del campo "anno".
     * */
    public static final String TROVA_ANNO = "AnnoDidattico.trovaAnno";
    /**
     * Nome della query per trovare un anno didattico in base al valore del campo "id".
     * */
    public static final String TROVA_ID = "AnnoDidattico.trovaId";
    /**
     * Nome della query per ottenere tutti gli anni didattici.
     * */
    public static final String TROVA_TUTTI = "AnnoDidattico.trovaTutti";
    /**
     * Nome della query per trovare gli anni didattici associati a un determinato corso di laurea.
     */
    public static final String TROVA_ANNI_CORSOLAUREA = "AnnoDidattico.trovaAnniCorsoLaurea";
    /**
     * Nome della query per trovare gli anni didattici associati a un determinato corso di laurea
     * e con un determinato valore del campo "anno".
     */
    public static final String TROVA_ANNI_CORSOLAUREA_NOME = "AnnoDidattico.trovaAnniCorsoLaureaNome";

    /**
     * Identificativo unico del'anno didattico
     * */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * Nome o descrizione dell'anno didattico (es. "Anno 1", "Anno 2").
     * */
    private String anno; //Esempio: "Anno 1", "Anno 2", ecc..

    /**
     * Lista di corsi di laurea associati a questo anno didattico.
     * La relazioni è mappata come bidirezionale.
     * */
    @ManyToMany(mappedBy = "anniDidattici")
    private List<CorsoLaurea> corsiLaurea = new ArrayList<>();

    /**
     * Lista dei corsi associati a questo anno didattico.
     * La relazione è mappata con il campo "annoDidattico" nella classe Corso.
     * Le operazioni di persistenza sui corsi vengono propagate automaticamente
     * a causa della configurazione della cascata {@code CascadeType.ALL}.
     */
    @OneToMany(mappedBy = "annoDidattico", cascade = CascadeType.ALL)
    private List<Corso> corsi = new ArrayList<>();

    /**
     * Restituisce la lista dei corsi associati a questo anno didattico.
     *
     * @return Una lista di oggetti {@link Corso}.
     */
    public List<Corso> getCorsi() {
        return corsi;
    }

    /**
     * Imposta la lista dei corsi per questo anno didattico.
     *
     * @param corsi La lista di oggetti {@link Corso} da associare.
     */
    public void setCorsi(List<Corso> corsi) {
        this.corsi = corsi;
    }

    /**
     * Costruttore che inizializza un anno didattico con il nome specificato.
     *
     * @param anno Nome o descrizione dell'anno didattico.
     * */
    public AnnoDidattico(String anno){
        this.anno = anno;
    }

    /**
     * Costruttore di default.
     * */
    public AnnoDidattico(){}

    /**
     * Restituisce in nome o la descrizione dell'anno didattico.
     *
     * @return Il valore del campo {@code anno}
     * */
    public String getAnno() {
        return anno;
    }

    /**
     * Imposta il nome o la descrizione dell'anno didattico.
     *
     * @param anno Nome o descrizione dell'anno didattico.
     * */
    public void setAnno(String anno) {
        this.anno = anno;
    }

    /**
     * Restituisce l'identificativo unico dell'anno didattico.
     *
     * @return Il valore del campo {@code id}.
     * */
    public int getId() {
        return id;
    }

    /**
     * Restituisce la lista di corsi di laurea associati a questo anno didattico.
     *
     * @return Una lista di oggetti {@code CorsoLaurea}
     * */
    public List<CorsoLaurea> getCorsiLaurea() {
        return corsiLaurea;
    }

    /**
     * Imposta la lista di corsi di laurea associati a questo anno didattico.
     *
     * @param corsiLaurea Una lista di oggetti {@code CorsoLaurea}
     * */
    public void setCorsiLaurea(List<CorsoLaurea> corsiLaurea) {
        this.corsiLaurea = corsiLaurea;
    }



    /**
     * Restituisce una rappresentazione testuale dell'oggetto.
     *
     * @return Una strinag che rappresenta l'oggetto {@code AnnoDidattico}
     * */
    @Override
    public String toString() {
        return "AnnoDidattico{" +
                "id=" + id +
                ", anno='" + anno + '\'' +
                '}';
    }
}
