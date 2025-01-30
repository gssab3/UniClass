package it.unisa.uniclass.utenti.model;

import it.unisa.uniclass.orari.model.Corso;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Lezione;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static it.unisa.uniclass.utenti.model.Docente.*;

/**
 * Rappresenta un docente universitario.
 * Estende la classe {@link Accademico} e implementa {@link Serializable}.
 * */
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

    /**
     * Query per trovare un docente tramite la matricola
     * */
    public static final String TROVA_DOCENTE = "Docente.trovaDocente";
    /**
     * Query per trovare docenti di un corso di laurea specifico
     * */
    public static final String TROVA_DOCENTE_CORSOLAUREA = "Docente.trovaDocenteCorsoLaurea";
    /**
     * Query per trovare tutti i docenti
     * */
    public static final String TROVA_TUTTI = "Docente.trovaTutti";
    /**
     * Query per trovare tuttii docenti con tipo "Docente".
     * */
    public static final String TROVA_TUTTI_DOCENTI = "Docente.trovaTuttiDocenti";
    /**
     * Query per trovare un docente tramite email
     * */
    public static final String TROVA_EMAIL = "Docente.trovaEmail";

    @ManyToMany
    @JoinTable(
            name = "docente_corso",
            joinColumns = @JoinColumn(name = "docente_id"),
            inverseJoinColumns = @JoinColumn(name = "corso_id")
    )
    protected List<Corso> corsi = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "docente_lezione",
            joinColumns = @JoinColumn(name = "docente_id"),
            inverseJoinColumns = @JoinColumn(name = "lezione_id")
    )
    private List<Lezione> lezioni = new ArrayList<>();


    /**
     * Dipartimento a cui appartiene il docente
     * */
    protected String dipartimento;

    /**
     * Costruttore parametrico della classe Docente
     * */
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
    }

    /**
     * Costruttore di default della classe Docente
     * */
    public Docente() {
        corsi = new ArrayList<>();
        tipo = Tipo.Docente;
    }

    public List<Lezione> getLezioni() {
        return lezioni;
    }

    public void setLezioni(List<Lezione> lezioni) {
        this.lezioni = lezioni;
    }

    /**
     * Restituisce il dipartimeno del docente
     *
     * @return Diaprtimento del docente
     * */
    public String getDipartimento() {
        return dipartimento;
    }

    /**
     * Imposta il dipartimento del docente.
     *
     * @param dipartimento Dipartimento del docente.
     * */
    public void setDipartimento(String dipartimento) {
        this.dipartimento = dipartimento;
    }

    /**
     * Restituisce la lista dei corsi associati al docente.
     *
     * @return Lista di {@link Corso}.
     * */
    public List<Corso> getCorsi() {
        return corsi;
    }

    /**
     * Imposta la lista dei corsi associati al docente.
     *
     * @param corsi Lista di {@link Corso}.
     * */
    public void setCorsi(List<Corso> corsi) {
        this.corsi = corsi;
    }

    /**
     * Restituisce una rappresentazione testuale del docente.
     *
     * @return Stringa rappresentante il docente.
     * */
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
