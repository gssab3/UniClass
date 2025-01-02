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

@Entity
@Table(name = "coordinatori")
@NamedQueries({
        @NamedQuery(name = TROVA_COORDINATORE, query = "SELECT c FROM Coordinatore c WHERE c.matricola = :matricola"),
        @NamedQuery(name = TROVA_COORDINATORE_CORSOLAUREA, query = "SELECT c FROM Coordinatore c WHERE c.corsoLaurea.nome = :nomeCorsoLaurea"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT c FROM Coordinatore c")
})
public class Coordinatore extends Docente implements Serializable {
    public static final String TROVA_COORDINATORE = "Coordinatore.trovaCoordinatore";
    public static final String TROVA_COORDINATORE_CORSOLAUREA = "Coordinatore.trovaCoordinatoreCorsoLaurea";
    public static final String TROVA_TUTTI = "Coordinatore.trovaTutti";

    public Coordinatore() {super(); tipo = Tipo.Coordinatore;}

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
