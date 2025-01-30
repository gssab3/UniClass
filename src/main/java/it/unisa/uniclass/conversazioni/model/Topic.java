package it.unisa.uniclass.conversazioni.model;

import it.unisa.uniclass.orari.model.Corso;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

import static it.unisa.uniclass.conversazioni.model.Topic.*;

/**
 * Rappresenta un topic di discussione associato a un corso di laurea o a un corso specifico.
 */
@Entity
@Table(name = "topics")
@NamedQueries({
        @NamedQuery(name = TROVA_ID, query = "SELECT t FROM Topic t WHERE t.id = :id"),
        @NamedQuery(name = TROVA_NOME, query = "SELECT t FROM Topic t WHERE t.nome = :nome"),
        @NamedQuery(name = TROVA_CORSOLAUREA, query = "SELECT t FROM Topic t WHERE t.corsoLaurea.nome = :nome"),
        @NamedQuery(name = TROVA_CORSO, query = "SELECT t FROM Topic t WHERE t.corso.nome = :nome"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT t FROM Topic t")
})
public class Topic implements Serializable {

    /** Nome della NamedQuery per trovare un topic tramite ID. */
    public static final String TROVA_ID = "Topic.trovaId";
    /** Nome della NamedQuery per trovare un topic tramite nome. */
    public static final String TROVA_NOME = "Topic.trovaNome";
    /** Nome della NamedQuery per trovare un topic associato a un corso di laurea. */
    public static final String TROVA_CORSOLAUREA = "Topic.trovaCorsoLaurea";
    /** Nome della NamedQuery per trovare un topic associato a un corso specifico. */
    public static final String TROVA_CORSO = "Topic.trovaCorso";
    /** Nome della NamedQuery per trovare tutti i topic. */
    public static final String TROVA_TUTTI = "Topic.trovaTutti";

    /** Identificatore univoco del topic. */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /** Nome del topic, ad esempio "Informatica" o "Programmazione Distribuita". */
    private String nome;

    /**
     * Corso di laurea associato al topic. Può essere null se il topic è relativo a un corso specifico.
     */
    @ManyToOne
    @JoinColumn(name = "corso_laurea_id", nullable = true)
    private CorsoLaurea corsoLaurea;

    /**
     * Corso specifico associato al topic. Può essere null se il topic è relativo a un corso di laurea.
     */
    @ManyToOne
    @JoinColumn(name = "corso_id", nullable = true)
    private Corso corso;

    /** Lista dei messaggi associati al topic. */
    @OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
    private List<Messaggio> messaggi;

    /**
     * Restituisce il nome del topic.
     *
     * @return il nome del topic
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome del topic.
     *
     * @param nome il nome del topic
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il corso di laurea associato al topic.
     *
     * @return il corso di laurea associato
     */
    public CorsoLaurea getCorsoLaurea() {
        return corsoLaurea;
    }

    /**
     * Imposta il corso di laurea associato al topic.
     *
     * @param corsoLaurea il corso di laurea da associare
     */
    public void setCorsoLaurea(CorsoLaurea corsoLaurea) {
        this.corsoLaurea = corsoLaurea;
    }

    /**
     * Restituisce il corso associato al topic.
     *
     * @return il corso associato
     */
    public Corso getCorso() {
        return corso;
    }

    /**
     * Imposta il corso associato al topic.
     *
     * @param corso il corso da associare
     */
    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    /**
     * Restituisce l'identificatore univoco del topic.
     *
     * @return l'ID del topic
     */
    public Long getId() {
        return id;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto Topic.
     *
     * @return stringa rappresentativa del topic
     */
    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", corsoLaurea=" + corsoLaurea +
                ", corso=" + corso +
                '}';
    }
}
