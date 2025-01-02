package it.unisa.uniclass.orari.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static it.unisa.uniclass.orari.model.CorsoLaurea.*;

@Entity
@Table(name = "corsiLaurea")
@NamedQueries({
        @NamedQuery(name = TROVA_CORSOLAUREA, query = "SELECT c FROM CorsoLaurea c WHERE c.id = :id"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT c FROM CorsoLaurea c"),
        @NamedQuery(name = TROVA_CORSOLAUREA_NOME, query = "SELECT c FROM CorsoLaurea c WHERE c.nome = :nome")
})
public class CorsoLaurea {

    public static final String TROVA_CORSOLAUREA = "CorsoLaurea.trovaCorsoLaurea";
    public static final String TROVA_TUTTI = "CorsoLaurea.trovaTutti";
    public static final String TROVA_CORSOLAUREA_NOME = "CorsoLaurea.trovaCorsoLaureaNome";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "corsoLaurea", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Corso> corsi;

    private String nome;

    public CorsoLaurea(String nome) {
        this.nome = nome;
        this.corsi = new ArrayList<Corso>();
    }

    public CorsoLaurea() {
        this.corsi = new ArrayList<>();
        this.nome = null;
    }

    public Long getId() {
        return id;
    }

    public List<Corso> getCorsi() {
        return corsi;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCorsi(List<Corso> corsi) {
        this.corsi = corsi;
    }

    @Override
    public String toString() {
        return "CorsoLaurea{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
