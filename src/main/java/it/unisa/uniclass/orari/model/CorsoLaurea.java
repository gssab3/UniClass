package it.unisa.uniclass.orari.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static it.unisa.uniclass.orari.model.CorsoLaurea.*;

@Entity
@NamedQueries({
        @NamedQuery(name = TROVA_CORSOLAUREA, query = "SELECT c FROM CorsoLaurea c WHERE c.id = :id"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT c FROM CorsoLaurea c"),
        @NamedQuery(name = TROVA_CORSOLAUREA_NOME, query = "SELECT c FROM CorsoLaurea c WHERE c.nome = :nome")
})
public class CorsoLaurea implements Serializable {

    public static final String TROVA_CORSOLAUREA = "CorsoLaurea.trovaCorsoLaurea";
    public static final String TROVA_TUTTI = "CorsoLaurea.trovaTutti";
    public static final String TROVA_CORSOLAUREA_NOME = "CorsoLaurea.trovaCorsoLaureaNome";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "corsoLaurea", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Corso> corsi;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "corsoLaurea", cascade = CascadeType.ALL)
    private List<Resto> resti; // I resti associati al corso di laurea

    @ManyToMany
    @JoinTable(
            name = "corso_laurea_anno_didattico",
            joinColumns = @JoinColumn(name = "corso_laurea_id"),
            inverseJoinColumns = @JoinColumn(name = "anno_didattico_id")
    )
    private List<AnnoDidattico> anniDidattici = new ArrayList<>();


    public CorsoLaurea(String nome) {
        this.nome = nome;
        this.corsi = new ArrayList<Corso>();
    }

    public CorsoLaurea(String nome, List<Resto> resti, List<AnnoDidattico> anniDidattici) {
        this.nome = nome;
        this.corsi = new ArrayList<Corso>();
        this.resti = resti;
        this.anniDidattici = anniDidattici;
    }

    public CorsoLaurea() {
        this.corsi = new ArrayList<>();
        this.nome = null;
        this.resti = new ArrayList<>();
    }

    public List<Resto> getResti() {
        return resti;
    }

    public void setResti(List<Resto> resti) {
        this.resti = resti;
    }

    public List<AnnoDidattico> getAnniDidattici() {
        return anniDidattici;
    }

    public void setAnniDidattici(List<AnnoDidattico> anniDidattici) {
        this.anniDidattici = anniDidattici;
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
