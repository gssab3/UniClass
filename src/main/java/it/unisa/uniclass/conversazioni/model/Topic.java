package it.unisa.uniclass.conversazioni.model;

import it.unisa.uniclass.orari.model.Corso;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.persistence.*;

import static it.unisa.uniclass.conversazioni.model.Topic.*;

@Entity
@Table(name = "topics")
@NamedQueries({
        @NamedQuery(name = TROVA_ID, query = "SELECT t FROM Topic t WHERE t.id = :id"),
        @NamedQuery(name = TROVA_NOME, query = "SELECT t FROM Topic t WHERE t.nome = :nome"),
        @NamedQuery(name = TROVA_CORSOLAUREA, query = "SELECT t FROM Topic t WHERE t.corsoLaurea.nome = :nome"),
        @NamedQuery(name = TROVA_CORSO, query = "SELECT t FROM Topic t WHERE t.corso.nome = :nome"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT t FROM Topic t")
})
public class Topic {

    public static final String TROVA_ID = "Topic.trovaId";
    public static final String TROVA_NOME = "Topic.trovaNome";
    public static final String TROVA_CORSOLAUREA = "Topic.trovaCorsoLaurea";
    public static final String TROVA_CORSO = "Topic.trovaCorso";
    public static final String TROVA_TUTTI = "Topic.trovaTutti";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome; // Es. "Informatica" o "Programmazione Distribuita"

    @ManyToOne
    @JoinColumn(name = "corso_laurea_id", nullable = true)
    private CorsoLaurea corsoLaurea; // Nullable se il topic è per un corso specifico.

    @ManyToOne
    @JoinColumn(name = "corso_id", nullable = true)
    private Corso corso; // Nullable se il topic è per un corso di laurea.

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public CorsoLaurea getCorsoLaurea() {
        return corsoLaurea;
    }

    public void setCorsoLaurea(CorsoLaurea corsoLaurea) {
        this.corsoLaurea = corsoLaurea;
    }

    public Corso getCorso() {
        return corso;
    }

    public void setCorso(Corso corso) {
        this.corso = corso;
    }

    public Long getId() {
        return id;
    }

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

