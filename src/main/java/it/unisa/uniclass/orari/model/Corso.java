package it.unisa.uniclass.orari.model;

import it.unisa.uniclass.esami.model.Appello;
import it.unisa.uniclass.utenti.model.Docente;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "corsi")
public class Corso implements Serializable {

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
                ", lezioni=" + lezioni +
                ", docenti=" + docenti +
                ", appelli=" + appelli +
                ", nome='" + nome + '\'' +
                '}';
    }
}
