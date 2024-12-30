package it.unisa.uniclass.utenti.model;

import it.unisa.uniclass.conversazioni.model.Conversazione;
import it.unisa.uniclass.conversazioni.model.Messaggio;
import it.unisa.uniclass.orari.model.CorsoLaurea;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Accademico extends Utente implements Serializable {

    @Id
    protected String matricola;
    protected LocalDate iscrizione;
    @OneToOne
    @JoinColumn(name = "corso_laurea_id")
    protected CorsoLaurea corsoLaurea;

    @ManyToMany(mappedBy = "messaggeri")
    private List<Conversazione> conversazioni;

    @OneToMany(mappedBy = "destinatario")
    private List<Messaggio> messaggiRicevuti;

    @OneToMany(mappedBy = "autore")
    private List<Messaggio> messaggiInviati;

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

    public List<Conversazione> getConversazioni() {
        return conversazioni;
    }

    public List<Messaggio> getMessaggiRicevuti() {
        return messaggiRicevuti;
    }

    public List<Messaggio> getMessaggiInviati() {
        return messaggiInviati;
    }
}
