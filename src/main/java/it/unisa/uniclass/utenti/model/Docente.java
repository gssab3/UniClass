package it.unisa.uniclass.utenti.model;

import it.unisa.uniclass.esami.model.AppelloDocente;
import it.unisa.uniclass.orari.model.Corso;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static it.unisa.uniclass.utenti.model.Docente.*;

@Entity
@Table(name = "docenti")
@NamedQueries({
        @NamedQuery(name = TROVA_DOCENTE, query = "SELECT d FROM Docente d WHERE d.matricola = :matricola"),
        @NamedQuery(name = TROVA_DOCENTE_CORSOLAUREA, query = "SELECT d FROM Docente d WHERE d.corsoLaurea.nome = :nome"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT d FROM Docente d"),
        @NamedQuery(name = TROVA_TUTTI_DOCENTI, query = "SELECT d FROM Docente d WHERE d.tipo = 'Docente'"),
        @NamedQuery(name = TROVA_EMAIL, query = "SELECT d FROM Docente d WHERE d.email = :email")
})
public class Docente extends Accademico implements Serializable {

    public static final String TROVA_DOCENTE = "Docente.trovaDocente";
    public static final String TROVA_DOCENTE_CORSOLAUREA = "Docente.trovaDocenteCorsoLaurea";
    public static final String TROVA_TUTTI = "Docente.trovaTutti";
    public static final String TROVA_TUTTI_DOCENTI = "Docente.trovaTuttiDocenti";
    public static final String TROVA_EMAIL = "Docente.trovaEmail";

    @ManyToMany
    @JoinTable(
            name = "docente_corso",
            joinColumns = @JoinColumn(name = "docente_id"),
            inverseJoinColumns = @JoinColumn(name = "corso_id")
    )
    protected List<Corso> corsi;

    protected String dipartimento;

    @OneToMany(mappedBy = "docente", cascade = CascadeType.ALL, orphanRemoval = true)
    protected List<AppelloDocente> appelloDocenti;

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
                '}';
    }
}
