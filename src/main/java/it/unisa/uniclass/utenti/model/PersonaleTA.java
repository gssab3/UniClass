package it.unisa.uniclass.utenti.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

import static it.unisa.uniclass.utenti.model.PersonaleTA.TROVA_PERSONALE;
import static it.unisa.uniclass.utenti.model.PersonaleTA.TROVA_TUTTI;

@Entity
@Table(name = "personaleTA")
@NamedQueries({
        @NamedQuery(name = TROVA_PERSONALE, query = "SELECT p FROM PersonaleTA p WHERE p.id = :id"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT p FROM PersonaleTA p")
})
public class PersonaleTA extends Utente implements Serializable {
    public static final String TROVA_PERSONALE = "PersonaleTA.trovaPersonale";
    public static final String TROVA_TUTTI = "PersonaleTA.trovaTutti";

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
