package it.unisa.uniclass.orari.model;

import it.unisa.uniclass.utenti.model.Studente;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static it.unisa.uniclass.orari.model.Resto.*;

@Entity
@NamedQueries({
        @NamedQuery(name = TROVA_RESTI_CORSO, query = "SELECT r FROM Resto r WHERE r.corsoLaurea.nome = :nome"),
        @NamedQuery(name = TROVA_RESTO, query = "SELECT r FROM Resto r WHERE r.id = :id"),
        @NamedQuery(name = TROVA_RESTO_NOME, query = "SELECT r FROM Resto r WHERE r.nome = :nome"),
        @NamedQuery(name = TROVA_RESTO_NOME_CORSO, query = "SELECT r FROM Resto r WHERE r.nome = :nome AND r.corsoLaurea.nome = :nomeCorso")
})
public class Resto {

    public static final String TROVA_RESTI_CORSO = "Resto.trovaRestiCorso";
    public static final String TROVA_RESTO = "Resto.trovaResto";
    public static final String TROVA_RESTO_NOME = "Resto.trovaRestoNome";
    public static final String TROVA_RESTO_NOME_CORSO = "Resto.trovaRestoNomeCorso";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // Esempio: "Resto 0", "Resto 1", ecc.

    @ManyToOne
    @JoinColumn(name = "corso_laurea_id", nullable = false)
    private CorsoLaurea corsoLaurea; // Corso di laurea a cui appartiene

    @OneToMany(mappedBy = "resto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Studente> studenti = new ArrayList<>();

    public Resto(String nome, CorsoLaurea corsoLaurea) {
        this.nome = nome;
        this.corsoLaurea = corsoLaurea;
    }

    public Resto() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public CorsoLaurea getCorsoLaurea() {
        return corsoLaurea;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCorsoLaurea(CorsoLaurea corsoLaurea) {
        this.corsoLaurea = corsoLaurea;
    }
}
