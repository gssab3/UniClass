package it.unisa.uniclass.orari.model;

import it.unisa.uniclass.esami.model.Appello;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static it.unisa.uniclass.orari.model.Aula.*;

/**
 * Classe che rappresenta un'Aula universitaria, con informazioni relative a edificio, nome e relazioni con lezioni e appelli.
 * */
@Entity
@Table(name = "aule")
@NamedQueries({
        @NamedQuery(name = TROVA_AULANOME, query = "SELECT a FROM Aula a WHERE a.nome = :nome"),
        @NamedQuery(name = TROVA_AULA, query = "SELECT a FROM Aula a WHERE a.id = :id"),
        @NamedQuery(name = TROVA_AULA_EDIFICIO, query = "SELECT a FROM Aula a WHERE a.edificio = :edificio"),
        @NamedQuery(name = TROVA_TUTTE, query = "SELECT a FROM Aula a"),
        @NamedQuery(name = TROVA_EDIFICI, query = "SELECT a.edificio FROM Aula a GROUP BY a.edificio")
})
public class Aula implements Serializable {
    /**
     * Nome della query per trovare un'aula dato il suo nome
     * */

    public static final String TROVA_AULANOME = "Aula.trovaAulaNome";
    /**
     * Nome della query per trovare un'aula dato il suo ID
     * */
    public static final String TROVA_AULA = "Aula.trovaAula";
    /**
     * Nome della query per trovare un'aula dato l'edificio
     * */
    public static final String TROVA_AULA_EDIFICIO = "Aula.trovaAulaEdificio";
    /**
     * Nome della query per trovare tutte le aule.
     * */
    public static final String TROVA_TUTTE = "Aula.trovaTutte";

    /**
     * Nome della query per trovare tutti gli edifici (distinti)
     */
    public static final String TROVA_EDIFICI = "Aula.trovaEdifici";

    /**
     * Identificativo univoco dell'Aula
     * */
    @Id @GeneratedValue
    private int id;

    /**
     * Elenco delle lezioni associate all'aula
     * */
    @OneToMany(mappedBy = "aula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lezione> lezioni = new ArrayList<>();

    /**
     * Elenco degli appelli associati all'aula
     * */
    @OneToMany(mappedBy = "aula", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appello> appelli = new ArrayList<>();

    /**
     * Edificio in cui si trova l'Aula
     * */
    private String edificio;
    /**
     * Nome dell'Aula
     * */
    private String nome;

    /**
     * Costruttore completo per creare un'istanza di Aula.
     *
     * @param id Identificativo dell'Aula
     * @param edificio Nome dell'edificio in cui si trova al'aula.
     * @param nome Nome dell'Aula
     * */
    public Aula(int id, String edificio, String nome) {
        this.id = id;
        this.edificio = edificio;
        this.nome = nome;
    }

    /**
     * Costruttore di default per creare un'istanza vuota di Aula.
     * */
    public Aula() {}

    /**
     * Restituisce l'identificativo dell'aula.
     *
     * @return L'identificativo dell'aula
     * */
    public int getId() {
        return id;
    }

    /**
     * Restituisc l'elenco delle lezioni associate all'aula.
     *
     * @return Lista delle lezioni
     * */
    public List<Lezione> getLezioni() {
        return lezioni;
    }

    /**
     * Restituisce l'edificio in cui si trova l'aula.
     *
     * @return Nome del'edificio.
     * */
    public String getEdificio() {
        return edificio;
    }

    /**
     * Restituisce il nome dell'aula.
     *
     * @return Nome del'aula
     * */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell'edificio in cui si trova l'aula.
     *
     * @param edificio Nome dell'edificio
     * */
    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    /**
     * Imposta il nome dell'Aula
     *
     * @param nome Nome dell'aula.
     * */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Aula.
     *
     * @return Sttringa rappresentativa dell'aula.
     * */
    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", edificio='" + edificio + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
