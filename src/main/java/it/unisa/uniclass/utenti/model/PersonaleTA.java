package it.unisa.uniclass.utenti.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import static it.unisa.uniclass.utenti.model.PersonaleTA.*;

@Entity
@Table(name = "personaleTA")
@NamedQueries({
        @NamedQuery(name = TROVA_PERSONALE, query = "SELECT p FROM PersonaleTA p WHERE p.id = :id"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT p FROM PersonaleTA p"),
        @NamedQuery(name = TROVA_EMAIL, query = "SELECT p FROM PersonaleTA p WHERE p.email = :email"),
        @NamedQuery(name = TROVA_EMAIL_PASSWORD, query = "SELECT p FROM PersonaleTA p WHERE p.email = :email AND p.password = :password" )
})
public class PersonaleTA extends Utente implements Serializable {
    public static final String TROVA_PERSONALE = "PersonaleTA.trovaPersonale";
    public static final String TROVA_TUTTI = "PersonaleTA.trovaTutti";
    public static final String TROVA_EMAIL = "PersonaleTA.trovaEmail";
    public static final String TROVA_EMAIL_PASSWORD = "PersonaleTA.trovaEmailPassword";

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String telefono;

    public PersonaleTA(String nome, String cognome, LocalDate dataNascita, String email, String password, String telefono) {
        this.telefono = telefono;
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.email = email;
        this.password = password;
        this.tipo = Tipo.PersonaleTA;
    }

    public PersonaleTA() {}

    public long getId() {
        return id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

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
