package it.unisa.uniclass.orari.model;

import it.unisa.uniclass.esami.model.Appello;
import it.unisa.uniclass.utenti.model.Docente;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static it.unisa.uniclass.orari.model.Corso.*;

@Entity
@Table(name = "corsi")
@NamedQueries({
    @NamedQuery(name = TROVA_CORSO, query = "SELECT c FROM Corso c WHERE c.id = :id"),
    @NamedQuery(name = TROVA_TUTTE, query = "SELECT c FROM Corso c"),
    @NamedQuery(name = TROVA_CORSI_CORSOLAUREA, query = "SELECT c FROM Corso c WHERE c.corsoLaurea.nome = :nomeCorsoLaurea")
})
public class Corso implements Serializable {

    public static final String TROVA_CORSO = "Corso.trovaCorso";
    public static final String TROVA_TUTTE = "Corso.trovaTutte";
    public static final String TROVA_CORSI_CORSOLAUREA = "Corso.trovaCorsoLaurea";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "corso_laurea_id")
    private CorsoLaurea corsoLaurea;

    @OneToMany(mappedBy = "corso", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lezione> lezioni;

    @ManyToMany(mappedBy = "corsi")
    private List<Docente> docenti;

    @OneToMany(mappedBy = "corso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Appello> appelli;

    @ManyToOne
    @JoinColumn(name = "anno_didattico_id", nullable = false)
    private AnnoDidattico annoDidattico;

    public AnnoDidattico getAnnoDidattico() {
        return annoDidattico;
    }

    public void setAnnoDidattico(AnnoDidattico annoDidattico) {
        this.annoDidattico = annoDidattico;
    }


    private String nome;

    public Corso(String nome) {
        this.nome = nome;
        appelli = new ArrayList<>();
        lezioni = new ArrayList<>();
        docenti = new ArrayList<>();
    }

    public Corso() {
        appelli = new ArrayList<>();
        lezioni = new ArrayList<>();
        docenti = new ArrayList<>();
    }

    public List<Docente> getDocenti() {
        return docenti;
    }

    public void setDocenti(List<Docente> docenti) {
        this.docenti = docenti;
    }


    public List<Appello> getAppelli() {
        return appelli;
    }

    public void setAppelli(List<Appello> appelli) {
        this.appelli = appelli;
    }

    public Long getId() {
        return id;
    }

    public CorsoLaurea getCorsoLaurea() {
        return corsoLaurea;
    }

    public List<Lezione> getLezioni() {
        return lezioni;
    }

    public String getNome() {
        return nome;
    }

    public void setCorsoLaurea(CorsoLaurea corsoLaurea) {
        this.corsoLaurea = corsoLaurea;
    }

    public void setLezioni(List<Lezione> lezioni) {
        this.lezioni = lezioni;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Corso{" +
                "id=" + id +
                ", corsoLaurea=" + corsoLaurea +
                ", docenti=" + docenti +
                ", nome='" + nome + '\'' +
                '}';
    }
}
