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

/**
 * Classe che rappresenta uno studente nel sistema UniClass.
 * Estende la classe {@link Accademico} e include funzionalità specifiche per gli studenti.
 * Implementa l'interfaccia {@link Serializable} per supportare la serializzazione.
 */
@Entity
@Table(name = "studenti")
@NamedQueries({
        @NamedQuery(name = TROVA_STUDENTE, query = "SELECT s FROM Studente s WHERE s.matricola = :matricola"),
        @NamedQuery(name = TROVA_STUDENTI_CORSO, query = "SELECT s FROM Studente s WHERE s.corsoLaurea.nome = :nome"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT s FROM Studente s"),
        @NamedQuery(name = TROVA_EMAIL, query = "SELECT s FROM Studente s WHERE s.email = :email")
})
public class Studente extends Accademico implements Serializable {

    /**
     * Nome della query per trovare uno studente tramite la matricola.
     */
    public static final String TROVA_STUDENTE = "Studente.trovaStudente";
    /**
     * Nome della query per trovare tutti gli studenti di un determinato corso di laurea.
     */
    public static final String TROVA_STUDENTI_CORSO = "Studente.trovaPerCorso";
    /**
     * Nome della queryy per trovare tutti gli studenti
     * */
    public static final String TROVA_TUTTI = "Studente.trovaTutti";
    /**
     * Nome della query per trovare uno strudente tramite l'email.
     * */
    public static final String TROVA_EMAIL = "Studente.trovaEmail";

    /**
     * Lista delle prenotazioni associate allo studente.
     *  La relazione è unidirezionale e gesita tramite la classe {@link Prenotazione}.
     * */
    @OneToMany(mappedBy = "studente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Prenotazione> prenotazioni;

    /**
     * Agenda personale dello studente.
     * Relazione unoo-a-uno
     * */
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "agenda")
    private Agenda agenda;

    /**
     * Costruttore predefinito.
     * Inizializza le liste e setta il tipo a {@link Tipo#Studente}.
     * */
    public Studente() {
        super.setTipo(Tipo.Studente);
        prenotazioni = new ArrayList<>();
        agenda = new Agenda();
    }

    /**
     * Resto associato allo studente, utilizzato per indicare informazioni extra.
     * Relazione uno-a-molti.
     * */
    @ManyToOne
    @JoinColumn(name = "resto", nullable = true)
    private Resto resto;

    @ManyToOne
    @JoinColumn(name = "corso_laurea_id", nullable = false) // Colonna FK per CorsoLaurea
    private CorsoLaurea corsoLaurea;


    /**
     * Costruttore con parametri.
     * Inizializza i campi principali dello stuedente.
     * */
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
        this.corsoLaurea = corsoLaurea;
    }

    /**
     * Restituisce la lista delle prenotazioni dello studente.
     *
     * @return Lista delle prentazioni.
     * */
    public List<Prenotazione> getPrenotazioni() {
        return prenotazioni;
    }

    /**
     * Restituisce l'agenda associata allo studente.
     *
     * @return Oggetto {@link Agenda}.
     * */
    public Agenda getAgenda() {
        return agenda;
    }

    /**
     * Restituisce il resto associato allo studente
     *
     * @return Oggetto {@link Resto}.
     * */
    public Resto getResto() {
        return resto;
    }

    /**
     * Imposta l'agenda associata allo Studente.
     *
     * @param agenda Oggetto {@link Agenda} da associare.
     * */
    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    @Override
    public CorsoLaurea getCorsoLaurea() {
        return corsoLaurea;
    }

    @Override
    public void setCorsoLaurea(CorsoLaurea corsoLaurea) {
        this.corsoLaurea = corsoLaurea;
    }

    /**
     * Imposta la lista delle prenotazioni dello studente.
     *
     * @param prenotazioni Lista delle prentazioni.
     * */
    public void setPrenotazioni(List<Prenotazione> prenotazioni) {
        this.prenotazioni = prenotazioni;
    }

    /**
     * Imposta il resto associato allo Studente.
     *
     * @param resto Oggetto {@link Resto} da associare.
     * */
    public void setResto(Resto resto) {
        this.resto = resto;
    }

    /**
     * Restituisce una rappresentazione in formato stringa dell'oggetto Studente.
     *
     * @return Stringa rappresentativa dello studente.
     * */
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
