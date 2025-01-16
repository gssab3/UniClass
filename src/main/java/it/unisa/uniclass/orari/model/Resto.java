package it.unisa.uniclass.orari.model;

import it.unisa.uniclass.utenti.model.Studente;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static it.unisa.uniclass.orari.model.Resto.*;

/**
 * Classe rappresentante un "Resto", che identifica una suddivisione di studenti all'interno di un  corso di laurea.
 * Viene mappata come entità JPA per la persistenza.
 * */
@Entity
@NamedQueries({
        @NamedQuery(name = TROVA_RESTI_CORSO, query = "SELECT r FROM Resto r WHERE r.corsoLaurea.nome = :nome"),
        @NamedQuery(name = TROVA_RESTO, query = "SELECT r FROM Resto r WHERE r.id = :id"),
        @NamedQuery(name = TROVA_RESTO_NOME, query = "SELECT r FROM Resto r WHERE r.nome = :nome"),
        @NamedQuery(name = TROVA_RESTO_NOME_CORSO, query = "SELECT r FROM Resto r JOIN r.corsoLaurea cl WHERE r.nome = :nome AND cl.nome = :nomeCorso")
})
public class Resto implements Serializable {

    /**
     * Nome della query per trovare i "Resto" associati a un corso di laurea.
     * */
    public static final String TROVA_RESTI_CORSO = "Resto.trovaRestiCorso";
    /**
     * Nome della query per trovare un singolo "Resto" tramite il suo ID.
     * */
    public static final String TROVA_RESTO = "Resto.trovaResto";
    /**
     * Nome della query per trovare un "Resto" tramite il nome.
     * */
    public static final String TROVA_RESTO_NOME = "Resto.trovaRestoNome";
    /**
     * Nome della query per trovare un "Resto" tramite il nome e il nome del corso di laurea.
     * */
    public static final String TROVA_RESTO_NOME_CORSO = "Resto.trovaRestoNomeCorso";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome; // Esempio: "Resto 0", "Resto 1", ecc.

    @OneToMany(mappedBy = "resto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Lezione> lezioni = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "corso_laurea_id", nullable = false)
    private CorsoLaurea corsoLaurea; // Corso di laurea a cui appartiene

    @OneToMany(mappedBy = "resto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Studente> studenti = new ArrayList<>();


    /**
     * Costruttore che inizializza un nuovo oggetto Resto con il nome e il corso di laurea associato.
     *
     * @param nome Nome del resto (esempio: "Resto 1").
     * @param corsoLaurea Corso di laurea a cui appartiene il resto.
     * */
    public Resto(String nome, CorsoLaurea corsoLaurea) {
        this.nome = nome;
        this.corsoLaurea = corsoLaurea;
    }

    /**
     * Costruttore vuoto richiesto per il funzionamento con JPA.
     * */
    public Resto() {
    }

    /**
     * Restituisce l'ID del resto.
     *
     * @return ID univoco del resto.
     * */
    public Long getId() {
        return id;
    }

    /**
     * Restituisce il nome del resto.
     *
     * @return Nome del resto.
     * */
    public String getNome() {
        return nome;
    }

    /**
     * Restituisce il corso di laurea associato a questo resto.
     *
     * @return Oggetto {@link CorsoLaurea} a cui appartiene il resto.
     * */
    public CorsoLaurea getCorsoLaurea() {
        return corsoLaurea;
    }

    /**
     * Imposta un nuovo nome per il resto
     *
     * @param nome Nuovo nome del resto.
     * */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Imposta un nuovo corso di laurea associato al resto.
     *
     * @param corsoLaurea Nuovo corso di laurea.
     * */
    public void setCorsoLaurea(CorsoLaurea corsoLaurea) {
        this.corsoLaurea = corsoLaurea;
    }
}
