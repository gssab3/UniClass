package it.unisa.uniclass.utenti.model;

import it.unisa.uniclass.esami.model.Prenotazione;
import it.unisa.uniclass.orari.model.Agenda;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import it.unisa.uniclass.orari.model.Resto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static it.unisa.uniclass.utenti.model.Studente.*;

@Entity
@Table(name = "studenti")
@NamedQueries({
        @NamedQuery(name = TROVA_STUDENTE, query = "SELECT s FROM Studente s WHERE s.matricola = :matricola"),
        @NamedQuery(name = TROVA_STUDENTI_CORSO, query = "SELECT s FROM Studente s WHERE s.corsoLaurea.nome = :nome"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT s FROM Studente s"),
        @NamedQuery(name = TROVA_EMAIL, query = "SELECT s FROM Studente s WHERE s.email = :email")
})
public class Studente extends Accademico implements Serializable {

    public static final String TROVA_STUDENTE = "Studente.trovaStudente";
    public static final String TROVA_STUDENTI_CORSO = "Studente.trovaPerCorso";
    public static final String TROVA_TUTTI = "Studente.trovaTutti";
    public static final String TROVA_EMAIL = "Studente.trovaEmail";

    @OneToMany(mappedBy = "studente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prenotazione> prenotazioni;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda")
    private Agenda agenda;

    public Studente() {
        super.setTipo(Tipo.Studente);
        prenotazioni = new ArrayList<>();
        agenda = new Agenda();
    }

    @ManyToOne
    @JoinColumn(name = "resto", nullable = true)
    private Resto resto;

    public Studente(String nome, String cognome, LocalDate dataNascita, String email, String password, String matricola, LocalDate iscrizione, CorsoLaurea corsoLaurea, Resto resto) {
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
        this.resto = resto;
    }

    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public Resto getResto() {
        return resto;
    }

    public void setResto(Resto resto) {
        this.resto = resto;
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
                '}';
    }
}
