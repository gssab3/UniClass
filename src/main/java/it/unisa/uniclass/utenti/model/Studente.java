package it.unisa.uniclass.utenti.model;

import it.unisa.uniclass.esami.model.Prenotazione;
import it.unisa.uniclass.orari.model.Agenda;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "studenti")
public class Studente extends Accademico implements Serializable {

    @OneToMany(mappedBy = "studente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prenotazione> prenotazioni;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;

    public Studente() {
        super.setNome("");
        super.setCognome("");
        super.setEmail("");
        super.setCorsoLaurea(null);
        super.setDataNascita(null);
        super.setIscrizione(null);
        super.setPassword("");
        super.setTipo(Tipo.Studente);
        super.setMatricola(null);
        prenotazioni = new ArrayList<>();
        agenda = new Agenda();
    }

    public Studente(String nome, String cognome, LocalDate dataNascita, String email, String password, String matricola, LocalDate iscrizione, CorsoLaurea corsoLaurea) {
        prenotazioni = new ArrayList<Prenotazione>();
        agenda = new Agenda();
        super.setNome(nome);
        super.setCognome(cognome);
        super.setEmail(email);
        super.setCorsoLaurea(corsoLaurea);
        super.setDataNascita(dataNascita);
        super.setIscrizione(iscrizione);
        super.setPassword(password);
        super.setTipo(Tipo.Studente);
        super.setMatricola(matricola);
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    @Override
    public String toString() {
        return "Studente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", dataNascita=" + dataNascita +
                ", email='" + email + '\'' +
                ", tipo=" + tipo +
                ", matricola='" + matricola + '\'' +
                ", iscrizione=" + iscrizione +
                ", corsoLaurea=" + corsoLaurea +
                ", prenotazioni=" + prenotazioni +
                ", agenda=" + agenda +
                '}';
    }
}
