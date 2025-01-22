package it.unisa.uniclass.utenti.model;

import it.unisa.uniclass.conversazioni.model.Conversazione;
import it.unisa.uniclass.conversazioni.model.Messaggio;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static it.unisa.uniclass.utenti.model.Accademico.*;

/**
 * Rappresenta un accademico all'interno del sistema.
 * <p>
 * Questa classe eredita da {@link Utente} e include informazioni specifiche di un accademico come matricola, corso di laurea associato, conversazioni e messaggi inviati/ricevuti.<p>
 * L'oggetto viene gestito come entità JPA con ereditarietà di tipo JOINED.
 *
 * @author [UniClass]
 * @version 1.0
 * */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = TROVA_ACCADEMICO, query = "SELECT a FROM Accademico a WHERE a.matricola = :matricola"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT a FROM Accademico a"),
        @NamedQuery(name = TROVA_EMAIL, query = "SELECT a FROM Accademico a WHERE a.email = :email"),
        @NamedQuery(name = TROVA_ATTIVATI, query = "SELECT a FROM Accademico a WHERE a.attivato = :attivato")
})
public class Accademico extends Utente implements Serializable {

    /**
     * Nome della query per trovare un Accademico dato il nome
     * */
    public static final String TROVA_ACCADEMICO = "Accademico.trovaAccademico";
    /**
     * Nome della query per trovare tutti gli accademici
     */
    public static final String TROVA_TUTTI = "Accademico.trovaTutti";
    /**
     * Nome della query per trovare un accademico data l'email
     */
    public static final String TROVA_EMAIL = "Accademico.trovaEmail";
    public static final String TROVA_ATTIVATI = "Accademico.trovaAttivati";

    /** Relazione unidirezionale {@code @OneToOne}, mappata sul campo {@code corso_laurea_id}
     * */
    @Id
    protected String matricola;
    protected LocalDate iscrizione;
    @OneToOne
    @JoinColumn(name = "corso_laurea_id")
    protected CorsoLaurea corsoLaurea;


    protected boolean attivato = false;

    @ManyToMany(mappedBy = "messaggeri")
    private Set<Conversazione> conversazioni = new HashSet<>();

    /** Relazione unidirezionale {@code @OneToMany}, con cascata totale e rimoazione orfana
     * */
    @OneToMany(mappedBy = "autore", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Messaggio> messaggiInviati = new HashSet<>();

    /** Relazione unidirezionale {@code @OneToMany}, con cascata totale e rimozione orfana.
     */
    @OneToMany(mappedBy = "destinatario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Messaggio> messaggiRicevuti = new HashSet<>();


    /** Costruttore predefinito.
     <p>
     Inizializza un'istanza vuota di {@code Accademico}
     */
    public Accademico() {}


    /**
     * Restituisce il valore d'attivazione dell'account
     *
     * @return il valore dell'attivazione
     */
    public boolean isAttivato() {
        return attivato;
    }

    /**
     * Imposta il valore dell'attivazione dell'account
     *
     * @param attivato il nuovo valore d'attivazione
     */
    public void setAttivato(boolean attivato) {
        this.attivato = attivato;
    }

    /**
     * Restituisce la data di iscrizione dell'accademico.
     *
     * @return la data di iscrizione, come {@link LocalDate}
     * */
    public LocalDate getIscrizione() {
        return iscrizione;
    }

    /**
    Imposta la data di iscrizione dell'accademico
    @param iscrizione la nuova data di iscrizione
    @throws IllegalArgumentException se la data è futura.
    */
    public void setIscrizione(LocalDate iscrizione) {
        this.iscrizione = iscrizione;
    }

    /**
     * Restituisce il corso di laurea associato all'accademico.
     *
     * @return l'oggetto {@link CorsoLaurea}.
     * */
    public CorsoLaurea getCorsoLaurea() {
        return corsoLaurea;
    }

    /** Imposta il corso di laurea associato all'accademico.
     *
     * @param corsoLaurea il nuovo corso di laurea.
     * */
    public void setCorsoLaurea(CorsoLaurea corsoLaurea) {
        this.corsoLaurea = corsoLaurea;
    }

    /**
     * Restituisce la matricola dell'accademico.
     *
     * @return la matricola, come {@link String}.
     * */
    public String getMatricola() {
        return matricola;
    }

    /**
     * Imposta la matricola dell'accademico.
     *
     * @param matricola la nuova matricola.
     * @throws IllegalArgumentException se la matricola è vuota o nulla.
     * @exception IllegalArgumentException
     *               Thrown to indicate that a method has been passed an illegal or inappropriate argument.
     * */
    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    /**
     * Restituisce le conversazioni a cui l'accademico partecipa.
     *
     * @return un {@link Set} di {@link Conversazione}.
     * */
    public Set<Conversazione> getConversazioni() {
        return conversazioni;
    }

    /**
     * Imposta le conversazioni a cui l'accademico partecipa.
     *
     * @param conversazioni il nuovo set di conversazioni
     * */
    public void setConversazioni(Set<Conversazione> conversazioni) {
        this.conversazioni = conversazioni;
    }

    /**
     * Restituisce i messaggi inviati all'accademico.
     *
     * @return un {@link Set} di {@link Messaggio}.
     * */
    public Set<Messaggio> getMessaggiInviati() {
        return messaggiInviati;
    }

    /**
     * Imposta i messaggi inviati dall'accademico.
     *
     * @param messaggiInviati il nuovo set di messaggi inviati.
     * */
    public void setMessaggiInviati(Set<Messaggio> messaggiInviati) {
        this.messaggiInviati = messaggiInviati;
    }

    /** Restituisce i messaggi ricevuti dall'accademico.
     *
     * @return un {@link Set} di {@link Messaggio}.
     * */
    public Set<Messaggio> getMessaggiRicevuti() {
        return messaggiRicevuti;
    }

    /**
     * Imposta i messaggi ricevuti dall'accademico.
     *
     * @param messaggiRicevuti il nuovo set di messaggi ricevuti.
     * */
    public void setMessaggiRicevuti(Set<Messaggio> messaggiRicevuti) {
        this.messaggiRicevuti = messaggiRicevuti;
    }
}
