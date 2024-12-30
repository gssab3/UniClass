package it.unisa.uniclass.utenti.model;

import it.unisa.uniclass.esami.model.AppelloDocente;
import it.unisa.uniclass.orari.model.Corso;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "docenti")
public class Docente extends Accademico implements Serializable {

    @ManyToMany
    @JoinTable(
            name = "docente_corso",
            joinColumns = @JoinColumn(name = "docente_id"),
            inverseJoinColumns = @JoinColumn(name = "corso_id")
    )
    private List<Corso> corsi;

    private String dipartimento;

    @OneToMany(mappedBy = "docente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AppelloDocente> appelloDocenti;

    public Docente(String nome, String cognome, LocalDate dataNascita, String email, String password, String matricola, LocalDate iscrizione, CorsoLaurea corsoLaurea, String dipartimento) {
        tipo = Tipo.Docente;
        corsi = new ArrayList<Corso>();
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.email = email;
        this.password = password;
        this.matricola = matricola;
        this.iscrizione = iscrizione;
        this.corsoLaurea = corsoLaurea;
        this.dipartimento = dipartimento;
        appelloDocenti = new ArrayList<>();
    }

    public Docente() {
        appelloDocenti = new ArrayList<>();
        corsi = new ArrayList<>();
        tipo = Tipo.Docente;
    }

    public List<AppelloDocente> getAppelloDocenti() {
        return appelloDocenti;
    }

    public void setAppelloDocenti(List<AppelloDocente> appelloDocenti) {
        this.appelloDocenti = appelloDocenti;
    }

    public String getDipartimento() {
        return dipartimento;
    }

    public void setDipartimento(String dipartimento) {
        this.dipartimento = dipartimento;
    }

    public List<Corso> getCorsi() {
        return corsi;
    }

    public void setCorsi(List<Corso> corsi) {
        this.corsi = corsi;
    }

    @Override
    public String toString() {
        return "Docente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", email='" + email + '\'' +
                ", tipo=" + tipo +
                ", matricola='" + matricola + '\'' +
                ", iscrizione=" + iscrizione +
                ", corsoLaurea=" + corsoLaurea +
                ", dipartimento='" + dipartimento + '\'' +
                ", corsi=" + corsi +
                '}';
    }
}
