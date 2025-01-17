package it.unisa.uniclass.utenti.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import static it.unisa.uniclass.utenti.model.PersonaleTA.*;

/**
 * Classe che rappresenta un membro del personale tecnico-amministrativo (TA).
 * Estende la classe {@link Utente} e include proprietà specifiche per il personale TA.
 * Implementa l'interfaccia {@link Serializable} per suppportare la serializzazione.
 * */
@Entity
@Table(name = "personaleTA")
@NamedQueries({
        @NamedQuery(name = TROVA_PERSONALE, query = "SELECT p FROM PersonaleTA p WHERE p.id = :id"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT p FROM PersonaleTA p"),
        @NamedQuery(name = TROVA_EMAIL, query = "SELECT p FROM PersonaleTA p WHERE p.email = :email"),
        @NamedQuery(name = TROVA_EMAIL_PASSWORD, query = "SELECT p FROM PersonaleTA p WHERE p.email = :email AND p.password = :password" )
})
public class PersonaleTA extends Utente implements Serializable {
    /**
     * Nome della query per trovare un membro del personale Ta tramite ID.
     * */
    public static final String TROVA_PERSONALE = "PersonaleTA.trovaPersonale";
    /**
     * Nome della query per trovare tutti i membri del peronale TA.
     * */
    public static final String TROVA_TUTTI = "PersonaleTA.trovaTutti";
    /**
     * Nome della query per trovare un membro del personale TA tramite email.
     * */
    public static final String TROVA_EMAIL = "PersonaleTA.trovaEmail";
    /**
     * Nome della query per trovare un membro del personale TA tramite email e password.
     */
    public static final String TROVA_EMAIL_PASSWORD = "PersonaleTA.trovaEmailPassword";

    /**
     * Identificatore univoco per il membro del personale TA
     * */
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String telefono;

    /**
     * Costruttore completo per creare un'istanza di {@code PersonaleTA}.
     * */
    public PersonaleTA(String nome, String cognome, LocalDate dataNascita, String email, String password, String telefono) {
        this.telefono = telefono;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.email = email;
        this.password = password;
        this.tipo = Tipo.PersonaleTA;
    }

    /**
     * Costruttore vuoto richiesto da JPA
     * */
    public PersonaleTA() {}

    /**
     * Restituisce l'Identificatore univoco del membro del personale TA
     *
     * @return L'identificatore univoco.
     * */
    public long getId() {
        return id;
    }

    /**
     * Restituisce il numero di telefono del membro del personale TA
     *
     * @return Il numero di telefono del membro del personale TA.
     * */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Imposta il numero di telefono del membro del personale TA
     *
     * @param telefono Il numero di telefono del membro del personale TA.
     * */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Restituisce una rappresentazione testuale dell'oggetto {@code PersonaleTA}.
     *
     * @return String restituisce il membro del Personale TA.
     * */
    @Override
    public String toString() {
        return "PersonaleTA{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", email='" + email + '\'' +
                ", tipo=" + tipo +
                ", id=" + id +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
