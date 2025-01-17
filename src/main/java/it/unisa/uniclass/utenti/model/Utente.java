package it.unisa.uniclass.utenti.model;


import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Classe base per rappresentare un utente generico del sistema.
 * Questa classe è mappata come superclasse per l'uso con JPA.
 * Implementa l'interfaccia Serializable per consentire la serializzazione.
 * */
@MappedSuperclass
public class Utente implements Serializable {

    protected String nome;
    protected String cognome;
    protected LocalDate dataNascita;
    protected String email;
    protected String password;
    protected Tipo tipo;

    /**
     * Costruttore di default.
     * Inizializza un'istanza vuota di Utente.
     * */
    public Utente() {}

    /**
     * Restituisce il nome dell'Utente
     *
     * @return il nome dell'utente
     * */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il nome dell'utente.
     *
     * @param nome il nuovo nome dell'utente
     * */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Restituisce il cognome dell'utente.
     *
     * @return il cognome dell'utente
     * */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il cognome dell'utente.
     *
     * @param cognome il nuovo cognome dell'utente
     * */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Restituisce la data di nascita dell'utente
     *
     * @return la data di nascita dell'Utente
     * */
    public LocalDate getDataNascita() {
        return dataNascita;
    }

    /**
     * Imposta la data di nascita dell'utente.
     *
     * @param dataNascita la nuova data di nascita dell'utente
     * */
    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    /**
     * Restituisce l'indirizzo email dell'utente.
     *
     * @return l'email dell'utente
     * */
    public String getEmail() {
        return email;
    }

    /**
     * Imposta l'indirizzo email dell'utente
     *
     * @param email il nuovo indirizzo email dell'utente
     * */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Restituisce la password dell'utente
     *
     * @return la password dell'utente
     * */
    public String getPassword() {
        return password;
    }

    /**
     * Imposta la password dell'utente.
     *
     * @param password la nuova password dell'utente
     * */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Restituisce il tipo di utente
     *
     * @return il tipo di utente
     * */
    public Tipo getTipo() {
        return tipo;
    }

    /**
     * Imposta il tipo di utente.
     *
     * @param tipo il nuovo tipo di utente
     * */
    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    /**
     * Restituisce una rappresentazione in formato stringa dell'oggetto Utente.
     *
     * @return una stringa contenente le informazioni dell'Utente
     * */
    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
