package it.unisa.uniclass.orari.model;

import jakarta.inject.Named;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

import static it.unisa.uniclass.orari.model.Aula.*;

@Entity
@Table(name = "aule")
@NamedQueries({
        @NamedQuery(name = TROVA_AULANOME, query = "SELECT a FROM Aula a WHERE a.nome = :nome"),
        @NamedQuery(name = TROVA_AULA, query = "SELECT a FROM Aula a WHERE a.id = :id"),
        @NamedQuery(name = TROVA_AULA_EDIFICIO, query = "SELECT a FROM Aula a WHERE a.edificio = :edificio")
})
public class Aula implements Serializable {
    public static final String TROVA_AULANOME = "Aula.trovaAulaNome";
    public static final String TROVA_AULA = "Aula.trovaAula";
    public static final String TROVA_AULA_EDIFICIO = "Aula.trovaAulaEdificio";


    @Id @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "aula", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lezione> lezioni;

    private String edificio;
    private String nome;

    public Aula(int id, String edificio, String nome) {
        this.id = id;
        this.edificio = edificio;
        this.nome = nome;
    }

    public Aula() {}

    public int getId() {
        return id;
    }

    public List<Lezione> getLezioni() {
        return lezioni;
    }

    public String getEdificio() {
        return edificio;
    }

    public String getNome() {
        return nome;
    }

    public void setEdificio(String edificio) {
        this.edificio = edificio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", edificio='" + edificio + '\'' +
                ", nome='" + nome + '\'' +
                '}';
    }
}
