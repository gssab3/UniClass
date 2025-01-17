package it.unisa.uniclass.utenti.model;

import it.unisa.uniclass.orari.model.Corso;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import static it.unisa.uniclass.utenti.model.Coordinatore.*;

/**
 * Classe che rappresenta un Coordinatore nel sistema.
 * Il Coordinatore è un'estesione della classe {@link Docente}.
 * Questa classe è mappata su una tabella di un database utilizzando JPA.
 * <p>
 * Contiene dettgli come nome, cognome, email ,corsi gestiti e altro.
 * <p>
 *
 * @see Docente
 * */
@Entity
@Table(name = "coordinatori")
@NamedQueries({
        @NamedQuery(name = TROVA_COORDINATORE, query = "SELECT c FROM Coordinatore c WHERE c.matricola = :matricola"),
        @NamedQuery(name = TROVA_COORDINATORE_CORSOLAUREA, query = "SELECT c FROM Coordinatore c WHERE c.corsoLaurea.nome = :nomeCorsoLaurea"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT c FROM Coordinatore c"),
        @NamedQuery(name = TROVA_EMAIL, query = "SELECT c FROM Coordinatore c WHERE c.email = :email")
})
public class Coordinatore extends Docente implements Serializable {
    /**
     * Nome della query per trovare un Coordinatore dato il numero di matricola
     * */
    public static final String TROVA_COORDINATORE = "Coordinatore.trovaCoordinatore";
    /**
     * Nome della query per trovare un Coordinatore dato il nome del corso di laurea associato
     * */
    public static final String TROVA_COORDINATORE_CORSOLAUREA = "Coordinatore.trovaCoordinatoreCorsoLaurea";
    /**
     * Nome della query per trovare tutti i Coordinatori.
     * */
    public static final String TROVA_TUTTI = "Coordinatore.trovaTutti";
    /**
     * Nome della query per trovare un Coordinatore dato l'email.
     * */
    public static final String TROVA_EMAIL = "Coordinatore.trovaEmail";

    /**
     * Costruttore di default. Inizializza il tipo a {@link Tipo#Coordinatore}.
     * */
    public Coordinatore() {super(); tipo = Tipo.Coordinatore;}

    /**
     * Costruttore parametrico della classe Coordinatore
     * */
    public Coordinatore(String nome, String cognome, LocalDate dataNascita, String email, String password, String matricola, LocalDate iscrizione, CorsoLaurea corsoLaurea, String dipartimento) {
        tipo = Tipo.Coordinatore;
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
}
