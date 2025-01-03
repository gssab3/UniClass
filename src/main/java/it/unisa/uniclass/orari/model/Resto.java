package it.unisa.uniclass.orari.model;

import jakarta.persistence.*;

@Entity
public class Resto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // Esempio: "Resto 0", "Resto 1", ecc.

    @ManyToOne
    @JoinColumn(name = "corso_laurea_id", nullable = false)
    private CorsoLaurea corsoLaurea; // Corso di laurea a cui appartiene

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
