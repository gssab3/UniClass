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

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
        @NamedQuery(name = TROVA_ACCADEMICO, query = "SELECT a FROM Accademico a WHERE a.matricola = :matricola"),
        @NamedQuery(name = TROVA_TUTTI, query = "SELECT a FROM Accademico a"),
        @NamedQuery(name = TROVA_EMAIL, query = "SELECT a FROM Accademico a WHERE a.email = :email")
})
public class Accademico extends Utente implements Serializable {

    public static final String TROVA_ACCADEMICO = "Accademico.trovaAccademico";
    public static final String TROVA_TUTTI = "Accademico.trovaTutti";
    public static final String TROVA_EMAIL = "Accademico.trovaEmail";

    @Id
    protected String matricola;
    protected LocalDate iscrizione;
    @OneToOne
    @JoinColumn(name = "corso_laurea_id")
    protected CorsoLaurea corsoLaurea;

    @ManyToMany(mappedBy = "messaggeri")
    private Set<Conversazione> conversazioni = new HashSet<>();

    @OneToMany(mappedBy = "autore", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Messaggio> messaggiInviati = new HashSet<>();

    @OneToMany(mappedBy = "destinatario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Messaggio> messaggiRicevuti = new HashSet<>();


    public Accademico() {}


    public LocalDate getIscrizione() {
        return iscrizione;
    }

    public void setIscrizione(LocalDate iscrizione) {
        this.iscrizione = iscrizione;
    }

    public CorsoLaurea getCorsoLaurea() {
        return corsoLaurea;
    }

    public void setCorsoLaurea(CorsoLaurea corsoLaurea) {
        this.corsoLaurea = corsoLaurea;
    }

    public String getMatricola() {
        return matricola;
    }

    public void setMatricola(String matricola) {
        this.matricola = matricola;
    }

    public Set<Conversazione> getConversazioni() {
        return conversazioni;
    }

    public void setConversazioni(Set<Conversazione> conversazioni) {
        this.conversazioni = conversazioni;
    }

    public Set<Messaggio> getMessaggiInviati() {
        return messaggiInviati;
    }

    public void setMessaggiInviati(Set<Messaggio> messaggiInviati) {
        this.messaggiInviati = messaggiInviati;
    }

    public Set<Messaggio> getMessaggiRicevuti() {
        return messaggiRicevuti;
    }

    public void setMessaggiRicevuti(Set<Messaggio> messaggiRicevuti) {
        this.messaggiRicevuti = messaggiRicevuti;
    }
}
